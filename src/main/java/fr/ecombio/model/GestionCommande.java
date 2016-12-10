package fr.ecombio.model;

import java.io.Serializable;

/**
 * <p>
 * Classe pour serialiser des donnes sur la commande :
 * <ul>
 * <li>nom</li>
 * <li>prenom</li>
 * <li>mail</li>
 * <li>livDom : si livraison a l'entrepot ou a domicile</li>
 * <li>cp : code postal</li>
 * <li>ville</li>
 * <li>date de livraison</li>
 * <li>heure de livraison</li>
 * </ul>
 * </p>
 */
@SuppressWarnings("serial")
public class GestionCommande implements Serializable{
	
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
	 * @return the livDom
	 */
	public String getLivDom() {
		return livDom;
	}
	/**
	 * @param livDom the livDom to set
	 */
	public void setLivDom(String livDom) {
		this.livDom = livDom;
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the heure
	 */
	public String getHeure() {
		return heure;
	}
	/**
	 * @param heure the heure to set
	 */
	public void setHeure(String heure) {
		this.heure = heure;
	}
	
	
}
