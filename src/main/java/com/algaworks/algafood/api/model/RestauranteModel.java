package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

public class RestauranteModel {

	
	
	@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private Long id;

	@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;

	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxafrete;

	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	
	private EnderecoModel endereco;

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

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
