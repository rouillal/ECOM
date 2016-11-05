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
@Path("/categorie")
@RequestScoped
public class CategorieResourceRESTService {

	@Inject
    private CategorieRepository repository;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categorie> listAllCategories() {
        return repository.findAllOrderedByName();
	}
}
