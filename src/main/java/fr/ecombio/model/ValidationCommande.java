package fr.ecombio.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ValidationCommande implements Serializable{
	private Long idPanier;
	private GestionCommande commandInfo;
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
