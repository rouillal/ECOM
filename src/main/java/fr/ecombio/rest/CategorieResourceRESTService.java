package fr.ecombio.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.CategorieRepository;
import fr.ecombio.data.ProduitRepository;
import fr.ecombio.model.Categorie;
import fr.ecombio.model.Produit;

/**
 * <p>
 * Permet un service RESTful read/write pour les categories
 * 
 * @see CategorieRepository
 * @see Categorie
 *
 */
@Path("/categorie")
@RequestScoped
public class CategorieResourceRESTService {

	/**
	 * @see CategorieRepository
	 */
	@Inject
    private CategorieRepository repository;

	/**
	 * Recherche de toutes les categories
	 * @return liste des categories
	 * 
	 * @see Categorie
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categorie> listAllCategories() {
        return repository.findAllOrderedByName();
	}
	
	
}
