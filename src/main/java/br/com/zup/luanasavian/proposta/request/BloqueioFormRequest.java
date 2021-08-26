package br.com.zup.luanasavian.proposta.request;

public class BloqueioFormRequest {

    private String sistemaResponsavel;

    public BloqueioFormRequest(){
        this.sistemaResponsavel = "propostas";
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
