eComBioApp.controller('CommandeDetailCtrl', [
		'$scope',
		'$window',
		'panierSvc',
		function($scope, $window, panierSvc) {
			$scope.listePanier = [];
			$scope.selectedCommandeDetail = '';

			$scope.$on('selectDetailsCommandeProvided', function(event,
					newSelectedCommande) {
				$scope.selectedCommandeDetail = newSelectedCommande;
			});
			
			$scope.$on('listPanierCommandeSupplied', function(event,
					listPanierCommande) {
				$window.alert('RRR');
				$scope.listePanier = listPanierCommande;
			});
			
			//Liste des horaires
			$scope.listeHoraires=[];
			for (i = 0; i < 12; i++) {
			    var horaireTmp = new Object();
			    horaireTmp['value'] = i;
			    var h1=i+8;
			    var h2=i+9;
			    horaireTmp['libelle'] = ''+h1+':00 - '+h2+':00';
			    $scope.listeHoraires.push(horaireTmp);
			}
		} ]);