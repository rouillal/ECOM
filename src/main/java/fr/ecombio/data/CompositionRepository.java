package fr.ecombio.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Composition;

@Stateless
public class CompositionRepository {
	
	@Inject
	private EntityManager em;
	
	public Composition findById(Long id) {
		return em.find(Composition.class, id);
	}

	public List<Composition> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Composition> criteria = cb.createQuery(Composition.class);
		Root<Composition> Composition = criteria.from(Composition.class);
		criteria.select(Composition).orderBy(cb.asc(Composition.get("name")));
		return em.createQuery(criteria).getResultList();
	}
	

	public  void AjoutCategorieRecette(Composition comp) {
		em.persist(comp);
	}

}
