package br.com.zup.luanasavian.proposta.model;

import br.com.zup.luanasavian.proposta.compartilhada.TipoCarteira;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCarteira tipo;
    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira(){}

    public Carteira(TipoCarteira tipo, Cartao cartao) {
        this.tipo = tipo;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public TipoCarteira getTipo() {
        return tipo;
    }
}
