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

/**
 * <p>
 * Permet un service RESTful read/write pour les composition (regimes)
 * 
 * @see CompositionRepository
 * @see Composition
 *
 */
@Path("/composition")
@RequestScoped
public class CompositionResourceRESTService {
	
	/**
	 * @see CompositionRepository
	 */
	@Inject
    private CompositionRepository repository;

	/**
	 * Recherche de la liste des compositions
	 * @return liste des compostions
	 * 
	 * @see CompositionRepository#findAllOrderedByName()
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Composition> listAllComposition() {
        return repository.findAllOrderedByName();
	}
	
}
