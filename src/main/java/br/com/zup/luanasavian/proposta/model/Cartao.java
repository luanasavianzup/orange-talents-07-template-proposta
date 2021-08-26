package br.com.zup.luanasavian.proposta.model;

import br.com.zup.luanasavian.proposta.compartilhada.StatusCartao;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCartao;
    private LocalDateTime emitidoEm = LocalDateTime.now();
    private String titular;
    private Integer limite;
    @OneToOne(cascade = {CascadeType.ALL})
    private Vencimento vencimento;
    @OneToOne
    @NotNull
    private Proposta proposta;
    @NotNull
    @Enumerated
    private StatusCartao status;
    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.ALL})
    private List<Biometria> biometria = new ArrayList<>();
    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<BloqueioCartao> bloqueio = new ArrayList<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, Integer limite, Vencimento vencimento, Proposta proposta) {
        this.numeroCartao = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.proposta = proposta;
        this.status = StatusCartao.ATIVO;
    }

    public Long getId() {
        return id;
    }
    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Integer getLimite() {
        return limite;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public StatusCartao getStatus() {
        return status;
    }

    public List<Biometria> getBiometria() {
        return biometria;
    }
    public List<BloqueioCartao> getBloqueio() {
        return bloqueio;
    }

    public void bloqueiaLocal() {
        this.status = StatusCartao.EM_ESPERA;
    }

    public void bloqueio() {
        this.status = StatusCartao.BLOQUEADO;
    }
}
