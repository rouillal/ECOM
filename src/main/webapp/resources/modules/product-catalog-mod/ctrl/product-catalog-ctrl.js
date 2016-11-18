eComBioApp.controller('ProductCatalogCtrl', [ '$scope','$window','searchProductSvc','panierSvc',function($scope,$window,searchProductSvc,panierSvc) {
	$scope.listProduits = searchProductSvc.getProductsInit();
	$scope.selectedProduit='';
	
	$scope.selectDetailsProduit = function(selectedProduitParam) {
		//Correction bug prixTotal non init.
		if (typeof selectedProduitParam.prixTotal == 'undefined') {
			selectedProduitParam['prixTotal']=0;
		}

		panierSvc.setSelectedProduit(selectedProduitParam);
		$scope.selectedProduit=selectedProduitParam;
	}
	
	$scope.isSelectProduct = function() {
		return $scope.searchProductString != "";
	}
	
	$scope.$on('listProductsSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});
	
	$scope.$on('detailsProductSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});
} ]);