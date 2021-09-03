package br.com.zup.luanasavian.proposta.model;

import br.com.zup.luanasavian.proposta.compartilhada.Criptografia;
import br.com.zup.luanasavian.proposta.compartilhada.DevolutivaAnalise;
import br.com.zup.luanasavian.proposta.compartilhada.StatusProposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String documento;
    @NotBlank
    private String documentoHash;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private StatusProposta status;
    @OneToOne(mappedBy = "proposta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        Criptografia criptografia = Criptografia.getInstance();
        this.documento = criptografia.criptografa(documento);
        this.documentoHash = criptografia.getHash(documento);
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        Criptografia criptografia = Criptografia.getInstance();
        return criptografia.descriptografa(documento);
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProposta getStatus() {
        return status;
    }
    public Cartao getCartao() {
        return cartao;
    }

    public void novoEstado(DevolutivaAnalise devolutivaAnalise) {
        this.status = devolutivaAnalise.getResultado();
    }

    public void adicionaCartao() {
        this.status = StatusProposta.CARTAO_APROVADO;
    }
}
