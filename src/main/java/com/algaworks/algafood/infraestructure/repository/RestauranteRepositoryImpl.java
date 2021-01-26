package com.algaworks.algafood.infraestructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);

		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome +  "%");
		
		criteria.where(nomePredicate);

		TypedQuery<Restaurante> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	/*
	 * String jpql = "from Restaurante where nome like :nome " +
	 * "and taxa_frete between :taxaInicial and :taxaFinal";
	 * 
	 * return manager.createQuery(jpql, Restaurante.class).setParameter("nome", "%"
	 * + nome + "%") .setParameter("taxaInicial",
	 * taxaFreteInicial).setParameter("taxaFinal", taxaFreteFinal) .getResultList();
	 * 
	 * 
	 * StringBuilder jpql = new StringBuilder();
	 * jpql.append("from Restaurante where 0 = 0 ");
	 * 
	 * HashMap<String, Object> parametros = new HashMap<String, Object>();
	 * 
	 * if (StringUtils.hasLength(nome)) { jpql.append("and nome like :nome ");
	 * parametros.put("nome", "%" + nome + "%"); }
	 * 
	 * if (taxaFreteInicial != null) {
	 * jpql.append("and taxa_frete >= :taxaInicial "); parametros.put("taxaInicial",
	 * taxaFreteInicial); }
	 * 
	 * if (taxaFreteFinal != null) { jpql.append("and taxa_frete <= :taxaFinal ");
	 * parametros.put("taxaFinal", taxaFreteFinal); }
	 * 
	 * TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(),
	 * Restaurante.class);
	 * 
	 * for (String name : parametros.keySet()) { String key = name.toString();
	 * String value = parametros.get(name).toString();
	 * System.out.println("Values do key " + key + " " + value); }
	 * 
	 * parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
	 * 
	 * System.out.println(parametros.size());
	 * 
	 * return query.getResultList(); // TypedQuery<Restaurante> query = //
	 * manager.createQuery(jpql.toString(),Restaurante.class);
	 */

}
