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
import javax.ws.rs.core.MediaType;
import javax.xml.ws.ResponseWrapper;

import fr.ecombio.data.PanierRepository;
import fr.ecombio.data.ArticleRepository;
import fr.ecombio.data.ProduitRepository;
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

	//Logger log;

	/*objet en JSon
	 * {
	 * 		{id1:,qte1},{id2,qte2},...
	 * }
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public Long createUpdatePanier(GestionArticle[] commande) {
		Panier panier = new Panier();
		for(GestionArticle article : commande) {
			Produit produit = ProduitRepository.findById(article.getId());
			/*
			 * TO DO : Update des stocks
			 */
			Article a = new Article(produit,article.getQuotite());
			ArticleRepository.AjoutArticle(a);
			panier.getArticles().put(article.getId(), a);
		}
		return PanierRepository.AjoutPanier(panier);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void createUpdatePanier(@QueryParam("id") Long id, GestionArticle[] commande) {
		Panier panier = PanierRepository.findById(id);
		for(GestionArticle article : commande) {
			Produit produit = ProduitRepository.findById(article.getId());
			/*
			 * TO DO : Update des stocks
			 */
			if (panier.getArticles().containsKey(produit.getId())) {
				Article a = panier.getArticles().get(produit.getId());
				ArticleRepository.updateArticle(a);
				panier.getArticles().put(a.getProduit().getId(), a);
			} else {
				Article a = new Article(produit,article.getQuotite());
				ArticleRepository.AjoutArticle(a);
				panier.getArticles().put(a.getProduit().getId(), a);
			}
		}
		PanierRepository.updatePanier(panier);
	}

}
