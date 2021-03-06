package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

//GET /cozinhas HTTP/1.1 

//@Controller
//@ResponseBody
//Vale pelas duas anotacoes acima
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@GetMapping
	public List<Cozinha> listar() {
		
	//	System.out.println("passou na cozinha 2");
		
      Cozinha cozinha =  new Cozinha();
		
	//	cozinha.setNome("Asiatica");
      
    //  ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withMatcher(cozinha.getNome() , match -> match.contains().ignoreCase());
	
		
	  //  Example<Cozinha> example = Example.of(cozinha,ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

	//	Example<Cozinha> example = Example.of(cozinha);
		
	  //  return cozinhaRepository.findAll(example);
		
	//	return cozinhaRepository.findAll((Sort) example);
		
		
		
		return cozinhaRepository.findAll();

	}

//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public CozinhasXmlWrapper listarXml() {
//
//		return new CozinhasXmlWrapper(cozinhaRepository.findAll());
//
//	}

	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
		
		return cadastroCozinha.buscarOuFalhar(id);
		
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable("cozinhaId") Long id, @RequestBody Cozinha cozinha) {

		
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(id);
		
		
		//Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

		// cozinhaAtual.setNome(cozinha.getNome());
		// Copia os valores da proprieda e joga na Atual -- Set

//		if (cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual,"id");
			
			return cadastroCozinha.salvar(cozinhaAtual);

//			return ResponseEntity.ok(cozinhaSalva);
//		}

	//	return ResponseEntity.notFound().build();

	}

	/*
	 * @DeleteMapping("/{cozinhaId}") public ResponseEntity<?>
	 * remover(@PathVariable("cozinhaId") Long id) {
	 * 
	 * try {
	 * 
	 * cadastroCozinha.excluir(id); return ResponseEntity.noContent().build();
	 * 
	 * } catch (EntidadeEmUsoException e) { return
	 * ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); } catch
	 * (EntidadeNaoEncontradaException e) { return
	 * ResponseEntity.notFound().build(); }
	 * 
	 * }
	 */
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}

}
