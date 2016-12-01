eComBioApp.factory('commandSvc', [ '$rootScope', 'restBackendSvc', '$window','panierSvc',
		function($rootScope,restBackendSvc,$window,panierSvc) {
	
			var commandInfo = {'nom':'Dupont','prenom':'Jean','mail':'dupont@gmail.com','livDom':'e','adresse':'17 Rue des Marguerites','cp':'38000','ville':'Grenoble','date':'17/12/2016','heure':'5'};
			var commandPaieInfo = {'num':'1234567891234567','mois':'5','annee':'2017','codeVerif':'789'};
			
			var getCommandInfo = function() {
				return commandInfo;
			};
			
			var getCommandPaieInfo = function() {
				return commandPaieInfo;
			};
			
			var validePaiement = function() {
				var commandInfoJson = angular.toJson(commandInfo);
				var commandPaieInfoJson = angular.toJson(commandPaieInfo);
				var idPanier = panierSvc.getIdPanierServer();
				var messageServeur = new Object();
				messageServeur['idPanier'] = idPanier;
				messageServeur['commandInfo'] = commandInfo;
				messageServeur['commandPaieInfo'] = commandPaieInfo;
				var messageServeurJson = angular.toJson(messageServeur);
				$window.alert("messageServeurJson : "+messageServeurJson);
				restBackendSvc.createItem('paiement', messageServeurJson).then(
							function(data) {
								$window.alert("RRRRTT ");
								$rootScope.$broadcast('recapAEditer');
							}, function(error) {
								var ff = angular.toJson(error);
								$window.alert("Problème de paiement : "+ff);
							});
			}
				

			return {
				getCommandInfo : getCommandInfo,
				getCommandPaieInfo : getCommandPaieInfo,
				validePaiement : validePaiement
			};
		} ]);