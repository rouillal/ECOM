eComBioApp.controller('RecetteSuggestedCtrl', [ '$scope', '$location', 'panierSvc','recetteSvc',
		function($scope, $location, panierSvc,recetteSvc) {
	
			$scope.listSuggestedRecettes = [];
			$scope.maxSuggestedRecettePage = 0;
			$scope.pageSuggestedRecette = 0;
			
			if (panierSvc.getIdPanierServer() >= 0) {
				panierSvc.getSuggestedRecette($scope.pageSuggestedRecette);
			}
			
			$scope.suggestedRecetteExist = function() {
				return ($scope.listSuggestedRecettes.length > 0);
			}
			
			$scope.isPageLeftsuggestedRecette = function() {
				return ($scope.maxSuggestedRecettePage > 1);
			}
			
			$scope.pageLeft = function() {
				if ($scope.pageSuggestedRecette > 0) {
					$scope.pageSuggestedRecette -= 1;
				} else {
					$scope.pageSuggestedRecette = $scope.maxSuggestedRecettePage -1;
				}
			}
			
			$scope.isPageRightsuggestedRecette = function() {
				return ($scope.maxSuggestedRecettePage > 1);
			}
			
			$scope.pageRight = function() {
				$scope.pageSuggestedRecette += 1;
				if ($scope.pageSuggestedRecette >= $scope.maxSuggestedRecettePage) {
					$scope.pageSuggestedRecette = 0;
				} 
			}
			
			$scope.selectDetailsRecette = function(selectedRecetteParam) {
				recetteSvc.setSelectDetailsRecette(selectedRecetteParam); 
				recetteSvc.getDetailsRecette(selectedRecetteParam.id);
			}
			
			$scope.$on('listSuggestedRecettesSupplied', function(event,listSuggestedRecettesSent) {
				$scope.listSuggestedRecettes = listSuggestedRecettesSent;
			});
			
			$scope.$on('maxSuggestedRecettesSupplied', function(event,maxSuggestedRecettesSent) {
				$scope.maxSuggestedRecettePage = maxSuggestedRecettesSent;
			});
		} ]);