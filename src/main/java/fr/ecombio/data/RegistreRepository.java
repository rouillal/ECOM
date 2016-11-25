package fr.ecombio.data;

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
import fr.ecombio.model.Panier;
import fr.ecombio.model.RegistreClient;
import fr.ecombio.model.ValidationClient;
import fr.ecombio.model.ValidationCommande;


@Stateless
public class RegistreRepository {

	@Inject
	private EntityManager em;

	Logger log = java.util.logging.Logger.getLogger("org.hibernate");


	public Client findClientByMailAndMdp(String mail, String mdp) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RegistreClient> criteria = cb.createQuery(RegistreClient.class);
		Root<RegistreClient> RegistreClient = criteria.from(RegistreClient.class);
		criteria.select(RegistreClient).where( cb.and(cb.equal( RegistreClient.get("mail"), mail ), cb.equal( RegistreClient.get("mdp"), mdp )));
		List<RegistreClient> res = em.createQuery(criteria).getResultList();
		if (res.size() != 1) {
			return null;
		} 
		return res.get(0).getClient();
	}

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

	public void registerClient(ValidationClient vc) {
		Client client = new Client();
		client.setNom(vc.getNom());
		client.setPrenom(vc.getPrenom());
		client.setMail(vc.getMail());
		client.setAdresse(vc.getAdresse());
		client.setCp(vc.getCp());
		client.setVille(vc.getVille());
		em.persist(client);
		RegistreClient reg = new RegistreClient();
		reg.setClient(client);
		reg.setMail(vc.getMail());
		reg.setMdp(vc.getPsw());
		em.persist(reg);
	}

	public void registerCommande(ValidationCommande infos) {
		log.log(Level.INFO,"debut enregistrement commande");
		// on regarde si le client existe en base de donnee
		Client client = this.findClientByMail(infos.getCommandInfo().getMail());
		log.log(Level.INFO,"get client");
		if (client == null) {
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
		nouveauPanier.setDateDerniereModif(null);
		nouveauPanier.setArticles(ancienPanier.getArticles());
		log.log(Level.INFO,"save panier");
		em.persist(nouveauPanier);
		log.log(Level.INFO,"ok");

		HistoriqueCommande com = new HistoriqueCommande();
		com.setDate(infos.getCommandInfo().getDate());
		com.setHeure(infos.getCommandInfo().getHeure());
		com.setClient(client);
		com.setPanier(nouveauPanier);
		em.persist(com);
	}
}
