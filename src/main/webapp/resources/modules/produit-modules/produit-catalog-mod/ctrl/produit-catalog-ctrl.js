eComBioApp.controller('ProduitCatalogCtrl', [ '$scope','$window','searchProductSvc','panierSvc','productSvc',function($scope,$window,searchProductSvc,panierSvc,productSvc) {
	$scope.listProduits = searchProductSvc.getProductsInit();
	$scope.selectedProduit='';
	
	$scope.selectDetailsProduit = function(selectedProduitParam) {
		panierSvc.setSelectedProduit(selectedProduitParam);
		$scope.selectedProduit=selectedProduitParam;
	}
	
	$scope.updateModeEdit = function(produit) {
		productSvc.updateProductToEdit(produit);
		//panierSvc.setSelectedProduit(selectedProduitParam);
		//$scope.selectedProduit=selectedProduitParam;
	}
	
	$scope.isSelectProduct = function() {
		return $scope.searchProductString != "";
	}
	
	$scope.$on('listProductsSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});
} ]);