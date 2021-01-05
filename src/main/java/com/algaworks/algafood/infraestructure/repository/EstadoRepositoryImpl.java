package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;


@Component
public class EstadoRepositoryImpl implements EstadoRepository{
	
	@PersistenceContext
	private EntityManager manager;

	public List<Estado> listar() {
		
		List<Estado> estados = manager.createQuery("from Estado", Estado.class).getResultList();
		
		for(Estado estado : estados) {
			System.out.println("O valor da cozinha " + estado.getNome());
		}

		return manager.createQuery("from Estado", Estado.class).getResultList();

	}
	
	@Transactional
	public Estado salvar(Estado estado) {
		
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
