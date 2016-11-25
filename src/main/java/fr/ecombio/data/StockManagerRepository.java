package fr.ecombio.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.ecombio.model.Article;
import fr.ecombio.model.Panier;
import fr.ecombio.model.Produit;

@Stateless
public class StockManagerRepository {

	private static boolean isInit = false;

	public StockManagerRepository() {
		if (!isInit) {
			initTimer();
		}
	}

	@Inject
	private ProduitRepository Produitrepository;

	@Inject
	private PanierRepository Panierrepository;

	public void incrementeStock(Panier panier) {
		Iterator<Article> i=panier.getArticles().iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			Article valeur = i.next();
			valeur.getProduit().setStock(valeur.getProduit().getStock()+valeur.getQuotite());
			Produitrepository.updateProduit(valeur.getProduit());
		}
	}

	public void decrementeStock(Panier panier) {

		Iterator<Article> i=panier.getArticles().iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			Article valeur = i.next();
			valeur.getProduit().setStock(valeur.getProduit().getStock()-1);
			Produitrepository.updateProduit(valeur.getProduit());
		}
		panier.setDateDerniereModif(new Date());
		Panierrepository.updatePanier(panier);
	}

	private void initTimer() {
		TimerTask timertask = new TimerTask() {
			public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
				long diffInMillies = date2.getTime() - date1.getTime();
				return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
			}
			@Override
			public void run() {
				Date today = new Date();
				if (Panierrepository != null) {
					List<Panier> paniers = Panierrepository.getAll();
					for(Panier panier : paniers) {
						if (!panier.getIsRegistred() && getDateDiff(panier.getDateDerniereModif(),today,TimeUnit.MINUTES) >= 1){
							// on incrémente le stock
							incrementeStock(panier);
							// on supprime le panier
							Panierrepository.SupprimePanier(panier);
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
