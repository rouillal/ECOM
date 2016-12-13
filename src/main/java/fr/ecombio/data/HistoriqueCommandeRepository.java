package fr.ecombio.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
@Stateless
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
	 * @param page pagination
	 * @param d si on veut voir les livraison a domicile
	 * @param e si on veut voir les livraison a l'entrepot
	 * @return liste des commandes
	 */
	public List<HistoriqueCommande> getHistoFromDate(String date, boolean e, boolean d, int page) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<HistoriqueCommande> criteria = cb.createQuery(HistoriqueCommande.class);
		Root<HistoriqueCommande> HistoriqueCommande = criteria.from(HistoriqueCommande.class);
		criteria.select(HistoriqueCommande).orderBy(cb.asc(HistoriqueCommande.get("heure")));

		Predicate predicate = cb.equal(HistoriqueCommande.get("date"), date) ;
		Predicate p2 = null;
		boolean b = true;
		if (e) {
			p2 = cb.equal(HistoriqueCommande.get("livDom"), "e");
			b = false;
		} else {
			p2 = cb.equal(HistoriqueCommande.get("livDom"), "x");
		}
		if (d) {
			if (b) {
				p2 = cb.equal(HistoriqueCommande.get("livDom"), "d");
			} else {
				p2 = cb.or(p2, cb.equal(HistoriqueCommande.get("livDom"), "d"));
			}
		}
		if (p2 != null) {
			predicate = cb.and(predicate,p2);
		}
		criteria.where(predicate);
		TypedQuery<HistoriqueCommande> typequery = em.createQuery(criteria);
		typequery.setFirstResult(page*2);
		typequery.setMaxResults(2);
		return typequery.getResultList();
	}

	/**
	 * Nombre de page de l'historique des commandes
	 * @param date jour que l'on souhaite voir
	 * @param d si on veut voir les livraison a domicile
	 * @param e si on veut voir les livraison a l'entrepot
	 * @return
	 */
	public Long getNbPageHisto(String date, boolean e, boolean d) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		Root<HistoriqueCommande> HistoriqueCommande = criteria.from(HistoriqueCommande.class);
		criteria.select(cb.countDistinct(HistoriqueCommande));

		Predicate predicate = cb.equal(HistoriqueCommande.get("date"), date) ;
		Predicate p2 = null;
		boolean b = true;
		if (e) {
			p2 = cb.equal(HistoriqueCommande.get("livDom"), "e");
			b = false;
		} else {
			p2 = cb.equal(HistoriqueCommande.get("livDom"), "x");
		}
		if (d) {
			if (b) {
				p2 = cb.equal(HistoriqueCommande.get("livDom"), "d");
			} else {
				p2 = cb.or(p2, cb.equal(HistoriqueCommande.get("livDom"), "d"));
			}
		}
		if (p2 != null) {
			predicate = cb.and(predicate,p2);
		}
		criteria.where(predicate);
		return (long) (Math.ceil((float)em.createQuery(criteria).getSingleResult()/2)) ;
	}
	/**
	 * Mise a jour de l'etat d'une commande
	 * @param isDelivred true si a ete livree, false sinon
	 * @param id id de la commande
	 */
	public void setCommandeDelivred(boolean isDelivred, Long id) {
		HistoriqueCommande commande = this.findById(id);
		if(commande != null){
			commande.setDelivred(isDelivred);
			em.merge(commande);
		}
	}

}
