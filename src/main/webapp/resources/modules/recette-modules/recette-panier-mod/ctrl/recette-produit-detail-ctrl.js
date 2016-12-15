eComBioApp.controller('RecetteProduitDetailCtrl', [
		'$scope',
		'$window',
		'$timeout',
		'panierSvc',
		function($scope, $window, $timeout, panierSvc) {
			$scope.selectedProduitRecette = panierSvc.getSelectedProduit();
			$scope.panierQuantite = 0;
			$scope.panierPrixQt = 0;
			$scope.errorStock='';
			
			$scope.isErrorMessage = function() {
				return $scope.errorStock != '';
			}
			
			$scope.$on('StockInsuffisant', function(event) {
				$scope.errorStock = "Votre produit n'est plus en stock";
				$timeout(function(){$scope.errorStock = ''}, 5000);
				});
			
			$scope.$on('StockOk', function(event) {
				$scope.errorStock = '';
			});

			$scope.isMoinsProduitInactif = function() {
				return !($scope.panierQuantite > 0);
			};
			
			$scope.moinsProduitRecette = function() {
				if ($scope.panierQuantite > 0) {
					panierSvc.changeProduit($scope.selectedProduitRecette,
							$scope.panierQuantite - 1);
				} else {
					$scope.panierQuantite = 0;
					$scope.panierPrixQt = 0;
				}
			};

			$scope.isPlusProduitInactif = function() {
				return false;
			};

			$scope.plusProduitRecette = function() {
				panierSvc.changeProduit($scope.selectedProduitRecette,
						$scope.panierQuantite + 1);
			};

			$scope.$on('selectedProduitChange', function(event,
					newSelectedProduit, qt ) {
				$scope.errorStock='';
				$scope.selectedProduitRecette = newSelectedProduit;
				$scope.panierQuantite = qt;
				$scope.panierPrixQt = Math.round(qt * newSelectedProduit.prix*100)/100;
			});
			
			
			$(document).ready(function(){
					$('#tooltip').tooltip({title: "Ajouté", trigger: "click"}); 
			});
			
			$('#tooltip').on('shown.bs.tooltip', function () {
				   setTimeout(function () {
				    $('#tooltip').tooltip('hide');
				   }, 1000);
				})
		} ]);