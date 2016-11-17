package fr.ecombio.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GestionCommande implements Serializable{
	
	private Long produit_id;
	private int quantite;
	
	public Long getProduit_id() {
		return produit_id;
	}
	public void setProduit_id(Long produit_id) {
		this.produit_id = produit_id;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}
