eComBioApp.controller('MainCtrl', [ '$scope', '$window',
		function($scope, $window) {
			$scope.anomalieTechnique = "";
			
			$scope.$on('anomalieTechnique', function(event, msg) {
				$scope.anomalieTechnique = msg;
			});
		} ])
