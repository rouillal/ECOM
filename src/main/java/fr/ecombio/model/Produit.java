package fr.ecombio.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/*
 * unité de mesure
 * durée de conservation
 * calories
 */

@SuppressWarnings("serial")
@Entity
public class Produit implements Serializable {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name="Categorie_id")
	private Categorie categorie;
	
	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String name;

	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String variete;


	@NotNull
	@Size(min = 1, max = 25)
	//@Pattern(regexp = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$", message = "Must be float")
	private float prix;

	/*@NotNull
    //@Size(min = 1, max = 25)
    //@Pattern(regexp = "[0-9]*", message = "Must contain numbers")
    private ?? image;*/

	// provenance
	@NotNull
	@Size(min = 1, max = 100)
	private String provenance;

	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)", message = "Must be dd/MM/yyyy")
	private String dateCueillette;


	public String getVariete() {
		return variete;
	}

	public void setVariete(String variete) {
		this.variete = variete;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public String getDateCueillette() {
		return dateCueillette;
	}

	public void setDateCueillette(String dateCueillette) {
		this.dateCueillette = dateCueillette;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}