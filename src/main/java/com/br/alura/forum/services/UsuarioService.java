package com.br.alura.forum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.forum.domain.usuario.DadosCadastroUsuario;
import com.br.alura.forum.domain.usuario.DadosDetalheUsuario;
import com.br.alura.forum.domain.usuario.DadosListagemUsuario;
import com.br.alura.forum.domain.usuario.Usuario;
import com.br.alura.forum.domain.usuario.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public ResponseEntity salvar(Usuario usuario, UriComponentsBuilder uriBuilder) {
		repository.save(usuario);
		var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalheUsuario(usuario));
	}
	
	public ResponseEntity<Page<DadosListagemUsuario>> listar(Pageable paginacao, String search) {
		if(search != "") {
			return ResponseEntity.ok(repository.findByEmail(search, paginacao).map(DadosListagemUsuario::new));
		}
		
		return ResponseEntity.ok(repository.findAll(paginacao).map(DadosListagemUsuario::new));
	}
	
	public ResponseEntity detalhe(Long id) {
		var usuario = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosListagemUsuario(usuario));
	}
	
	public ResponseEntity atualizar(DadosCadastroUsuario dados, Long id) {
		var usuario = repository.getReferenceById(id);
		usuario.atualizarInfo(dados);
		
		return ResponseEntity.ok(new DadosDetalheUsuario(usuario));
	}
	
	public ResponseEntity deletar(Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
