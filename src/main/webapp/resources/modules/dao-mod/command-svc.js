eComBioApp.factory('commandSvc', [ '$rootScope', 'restBackendSvc', '$window','panierSvc',
                                   function($rootScope,restBackendSvc,$window,panierSvc) {

	var commandInfo = {'nom':'','prenom':'','mail':'','livDom':'e','adresse':'','cp':'','ville':'','date':'','heure':'5'};
	var commandPaieInfo = {'num':'1234567891234567','mois':'5','annee':'2017','codeVerif':'789'};

	var getCommandInfo = function() {
		return commandInfo;
	};
	
	var setCommandInfo = function(userInfo) {
		commandInfo.nom = userInfo.nom;
		commandInfo.prenom = userInfo.prenom;
		commandInfo.mail = userInfo.mail;
		commandInfo.adresse = userInfo.adresse;
		commandInfo.cp = userInfo.cp;
		commandInfo.ville = userInfo.ville;
		$rootScope.$broadcast('commandInfoProvided');
	};
	
	var setCommandAdrEntrepot = function() {
		commandInfo.adresse = "15 Chemin de Taillat";
		commandInfo.ville = "Meylan";
		commandInfo.cp = "38240";
	};

	var getCommandPaieInfo = function() {
		return commandPaieInfo;
	};

	var validePaiement = function() {
		if (commandInfo.livDom == "e"){
			setCommandAdrEntrepot();
		}
		var commandInfoJson = angular.toJson(commandInfo);
		var commandPaieInfoJson = angular.toJson(commandPaieInfo);
		var idPanier = panierSvc.getIdPanierServer();
		var messageServeur = new Object();
		messageServeur['idPanier'] = idPanier;
		messageServeur['commandInfo'] = commandInfo;
		messageServeur['commandPaieInfo'] = commandPaieInfo;
		var messageServeurJson = angular.toJson(messageServeur);
		//$window.alert("messageServeurJson : "+messageServeurJson);
		restBackendSvc.createItem('paiement', messageServeurJson).then(
				function(data) {
					if(data.data == "ok"){
						panierSvc.resetPanier();
						$rootScope.$broadcast('recapAEditer');
					}
					else {
						$window.alert("go dans error");
						$rootScope.$broadcast('erreurPaiement',data.data);
					}
				}, function(error) {
					$rootScope.$broadcast('anomalieTechnique',error);
				});
	}

	return {
		getCommandInfo : getCommandInfo,
		setCommandInfo : setCommandInfo,
		getCommandPaieInfo : getCommandPaieInfo,
		validePaiement : validePaiement
	};
} ]);