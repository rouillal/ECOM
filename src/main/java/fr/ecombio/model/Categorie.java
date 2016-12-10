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


	/**
	 * @param name
	 * @param produits
	 */
	public Categorie(String name, Set<Produit> produits) {
		super();
		this.name = name;
		this.produits = produits;
	}


	/**
	 * @param name
	 */
	public Categorie(String name) {
		super();
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the produits
	 */
	public Set<Produit> getProduits() {
		return produits;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

}
