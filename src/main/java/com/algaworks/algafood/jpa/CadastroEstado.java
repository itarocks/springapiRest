package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CadastroEstado {

	@PersistenceContext
	private EntityManager manager;

	public List<Estado> listar() {

		return manager.createQuery("from Estado", Estado.class).getResultList();

	}
	
	@Transactional
	public Estado adicionar(Estado estado) {
		
		return manager.merge(estado);
	}
	
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}
	
	@Transactional
	public void remover(Estado estado) {
		
		estado = buscar(estado.getId());
		manager.remove(estado);
	}

}
