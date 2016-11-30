package fr.ecombio.rest;


import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
import fr.ecombio.data.StockManagerRepository;
import fr.ecombio.model.Article;
import fr.ecombio.model.GestionArticle;
import fr.ecombio.model.Panier;
import fr.ecombio.model.Produit;

@Path("/panier")
@RequestScoped
public class PanierResourceRESTService {

	@Inject
	private PanierRepository PanierRepository;

	@Inject
	private ProduitRepository ProduitRepository;

	@Inject
	private ArticleRepository ArticleRepository;

	@Inject
	private StockManagerRepository StockManagerRepository;

	//Logger log;
	Logger log = java.util.logging.Logger.getLogger("org.hibernate");

	/*objet en JSon
	 * {
	 * 		{id1:,qte1},{id2,qte2},...
	 * }
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
				Article a = panier.getArticle(produit.getId());
				if (produit.getStock()>=1) {
					// on met à jour les quantite du panier 
					if (panier.contains(produit.getId())) {
						if ( a!= null ){
							if (a.getQuotite() < article.getQuotite() ) {
								for (int i =0; i<Math.abs(a.getQuotite() - article.getQuotite()) ; i++) {
									StockManagerRepository.decrementeStock(panier,a);
								}
								a.setQuotite(article.getQuotite());
								ArticleRepository.updateArticle(a);
							} else if (a.getQuotite() > article.getQuotite()) {
								for (int i =0; i<Math.abs(a.getQuotite() - article.getQuotite()) ; i++) {
									StockManagerRepository.incrementeStock(panier,a);
								}
								a.setQuotite(article.getQuotite());
								ArticleRepository.updateArticle(a);
							}
						}
					} else {
						log.log(Level.INFO, "ajout");
						Article a2 = new Article();
						a2.setProduit(produit);
						a2.setQuotite(article.getQuotite());
						ArticleRepository.AjoutArticle(a2);
						panier.getArticles().add(a2);
						for (int i =0; i<a2.getQuotite() ; i++) {
							StockManagerRepository.decrementeStock(panier,a2);
						}
					}
					PanierRepository.updatePanier(panier);
				} else if (panier.contains(produit.getId()) && a.getQuotite() != article.getQuotite()) {
					log.log(Level.INFO, "echec end transaction");
					return Response.notModified("Le stock de ce produit n'est pas suffisant").build();
				}
				//log.log(Level.INFO, Integer.toString(a.getQuotite()) + Integer.toString(article.getQuotite()));
			}
			// on va alors décrementer les stocks en base
			PanierRepository.updatePanier(panier);
			return Response.ok().build();
		} else {
			log.log(Level.INFO, "echec end transaction");
			Throwable cause = new Throwable("Votre panier a été supprimé, temps d'inactivité trop long");
			throw new WebApplicationException(cause,Response.Status.NOT_FOUND);
		}
	}
}
