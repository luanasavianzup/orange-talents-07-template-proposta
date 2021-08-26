package br.com.zup.luanasavian.proposta.request;

import br.com.zup.luanasavian.proposta.compartilhada.EncodedFingerprint;
import br.com.zup.luanasavian.proposta.model.Biometria;
import br.com.zup.luanasavian.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class BiometriaFormRequest {

    @NotBlank
    private String fingerprint;

    public BiometriaFormRequest(@JsonProperty(value = "fingerprint") String fingerprint){
        this.fingerprint = fingerprint;
    }

    public Biometria toModel(Cartao cartao){
        return new Biometria(new EncodedFingerprint(fingerprint), cartao);
    }

    public String getFingerPrint() {
        return fingerprint;
    }

}
