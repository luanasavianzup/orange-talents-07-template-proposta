package br.com.zup.luanasavian.proposta.compartilhada;

import br.com.zup.luanasavian.proposta.model.AnaliseProposta;
import br.com.zup.luanasavian.proposta.response.AnalisePropostaResponse;
import br.com.zup.luanasavian.proposta.request.AnalisePropostaFormRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnalisePropostaService {

    public AnaliseProposta analiseCredito(AnalisePropostaFormRequest form) throws ResponseStatusException {

        String analiseCreditoUrl = "http://localhost:9999/api/solicitacao";

        try {
            RestTemplate restTemplate = new RestTemplate();
            AnalisePropostaResponse analiseResponse = restTemplate.postForObject(
                    analiseCreditoUrl, form, AnalisePropostaResponse.class);

            AnaliseProposta analise = analiseResponse.toModel();

            return analise;

        } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro durante a análise");
            }
        }
}
