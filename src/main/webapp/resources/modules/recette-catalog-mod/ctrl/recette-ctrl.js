eComBioApp.controller('RecetteCtrl', [ '$scope','$window','searchRecetteSvc','recetteSvc', function($scope,$window,searchRecetteSvc,recetteSvc) {
	
	$scope.listRecettes = searchRecetteSvc.getRecettesInit();
	$scope.selectedRecette='';
	$scope.listProduitRecette=[];
	
	$scope.selectDetailsRecette = function(selectedRecetteParam) {
		//panierSvc.setSelectedRecette(selectedRecetteParam);
		$scope.selectedRecette=selectedRecetteParam;
		recetteSvc.getDetailsRecette();
		var messageRecetteJson = angular.toJson(selectedRecetteParam);
		//$window.alert("recette : "+messageRecetteJson);
	}
	
	$scope.isSelectRecette = function() {
		return $scope.selectedRecette != "";
	}
	
	$scope.$on('listRecettesSupplied', function(event,listRecettesReceived) {
		$scope.listRecettes = listRecettesReceived;
	});
} ]);