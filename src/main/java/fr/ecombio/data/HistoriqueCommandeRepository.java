package fr.ecombio.data;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Categorie;
import fr.ecombio.model.HistoriqueCommande;

/**
 * <p>
 * Permet une gestion des Commandes :
 * <ul>
 * 	<li>voir les commandes du jour</li>
 * 	<li>marquer une commande comme livree</li>
 *  </ul>
 * </p>
 * 
 * @see EntityManager
 * @see HistoriqueCommande
 *
 */
public class HistoriqueCommandeRepository {

	/**
	 * pour gerer l'aspect transactionnel
	 * 
	 * @see EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * Recherche d'une commande par son identifiant
	 * @param id identificateur de la commande
	 * @return la commande
	 */
	public HistoriqueCommande findById(Long id) {
		return em.find(HistoriqueCommande.class, id);
	}
	
	/**
	 * Recherche de l'historique des commande à un jour donné
	 * @param date jour que l'on souhaite voir
	 * @return liste des commandes
	 */
	public List<HistoriqueCommande> getHistoFromDate(String date) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<HistoriqueCommande> criteria = cb.createQuery(HistoriqueCommande.class);
		Root<HistoriqueCommande> HistoriqueCommande = criteria.from(HistoriqueCommande.class);
		criteria.select(HistoriqueCommande).orderBy(cb.asc(HistoriqueCommande.get("heure")));
		criteria.where(cb.equal(HistoriqueCommande.get("date"), date));
		return em.createQuery(criteria).getResultList();
	}

	/**
	 * Mise a jour de l'etat d'une commande
	 * @param isDelivred true si a ete livree, false sinon
	 * @param id id de la commande
	 */
	public void setCommandeDelivred(boolean isDelivred, Long id) {
		HistoriqueCommande commande = this.findById(id);
		commande.setDelivred(isDelivred);
		em.merge(commande);
	}

}
