package fr.ecombio.data;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.ecombio.model.Article;
import fr.ecombio.model.Panier;
import fr.ecombio.model.Produit;

/**
 * <p>
 * Permet une gestion des stocks optimiste :
 * <ul>
 * 	<li>decrement/increment du stock lorsqu'un utilisateur ajoute/suprime un produit au panier</li>
 * 	<li>increment du stock lorsqu'un panier a atteint un time-out d'inactivite</li>
 *  </ul>
 * </p>
 * 
 * @see ProduitRepository
 * @see PanierRepository
 *
 */
@Stateless
public class StockManagerRepository {

	private static boolean isInit = false;

	/**
	 * default cstor.
	 */
	public StockManagerRepository() {
		if (!isInit) {
			initTimer();
		}
	}

	/**
	 * @see ProduitRepository
	 */
	@Inject
	private ProduitRepository Produitrepository;

	/**
	 * @see PanierRepository
	 */
	@Inject
	private PanierRepository Panierrepository;

	/**
	 * increment du stock lorsqu'un panier a atteint un time-out d'inactivité
	 * @param panier
	 */
	public void incrementeStock(Panier panier) {
		Iterator<Article> i=panier.getArticles().iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			Article valeur = i.next();
			valeur.getProduit().setStock(valeur.getProduit().getStock()+valeur.getQuotite());
			Produitrepository.updateProduit(valeur.getProduit());
		}
	}

	/**
	 * incremente le stock correspondant a un produit
	 * @param panier
	 * @param article
	 */
	public void incrementeStock(Panier panier, Article article) {
		Iterator<Article> i=panier.getArticles().iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			Article valeur = i.next();
			if (valeur.getId() == article.getId()){
				valeur.getProduit().setStock(valeur.getProduit().getStock()+1);
				Produitrepository.updateProduit(valeur.getProduit());
			}
		}
		panier.setDateDerniereModif(new Date());
		Panierrepository.updatePanier(panier);
	}

	/**
	 * decremente le stock correspondant à un produit
	 * @param panier
	 * @param article
	 */
	public void decrementeStock(Panier panier, Article article) {

		Iterator<Article> i=panier.getArticles().iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			Article valeur = i.next();
			if (valeur.getId() == article.getId()){
				valeur.getProduit().setStock(valeur.getProduit().getStock()-1);
				Produitrepository.updateProduit(valeur.getProduit());
			}
		}
		panier.setDateDerniereModif(new Date());
		Panierrepository.updatePanier(panier);
	}



	/**
	 * Incremente le stock du produit
	 * @param id identifiant du produit
	 */
	public void incrementeStock(Long id) {
		Produit p = Produitrepository.findById(id);
		p.setStock(p.getStock()+1);
		Produitrepository.updateProduit(p);
	}

	/**
	 * Decremente le stock du produit
	 * @param id identifiant du produit
	 */
	public void decrementeStock(Long id) {
		Produit p = Produitrepository.findById(id);
		p.setStock(p.getStock()-1);
		Produitrepository.updateProduit(p);
	}

	/**
	 * Tache qui tourne sur le serveur:
	 *   Elle supprime un panier au bou d'un time out d'inactivite,
	 *   de maniere à liberer les stocks.
	 */
	private void initTimer() {
		TimerTask timertask = new TimerTask() {

			@Inject
			private PanierRepository Panierrepository2;

			public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
				long diffInMillies = date2.getTime() - date1.getTime();
				return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
			}
			@Override
			public void run() {
				Date today = new Date();
				if (Panierrepository2 != null) {
					List<Panier> paniers = Panierrepository2.getAll();
					for(Panier panier : paniers) {
						if (!panier.getIsRegistred() && getDateDiff(panier.getDateDerniereModif(),today,TimeUnit.MINUTES) >= 1){
							// on incrémente le stock
							incrementeStock(panier);
							// on supprime le panier
							Panierrepository2.SupprimePanier(panier);
							// on envoie un evenement suppression
							// on l'envoie au moment où le client fait un put et que l'id du panier n'est plus en base
						}
					}
					StockManagerRepository.isInit = true;
				}
			}
		};

		Timer timer = new Timer("mon timer");
		timer.scheduleAtFixedRate(timertask, 0, 60000);
	}

}
