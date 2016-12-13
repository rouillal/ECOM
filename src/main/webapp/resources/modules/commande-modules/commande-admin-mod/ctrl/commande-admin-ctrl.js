eComBioApp.controller('CommandeAdminCtrl', [
		'$scope',
		'$window',
		'commandeSearchSvc',
		'commandeSvc',
		'userInfoSvc',
		'panierSvc',
		function($scope, $window, commandeSearchSvc, commandeSvc, userInfoSvc,panierSvc) {
			$scope.listCommandes = commandeSearchSvc.getCommandesInit();
			/*
			 * [{'nom':'DD','prenom':'es','mail':'f@o','livDom':'e','adresse':'89
			 * mm','cp':'649','ville':'F','date':'15/09/2016','heure':'5'},
			 * {'nom':'VV','prenom':'vb','mail':'f@o','livDom':'e','adresse':'45
			 * kkl m','cp':'654','ville':'C','date':'12/08/2016','heure':'6'}];
			 */
			
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
			
			$scope.selectedCommande = '';
			$scope.isGestion = userInfoSvc.isGestion();
			$scope.isAdmin = userInfoSvc.isAdmin();
			$scope.selectedCommande = '';

			$scope.selectDetailsCommande = function(selectedCommandeParam) {
				commandeSvc.setSelectedCommande(selectedCommandeParam);
				$scope.selectedCommande = selectedCommandeParam;
				panierSvc.getPanierCommande(selectedCommandeParam.panier.id);
			}

			$scope.changeLivraisonStatut = function(selectedCommandeParam) {
				commandeSvc.changeLivraisonStatut(selectedCommandeParam);
				// $scope.selectedCommande=selectedCommandeParam;
			}

			$scope.isSelectProduct = function() {
				return $scope.searchProductString != "";
			}

			$scope.$on('listCommandesSupplied', function(event,
					listCommandesReceived) {
				$scope.listCommandes = listCommandesReceived;
			});

			$scope.$on('userConnectionChanged', function(event) {
				$scope.isGestion = userInfoSvc.isGestion();
				$scope.isAdmin = userInfoSvc.isAdmin();
			});
		} ]);