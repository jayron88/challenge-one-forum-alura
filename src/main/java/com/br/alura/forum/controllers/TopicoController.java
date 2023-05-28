package com.br.alura.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.br.alura.forum.domain.topico.DadosCadastroTopico;
import com.br.alura.forum.domain.topico.Topico;
import com.br.alura.forum.services.TopicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoService topicoService;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
		return topicoService.salvar(new Topico(dados), uriBuilder);
	}
	
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size=10, sort = {"dataCriacao"}) Pageable paginacao, @RequestParam(name = "search") String search) {
		
		return topicoService.listar(paginacao, search);
	}
	
	@GetMapping("{id}")
	public ResponseEntity detalhe(@PathVariable  Long id) {
		
		return topicoService.detalhe(id);
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosCadastroTopico dados, @PathVariable Long id) {
		return topicoService.atualizar(dados, id);
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		return topicoService.deletar(id);
	}
	
	
}