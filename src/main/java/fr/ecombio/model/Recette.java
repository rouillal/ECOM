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
 * 
 * Classe représentant une recette
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "recette")
public class Recette implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recette_id")
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "recette_name")
	private String name;
	
	@NotNull
    @Column(name = "recette_personne")
	private int quantite;

	@NotNull
	@Column(name="recette_tpsprepa")
	private int tpsPreparation;
	
	@NotNull
	@Column(name="recette_tpscuisson")
	private int tpsCuisson;
	
	@NotNull
	@Column(name="recette_cout")
	private int cout;
	
	@NotNull
	@Column(name="recette_difficulte")
	private int difficulte;
	
	@NotNull
	@Column(name="recette_listeingredients")
	private String listeIngredients;
	
	@NotNull
	@Column(name="recette_preparation")
	private String preparation;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="recettes")
	@JsonBackReference
    private Set<RecetteProduit> produits;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="recettes")
	@JsonBackReference
    private Set<RecetteSaison> saisons;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="recettes")
	@JsonBackReference
    private Set<CompositionRecette> compositions;
	
	@ManyToOne
	@JoinColumn(name="categorierecette_id")
    @JsonManagedReference
	private CategorieRecette categorieRecette;
	
	@Column(name = "recette_filename")
	private String filename;
	
	

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Set<RecetteSaison> getSaisons() {
		return saisons;
	}

	public void setSaisons(Set<RecetteSaison> saisons) {
		this.saisons = saisons;
	}

	@JsonIgnore
	public Set<CompositionRecette> getComposition() {
		return compositions;
	}

	public void setComposition(Set<CompositionRecette> composition) {
		this.compositions = composition;
	}

	public CategorieRecette getCategorieRecette() {
		return categorieRecette;
	}

	public void setCategorieRecette(CategorieRecette categorieRecette) {
		this.categorieRecette = categorieRecette;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getTpsPreparation() {
		return tpsPreparation;
	}

	public void setTpsPreparation(int tpsPreparation) {
		this.tpsPreparation = tpsPreparation;
	}

	public int getTpsCuisson() {
		return tpsCuisson;
	}

	public void setTpsCuisson(int tpsCuisson) {
		this.tpsCuisson = tpsCuisson;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public int getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	public String getListeIngredients() {
		return listeIngredients;
	}

	public void setListeIngredients(String listeIngredients) {
		this.listeIngredients = listeIngredients;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}


	public Set<RecetteProduit> getProduits() {
		return produits;
	}

	public void setProduits(Set<RecetteProduit> produits) {
		this.produits = produits;
	}

	public Set<CompositionRecette> getCompositions() {
		return compositions;
	}

	public void setCompositions(Set<CompositionRecette> compositions) {
		this.compositions = compositions;
	}
	
	

	
}