package br.com.zup.luanasavian.proposta.handler;

public class ValidationErrorDto {

    private String campo;
    private String erro;

    public ValidationErrorDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
