package fr.ecombio.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public List<Produit> listAllProduits() {
        return repository.findAllOrderedByName();
    }
}
