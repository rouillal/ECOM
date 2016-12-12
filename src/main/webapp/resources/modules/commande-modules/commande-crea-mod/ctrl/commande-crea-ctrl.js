eComBioApp.controller('CommandeCreaCtrl', [ '$scope', '$location','$window','commandeSvc','panierSvc',
		function($scope, $location,$window,commandeSvc,panierSvc) {
	$scope.commandInfo = commandeSvc.getCommandInfo();
	$scope.commandPaieInfo = commandeSvc.getCommandPaieInfo();
	$scope.payerInfoVoir = false;
	$scope.seeCalend = false;
	$scope.recapInfoVoir = false;
	$scope.erreurPaiement='';
	$scope.montantTotal = panierSvc.getMontantTotal();
	var date = new Date();
	$scope.FormDate = ('0' + date.getDate()).slice(-2) + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + date.getFullYear();
	
	$scope.isErrorMessage = function() {
		return $scope.erreurPaiement != '';
	}
	
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
	    if(i<10)
	    	moisTmp['libelle'] = '0'+i;
	    else
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
	
	$scope.formatDate = function (date) {
	    function pad(n) {
	        return n < 10 ? '0' + n : n;
	    }

	    return date && pad(date.getDate())
	        + '/' + pad(date.getMonth() + 1)
	        + '/' + date.getFullYear();
	};

	$scope.parseDate = function (s) {
	    var tokens = /^(\d{2})-(\d{2})-(\d{4})$/.exec(s);

	    return tokens && new Date(tokens[1], tokens[2] - 1, tokens[3]);
	};
	
	$scope.payerInfo = function() {
		$scope.payerInfoVoir = true;
		$scope.recapInfoVoir = false;
	}
	
	$scope.revenirCatalog = function() {
		$location.path("#/catalog");
		$scope.payerInfoVoir = true;
	}
	
	$scope.revenirAccueil = function() {
		$location.path("#/catalog");
		$scope.payerInfoVoir = false;
		$scope.recapInfoVoir = false;
	}
	
	$scope.revenirPanier = function() {
		$location.path("panier");
	}
	
	$scope.validationCmd = function(){
		commandeSvc.validePaiement();
	}
	
	$scope.seeCalendar = function(){
		$scope.seeCalend = true;
	}
	
	$scope.hideCalendar = function(){
		$scope.seeCalend = false;
	}
	
	$scope.$on('errorDateExpi', function(event) {
		$scope.erreurPaiement = "La date d'expiration est dépassée";
	});
	
	$scope.$on('commandInfoProvided', function(event) {
		$scope.commandInfo = commandeSvc.getCommandInfo();
	});
	
	$scope.$on('recapAEditer', function(event) {
		$scope.recapInfoVoir = true;
		$scope.payerInfoVoir = false;
		$scope.erreurPaiement='';
	});
	
	$scope.$on('erreurPaiement', function(event,message) {
		$scope.erreurVoir = true;
		$scope.recapInfoVoir = false;
		$scope.payerInfoVoir = true;
		$scope.erreurPaiement=message;
	});
	
	$(window).ready(function(){
	    $("#myBtnAnnulePaiement").click(function(){
	        $("#myModalPaiement").modal('hide');
	    });
	});
} ]);