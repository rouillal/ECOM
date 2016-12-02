package fr.ecombio.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import fr.ecombio.model.CategorieRecette;
import fr.ecombio.data.CategorieRecetteRepository;


@Path("/categorieRecette")
@RequestScoped
public class CategorieRecetteResourceRESTService {

	@Inject
    private CategorieRecetteRepository repository;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategorieRecette> listAllCategoriesRecette() {
        return repository.findAllOrderedByName();
	}
	
	
}
