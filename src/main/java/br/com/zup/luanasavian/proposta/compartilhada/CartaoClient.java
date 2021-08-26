package br.com.zup.luanasavian.proposta.compartilhada;

import br.com.zup.luanasavian.proposta.request.BloqueioFormRequest;
import br.com.zup.luanasavian.proposta.response.BloqueioResponse;
import br.com.zup.luanasavian.proposta.response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartaoClient",url = "${system.cartaoClient}")
public interface CartaoClient {
    @GetMapping("/api/cartoes")
    CartaoResponse consultaCartao(@RequestParam String idProposta);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    BloqueioResponse executaBloqueioCartao(@PathVariable String id, @RequestBody BloqueioFormRequest form);
}
