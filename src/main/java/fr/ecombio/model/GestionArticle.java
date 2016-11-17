package fr.ecombio.model;

public class GestionArticle {
	private Long id;

	private int quotite;


	public Long getProduit() {
		return id;
	}

	public void setProduit(Long produit_id) {
		this.id = produit_id;
	}

	public int getQuotite() {
		return quotite;
	}

	public void setQuotite(int quotite) {
		this.quotite = quotite;
	}
}
