package br.com.zup.luanasavian.proposta.request;

public class AnalisePropostaFormRequest {

    private String documento;
    private String nome;
    private String idProposta;

    public AnalisePropostaFormRequest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
