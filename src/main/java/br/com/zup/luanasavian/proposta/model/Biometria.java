package br.com.zup.luanasavian.proposta.model;

import br.com.zup.luanasavian.proposta.compartilhada.EncodedFingerprint;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String fingerprint;
    private LocalDateTime criadoEm = LocalDateTime.now();
    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(EncodedFingerprint fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint.getFingerprint();
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
