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
	public String getLivDom() {
		return livDom;
	}
	public void setLivDom(String livDom) {
		this.livDom = livDom;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
}
