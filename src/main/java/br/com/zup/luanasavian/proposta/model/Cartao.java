package br.com.zup.luanasavian.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Cartao {
    @Id
    private String id;
    private LocalDateTime emitidoEm = LocalDateTime.now();
    private String titular;
    private Integer limite;

    @OneToOne(cascade = {CascadeType.ALL})
    private Vencimento vencimento;

    @OneToOne
    @NotNull
    private Proposta proposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, Integer limite, Vencimento vencimento, Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }

    public String getId() {
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
}
