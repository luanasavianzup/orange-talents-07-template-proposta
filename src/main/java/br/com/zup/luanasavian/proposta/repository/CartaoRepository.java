package br.com.zup.luanasavian.proposta.repository;

import br.com.zup.luanasavian.proposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
