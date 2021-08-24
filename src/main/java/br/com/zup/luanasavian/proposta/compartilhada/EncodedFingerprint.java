package br.com.zup.luanasavian.proposta.compartilhada;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.constraints.NotBlank;

public class EncodedFingerprint {

    private String fingerprint;

    public EncodedFingerprint(String senhaBase64) {
        if (!Base64.isBase64(senhaBase64))
            throw new IllegalArgumentException("Este não é um base64 válido.");

        this.fingerprint = senhaBase64;
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
