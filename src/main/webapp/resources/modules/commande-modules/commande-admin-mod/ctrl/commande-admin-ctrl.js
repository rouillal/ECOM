eComBioApp.controller('CommandeAdminCtrl', [ '$scope','$window',function($scope,$window){//'commandeSearchSvc','commandeSvc',function($scope,$window,commandeSearchSvc,commandeSvc) {
	$scope.listCommandes = [{'nom':'DD','prenom':'es','mail':'f@o','livDom':'e','adresse':'89 mm','cp':'649','ville':'F','date':'15/09/2016','heure':'5'},
	                        {'nom':'VV','prenom':'vb','mail':'f@o','livDom':'e','adresse':'45 kkl m','cp':'654','ville':'C','date':'12/08/2016','heure':'6'}];////commandeSearchSvc.getCommandsInit();
	$scope.selectedCommande='';
	
	$scope.selectDetailsCommande = function(selectedCommandeParam) {
		////commandeSvc.setSelectedCommande(selectedCommandeParam);
		$scope.selectedCommande=selectedCommandeParam;
	}
	
	$scope.isSelectProduct = function() {
		return $scope.searchProductString != "";
	}
	
	$scope.$on('listCommandesSupplied', function(event,listCommandesReceived) {
		$scope.listCommandes = listCommandesReceived;
	});
} ]);