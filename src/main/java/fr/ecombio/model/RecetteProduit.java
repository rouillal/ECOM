package fr.ecombio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * <p>
 * Association d'une recette a un produit :
 * </p>
 * 
 * @see Produit
 * @see Recette
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "recetteproduit")
public class RecetteProduit implements Serializable{

	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="recetteproduit_id")
	private Long Id;
	
	/**
	 * produit associe
	 * @see Produit
	 */
	@ManyToOne
	@JoinColumn(name="produit_id")
	@JsonManagedReference
	private Produit produits;
	
	/**
	 * recette associe
	 * @see Recette
	 */
	@ManyToOne
	@JoinColumn(name="recette_id")
	@JsonManagedReference
	private Recette recettes;
	
	/**
	 * non utilise pour le moment
	 * quantite du produit dans la recette
	 */
	@NotNull
	@Column(name="recetteproduit_quantite")
	private int quantite;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Produit getArticles() {
		return produits;
	}

	public void setArticles(Produit produits) {
		this.produits = produits;
	}

	public Recette getRecettes() {
		return recettes;
	}

	public void setRecettes(Recette recettes) {
		this.recettes = recettes;
	}

	public Produit getProduits() {
		return produits;
	}

	public void setProduits(Produit produits) {
		this.produits = produits;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	

}
