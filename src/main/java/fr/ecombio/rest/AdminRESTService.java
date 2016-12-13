package fr.ecombio.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.ecombio.data.StockManagerRepository;
import fr.ecombio.model.HistoriqueCommande;
import fr.ecombio.data.HistoriqueCommandeRepository;

/**
 * <p>
 * Permet un service RESTful read/write pour l'admin
 * 
 * @see StockManagerRepository
 * @see HistoriqueCommandeRepository
 *
 */
@Path("/admin")
@RequestScoped
public class AdminRESTService {

	/**
	 * @see StockManagerRepository
	 */
	@Inject
	private StockManagerRepository StockManagerRepository;

	/**
	 * @see HistoriqueCommandeRepository
	 */
	@Inject
	private HistoriqueCommandeRepository HistoriqueCommandeRepository;

	private Logger logger = java.util.logging.Logger.getLogger("org.hibernate");
	
	/**
	 * @see StockManagerRepository
	 * @return ok ou erreur
	 * @see StockManagerRepository#incrementeStock(Long)
	 * @see StockManagerRepository#decrementeStock(Long)
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeStockOfProduct(@QueryParam("op") String op, @QueryParam("id") Long id) {
		if (op.equals("+")) {
			StockManagerRepository.incrementeStock(id);
		} else if (op.equals("-")) {
			StockManagerRepository.decrementeStock(id);
		} else {
			return Response.noContent().build();
		}
		return Response.ok().build();
	}

	/**
	 * @see HistoriqueCommandeRepository
	 * @param date jour que l'on souhaite voir
	 * @return commandes du jour
	 * @see HistoriqueCommandeRepository#getHistoFromDate(String)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/commande")
	public List<HistoriqueCommande> getHistoriqueCommandefromDate(@QueryParam("date") String date, @QueryParam("ent") int ent, @QueryParam("dom") int dom, @QueryParam("page") int page) {
		boolean e = (ent == 1);
		boolean d = (dom == 1);
		return HistoriqueCommandeRepository.getHistoFromDate(date,e,d,page);
	}
	/**
	 * @see HistoriqueCommandeRepository
	 * @param date jour que l'on souhaite voir
	 * @return commandes du jour
	 * @see HistoriqueCommandeRepository#getHistoFromDate(String)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/commande/page")
	public Long getHistoriqueCommandefromDate(@QueryParam("date") String date, @QueryParam("ent") int ent, @QueryParam("dom") int dom) {
		boolean e = (ent == 1);
		boolean d = (dom == 1);
		return HistoriqueCommandeRepository.getNbPageHisto(date,e,d);
	}

	/**
	 * @see HistoriqueCommandeRepository
	 * @param Commande que l'on souhaite voir
	 * @return commandes du jour
	 * @see HistoriqueCommandeRepository#setCommandeDelivred(String)
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/commande")
	public Response setHistoriqueCommandefromDate(@QueryParam("livree") int livree, @QueryParam("id") Long id) {
		logger.info("livree<"+livree+">id<"+id+">");
		if (livree == 0) {
			HistoriqueCommandeRepository.setCommandeDelivred(false, id);
			logger.info("commande "+id+" pas livrée");
		} else if (livree == 1) {
			HistoriqueCommandeRepository.setCommandeDelivred(true, id);
			logger.info("commande "+id+" livrée");
		} else {
			return Response.noContent().build();
		}
		return Response.ok().build();
	}
}
