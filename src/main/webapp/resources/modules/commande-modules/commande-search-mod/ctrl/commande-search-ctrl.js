eComBioApp.controller('CommandeSearchCtrl', [ '$scope','$window','searchCommandeSvc',function($scope,$window,searchCommandeSvc) {
	$scope.listCategories = searchCommandeSvc.getListCategories();
	$scope.listCategoriesChoix=searchCommandeSvc.getListCategoriesChoix();
	$scope.searchCommandeString = searchCommandeSvc.getSearchString();
	$scope.debug = 'Url Rest Search à venir';
	$scope.isSaison = searchCommandeSvc.getIsSaison();
	
	$scope.changeCommandeCatego = function() {
		searchCommandeSvc.changeListCategoriesChoix($scope.listCategoriesChoix);
	}
	
	$scope.changeSaison = function() {
		searchCommandeSvc.changeSaison($scope.isSaison);
	}
	
	$scope.searchCommandeByName  = function() {
		searchCommandeSvc.setSearchString($scope.searchCommandeString);
	}
	
	$scope.$on('listCategoriesCritSupplied', function(event) {
		$scope.listCategories = searchCommandeSvc.getListCategories();
		$scope.listCategoriesChoix=searchCommandeSvc.getListCategoriesChoix();
	});
	
	$scope.$on('debug', function(event,message) {
		$scope.debug = message;
	});
} ]);