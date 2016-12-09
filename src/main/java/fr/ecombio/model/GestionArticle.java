package fr.ecombio.model;

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


	public Long getId() {
		return id;
	}

	public void setId(Long produit_id) {
		this.id = produit_id;
	}

	public int getQuotite() {
		return quotite;
	}

	public void setQuotite(int quotite) {
		this.quotite = quotite;
	}
}
