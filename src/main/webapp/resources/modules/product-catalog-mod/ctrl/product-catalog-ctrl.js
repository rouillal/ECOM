eComBioApp.controller('ProductCatalogCtrl', [ '$scope','$window','searchProductSvc',function($scope,$window,searchProductSvc) {
	$scope.listProduits = searchProductSvc.getProductsInit();
	$scope.selectedProduit='';
	
	$scope.selectDetailsProduit = function(selectedProduitParam) {
		$scope.selectedProduit=selectedProduitParam;
	}
	
	$scope.isSelectProduct = function() {
		return $scope.searchProductString != "";
	}
	
	$scope.selectProduct  = function(productSelected) {
		$scope.selectedProduct = productSelected;
	}
	
	$scope.$on('listProductsSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});
	
	$scope.$on('detailsProductSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});
} ]);