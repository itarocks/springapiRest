package com.algaworks.algafood.infraestructure.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComFreteGratis;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;

public class RestauranteSpecs {
	
	public static Specification<Restaurante> comFreteGratis(){
		//return new RestauranteComFreteGratis();
		//com LAmbda
		return(root,query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemlehante(String nome){
		//return new RestauranteComNomeSemelhanteSpec();
		return(root,query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
	}

}
