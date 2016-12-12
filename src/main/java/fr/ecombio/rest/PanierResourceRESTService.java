package fr.ecombio.rest;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.ResponseWrapper;

import fr.ecombio.data.PanierRepository;
import fr.ecombio.data.ArticleRepository;
import fr.ecombio.data.ProduitRepository;
import fr.ecombio.data.RegistreRepository;
import fr.ecombio.data.StockManagerRepository;
import fr.ecombio.model.Article;
import fr.ecombio.model.GestionArticle;
import fr.ecombio.model.InfosArticle;
import fr.ecombio.model.Panier;
import fr.ecombio.model.Produit;
import fr.ecombio.model.SendEmail;
import fr.ecombio.model.ValidationCommande;

/**
 * <p>
 * Permet un service RESTful read/write pour le panier
 * 
 * @see ArticleRepository
 * @see ProduitRepository
 * @see PanierRepository
 * @see StockManagerRepository
 * @see GestionArticle
 * @see Panier
 * @see Produit
 * @see Article
 *
 */
@Path("/panier")
@RequestScoped
public class PanierResourceRESTService {

	/**
	 * @see PanierRepository
	 */
	@Inject
	private PanierRepository PanierRepository;

	/**
	 * @see ProduitRepository
	 */
	@Inject
	private ProduitRepository ProduitRepository;

	/**
	 * @see ArticleRepository
	 */
	@Inject
	private ArticleRepository ArticleRepository;

	/**
	 * @see StockManagerRepository
	 */
	@Inject
	private StockManagerRepository StockManagerRepository;

	//Logger log;
	Logger log = java.util.logging.Logger.getLogger("org.hibernate");

