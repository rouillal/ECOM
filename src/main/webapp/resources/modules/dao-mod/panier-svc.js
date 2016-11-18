eComBioApp.factory('panierSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		function($rootScope, restBackendSvc, $window) {
			var listePanier = [];
			var selectedProduit = '';
			var montantTotal = 0;
			var idPanierServer = -1;

			var setSelectedProduit = function(newSelectedProduit) {
				selectedProduit = newSelectedProduit;
				var qtret = getPanierQuantite(newSelectedProduit);
				$rootScope.$broadcast('selectedProduitChange',
						newSelectedProduit, qtret);
			};

			var getSelectedProduit = function() {
				return selectedProduit;
			};

			var getListePanier = function() {
				return listePanier;
			};

			var supprimeArticlePanier = function(ligne) {
				montantTotal = 0;
				angular.forEach(listePanier, function(ligneArticle, key) {
					if (ligneArticle.id == ligne.id) {
						listePanier.splice(key, 1);
					} else {
						montantTotal += ligneArticle.prixTotal;
					}
				});
				$rootScope.$broadcast('rafraichirPanier');
			};
			
			var prepareMessageServeur = function() {
				var listePanierServeur = [];
				angular.forEach(listePanier, function(ligneArticle, key) {
					var ligneServeurTmp = new Object();
					ligneServeurTmp['id']=ligneArticle.id;
					ligneServeurTmp['quotite']=ligneArticle.quotite;
					listePanierServeur.push(ligneServeurTmp);
				});
				var listePanierServeurJson = angular.toJson(listePanierServeur);
				return listePanierServeurJson; 
			}

			var changeProduit = function(produitAChanger, quantite) {
				if (quantite == 0) {
					supprimeArticlePanier(produitAChanger);
				} else {
					var ligne = '';
					montantTotal = 0;
					angular.forEach(listePanier, function(ligneArticle, key) {
						if (produitAChanger.id == ligneArticle.id) {
							ligneArticle.quotite = quantite;
							ligneArticle.prixTotal = quantite * ligneArticle.prix;
							ligne = ligneArticle;
						}
						montantTotal += ligneArticle.prixTotal;
					});
					if (ligne == '') {
						//Le produit à changer n'a pas été trouvé dans la liste, il faut le créer
						var ligneTmp = produitAChanger;
						ligneTmp['quotite'] = quantite;
						var price = quantite * produitAChanger.prix;
						ligneTmp['prixTotal'] = price;
						listePanier.push(ligneTmp);
						montantTotal += price;
					}
				}
				var panierJson = prepareMessageServeur();
				if (idPanierServer < 0) {
					restBackendSvc.createItem('panier', panierJson).then(
							function(data) {
								idPanierServer = data.data;
							});
				} else {
					var urlUpdate = 'panier?id='+idPanierServer;
					restBackendSvc.updateItem(urlUpdate, panierJson).then(function(response) {
						$window.alert('OK !!');
					}, function(error) {
						var errorJson = angular.toJson(error);
						if (error.status == 304) {
							$rootScope.$broadcast('anomalieTechnique', "Plus de stock");
							$window.alert("Plus de stock - "+errorJson);
						} else if (error.status == 404) {
							$rootScope.$broadcast('anomalieTechnique', "Votre panier a été supprimé, temps d'inactivité trop long - Recréation - "+errorJson);
							$window.alert("Votre panier a été supprimé, temps d'inactivité trop long - Recréation - "+errorJson);
							restBackendSvc.createItem('panier', panierJson).then(
									function(data) {
										idPanierServer = data.data;
										$window.alert("Panier créé à nouveau !");
									});
						} else {
							$rootScope.$broadcast('anomalieTechnique', errorJson);
							$window.alert('Error : '+errorJson);
						}
					});
				}
				$rootScope.$broadcast('selectedProduitChange', produitAChanger,
						quantite);
				$rootScope.$broadcast('rafraichirPanier');
			};

			var getPanierQuantite = function(produit) {
				var ret = 0;
				angular.forEach(listePanier, function(article, key) {
					if (produit.id == article.id) {
						ret = article.quotite;
					}
				});
				return ret;
			};

			var getMontantTotal = function() {
				return montantTotal;
			};

			return {
				setSelectedProduit : setSelectedProduit,
				getSelectedProduit : getSelectedProduit,
				getListePanier : getListePanier,
				changeProduit : changeProduit,
				getPanierQuantite : getPanierQuantite,
				supprimeArticlePanier : supprimeArticlePanier,
				getMontantTotal : getMontantTotal
			};
		} ]);