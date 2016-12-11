eComBioApp.controller('CommandeDetailCtrl', [
		'$scope',
		'$window',
		'panierSvc',
		function($scope, $window, panierSvc) {
			$scope.selectedCommandeDetail = '';

			$scope.$on('selectDetailsCommandeProvided', function(event,
					newSelectedCommande) {
				$scope.selectedCommandeDetail = newSelectedCommande;
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