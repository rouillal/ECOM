eComBioApp.controller('RecetteTriPaginCtrl', [
		'$scope',
		'$window',
		'recetteSearchSvc',
		function($scope, $window, recetteSearchSvc) {
			$scope.pageMax=0;
			$scope.listeTris = recetteSearchSvc.getlisteTris();
			$scope.currentPage = recetteSearchSvc.getCurrentPage();
			$scope.currentTri = recetteSearchSvc
					.getCurrentTri();
			
			$scope.$watch('currentTri', function() {
				recetteSearchSvc.setCurrentTri($scope.currentTri);
			});

			$scope.isPagedownActive = function() {
				return ($scope.currentPage != 0);
			};

			$scope.pagedown = function() {
				$scope.currentPage = recetteSearchSvc.pagedown();
			};

			$scope.pageup = function() {
				if($scope.currentPage < ($scope.pageMax-1)){
					$scope.currentPage = recetteSearchSvc.pageup();
				}
			};
			
			$scope.isPageupActive = function() {
				return ($scope.currentPage < $scope.pageMax);
			};

			$scope.$on('reinitPageDueToNewSearch', function(event,
					newCurrentPage) {
				$scope.currentPage = newCurrentPage;
			});
			
			$scope.$on('pageMaxRecetteReset', function(event,
					newPageMax) {
				$scope.pageMax = newPageMax;
			});
		} ]);