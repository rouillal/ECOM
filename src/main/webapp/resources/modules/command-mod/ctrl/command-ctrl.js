eComBioApp.controller('CommandCtrl', [ '$scope', '$location','commandSvc',
		function($scope, $location,commandSvc) {
	$scope.commandInfo = commandSvc.getCommandInfo();
	$scope.aa = $scope.commandInfo;
	//angular.toJson(listePanier);
} ]);