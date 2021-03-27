package com.algaworks.algafood.api.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch(
					(MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
	
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
			MethodArgumentTypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

		Problema problem = problemBuilder(status, problemType, detail);
					
				
	    //createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}


	private Problema problemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		Problema problem = new Problema();
		
		problem.setDetail(detail);
		problem.setTitle(problemType.getTitle());
		problem.setType(problemType.getUri());
		problem.setStatus(status.value());
		return problem;
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
		
		
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
		
	//	Problema problema = new Problema();
		
		System.out.println("mensage" + ex.getMessage());
		
		if (body == null) {
			
			System.out.println("handle1");
		 
			Problema problema = new Problema();
			problema.setDataHora(LocalDateTime.now());
			problema.setMensagem(status.getReasonPhrase());
			body = problema;
			
		} else if (body instanceof String) {
			
			System.out.println("handle2");
			
			Problema problema = new Problema();
			problema.setDataHora(LocalDateTime.now());
			problema.setMensagem((String) body);
			body = problema;
			
		}
		
		//Problema problema = new Problema();
		
	//	problema.setMensagem(status.getReasonPhrase());
		
	//	body = problema;
		
		
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
