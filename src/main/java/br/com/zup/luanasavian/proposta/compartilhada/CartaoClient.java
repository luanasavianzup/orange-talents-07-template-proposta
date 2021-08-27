package br.com.zup.luanasavian.proposta.compartilhada;

import br.com.zup.luanasavian.proposta.model.EnviaAvisoFormRequest;
import br.com.zup.luanasavian.proposta.request.BloqueioFormRequest;
import br.com.zup.luanasavian.proposta.response.AvisoViagemResponse;
import br.com.zup.luanasavian.proposta.response.BloqueioResponse;
import br.com.zup.luanasavian.proposta.response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "cartaoClient",url = "${system.cartaoClient}")
public interface CartaoClient {
    @GetMapping("/api/cartoes")
    CartaoResponse consultaCartao(@RequestParam String idProposta);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    BloqueioResponse executaBloqueioCartao(@PathVariable String id, @RequestBody BloqueioFormRequest form);

    @PostMapping("/api/cartoes/{id}/avisos")
    AvisoViagemResponse enviaAvisoViagem(@PathVariable String id, @RequestBody @Valid EnviaAvisoFormRequest form);
}
