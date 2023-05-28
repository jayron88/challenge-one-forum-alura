package com.br.alura.forum.domain.topico;

import java.time.LocalDateTime;

import com.br.alura.forum.domain.curso.Curso;
import com.br.alura.forum.domain.usuario.Usuario;

public record DadosListagemTopico(String titulo, String mensagem,LocalDateTime data_criacao, StatusTopico status, Usuario autor,Curso curso) {
	public DadosListagemTopico(Topico topico) {
		this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
	}
	
	
}
