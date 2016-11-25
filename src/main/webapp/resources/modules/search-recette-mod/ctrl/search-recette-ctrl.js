eComBioApp.controller('SearchRecetteCtrl', [ '$scope','$window','searchRecetteSvc',function($scope,$window,searchRecetteSvc) {
	$scope.listCategories = searchRecetteSvc.getListCategories();
	$scope.listCategoriesChoix=searchRecetteSvc.getListCategoriesChoix();
	
	$scope.listTypesRecette = searchRecetteSvc.getListTypesRecette();
	$scope.listTypesRecetteChoix=searchRecetteSvc.getListTypesRecetteChoix();
	
	$scope.listSaisons = searchRecetteSvc.getListSaison();
	$scope.listSaisonChoix=searchRecetteSvc.getListSaison();
	
	$scope.listCompositions = searchRecetteSvc.getListComposition();
	$scope.listCompositionChoix=searchRecetteSvc.getListCompositionChoix();
	
	$scope.searchRecetteString = searchRecetteSvc.getSearchString();
	$scope.debug = 'Url Rest Search à venir';
	$scope.isSaison = searchRecetteSvc.getIsSaison();
	
	$scope.changeRecetteCatego = function() {
		searchRecetteSvc.changeListCategoriesChoix($scope.listCategoriesChoix);
	}
	
	$scope.changeTypeRecette = function() {
		//searchRecetteSvc.changeListTypesRecetteChoix($scope.listTypesRecetteChoix);
	}
	
	$scope.changeSaison = function() {
		searchRecetteSvc.changeSaison($scope.isSaison);
	}
	
	$scope.changeComposition = function() {
		//searchRecetteSvc.changeListTypesRecetteChoix($scope.listTypesRecetteChoix);
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