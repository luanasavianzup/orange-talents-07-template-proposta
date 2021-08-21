package br.com.zup.luanasavian.proposta.compartilhada;

import br.com.zup.luanasavian.proposta.response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartaoClient",url = "http://localhost:8888")
public interface CartaoClient {
    @GetMapping("/api/cartoes")
    CartaoResponse consultaCartao(@RequestParam String idProposta);
}
