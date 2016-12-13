package fr.ecombio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}

	/**
	 * @return the produits
	 */
	public Produit getProduits() {
		return produits;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(Produit produits) {
		this.produits = produits;
	}

	/**
	 * @return the recettes
	 */
	public Recette getRecettes() {
		return recettes;
	}

	/**
	 * @param recettes the recettes to set
	 */
	public void setRecettes(Recette recettes) {
		this.recettes = recettes;
	}

}
