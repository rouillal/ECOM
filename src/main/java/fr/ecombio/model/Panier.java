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

/**
 * <p>
 * Description d'un panier :
 * </p>
 * 
 * @see Article
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "panier")
public class Panier implements Serializable {

	/**
	 * identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "panier_id")
	private Long id;

	/**
	 * liste d'articles contenus
	 * @see Article
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE, mappedBy="panier")
	@JsonBackReference
    @Column(name = "panier_articles")
	private Set<Article> articles;
    
	/**
	 * derniere date de modification du panier
	 * pour detecter l'inacivite d'une session
	 */
	Date dateDerniereModif = new Date();
	
	/**
	 * false : panier en cours d'utilisation
	 * true : panier persistant pour l'historique des commandes
	 */
	Boolean isRegistred = false;
	
	/**
	 * default cstor.
	 */
	public Panier() {
		super();
		this.articles = new HashSet<Article>();
	}

	/**
	 * cstor.
	 * @param commande
	 */
    public Panier(Set<Article> commande) {
		this.setArticles(commande);
	}

	/**
	 * Recherche d'un article dans le panier
	 * @param id identifiant de l'article qu'on cherche
	 * @return article
	 */
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
	
	/**
	 * Generation du contenu de panier sou la forme :
	 * <quantite,produit>,<qte2,pdt2>,...
	 * @return contenu du panier
	 */
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
	
	/**
	 * Generation du contenu du panier en HTML
	 * @return text en HTML
	 */
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

	/**
	 * Calcul de montant total du panier
	 * @return montant du panier
	 */
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
		dateDerniereModif = new Date();
	}

	/**
	 * @return the set of article
	 */
	public Set<Article> getArticles() {
		dateDerniereModif = new Date();
		return articles;
	}
	
	/**
	 * @param articles the set of article to set
	 */
	public void setArticles(Set<Article> articles) {
		this.articles = new HashSet<Article>();
		Iterator<Article> it = articles.iterator();
		while(it.hasNext()){
			this.articles.add(it.next());
		}
		dateDerniereModif = new Date();
	}

	/**
	 * @return the dateDerniereModif
	 */
	public Date getDateDerniereModif() {
		return dateDerniereModif;
	}

	/**
	 * @param dateDerniereModif the dateDerniereModif to set
	 */
	public void setDateDerniereModif(Date dateDerniereModif) {
		this.dateDerniereModif = dateDerniereModif;
	}

	/**
	 * @return the isRegistred
	 */
	public Boolean getIsRegistred() {
		return isRegistred;
	}

	/**
	 * @param isRegistred the isRegistred to set
	 */
	public void setIsRegistred(Boolean isRegistred) {
		this.isRegistred = isRegistred;
	}
}
