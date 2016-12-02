package fr.ecombio.data;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.ecombio.model.Article;
import fr.ecombio.model.Panier;

@Stateless
public class PanierRepository {
	
	@Inject
	private EntityManager em;
	
	@Inject
	private ArticleRepository ArticleRepository;
	
	//Logger log;
	Logger log = java.util.logging.Logger.getLogger("org.hibernate");

	public Long AjoutPanier(Panier panier) {
		em.persist(panier);
		return panier.getId();
	}
	
	public Panier findById(Long id) {
		Panier p = em.find(Panier.class, id);
		log.log(Level.INFO, p.toString());
		return p;
	}

	public void updatePanier(Panier panier) {
		Iterator<Article> i=panier.getArticles().iterator();
		while(i.hasNext()) // tant qu'on a un suivant
		{
			ArticleRepository.updateArticle(i.next());
		}
		em.merge(panier);
	}

	public List<Panier> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Panier> criteria = cb.createQuery(Panier.class);
		Root<Panier> Panier = criteria.from(Panier.class);
		criteria.select(Panier);
		return em.createQuery(criteria).getResultList();
	}

	public void SupprimePanier(Panier panier) {
		em.remove(em.find(Panier.class, panier.getId()));
	}

}