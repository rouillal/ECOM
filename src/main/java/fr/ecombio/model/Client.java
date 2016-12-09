package fr.ecombio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * Description d'un client :
 * </p>
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "client")
public class Client implements Serializable {

	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Long id;
	
	/**
	 * nom
	 */
	@NotNull
	@Column(name = "client_nom")
	private String nom;
	
	/**
	 * prenom
	 */
	@NotNull
	@Column(name = "client_prenom")
	private String prenom;
	
	/**
	 * mail (doit etre unique)
	 */
	@NotNull
	@Column(name = "client_mail")
	private String mail;

	/**
	 * adresse
	 */
	@Column(name = "client_adresse")
	private String adresse;
	
	/**
	 * code postal
	 */
	@Column(name = "client_cp")
	private String cp;
	
	/**
	 * ville
	 */
	@Column(name = "client_ville")
	private String ville;
	
	/**
	 * type du client :
	 * <ul>
	 * <li>g: gestionnaire (voir les commandes du jours)</li>
	 * <li>a: admin (ajout de produits, voir commandes du jour)</li>
	 * <li>c: client lambda</li>
	 * </ul>
	 */
	private String typeClient = "c";
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}
}
