package fr.ecombio.model;

import java.io.Serializable;

/**
 * <p>
 * Classe pour serialiser des donnes d'une commande :
 * <ul>
 * <li>id du panier</li>
 * <li>infos sur la commande</li>
 * <li>infos sur le paiement</li>
 * </ul>
 * </p>
 * 
 * @see GestionCommande
 * @see ValidationPaiement
 */
@SuppressWarnings("serial")
public class ValidationCommande implements Serializable{
	private Long idPanier;
	/**
	 * @see GestionCommande
	 */
	private GestionCommande commandInfo;
	/**
	 * @see ValidationPaiement
	 */
	private ValidationPaiement commandPaieInfo;
	
	public Long getIdPanier() {
		return idPanier;
	}
	public void setIdPanier(Long idPanier) {
		this.idPanier = idPanier;
	}
	public GestionCommande getCommandInfo() {
		return commandInfo;
	}
	public void setCommandInfo(GestionCommande commandInfo) {
		this.commandInfo = commandInfo;
	}
	public ValidationPaiement getCommandPaieInfo() {
		return commandPaieInfo;
	}
	public void setCommandPaieInfo(ValidationPaiement commandPaieInfo) {
		this.commandPaieInfo = commandPaieInfo;
	}
	
	
}
