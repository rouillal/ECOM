package fr.ecombio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * <p>
 * Association d'une composition a une recette :
 * </p>
 * 
 * @see Recette
 * @see Composition
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "compositionrecette")
public class CompositionRecette implements Serializable{

	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="compositionrecette_id")
	private Long Id;
	
	/**
	 * recette associee
	 * @see Recette
	 */
	@ManyToOne
	@JoinColumn(name="recette_id")
    @JsonManagedReference
	private Recette recettes;
	
	/**
	 * composition associee
	 * @see Composition
	 */
	@ManyToOne
	@JoinColumn(name="composition_id")
    @JsonManagedReference
	private Composition compositions;


	public Recette getRecettes() {
		return recettes;
	}

	public void setRecettes(Recette recettes) {
		this.recettes = recettes;
	}

	public Composition getCompositions() {
		return compositions;
	}

	public void setCompositions(Composition compositions) {
		this.compositions = compositions;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	

	
}
