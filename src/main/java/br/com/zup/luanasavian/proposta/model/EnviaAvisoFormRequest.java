package br.com.zup.luanasavian.proposta.model;

import br.com.zup.luanasavian.proposta.request.AvisoViagemFormRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class EnviaAvisoFormRequest {

    @NotBlank
    private String destino;
    @NotNull
    private LocalDate validoAte;

    public EnviaAvisoFormRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
