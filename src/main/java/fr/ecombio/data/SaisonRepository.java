package fr.ecombio.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Saison;

/**
 * <p>
 * Permet une gestion des saisons :
 * <ul>
 * 	<li>faire des requetes de select</li>
 * 	<li>ajouter une saison en base</li>
 *  </ul>
 * </p>
 * 
 * @see EntityManager
 * @see Saison
 *
 */
@Stateless
public class SaisonRepository {

	/**
	 * pour gerer l'aspect transactionnel
	 * 
	 * @see EntityManager
	 */
	@Inject
	private EntityManager em;

	/**
	 * Recherche d'une saison par id
	 * @param id
	 * @return la saison correspondante
	 * 
	 * @see Saison
	 */
	public Saison findById(Long id) {
		return em.find(Saison.class, id);
	}

	/**
	 * Recherche de toutes les saisons
	 * @return la liste des saison
	 * 
	 * @see Saison
	 */
	public List<Saison> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Saison> criteria = cb.createQuery(Saison.class);
		Root<Saison> Saison = criteria.from(Saison.class);
		criteria.select(Saison).orderBy(cb.asc(Saison.get("name")));
		return em.createQuery(criteria).getResultList();
	}
	
	/**
	 * Ajout d'une saison
	 * 
	 * @see Saison
	 */
	public  void AjoutSaison(Saison saison) {
		em.persist(saison);
	}
}