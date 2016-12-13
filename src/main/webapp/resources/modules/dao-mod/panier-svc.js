eComBioApp.factory('panierSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		'cookieStoreSvc',
		'imgProviderSvc',
		function($rootScope, restBackendSvc, $window,cookieStoreSvc,imgProviderSvc) {
			var listePanier = [];// cookieStoreSvc.getStoredLocalItem('panier');
			var selectedProduit = '';
			var montantTotal = 0;// cookieStoreSvc.getStoredLocalString('montantTotal');
			var idPanierServer = cookieStoreSvc.getStoredLocalString('idPanierServer');
			
			var recalculPanier = function() {
				montantTotal = 0.00;
				var listPanierCommandeJson = angular.toJson(listePanier);
				$window.alert('restaurPanier'+listPanierCommandeJson);
				angular.forEach(listePanier, function(ligneArticle, key) {
					ligneArticle['prixTotal'] = Math.round(ligneArticle.quotite * ligneArticle.prix*100)/100;
					montantTotal += ligneArticle.prixTotal;
				});
				var listPanierCommandeJson2 = angular.toJson(listePanier);
				$window.alert('restaurPanier MAJ prix'+listPanierCommandeJson2);
			}
			
			var restaurPanierInit = function(idPanierServer) {
				listePanier = [];
				selectedProduit = '';
				montantTotal = 0.00;
				var urlPanierRestau = 'panier?id='+idPanierServer;
				//$window.alert('RRR0_'+urlPanierRestau);
				restBackendSvc.getItemsByUrl(urlPanierRestau).then(function(data) {
					listePanier = data.data;
					recalculPanier();
					$rootScope.$broadcast('rafraichirPanier');
				}, function(reason) {
					$rootScope.$broadcast('debug', reason);
					if (reason.status == 404) {
						$rootScope.$broadcast('rafraichirPanier', '');
					} else {
						alert('Failed: ' + reason);
					}
				});
			};
			
			if (idPanierServer.length>0) {
				restaurPanierInit(idPanierServer);
			}
				
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
					angular.forEach(listePanier, function(ligneArticle, key) {
						if (produitAChanger.id == ligneArticle.id) {
							ligneArticle.quotite = quantite;
							ligne = ligneArticle;
						}
					});
					var infoJson = angular.toJson(produitAChanger);
					if (ligne == '') {
						// Le produit à changer n'a pas été trouvé dans la
						// liste, il faut le créer
						var ligneTmp = produitAChanger;
						ligneTmp['quotite'] = quantite;
						var price = quantite * produitAChanger.prix;
						ligneTmp['prixTotal'] = price;
						listePanier.push(ligneTmp);
					}
					recalculPanier();
				}
				cookieStoreSvc.storeLocalItem('panier',listePanier);
				cookieStoreSvc.storeLocalString('montantTotal',montantTotal);
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
						$rootScope.$broadcast('StockOk');
						//$window.alert('OK !!');
					}, function(error) {
						var errorJson = angular.toJson(error);
						if (error.status == 304) {
							montantTotal = 0;
							angular.forEach(listePanier, function(ligneArticle, key) {
								if (produitAChanger.id == ligneArticle.id) {
									ligneArticle.quotite = quantite-1;
									ligneArticle.prixTotal = Math.round((quantite-1) * ligneArticle.prix*100)/100;
									ligne = ligneArticle;
								}
								montantTotal += ligneArticle.prixTotal;
							});
							$rootScope.$broadcast('rafraichirPanier');
							$rootScope.$broadcast('StockInsuffisant');
							$rootScope.$broadcast('anomalieTechnique', "Plus de stock");
							//$window.alert("Votre produit n'est plus en stock");
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
			
			var supprimeArticlePanier = function(ligne) {
				montantTotal = 0.00;
				angular.forEach(listePanier, function(ligneArticle, key) {
					if (ligneArticle.id == ligne.id) {
						changeProduit(ligneArticle,0);
						listePanier.splice(key, 1);
					} else {
						montantTotal += ligneArticle.prixTotal;
					}
				});
				cookieStoreSvc.storeLocalItem('panier',listePanier);
				cookieStoreSvc.storeLocalString('montantTotal',montantTotal);
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
			
			var getPanierCommande = function(idPanierCommande) {
				var urlPanierCommande = 'panier?id='+idPanierCommande;
				$window.alert('RRR0_'+urlPanierCommande);
				restBackendSvc.getItemsByUrl(urlPanierCommande).then(function(data) {
					var listPanierCommande = data.data;
					var listPanierCommandeJson = angular.toJson(listPanierCommande);
					$window.alert('RRR0888_'+listPanierCommandeJson);
					$rootScope.$broadcast('listPanierCommandeSupplied', listPanierCommande);
				}, function(reason) {
					$rootScope.$broadcast('debug', reason);
					if (reason.status == 404) {
						$rootScope.$broadcast('listPanierCommandeSupplied', '');
					} else {
						alert('Failed: ' + reason);
					}
				});
			}

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
				getMontantTotal : getMontantTotal,
				getPanierCommande : getPanierCommande
			};
		} ]);