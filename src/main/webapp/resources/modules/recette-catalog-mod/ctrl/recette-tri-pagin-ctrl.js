eComBioApp.controller('RecetteTriPaginCtrl', [
		'$scope',
		'$window',
		'searchRecetteSvc',
		function($scope, $window, searchRecetteSvc) {
			$scope.pageMax=0;
			$scope.listeTris = searchRecetteSvc.getlisteTris();
			$scope.currentPage = searchRecetteSvc.getCurrentPage();
			$scope.currentTri = searchRecetteSvc
					.getCurrentTri();
			
			$scope.$watch('currentTri', function() {
				searchRecetteSvc.setCurrentTri($scope.currentTri);
			});

			$scope.isPagedownActive = function() {
				return ($scope.currentPage != 0);
			};

			$scope.pagedown = function() {
				$scope.currentPage = searchRecetteSvc.pagedown();
			};

			$scope.pageup = function() {
				if($scope.currentPage < ($scope.pageMax-1)){
					$scope.currentPage = searchRecetteSvc.pageup();
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