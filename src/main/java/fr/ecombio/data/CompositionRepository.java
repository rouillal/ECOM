package fr.ecombio.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Composition;
import fr.ecombio.model.Produit;

/**
 * <p>
 * Permet une gestion des compostion :
 * <ul>
 * 	<li>faire des requetes de select</li>
 * 	<li>ajouter une composition en base</li>
 *  </ul>
 * </p>
 * 
 * @see EntityManager
 * @see Composition
 *
 */
@Stateless
public class CompositionRepository {
	
	/**
	 * pour gerer l'aspect transactionnel
	 * 
	 * @see EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * Recherche d'une composition par son identifiant
	 * @param id identificateur de la composition
	 * @return composition
	 */
	public Composition findById(Long id) {
		return em.find(Composition.class, id);
	}

	/**
	 * Recherche de toutes les compositions
	 * @return liste de composition
	 */
	public List<Composition> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Composition> criteria = cb.createQuery(Composition.class);
		Root<Composition> Composition = criteria.from(Composition.class);
		criteria.select(Composition).orderBy(cb.asc(Composition.get("name")));
		return em.createQuery(criteria).getResultList();
	}
	

	/**
	 * Persistence d'une composition
	 * @param comp Composition
	 */
	public  void AjoutCompoRecette(Composition comp) {
		em.persist(comp);
	}

}
