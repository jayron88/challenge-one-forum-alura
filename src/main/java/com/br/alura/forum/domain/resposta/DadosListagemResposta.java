package com.br.alura.forum.domain.resposta;

import java.time.LocalDateTime;

import com.br.alura.forum.domain.topico.Topico;
import com.br.alura.forum.domain.usuario.Usuario;

public record DadosListagemResposta(Long id, String mensagem,Topico topico, LocalDateTime data_criacao, Usuario autor, Boolean solucao) {
	public DadosListagemResposta(Resposta resposta) {
		this(resposta.getId(), resposta.getMensagem(), resposta.getTopico(), resposta.getDataCriacao(), resposta.getAutor(), resposta.getSolucao());
	}
	
	
}
