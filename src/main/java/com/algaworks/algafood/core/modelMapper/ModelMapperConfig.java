package com.algaworks.algafood.core.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	
	@Bean
	public ModelMapper modelMapper() {
		
		// var modelMapper =  new ModelMapper();
		return new ModelMapper(); 
		
	}
}
