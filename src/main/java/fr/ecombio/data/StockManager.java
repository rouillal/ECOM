package fr.ecombio.data;

import java.util.Date;

import fr.ecombio.model.Produit;

public class StockManager {
	private Produit produit;
	private int quantite;
	private Date dateDerniereModif;
	
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Date getDateDerniereModif() {
		return dateDerniereModif;
	}

	public void setDateDerniereModif(Date dateDerniereModif) {
		this.dateDerniereModif = dateDerniereModif;
	}

	public StockManager(Produit produit, int quantite) {
		super();
		this.produit = produit;
		this.quantite = quantite;
		this.dateDerniereModif = new Date();
	}
}
