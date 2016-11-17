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
						montantTotal += ligneArticle.px;
					}
				});
				$rootScope.$broadcast('rafraichirPanier');
			};

			var changeProduit = function(produitAChanger, quantite) {
				if (quantite == 0) {
					supprimeArticlePanier(produitAChanger);
				} else {
					var ligne = '';
					montantTotal = 0;
					angular.forEach(listePanier, function(ligneArticle, key) {
						if (produitAChanger.id == ligneArticle.id) {
							ligneArticle.qt = quantite;
							ligneArticle.px = quantite * ligneArticle.prix;
							ligne = ligneArticle;
						}
						montantTotal += ligneArticle.px;
					});
					if (ligne == '') {
						//Le produit à changer n'a pas été trouvé dans la liste, il faut le créer
						var ligneTmp = produitAChanger;
						ligneTmp['qt'] = quantite;
						var price = quantite * produitAChanger.prix;
						ligneTmp['px'] = price;
						listePanier.push(ligneTmp);
						montantTotal += price;
					}
				}
				//$window.alert('ff');
				var panierJson = angular.toJson(listePanier);
				$window.alert('Panier : '+panierJson);
				if (idPanierServer < 0) {
					restBackendSvc.createItem('panier', panierJson).then(
							function(data) {
								$window.alert('ff'+data.data);
								idPanierServer = data.data;
							});
				} else {
					restBackendSvc.updateItem('panier?id='+idPanierServer, panierJson).then(
							function(data) {
								$window.alert('gg');
							});
				}
				
				;
				$rootScope.$broadcast('selectedProduitChange', produitAChanger,
						quantite);
				$rootScope.$broadcast('rafraichirPanier');
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