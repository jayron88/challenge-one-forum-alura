package com.br.alura.forum.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HandleErrorException {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity handleError404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleError400(MethodArgumentNotValidException e) {
		var error = e.getFieldErrors();
		
		return ResponseEntity.badRequest().body(error.stream().map(DadosErrorHandler::new).toList());
	}
	
	private record DadosErrorHandler(String campo, String mensagem) {
		public DadosErrorHandler (FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
