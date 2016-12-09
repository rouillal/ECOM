eComBioApp.controller('RecetteDetailCtrl', [
		'$scope',
		'$window',
		'panierSvc',
		function($scope, $window, panierSvc) {
			$scope.selectedProduitPanier = '';
			$scope.panierQuantite = 0;
			$scope.panierPrixQt = 0;
			
			$scope.listeCout=[];
			for (i = 0; i < 3; i++) {
				var coutTmp = new Object();
				coutTmp['value'] = i;
				if (i==0)
					coutTmp['libelle'] = 'Bon marché';
				else if (i==1)
					coutTmp['libelle'] = 'Coûteux';
				else
					coutTmp['libelle'] = 'Onéreux';
				$scope.listeCout.push(coutTmp);
			}
			
			$scope.listeDifficult=[];
			for (i = 0; i < 3; i++) {
				var DiffTmp = new Object();
				coutTmp['value'] = i;
				if (i==0)
					DiffTmp['libelle'] = 'Très facile';
				else if (i==1)
					DiffTmp['libelle'] = 'Facile';
				else if (i==2)
					DiffTmp['libelle'] = 'Moyenne';
				else
					DiffTmp['libelle'] = 'Difficile';
				$scope.listeDifficult.push(DiffTmp);
			}

			$scope.isMoinsProduitInactif = function() {
				return !($scope.panierQuantite > 0);
			};
			
			$scope.moinsProduit = function() {
				if ($scope.panierQuantite > 0) {
					panierSvc.changeProduit($scope.selectedProduitPanier,
							$scope.panierQuantite - 1);
				} else {
					$scope.panierQuantite = 0;
					$scope.panierPrixQt = 0;
				}
			};

			$scope.isPlusProduitInactif = function() {
				return false;
			};

			$scope.plusProduit = function() {
				panierSvc.changeProduit($scope.selectedProduitPanier,
						$scope.panierQuantite + 1);
			};

			$scope.$on('selectedProduitChange', function(event,
					newSelectedProduit, qt ) {
				$scope.selectedProduitPanier = newSelectedProduit;
				$scope.panierQuantite = qt;
				$scope.panierPrixQt = Math.round(qt * newSelectedProduit.prix*100)/100;
			});
		} ]);