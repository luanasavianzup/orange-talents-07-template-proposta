package br.com.zup.luanasavian.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    @NotBlank
    private String origemIp;
    @NotBlank
    private String userAgent;
    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(@NotBlank String origemIp, @NotBlank String userAgent, @NotNull Cartao cartao) {
        this.origemIp = origemIp;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

}
