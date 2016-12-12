eComBioApp.controller('ProduitTriPaginCtrl', [
		'$scope',
		'$window',
		'searchProductSvc',
		function($scope, $window, searchProductSvc) {
			$scope.pageMax=0;
			$scope.listeTris = searchProductSvc.getlisteTris();
			$scope.currentPage = searchProductSvc.getCurrentPage();
			$scope.currentTri = searchProductSvc
					.getCurrentTri();
			
			$scope.$watch('currentTri', function() {
				searchProductSvc.setCurrentTri($scope.currentTri);
			});

			$scope.isPagedownActive = function() {
				return ($scope.currentPage != 0);
			};

			$scope.pagedown = function() {
				$scope.currentPage = searchProductSvc.pagedown();
			};

			$scope.pageup = function() {
				if($scope.currentPage < ($scope.pageMax-1)){
					$scope.currentPage = searchProductSvc.pageup();
				}
			};
			
			$scope.isPageupActive = function() {
				return ($scope.currentPage < $scope.pageMax);
			};

			$scope.$on('reinitPageDueToNewSearch', function(event,
					newCurrentPage) {
				$scope.currentPage = newCurrentPage;
			});
			
			$scope.$on('changePageCurrent', function(event,
					newCurrentPage) {
				$scope.currentPage = newCurrentPage;
			});
			
			$scope.$on('pageMaxProduitReset', function(event,
					newPageMax) {
				$scope.pageMax = newPageMax;
			});
		} ]);