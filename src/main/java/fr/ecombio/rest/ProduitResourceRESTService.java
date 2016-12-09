package fr.ecombio.rest;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.ecombio.data.CategorieRecetteRepository;
import fr.ecombio.data.ProduitRepository;
import fr.ecombio.model.CategorieRecette;
import fr.ecombio.model.Produit;


/**
 * <p>
 * Permet un service RESTful read/write pour les produits 
 * 
 * @see ProduitRepository
 * @see Produit
 *
 */
@Path("/produit")
@RequestScoped
public class ProduitResourceRESTService {

	/**
	 * @see ProduitRepository
	 */
	@Inject
    private ProduitRepository repository;

	/**
	 * Recherche des produits selon une selection
	 * @param page page courante pour la pagination
	 * @param tri selection plus precise
	 * @param saison selection par saison
	 * @return liste de produits
	 * 
	 * @see Produit
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produit> listAllProduits(@DefaultValue("0") @QueryParam("page") int page, @QueryParam("tri") String tri, @QueryParam("saison") int saison) {
        return repository.findAllOrderedByName(page, tri, saison);
    }
	
	/**
	 * Ajout d'un produit
	 * @param prod produit a ajouter
	 * 
	 * @see Produit
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void AjoutProduit(Produit prod){
		repository.AjoutProduit(prod);
	}
	
	/**
	 * Recherche des produits selon une selection
	 * @param cat categorie du produit
	 * @param search recherche par mot clef
	 * @param saison selection par saison
	 * @param page page courante pour la pagination
	 * @param tri selection plus precise
	 * @return liste de produits
	 * 
	 * @see Produit
	 */
	@GET
	@Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produit> listAllProduits(@QueryParam("cat") String cat, @QueryParam("search") String search, @QueryParam("saison") int saison,
    		@DefaultValue("0") @QueryParam("page") int page, @QueryParam("tri") String tri) {
        return repository.findCatOrderedByName(cat, search, page, tri, saison);
    }
	
	/**
	 * Recherche du nombre de page selon une selection
	 * @param cat categorie du produit
	 * @param search recherche par mot clef
	 * @param saison selection par saison
	 * @return nombre de pages
	 */
	@GET
	@Path("/page/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Long NumberPage(@QueryParam("cat") String cat, @QueryParam("search") String search, @QueryParam("saison") int saison) {
        return repository.findNumberPage(cat, search, saison);
    }
	
	/**
	 * Recherche du nombre de page selon une selection
	 * @param saison selection par saison
	 * @return nombre de pages
	 */
	@GET
	@Path("/page")
    @Produces(MediaType.APPLICATION_JSON)
    public Long NumberPage(@QueryParam("saison") int saison) {
        return repository.findNumberPage(saison);
    }
}

