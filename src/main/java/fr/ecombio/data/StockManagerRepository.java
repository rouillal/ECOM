package fr.ecombio.data;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.ecombio.model.Produit;

@Stateless
public class StockManagerRepository {

	static HashMap<Long,StockManager> stockManager = new HashMap<Long,StockManager>();

	public StockManagerRepository() {
		initTimer();
	}

	@Inject
	private EntityManager em;

	private ProduitRepository repository;

	public void incrementeStock(Produit p, int quantite) {
		if (stockManager.containsKey(p.getId())){
			p.setStock(p.getStock()-quantite);
			repository.updateProduit(p);
			StockManager sm = stockManager.get(p.getId());
			if (sm.getQuantite() == quantite) {
				stockManager.remove(p.getId());
			} else {
				sm.setQuantite(sm.getQuantite()+quantite);
				stockManager.put(p.getId(), sm);
			}
		}
	}

	public void decrementeStock(Produit p, int quantite) {
		if (stockManager.containsKey(p.getId())){
			StockManager sm = stockManager.get(p.getId());
			sm.setQuantite(sm.getQuantite()+quantite);
			sm.setDateDerniereModif(new Date());
			stockManager.put(p.getId(), sm);
		} else {
			StockManager sm = new StockManager(p,quantite);
			stockManager.put(p.getId(), sm);
		}
		p.setStock(p.getStock()-quantite);
		repository.updateProduit(p);
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
				for(Entry<Long, StockManager> entry : StockManagerRepository.stockManager.entrySet()) {
				    Long cle = entry.getKey();
				    StockManager valeur = entry.getValue();
				    if (getDateDiff(valeur.getDateDerniereModif(),today,TimeUnit.DAYS) > 1){
				    	
				    }
				}
			}
		};

		Timer timer = new Timer("mon timer");
		timer.scheduleAtFixedRate(timertask, 0, 60000);
	}
}
