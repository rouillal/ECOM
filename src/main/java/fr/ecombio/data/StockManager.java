package fr.ecombio.data;

import java.util.Date;

import fr.ecombio.model.Produit;

public class StockManager {
	Produit produit;
	int quantite;
	Date dateDerniereModif;
	
	public StockManager(Produit produit, Date dateDerniereModif) {
		super();
		this.produit = produit;
		this.dateDerniereModif = dateDerniereModif;
	}
}
