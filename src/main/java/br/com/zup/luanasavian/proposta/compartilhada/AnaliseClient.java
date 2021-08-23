package br.com.zup.luanasavian.proposta.compartilhada;

import br.com.zup.luanasavian.proposta.request.AnalisePropostaFormRequest;
import br.com.zup.luanasavian.proposta.response.AnalisePropostaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "AnaliseClient", url = "${system.analiseClient}")
public interface AnaliseClient {

    @PostMapping("/api/solicitacao")
    AnalisePropostaResponse analisa(AnalisePropostaFormRequest form);
}
