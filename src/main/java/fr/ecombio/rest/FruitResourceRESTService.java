package fr.ecombio.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.FruitRepository;
import fr.ecombio.model.Fruit;


/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the Fruits table.
 */
@Path("/fruit")
@RequestScoped
public class FruitResourceRESTService {

	@Inject
    private FruitRepository repository;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> listAllFruits() {
        return repository.findAllOrderedByName();
    }
}
