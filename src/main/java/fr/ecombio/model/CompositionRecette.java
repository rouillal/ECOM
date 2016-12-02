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
@Table(name = "compositionrecette")
public class CompositionRecette implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="compositionrecette_id")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="recette_id")
	@JsonManagedReference
	private Produit recettes;
	
	@ManyToOne
	@JoinColumn(name="composition_id")
	@JsonManagedReference
	private Saison composition;


	public Produit getRecettes() {
		return recettes;
	}

	public void setRecettes(Produit recettes) {
		this.recettes = recettes;
	}

	public Saison getComposition() {
		return composition;
	}

	public void setComposition(Saison composition) {
		this.composition = composition;
	}


}
