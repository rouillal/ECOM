package fr.ecombio.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;
import javax.ws.rs.core.Response.Status;
import javax.xml.ws.ResponseWrapper;

import fr.ecombio.data.RegistreRepository;
import fr.ecombio.model.Article;
import fr.ecombio.model.GestionArticle;
import fr.ecombio.model.Panier;
import fr.ecombio.model.Produit;
import fr.ecombio.model.ValidationCommande;
import fr.ecombio.model.ValidationPaiement;

@Path("/paiement")
@RequestScoped
public class PaiementRESTService {
	
	Logger log = java.util.logging.Logger.getLogger("org.hibernate");
	
	@Inject
	private RegistreRepository RegistreRepository;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public Response validationPaiement(ValidationCommande infos) throws Exception {
		String err = infos.getCommandPaieInfo().verify();
		if (err!=null || (err!=null && !err.isEmpty())) {
			return Response.ok(err).build();
			//throw new Exception(err);
		} else {
			// enregistrer le client et l'historique de la commande
			RegistreRepository.registerCommande(infos);
			return Response.ok("ok").build();
		}
	}
}
