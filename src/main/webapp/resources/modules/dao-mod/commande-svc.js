eComBioApp.factory('commandeSvc', [ '$rootScope', 'restBackendSvc', '$window','panierSvc',
                                   function($rootScope,restBackendSvc,$window,panierSvc) {

	var commandInfo = {'nom':'','prenom':'','mail':'','livDom':'e','adresse':'','cp':'','ville':'','date':'','heure':'5'};
	var commandPaieInfo = {'num':'1234567891234567','mois':'5','annee':'2017','codeVerif':'789'};
	var selectedCommande = '';

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
						panierSvc.resetPanier();
						$rootScope.$broadcast('recapAEditer');
				}, function(error) {
					if (error.status == 403) {
						$rootScope.$broadcast('errorDateExpi');
					} else {
						/*var errorJson = angular.toJson(error);
						$rootScope.$broadcast('anomalieTechnique', errorJson);
						$window.alert('Failed: ' + errorJson);*/
					}
				});
	}
	
	var boolToInt = function(boolCond) {
		var ret=1;
		if (!boolCond) {
			ret=0;
		}
		return ret;
	}
	
	var getCommandesByDateLivraison = function(searchDateLivraison,searchEnt,searchDom,page,tri) {
		var restAdress = "?";//"/filter?";
		if (searchDateLivraison != '') {
			restAdress += 'date=' + searchDateLivraison;
		}
		restAdress += '&ent=' + boolToInt(searchEnt);
		restAdress += '&dom=' + boolToInt(searchDom);
		restAdress += '&page=' + page;
		//restAdress += '&tri=' + tri;
		$rootScope.$broadcast('debug', restAdress);
		restBackendSvc.getItemsByUrl('admin/commande'+restAdress).then(function(data) {
			var listCommandes = data.data;
			$rootScope.$broadcast('listCommandesSupplied',listCommandes);
		}, function(reason) {
			if (reason.status == 404) {
				$rootScope.$broadcast('listCommandesSupplied', '');
			} else {
				$rootScope.$broadcast('debug', reason);
				alert('Failed: ' + reason);
			}
		});
		restBackendSvc.getItemsByUrl('admin/commande/page'+restAdress).then(function(data) {
			var pageMax = data.data;
			$rootScope.$broadcast('pageMaxCommandeReset',pageMax);
		}, function(reason) {
			$rootScope.$broadcast('debug', reason);
			alert('Failed: ' + reason);
		});
	}
	
	var setSelectedCommande = function(selectedCommandeParam) {
		selectedCommande=selectedCommandeParam;
		$rootScope.$broadcast('selectDetailsCommandeProvided', selectedCommande);
	}
	
	var changeLivraisonStatut= function(commandeChanged) {
		//var infoJson = angular.toJson(commandeChanged);
		var restAdress = "admin/commande?livree="+boolToInt(commandeChanged.delivred);
		restAdress += '&id=' + commandeChanged.id;
		$rootScope.$broadcast('debug', restAdress);
		restBackendSvc.updateItem(restAdress,commandeChanged).then(function(data) {
			
		}, function(reason) {
			if (reason.status == 404) {
				$rootScope.$broadcast('listCommandesSupplied', '');
			} else {
				$rootScope.$broadcast('debug', reason);
				alert('Failed: ' + reason);
			}
		});
	}

	return {
		getCommandInfo : getCommandInfo,
		setCommandInfo : setCommandInfo,
		getCommandPaieInfo : getCommandPaieInfo,
		validePaiement : validePaiement,
		getCommandesByDateLivraison : getCommandesByDateLivraison,
		setSelectedCommande : setSelectedCommande,
		changeLivraisonStatut : changeLivraisonStatut
	};
} ]);