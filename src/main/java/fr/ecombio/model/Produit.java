package fr.ecombio.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * 
 * Classe représentant un produit
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "produit")
public class Produit implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="categorie_id")
    @JsonManagedReference
	private Categorie categorie;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="produits")
	@JsonBackReference
    private Set<ProduitSaison> saisons;
	
	public Produit() {
		super();
	}

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

	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "produit_name")
	private String name;

	@NotNull
	@Size(min = 1, max = 25)
    @Column(name = "produit_variete")
	private String variete;
	
	@NotNull
	@Size(min = 1, max = 25)
    @Column(name = "produit_unite")
	private String unite;
	
	@NotNull
    @Column(name = "produit_quantite")
	private int quantite;

    @Column(name = "produit_stock")
	private int stock;

	@NotNull
	//@Pattern(regexp = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$", message = "Must be float")
    @Column(name = "produit_prix")
	private float prix;

	/*@NotNull
    //@Size(min = 1, max = 25)
    //@Pattern(regexp = "[0-9]*", message = "Must contain numbers")
    private ?? image;*/
	
    @Column(name = "produit_filename")
	private String filename;

	// provenance
	@NotNull
	@Size(min = 1, max = 100)
    @Column(name = "produit_provenance")
	private String provenance;

	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)", message = "Must be dd/MM/yyyy")
    @Column(name = "produit_dateCueillette")
	private String dateCueillette;

	//nombre de jours
	@NotNull
    @Column(name = "produit_dureeConservation")
	private int dureeConservation;

	@NotNull
	@Column(name = "produit_calories")
	private int calories;
	
	@NotNull
	@Column(name = "produit_glucides")
	private int glucides;
	
	@NotNull
	@Column(name = "produit_fibres")
	private int fibres;
	
	@NotNull
	@Column(name = "produit_proteines")
	private int proteines;
	
	public String getVariete() {
		return variete;
	}

	public void setVariete(String variete) {
		this.variete = variete;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public String getDateCueillette() {
		return dateCueillette;
	}

	public void setDateCueillette(String dateCueillette) {
		this.dateCueillette = dateCueillette;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}
	
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Set<ProduitSaison> getSaisons() {
		return saisons;
	}

	public void setSaisons(Set<ProduitSaison> saisons) {
		this.saisons = saisons;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getDureeConservation() {
		return dureeConservation;
	}

	public void setDureeConservation(int dureeConservation) {
		this.dureeConservation = dureeConservation;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getGlucides() {
		return glucides;
	}

	public void setGlucides(int glucides) {
		this.glucides = glucides;
	}

	public int getFibres() {
		return fibres;
	}

	public void setFibres(int fibres) {
		this.fibres = fibres;
	}

	public int getProteines() {
		return proteines;
	}

	public void setProteines(int proteines) {
		this.proteines = proteines;
	}


}