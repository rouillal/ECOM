package fr.ecombio.data;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


//import org.jboss.logging.Logger;

import fr.ecombio.model.Recette;
import fr.ecombio.model.RecetteSaison;
import fr.ecombio.model.Saison;

@Stateless
public class RecetteRepository {
	
	@Inject
	private EntityManager em;

	/**
	 * 
	 * @param id
	 * @return Recette
	 */
	public Recette findById(Long id) {
		return em.find(Recette.class, id);
	}
	
	public  void AjoutRecette(Recette recette) {
		em.persist(recette);
	}
	
	public List<fr.ecombio.model.Recette> findAllOrderedByName(int page, String saison) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Recette> criteria = cb.createQuery(Recette.class);
		Root<Recette> Recette = criteria.from(Recette.class);
		criteria.select(Recette).orderBy(cb.asc(Recette.get("name"))).distinct(true);
		String[] saisons = saison.split(",");
		Join<Recette, RecetteSaison> join1 = Recette.join("saisons");
		Join<RecetteSaison, Saison> join2 = join1.join("saisons");
		Predicate predicate = null ;
		//em.createQuery(criteria).getResultList();
		predicate = cb.equal( join2.get("id"), Integer.parseInt(saisons[0])) ;
		for (String saison1 : saisons) {
			predicate = cb.or(predicate,cb.equal( join2.get("id"), Integer.parseInt(saison1)));
		}
		criteria.where(predicate);
		TypedQuery<Recette> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*6);
		typequery.setMaxResults(6);
		return typequery.getResultList();
		
	}

	public List<Recette> findAllOrderedByName(int page, String cat, String saison, String search) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Recette> criteria = cb.createQuery(Recette.class);
		Root<Recette> Recette = criteria.from(Recette.class);
		Predicate predicate = null ;
		Predicate predicate2 = null;
		criteria.select(Recette).orderBy(cb.asc(Recette.get("name"))).distinct(true);
		
		if(cat == null || cat == "") {
			if(search == null || search == "") {
				return this.findAllOrderedByName(page, saison);
			}
		} else {
			String[] cats = cat.split(",");
			predicate = cb.equal( Recette.get("categorieRecette").get("id"), Integer.parseInt(cats[0])) ;
			for (String cat1 : cats) {
				predicate = cb.or(predicate,(cb.equal( Recette.get("categorieRecette").get("id"), Integer.parseInt(cat1))));
			}
		}
		if(search != null && search != "") {
			search = search.toLowerCase();
			String[] recettes = search.split(",");
				predicate2 = cb.equal(cb.lower(Recette.<String>get("name")), recettes[0]);
			for (String recette1: recettes) {
				predicate2 = cb.or(predicate2,cb.equal(cb.lower(Recette.<String>get("name")), recette1));
			}
		}
		if(saison != null && saison != ""){
			String[] saisons = saison.split(",");
			Join<Recette, RecetteSaison> join1 = Recette.join("saisons");
			Join<RecetteSaison, Saison> join2 = join1.join("saisons");
			if(predicate == null) {
				predicate = cb.equal( join2.get("id"), Integer.parseInt(saisons[0])) ;
			} else {
				predicate = cb.or(predicate,cb.equal( join2.get("id"), Integer.parseInt(saisons[0]))) ;
			}
			for (String saison1 : saisons) {
				predicate = cb.or(predicate,cb.equal( join2.get("id"), Integer.parseInt(saison1)));
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
		TypedQuery<Recette> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*6);
		typequery.setMaxResults(6);
		return typequery.getResultList();
	}

}
