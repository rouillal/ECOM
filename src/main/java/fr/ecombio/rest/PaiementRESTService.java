package fr.ecombio.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;
import javax.xml.ws.ResponseWrapper;

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

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseWrapper public void validationPaiement(ValidationCommande infos) throws Exception {
		String err = infos.getCommandPaieInfo().verify();
		//log.log(Level.INFO, err);
		if (err!=null || (err!=null && !err.isEmpty())) {
			throw new Exception(err);
		} else {
			// enregistrer le client et l'historique de la commande
		}
	}
}
