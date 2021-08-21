package br.com.zup.luanasavian.proposta.compartilhada;

import br.com.zup.luanasavian.proposta.model.Cartao;
import br.com.zup.luanasavian.proposta.model.Proposta;
import br.com.zup.luanasavian.proposta.repository.CartaoRepository;
import br.com.zup.luanasavian.proposta.repository.PropostaRepository;
import br.com.zup.luanasavian.proposta.request.AnalisePropostaFormRequest;
import br.com.zup.luanasavian.proposta.response.AnalisePropostaResponse;
import br.com.zup.luanasavian.proposta.response.CartaoResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnaliseFinanceira {
    @Autowired
    private CartaoClient cartaoClient;
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CartaoRepository cartaoRepository;

    Logger log = LoggerFactory.getLogger(AnaliseFinanceira.class);

    @Scheduled(fixedDelay = 60000)
    public void vinculaCartaoAProposta() {
        List<Proposta> propostas = propostaRepository.findAllByStatus(StatusProposta.ELEGIVEL);
        propostas.forEach(proposta -> {
            try {
                CartaoResponse response = cartaoClient.consultaCartao(proposta.getId().toString());
                Cartao cartao = response.toModel(proposta);
                cartaoRepository.save(cartao);
                log.info("Vinculação concluída. Proposta: " + proposta.getId());
            } catch (FeignException ex) {
                log.error("Erro de vinculação do cartão. Proposta: " + proposta.getId());
            }
        });
    }
}
