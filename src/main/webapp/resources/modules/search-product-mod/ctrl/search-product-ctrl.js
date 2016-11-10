eComBioApp.controller('SearchProductCtrl', [ '$scope','$window','categorieSvc','searchProductCriteriaSvc',function($scope,$window,categorieSvc,searchProductCriteriaSvc) {
	$scope.listCategories = categorieSvc.getAllCategories();
	$scope.listCategoriesChoix=[false,true,false];
	$scope.searchProductString = searchProductCriteriaSvc.getSearchProductStringCriteria();
	$scope.debug = 'R';
	
	$scope.searchProductByCategoChange  = function() {
		$scope.debug = $scope.listCategoriesChoix;
		searchProductCriteriaSvc.searchProductDirectly($scope.listCategories,$scope.listCategoriesChoix);
	}
	
	$scope.searchProductByName  = function() {
		$scope.debug = $scope.listCategoriesChoix;
		searchProductCriteriaSvc.setSearchProductStringCriteria($scope.searchProductString,$scope.listCategories,$scope.listCategoriesChoix);
	}
	
	$scope.$on('listCategoriesSupplied', function(event,listCategoriesSupplied) {
		$scope.listCategories = listCategoriesSupplied;
	});
	
	$scope.$on('debug', function(event,message) {
		//$scope.debug = message;
	});
} ]);