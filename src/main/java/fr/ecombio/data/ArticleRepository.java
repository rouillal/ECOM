package fr.ecombio.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.ecombio.model.Article;

@Stateless
public class ArticleRepository {

	@Inject
	private EntityManager em;
	
	@Inject
	private ProduitRepository ProduitRepository;

	public Article findById(Long id) {
		return em.find(Article.class, id);
	}
	

	public  void AjoutArticle(Article article) {
		em.persist(article);
		em.flush();
	}


	public void updateArticle(Article article) {
		ProduitRepository.updateProduit(article.getProduit());
		em.merge(article);
	}
}
