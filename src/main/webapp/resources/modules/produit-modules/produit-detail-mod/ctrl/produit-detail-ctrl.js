eComBioApp.controller('ProduitDetailCtrl', [
		'$scope',
		'$window',
		'panierSvc',
		function($scope, $window, panierSvc) {
			$scope.selectedProduitDetail = '';
			$scope.panierQuantite = 0;
			$scope.panierPrixQt = 0;
			$scope.errorStock='';
			
			$scope.isErrorMessage = function() {
				return $scope.errorStock != '';
			}

			$scope.isMoinsProduitInactif = function() {
				return !($scope.panierQuantite > 0);
			};
			
			$scope.moinsProduitDetail = function() {
				if ($scope.panierQuantite > 0) {
					panierSvc.changeProduit($scope.selectedProduitDetail,
							$scope.panierQuantite - 1);
				} else {
					$scope.panierQuantite = 0;
					$scope.panierPrixQt = 0;
				}
			};

			$scope.isPlusProduitInactif = function() {
				return false;
			};

			$scope.plusProduitDetail = function() {
				panierSvc.changeProduit($scope.selectedProduitDetail,
						$scope.panierQuantite + 1);
			};

			$scope.$on('selectedProduitChange', function(event,
					newSelectedProduit, qt ) {
				$scope.errorStock = '';
				$scope.selectedProduitDetail = newSelectedProduit;
				$scope.panierQuantite = qt;
				$scope.panierPrixQt = Math.round(qt * newSelectedProduit.prix*100)/100;
			});
			
			
			$scope.$on('StockInsuffisant', function(event) {
				$scope.panierQuantite = $scope.panierQuantite - 1 ;
				$scope.errorStock = "Votre produit n'est plus en stock";
			});
			
			$scope.$on('StockOk', function(event) {
				$scope.errorStock = '';
			});
			
			
			
			$(document).ready(function(){
			    $('[data-toggle="tooltip"]').tooltip({trigger: "click"});   
			});
			
			$('[data-toggle="tooltip"]').on('shown.bs.tooltip', function () {
				   setTimeout(function () {
				    $('[data-toggle="tooltip"]').tooltip('hide');
				   }, 500);
				})
		} ]);