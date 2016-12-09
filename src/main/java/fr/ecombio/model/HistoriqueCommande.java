package fr.ecombio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * <p>
 * Description d'un historique des commande :
 * </p>
 *
 * @see Client
 * @see Panier
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "historiquecommande")
public class HistoriqueCommande implements Serializable {

	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produit_id")
	private Long id;

	/**
	 * client associe
	 * @see Client
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client;

	/**
	 * panier associe
	 * @see Panier
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="panier_id")
	private Panier panier;
	
	/**
	 * date de la commande
	 */
	private String date;
	
	/**
	 * heure de la commande
	 */
	private String heure;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Panier getPanier() {
		return panier;
	}
	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
}
