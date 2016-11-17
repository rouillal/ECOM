package fr.ecombio.data;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.ecombio.model.Categorie;
import fr.ecombio.model.Produit;

@Stateless
public class StockManagerRepository {
	
	LinkedList<StockManager> stockManager = new LinkedList<StockManager>();
	
	public StockManagerRepository() {
		initTimer();
	}

	@Inject
	private EntityManager em;

	public void incrementeStock(Produit p) {
	}
	
	public void decrementeStock(Produit p, int quantite) {
		if (stockManager.contains(p)){
		}
		p.setStock(p.getStock()-quantite);
	}
	
	private void initTimer() {
		TimerTask timertask = new TimerTask() {
			@Override
			public void run() {
				
			}
		};
		
		Timer timer = new Timer("mon timer");
		timer.scheduleAtFixedRate(timertask, 0, 60000);
	}
}
