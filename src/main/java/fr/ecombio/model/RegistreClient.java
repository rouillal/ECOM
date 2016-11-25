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
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "registreclient")
public class RegistreClient implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registreclient_id")
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String mail;
	
	@NotNull
	private String mdp;
	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="client_id")
	private Client client;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return MyCryptoConverter.decrypt(mdp);
	}

	public void setMdp(String mdp) {
		this.mdp = MyCryptoConverter.encrypt(mdp);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
