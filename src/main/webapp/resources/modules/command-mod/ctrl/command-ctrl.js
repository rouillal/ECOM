eComBioApp.controller('CommandCtrl', [ '$scope', '$location','$window','commandSvc',
		function($scope, $location,$window,commandSvc) {
	$scope.commandInfo = commandSvc.getCommandInfo();
	$scope.commandPaieInfo = commandSvc.getCommandPaieInfo();
	$scope.payerInfoVoir = false;
	$scope.listeHoraires=[];
	for (i = 0; i < 14; i++) {
	    var horaireTmp = new Object();
	    horaireTmp['id'] = i;
	    var h1=i+6;
	    var h2=i+7;
	    horaireTmp['libelle'] = ''+h1+':00 - '+h2+':00';
	    $scope.listeHoraires.push(horaireTmp);
	}
	$scope.payerInfo = function() {
		$scope.payerInfoVoir = true;
	}
	
	//angular.toJson(listePanier);
} ]);