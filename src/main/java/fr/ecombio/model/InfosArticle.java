package fr.ecombio.model;

import java.io.Serializable;

/**
 * <p>
 * Classe pour serialiser des donnes sur un article :
 * <ul>
 * <li>son nom complet (name+variete)</li>
 * <li>quantite</li>
 * </ul>
 * </p>
 */
@SuppressWarnings("serial")
public class InfosArticle implements Serializable {
private String name;
private int quotite;
private int quantite;
private String unite;
private float prix;


/**
 * @param name
 * @param quantite
 */
public InfosArticle(String name, String variete, int quantite, int quotite, String unite, float prix ) {
	super();
	this.name = name +" "+ variete;
	this.quantite = quantite;
	this.quotite = quotite;
	this.unite = unite;
	this.prix = prix;
}
/**
 * @return the quotite
 */
public int getQuotite() {
	return quotite;
}
/**
 * @param quotite the quotite to set
 */
public void setQuotite(int quotite) {
	this.quotite = quotite;
}
/**
 * @return the unite
 */
public String getUnite() {
	return unite;
}
/**
 * @param unite the unite to set
 */
public void setUnite(String unite) {
	this.unite = unite;
}
/**
 * 
 */
public InfosArticle() {
	super();
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the quantite
 */
public int getQuantite() {
	return quantite;
}
/**
 * @param quantite the quantite to set
 */
public void setQuantite(int quantite) {
	this.quantite = quantite;
}

/**
 * @return the prix
 */
public float getPrix() {
	return prix;
}
/**
 * @param prix the prix to set
 */
public void setPrix(float prix) {
	this.prix = prix;
}

}
