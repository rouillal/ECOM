eComBioApp.controller('CommandeSearchCtrl', [ '$scope','$window','commandeSearchSvc',function($scope,$window,commandeSearchSvc) {
	$scope.searchCommandeDate = commandeSearchSvc.getDateLivraison();
	$scope.debug = 'Url2 Rest Search à venir';
	
	$scope.searchCommandeByDate  = function() {
		commandeSearchSvc.changeDateLivraison($scope.searchCommandeDate);
	}
	
	/*$scope.$on('listCategoriesCritSupplied', function(event) {
		$scope.listCategories = searchCommandeSvc.getListCategories();
		$scope.listCategoriesChoix=searchCommandeSvc.getListCategoriesChoix();
	});*/
	
	$scope.$on('debug', function(event,message) {
		$scope.debug = message;
	});
} ]);