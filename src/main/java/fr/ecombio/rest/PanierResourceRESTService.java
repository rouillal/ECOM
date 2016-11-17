package fr.ecombio.rest;


import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.PanierRepository;
import fr.ecombio.model.Panier;

@Path("/categorie")
@RequestScoped
public class PanierResourceRESTService {

	@Inject
    private PanierRepository repository;

	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Long listAllCategories(Panier panier) {
        return repository.AjoutPanier(panier);
	}
	
}
