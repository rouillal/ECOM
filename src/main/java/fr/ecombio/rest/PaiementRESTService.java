package fr.ecombio.rest;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.ResponseWrapper;

import fr.ecombio.data.RegistreRepository;
import fr.ecombio.model.ValidationCommande;

@Path("/paiement")
@RequestScoped
public class PaiementRESTService {
	
	Logger log = java.util.logging.Logger.getLogger("org.hibernate");
	
	@Inject
	private RegistreRepository RegistreRepository;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public String validationPaiement(ValidationCommande infos) throws Exception {
		String err = infos.getCommandPaieInfo().verify();
		if (err!=null || (err!=null && !err.isEmpty())) {
			return err;
			//throw new Exception(err);
		} else {
			// enregistrer le client et l'historique de la commande
			RegistreRepository.registerCommande(infos);
			return "OK";
		}
	}
}
