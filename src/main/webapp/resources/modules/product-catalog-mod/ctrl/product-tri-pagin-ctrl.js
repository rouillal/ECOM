eComBioApp.controller('ProductTriPaginCtrl', [
		'$scope',
		'$window',
		'searchProductSvc',
		function($scope, $window, searchProductSvc) {
			$scope.aab='tt';
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
				$scope.currentPage = searchProductSvc.pageup();
			};

			$scope.$on('reinitPageDueToNewSearch', function(event,
					newCurrentPage) {
				$scope.currentPage = newCurrentPage;
			});
		} ]);