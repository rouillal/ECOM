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
@Table(name = "recetteproduit")
public class RecetteProduit implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="recetteproduit_id")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="produit_id")
	@JsonManagedReference
	private Produit produits;
	
	@ManyToOne
	@JoinColumn(name="recette_id")
	@JsonManagedReference
	private Recette recettes;

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
	
	

}
