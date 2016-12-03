package fr.ecombio.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Categorie;
import fr.ecombio.model.CategorieRecette;
@Stateless
public class CategorieRecetteRepository {
	
	@Inject
	private EntityManager em;
	
	public CategorieRecette findById(Long id) {
		return em.find(CategorieRecette.class, id);
	}

	public List<CategorieRecette> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CategorieRecette> criteria = cb.createQuery(CategorieRecette.class);
		Root<CategorieRecette> CategorieRecette = criteria.from(CategorieRecette.class);
		criteria.select(CategorieRecette).orderBy(cb.asc(CategorieRecette.get("name")));
		return em.createQuery(criteria).getResultList();
	}
	

	public  void AjoutCategorieRecette(CategorieRecette cat) {
		em.persist(cat);
	}

}
