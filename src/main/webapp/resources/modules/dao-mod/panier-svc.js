eComBioApp.factory('panierSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		'cookieStoreSvc',
		'imgProviderSvc',
		function($rootScope, restBackendSvc, $window,cookieStoreSvc,imgProviderSvc) {
			var listePanier = [];
			var selectedProduit = '';
			var montantTotal = 0;
			var idPanierServer = '';
			
			var recalculPanier = function() {
				montantTotal = 0.00;
				angular.forEach(listePanier, function(ligneArticle, key) {
					ligneArticle['prixTotal'] = Math.round(ligneArticle.quotite * ligneArticle.prix*100)/100;
					montantTotal += ligneArticle.prixTotal;
				});
				$rootScope.$broadcast('rafraichirPanier',listePanier,montantTotal);
			}
			
			var resetPanierDapresServeur = function() {
				listePanier = [];
				selectedProduit = '';
				montantTotal = 0.00;
				idPanierServer = -1;
				cookieStoreSvc.storeLocalString('idPanierServer',idPanierServer);
				$rootScope.$broadcast('rafraichirPanier',listePanier,montantTotal);
			};
			
			var restaurPanierInit = function() {
				selectedProduit = '';
				montantTotal = 0.00;
				idPanierServer = cookieStoreSvc.getStoredLocalString('idPanierServer');
				if (idPanierServer.length>0) {
					var urlPanierRestau = 'panier?id='+idPanierServer;
					restBackendSvc.getItemsByUrl(urlPanierRestau).then(function(data) {
						listePanier = data.data;
						recalculPanier();
					}, function(reason) {
						if (reason.status == 404) {
							resetPanierDapresServeur();
						} else {
							$rootScope.$broadcast('anomalieTechnique',reason);
						}
					});
				} else {
					resetPanierDapresServeur();
				}
			};
			
			restaurPanierInit();
			
			var addProduitNew = function(produitAjouter,quantite) {
				var ligneTmp = produitAjouter;
				ligneTmp['quotite'] = quantite;
				var price = quantite * produitAjouter.prix;
				ligneTmp['prixTotal'] = price;
				listePanier.push(ligneTmp);
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
					if (reason.status == 404) {
						$rootScope.$broadcast('listRecettesSupplied', '');
					} else {
						$rootScope.$broadcast('anomalieTechnique',reason);
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
				if (quantite <= 0) {
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
						addProduitNew(produitAChanger,quantite);
					}
					recalculPanier();
				}
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
						$rootScope.$broadcast('selectedProduitChange', produitAChanger,
								quantite);
					}, function(error) {
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
							$rootScope.$broadcast('rafraichirPanier',listePanier,montantTotal);
							$rootScope.$broadcast('StockInsuffisant');
							$rootScope.$broadcast('anomalieTechnique', "Plus de stock");
						} else if (error.status == 404) {
							resetPanierDapresServeur();
							$window.alert("Votre panier a été supprimé, temps d'inactivité trop long - Recréation - ");
							quantite=1;
							addProduitNew(produitAChanger,quantite);
							panierJson = prepareMessageServeur();
							restBackendSvc.createItem('panier', panierJson).then(
									function(data) {
										idPanierServer = data.data;
										cookieStoreSvc.storeLocalString('idPanierServer',idPanierServer);
										$window.alert("Panier créé à nouveau !");
										$rootScope.$broadcast('selectedProduitChange', produitAChanger,
												quantite);
									}, function(error) {
										if (error.status == 304) {
											supprimeArticlePanier(produitAChanger);
											$rootScope.$broadcast('rafraichirPanier',listePanier,montantTotal);
											$rootScope.$broadcast('StockInsuffisant');
											$rootScope.$broadcast('anomalieTechnique', "Plus de stock");
										}
									});
						} else {
							montantTotal = 0;
							angular.forEach(listePanier, function(ligneArticle, key) {
								if (produitAChanger.id == ligneArticle.id) {
									ligneArticle.quotite = quantite-1;
									ligneArticle.prixTotal = Math.round((quantite-1) * ligneArticle.prix*100)/100;
									ligne = ligneArticle;
								}
								montantTotal += ligneArticle.prixTotal;
							});
							$window.alert("Rollback suite anomalie serveur");
							$rootScope.$broadcast('rafraichirPanier',listePanier,montantTotal);
							$rootScope.$broadcast('anomalieTechnique',error);
						}
					});
				}
				$rootScope.$broadcast('rafraichirPanier',listePanier,montantTotal);
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
				$rootScope.$broadcast('rafraichirPanier',listePanier,montantTotal);
			};
			
			var getIdPanierServer = function() {
				return idPanierServer;
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
				restBackendSvc.getItemsByUrl(urlPanierCommande).then(function(data) {
					var listPanierCommande = data.data;
					var listPanierCommandeJson = angular.toJson(listPanierCommande);
					$rootScope.$broadcast('listPanierCommandeSupplied', listPanierCommande);
				}, function(reason) {
					if (reason.status == 404) {
						$rootScope.$broadcast('listPanierCommandeSupplied', '');
					} else {
						$rootScope.$broadcast('anomalieTechnique',reason);
					}
				});
			}

			return {
				setSelectedProduit : setSelectedProduit,
				getSelectedProduit : getSelectedProduit,
				getListePanier : getListePanier,
				resetPanierDapresServeur : resetPanierDapresServeur,
				getSuggestedRecette : getSuggestedRecette,
				changeProduit : changeProduit,
				getIdPanierServer : getIdPanierServer,
				getPanierQuantite : getPanierQuantite,
				supprimeArticlePanier : supprimeArticlePanier,
				getMontantTotal : getMontantTotal,
				getPanierCommande : getPanierCommande
			};
		} ]);