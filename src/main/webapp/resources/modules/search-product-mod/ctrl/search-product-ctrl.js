eComBioApp.controller('SearchProductCtrl', [ '$scope','$window','searchProductSvc',function($scope,$window,searchProductSvc) {
	$scope.listCategories = searchProductSvc.getListCategories();
	$scope.listCategoriesChoix=searchProductSvc.getListCategoriesChoix();
	$scope.searchProductString = searchProductSvc.getSearchString();
	$scope.debug = 'Url Rest Search à venir';
	
	$scope.changeProductCatego = function() {
		searchProductSvc.changeListCategoriesChoix($scope.listCategoriesChoix);
	}
	
	$scope.searchProductByName  = function() {
		searchProductSvc.setSearchString($scope.searchProductString);
	}
	
	$scope.$on('listCategoriesCritSupplied', function(event) {
		$scope.listCategories = searchProductSvc.getListCategories();
		$scope.listCategoriesChoix=searchProductSvc.getListCategoriesChoix();
	});
	
	$scope.$on('debug', function(event,message) {
		$scope.debug = message;
	});
} ]);