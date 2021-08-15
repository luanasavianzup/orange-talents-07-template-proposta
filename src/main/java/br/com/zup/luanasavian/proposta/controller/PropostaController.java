package br.com.zup.luanasavian.proposta.controller;

import br.com.zup.luanasavian.proposta.model.Proposta;
import br.com.zup.luanasavian.proposta.repository.PropostaRepository;
import br.com.zup.luanasavian.proposta.request.PropostaFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> post(@RequestBody @Valid PropostaFormRequest form, UriComponentsBuilder uriBuilder) {
        Proposta proposta = form.toModel();
        propostaRepository.save(proposta);

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
