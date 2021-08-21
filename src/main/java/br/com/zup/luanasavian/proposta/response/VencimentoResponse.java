package br.com.zup.luanasavian.proposta.response;

import br.com.zup.luanasavian.proposta.model.Vencimento;

import java.time.LocalDateTime;

public class VencimentoResponse {

    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    public VencimentoResponse(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento toModel() {
        return new Vencimento(id, dia, dataDeCriacao);
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
