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
 * 
 * Classe représentant une catégorie
 * Fruit, Legumes, Cremerie
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="categorierecette")
public class CategorieRecette implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorierecette_id")
	private Long id ;
	
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "categorierecette_name")
	private String name;
	
	@JsonBackReference
	@OneToMany(mappedBy="categorieRecette", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Recette> recette;
	
	public CategorieRecette() {
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<Recette> getRecette() {
		return recette;
	}


	public void setRecette(Set<Recette> recette) {
		this.recette = recette;
	}
	
	
	
	
}
