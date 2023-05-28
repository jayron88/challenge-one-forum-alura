package com.br.alura.forum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.forum.domain.topico.DadosCadastroTopico;
import com.br.alura.forum.domain.topico.DadosDetalheTopico;
import com.br.alura.forum.domain.topico.DadosListagemTopico;
import com.br.alura.forum.domain.topico.Topico;
import com.br.alura.forum.domain.topico.TopicoRepository;

@Service
public class TopicoService {
	
	@Autowired
	private TopicoRepository repository;
	
	public ResponseEntity salvar(Topico topico, UriComponentsBuilder uriBuilder) {
		repository.save(topico);
		
		var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalheTopico(topico));
	}
	
	public ResponseEntity<Page<DadosListagemTopico>> listar(Pageable paginacao, String search) {
		if(search != "") {
			return ResponseEntity.ok(repository.findByTitle(search, paginacao).map(DadosListagemTopico::new));
		}
		
		return ResponseEntity.ok(repository.findAll(paginacao).map(DadosListagemTopico::new));
	}
	
	public ResponseEntity<DadosListagemTopico> detalhe(Long id) {
		var topico = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosListagemTopico(topico));
	}
	
	public ResponseEntity atualizar(DadosCadastroTopico dados, Long id) {
		var topico = repository.getReferenceById(id);
		topico.atualizarInfo(dados);
		
		return ResponseEntity.ok(new DadosDetalheTopico(topico));
	}
	
	public ResponseEntity deletar(Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
