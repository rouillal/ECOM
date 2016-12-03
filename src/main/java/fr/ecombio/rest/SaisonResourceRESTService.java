package fr.ecombio.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import fr.ecombio.model.Saison;
import fr.ecombio.data.SaisonRepository;


@Path("/saison")
@RequestScoped
public class SaisonResourceRESTService {

	@Inject
    private SaisonRepository repository;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Saison> listAllSaison() {
        return repository.findAllOrderedByName();
	}
	
	
}