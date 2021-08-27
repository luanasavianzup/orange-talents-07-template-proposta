package br.com.zup.luanasavian.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotNull
    @NotBlank
    private String destino;
    @Future
    private LocalDate validoAte;
    private LocalDateTime avisadoEm = LocalDateTime.now();
    @NotNull
    @NotBlank
    private String origemIp;
    @NotNull
    @NotBlank
    private String userAgent;
    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(String destino, LocalDate validoAte, String origemIp, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.validoAte = validoAte;
        this.origemIp = origemIp;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