	/**
	 * Recherche d'un panier
	 * @param id identifiant du panier
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public List<InfosArticle> getPanierFromId(@QueryParam("id") Long id) {
		Panier p = PanierRepository.findById(id);
		List<InfosArticle> retour = new LinkedList<InfosArticle>();
		if (p != null) {
			for (Article a : p.getArticles()) {
				retour.add(new InfosArticle(a.getProduit().getName(), a.getProduit().getVariete(), a.getProduit().getQuantite(), a.getQuotite(),  a.getProduit().getUnite()));
			}
		}
		return retour;
	}

	/**
	 * Creation du panier
	 * @param commande mon panier
	 * @return id du panier cree ou erreur
	 * 
	 * @see GestionArticle
	 * @see Panier
	 * @see Produit
	 * @see Article
	 * @see PanierRepository#AjoutPanier(Panier)
	 * @see PanierRepository#updatePanier(Panier)
	 * @see ProduitRepository#findById(Long)
	 * @see ArticleRepository#AjoutArticle(Article)
	 * @see StockManagerRepository#decrementeStock(Panier, Article)
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public Response createUpdatePanier(GestionArticle[] commande){
		Panier panier = new Panier();
		Long PanierID = PanierRepository.AjoutPanier(panier);
		for(GestionArticle article : commande) {
			Produit produit = ProduitRepository.findById(article.getId());
			// si le stock est suffisant 
			if (produit.getStock()>=1) {
				// on crée l'article
				Article a = new Article();
				a.setProduit(produit);
				a.setQuotite(article.getQuotite());
				a.setPanier(panier);
				ArticleRepository.AjoutArticle(a);
				// on l'ajoute au panier
				panier.getArticles().add(a);
				StockManagerRepository.decrementeStock(panier,a);
			} else {
				return Response.notModified("Le stock de ce produit n'est pas suffisant").build();
			}
		}
		// on va alors décrementer les stocks en base
		PanierRepository.updatePanier(panier);
		return Response.ok(PanierID).build();
	}

	/**
	 * Mise a jour du panier
	 * @param id identifiant du panier
	 * @param commande mon panier
	 * @return ok ou erreur
	 * 
	 * @see GestionArticle
	 * @see Panier
	 * @see Produit
	 * @see Article
	 * @see PanierRepository#findById(Long)
	 * @see PanierRepository#updatePanier(Panier)
	 * @see ProduitRepository#findById(Long)
	 * @see ArticleRepository#AjoutArticle(Article)
	 * @see ArticleRepository#updateArticle(Article)
	 * @see StockManagerRepository#decrementeStock(Panier, Article)
	 * @see StockManagerRepository#incrementeStock(Panier,Article)
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public Response createUpdatePanier(@QueryParam("id") Long id, GestionArticle[] commande){
		log.log(Level.INFO, "begin transaction");
		Panier panier = PanierRepository.findById(id);
		// si le panier existe
		if (panier != null) {
			// pour chaque article
			for(GestionArticle article : commande) {
				log.log(Level.INFO, "article"+article.getId());
				// on va chercher le produit correspondant en base
				Produit produit = ProduitRepository.findById(article.getId());
				// si le stock est suffisant
				// on met à jour les quantite du panier 
				Article a = panier.getArticle(produit.getId());
				if (a!=null) {
					if (a.getQuotite() < article.getQuotite() ) {
						if (produit.getStock()>=1) {
							for (int i =0; i<Math.abs(a.getQuotite() - article.getQuotite()) ; i++) {
								StockManagerRepository.decrementeStock(panier,a);
							}
							a.setQuotite(article.getQuotite());
							ArticleRepository.updateArticle(a);
						} else {
							log.log(Level.INFO, "echec pas de stock pour "+produit.getName()+", end transaction");
							log.log(Level.INFO, "quotité panier "+article.getQuotite());
							log.log(Level.INFO, "quotité base "+a.getQuotite());
							return Response.notModified("Le stock de ce produit n'est pas suffisant").build();
						}
					} else if (a.getQuotite() > article.getQuotite()) {
						for (int i =0; i<Math.abs(a.getQuotite() - article.getQuotite()) ; i++) {
							StockManagerRepository.incrementeStock(panier,a);
						}
						a.setQuotite(article.getQuotite());
						ArticleRepository.updateArticle(a);
					} 
				} else {
					log.log(Level.INFO, panier.toString());
					if (produit.getStock()>=1) {
						log.log(Level.INFO, "ajout:"+produit.getName());
						Article a2 = new Article();
						a2.setProduit(produit);
						a2.setQuotite(article.getQuotite());
						a2.setPanier(panier);
						ArticleRepository.AjoutArticle(a2);
						panier.getArticles().add(a2);
						for (int i =0; i<a2.getQuotite() ; i++) {
							StockManagerRepository.decrementeStock(panier,a2);
						} 
					} else {								
						log.log(Level.INFO, "echec pas de stock pour "+produit.getName()+", end transaction");
						log.log(Level.INFO, "quotité panier "+article.getQuotite());
						return Response.notModified("Le stock de ce produit n'est pas suffisant").build();
					}
				}
			} 
			// on supprime les articles à supprimer
			List<Article> toDelete = new LinkedList<Article>();
			for (Article a : panier.getArticles()){
				boolean isInCommande = false;
				for(GestionArticle article : commande) {
					if (article.getId() == a.getProduit().getId()) {
						isInCommande = true;
						break;
					}
				}
				if (!isInCommande) {
					toDelete.add(a);
				}
			}
			for (Article a : toDelete) {
				for (int i =0; i<Math.abs(a.getQuotite()) ; i++) {
					StockManagerRepository.incrementeStock(panier,a);
				}
				panier.getArticles().remove(a);
			}
			log.log(Level.INFO, "save : " +panier.toString());
			PanierRepository.updatePanier(panier);
			return Response.ok().build();
		} else {
			log.log(Level.INFO, "echec end transaction");
			Throwable cause = new Throwable("Votre panier a été supprimé, temps d'inactivité trop long");
			throw new WebApplicationException(cause,Response.Status.NOT_FOUND);
		}
	}
}
