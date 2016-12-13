eComBioApp.controller('PanierCtrl', [ '$scope', '$location','$window','panierSvc',
		function($scope, $location,$window,panierSvc) {
			$scope.listePanier = panierSvc.getListePanier();
			$scope.montantTotal = panierSvc.getMontantTotal();

			$scope.isMoinsProduitInactif = function(ligne) {
				return !(ligne.quotite > 0);
			};

			$scope.moinsProduit = function(ligne) {
				if (ligne.quotite > 0) {
					ligne.quotite -= 1;
					panierSvc.changeProduit(ligne, ligne.quotite);
				}
			};

			$scope.isPlusProduitInactif = function(ligne) {
				return false;
			};

			$scope.plusProduit = function(ligne) {
				ligne.quotite += 1;
				panierSvc.changeProduit(ligne, ligne.quotite);
			};

			$scope.supprimeLigne = function(ligne) {
				panierSvc.supprimeArticlePanier(ligne);
			};

			$scope.isTotalAffiche = function() {
				return ($scope.montantTotal>0);
			}
			
			$scope.isPanierVide = function() {
				return ($scope.listePanier.length==0);
			}
			
			$scope.goNext = function(hash) {
				$location.path(hash);
			}
			
			$scope.revenirCatalog = function() {
				$location.path("#/catalogue");
			}
			
			$scope.passerCmd = function() {
				$location.path("commande");
			}

			$scope.$on('rafraichirPanier', function(event) {
				$window.alert('Panier MAJ !!');
				$scope.listePanier = panierSvc.getListePanier();
				$scope.montantTotal = panierSvc.getMontantTotal();
			});
		} ]);