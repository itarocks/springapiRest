package com.algaworks.algafood.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<Cidade> listar() {

		List<Cidade> cidades = manager.createQuery("from Cidade", Cidade.class).getResultList();

		for (Cidade cidade : cidades) {
			System.out.println("O valor da cozinha " + cidade.getNome());
		}

		return manager.createQuery("from Cidade", Cidade.class).getResultList();

	}

	@Transactional
	public Cidade salvar(Cidade cidade) {

		return manager.merge(cidade);
	}

	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Transactional
	public void remover(Long id) {

		Cidade cidade = buscar(id);
		if (cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(cidade);
	}

}
