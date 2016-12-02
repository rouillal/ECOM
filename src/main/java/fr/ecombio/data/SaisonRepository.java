package fr.ecombio.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Saison;

@Stateless
public class SaisonRepository {

	@Inject
	private EntityManager em;

	public Saison findById(Long id) {
		return em.find(Saison.class, id);
	}

	public List<Saison> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Saison> criteria = cb.createQuery(Saison.class);
		Root<Saison> Saison = criteria.from(Saison.class);
		criteria.select(Saison).orderBy(cb.asc(Saison.get("name")));
		return em.createQuery(criteria).getResultList();
	}
	

	public  void AjoutCategorie(Saison cat) {
		em.persist(cat);
	}
}