eComBioApp.controller('MainCtrl', [ '$scope', '$window', 'restBackendSvc',
		function($scope, $window, restBackendSvc) {
			$scope.listFruits = [ 't', 'tt' ];
			$scope.anomalieTechnique = "";
			
			$scope.$on('anomalieTechnique', function(event, msg) {
				$scope.anomalieTechnique = msg;
			});
			
			restBackendSvc.getItems().then(function(data) {
				$scope.listFruits = data.data;
			});
		} ])
