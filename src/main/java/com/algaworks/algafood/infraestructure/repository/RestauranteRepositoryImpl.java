package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;


@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{
	
	@PersistenceContext
	private EntityManager manager;

	public List<Restaurante> listar() {

		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();

	}
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		
		return manager.merge(restaurante);
	}
	
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Transactional
	public void remover(Restaurante reustarante) {
		
		reustarante = buscar(reustarante.getId());
		manager.remove(reustarante);
	}


}
