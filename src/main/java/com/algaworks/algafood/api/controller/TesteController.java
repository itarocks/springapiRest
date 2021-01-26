package com.algaworks.algafood.api.controller;


import static com.algaworks.algafood.infraestructure.repository.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infraestructure.repository.RestauranteSpecs.comNomeSemlehante;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.infraestructure.repository.RestauranteSpecs;

@RestController
@RequestMapping("/testes")
public class TesteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantePorNome(
			String nome, Long cozinhaId){
		
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
		
	}
	
	
	@GetMapping("/restaurantes/por-taxa")
	public List<Restaurante> restaurantePorTaxaFrete(
			String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
		
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantePorTaxaFrete(
			String nome){
			
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemlehante(nome)));
				
	}

}
