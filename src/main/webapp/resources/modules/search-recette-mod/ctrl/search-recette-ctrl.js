eComBioApp.controller('SearchRecetteCtrl', [ '$scope','$window','searchRecetteSvc',function($scope,$window,searchRecetteSvc) {
	$scope.listCategoriesRecette = searchRecetteSvc.getListCategoriesRecette();
	$scope.listCategoriesRecetteChoix=searchRecetteSvc.getListCategoriesRecetteChoix();
	$scope.listSaisons = searchRecetteSvc.getListSaison();
	$scope.listSaisonChoix=searchRecetteSvc.getListSaisonChoix();
	
	$scope.listCompositions = searchRecetteSvc.getListComposition();
	$scope.listCompositionChoix=searchRecetteSvc.getListCompositionChoix();
	
	$scope.searchRecetteString = searchRecetteSvc.getSearchRecetteString();
	$scope.debug = 'Url Rest Search à venir';
	
	$scope.changeCategorieRecette = function() {
		searchRecetteSvc.changeListCategoriesRecetteChoix($scope.listCategoriesRecetteChoix);
	}
	
	$scope.changeSaison = function() {
		searchRecetteSvc.changeListSaisonChoix($scope.listSaisonChoix);
	}
	
	$scope.changeComposition = function() {
		searchRecetteSvc.changeListCompositionChoix($scope.listCompositionChoix);
	}
	
	$scope.searchRecetteByName  = function() {
		searchRecetteSvc.setSearchRecetteString($scope.searchRecetteString);
	}
	
	$scope.$on('listCategorieRecettesCritSupplied', function(event) {
		$scope.listCategoriesRecette = searchRecetteSvc.getListCategoriesRecette();
		$scope.listCategoriesRecetteChoix=searchRecetteSvc.getListCategoriesRecetteChoix();
	});
	
	$scope.$on('listSaisonsCritSupplied', function(event) {
		$scope.listSaisons = searchRecetteSvc.getListSaison();
		$scope.listSaisonChoix=searchRecetteSvc.getListSaisonChoix();
	});
	
	$scope.$on('listCompositionsCritSupplied', function(event) {
		$scope.listCompositions = searchRecetteSvc.getListComposition();
		$scope.listCompositionChoix=searchRecetteSvc.getListCompositionChoix();
	});
	
	$scope.$on('debug', function(event,message) {
		$scope.debug = message;
	});
} ]);