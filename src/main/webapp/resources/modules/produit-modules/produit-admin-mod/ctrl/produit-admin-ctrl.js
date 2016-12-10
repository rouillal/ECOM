eComBioApp.controller('ProduitAdminCtrl', [ '$scope', '$location','$window','userInfoSvc','productSvc',
		function($scope, $location,$window,userInfoSvc,productSvc) {
	
	$scope.visuMode='tt';
	
	$scope.editedProduct={"categorie":{"id":1,"name":"Fruits"},"name":"Abricot","variete":"Orangé de Provence bio","unite":"g","quantite":2000,"stock":22,"prix":5.2,"filename":"AbricotOrange","provenance":"La Ferme des Marmottes","dateCueillette":"16/12/2016","dureeConservation":4,"calories":49,"glucides":9,"fibres":2,"proteines":1,"url":"http://localhost:8080/ECOM/image?name=AbricotOrange"};
	
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
		return ($scope.visuMode=='C');
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
} ]);