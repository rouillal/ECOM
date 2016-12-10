package fr.ecombio.model;

import java.io.Serializable;

/**
 * <p>
 * Classe pour serialiser des donnes sur le client :
 * <ul>
 * <li>nom</li>
 * <li>prenom</li>
 * <li>mail</li>
 * <li>adresse</li>
 * <li>code postal</li>
 * <li>ville</li>
 * <li>mot de passe</li>
 * </ul>
 * </p>
 */
@SuppressWarnings("serial")
public class ValidationClient implements Serializable{
	private String nom;
	private String prenom;
	private String mail;
	private String adresse;
	private String cp;
	private String ville;
	private String psw;
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
	 * @return the psw
	 */
	public String getPsw() {
		return psw;
	}
	/**
	 * @param psw the psw to set
	 */
	public void setPsw(String psw) {
		this.psw = psw;
	}
	

}
