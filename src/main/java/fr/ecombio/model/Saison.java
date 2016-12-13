package fr.ecombio.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * <p>
 * Description d'une saison :
 * </p>
 * 
 * @see ProduitSaison
 * @see RecetteSaison
 *
 */
@SuppressWarnings("serial")
@Table(name="saison")
@Entity
public class Saison implements Serializable {

	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saison_id")
	private Long id ;

	/**
	 * nom
	 */
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "saison_name")
	private String name;
	
	/**
	 * produits associés
	 * @see ProduitSaison
	 */
	@OneToMany(mappedBy="saisons", fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JsonBackReference
	private Set<ProduitSaison> produits;
	
	/**
	 * recettes associees
	 * @see RecetteSaison
	 */
	@OneToMany(mappedBy="saisons", fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JsonBackReference
	private Set<RecetteSaison> recettes;
	
	/**
	 * default cstor.
	 */
	public Saison() {
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
	public Set<ProduitSaison> getProduits() {
		return produits;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(Set<ProduitSaison> produits) {
		this.produits = produits;
	}

	/**
	 * @return the recettes
	 */
	public Set<RecetteSaison> getRecettes() {
		return recettes;
	}

	/**
	 * @param recettes the recettes to set
	 */
	public void setRecettes(Set<RecetteSaison> recettes) {
		this.recettes = recettes;
	}
	

	
}
