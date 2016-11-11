eComBioApp.controller('MainCtrl', [ '$scope', '$window','categorieSvc','productSvc','panierSvc',
		function($scope, $window) {
			$scope.anomalieTechnique = "";
			
			$scope.$on('anomalieTechnique', function(event, msg) {
				$scope.anomalieTechnique = msg;
			});
		} ])
