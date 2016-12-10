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

/**
 * <p>
 * Permet une gestion des recettes :
 * <ul>
 * 	<li>faire des requêtes de select</li>
 * 	<li>ajouter une saison en base</li>
 *  </ul>
 * </p>
 * 
 * @see EntityManager
 * @see Recette
 * @see PanierRepository
 *
 */
@Stateless
public class RecetteRepository {

	/**
	 * pour gérer l'aspect transactionnel
	 * 
	 * @see EntityManager
	 */
	@Inject
	private EntityManager em;

	/**
	 * @see PanierRepository
	 */
	@Inject
	private PanierRepository PanierRepository;

	//Logger log;
	Logger logger = java.util.logging.Logger.getLogger("org.hibernate");

	/**
	 * 
	 * @param id identificateur de la recette
	 * @return Recette
	 */
	public Recette findById(Long id) {
		return em.find(Recette.class, id);
	}

	/**
	 * @param recette la recette
	 */
	public  void AjoutRecette(Recette recette) {
		em.persist(recette);
	}

	private List<DefRecette> listRecette = new LinkedList<DefRecette>();

	/**
	 * 
	 * @param page numéro de la page, pour la pagination
	 * @param saison saisons associées à la recette
	 * @param tri alpha,name,difficulte,cout,tpsPreparation,tpsCuisson
	 * @return liste de recettes
	 */
	public List<Recette> findAllOrderedByName(int page, String saison, String tri) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Recette> criteria = cb.createQuery(Recette.class);
		Root<Recette> Recette = criteria.from(Recette.class);
		criteria.select(Recette).orderBy(cb.asc(Recette.get("name"))).distinct(true);
		if (saison != null) {
			String[] saisons = saison.split(",");
			Join<Recette, RecetteSaison> join1 = Recette.join("saisons");
			Join<RecetteSaison, Saison> join2 = join1.join("saisons");
			Predicate predicate = null ;
			predicate = cb.equal( join2.get("id"), Integer.parseInt(saisons[0])) ;
			for (String saison1 : saisons) {
				predicate = cb.or(predicate,cb.equal( join2.get("id"), Integer.parseInt(saison1)));
			}
			criteria.where(predicate);
		}
		if(tri != null && tri != ""){
			if(tri.equalsIgnoreCase("alpha")) {
				criteria.orderBy(cb.asc(Recette.get("name")));
			} else if(tri.equalsIgnoreCase("diff")) {
				criteria.orderBy(cb.asc(Recette.get("difficulte")));
			} else if(tri.equalsIgnoreCase("cout")) {
				criteria.orderBy(cb.desc(Recette.get("cout")));
			} else if(tri.equalsIgnoreCase("preparation")) {
				criteria.orderBy(cb.desc(Recette.get("tpsPreparation")));
			} else if(tri.equalsIgnoreCase("cuisson")) {
				criteria.orderBy(cb.desc(Recette.get("tpsCuisson")));
			}
		}
		TypedQuery<Recette> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*6);
		typequery.setMaxResults(6);
		return typequery.getResultList();

	}

	/**
	 * 
	 * @param page numéro de la page, pour la pagination
	 * @param cat catégorie de la recette
	 * @param saison saisons associées à la recette
	 * @param search recherche par nom de recette
	 * @param compo type de la recette (vegetarien ...)
	 * @param tri alpha,name,difficulte,cout,tpsPreparation,tpsCuisson
	 * @return liste de recettes
	 */
	public List<Recette> findAllOrderedByName(int page, String cat, String saison, String search, String compo, String tri) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Recette> criteria = cb.createQuery(Recette.class);
		Root<Recette> Recette = criteria.from(Recette.class);
		Predicate predicate = null ;
		Predicate predicate2 = null;
		criteria.select(Recette).orderBy(cb.asc(Recette.get("name"))).distinct(true);

		if(cat == null || cat == "") {
			if(search == null || search == "") {
				if(compo == null || compo == "")
					return this.findAllOrderedByName(page, saison, tri);
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
				if (d.getName().toLowerCase().contains(search) || d.getIngredients().toLowerCase().contains(search)) {
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
		if(tri != null && tri != ""){
			if(tri.equalsIgnoreCase("alpha")) {
				criteria.orderBy(cb.asc(Recette.get("name")));
			} else if(tri.equalsIgnoreCase("diff")) {
				criteria.orderBy(cb.asc(Recette.get("difficulte")));
			} else if(tri.equalsIgnoreCase("cout")) {
				criteria.orderBy(cb.desc(Recette.get("cout")));
			} else if(tri.equalsIgnoreCase("preparation")) {
				criteria.orderBy(cb.desc(Recette.get("tpsPreparation")));
			} else if(tri.equalsIgnoreCase("cuisson")) {
				criteria.orderBy(cb.desc(Recette.get("tpsCuisson")));
			}
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
		criteria.multiselect(Recette.get("id"), Recette.get("name"), Recette.get("listeIngredients"));
		List<Tuple> tupleResult = em.createQuery(criteria).getResultList();
		logger.log(Level.INFO, "Recette : ");
		for (Tuple t : tupleResult) {
			this.listRecette.add(new DefRecette((Long) t.get(0),(String) t.get(1), (String) t.get(2)));
			logger.log(Level.INFO, (String) t.get(1));
		}
	}

	public class DefRecette {
		private Long id;
		private String name;
		private String ingredients;

		public DefRecette() {
			super();
		}

		public DefRecette(Long id, String name, String ingredients) {
			this.id = id;
			this.name = name;
			this.ingredients = ingredients;
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
		public String getIngredients() {
			return this.ingredients;
		}
		public void setIngredients(String ingredients) {
			this.ingredients = ingredients;
		}
	}

	/**
	 * Recherche de la liste des produits contenus dans une recette
	 * @param id identifiant de la recette
	 * @return liste des produits de la recette
	 */
	public List<Produit> findAllProduitsFromId(int id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produit> criteria = cb.createQuery(Produit.class);
		Root<RecetteProduit> RecetteProduit = criteria.from(RecetteProduit.class);
		Join<RecetteProduit, Produit> join1 = RecetteProduit.join("produits");
		criteria.select(join1);
		criteria.where(cb.equal(RecetteProduit.get("recettes"), id));
		return em.createQuery(criteria).getResultList();
	}

	/**
	 * Recherche des recettes contenant un produit
	 * @param id identifiant du produit
	 * @return liste des recettes
	 */
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

		criteria.select(Recette).distinct(true);
		Expression<String> exp = join2.get("id");
		Predicate predicate = exp.in(listProduit);
		criteria.where(predicate);
		return em.createQuery(criteria).setMaxResults(15).getResultList();
	}

	public Long findNumberPage(String cat, String saison, String search, String compo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		Root<Recette> Recette = criteria.from(Recette.class);
		Predicate predicate = null ;
		Predicate predicate2 = null;
		criteria.select(cb.countDistinct(Recette));

		if(cat == null || cat == "") {
			if(search == null || search == "") {
				if(compo == null || compo == "")
					return this.findNumberPage(saison);
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
		return (long) (Math.ceil((float)em.createQuery(criteria).getSingleResult()/6)) ;
	}

	/**
	 * Recherche du nombre de recette par saison divisé par 6
	 * @param saison la saison selectionnée
	 * @return nombre de page
	 */
	public Long findNumberPage(String saison) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		Root<Recette> Recette = criteria.from(Recette.class);
		criteria.select(cb.countDistinct(Recette));
		Predicate predicate = null ;
		if (saison != null) {
			String[] saisons = saison.split(",");
			Join<Recette, RecetteSaison> join1 = Recette.join("saisons");
			Join<RecetteSaison, Saison> join2 = join1.join("saisons");
			predicate = cb.equal( join2.get("id"), Integer.parseInt(saisons[0])) ;
			for (String saison1 : saisons) {
				predicate = cb.or(predicate,cb.equal( join2.get("id"), Integer.parseInt(saison1)));
			}
			criteria.where(predicate);
		}
		return (long) (Math.ceil((float)em.createQuery(criteria).getSingleResult()/6)) ;
	}
}
