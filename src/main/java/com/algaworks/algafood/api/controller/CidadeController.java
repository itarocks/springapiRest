package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired	
	EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	
	@GetMapping()
	public List<Cidade> Listar() {

		List<Cidade> cidades = cidadeRepository.findAll();

		return cidades;

	}

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		
			return cadastroCidade.salvar(cidade);
		
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		
		
		cadastroCidade.excluir(cidadeId);
		
	}
	
	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable("cidadeId") Long id) {
		
		return cadastroCidade.buscarOuFalhar(id);
		
	}

}
