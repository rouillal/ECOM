eComBioApp.controller('ProductCatalogCtrl', [ '$scope','$window', 'globalDataSvc', 'productSvc','searchProductCriteriaSvc',function($scope,$window,globalDataSvc,productSvc,searchProductCriteriaSvc) {
	$scope.listProducts = productSvc.getAllProducts();
	$scope.listCategories = [ 'c', 'cc' ];
	$scope.searchProductString = searchProductCriteriaSvc.getSearchProductStringCriteria();
	
	$scope.selectedProduct='';
	$scope.selectDetailsProduct = function(productSelected) {
		$scope.selectedProduct=productSelected;
	}
	
	$scope.isSelectProduct = function() {
		return $scope.searchProductString != "";
	}
	
	$scope.searchProductByName  = function() {
		searchProductCriteriaSvc.setSearchProductStringCriteria($scope.searchProductString);
	}
	
	$scope.selectProduct  = function(productSelected) {
		$window.alert('Yo !');
		$scope.selectedProduct = productSelected;
	}
	
	//$scope.listProducts = globalDataSvc.getListItems(0, false);
	//$scope.listCategories = globalDataSvc.getListItems(1, false);
	
	
	$scope.$on('listProductsSupplied', function(event,listProductsReceived) {
		$scope.listProducts = listProductsReceived;
	});
	
	$scope.$on('detailsProductSupplied', function(event,listProductsReceived) {
		$scope.listProducts = listProductsReceived;
	});
	
} ]);