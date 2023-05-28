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

import com.br.alura.forum.domain.resposta.DadosCadastroResposta;
import com.br.alura.forum.domain.resposta.DadosDetalheResposta;
import com.br.alura.forum.domain.resposta.DadosListagemResposta;
import com.br.alura.forum.domain.resposta.Resposta;
import com.br.alura.forum.services.RespostaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/respostas")
public class RespostaController {
	
	@Autowired
	private RespostaService respostaService;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriBuilder) {
		return respostaService.salvar(new Resposta(dados), uriBuilder);
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemResposta>> listar(@PageableDefault(size=10, sort = {"dataCriacao"}) Pageable paginacao, @RequestParam(name = "search") String search) {
		
		return respostaService.listar(paginacao, search);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<DadosDetalheResposta>> detalhe(@PathVariable  Long id) {
		
		return respostaService.detalhe(id);
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosCadastroResposta dados, @PathVariable Long id) {
		return respostaService.atualizar(dados, id);
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		return respostaService.deletar(id);
	}
	
	
}