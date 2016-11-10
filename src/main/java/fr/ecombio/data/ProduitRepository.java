package fr.ecombio.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Categorie;
import fr.ecombio.model.Produit;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProduitRepository {

	@Inject
	private EntityManager em;

	public Produit findById(Long id) {
		return em.find(Produit.class, id);
	}

	public List<Produit> findAllOrderedByName(int page) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		criteria.select(Produit).orderBy(cb.asc(Produit.get("name")));
		TypedQuery<Produit> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*6);
		typequery.setMaxResults(6);
		return typequery.getResultList();
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

	public List<Produit> findCatOrderedByName(String cat, String search, int page) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		Predicate predicate = null ;
		Predicate predicate2 = null;
		criteria.select(Produit);
		
		if(cat == null || cat == "") {
			if(search == null || search == "") {
				return this.findAllOrderedByName(0);
			}
		} else {
			String[] cats = cat.split(",");
			//em.createQuery(criteria).getResultList();
			predicate = cb.equal( Produit.get("categorie").get("id"), Integer.parseInt(cats[0])) ;
			for (String cat1 : cats) {
				predicate = cb.or(predicate,(cb.equal( Produit.get("categorie").get("id"), Integer.parseInt(cat1))));
			}
		}
		if(search != null && search != "") {
			String[] prods = search.split(",");
				predicate2 = cb.equal(Produit.get("name"), prods[0]);
				predicate2 = cb.or(predicate2,(cb.equal(Produit.get("variete"), prods[0])));
			for (String prod1 : prods) {
				predicate2 = cb.and(predicate2, cb.or((cb.equal(Produit.get("name"), prod1)),(cb.equal(Produit.get("variete"), prod1))));
			}
		}
		if(predicate != null) {
			if(predicate2 != null) {
				predicate = cb.and(predicate,predicate2);
			}
		} else {
			predicate = predicate2;
		}
		criteria.where(predicate);
		TypedQuery<Produit> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*6);
		typequery.setMaxResults(6);
		return typequery.getResultList();
	}	
}

