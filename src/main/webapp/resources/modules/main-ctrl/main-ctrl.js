eComBioApp.controller('MainCtrl', [ '$scope', '$window', 'restBackendSvc',
		function($scope, $window, restBackendSvc) {
			$scope.listProduits = [ 'p', 'pp' ];
			$scope.listCategories = [ 'c', 'cc' ];
			$scope.anomalieTechnique = "";
			
			$scope.$on('anomalieTechnique', function(event, msg) {
				$scope.anomalieTechnique = msg;
			});
			
			restBackendSvc.getItems(0).then(function(data) {
				$scope.listProduits = data.data;
			});
			restBackendSvc.getItems(1).then(function(data) {
				$scope.listCategories = data.data;
			});
		} ])
