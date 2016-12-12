package fr.ecombio.model;

import java.io.Serializable;
import java.util.Set;

import javax.jdo.annotations.Index;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * 
 * Classe representant un produit
 * 
 * @see Categorie
 * @see ProduitSaison
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "produit")
public class Produit implements Serializable {
	
	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id")
	private Long id;

	/**
	 * categorie
	 * @see Categorie
	 */
	@ManyToOne
	@JoinColumn(name="categorie_id")
    @JsonManagedReference
	@Index(name = "cat")
	private Categorie categorie;
	
	/**
	 * Association de saisons
	 * @see ProduitSaison
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL ,mappedBy="produits")
	@JsonBackReference
    private Set<ProduitSaison> saisons;
	
	/**
	 * default cstor.
	 */
	public Produit() {
		super();
	}

	/**
	 * cstor.
	 * @param categorie
	 * @param name
	 * @param variete
	 * @param quantite
	 * @param stock
	 * @param prix
	 * @param filename
	 * @param provenance
	 * @param dateCueillette
	 */
	public Produit(Categorie categorie, String name, String variete, int quantite, int stock, float prix,
			String filename, String provenance, String dateCueillette) {
		super();
		this.categorie = categorie;
		this.name = name;
		this.variete = variete;
		this.quantite = quantite;
		this.stock = stock;
		this.prix = prix;
		this.filename = filename;
		this.provenance = provenance;
		this.dateCueillette = dateCueillette;
	}

	/**
	 * @param categorie
	 * @param saisons
	 * @param name
	 * @param variete
	 * @param unite
	 * @param quantite
	 * @param stock
	 * @param prix
	 * @param filename
	 * @param provenance
	 * @param dateCueillette
	 * @param dureeConservation
	 * @param calories
	 * @param glucides
	 * @param fibres
	 * @param proteines
	 */
	public Produit(Categorie categorie, String name, String variete, String unite,
			int quantite, int stock, float prix, String filename, String provenance, String dateCueillette,
			int dureeConservation, int calories, int glucides, int fibres, int proteines) {
		super();
		this.categorie = categorie;
		this.name = name;
		this.variete = variete;
		this.unite = unite;
		this.quantite = quantite;
		this.stock = stock;
		this.prix = prix;
		this.filename = filename;
		this.provenance = provenance;
		this.dateCueillette = dateCueillette;
		this.dureeConservation = dureeConservation;
		this.calories = calories;
		this.glucides = glucides;
		this.fibres = fibres;
		this.proteines = proteines;
	}

