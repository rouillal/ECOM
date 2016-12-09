package fr.ecombio.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.ws.ResponseWrapper;

import fr.ecombio.data.CompositionRepository;
import fr.ecombio.model.Client;
import fr.ecombio.model.Composition;
import fr.ecombio.model.ValidationClient;

/**
 * <p>
 * Permet un service RESTful read/write pour la connection
 * 
 * @see Client
 * @see RegistreRepository
 * @see ValidationClient
 *
 */
@Path("/connect")
@RequestScoped
public class ConnectResourceRESTService {

	Logger logger = java.util.logging.Logger.getLogger("org.hibernate");

	/**
	 * @see RegistreRepository
	 */
	@Inject
	private fr.ecombio.data.RegistreRepository RegistreRepository;

	/**
	 * authentification du client
	 * @param mail login
	 * @param psw mot de passe
	 * @return Client si authentification ok erreur sinon
	 * @throws Exception
	 * 
	 * @see Client
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public Response registerClient( @QueryParam("mail") String mail, @QueryParam("psw") String psw) throws Exception {
		Client client = RegistreRepository.findClientByMailAndMdp(mail, psw);
		if (client == null) {
			logger.log(Level.INFO,"client <"+mail+"> non inscrit");
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(client).build();
	}

	/**
	 * Enregistrement du client en base
	 * @param client infos sur le client
	 * @return le client si il n'existait pas déjà, erreur sinon
	 * 
	 * @see ValidationClient
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerClient(ValidationClient client) {
		try {
			RegistreRepository.registerClient(client);
			//return "ok";
			return Response.ok(client).build();
		} catch (Exception e) {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

}
