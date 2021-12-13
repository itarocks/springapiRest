package com.algaworks.algafood.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Grupo {

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

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"),
			inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private Set<Permissao> permissoes = new HashSet<>();
	
	public boolean removerPermissao(Permissao permissao) {
		return getPermissoes().remove(permissao);
	}
	
	public boolean adicionarPermissao(Permissao permissao) {
		return getPermissoes().add(permissao);
	}
	
}