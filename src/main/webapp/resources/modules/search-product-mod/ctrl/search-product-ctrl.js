eComBioApp.controller('SearchProductCtrl', [ '$scope','$window','searchProductCriteriaSvc',function($scope,$window,searchProductCriteriaSvc) {
	$scope.listCategories = searchProductCriteriaSvc.getListCategories();
	$scope.listCategoriesChoix=searchProductCriteriaSvc.getListCategoriesChoix();
	$scope.searchProductString = searchProductCriteriaSvc.getSearchProductStringCriteria();
	$scope.debug = 'Url Rest Search à venir';
	
	$scope.changeProductCatego = function() {
		$scope.debug = $scope.listCategoriesChoix;
		searchProductCriteriaSvc.changeListCategoriesChoix($scope.listCategoriesChoix);
	}
	
	$scope.searchProductByName  = function() {
		$scope.debug = $scope.listCategoriesChoix;
		searchProductCriteriaSvc.setSearchProductStringCriteria($scope.searchProductString,$scope.listCategories,$scope.listCategoriesChoix);
	}
	
	$scope.$on('listCategoriesCritSupplied', function(event) {
		$scope.listCategories = searchProductCriteriaSvc.getListCategories();
		$scope.listCategoriesChoix=searchProductCriteriaSvc.getListCategoriesChoix();
	});
	
	$scope.$on('debug', function(event,message) {
		$scope.debug = message;
	});
} ]);