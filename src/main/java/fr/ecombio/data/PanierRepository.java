package fr.ecombio.data;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Article;
import fr.ecombio.model.Panier;
import fr.ecombio.model.Produit;

/**
 * <p>
 * Permet une gestion du panier :
 * <ul>
 * 	<li>faire des requetes de select</li>
 * 	<li>ajouter une saison en base</li>
 *  </ul>
 * </p>
 * 
 * @see EntityManager
 * @see Panier 
 * @see ArticleRepository
 *
 */
@Stateless
public class PanierRepository {
	
	/**
	 * pour gerer l'aspect transactionnel
	 * 
	 * @see EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * 
	 * @see ArticleRepository
	 */
	@Inject
	private ArticleRepository ArticleRepository;
	
	//Logger log;
	Logger log = java.util.logging.Logger.getLogger("org.hibernate");

	/**
	 * Ajout d'un panier en base
	 * @param panier panier a ajouter
	 * @return identifiant genere automatiquement
	 */
	public Long AjoutPanier(Panier panier) {
		em.persist(panier);
		return panier.getId();
	}
	
	/**
	 * Recherche d'un panier avec son identifiant
	 * @param id identifiant du panier
	 * @return panier
	 */
	public Panier findById(Long id) {
		Panier p = em.find(Panier.class, id);
		log.log(Level.INFO, p.toString());
		return p;
	}

	/**
	 * Modification du panier
	 * @param panier panier a modifier
	 */
	public void updatePanier(Panier panier) {
		Iterator<Article> i=panier.getArticles().iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			ArticleRepository.updateArticle(i.next());
		}
		em.merge(panier);
	}

	/**
	 * Recherche de tous les paniers
	 * @return liste de paniers
	 */
	public List<Panier> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Panier> criteria = cb.createQuery(Panier.class);
		Root<Panier> Panier = criteria.from(Panier.class);
		criteria.select(Panier);
		return em.createQuery(criteria).getResultList();
	}

	/**
	 * Supprimer un panier
	 * @param panier panier a supprimer
	 */
	public void SupprimePanier(Panier panier) {
		em.remove(em.find(Panier.class, panier.getId()));
	}

}