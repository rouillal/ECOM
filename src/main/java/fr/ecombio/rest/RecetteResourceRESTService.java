package fr.ecombio.rest;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.RecetteRepository;
import fr.ecombio.model.Client;
import fr.ecombio.model.Produit;
import fr.ecombio.model.Recette;
import fr.ecombio.model.ValidationClient;


/**
 * <p>
 * Permet un service RESTful read/write pour les recettes
 * 
 * @see Recette
 * @see Produits
 * @see RecetteRepository
 *
 */
@Path("/recette")
@RequestScoped
public class RecetteResourceRESTService {

	/**
	 * @see RecetteRepository
	 */
	@Inject
    private RecetteRepository repository;
	
	/**
	 * Ajout d'une recette en base
	 * @param recette recette
	 * 
	 * @see Recette
	 * @see RecetteRepository#AjoutRecette(Recette)
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void AjoutRecette(Recette recette){
		repository.AjoutRecette(recette);
	}
	
	/**
	 * Recherche des recettes selon une selection
	 * @param page page courante pour la pagination
	 * @param tri selection plus precise
	 * @param saison selection par saison
	 * @return liste de recettes
	 * 
	 * @see Recette
	 * @see RecetteRepository#findAllOrderedByName(int, String, String)
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recette> listAllRecette(@DefaultValue("0") @QueryParam("page") int page, @QueryParam("tri") String tri, @QueryParam("saison") String saison) {
        return repository.findAllOrderedByName(page, tri, saison);
    }

	/**
	 * Recherche des recettes selon une selection
	 * @param page page courante pour la pagination
	 * @param cat recherche par type de recette
	 * @param saison selection par saison
	 * @param search recherche par mot clef
	 * @param compo recherche par type de regime
	 * @param tri selection plus precise
	 * @return liste de recettes
	 * 
	 * @see Recette
	 * @see RecetteRepository#findAllOrderedByName(int, String, String, String, String, String)
	 */
	@GET
	@Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recette> listAllRecette(@DefaultValue("0") @QueryParam("page") int page, @QueryParam("cat") String cat, @QueryParam("saison") String saison, @QueryParam("search") String search, @QueryParam("compo") String compo, @QueryParam("tri") String tri) {
        return repository.findAllOrderedByName(page, cat, saison, search, compo, tri);
    }
	
	/**
	 * Recherche de produit d'une recette
	 * @param id identifiant de la recette
	 * @return liste de produits
	 * 
	 * @see Produits
	 * @see RecetteRepository#findAllProduitsFromId(int)
	 */
	@GET
	@Path("/produits")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produit> listAllProduitFromRecette(@QueryParam("id") int id) {
		return repository.findAllProduitsFromId(id);
    }
	
	/**
	 * Recherche de recette par rapport aux produits d'un panier
	 * @param id identifiant du panier
	 * @return liste de recettes
	 * 
	 * @see Recette
	 * @see RecetteRepository#findAllRecetteFromPanier(Long)
	 */
	@GET
	@Path("/panier")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recette> listAllRecetteFromPanier(@QueryParam("id") Long id) {
		return repository.findAllRecetteFromPanier(id);
    }

	/**
	 * Recherche du nombre de page selon une selection
	 * @param cat recherche par type de recette
	 * @param saison selection par saison
	 * @param search recherche par mot clef
	 * @param compo recherche par type de regime
	 * @param tri selection plus precise
	 * @return nombre de page
	 * 
	 * @see RecetteRepository#findNumberPage(String, String, String, String)
	 */
	@GET
	@Path("/page/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Long NumberPage(@QueryParam("cat") String cat, @QueryParam("saison") String saison, @QueryParam("search") String search, @QueryParam("compo") String compo, @QueryParam("tri") String tri) {
        return repository.findNumberPage(cat, saison, search, compo);
    }
	
	/**
	 * Recherche du nombre de page selon une selection
	 * @param saison selection par saison
	 * @return nombre de page
	 * 
	 * @see RecetteRepository#findNumberPage(String)
	 */
	@GET
	@Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    public Long NumberPage(@QueryParam("saison") String saison) {
        return repository.findNumberPage(saison);
    }
	
}

