eComBioApp.controller('RecettePanierCtrl', [ '$scope','$window','$timeout','panierSvc','imgProviderSvc',function($scope,$window,$timeout,panierSvc,imgProviderSvc) {
	$scope.listeProduitsRecette = [];
	$scope.selectedProduitRecette='';
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

	$scope.selectDetailsProduit = function(selectedProduitParam) {
		panierSvc.setSelectedProduit(selectedProduitParam);
		$scope.selectedProduitRecette=selectedProduitParam;
	}

	$scope.isMoinsProduitInactifRecettePanier = function(produitSelect) {
		return !(produitSelect.quotite > 0);
	};

	$scope.moinsProduitRecettePanier = function(produitSelect) {
		$scope.selectedProduitRecette=produitSelect;
		if (produitSelect.quotite > 0) {
			panierSvc.changeProduit(produitSelect,
					produitSelect.quotite -1);
		}
	};

	$scope.isPlusProduitInactifRecettePanier = function(produitSelect) {
		return false;
	};

	$scope.plusProduitRecettePanier = function(produitSelect) {
		$scope.selectedProduitRecette=produitSelect;
		panierSvc.changeProduit(produitSelect,
				produitSelect.quotite + 1);
	};

	$scope.supprimeLigne = function(produitSelect) {
		var ret = [];
		if (produitSelect.quotite > 0) {
			panierSvc.changeProduit(produitSelect,
					0);
		}
		angular.forEach($scope.listeProduitsRecette, function(produit, key) {
			if (produit.id != produitSelect.id) {
				ret.push(produit);
			}
		});
		$scope.listeProduitsRecette = ret;
	};

	$scope.$on('detailsRecetteSupplied', function(event,detailsRecetteSupplied) {
		$scope.listeProduitsRecette = detailsRecetteSupplied;
		angular.forEach($scope.listeProduitsRecette, function(produit, key) {
			produit.quotite=panierSvc.getPanierQuantite(produit);
			produit['quotite']=panierSvc.getPanierQuantite(produit);
			produit['url']=imgProviderSvc.getImage(produit.filename);
		});
	});

	$scope.$on('selectedProduitChange', function(event,produitAChanger,quantite) {
		angular.forEach($scope.listeProduitsRecette, function(produit, key) {
			if (produit.id == produitAChanger.id) {
				produit.quotite=quantite;
			}
			
		});
	});

	} ]);