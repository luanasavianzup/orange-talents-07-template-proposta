package br.com.zup.luanasavian.proposta.response;

import br.com.zup.luanasavian.proposta.compartilhada.StatusProposta;

public class AcompanhamentoPropostaResponse {

    private StatusProposta status;

    public AcompanhamentoPropostaResponse(StatusProposta status) {
        this.status = status;
    }

    public StatusProposta getStatus() {
        return status;
    }
}
