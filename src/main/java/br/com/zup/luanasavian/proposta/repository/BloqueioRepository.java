package br.com.zup.luanasavian.proposta.repository;

import br.com.zup.luanasavian.proposta.model.BloqueioCartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueioRepository extends JpaRepository<BloqueioCartao, Long> {
}
