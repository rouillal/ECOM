eComBioApp.controller('ProduitCatalogCtrl', [
		'$scope',
		'$window',
		'searchProductSvc',
		'panierSvc',
		'productSvc',
		'userInfoSvc',
		function($scope, $window, searchProductSvc, panierSvc, productSvc,
				userInfoSvc) {
			$scope.listProduits = searchProductSvc.getProductsInit();
			$scope.selectedProduit = '';
			$scope.isGestion = userInfoSvc.isGestion();
			$scope.isAdmin = userInfoSvc.isAdmin();

			$scope.selectDetailsProduit = function(selectedProduitParam) {
				panierSvc.setSelectedProduit(selectedProduitParam);
				$scope.selectedProduit = selectedProduitParam;
			}

			$scope.updateModeEdit = function(produit) {
				productSvc.updateProductToEdit(produit);
				// panierSvc.setSelectedProduit(selectedProduitParam);
				// $scope.selectedProduit=selectedProduitParam;
			}

			$scope.isSelectProduct = function() {
				return $scope.searchProductString != "";
			}

			$scope.$on('listProductsSupplied', function(event,
					listProductsReceived) {
				$scope.listProduits = listProductsReceived;
			});
			
			$scope.$on('userConnectionChanged', function(event) {
				$scope.isGestion = userInfoSvc.isGestion();
				$scope.isAdmin = userInfoSvc.isAdmin();
			});
		} ]);