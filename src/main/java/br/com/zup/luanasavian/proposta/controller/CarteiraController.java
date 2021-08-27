package br.com.zup.luanasavian.proposta.controller;

import br.com.zup.luanasavian.proposta.compartilhada.CartaoClient;
import br.com.zup.luanasavian.proposta.model.Cartao;
import br.com.zup.luanasavian.proposta.model.Carteira;
import br.com.zup.luanasavian.proposta.repository.CartaoRepository;
import br.com.zup.luanasavian.proposta.repository.CarteiraRepository;
import br.com.zup.luanasavian.proposta.request.AssociaCarteiraFormRequest;
import br.com.zup.luanasavian.proposta.request.CarteiraFormRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CarteiraController {
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CarteiraRepository carteiraRepository;
    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/{id}/carteiras")
    public ResponseEntity<?> post(@PathVariable Long id, @RequestBody @Valid CarteiraFormRequest form, UriComponentsBuilder uriBuilder){
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(id);
        if(cartaoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Cartao cartao = cartaoOpt.get();
        if(cartao.associaCarteira(form.getTipoCarteira())) return ResponseEntity.unprocessableEntity().build();

        try{
            AssociaCarteiraFormRequest associa = new AssociaCarteiraFormRequest(form.getEmail(), form.getTipoCarteira());
            cartaoClient.associacaoCarteira(cartao.getNumeroCartao(), associa);
        }catch (FeignException e){
            return ResponseEntity.status(e.status()).body("Associação de carteira não pôde ser realizada");
        }

        Carteira carteira = form.toModel(cartao);
        carteiraRepository.save(carteira);

        URI uri = uriBuilder.path("/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();
        return ResponseEntity.ok().body(uri);
    }

}
