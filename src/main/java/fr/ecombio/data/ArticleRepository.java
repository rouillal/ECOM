package fr.ecombio.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import fr.ecombio.model.Article;


/**
 * <p>
 * Permet une gestion des articles :
 * <ul>
 * 	<li>faire des requetes de select</li>
 * 	<li>ajouter un article en base</li>
 *  </ul>
 * </p>
 * 
 * @see EntityManager
 * @see Article
 * @see ProduitRepository
 *
 */
@Stateless
public class ArticleRepository {

	/**
	 * pour gerer l'aspect transactionnel
	 * 
	 * @see EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * 
	 * @see ProduitRepository
	 */
	@Inject
	private ProduitRepository ProduitRepository;

	/**
	 * Recherche d'un article par son identifiant
	 * @param id identificateur de l'article
	 * @return l'article
	 */
	public Article findById(Long id) {
		return em.find(Article.class, id);
	}
	

	/**
	 * Persistence d'un article en base
	 * @param article Article a faire persiter
	 */
	public  void AjoutArticle(Article article) {
		em.persist(article);
		em.flush();
	}

	/**
	 * Mise a jour d'un article en base
	 * @param article Article a faire mettre a jour
	 */
	public void updateArticle(Article article) {
		ProduitRepository.updateProduit(article.getProduit());
		em.merge(article);
	}


	public void SupprimeArticle(Article a) {
		em.remove(em.find(Article.class, a.getId()));
	}
}
