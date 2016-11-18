package fr.ecombio.rest;


import java.util.Collection;
import java.util.List;

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

	/*objet en JSon
	 * {
	 * 		{id1:,qte1},{id2,qte2},...
	 * }
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public Response createUpdatePanier(GestionArticle[] commande) {
		Panier panier = new Panier();
		for(GestionArticle article : commande) {
			Produit produit = ProduitRepository.findById(article.getId());
			// si le stock est suffisant 
			if (produit.getStock()>=1) {
				// on crée l'article
				Article a = new Article(produit,article.getQuotite());
				ArticleRepository.AjoutArticle(a);
				// on l'ajoute au panier
				panier.getArticles().put(article.getId(), a);
			} else {
				return Response.notModified("Le stock de ce produit n'est pas suffisant").build();
			}
		}
		// on va alors décrementer les stocks en base
		StockManagerRepository.decrementeStock(panier);
		return Response.ok(PanierRepository.AjoutPanier(panier)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public Response createUpdatePanier(@QueryParam("id") Long id, GestionArticle[] commande) {
		Panier panier = PanierRepository.findById(id);
		// si le panier existe
		if (panier != null) {
			// pour chaque article
			for(GestionArticle article : commande) {
				// on va chercher le produit correspondant en base
				Produit produit = ProduitRepository.findById(article.getId());
				// si le stock est suffisant
				if (produit.getStock()>=1) {
					// on met à jour les quantite du panier 
					if (panier.getArticles().containsKey(produit.getId())) {
						Article a = panier.getArticles().get(produit.getId());
						ArticleRepository.updateArticle(a);
						panier.getArticles().put(a.getProduit().getId(), a);
					} else {
						Article a = new Article(produit,article.getQuotite());
						ArticleRepository.AjoutArticle(a);
						panier.getArticles().put(a.getProduit().getId(), a);
					}
				} else {
					return Response.notModified("Le stock de ce produit n'est pas suffisant").build();
				}
			}
			PanierRepository.updatePanier(panier);
			// on va alors décrementer les stocks en base
			StockManagerRepository.decrementeStock(panier);
			return Response.ok().build();
		} else {
			Throwable cause = new Throwable("Votre panier a été supprimé, temps d'inactivité trop long");
			throw new WebApplicationException(cause,Response.Status.NOT_FOUND);
		}
	}
}
