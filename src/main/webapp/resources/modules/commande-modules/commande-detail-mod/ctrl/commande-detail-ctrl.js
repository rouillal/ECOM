eComBioApp.controller('CommandeDetailCtrl', [
		'$scope',
		'$window',
		'panierSvc',
		function($scope, $window, panierSvc) {
			$scope.selectedCommandeDetail = '';
			$scope.panierQuantite = 0;
			$scope.panierPrixQt = 0;

			$scope.isMoinsCommandeInactif = function() {
				return !($scope.panierQuantite > 0);
			};
			
			$scope.moinsCommandeDetail = function() {
				if ($scope.panierQuantite > 0) {
					panierSvc.changeCommande($scope.selectedCommandeDetail,
							$scope.panierQuantite - 1);
				} else {
					$scope.panierQuantite = 0;
					$scope.panierPrixQt = 0;
				}
			};

			$scope.isPlusCommandeInactif = function() {
				return false;
			};

			$scope.plusCommandeDetail = function() {
				panierSvc.changeCommande($scope.selectedCommandeDetail,
						$scope.panierQuantite + 1);
			};

			$scope.$on('selectedCommandeChange', function(event,
					newSelectedCommande, qt ) {
				$scope.selectedCommandeDetail = newSelectedCommande;
				$scope.panierQuantite = qt;
				$scope.panierPrixQt = Math.round(qt * newSelectedCommande.prix*100)/100;
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