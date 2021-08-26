package br.com.zup.luanasavian.proposta.response;

import br.com.zup.luanasavian.proposta.model.Cartao;
import br.com.zup.luanasavian.proposta.model.Proposta;
import br.com.zup.luanasavian.proposta.model.Vencimento;

import java.time.LocalDateTime;

public class CartaoResponse {
    private String numeroCartao;
    private LocalDateTime emitidoEm;
    private String titular;
    private Integer limite;
    private VencimentoResponse vencimento;
    private String idProposta;

    public CartaoResponse(String numeroCartao, LocalDateTime emitidoEm, String titular, Integer limite, VencimentoResponse vencimento, String idProposta) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public Cartao toModel(Proposta proposta) {
        Vencimento vencimento = this.vencimento.toModel();
        return new Cartao(numeroCartao, emitidoEm, titular, limite, vencimento, proposta);
    }

    public String getNumeroCartao() {
        return numeroCartao;
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

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
