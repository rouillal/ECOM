eComBioApp.factory('panierSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		'cookieStoreSvc',
		'imgProviderSvc',
		function($rootScope, restBackendSvc, $window,cookieStoreSvc,imgProviderSvc) {
			var listePanier = cookieStoreSvc.getStoredLocalItem('panier');
			var selectedProduit = '';
			var montantTotal = cookieStoreSvc.getStoredLocalString('montantTotal');
			var idPanierServer = cookieStoreSvc.getStoredLocalString('idPanierServer');
			if (idPanierServer=='') {
				idPanierServer=-1;
			}

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
			
			var getSuggestedRecette = function(pageSuggestedRecette) {
				var urlSuggestedRecette = 'recette/panier?id='+idPanierServer+'&page='+pageSuggestedRecette;
				restBackendSvc.getItemsByUrl(urlSuggestedRecette).then(function(data) {
					var listRecette = data.data;
					angular.forEach(listRecette, function(recette, key) {
						recette['url']=imgProviderSvc.getImage(recette.filename);
					});
					$rootScope.$broadcast('listSuggestedRecettesSupplied', listRecette);
				}, function(reason) {
					$rootScope.$broadcast('debug', reason);
					if (reason.status == 404) {
						$rootScope.$broadcast('listRecettesSupplied', '');
					} else {
						alert('Failed: ' + reason);
					}
				});
			};

			var supprimeArticlePanier = function(ligne) {
				montantTotal = 0.00;
				angular.forEach(listePanier, function(ligneArticle, key) {
					if (ligneArticle.id == ligne.id) {
						listePanier.splice(key, 1);
					} else {
						montantTotal += ligneArticle.prixTotal;
					}
				});
				cookieStoreSvc.storeLocalItem('panier',listePanier);
				cookieStoreSvc.storeLocalString('montantTotal',montantTotal);
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
					montantTotal = 0.00;
					angular.forEach(listePanier, function(ligneArticle, key) {
						if (produitAChanger.id == ligneArticle.id) {
							ligneArticle.quotite = quantite;
							ligneArticle.prixTotal = Math.round(quantite * ligneArticle.prix*100)/100;
							ligne = ligneArticle;
						}
						montantTotal += ligneArticle.prixTotal;
					});
					var infoJson = angular.toJson(produitAChanger);
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
				cookieStoreSvc.storeLocalItem('panier',listePanier);
				cookieStoreSvc.storeLocalString('montantTotal',montantTotal);
				montantTotal
				var panierJson = prepareMessageServeur();
				if (idPanierServer < 0) {
					restBackendSvc.createItem('panier', panierJson).then(
							function(data) {
								idPanierServer = data.data;
								cookieStoreSvc.storeLocalString('idPanierServer',idPanierServer);
							});
				} else {
					var urlUpdate = 'panier?id='+idPanierServer;
					restBackendSvc.updateItem(urlUpdate, panierJson).then(function(response) {
						//$window.alert('OK !!');
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
			
			var getIdPanierServer = function() {
				return idPanierServer;
			};
			
			var resetPanier = function() {
				listePanier = [];
				selectedProduit = '';
				montantTotal = 0.00;
				idPanierServer = -1;
				cookieStoreSvc.storeLocalItem('panier',listePanier);
				cookieStoreSvc.storeLocalString('idPanierServer',idPanierServer);
				cookieStoreSvc.storeLocalString('montantTotal',montantTotal);
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
				return Math.round(montantTotal*100)/100;
			};

			return {
				setSelectedProduit : setSelectedProduit,
				getSelectedProduit : getSelectedProduit,
				getListePanier : getListePanier,
				getSuggestedRecette : getSuggestedRecette,
				changeProduit : changeProduit,
				getIdPanierServer : getIdPanierServer,
				getPanierQuantite : getPanierQuantite,
				supprimeArticlePanier : supprimeArticlePanier,
				resetPanier : resetPanier,
				getMontantTotal : getMontantTotal
			};
		} ]);