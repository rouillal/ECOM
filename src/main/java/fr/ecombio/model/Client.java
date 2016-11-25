package fr.ecombio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "client")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produit_id")
	private Long id;
	
	private String nom;
	private String prenom;
	private String mail;
	/**
	 * livraison à domicile:
	 * 	e si entrepot
	 * 	d si domicile
	 */
	private String livDom;
	private String adresse;
	private String cp;
	private String ville;
	/**
	 * dd/mm/yyyy
	 */
	private String date;
	private String heure;
}
