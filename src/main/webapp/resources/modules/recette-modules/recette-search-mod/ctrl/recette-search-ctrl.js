eComBioApp.controller('RecetteSearchCtrl', [ '$scope','$window','recetteSearchSvc',function($scope,$window,recetteSearchSvc) {
	$scope.listCategoriesRecette = recetteSearchSvc.getListCategoriesRecette();
	$scope.listCategoriesRecetteChoix=recetteSearchSvc.getListCategoriesRecetteChoix();
	$scope.listSaisons = recetteSearchSvc.getListSaison();
	$scope.listSaisonChoix=recetteSearchSvc.getListSaisonChoix();
	
	$scope.listCompositions = recetteSearchSvc.getListComposition();
	$scope.listCompositionChoix=recetteSearchSvc.getListCompositionChoix();
	
	$scope.searchRecetteString = recetteSearchSvc.getSearchRecetteString();
	$scope.debug = '';
	
	$scope.changeCategorieRecette = function() {
		recetteSearchSvc.changeListCategoriesRecetteChoix($scope.listCategoriesRecetteChoix);
	}
	
	$scope.changeSaison = function() {
		recetteSearchSvc.changeListSaisonChoix($scope.listSaisonChoix);
	}
	
	$scope.changeComposition = function() {
		recetteSearchSvc.changeListCompositionChoix($scope.listCompositionChoix);
	}
	
	$scope.searchRecetteByName  = function() {
		recetteSearchSvc.setSearchRecetteString($scope.searchRecetteString);
	}
	
	$scope.$on('listCategorieRecettesCritSupplied', function(event) {
		$scope.listCategoriesRecette = recetteSearchSvc.getListCategoriesRecette();
		$scope.listCategoriesRecetteChoix=recetteSearchSvc.getListCategoriesRecetteChoix();
	});
	
	$scope.$on('listSaisonsCritSupplied', function(event) {
		$scope.listSaisons = recetteSearchSvc.getListSaison();
		$scope.listSaisonChoix=recetteSearchSvc.getListSaisonChoix();
	});
	
	$scope.$on('listCompositionsCritSupplied', function(event) {
		$scope.listCompositions = recetteSearchSvc.getListComposition();
		$scope.listCompositionChoix=recetteSearchSvc.getListCompositionChoix();
	});
	
	$scope.$on('debug', function(event,message) {
		//$scope.debug = message;
	});
} ]);