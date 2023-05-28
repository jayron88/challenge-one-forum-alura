package com.br.alura.forum.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.forum.domain.usuario.DadosCadastroUsuario;
import com.br.alura.forum.domain.usuario.DadosListagemUsuario;
import com.br.alura.forum.domain.usuario.Usuario;
import com.br.alura.forum.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
		
		return usuarioService.salvar(new Usuario(dados), uriBuilder);
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size=10, sort = {"email"}) Pageable paginacao, @RequestParam(name = "search") String search) {
		
		return usuarioService.listar(paginacao, search);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<DadosListagemUsuario>> detalhe(@PathVariable  Long id) {
		
		return usuarioService.detalhe(id);
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosCadastroUsuario dados, @PathVariable Long id) {
		return usuarioService.atualizar(dados, id);
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		return usuarioService.deletar(id);
	}
	
	
}