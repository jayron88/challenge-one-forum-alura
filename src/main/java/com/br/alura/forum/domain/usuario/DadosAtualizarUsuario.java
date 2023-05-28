package com.br.alura.forum.domain.usuario;

import com.br.alura.forum.domain.topico.StatusTopico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarUsuario(
		@NotNull
		Long id,
		@NotBlank
		String titulo, 
		@NotBlank
		String mensagem,
		@NotBlank
		String data_criacao, 
		@NotNull
		StatusTopico status, 
		@NotNull
		Long autor,
		@NotNull
		Long curso) {

}
