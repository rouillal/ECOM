eComBioApp.controller('ProductDetailCtrl', [ '$scope','$window', 'productSvc','panierSvc',function($scope,$window,globalDataSvc,productSvc,panierSvc) {
	$scope.aa='tt';
	$scope.basket='2';
	
	$scope.isMinusProductShown=function(produit) {
		return true;
	};
	
	
	$scope.minusProduct=function(produit) {
		$window.alert("minusProduct");
	};
	
	$scope.isPlusProductShown=function(produit) {
		return true;
	};
	
	$scope.plusProduct=function(produit) {
		$window.alert("plusProduct");
		
	};
	
} ]);