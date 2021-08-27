package br.com.zup.luanasavian.proposta.request;

import br.com.zup.luanasavian.proposta.compartilhada.TipoCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociaCarteiraFormRequest {
    @NotBlank
    @Email
    private String email;
    @NotNull
    private TipoCarteira carteira;

    public AssociaCarteiraFormRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
