eComBioApp.controller('ProductDetailCtrl', [ '$scope','$window', 'productSvc',function($scope,$window,globalDataSvc,productSvc) {
	$scope.aa='tt';
	$scope.basket='2';
	
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