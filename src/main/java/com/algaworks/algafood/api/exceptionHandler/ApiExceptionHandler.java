package com.algaworks.algafood.api.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		
	
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders() , HttpStatus.NOT_FOUND , request);

		/*
		 * Problema problema = new Problema();
		 * 
		 * problema.setDataHora(LocalDateTime.now());
		 * problema.setMensagem(e.getMessage());
		 * 
		 * return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
		 */
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {
		
		System.out.println("Entidade em uso");

		Problema problema = new Problema();

		problema.setDataHora(LocalDateTime.now());
		problema.setMensagem(e.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e) {
		System.out.println("Negocio exception");
		Problema problema = new Problema();

		problema.setDataHora(LocalDateTime.now());
		problema.setMensagem(e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		System.out.println("handle");
		
		Problema problema = new Problema();
		
		System.out.println("mensage" + ex.getMessage());
		
		if (body == null) {
			
			System.out.println("handle1");
		 

			problema.setDataHora(LocalDateTime.now());
			problema.setMensagem(status.getReasonPhrase());
			
		} else if (body instanceof String) {
			
			System.out.println("handle2");
			

			problema.setDataHora(LocalDateTime.now());
			problema.setMensagem((String) body);
			
		}
		
		//Problema problema = new Problema();
		
	//	problema.setMensagem(status.getReasonPhrase());
		
	//	body = problema;
		
		body = problema;
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
