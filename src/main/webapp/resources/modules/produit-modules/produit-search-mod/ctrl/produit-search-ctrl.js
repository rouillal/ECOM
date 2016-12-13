eComBioApp.controller('SearchProductCtrl', [ '$scope','$window','searchProductSvc',function($scope,$window,searchProductSvc) {
	$scope.listCategories = searchProductSvc.getListCategories();
	$scope.listCategoriesChoix=searchProductSvc.getListCategoriesChoix();
	$scope.searchProductString = searchProductSvc.getSearchString();
	$scope.debug = '';
	$scope.isSaison = searchProductSvc.getIsSaison();
	
	$scope.changeProductCatego = function() {
		searchProductSvc.changeListCategoriesChoix($scope.listCategoriesChoix);
	}
	
	$scope.changeSaison = function() {
		searchProductSvc.changeSaison($scope.isSaison);
	}
	
	$scope.searchProductByName  = function() {
		searchProductSvc.setSearchString($scope.searchProductString);
	}
	
	$scope.$on('listCategoriesCritSupplied', function(event) {
		$scope.listCategories = searchProductSvc.getListCategories();
		$scope.listCategoriesChoix=searchProductSvc.getListCategoriesChoix();
	});
	
	$scope.$on('debug', function(event,message) {
		//$scope.debug = message;
	});
} ]);