eComBioApp.controller('CommandeTriPaginCtrl', [
		'$scope',
		'$window',
		'commandeSearchSvc',
		function($scope, $window, commandeSearchSvc) {
			$scope.pageMax=0;
			$scope.listeTris = commandeSearchSvc.getlisteTris();
			$scope.currentPage = commandeSearchSvc.getCurrentPage();
			$scope.currentTri = commandeSearchSvc
					.getCurrentTri();
			
			$scope.$watch('currentTri', function() {
				commandeSearchSvc.setCurrentTri($scope.currentTri);
			});

			$scope.isPagedownActive = function() {
				return ($scope.currentPage != 0);
			};

			$scope.pagedown = function() {
				$scope.currentPage = commandeSearchSvc.pagedown();
			};

			$scope.pageup = function() {
				if($scope.currentPage < ($scope.pageMax-1)){
					$scope.currentPage = commandeSearchSvc.pageup();
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