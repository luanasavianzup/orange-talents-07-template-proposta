package br.com.zup.luanasavian.proposta.request;

import br.com.zup.luanasavian.proposta.compartilhada.TipoCarteira;
import br.com.zup.luanasavian.proposta.model.Cartao;
import br.com.zup.luanasavian.proposta.model.Carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraFormRequest {
    @NotBlank
    @Email
    private String email;
    @NotNull
    private TipoCarteira tipoCarteira;

    public CarteiraFormRequest(String email, TipoCarteira tipoCarteira) {
        this.email = email;
        this.tipoCarteira = tipoCarteira;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(tipoCarteira, cartao);
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }
}
