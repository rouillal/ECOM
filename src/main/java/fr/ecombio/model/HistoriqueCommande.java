package fr.ecombio.model;

import java.io.Serializable;

import javax.jdo.annotations.Index;
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
	@Column(name = "historiquecommande_id")
	private Long id;

	/**
	 * client associe
	 * @see Client
	 */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="client_id")
	private Client client;

	/**
	 * panier associe
	 * @see Panier
	 */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="panier_id")
	private Panier panier;
	
	/**
	 * true : la commande a ete livree
	 * false : sinon
	 */
	private boolean isDelivred = false;
	
	/**
	 * date de la commande
	 */
	@Index(name="commande")
	private String date;
	
	/**
	 * heure de la commande
	 */
	private String heure;
	
	/**
	 * adresse de livraison
	 */
	private String livDom;

	/**
	 * @return the livDom
	 */
	public String getLivDom() {
		return livDom;
	}

	/**
	 * @param livDom the livDom to set
	 */
	public void setLivDom(String livDom) {
		this.livDom = livDom;
	}

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
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the panier
	 */
	public Panier getPanier() {
		return panier;
	}

	/**
	 * @param panier the panier to set
	 */
	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the heure
	 */
	public String getHeure() {
		return heure;
	}

	/**
	 * @param heure the heure to set
	 */
	public void setHeure(String heure) {
		this.heure = heure;
	}

	/**
	 * @return the isDelivred
	 */
	public boolean isDelivred() {
		return isDelivred;
	}

	/**
	 * @param isDelivred the isDelivred to set
	 */
	public void setDelivred(boolean isDelivred) {
		this.isDelivred = isDelivred;
	}

}
