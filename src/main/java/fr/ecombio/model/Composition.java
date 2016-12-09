package fr.ecombio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.Action;
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
	

	public Set<CompositionRecette> getRecettes() {
		return recettes;
	}

	public void setRecettes(Set<CompositionRecette> recettes) {
		this.recettes = recettes;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
