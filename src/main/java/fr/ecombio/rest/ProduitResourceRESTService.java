package fr.ecombio.rest;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.ProduitRepository;
import fr.ecombio.model.Produit;


/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the Produits table.
 */
@Path("/produit")
@RequestScoped
public class ProduitResourceRESTService {

	@Inject
    private ProduitRepository repository;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produit> listAllProduits(@DefaultValue("0") @QueryParam("page") int page, @QueryParam("tri") String tri, @QueryParam("saison") int saison) {
        return repository.findAllOrderedByName(page, tri, saison);
    }
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void AjoutProduit(Produit prod){
		repository.AjoutProduit(prod);
	}
	
	@GET
	@Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produit> listAllProduits(@QueryParam("cat") String cat, @QueryParam("search") String search, @QueryParam("saison") int saison,
    		@DefaultValue("0") @QueryParam("page") int page, @QueryParam("tri") String tri) {
        return repository.findCatOrderedByName(cat, search, page, tri, saison);
    }
	
	@GET
	@Path("/page/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Long NumberPage(@QueryParam("cat") String cat, @QueryParam("search") String search, @QueryParam("saison") int saison) {
        return repository.findNumberPage(cat, search, saison);
    }
	
	@GET
	@Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    public Long NumberPage(@QueryParam("saison") int saison) {
        return repository.findNumberPage(saison);
    }
}

