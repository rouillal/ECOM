eComBioApp.controller('ProductCatalogCtrl', [ '$scope','$window','searchProductSvc','panierSvc',function($scope,$window,searchProductSvc,panierSvc) {
	$scope.listProduits = searchProductSvc.getProductsInit();
	$scope.selectedProduit='';
	
	$scope.selectDetailsProduit = function(selectedProduitParam) {
		panierSvc.setSelectedProduit(selectedProduitParam);
		$scope.selectedProduit=selectedProduitParam;
	}
	
	$scope.isSelectProduct = function() {
		return $scope.searchProductString != "";
	}
	
	$scope.$on('listProductsSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});
} ]);