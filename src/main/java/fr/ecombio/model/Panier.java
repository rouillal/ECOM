package fr.ecombio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
    @Column(name = "panier_id")
	private Long id;

	//private Map<Long,Article> articles = new HashMap<Long,Article>(); 
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE, mappedBy="panier")
	@JsonBackReference
    @Column(name = "panier_articles")
	private Set<Article> articles;
    
	
	Date dateDerniereModif = new Date();
	
	Boolean isRegistred = false;
	
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
		Iterator<Article> i=this.articles.iterator();
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

	public Boolean getIsRegistred() {
		return isRegistred;
	}

	public void setIsRegistred(Boolean isRegistred) {
		this.isRegistred = isRegistred;
	}
	
	@Override
	public String toString() {
		String retour = "panier"+this.getId()+" : ";
		Iterator<Article> i=this.articles.iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			Article a = i.next();
			retour+= ("<"+a.getQuotite()+":"+a.getProduit().getName()+">,");
		}
		return retour;
	}
	
	public String toStringHTML() {
		String retour = "Vos produits :\n<center><table>\n";			
		retour += String.format("<tr style=\"font-weight:bold;\"><td>%s</td><td style=\"text-align:center;\">%s</td><td>%s</td>", "Produit", "Quantite", "Prix à l'unité (€)");

		Iterator<Article> i=this.articles.iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			Article a = i.next();			
			retour += String.format("<tr><td>%s %s</td><td style=\"text-align:center;\">%s</td><td style=\"text-align:center;\">%s</td>", a.getProduit().getName(), a.getProduit().getVariete(), a.getQuotite(), a.getProduit().getPrix() );
		}
		return retour+"\n</table></center><br>";
	}

	public double getTotal() {
		double retour = 0;
		Iterator<Article> i=this.articles.iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			Article a = i.next();
			retour+= a.getProduit().getPrix()*a.getQuotite();
		}
		return Math.round(retour * 100.0) / 100.0;
	}
}
