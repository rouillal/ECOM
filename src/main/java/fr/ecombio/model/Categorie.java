package fr.ecombio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.*;

@SuppressWarnings("serial")
@Entity
@Table(name="categorie")
public class Categorie implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorie_id")
	private Long id ;
	
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "categorie_name")
	private String name;
	
	@JsonBackReference
	@OneToMany(mappedBy="categorie", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<Produit> produits;
	
	public Categorie() {
		this.produits = new ArrayList<Produit>();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Categorie(String name) {
		super();
		this.name = name;
		this.produits = new ArrayList<Produit>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Produit> getProduits() {
		return produits;
	}

	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}
	
	
}
