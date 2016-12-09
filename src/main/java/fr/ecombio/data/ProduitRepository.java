package fr.ecombio.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Produit;
import fr.ecombio.model.ProduitSaison;
import fr.ecombio.model.Recette;
import fr.ecombio.model.Saison;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Permet une gestion des produits :
 * <ul>
 * 	<li>faire des requetes de select</li>
 * 	<li>ajouter une saison en base</li>
 *  </ul>
 * </p>
 * 
 * @see EntityManager
 * @see Produit
 *
 */
@Stateless
public class ProduitRepository {

	/**
	 * pour gerer l'aspect transactionnel
	 * 
	 * @see EntityManager
	 */
	@Inject
	private EntityManager em;

	/**
	 * Recherche d'un produit
	 * @param id identifiant du produit
	 * @return Produit produit associe
	 */
	public Produit findById(Long id) {
		return em.find(Produit.class, id);
	}

	/**
	 * 
	 * @param page numero de la page voulu
	 * @param tri selection plus précise
	 * @param saison selection par saison
	 * @return
	 */
	public List<Produit> findAllOrderedByName(int page, String tri, int saison) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		Predicate predicate = null;
		criteria.select(Produit);
		if(tri != null && tri != ""){
			if(tri.equalsIgnoreCase("alpha")) {
				criteria.orderBy(cb.asc(Produit.get("name")));
			} else if(tri.equalsIgnoreCase("prixup")) {
				criteria.orderBy(cb.asc(Produit.get("prix")));
			} else if(tri.equalsIgnoreCase("prixdown")) {
				criteria.orderBy(cb.desc(Produit.get("prix")));
			} 
		} else {
			criteria.orderBy(cb.asc(Produit.get("name")));
		}
		if( saison == 1 ) {
			String saisonDuMoment = this.getSeason(new Date());
			Join<Produit, ProduitSaison> join1 = Produit.join("saisons");
			Join<ProduitSaison, Saison> join2 = join1.join("saisons");
			predicate = cb.equal( cb.lower(join2.<String>get("name")), saisonDuMoment);
		}
		if (predicate == null) {
			predicate = cb.notEqual(Produit.get("stock"), 0);
		} else {
			predicate = cb.and(predicate,cb.notEqual(Produit.get("stock"), 0));
		}
		criteria.where(predicate);
		TypedQuery<Produit> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*6);
		typequery.setMaxResults(6);
		return typequery.getResultList();
	}
	
	/**
	 * Ajout d'un produit
	 * @param prod Produit a ajouter
	 */
	public  void AjoutProduit(Produit prod) {
		em.persist(prod);
	}
	
	/**
	 * Recherche des produits selon categorie donnee
	 * @param cat categorie
	 * @return liste de produits
	 */
	public List<Produit> findCatOrderedByName(String cat) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Produit).orderBy(cb.asc(Produit.get(Produit_.name)));
		criteria.select(Produit).where( cb.and(cb.equal( Produit.get("categorie").get("name"),cat ), cb.notEqual(Produit.get("stock"), 0)));
		return em.createQuery(criteria).getResultList();
	}

	/**
	 * 
	 * @param cat categorie
	 * @param search recherche par nom
	 * @param page page courante
	 * @param tri selection plus précise
	 * @param saison selection par saison
	 * @return liste de produits
	 */
	public List<Produit> findCatOrderedByName(String cat, String search, int page, String tri, int saison) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		Predicate predicate = null ;
		Predicate predicate2 = null;
		criteria.select(Produit);
		
		if(cat == null || cat == "") {
			if(search == null || search == "") {
				return this.findAllOrderedByName(page, tri, saison);
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
		if( saison == 1 ) {
			String saisonDuMoment = this.getSeason(new Date());
			Join<Produit, ProduitSaison> join1 = Produit.join("saisons");
			Join<ProduitSaison, Saison> join2 = join1.join("saisons");
			predicate2 = cb.equal( cb.lower(join2.<String>get("name")), saisonDuMoment);
			predicate = cb.and(predicate,predicate2);
		}
		predicate = cb.and(predicate,cb.notEqual(Produit.get("stock"), 0));
		criteria.where(predicate);
		if(tri != null && tri != "") {
			if(tri.equalsIgnoreCase("alpha")) {
				criteria.orderBy(cb.asc(Produit.get("name")));
			} else if(tri.equalsIgnoreCase("prixup")) {
				criteria.orderBy(cb.asc(Produit.get("prix")));
			} else if(tri.equalsIgnoreCase("prixdown")) {
				criteria.orderBy(cb.desc(Produit.get("prix")));
			}
		}
		TypedQuery<Produit> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*6);
		typequery.setMaxResults(6);
		return typequery.getResultList();
	}

	/**
	 * mise a jour du produit
	 * @param p produit
	 */
	public void updateProduit(Produit p) {
		em.merge(p);
	}	
	
	private String getSeason(Date today) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
	    switch(cal.get(Calendar.MONTH)) {
	          case 12:
	                return "hiver";
	          case 1:
	                return "hiver";
	          case 2:
	                return "hiver";
	          case 3:
	                return "printemps";
	          case 4:
	                return "printemps";
	          case 5:
	                return "printemps";
	          case 6:
	                return "ete";
	          case 7:
	                return "ete";
	          case 8:
	                return "ete";
	          default:
	                return "automne";
	      }
	}

	/**
	 * Recherche du nombre de pages total
	 * @param cat categorie
	 * @param search recherche par nom
	 * @param saison selection par saison
	 * @return nombre de pages
	 */
	public Long findNumberPage(String cat, String search, int saison) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		Predicate predicate = null ;
		Predicate predicate2 = null;
		criteria.select(cb.countDistinct(Produit));
		
		if(cat == null || cat == "") {
			if(search == null || search == "") {
				return this.findNumberPage(saison);
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
		if( saison == 1 ) {
			String saisonDuMoment = this.getSeason(new Date());
			Join<Produit, ProduitSaison> join1 = Produit.join("saisons");
			Join<ProduitSaison, Saison> join2 = join1.join("saisons");
			predicate2 = cb.equal( cb.lower(join2.<String>get("name")), saisonDuMoment);
			predicate = cb.and(predicate,predicate2);
		}
		predicate = cb.and(predicate,cb.notEqual(Produit.get("stock"), 0));
		criteria.where(predicate);
		return (long) (Math.ceil((float)em.createQuery(criteria).getSingleResult()/6)) ;
	}

	/**
	 * Recherche du nombre de pages total
	 * @param saison selection par saison
	 * @return nombre de pages
	 */
	public Long findNumberPage(int saison) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		Root<Produit> Produit = criteria.from(Produit.class);
		criteria.select(cb.countDistinct(Produit));
		Predicate predicate = null ;
		if( saison == 1 ) {
			String saisonDuMoment = this.getSeason(new Date());
			Join<Produit, ProduitSaison> join1 = Produit.join("saisons");
			Join<ProduitSaison, Saison> join2 = join1.join("saisons");
			predicate = cb.equal( cb.lower(join2.<String>get("name")), saisonDuMoment);
		}
		if (predicate == null) {
			predicate = cb.notEqual(Produit.get("stock"), 0);
		} else {
			predicate = cb.and(predicate,cb.notEqual(Produit.get("stock"), 0));
		}
		criteria.where(predicate);
		return (long) (Math.ceil((float)em.createQuery(criteria).getSingleResult()/6)) ;
	}
}

