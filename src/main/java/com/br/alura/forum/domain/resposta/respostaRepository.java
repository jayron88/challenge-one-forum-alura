package com.br.alura.forum.domain.resposta;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface respostaRepository extends JpaRepository<Resposta, Long> {
	@Query("SELECT r FROM Resposta r WHERE r.dataCriacao LIKE %:search%")
	Page<Resposta> findByDataCriacao(String search, Pageable paginacao);
}
