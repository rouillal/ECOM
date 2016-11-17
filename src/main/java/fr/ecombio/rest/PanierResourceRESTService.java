package fr.ecombio.rest;


import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.PanierRepository;
import fr.ecombio.model.Article;
import fr.ecombio.model.GestionCommande;
import fr.ecombio.model.Panier;

@Path("/panier")
@RequestScoped
public class PanierResourceRESTService {

	@Inject
    private PanierRepository repository;
	
	Logger log;

	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Long createUpdatePanier(Collection<Article> commande) {
		
		//Collection<Article> articles = new Collection<Article>();
		
		Panier panier = new Panier(commande);
        return repository.AjoutPanier(panier);
	}
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void createUpdatePanier(@QueryParam("id") Long id, Collection<Article> commande) {
		
		//Collection<Article> articles = new Collection<Article>();
		
		Panier panier = repository.findById(id);
		panier.setArticles(commande);
		repository.updatePanier(panier);
	}
	
}
