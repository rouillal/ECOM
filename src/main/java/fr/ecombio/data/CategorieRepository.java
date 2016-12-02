package fr.ecombio.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Categorie;

@Stateless
public class CategorieRepository {

	@Inject
	private EntityManager em;

	public Categorie findById(Long id) {
		return em.find(Categorie.class, id);
	}

	public List<Categorie> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> criteria = cb.createQuery(Categorie.class);
		Root<Categorie> Categorie = criteria.from(Categorie.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Categorie).orderBy(cb.asc(Categorie.get(Categorie_.name)));
		criteria.select(Categorie).orderBy(cb.asc(Categorie.get("name")));
		return em.createQuery(criteria).getResultList();
	}
	

	public  void AjoutCategorie(Categorie cat) {
		em.persist(cat);
	}
}
