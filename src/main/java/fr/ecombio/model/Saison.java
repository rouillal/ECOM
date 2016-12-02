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

@SuppressWarnings("serial")
@Table(name="saison")
@Entity
public class Saison implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saison_id")
	private Long id ;

	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Column(name = "saison_name")
	private String name;
	
	@OneToMany(mappedBy="saisons", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonBackReference
	private Set<ProduitSaison> produits;
	
	public Saison() {
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProduitSaison> getProduits() {
		return produits;
	}

	public void setProduits(Set<ProduitSaison> produits) {
		this.produits = produits;
	}
}
