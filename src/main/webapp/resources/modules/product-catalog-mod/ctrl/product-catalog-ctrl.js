eComBioApp.controller('ProductCatalogCtrl', [ '$scope','$window','productSvc',function($scope,$window,productSvc) {
	$scope.listProduits = productSvc.getAllProducts();
	$scope.selectedProduct='';
	$scope.selectDetailsProduct = function(productSelected) {
		$scope.selectedProduct=productSelected;
	}
	
	$scope.isSelectProduct = function() {
		return $scope.searchProductString != "";
	}
	
	$scope.selectProduct  = function(productSelected) {
		$window.alert('Yo !');
		$scope.selectedProduct = productSelected;
	}
	
	//$scope.listProducts = globalDataSvc.getListItems(0, false);
	//$scope.listCategories = globalDataSvc.getListItems(1, false);
	
	$scope.$on('listProductsSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});
	
	$scope.$on('detailsProductSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});
} ]);