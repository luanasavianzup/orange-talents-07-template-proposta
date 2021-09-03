package br.com.zup.luanasavian.proposta.compartilhada;

public class DadoExistenteException extends Exception {

    private String message;

    public DadoExistenteException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
