package br.com.zup.luanasavian.proposta.response;

import br.com.zup.luanasavian.proposta.compartilhada.DevolutivaAnalise;
import br.com.zup.luanasavian.proposta.model.AnaliseProposta;

public class AnalisePropostaResponse {

    private String documento;
    private String nome;
    private DevolutivaAnalise devolutivaAnalise;
    private String idProposta;

    public AnalisePropostaResponse(String documento, String nome, DevolutivaAnalise devolutivaAnalise, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.devolutivaAnalise = devolutivaAnalise;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public DevolutivaAnalise getDevolutivaAnalise() {
        return devolutivaAnalise;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public AnaliseProposta toModel() {
        return new AnaliseProposta(documento, nome, devolutivaAnalise, idProposta);
    }
}
