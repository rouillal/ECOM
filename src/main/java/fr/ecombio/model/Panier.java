package fr.ecombio.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @Column(name = "panier_id")
	private Long id;

	//private Map<Long,Article> articles = new HashMap<Long,Article>(); 
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE, mappedBy="panier")
	@JsonBackReference
    @Column(name = "panier_articles")
	private Set<Article> articles;
    
	
	Date dateDerniereModif = new Date();
	
	public Panier() {
		super();
		this.articles = new HashSet<Article>();
	}

    public Panier(Set<Article> commande) {
		this.setArticles(commande);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		dateDerniereModif = new Date();
	}

	public Set<Article> getArticles() {
		dateDerniereModif = new Date();
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = new HashSet<Article>();
		Iterator<Article> it = articles.iterator();
		while(it.hasNext()){
			this.articles.add(it.next());
		}
		dateDerniereModif = new Date();
	}


	public Date getDateDerniereModif() {
		return dateDerniereModif;
	}

	public void setDateDerniereModif(Date dateDerniereModif) {
		this.dateDerniereModif = dateDerniereModif;
	}

	public boolean contains(Long id) {
		Iterator<Article> i=articles.iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			if ((i.next()).getProduit().getId() == id) {
				return true;
			}
		}
		return false;
	}

	public Article getArticle(Long id) {
		Iterator<Article> i=articles.iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			Article article = i.next();
			if ((article).getProduit().getId() == id) {
				return article;
			}
		}
		return null;
	}
}
