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
 * Description d'une composition (regime) :
 * </p>
 * 
 * @see CompositionRecette
 *
 */
@SuppressWarnings("serial")
@Table(name="composition")
@Entity
public class Composition implements Serializable {

	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "composition_id")
	private Long id ;

	/**
	 * nom
	 */
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "composition_name")
	private String name;
	
	/**
	 * recette associee
	 * @see CompositionRecette
	 */
	@OneToMany(mappedBy="compositions", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonBackReference
	private Set<CompositionRecette> recettes;
	
	/**
	 * default cstor.
	 */
	public Composition() {
		super();
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
	 * @return the recettes
	 */
	public Set<CompositionRecette> getRecettes() {
		return recettes;
	}

	/**
	 * @param recettes the recettes to set
	 */
	public void setRecettes(Set<CompositionRecette> recettes) {
		this.recettes = recettes;
	}
	
}
