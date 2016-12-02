package fr.ecombio.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.CompositionRepository;
import fr.ecombio.model.Composition;

@Path("/composition")
@RequestScoped
public class CompositionResourceRESTService {
	
	@Inject
    private CompositionRepository repository;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Composition> listAllComposition() {
        return repository.findAllOrderedByName();
	}
	
}
