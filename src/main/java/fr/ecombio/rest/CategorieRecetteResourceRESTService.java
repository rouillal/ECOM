package fr.ecombio.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import fr.ecombio.model.CategorieRecette;
import fr.ecombio.model.Produit;
import fr.ecombio.data.CategorieRecetteRepository;

/**
 * <p>
 * Permet un service RESTful read/write pour les categorie de recettes 
 * 
 * @see CategorieRecetteRepository
 * @see CategorieRecette
 *
 */
@Path("/categorieRecette")
@RequestScoped
public class CategorieRecetteResourceRESTService {

	/**
	 * @see CategorieRecetteRepository
	 */
	@Inject
    private CategorieRecetteRepository repository;

	/**
	 * @see CategorieRecette
	 * @return liste des categories
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategorieRecette> listAllCategoriesRecette() {
        return repository.findAllOrderedByName();
	}
	
	
}
