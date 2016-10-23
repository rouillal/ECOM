eComBioApp.controller('ProductCatalogCtrl', [ '$scope','$window', 'globalDataSvc', 'restBackendSvc', function($scope,$window,globalDataSvc,restBackendSvc) {
	$scope.listProduits = [ 'p', 'pp' ];
	$scope.listCategories = [ 'c', 'cc' ];
	
	restBackendSvc.getItems(0).then(function(data) {
		$scope.listProduits = data.data;
	});
	restBackendSvc.getItems(1).then(function(data) {
		$scope.listCategories = data.data;
	});
	
} ]);