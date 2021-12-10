package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;


//GET /cozinhas HTTP/1.1 

//@Controller
//@ResponseBody
//Vale pelas duas anotacoes acima
@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@GetMapping
	public List<FormaPagamento> listar(){
		
		return formaPagamentoRepository.listar();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	// ? aceita o retorno de qualquer tipo
	public FormaPagamento adicionar(@RequestBody @Valid FormaPagamento formaPagamento) {
		try {
			// restaurante = cadastroRestaurante.salvar(restaurante);
			return formaPagamentoRepository.save(formaPagamento);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

}
