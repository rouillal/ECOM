eComBioApp.controller('ProduitAdminCtrl', [ '$scope', '$location','$window','userInfoSvc','productSvc',
		function($scope, $location,$window,userInfoSvc,productSvc) {
	
	$scope.visuMode='N';
	
	$scope.editedProduct=productSvc.getEmptyItem();
	
	$scope.isAdmin = function() {
		//return true;
		return userInfoSvc.isAdmin();
	}
	
	$scope.createMode = function() {
		$scope.visuMode='C';
	}
	
	$scope.isCreate = function() {
		return ($scope.visuMode=='C');
	}
	
	$scope.isUpdate = function() {
		return ($scope.visuMode=='U');
	}
	
	$scope.hasItemToEdit = function() {
		return ($scope.isCreate()||$scope.isUpdate());
	}
	
	$scope.isCloseDisplay = function() {
		return ($scope.isCreate()||$scope.isUpdate());
	}
	
	$scope.closeToNormal = function() {
		$scope.visuMode='N';
	}
	
	$scope.doCreate = function() {
		productSvc.createProduct($scope.editedProduct);
	}
	
	$scope.doUpdate = function() {
		$scope.erreurSignin='';
		productSvc.updateProduct($scope.editedProduct);
	}
	
	$scope.$on('listProductsSupplied', function(event,listProduitSupplied) {
		//$scope.editedProduct = listProduitSupplied;
	});
	
	$scope.$on('updateProductToEdit', function(event,updateProductToEdit) {
		$scope.editedProduct=updateProductToEdit;
		$scope.visuMode='U';
	});
} ]);