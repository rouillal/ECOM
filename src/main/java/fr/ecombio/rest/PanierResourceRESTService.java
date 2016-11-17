package fr.ecombio.rest;


import java.io.PrintWriter;
import java.util.List;
//import org.jboss.logging.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.PanierRepository;
import fr.ecombio.model.GestionCommande;
import fr.ecombio.model.Panier;

@Path("/panier")
@RequestScoped
public class PanierResourceRESTService {

	@Inject
    private PanierRepository repository;
	
	//Logger log;

	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Long createUpdatePanier(Object commande) {
		/*PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
	    writer.println("The first line");
	    writer.println("The second line");
	    writer.close();*/
		//log.debug(commande.toString());
		/*Collection<Article> articles = new Collection<Article>();
		
		Panier panier = new Panier(articles);
        return repository.AjoutPanier(panier);*/
		return 150L;
	}
	
}
