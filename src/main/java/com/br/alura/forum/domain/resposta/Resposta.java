package com.br.alura.forum.domain.resposta;

import java.time.LocalDateTime;

import com.br.alura.forum.domain.topico.Topico;
import com.br.alura.forum.domain.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;
	@ManyToOne
	@JoinColumn(name = "topico_id")
	private Topico topico;
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	private Boolean solucao = false;
	
	
	public Resposta(DadosCadastroResposta dados) {
		this.mensagem = dados.mensagem();
		this.topico = new Topico(dados.topico());
		this.dataCriacao = LocalDateTime.now();
		this.autor = new Usuario(dados.autor());
		this.solucao = dados.solucao();
	}
	
	public void atualizarInfo(@Valid DadosCadastroResposta dados) {
		this.mensagem = dados.mensagem();
		this.topico = new Topico(dados.topico());
		this.dataCriacao = LocalDateTime.now();
		this.autor = new Usuario(dados.autor());
		this.solucao = dados.solucao();
		
	}

}
