eComBioApp.controller('RecetteCtrl', [ '$scope','$window','searchRecetteSvc', function($scope,$window,searchRecetteSvc) {
	
	$scope.listRecettes = searchRecetteSvc.getRecettesInit();
	$scope.selectedRecette='';
	
	$scope.selectDetailsRecette = function(selectedRecetteParam) {
		//panierSvc.setSelectedRecette(selectedRecetteParam);
		$scope.selectedRecette=selectedRecetteParam;
	}
	
	$scope.isSelectRecette = function() {
		return $scope.selectedRecette != "";
	}
	
	$scope.$on('listRecettesSupplied', function(event,listRecettesReceived) {
		$scope.listRecettes = listRecettesReceived;
	});
} ]);