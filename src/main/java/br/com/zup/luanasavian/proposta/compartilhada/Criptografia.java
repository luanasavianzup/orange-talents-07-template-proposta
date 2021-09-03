package br.com.zup.luanasavian.proposta.compartilhada;

import org.jasypt.util.text.AES256TextEncryptor;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Criptografia {
    private static Criptografia instance = null;

    public static synchronized Criptografia getInstance() {
        if(instance == null)
            instance = new Criptografia();

        return instance;
    }

    AES256TextEncryptor textEncryptor = new AES256TextEncryptor();

    public String criptografa(@NotBlank String entrada) {
        textEncryptor.setPassword("${CRIPTOGRAFIA_SECRET}");
        String documentoCriptografado = textEncryptor.encrypt(entrada);
        return documentoCriptografado;
    }

    public String descriptografa(String entradaCriptografada) {
        textEncryptor.setPassword("${CRIPTOGRAFIA_SECRET}");
        String documentoCriptografado = textEncryptor.decrypt(entradaCriptografada);
        return documentoCriptografado;
    }

    public static String getHash(@NotBlank String entrada){
        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(entrada.getBytes(StandardCharsets.UTF_8));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

}
