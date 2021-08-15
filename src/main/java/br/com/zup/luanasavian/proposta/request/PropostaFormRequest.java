package br.com.zup.luanasavian.proposta.request;

import br.com.zup.luanasavian.proposta.model.Proposta;
import br.com.zup.luanasavian.proposta.validation.CpfOrCnpj;
import br.com.zup.luanasavian.proposta.validation.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaFormRequest {

    @CpfOrCnpj
    @NotBlank
    private String documento;
    @NotBlank
    @Email
    @UniqueValue(domainClass = Proposta.class, fieldName = "email")
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    public PropostaFormRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome, @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(documento, email, nome, endereco, salario);
    }
}
