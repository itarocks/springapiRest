package com.algaworks.algafood.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	
	public ModelMapper modelMapper() {
		
		var modelMapper =  new ModelMapper();
		return modelMapper; 
		
	}
}
