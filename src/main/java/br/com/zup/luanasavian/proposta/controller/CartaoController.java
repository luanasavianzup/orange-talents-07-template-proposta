package br.com.zup.luanasavian.proposta.controller;

import br.com.zup.luanasavian.proposta.compartilhada.CartaoClient;
import br.com.zup.luanasavian.proposta.compartilhada.StatusCartao;
import br.com.zup.luanasavian.proposta.model.*;
import br.com.zup.luanasavian.proposta.repository.AvisoViagemRepository;
import br.com.zup.luanasavian.proposta.repository.BiometriaRepository;
import br.com.zup.luanasavian.proposta.repository.BloqueioRepository;
import br.com.zup.luanasavian.proposta.repository.CartaoRepository;
import br.com.zup.luanasavian.proposta.request.AvisoViagemFormRequest;
import br.com.zup.luanasavian.proposta.request.BiometriaFormRequest;
import br.com.zup.luanasavian.proposta.request.EnviaAvisoFormRequest;
import feign.FeignException;
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
    @Autowired
    private AvisoViagemRepository avisoViagemRepository;
    @Autowired
    private CartaoClient cartaoClient;

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
        cartao.bloqueiaLocal();
        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idCartao}/avisos")
    @Transactional
    public ResponseEntity<?> post(@PathVariable Long idCartao, @RequestBody @Valid AvisoViagemFormRequest form, HttpServletRequest request) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(idCartao);

        if (cartaoOpt.isEmpty())
            return ResponseEntity.notFound().build();

        Cartao cartao = cartaoOpt.get();

        try{
            EnviaAvisoFormRequest avisoRequest = new EnviaAvisoFormRequest(form.getDestino(),form.getValidoAte());
            cartaoClient.enviaAvisoViagem(cartao.getNumeroCartao(),avisoRequest);
        }catch (FeignException e){
            return ResponseEntity.status(e.status()).body("Sua solicitação não foi processada");
        }

        String userAgent = request.getHeader("User-Agent");
        String origemIp = request.getHeader("X-FORWARDED-FOR");
        if (origemIp == null) {
            origemIp = request.getRemoteAddr();
        }

        AvisoViagem avisoViagem = form.toModel(origemIp,userAgent,cartao);
        avisoViagemRepository.save(avisoViagem);

        return ResponseEntity.ok().build();
    }
}
