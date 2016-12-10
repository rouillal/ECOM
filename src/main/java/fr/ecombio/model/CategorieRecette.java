package fr.ecombio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.*;


/**
 * <p>
 * Description d'une categorie de recette :
 * <ul>
 * <li>plat</li>
 * <li>boisson</li>
 * <li>entree</li>
 * <li>dessert</li>
 * </ul>
 * </p>
 * 
 * @see Recette
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="categorierecette")
public class CategorieRecette implements Serializable {


	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorierecette_id")
	private Long id ;
	
	/**
	 * nom
	 */
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "categorierecette_name")
	private String name;
	
	/**
	 * liste de recettes
	 * @see Recette
	 */
	@JsonBackReference
	@OneToMany(mappedBy="categorieRecette", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Recette> recette;
	
	/**
	 * default ctsor.
	 */
	public CategorieRecette() {
	}

	public CategorieRecette(String name, Set<Recette> recette) {
		super();
		this.name = name;
		this.recette = recette;
	}


	public CategorieRecette(String name) {
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
	 * @return the recette
	 */
	public Set<Recette> getRecette() {
		return recette;
	}

	/**
	 * @param recette the recette to set
	 */
	public void setRecette(Set<Recette> recette) {
		this.recette = recette;
	}

}
