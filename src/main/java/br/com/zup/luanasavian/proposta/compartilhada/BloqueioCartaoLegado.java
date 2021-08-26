package br.com.zup.luanasavian.proposta.compartilhada;

import br.com.zup.luanasavian.proposta.model.Cartao;
import br.com.zup.luanasavian.proposta.repository.CartaoRepository;
import br.com.zup.luanasavian.proposta.request.BloqueioFormRequest;
import br.com.zup.luanasavian.proposta.response.BloqueioResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloqueioCartaoLegado {
    Logger log = LoggerFactory.getLogger(BloqueioCartaoLegado.class);
    @Autowired
    CartaoClient client;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedDelay = 50000)
    private void bloqueioCartoes() {
        BloqueioFormRequest form = new BloqueioFormRequest();

        List<Cartao> listaBloqueioEmEspera = cartaoRepository.findAllByStatus(StatusCartao.EM_ESPERA);

        listaBloqueioEmEspera.forEach(cartao -> {
            try {
                BloqueioResponse response = client.executaBloqueioCartao(cartao.getNumeroCartao(), form);
                cartao.bloqueio();
                cartaoRepository.save(cartao);
                log.info("Cartão " + cartao.getId() + " bloqueado no sistema legado.");
            } catch (FeignException e) {
                log.error("Erro ao bloquear o cartão " + cartao.getId() + " no sistema legado.");
            }
        });
    }
}
