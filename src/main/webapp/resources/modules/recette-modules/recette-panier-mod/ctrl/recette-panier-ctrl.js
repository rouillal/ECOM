eComBioApp.controller('RecettePanierCtrl', [ '$scope','$window','panierSvc','imgProviderSvc',function($scope,$window,panierSvc,imgProviderSvc) {
	$scope.listeProduitsRecette = [];
	$scope.selectedProduit='';
	
	$scope.selectDetailsProduit = function(selectedProduitParam) {
		panierSvc.setSelectedProduit(selectedProduitParam);
		$scope.selectedProduit=selectedProduitParam;
	}
	
	$scope.isMoinsProduitInactif = function(produitSelect) {
		return !(produitSelect.quotite > 0);
	};
	
	$scope.moinsProduit = function(produitSelect) {
		if (produitSelect.quotite > 0) {
			produitSelect.quotite -= 1;
			panierSvc.changeProduit(produitSelect,
					produitSelect.quotite);
		}
	};

	$scope.isPlusProduitInactif = function(produitSelect) {
		return false;
	};

	$scope.plusProduit = function(produitSelect) {
		produitSelect.quotite += 1;
		panierSvc.changeProduit(produitSelect,
				produitSelect.quotite);
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