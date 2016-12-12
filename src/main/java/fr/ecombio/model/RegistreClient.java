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
import javax.validation.constraints.NotNull;

/**
 * <p>
 * Enregistrement d'un client avec un login et mot de passe
 * </p>
 *
 * @see Client
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "registreclient")
public class RegistreClient implements Serializable {
	
	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registreclient_id")
	private Long id;
	
	/**
	 * mail = login
	 */
	@NotNull
	@Column(unique = true)
	@Index(name = "mail")
	private String mail;
	
	/**
	 * mot de passe
	 */
	@NotNull
	private String mdp;
	

	/**
	 * client associe
	 * @see Client
	 */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="client_id")
	private Client client;

	
	/**
	 * @return the decrypted password
	 * @see MyCryptoConverter
	 * @see MyCryptoConverter#decrypt(String)
	 */
	public String getMdp() {
		return MyCryptoConverter.decrypt(mdp);
	}

	/**
	 * @param mdp the password to encrypt
	 * @see MyCryptoConverter
	 * @see MyCryptoConverter#encrypt(String)
	 */
	public void setMdp(String mdp) {
		this.mdp = MyCryptoConverter.encrypt(mdp);
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
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
}
