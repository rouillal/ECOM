package fr.ecombio.data;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Client;
import fr.ecombio.model.HistoriqueCommande;
import fr.ecombio.model.MyCryptoConverter;
import fr.ecombio.model.Panier;
import fr.ecombio.model.RegistreClient;
import fr.ecombio.model.Saison;
import fr.ecombio.model.ValidationClient;
import fr.ecombio.model.ValidationCommande;

/**
 * <p>
 * Permet une gestion des clients :
 * <ul>
 * 	<li>faire des requetes de select</li>
 * 	<li>ajouter en base</li>
 *  </ul>
 * </p>
 * 
 * @see EntityManager
 * @see RegistreClient
 * @see Client
 *
 */
@Stateless
public class RegistreRepository {

	/**
	 * pour gerer l'aspect transactionnel
	 * 
	 * @see EntityManager
	 */
	@Inject
	private EntityManager em;

	Logger log = java.util.logging.Logger.getLogger("org.hibernate");

	/**
	 * Recherche d'un client avec ses identifiants
	 * @param mail login du client
	 * @param mdp mot de passe
	 * @return le client
	 */
	public Client findClientByMailAndMdp(String mail, String mdp) {
		String encryptedPwd = MyCryptoConverter.encrypt(mdp);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RegistreClient> criteria = cb.createQuery(RegistreClient.class);
		Root<RegistreClient> RegistreClient = criteria.from(RegistreClient.class);
		criteria.select(RegistreClient).where( cb.and(cb.equal( RegistreClient.get("mail"), mail ), cb.equal( RegistreClient.get("mdp"), encryptedPwd )));
		List<RegistreClient> res = em.createQuery(criteria).getResultList();
		if (res.size() != 1) {
			return null;
		} 
		return res.get(0).getClient();
	}

	/**
	 * Recherche d'un client avec son mail
	 * @param mail login du client
	 * @return le client
	 */
	public Client findClientByMail(String mail) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
		Root<Client> Client = criteria.from(Client.class);
		criteria.select(Client).where(cb.equal( Client.get("mail"), mail ));
		List<Client> res = em.createQuery(criteria).getResultList();
		if (res.size() != 1) {
			return null;
		} 
		return res.get(0);
	}

	/**
	 * Enregistrement du client
	 * @param infos informations 
	 */
	public void registerClient(ValidationClient infos) {
		Client client = new Client();
		client.setNom(infos.getNom());
		client.setPrenom(infos.getPrenom());
		client.setMail(infos.getMail());
		client.setAdresse(infos.getAdresse());
		client.setCp(infos.getCp());
		client.setVille(infos.getVille());
		if (infos.getMail().equals("biotobealive@gmail.com")) {
			client.setTypeClient("a");
		}
		em.persist(client);
		RegistreClient reg = new RegistreClient();
		reg.setClient(client);
		reg.setMail(infos.getMail());
		reg.setMdp(infos.getPsw());
		em.persist(reg);
	}

	/**
	 * Enregistrement du'une commande:
	 * <ul>
	 * <li>enregistrement du client</li>
	 * <li>enregistrement du panier associe</li>
	 * </ul>
	 * @param infos infos de la commande
	 */
	public void registerCommande(ValidationCommande infos) {
		log.log(Level.INFO,"debut enregistrement commande");
		// on regarde si le client existe en base de donnee
		Client client = this.findClientByMail(infos.getCommandInfo().getMail());
		log.log(Level.INFO,"get client");
		if (client == null ) {
			log.log(Level.INFO,"client null");
			client = new Client();
			client.setNom(infos.getCommandInfo().getNom());
			client.setPrenom(infos.getCommandInfo().getPrenom());
			client.setMail(infos.getCommandInfo().getMail());
			if (infos.getCommandInfo().getLivDom().equals("d")) {
				log.log(Level.INFO,"livraison à domicile");
				client.setAdresse(infos.getCommandInfo().getAdresse());
				client.setCp(infos.getCommandInfo().getCp());
				client.setVille(infos.getCommandInfo().getVille());
			}
			log.log(Level.INFO,"save client");
			em.persist(client);
			log.log(Level.INFO,"ok");
		}
		Panier nouveauPanier = new Panier();
		log.log(Level.INFO,"get panier");
		Panier ancienPanier = em.find(Panier.class, infos.getIdPanier());
		nouveauPanier.setDateDerniereModif(new Date());
		nouveauPanier.setArticles(ancienPanier.getArticles());
		nouveauPanier.setIsRegistred(true);
		log.log(Level.INFO,"save panier");
		em.persist(nouveauPanier);
		em.remove(ancienPanier);
		log.log(Level.INFO,"ok");

		HistoriqueCommande com = new HistoriqueCommande();
		com.setDate(infos.getCommandInfo().getDate());
		com.setHeure(infos.getCommandInfo().getHeure());
		com.setClient(client);
		com.setPanier(nouveauPanier);
		com.setLivDom(infos.getCommandInfo().getLivDom());
		em.persist(com);
	}
}
