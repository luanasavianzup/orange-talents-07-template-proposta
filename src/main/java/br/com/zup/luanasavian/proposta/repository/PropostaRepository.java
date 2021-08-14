package br.com.zup.luanasavian.proposta.repository;

import br.com.zup.luanasavian.proposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
