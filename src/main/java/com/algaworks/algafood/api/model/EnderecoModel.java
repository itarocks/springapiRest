package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.Cidade;

public class EnderecoModel {

	private String cep;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private CidadeModel cidade;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public CidadeModel getCidade() {
		return cidade;
	}

	public void setCidade(CidadeModel cidade) {
		this.cidade = cidade;
	}

}
