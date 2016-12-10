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
	/**
	 * @return the idPanier
	 */
	public Long getIdPanier() {
		return idPanier;
	}
	/**
	 * @param idPanier the idPanier to set
	 */
	public void setIdPanier(Long idPanier) {
		this.idPanier = idPanier;
	}
	/**
	 * @return the commandInfo
	 */
	public GestionCommande getCommandInfo() {
		return commandInfo;
	}
	/**
	 * @param commandInfo the commandInfo to set
	 */
	public void setCommandInfo(GestionCommande commandInfo) {
		this.commandInfo = commandInfo;
	}
	/**
	 * @return the commandPaieInfo
	 */
	public ValidationPaiement getCommandPaieInfo() {
		return commandPaieInfo;
	}
	/**
	 * @param commandPaieInfo the commandPaieInfo to set
	 */
	public void setCommandPaieInfo(ValidationPaiement commandPaieInfo) {
		this.commandPaieInfo = commandPaieInfo;
	}
	
	
}
