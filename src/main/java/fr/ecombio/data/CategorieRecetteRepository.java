package fr.ecombio.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Categorie;
import fr.ecombio.model.CategorieRecette;

/**
 * <p>
 * Permet une gestion des categories des recettes :
 * <ul>
 * 	<li>faire des requetes de select</li>
 * 	<li>ajouter une saison en base</li>
 *  </ul>
 * </p>
 * 
 * @see EntityManager
 * @see CategorieRecette
 *
 */
@Stateless
public class CategorieRecetteRepository {
	
	/**
	 * pour gerer l'aspect transactionnel
	 * 
	 * @see EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * Recherche d'une categorie par son identifiant
	 * @param id identificateur de la categorie
	 * @return la categorie
	 */
	public CategorieRecette findById(Long id) {
		return em.find(CategorieRecette.class, id);
	}

	/**
	 * Recherche de l'ensemble des categories
	 * @return liste des categories
	 */
	public List<CategorieRecette> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CategorieRecette> criteria = cb.createQuery(CategorieRecette.class);
		Root<CategorieRecette> CategorieRecette = criteria.from(CategorieRecette.class);
		criteria.select(CategorieRecette).orderBy(cb.asc(CategorieRecette.get("name")));
		return em.createQuery(criteria).getResultList();
	}
	
	/**
	 * Persitence d'une categorie
	 * @param cat categorie
	 */
	public  void AjoutCategorieRecette(CategorieRecette cat) {
		em.persist(cat);
	}

}
