package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.model.view.RestauranteView;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
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

	@Autowired
	private RestauranteModelAssembler restauranteAssembler;

	@Autowired
	private RestauranteInputDisassembler restauranteDisassember;

	/*
	 * @JsonView(RestauranteView.Resumo.class)
	 * 
	 * @GetMapping() public List<RestauranteModel> listar() {
	 * 
	 * // List<Restaurante> restaurantes = restauranteRepository.findAll();
	 * 
	 * return toCollectionModel(restauranteRepository.findAll());
	 * 
	 * }
	 */

	@JsonView(RestauranteView.Resumo.class)

	@GetMapping()
	public List<RestauranteModel> listar() {

		// List<Restaurante> restaurantes = restauranteRepository.findAll();

		return toCollectionModel(restauranteRepository.findAll());

	}

	@JsonView(RestauranteView.ApenasNome.class)

	@GetMapping(params = "projecao=apenas-nome")
	public List<RestauranteModel> listarApenasNome() {

		// List<Restaurante> restaurantes = restauranteRepository.findAll();

		return listar();

	}

	/*
	 * // Como fazer dinamicamente
	 * 
	 * @GetMapping() public MappingJacksonValue listar(@RequestParam(required =
	 * false) String projecao) {
	 * 
	 * List<Restaurante> restaurantes = restauranteRepository.findAll();
	 * 
	 * List<RestauranteModel> restauranteModel =
	 * restauranteAssembler.toCollectionModel(restaurantes);
	 * 
	 * MappingJacksonValue restauranteWrapper = new
	 * MappingJacksonValue(restauranteModel);
	 * 
	 * restauranteWrapper.setSerializationView(RestauranteView.Resumo.class);
	 * 
	 * if ("apenas-nome".equals(projecao)) {
	 * restauranteWrapper.setSerializationView(RestauranteView.ApenasNome.class); }
	 * else if ("completo".equals(projecao)) {
	 * restauranteWrapper.setSerializationView(null); }
	 * 
	 * return restauranteWrapper;
	 * 
	 * // return toCollectionModel(restauranteRepository.findAll());
	 * 
	 * }
	 */

	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable("restauranteId") Long id) {

		// Optional<Restaurante> restaurante = restauranteRepository.findById(id);

		// return ResponseEntity.status(HttpStatus.OK).body(cozinha);

		// if (restaurante.isPresent())
		// return ResponseEntity.ok(restaurante.get());

		// return ResponseEntity.notFound().build();

		// return null;

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(id);

		return restauranteAssembler.toModel(restaurante);

	}

	private List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> restauranteAssembler.toModel(restaurante))
				.collect(Collectors.toList());

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	// ? aceita o retorno de qualquer tipo
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			// restaurante = cadastroRestaurante.salvar(restaurante);
			Restaurante restaurante = restauranteDisassember.toDomainObject(restauranteInput);
			return restauranteAssembler.toModel(cadastroRestaurante.salvar(restaurante));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteInput restauranteInput) {
		try {

			Restaurante restaurante = restauranteDisassember.toDomainObject(restauranteInput);

			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
					"produtos");

			return restauranteAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

//	@PatchMapping("/{restauranteId}")
//	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
//			@RequestBody Map<String, Object> campos) {
//
//		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
//
//		if (!restauranteAtual.isPresent()) {
//
//			return ResponseEntity.notFound().build();
//
//		}
//
//		Restaurante restauranteParaAtualizar = restauranteAtual.get();
//
//		merge(campos, restauranteParaAtualizar);
//
//		return atualizar(restauranteId, restauranteParaAtualizar);
//
//		// return ResponseEntity.ok().build();
//
//	}

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
