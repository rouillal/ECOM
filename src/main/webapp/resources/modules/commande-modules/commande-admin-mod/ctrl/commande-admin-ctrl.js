eComBioApp.controller('CommandeAdminCtrl', [ '$scope','$window','commandeSearchSvc','commandeSvc',function($scope,$window,commandeSearchSvc,commandeSvc) {
	$scope.listCommandes = commandeSearchSvc.getCommandsInit();
	$scope.selectedCommande='';
	
	$scope.selectDetailsCommande = function(selectedCommandeParam) {
		commandeSvc.setSelectedCommande(selectedCommandeParam);
		$scope.selectedCommande=selectedCommandeParam;
	}
	
	$scope.isSelectProduct = function() {
		return $scope.searchProductString != "";
	}
	
	$scope.$on('listCommandsSupplied', function(event,listCommandsReceived) {
		$scope.listCommandes = listCommandsReceived;
	});
} ]);