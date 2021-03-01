package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Controller
//@ResponseBody
//Vale pelas duas anotacoes acima
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping()
	public List<Restaurante> Listar() {

		List<Restaurante> restaurantes = restauranteRepository.findAll();
		
		return restaurantes;

	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable("restauranteId") Long id) {

		Optional<Restaurante> restaurante = restauranteRepository.findById(id);

		// return ResponseEntity.status(HttpStatus.OK).body(cozinha);

		if (restaurante.isPresent())
			return ResponseEntity.ok(restaurante.get());

		return ResponseEntity.notFound().build();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	// ? aceita o retorno de qualquer tipo
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable("restauranteId") Long restauranteId,
			@RequestBody Restaurante restaurante) {

		try {
			Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

			if (! restauranteAtual.isPresent()) {

				return ResponseEntity.notFound().build();

			}

			// cozinhaAtual.setNome(cozinha.getNome());
			// Copia os valores da proprieda e joga na Atual -- Set
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

			Restaurante restauranteParaAtualizar = restauranteAtual.get();
			
			cadastroRestaurante.salvar(restauranteParaAtualizar);
			
			return ResponseEntity.ok(restauranteAtual);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos) {

		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

		if (! restauranteAtual.isPresent()) {

			return ResponseEntity.notFound().build();

		}
		
		Restaurante restauranteParaAtualizar = restauranteAtual.get();

		merge(campos, restauranteParaAtualizar);

		return atualizar(restauranteId, restauranteParaAtualizar);

		// return ResponseEntity.ok().build();

	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

		// Converter objetos
		ObjectMapper objectMApper = new ObjectMapper();

		Restaurante restauranteOrigem = objectMApper.convertValue(dadosOrigem, Restaurante.class);

		System.out.println("Restaurante origem: " + restauranteOrigem);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {

			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			System.out.println(nomePropriedade + " = " + valorPropriedade + " " + novoValor);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);

		});
	}

}
