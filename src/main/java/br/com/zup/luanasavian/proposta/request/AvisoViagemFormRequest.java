package br.com.zup.luanasavian.proposta.request;

import br.com.zup.luanasavian.proposta.model.AvisoViagem;
import br.com.zup.luanasavian.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemFormRequest {
    @NotBlank
    private String destino;
    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validoAte;

    public AvisoViagemFormRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public AvisoViagem toModel(String origemIp, String userAgent, Cartao cartao){
        return new AvisoViagem(destino, validoAte, origemIp, userAgent, cartao);
    }
}
