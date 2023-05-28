package com.br.alura.forum.domain.topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.br.alura.forum.domain.curso.Curso;
import com.br.alura.forum.domain.resposta.Resposta;
import com.br.alura.forum.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String titulo;
	@Column(unique = true)
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;
	
	@ManyToOne
    @JoinColumn(name = "autor_id")
	private Usuario autor;
	
	@ManyToOne
    @JoinColumn(name = "curso_id")
	private Curso curso;
	
	@JsonIgnore
	@OneToMany(mappedBy = "topico", fetch = FetchType.LAZY)
	private List<Resposta> respostas = new ArrayList<>();
	
	public Topico(DadosCadastroTopico dados) {
		this.titulo = dados.titulo();
		this.mensagem = dados.mensagem();
		this.dataCriacao = LocalDateTime.now();
		this.status = dados.status();
		this.autor = new Usuario(dados.autor());
		this.curso = new Curso(dados.curso());
		
	}
	
	public Topico(Long topico) {
		this.id = topico;
		
	}

	public void atualizarInfo(@Valid DadosCadastroTopico dados) {
		this.titulo = dados.titulo();
		this.mensagem = dados.mensagem();
		this.dataCriacao = LocalDateTime.now();
		this.status = dados.status();
		this.autor = new Usuario(dados.autor());
		this.curso = new Curso(dados.curso());
		
	}
}
