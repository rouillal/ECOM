package fr.ecombio.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@SuppressWarnings("serial")
@Entity
@Table(name = "panier")
public class Panier implements Serializable {

	// identification de la session
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id")
	private Long id;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE, mappedBy="panier")
	@JsonBackReference
    @Column(name = "produit_articles")
	private Map<Long,Article> articles = new HashMap<Long,Article>(); 
    
	public Panier() {
		super();
	}

    public Panier(Map<Long,Article> commande) {
		this.setArticles(commande);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<Long,Article> getArticles() {
		return articles;
	}

	public void setArticles(Map<Long,Article> articles) {
		this.articles = articles;
	}
}
