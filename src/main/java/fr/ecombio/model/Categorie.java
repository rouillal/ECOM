package fr.ecombio.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Classe representant une categorie
 * Fruit, Legumes, Cremerie
 * 
 * @see Produit
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="categorie")
public class Categorie implements Serializable {


	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorie_id")
	private Long id ;
	
	/**
	 * nom
	 */
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "categorie_name")
	private String name;
	
	/**
	 * liste de produits associés
	 * @see Produit
	 */
	@JsonBackReference
	@OneToMany(mappedBy="categorie", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Produit> produits;
	
	/**
	 * default cstor.
	 */
	public Categorie() {
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categorie(String name, Set<Produit> produits) {
		super();
		this.name = name;
		this.produits = produits;
	}


	public Categorie(String name) {
		super();
		this.name = name;
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

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}
	
	
}
