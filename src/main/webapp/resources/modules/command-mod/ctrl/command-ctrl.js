eComBioApp.controller('CommandCtrl', [ '$scope', '$location','$window','commandSvc','panierSvc',
		function($scope, $location,$window,commandSvc,panierSvc) {
	$scope.commandInfo = commandSvc.getCommandInfo();
	$scope.commandPaieInfo = commandSvc.getCommandPaieInfo();
	$scope.payerInfoVoir = false;
	$scope.montantTotal = panierSvc.getMontantTotal();
	//Liste des horaires
	$scope.listeHoraires=[];
	for (i = 0; i < 12; i++) {
	    var horaireTmp = new Object();
	    horaireTmp['value'] = i;
	    var h1=i+8;
	    var h2=i+9;
	    horaireTmp['libelle'] = ''+h1+':00 - '+h2+':00';
	    $scope.listeHoraires.push(horaireTmp);
	}
	//Mois de paiement
	$scope.listeMois=[];
	for (i = 1; i < 13; i++) {
	    var moisTmp = new Object();
	    moisTmp['value'] = i;
	    moisTmp['libelle'] = ''+i;
	    $scope.listeMois.push(moisTmp);
	}
	//Années pour paiement 
	$scope.listeAnnees=[];
	for (i = 2016; i < 2036; i++) {
	    var anneeTmp = new Object();
	    anneeTmp['value'] = i;
	    anneeTmp['libelle'] = ''+i;
	    $scope.listeAnnees.push(anneeTmp);
	}
	
	$scope.payerInfo = function() {
		$scope.payerInfoVoir = true;
	}
	
	$scope.revenirCatalog = function() {
		$location.path("#/catalog");
		$scope.payerInfoVoir = true;
	}
	
	//angular.toJson(listePanier);
} ]);