eComBioApp.controller('ProduitAdminCtrl', [ '$scope', '$location','$window','userInfoSvc','productSvc',
		function($scope, $location,$window,userInfoSvc,productSvc) {
	
	$scope.visuMode='N';
	$scope.messageInfo="";
	
	$scope.isMessageInfo = function() {
		return ($scope.messageInfo!="");
	}
	
	$scope.closeMessage = function() {
		return $scope.messageInfo = "";
	}
	
	$scope.editedProduct=productSvc.getEmptyItem();
	
	$scope.isAdmin = function() {
		return userInfoSvc.isAdmin();
	}
	
	$scope.createMode = function() {
		$scope.editedProduct=productSvc.getEmptyItem();
		$scope.visuMode='C';
		$scope.messageInfo="";
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
		$scope.messageInfo="";
		productSvc.createProduct($scope.editedProduct);
		$scope.closeToNormal();
	}
	
	$scope.doUpdate = function() {
		$scope.messageInfo="";
		productSvc.updateProduct($scope.editedProduct);
		$scope.closeToNormal();
	}
	
	$scope.$on('updateProductToEdit', function(event,updateProductToEdit) {
		$scope.messageInfo="";
		$scope.editedProduct=updateProductToEdit;
		$scope.visuMode='U';
	});
	
	$scope.$on('createProduitOk', function(event) {
		$scope.messageInfo="Produit créé avec succès !";
	});
	
	$scope.$on('updateProduitOk', function(event) {
		$scope.messageInfo="Produit modifié avec succès !";
	});
} ]);