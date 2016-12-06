//eComBioApp.controller('RecetteCtrl', [ '$scope','$window','searchRecetteSvc','recetteSvc', function($scope,$window,searchRecetteSvc,recetteSvc) {
eComBioApp.controller('RecetteCtrl', [ '$scope','$window','recetteSvc', function($scope,$window,recetteSvc) {
	$scope.aa='tqut';
	$scope.listRecettes = [{'name':'Tofu en bolo','nbPersonnes':4,'tpsPreparation':15,'tpsCuisson':20,'cout':'Bon marché','difficulte':'Très facile','listeIngredients':'<ul><li>1 oignon,</li><li>2 carottes</li></ul>','preparation':'<ul><li>1.</li><li>2.</li><li>3.</li></ul>'},
		                   {'name':'Tofu2 en bolo','nbPersonnes':4,'tpsPreparation':15,'tpsCuisson':20,'cout':'Bon marché','difficulte':'Très facile','listeIngredients':'1 oignon','preparation':'<ul><li>1.</li><li>2.</li><li>3.</li></ul>'}];
	//$scope.listRecettes = searchRecetteSvc.getRecettesInit();
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