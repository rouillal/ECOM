package fr.ecombio.data;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.ecombio.model.Categorie;
import fr.ecombio.model.Panier;

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
		em.persist(panier);
	}

}