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

import fr.ecombio.model.Client;
import fr.ecombio.model.ValidationClient;

@Path("/connect")
@RequestScoped
public class ConnectRessourceRESTService {

	Logger logger = java.util.logging.Logger.getLogger("org.hibernate");

	@Inject
	private fr.ecombio.data.RegistreRepository RegistreRepository;

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

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String registerClient(ValidationClient client) {
		try {
			RegistreRepository.registerClient(client);
			return "ok";
		} catch (Exception e) {
			return "ce profil existe déjà";
		}
	}

}
