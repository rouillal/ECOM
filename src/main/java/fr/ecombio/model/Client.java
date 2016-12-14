package fr.ecombio.model;

import java.io.Serializable;

import javax.jdo.annotations.Index;
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
	@Column(unique = true, name = "client_mail")
	@Index(name = "mail2")
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
	@Column(name = "client_type")
	private String typeClient = "c";

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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * @param cp the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the typeClient
	 */
	public String getTypeClient() {
		return typeClient;
	}

	/**
	 * @param typeClient the typeClient to set
	 */
	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}
}
