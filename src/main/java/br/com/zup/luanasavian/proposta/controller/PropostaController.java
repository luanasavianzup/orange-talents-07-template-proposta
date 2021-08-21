package br.com.zup.luanasavian.proposta.controller;

import br.com.zup.luanasavian.proposta.compartilhada.AnalisePropostaService;
import br.com.zup.luanasavian.proposta.compartilhada.DevolutivaAnalise;
import br.com.zup.luanasavian.proposta.model.Proposta;
import br.com.zup.luanasavian.proposta.repository.PropostaRepository;
import br.com.zup.luanasavian.proposta.request.PropostaFormRequest;
import br.com.zup.luanasavian.proposta.response.AcompanhamentoPropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private AnalisePropostaService analisePropostaService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> post(@RequestBody @Valid PropostaFormRequest form, UriComponentsBuilder uriBuilder) {
        Optional<Proposta> novaProposta = propostaRepository.findByDocumento(form.getDocumento());
        if (novaProposta.isPresent()) return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Foi encontrada mais de uma proposta para o CPF ou CNPJ cadastrado!");

        Proposta proposta = form.toModel();
        propostaRepository.save(proposta);

        DevolutivaAnalise devolutiva = analisePropostaService.avalia(proposta);
        proposta.novoEstado(devolutiva);

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcompanhamentoPropostaResponse> get(@PathVariable Long id){
        Optional<Proposta> proposta = propostaRepository.findById(id);

        if(proposta.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        AcompanhamentoPropostaResponse response = new AcompanhamentoPropostaResponse(proposta.get().getStatus());

        return ResponseEntity.ok(response);
    }

}
