package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;


//GET /cozinhas HTTP/1.1 

//@Controller
//@ResponseBody
//Vale pelas duas anotacoes acima
@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> listar(){
		
		return estadoRepository.listar();
		
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado cidade) {
		
		return estadoRepository.salvar(cidade);
	}

}
