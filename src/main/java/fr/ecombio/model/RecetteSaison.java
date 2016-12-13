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
 * Association d'une recette a une saison :
 * </p>
 * 
 * @see Saison
 * @see Recette
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "recettesaison")
public class RecetteSaison implements Serializable{

	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="recettesaison_id")
	private Long Id;
	
	/**
	 * recette assiociee
	 * @see Recette
	 */
	@ManyToOne
	@JoinColumn(name="recette_id")
	@JsonManagedReference
	private Recette recettes;
	
	/**
	 * saison associee
	 * @see Saison
	 */
	@ManyToOne
	@JoinColumn(name="saison_id")
	@JsonManagedReference
	private Saison saisons;

	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
	}

	/**
	 * @return the recettes
	 */
	public Recette getRecettes() {
		return recettes;
	}

	/**
	 * @param recettes the recettes to set
	 */
	public void setRecettes(Recette recettes) {
		this.recettes = recettes;
	}

	/**
	 * @return the saisons
	 */
	public Saison getSaisons() {
		return saisons;
	}

	/**
	 * @param saisons the saisons to set
	 */
	public void setSaisons(Saison saisons) {
		this.saisons = saisons;
	}

}
