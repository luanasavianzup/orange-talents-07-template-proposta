package br.com.zup.luanasavian.proposta.repository;

import br.com.zup.luanasavian.proposta.compartilhada.StatusCartao;
import br.com.zup.luanasavian.proposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findAllByStatus(StatusCartao statusCartao);
}
