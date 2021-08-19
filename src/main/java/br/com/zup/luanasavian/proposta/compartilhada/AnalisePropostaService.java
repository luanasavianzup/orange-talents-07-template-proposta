package br.com.zup.luanasavian.proposta.compartilhada;

import br.com.zup.luanasavian.proposta.model.AnaliseProposta;
import br.com.zup.luanasavian.proposta.model.Proposta;
import br.com.zup.luanasavian.proposta.response.AnalisePropostaResponse;
import br.com.zup.luanasavian.proposta.request.AnalisePropostaFormRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnalisePropostaService {

  @Autowired
  AnaliseClient analiseClient;

  public DevolutivaAnalise avalia(Proposta proposta) {
      AnalisePropostaFormRequest form = new AnalisePropostaFormRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId().toString());

      try {
          AnalisePropostaResponse response = analiseClient.analisa(form);
          return DevolutivaAnalise.SEM_RESTRICAO;
      }
      catch (FeignException e) {
          if(e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
              return DevolutivaAnalise.COM_RESTRICAO;
          } else {
              throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }
  }

}
