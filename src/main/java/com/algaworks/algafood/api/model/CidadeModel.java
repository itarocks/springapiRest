package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.Estado;

public class CidadeModel {

	
	private Long id;
	
	private String nome; 
	
	private Estado estado;

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
