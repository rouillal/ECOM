eComBioApp.controller('PanierCtrl', [ '$scope', '$location', 'panierSvc',
		function($scope, $location, panierSvc) {
			$scope.listePanier = panierSvc.getListePanier();
			$scope.montantTotal = panierSvc.getMontantTotal();

			$scope.isMoinsProduitInactif = function(ligne) {
				return !(ligne.qt > 0);
			};

			$scope.moinsProduit = function(ligne) {
				if (ligne.qt > 0) {
					ligne.qt -= 1;
					panierSvc.changeProduit(ligne, ligne.qt);
				}
			};

			$scope.isPlusProduitInactif = function(ligne) {
				return false;
			};

			$scope.plusProduit = function(ligne) {
				ligne.qt += 1;
				panierSvc.changeProduit(ligne, ligne.qt);
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

			$scope.$on('rafraichirPanier', function(event) {
				$scope.listePanier = panierSvc.getListePanier();
				$scope.montantTotal = panierSvc.getMontantTotal();
			});
		} ]);