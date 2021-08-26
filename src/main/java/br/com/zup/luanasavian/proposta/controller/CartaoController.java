package br.com.zup.luanasavian.proposta.controller;

import br.com.zup.luanasavian.proposta.model.Biometria;
import br.com.zup.luanasavian.proposta.model.Cartao;
import br.com.zup.luanasavian.proposta.repository.BiometriaRepository;
import br.com.zup.luanasavian.proposta.repository.CartaoRepository;
import br.com.zup.luanasavian.proposta.request.BiometriaFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BiometriaRepository biometriaRepository;

    @PostMapping("/{cartaoId}/biometrias")
    @Transactional
    public ResponseEntity<?> post(@PathVariable Long cartaoId, @RequestBody @Valid BiometriaFormRequest form, UriComponentsBuilder uriBuilder) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(cartaoId);
        if (cartaoOpt.isEmpty()) return ResponseEntity.status(404).build();

        Cartao cartao = cartaoOpt.get();
        Biometria biometria = form.toModel(cartao);
        biometriaRepository.save(biometria);

        URI uri = uriBuilder.path("/cartoes/{cartaoId}/biometrias/{idBiometria}").buildAndExpand(cartao.getId(), biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
