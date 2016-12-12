package fr.ecombio.rest;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.ws.ResponseWrapper;

import fr.ecombio.data.RegistreRepository;
import fr.ecombio.data.PanierRepository;
import fr.ecombio.model.SendEmail;
import fr.ecombio.model.ValidationCommande;

/**
 * <p>
 * Permet un service RESTful read/write pour le paiement
 * 
 * @see ValidationCommande
 * @see RegistreRepository
 * @see PanierRepository
 * @see SendEmail
 *
 */
@Path("/paiement")
@RequestScoped
public class PaiementRESTService {
	
	Logger log = java.util.logging.Logger.getLogger("org.hibernate");
	
	/**
	 * @see RegistreRepository
	 */
	@Inject
	private RegistreRepository RegistreRepository;	
	
	/**
	 * @see PanierRepository
	 */
	@Inject
	private PanierRepository PanierRepository;
	
	/**
	 * Verification des donnees de paiement
	 * @param infos informations de paiement
	 * @returnok ou erreur
	 * @throws Exception
	 * 
	 * @see ValidationCommande
	 * @see SendEmail
	 * @see SendEmail#send(ValidationCommande)
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public Response validationPaiement(ValidationCommande infos) throws Exception {
		String err = infos.getCommandPaieInfo().verify();
		if (err!=null || (err!=null && !err.isEmpty())) {
			return Response.status(Status.FORBIDDEN).build();
			//throw new Exception(err);
		} else {
			Logger.getGlobal().info("send mail");
			// envoie d'un mail recap
			SendEmail sendMail = new SendEmail(PanierRepository);
			sendMail.send(infos);
			// enregistrer le client et l'historique de la commande
			RegistreRepository.registerCommande(infos);
			return Response.ok(infos).build();
		}
	}
}
