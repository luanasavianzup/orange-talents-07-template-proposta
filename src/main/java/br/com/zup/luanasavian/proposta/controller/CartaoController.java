package br.com.zup.luanasavian.proposta.controller;

import br.com.zup.luanasavian.proposta.compartilhada.StatusCartao;
import br.com.zup.luanasavian.proposta.model.Biometria;
import br.com.zup.luanasavian.proposta.model.BloqueioCartao;
import br.com.zup.luanasavian.proposta.model.Cartao;
import br.com.zup.luanasavian.proposta.repository.BiometriaRepository;
import br.com.zup.luanasavian.proposta.repository.BloqueioRepository;
import br.com.zup.luanasavian.proposta.repository.CartaoRepository;
import br.com.zup.luanasavian.proposta.request.BiometriaFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private BloqueioRepository bloqueioRepository;

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

    @PostMapping("/{cartaoId}/bloqueios")
    public ResponseEntity<?> post(@PathVariable Long cartaoId, HttpServletRequest request) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(cartaoId);

        if (cartaoOpt.isEmpty())
            return ResponseEntity.notFound().build();

        Cartao cartao = cartaoOpt.get();

        if (cartao.getStatus() == StatusCartao.BLOQUEADO) {
            return ResponseEntity.unprocessableEntity().body("Cartão já foi bloqueado");
        }

        String userAgent = request.getHeader("User-Agent");
        String enderecoIp = request.getHeader("X-FORWARDED-FOR");
        if (enderecoIp == null) {
            enderecoIp = request.getRemoteAddr();
        }

        if(userAgent == null || userAgent.isBlank() || enderecoIp.isBlank())
            return ResponseEntity.badRequest().build();

        BloqueioCartao bloqueio = new BloqueioCartao(enderecoIp, userAgent, cartao);

        bloqueioRepository.save(bloqueio);
        cartao.bloqueia();
        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }
}
