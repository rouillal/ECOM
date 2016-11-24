eComBioApp.controller('ProductTriPaginCtrl', [
		'$scope',
		'$window',
		'searchProductSvc',
		function($scope, $window, searchProductSvc) {
			$scope.listeTris = searchProductSvc.getlisteTris();
			$scope.currentPage = searchProductSvc.getCurrentPage();
			$scope.currentTriIndex = searchProductSvc
					.getCurrentTriIndex();

			$scope.changeTri = function(newTriIndex) {
				$window.alert('Ou');//newTriIndex);
				searchProductSvc.setCurrentTriIndex(newTriIndex);
			}

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