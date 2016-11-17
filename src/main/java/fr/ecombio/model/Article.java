package fr.ecombio.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Table(name="article")
@Entity
public class Article implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Long id ;

	@OneToOne (cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Produit produit;
	
	@NotNull
	private int quotite;
	
	@NotNull
	private int prixTotal;
	
	@ManyToOne
	@JoinColumn(name="panier_id")
	@JsonManagedReference
	private Panier panier;
	
	public Article() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuotite() {
		return quotite;
	}

	public void setQuotite(int quotite) {
		this.quotite = quotite;
	}	

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

}
