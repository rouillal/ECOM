package fr.ecombio.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Produit;

import java.util.List;


@Stateless
public class ProduitRepository {

	@Inject
	private EntityManager em;

	/**
	 * 
	 * @param id
	 * @return Produit
	 */
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
		em.persist(prod);
	}
	
	/**
	 * 
	 * @param cat
	 * @return
	 */
	public List<Produit> findCatOrderedByName(String cat) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Produit).orderBy(cb.asc(Produit.get(Produit_.name)));
		criteria.select(Produit).where( cb.equal( Produit.get("categorie").get("name"),cat ));
		return em.createQuery(criteria).getResultList();
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
				return this.findAllOrderedByName(page);
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
			search = search.toLowerCase();
			String[] prods = search.split(",");
				predicate2 = cb.equal(cb.lower(Produit.<String>get("name")), prods[0]);
				predicate2 = cb.or(predicate2,(cb.equal(cb.lower(Produit.<String>get("variete")), prods[0])));
			for (String prod1 : prods) {
				predicate2 = cb.and(predicate2, cb.or((cb.equal(cb.lower(Produit.<String>get("name")), prod1)),
						(cb.equal(cb.lower(Produit.<String>get("variete")), prod1))));
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

