package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<Cozinha> listar() {

		List<Cozinha> cozinhas = manager.createQuery("from Cozinha", Cozinha.class).getResultList();

		for (Cozinha cozinha : cozinhas) {
			System.out.println("O valor da cozinha " + cozinha.getNome());
		}

		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();

	}

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {

		return manager.merge(cozinha);
	}

	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Transactional
	public void remover(Long id) {

		Cozinha cozinha = buscar(id);
		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(cozinha);
	}

}
