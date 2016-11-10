eComBioApp.controller('PanierCtrl', [ '$scope','$window', 'panierSvc', function($scope,$window,panierSvc) {
	$scope.listePanier = panierSvc.getListePanier();
	
	$scope.isMinusProductShown=function() {
		return true;
	};
		
	$scope.minusProduct=function() {
		$window.alert("minusProduct");
	};
	
	$scope.isPlusProductShown=function() {
		return true;
	};
	
	$scope.plusProduct=function() {
		$window.alert("plusProduct");
	};
} ]);