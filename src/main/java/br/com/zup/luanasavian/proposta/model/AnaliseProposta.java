package br.com.zup.luanasavian.proposta.model;

import br.com.zup.luanasavian.proposta.compartilhada.DevolutivaAnalise;

public class AnaliseProposta {

    private String documento;
    private String nome;
    private DevolutivaAnalise devolutivaAnalise;
    private String idProposta;

    public AnaliseProposta(String documento, String nome, DevolutivaAnalise devolutivaAnalise, String idProposta) {
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
}
