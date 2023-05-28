package com.br.alura.forum.domain.usuario;

public record DadosDetalheUsuario(
		Long id,
		String nome, 
		String email
		) {
	
	public DadosDetalheUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getEmail());
	}
}
