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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * <p>
 * Description d'un article :
 * </p>
 * 
 * @see Produit
 * @see Panier
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="article")
public class Article implements Serializable {

	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Long id;

	/**
	 * Produit associe
	 * @see Produit
	 */
	@ManyToOne
    @JsonManagedReference
	@JoinColumn(name="produit_id")
	private Produit produit;
	
	/**
	 * Quantite dans le panier
	 */
	@NotNull
	private int quotite;
	
	//@NotNull
	//private int prixTotal;
	
	/**
	 * Panier associe
	 * @see Panier
	 */
	@ManyToOne
	@JoinColumn(name="panier_id")
	@JsonManagedReference
	private Panier panier;
	
	/**
	 * default cstor.
	 */
	public Article() {
		super();
	}

	/**
	 * cstor.
	 * @param produit produit
	 * @param quotite quantite
	 */
	public Article(Produit produit, int quotite) {
		super();
		this.produit = produit;
		this.quotite = quotite;
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
	 * @return the produit
	 */
	public Produit getProduit() {
		return produit;
	}

	/**
	 * @param produit the produit to set
	 */
	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	/**
	 * @return the quotite
	 */
	public int getQuotite() {
		return quotite;
	}

	/**
	 * @param quotite the quotite to set
	 */
	public void setQuotite(int quotite) {
		this.quotite = quotite;
	}

	/**
	 * @return the panier
	 */
	public Panier getPanier() {
		return panier;
	}

	/**
	 * @param panier the panier to set
	 */
	public void setPanier(Panier panier) {
		this.panier = panier;
	}

}
