package fr.ecombio.data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Article;
import fr.ecombio.model.Categorie;
import fr.ecombio.model.Composition;
import fr.ecombio.model.CompositionRecette;
import fr.ecombio.model.Panier;
import fr.ecombio.model.Produit;

//import org.jboss.logging.Logger;

import fr.ecombio.model.Recette;
import fr.ecombio.model.RecetteProduit;
import fr.ecombio.model.RecetteSaison;
import fr.ecombio.model.Saison;

@Stateless
public class RecetteRepository {

	@Inject
	private EntityManager em;

	@Inject
	private PanierRepository PanierRepository;

	//Logger log;
	Logger logger = java.util.logging.Logger.getLogger("org.hibernate");

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

	List<DefRecette> listRecette = new LinkedList<DefRecette>();

	public List<fr.ecombio.model.Recette> findAllOrderedByName(int page, String saison) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Recette> criteria = cb.createQuery(Recette.class);
		Root<Recette> Recette = criteria.from(Recette.class);
		criteria.select(Recette).orderBy(cb.asc(Recette.get("name"))).distinct(true);
		if (saison != null) {
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
		}
		TypedQuery<Recette> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*6);
		typequery.setMaxResults(6);
		return typequery.getResultList();

	}

	public List<Recette> findAllOrderedByName(int page, String cat, String saison, String search, String compo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Recette> criteria = cb.createQuery(Recette.class);
		Root<Recette> Recette = criteria.from(Recette.class);
		Predicate predicate = null ;
		Predicate predicate2 = null;
		criteria.select(Recette).orderBy(cb.asc(Recette.get("name"))).distinct(true);

		if(cat == null || cat == "") {
			if(search == null || search == "") {
				if(compo == null || compo == "")
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
			//String[] recettes = search.split(",");
			if (this.listRecette == null || this.listRecette.isEmpty()) {
				this.getListRecette();
			}
			for (DefRecette d : this.listRecette) {
				if (d.getName().toLowerCase().contains(search)) {
					if (predicate2 == null) {
						predicate2 = cb.equal(Recette.get("id"), d.id);
					} else {
						predicate2 = cb.or(predicate2,cb.equal(Recette.get("id"), d.id));
					}
				}
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
		if(compo != null && compo != "") {
			String[] compos = compo.split(",");
			Join<Recette, CompositionRecette> join1 = Recette.join("compositions");
			Join<CompositionRecette, Composition> join2 = join1.join("compositions");
			if(predicate == null) {
				predicate = cb.equal( join2.get("id"), Integer.parseInt(compos[0])) ;
			} else {
				predicate = cb.or(predicate,cb.equal( join2.get("id"), Integer.parseInt(compos[0]))) ;
			}
			for (String compo1 : compos) {
				predicate = cb.or(predicate,cb.equal( join2.get("id"), Integer.parseInt(compo1)));
			}
		}
		if(predicate != null) {
			if(predicate2 != null) {
				predicate = cb.and(predicate,predicate2);
			}
		} else {
			predicate = predicate2;
		}
		if (predicate != null) {
			criteria.where(predicate);
		} else {
			criteria.where(cb.equal(Recette.get("id"), -1));
		}
		TypedQuery<Recette> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*6);
		typequery.setMaxResults(6);
		return typequery.getResultList();
	}

	private void getListRecette() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
		Root<Recette> Recette = criteria.from(Recette.class);
		criteria.multiselect(Recette.get("id"), Recette.get("name"));
		List<Tuple> tupleResult = em.createQuery(criteria).getResultList();
		logger.log(Level.INFO, "Recette : ");
		for (Tuple t : tupleResult) {
			this.listRecette.add(new DefRecette((Long) t.get(0),(String) t.get(1)));
			logger.log(Level.INFO, (String) t.get(1));
		}
	}

	public class DefRecette {
		private Long id;
		private String name;

		public DefRecette() {
			super();
		}

		public DefRecette(Long id, String name) {
			this.id = id;
			this.name = name;
		}

		public Long getId() {
			return this.id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	public List<Produit> findAllProduitsFromId(int id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<RecetteProduit> RecetteProduit = criteria.from(RecetteProduit.class);
		Join<RecetteProduit, Produit> join1 = RecetteProduit.join("produits");
		criteria.select(join1);
		criteria.where(cb.equal(RecetteProduit.get("recettes"), id));
		return em.createQuery(criteria).getResultList();
	}


	public List<Recette> findAllRecetteFromPanier(Long id) {
		Panier panier = PanierRepository.findById(id);
		List<Long> listProduit = new ArrayList<Long> ();
		for (Article a : panier.getArticles()) {
			listProduit.add(a.getProduit().getId());
		}

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Recette> criteria = cb.createQuery(Recette.class);
		Root<Recette> Recette = criteria.from(Recette.class);
		Join<Recette, RecetteProduit> join1 = Recette.join("produits");
		Join<RecetteProduit, Produit> join2 = join1.join("produits");

		criteria.select(Recette);
		Expression<String> exp = join2.get("id");
		Predicate predicate = exp.in(listProduit);
		criteria.where(predicate);
		return em.createQuery(criteria).getResultList();
	}
}
