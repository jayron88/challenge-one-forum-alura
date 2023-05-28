package com.br.alura.forum.domain.topico;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	@Query("SELECT t FROM Topico t WHERE t.titulo LIKE %:search%")
	Page<Topico> findByTitle(String search, Pageable paginacao);
}
