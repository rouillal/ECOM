package fr.ecombio.data;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Categorie;
import fr.ecombio.model.Panier;
import fr.ecombio.model.Produit;

@Stateless
public class PanierRepository {
	
	@Inject
	private EntityManager em;

	public Long AjoutPanier(Panier panier) {
		em.persist(panier);
		return panier.getId();
	}
	
	public Panier findById(Long id) {
		return em.find(Panier.class, id);
	}

	public void updatePanier(Panier panier) {
		em.merge(panier);
	}

	public List<Panier> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Panier> criteria = cb.createQuery(Panier.class);
		Root<Panier> Panier = criteria.from(Panier.class);
		criteria.select(Panier);
		return em.createQuery(criteria).getResultList();
	}

	public void SupprimePanier(Panier panier) {
		em.remove(em.find(Panier.class, panier.getId()));
	}

}