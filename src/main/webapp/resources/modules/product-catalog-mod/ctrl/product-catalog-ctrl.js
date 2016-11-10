eComBioApp.controller('ProductCatalogCtrl', [ '$scope','$window','categorieSvc','productSvc','searchProductCriteriaSvc',function($scope,$window,categorieSvc,productSvc,searchProductCriteriaSvc) {
	$scope.listProduits = productSvc.getAllProducts();
	$scope.listCategories = categorieSvc.getAllCategories();
	$scope.listCategoriesChoix=[false,true,false];
	$scope.searchProductString = searchProductCriteriaSvc.getSearchProductStringCriteria();
	
	$scope.debug = 'R';
	$scope.selectedProduct='';
	$scope.selectDetailsProduct = function(productSelected) {
		$scope.selectedProduct=productSelected;
	}
	
	$scope.isSelectProduct = function() {
		return $scope.searchProductString != "";
	}
	
	$scope.searchProductByCategoChange  = function() {
		//$window.alert('Yo !');
		searchProductCriteriaSvc.searchProductDirectly($scope.listCategories,$scope.listCategoriesChoix);
	}
	
	$scope.searchProductByName  = function() {
		searchProductCriteriaSvc.setSearchProductStringCriteria($scope.searchProductString,$scope.listCategories,$scope.listCategoriesChoix);
	}
	
	$scope.selectProduct  = function(productSelected) {
		$window.alert('Yo !');
		$scope.selectedProduct = productSelected;
	}
	
	//$scope.listProducts = globalDataSvc.getListItems(0, false);
	//$scope.listCategories = globalDataSvc.getListItems(1, false);
	
	$scope.$on('listCategoriesSupplied', function(event,listCategoriesSupplied) {
		$scope.listCategories = listCategoriesSupplied;
	});
	
	$scope.$on('listProductsSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});
	
	$scope.$on('detailsProductSupplied', function(event,listProductsReceived) {
		$scope.listProduits = listProductsReceived;
	});

	$scope.$on('debug', function(event,message) {
		$scope.debug = message;
	});
	
} ]);