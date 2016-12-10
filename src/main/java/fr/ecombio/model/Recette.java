package fr.ecombio.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * <p>
 * Description d'une recette :
 * </p>
 * 
 * @see RecetteProduit
 * @see RecetteSaison
 * @see CompositionRecette
 * @see CategorieRecette
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "recette")
public class Recette implements Serializable {
	
	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recette_id")
	private Long id;
	
	/**
	 * nom
	 */
	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "recette_name")
	private String name;
	
	/**
	 * nombre de personne
	 */
	@NotNull
    @Column(name = "recette_personne")
	private int quantite;

	/**
	 * temps de preparation
	 */
	@NotNull
	@Column(name="recette_tpsprepa")
	private int tpsPreparation;
	
	/**
	 * temps de cuisson
	 */
	@NotNull
	@Column(name="recette_tpscuisson")
	private int tpsCuisson;
	
	/**
	 * cout de la recette
	 */
	@NotNull
	@Column(name="recette_cout")
	private int cout;
	
	/**
	 * difficulte de la recette
	 */
	@NotNull
	@Column(name="recette_difficulte")
	private int difficulte;
	
	/**
	 * liste des ingredients
	 */
	@NotNull
	@Column(name="recette_listeingredients")
	private String listeIngredients;
	
	/**
	 * description de la preparation
	 */
	@NotNull
	@Column(name="recette_preparation")
	private String preparation;
	
	/**
	 * liste des produits contenus dans la recette
	 * @see RecetteProduit
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="recettes")
	@JsonBackReference
    private Set<RecetteProduit> produits;
	
	/**
	 * liste des saisons associees a la recette
	 * @see RecetteSaison
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="recettes")
	@JsonBackReference
    private Set<RecetteSaison> saisons;
	
	/**
	 * liste des regimes associes a la recette
	 * @see CompositionRecette
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="recettes")
	@JsonBackReference
    private Set<CompositionRecette> compositions;
	
	/**
	 * liste des categorie de la recette
	 * @see CategorieRecette
	 */
	@ManyToOne
	@JoinColumn(name="categorierecette_id")
    @JsonManagedReference
	private CategorieRecette categorieRecette;
	
	@Column(name = "recette_filename")
	private String filename;
	
	public Recette() {
		super();
		this.compositions = new HashSet<CompositionRecette>();
	}

	public Recette(String name, int quantite, int tpsPreparation, int tpsCuisson, int cout, int difficulte,
			String listeIngredients, String preparation) {
		super();
		this.name = name;
		this.quantite = quantite;
		this.tpsPreparation = tpsPreparation;
		this.tpsCuisson = tpsCuisson;
		this.cout = cout;
		this.difficulte = difficulte;
		this.listeIngredients = listeIngredients;
		this.preparation = preparation;
	}

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
	 * @return the tpsPreparation
	 */
	public int getTpsPreparation() {
		return tpsPreparation;
	}

	/**
	 * @param tpsPreparation the tpsPreparation to set
	 */
	public void setTpsPreparation(int tpsPreparation) {
		this.tpsPreparation = tpsPreparation;
	}

	/**
	 * @return the tpsCuisson
	 */
	public int getTpsCuisson() {
		return tpsCuisson;
	}

	/**
	 * @param tpsCuisson the tpsCuisson to set
	 */
	public void setTpsCuisson(int tpsCuisson) {
		this.tpsCuisson = tpsCuisson;
	}

	/**
	 * @return the cout
	 */
	public int getCout() {
		return cout;
	}

	/**
	 * @param cout the cout to set
	 */
	public void setCout(int cout) {
		this.cout = cout;
	}

	/**
	 * @return the difficulte
	 */
	public int getDifficulte() {
		return difficulte;
	}

	/**
	 * @param difficulte the difficulte to set
	 */
	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	/**
	 * @return the listeIngredients
	 */
	public String getListeIngredients() {
		return listeIngredients;
	}

	/**
	 * @param listeIngredients the listeIngredients to set
	 */
	public void setListeIngredients(String listeIngredients) {
		this.listeIngredients = listeIngredients;
	}

	/**
	 * @return the preparation
	 */
	public String getPreparation() {
		return preparation;
	}

	/**
	 * @param preparation the preparation to set
	 */
	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	/**
	 * @return the produits
	 */
	public Set<RecetteProduit> getProduits() {
		return produits;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(Set<RecetteProduit> produits) {
		this.produits = produits;
	}

	/**
	 * @return the saisons
	 */
	public Set<RecetteSaison> getSaisons() {
		return saisons;
	}

	/**
	 * @param saisons the saisons to set
	 */
	public void setSaisons(Set<RecetteSaison> saisons) {
		this.saisons = saisons;
	}

	/**
	 * @return the compositions
	 */
	public Set<CompositionRecette> getCompositions() {
		return compositions;
	}

	/**
	 * @param compositions the compositions to set
	 */
	public void setCompositions(Set<CompositionRecette> compositions) {
		this.compositions = compositions;
	}

	/**
	 * @return the categorieRecette
	 */
	public CategorieRecette getCategorieRecette() {
		return categorieRecette;
	}

	/**
	 * @param categorieRecette the categorieRecette to set
	 */
	public void setCategorieRecette(CategorieRecette categorieRecette) {
		this.categorieRecette = categorieRecette;
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

}