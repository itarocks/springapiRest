package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

public class RestauranteModel {

	private Long id;

	private String nome;

	private BigDecimal taxafrete;

	private CozinhaModel cozinha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getTaxafrete() {
		return taxafrete;
	}

	public void setTaxafrete(BigDecimal taxafrete) {
		this.taxafrete = taxafrete;
	}

	public CozinhaModel getCozinha() {
		return cozinha;
	}

	public void setCozinha(CozinhaModel cozinha) {
		this.cozinha = cozinha;
	}

}
