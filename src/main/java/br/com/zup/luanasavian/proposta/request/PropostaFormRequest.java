package br.com.zup.luanasavian.proposta.request;

import br.com.zup.luanasavian.proposta.compartilhada.Criptografia;
import br.com.zup.luanasavian.proposta.compartilhada.DadoExistenteException;
import br.com.zup.luanasavian.proposta.model.Proposta;
import br.com.zup.luanasavian.proposta.repository.PropostaRepository;
import br.com.zup.luanasavian.proposta.validation.CpfOrCnpj;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaFormRequest {

    @CpfOrCnpj
    @NotBlank
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    public PropostaFormRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome, @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(PropostaRepository propostaRepository) throws DadoExistenteException {
        String documentoHash = Criptografia.getInstance().getHash(documento);
        if(propostaRepository.existsByDocumentoHash(documentoHash))
             throw new DadoExistenteException("Já existe uma proposta para esse documento");
        return new Proposta(documento, email, nome, endereco, salario);
    }

    public String getDocumento() {
        return documento;
    }
}
