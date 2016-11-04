package fr.ecombio.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Produit;

import java.util.List;

@ApplicationScoped
public class ProduitRepository {

	@Inject
	private EntityManager em;

	public Produit findById(Long id) {
		return em.find(Produit.class, id);
	}

	public List<Produit> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Produit).orderBy(cb.asc(Produit.get(Produit_.name)));
		criteria.select(Produit).orderBy(cb.asc(Produit.get("name")));
		return em.createQuery(criteria).getResultList();
	}
	
	public  void AjoutProduit(Produit prod) {
		/*Produit prod = new Produit();
		prod.setName(name);
		prod.setVariete(variete);
		prod.setQuantite(quantite);
		prod.setStock(stock);
		prod.setPrix(prix);
		prod.setProvenance(provenance);
		prod.setDateCueillette(dateCueillette);*/
		em.getTransaction().begin();
		em.persist(prod);
		em.getTransaction().commit();
	}
}
