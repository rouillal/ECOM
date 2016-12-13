package fr.ecombio.model;

import java.util.Iterator;

/**
 * <p>
 * Classe pour serialiser des donnes sur un article :
 * <ul>
 * <li>son id</li>
 * <li>quantite</li>
 * </ul>
 * </p>
 */
public class GestionArticle {
	private Long id;

	private int quotite;

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
	 * Generation du contenu de panier sou la forme :
	 * <quantite,produit>,<qte2,pdt2>,...
	 * @return contenu du panier
	 */
	@Override
	public String toString() {
			return "<"+this.getQuotite()+":id<"+this.getId()+">>," ;
	}
}
