eComBioApp.controller('ProductDetailCtrl', [ '$scope','$window','panierSvc',function($scope,$window,panierSvc) {
	$scope.selectedProduitPanier='';
	$scope.panierQuantite=0;
	
	$scope.isMoinsProduitInactif=function() {
		return !($scope.panierQuantite>0);
	};
	
	$scope.moinsProduit=function() {
		if ($scope.panierQuantite > 0) {
			panierSvc.changeProduit($scope.selectedProduitPanier,$scope.panierQuantite-1);
		}
	};
	
	$scope.isPlusProduitInactif=function() {
		return false;
	};
	
	$scope.plusProduit=function() {
		panierSvc.changeProduit($scope.selectedProduitPanier,$scope.panierQuantite+1);
	};
	
	$scope.$on('selectedProduitChange', function(event,newSelectedProduit,qt) {
		$scope.selectedProduitPanier=newSelectedProduit;
		$scope.panierQuantite=qt;
	});
} ]);