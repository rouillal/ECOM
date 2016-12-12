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


/**
 * @param name
 * @param quantite
 */
public InfosArticle(String name, String variete, int quantite, int quotite, String unite) {
	super();
	this.name = name +" "+ variete;
	this.quantite = quantite;
	this.quotite = quotite;
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

}
