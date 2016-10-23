eComBioApp.controller('ProductCatalogCtrl', [ '$scope','$window', 'globalDataSvc', function($scope,$window,globalDataSvc) {
	$scope.listProduits = [ 'p', 'pp' ];
	$scope.listCategories = [ 'c', 'cc' ];
	
	$scope.listProduits = globalDataSvc.getListItems(0, false);
	$scope.listCategories = globalDataSvc.getListItems(1, false);
	
	$scope.$on('datasupplied', function(event, rubrique_order) {
		switch (rubrique_order
				.toString()) {
		case '0':
			$scope.listProduits = globalDataSvc.getListItems(0, false);
			break;
		case '1':
			$scope.listProduits = globalDatalSvc.getListItems(0, false);
			break;
		}
	});
} ]);