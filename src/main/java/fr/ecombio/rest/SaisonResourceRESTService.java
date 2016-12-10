package fr.ecombio.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.ecombio.model.Article;
import fr.ecombio.model.GestionArticle;
import fr.ecombio.model.Panier;
import fr.ecombio.model.Produit;
import fr.ecombio.model.Saison;
import fr.ecombio.data.ArticleRepository;
import fr.ecombio.data.PanierRepository;
import fr.ecombio.data.ProduitRepository;
import fr.ecombio.data.SaisonRepository;
import fr.ecombio.data.StockManagerRepository;

/**
 * <p>
 * Permet un service RESTful read/write pour les saisons
 * 
 * @see SaisonRepository
 * @see Saison
 *
 */
@Path("/saison")
@RequestScoped
public class SaisonResourceRESTService {

	/**
	 * @see SaisonRepository
	 */
	@Inject
    private SaisonRepository repository;

	/**
	 * Recherche de l'ensemble des saisons
	 * @return liste des saisons
	 * 
	 * @see Saison
	 * @see SaisonRepository#findAllOrderedByName()
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Saison> listAllSaison() {
        return repository.findAllOrderedByName();
	}
	
	
}