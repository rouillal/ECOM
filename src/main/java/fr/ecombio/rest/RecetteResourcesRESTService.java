package fr.ecombio.rest;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.RecetteRepository;
import fr.ecombio.model.Produit;
import fr.ecombio.model.Recette;


/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the Produits table.
 */
@Path("/recette")
@RequestScoped
public class RecetteResourcesRESTService {

	@Inject
    private RecetteRepository repository;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void AjoutRecette(Recette recette){
		repository.AjoutRecette(recette);
	}
	
	@GET
	@Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recette> listAllProduits(@DefaultValue("0") @QueryParam("page") int page, @QueryParam("cat") String cat, @QueryParam("saison") String saison, @QueryParam("search") String search) {
        return repository.findAllOrderedByName(page, cat, saison, search);
    }
	
}

