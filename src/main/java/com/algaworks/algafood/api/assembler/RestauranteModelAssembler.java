package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;


@Component
public class RestauranteModelAssembler {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteModel toModel(Restaurante restaurante) {
		/*
		 * CozinhaModel cozinhaModel = new CozinhaModel();
		 * cozinhaModel.setId(restaurante.getCozinha().getId());
		 * cozinhaModel.setNome(restaurante.getCozinha().getNome());
		 * 
		 * EnderecoModel enderecoModel = new EnderecoModel();
		 * 
		 * enderecoModel.setBairro(restaurante.getEndereco().getBairro());
		 * 
		 * 
		 * 
		 * RestauranteModel restauranteModel = new RestauranteModel();
		 * 
		 * restauranteModel.setId(restaurante.getId());
		 * restauranteModel.setNome(restaurante.getNome());
		 * restauranteModel.setTaxafrete(restaurante.getTaxaFrete());
		 * restauranteModel.setCozinha(cozinhaModel);
		 * restauranteModel.setEndereco(enderecoModel);
		 */
		
		return modelMapper.map(restaurante, RestauranteModel.class);
		

	}
	
	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());

	}

}
