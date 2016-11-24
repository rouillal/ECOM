eComBioApp.controller('SearchRecetteCtrl', [ '$scope','$window','searchRecetteSvc',function($scope,$window,searchRecetteSvc) {
	$scope.listCategories = searchRecetteSvc.getListCategories();
	$scope.listCategoriesChoix=searchRecetteSvc.getListCategoriesChoix();
	$scope.searchRecetteString = searchRecetteSvc.getSearchString();
	$scope.debug = 'Url Rest Search à venir';
	$scope.isSaison = searchRecetteSvc.getIsSaison();
	
	$scope.changeRecetteCatego = function() {
		searchRecetteSvc.changeListCategoriesChoix($scope.listCategoriesChoix);
	}
	
	$scope.changeSaison = function() {
		searchRecetteSvc.changeSaison($scope.isSaison);
	}
	
	$scope.searchRecetteByName  = function() {
		searchRecetteSvc.setSearchString($scope.searchRecetteString);
	}
	
	$scope.$on('listCategoriesCritSupplied', function(event) {
		$scope.listCategories = searchRecetteSvc.getListCategories();
		$scope.listCategoriesChoix=searchRecetteSvc.getListCategoriesChoix();
	});
	
	$scope.$on('debug', function(event,message) {
		$scope.debug = message;
	});
} ]);