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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Entity
@Table(name = "produitsaison")
public class ProduitSaison implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="produitsaison_id")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="produit_id")
	@JsonManagedReference
	private Produit produits;
	
	@ManyToOne
	@JoinColumn(name="saison_id")
	@JsonManagedReference
	private Saison saisons;

	public Produit getProduits() {
		return produits;
	}

	public void setProduits(Produit produits) {
		this.produits = produits;
	}

	public Saison getSaisons() {
		return saisons;
	}

	public void setSaisons(Saison saisons) {
		this.saisons = saisons;
	}
}
