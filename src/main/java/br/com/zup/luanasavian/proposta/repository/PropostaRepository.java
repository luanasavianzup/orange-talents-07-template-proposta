package br.com.zup.luanasavian.proposta.repository;

import br.com.zup.luanasavian.proposta.compartilhada.StatusProposta;
import br.com.zup.luanasavian.proposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);
    List<Proposta> findAllByStatus(StatusProposta status);
}
