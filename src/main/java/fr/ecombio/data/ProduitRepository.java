package fr.ecombio.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Produit;

import java.util.List;

/**
 * 
 * 
 *
 */
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

	/**
	 * 
	 * @return List<Produit>
	 */
	public List<Produit> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		criteria.select(Produit).orderBy(cb.asc(Produit.get("name")));
		TypedQuery<Produit> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(6);
		return typedQuery.getResultList();
	}
	
	/**
	 * 
	 * @param prod
	 */
	public  void AjoutProduit(Produit prod) {
		/*Produit prod = new Produit();
		prod.setName(name);
		prod.setVariete(variete);
		prod.setQuantite(quantite);
		prod.setStock(stock);
		prod.setPrix(prix);
		prod.setProvenance(provenance);
		prod.setDateCueillette(dateCueillette);*/
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

	/*public List<Produit> findCatOrderedByName(String cat) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> criteria = cb.createQuery(Categorie.class);
		Root<Categorie> Categorie = criteria.from(Categorie.class);
		criteria.select(Categorie).where( cb.equal( Categorie.get("name"),cat ));
		List<Categorie> cats = em.createQuery(criteria).getResultList();
		List<Produit> result = null;
		for (Categorie cat1 : cats) {
			result.addAll(cat1.getProduits());
		}
		return result;
	}*/
}
