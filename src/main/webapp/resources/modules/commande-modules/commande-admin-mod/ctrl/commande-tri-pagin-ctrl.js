eComBioApp.controller('CommandeTriPaginCtrl', [
		'$scope',
		'$window',
		'searchCommandeSvc',
		function($scope, $window, searchCommandeSvc) {
			$scope.pageMax=0;
			$scope.listeTris = searchCommandeSvc.getlisteTris();
			$scope.currentPage = searchCommandeSvc.getCurrentPage();
			$scope.currentTri = searchCommandeSvc
					.getCurrentTri();
			
			$scope.$watch('currentTri', function() {
				searchCommandeSvc.setCurrentTri($scope.currentTri);
			});

			$scope.isPagedownActive = function() {
				return ($scope.currentPage != 0);
			};

			$scope.pagedown = function() {
				$scope.currentPage = searchCommandeSvc.pagedown();
			};

			$scope.pageup = function() {
				if($scope.currentPage < ($scope.pageMax-1)){
					$scope.currentPage = searchCommandeSvc.pageup();
				}
			};
			
			$scope.isPageupActive = function() {
				return ($scope.currentPage < $scope.pageMax);
			};

			$scope.$on('reinitPageDueToNewSearch', function(event,
					newCurrentPage) {
				$scope.currentPage = newCurrentPage;
			});
			
			$scope.$on('pageMaxCommandeReset', function(event,
					newPageMax) {
				$scope.pageMax = newPageMax;
			});
		} ]);