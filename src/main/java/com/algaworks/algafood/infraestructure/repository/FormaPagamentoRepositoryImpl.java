package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	
	private EntityManager manager;

	public List<FormaPagamento> listar() {
		
		List<FormaPagamento> formaPagamentos = manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
		
		for(FormaPagamento formaPagamento : formaPagamentos) {
			System.out.println("O valor da cozinha " + formaPagamento.getDescricao());
		}

		return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();

	}



}