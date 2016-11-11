eComBioApp
		.factory(
				'panierSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope,restBackendSvc,$window) {
							var listePanier = [];
							var selectedProduit = '';
							
							var setSelectedProduit  = function(newSelectedProduit) {
								selectedProduit = newSelectedProduit;
								var qtret = getPanierQuantite(newSelectedProduit);
								$rootScope.$broadcast('selectedProduitChange', newSelectedProduit,qtret);
							};
							
							var getSelectedProduit  = function() {
								return selectedProduit;
							};		
							
							var getListePanier = function() {
								return listePanier;
							};
							
							var supprimeArticlePanier = function(ligne) {
								angular.forEach(listePanier, function(ligneArticle, key) {
									if (ligneArticle.id == ligne.id) {
										listePanier.splice(key,1);
									}
								});
								$rootScope.$broadcast('rafraichirPanier');
							};
							
							var changeProduit=function(produitAChanger,quantite) {
								if (quantite == 0) {
									supprimeArticlePanier(produitAChanger);
								} else {
									var ligne = '';
									$rootScope.$broadcast('selectedProduitChange', produitAChanger,8);
									angular.forEach(listePanier, function(ligneArticle, key) {
										if (produitAChanger.id == ligneArticle.id) {
											ligneArticle.qt=quantite;
											ligneArticle.px=quantite*ligneArticle.prix;
											ligne = ligneArticle;
										}
									});
									if (ligne == '') {
										//Le produit à changer n'a pas été trouvé dans la liste, il faut le créer
										var ligneTmp= produitAChanger;
										ligneTmp['qt']=quantite;
										var price = quantite * produitAChanger.prix;
										ligneTmp['px']=price;
										listePanier.push(ligneTmp);
									}
								}
								$rootScope.$broadcast('selectedProduitChange', produitAChanger,quantite);
							};
							
							var getPanierQuantite = function(produit) {
								var ret = 0;
								angular.forEach(listePanier, function(article, key) {
									if (produit.id == article.id) {
										ret = article.qt;
									}
								});
								return ret;
							};
							
							return {
								setSelectedProduit: setSelectedProduit,
								getSelectedProduit: getSelectedProduit,
								getListePanier : getListePanier,
								changeProduit : changeProduit,
								getPanierQuantite : getPanierQuantite,
								supprimeArticlePanier : supprimeArticlePanier
							};
						} ]);