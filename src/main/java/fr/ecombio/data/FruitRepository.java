package fr.ecombio.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Fruit;

import java.util.List;

@ApplicationScoped
public class FruitRepository {

	@Inject
	private EntityManager em;

	public Fruit findById(Long id) {
		return em.find(Fruit.class, id);
	}

	public List<Fruit> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Fruit> criteria = cb.createQuery(Fruit.class);
		Root<Fruit> Fruit = criteria.from(Fruit.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Fruit).orderBy(cb.asc(Fruit.get(Fruit_.name)));
		criteria.select(Fruit).orderBy(cb.asc(Fruit.get("name")));
		return em.createQuery(criteria).getResultList();
	}
}