	/**
	 * nom
	 */
	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "produit_name")
	private String name;

	/**
	 * variete
	 */
	@NotNull
	@Size(min = 1, max = 25)
    @Column(name = "produit_variete")
	private String variete;
	
	/**
	 * unite de stockage
	 */
	@NotNull
	@Size(min = 1, max = 25)
    @Column(name = "produit_unite")
	private String unite;
	
	/**
	 * quantite d'un stock
	 */
	@NotNull
    @Column(name = "produit_quantite")
	private int quantite;

	/**
	 * stock
	 */
    @Column(name = "produit_stock")
	private int stock;

    /**
     * prix
     */
	@NotNull
    @Column(name = "produit_prix")
	private float prix;
	
	/**
	 * chemin de l'image
	 */
    @Column(name = "produit_filename")
	private String filename;

	/**
	 * provenance du produit
	 */
	@NotNull
	@Size(min = 1, max = 100)
    @Column(name = "produit_provenance")
	private String provenance;

	/**
	 * date de la cueillette
	 */
	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)", message = "Must be dd/MM/yyyy")
    @Column(name = "produit_dateCueillette")
	private String dateCueillette;

	/**
	 * nombre de jour que le produit peut etre conserve
	 */
	@NotNull
    @Column(name = "produit_dureeConservation")
	private int dureeConservation;

	/**
	 * teneur en calories
	 */
	@NotNull
	@Column(name = "produit_calories")
	private int calories;
	
	/**
	 * teneur en glucide
	 */
	@NotNull
	@Column(name = "produit_glucides")
	private int glucides;
	
	/**
	 * teneur en fibre
	 */
	@NotNull
	@Column(name = "produit_fibres")
	private int fibres;
	
	/**
	 * teneur en proteine
	 */
	@NotNull
	@Column(name = "produit_proteines")
	private int proteines;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the saisons
	 */
	public Set<ProduitSaison> getSaisons() {
		return saisons;
	}

	/**
	 * @param saisons the saisons to set
	 */
	public void setSaisons(Set<ProduitSaison> saisons) {
		this.saisons = saisons;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the variete
	 */
	public String getVariete() {
		return variete;
	}

	/**
	 * @param variete the variete to set
	 */
	public void setVariete(String variete) {
		this.variete = variete;
	}

	/**
	 * @return the unite
	 */
	public String getUnite() {
		return unite;
	}

	/**
	 * @param unite the unite to set
	 */
	public void setUnite(String unite) {
		this.unite = unite;
	}

	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the prix
	 */
	public float getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(float prix) {
		this.prix = prix;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the provenance
	 */
	public String getProvenance() {
		return provenance;
	}

	/**
	 * @param provenance the provenance to set
	 */
	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	/**
	 * @return the dateCueillette
	 */
	public String getDateCueillette() {
		return dateCueillette;
	}

	/**
	 * @param dateCueillette the dateCueillette to set
	 */
	public void setDateCueillette(String dateCueillette) {
		this.dateCueillette = dateCueillette;
	}

	/**
	 * @return the dureeConservation
	 */
	public int getDureeConservation() {
		return dureeConservation;
	}

	/**
	 * @param dureeConservation the dureeConservation to set
	 */
	public void setDureeConservation(int dureeConservation) {
		this.dureeConservation = dureeConservation;
	}

	/**
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * @param calories the calories to set
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}

	/**
	 * @return the glucides
	 */
	public int getGlucides() {
		return glucides;
	}

	/**
	 * @param glucides the glucides to set
	 */
	public void setGlucides(int glucides) {
		this.glucides = glucides;
	}

	/**
	 * @return the fibres
	 */
	public int getFibres() {
		return fibres;
	}

	/**
	 * @param fibres the fibres to set
	 */
	public void setFibres(int fibres) {
		this.fibres = fibres;
	}

	/**
	 * @return the proteines
	 */
	public int getProteines() {
		return proteines;
	}

	/**
	 * @param proteines the proteines to set
	 */
	public void setProteines(int proteines) {
		this.proteines = proteines;
	}

	/**
	 * Mise a jour d'un produit
	 * @param p nouveau produit
	 * 
	 * @see GestionProduit
	 */
	public void modifyProduit(GestionProduit p) {
		if (this.calories != p.getCalories()) {
			this.calories = p.getCalories();
		}
		if (!this.dateCueillette.equals(this.getDateCueillette())) {
			this.dateCueillette = this.getDateCueillette();
		}
		if (this.dureeConservation != p.getDureeConservation()) {
			this.dureeConservation = p.getDureeConservation();
		}
		if (this.fibres != p.getFibres()) {
			this.fibres = p.getFibres();
		}
		if (!this.filename.equals(p.getFilename())) {
			this.filename = p.getFilename();
		}
		if (this.glucides != p.getGlucides()) {
			this.glucides = p.getGlucides();
		}
		if (!this.name.equals(p.getName())) {
			this.name = p.getName();
		}
		if (this.prix != p.getPrix()) {
			this.prix = p.getPrix();
		}
		if (this.proteines != p.getProteines()) {
			this.proteines = p.getProteines();
		}
		if (!this.provenance.equals(p.getProvenance())) {
			this.provenance = p.getProvenance();
		}
		if (this.quantite != p.getQuantite()) {
			this.quantite = p.getQuantite();
		}
		if (this.stock != p.getStock()) {
			this.stock = p.getStock();
		}
		if (!this.unite.equals(p.getUnite())) {
			this.unite = p.getUnite();
		}
		if (!this.variete.equals(p.getVariete())) {
			this.variete = p.getVariete();
		}
	}

}