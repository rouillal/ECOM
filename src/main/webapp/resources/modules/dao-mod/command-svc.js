eComBioApp.factory('commandSvc', [ '$rootScope', 'restBackendSvc', '$window',
		function($rootScope, restBackendSvc, $window) {
	
			var commandInfo = {'nom':'Dupont','prenom':'Jean','mail':'dupont@gmail.com','livDom':'e','adresse':'17 Rue des Marguerites','cp':'38000','ville':'Grenoble','date':'17/12/2016','heure':'5'};
			var commandPaieInfo = {'num':'1234567891234567','mois':'5','annee':'2017','codeVerif':'789'};
			
			var getCommandInfo = function() {
				return commandInfo;
			};
			
			var getCommandPaieInfo = function() {
				return commandPaieInfo;
			};
			
			var validePaiement = function() {
				var commandPaieInfoJson = angular.toJson(commandPaieInfo);
				restBackendSvc.createItem('paiement', commandPaieInfo).then(
							function(data) {
								var commandInfoJson = angular.toJson(commandInfo);
								restBackendSvc.createItem('commande', commandInfoJson).then(
										function(data) {
											$window.alert("Bravo, vous avez passé commande chez nous");
											$rootScope.$broadcast('recapAEditer');
										}, function(error) {
											$window.alert("Problème de prise de la commande");
										});
							}, function(error) {
								$window.alert("Problème de paiement");
							});
			}
				

			return {
				getCommandInfo : getCommandInfo,
				getCommandPaieInfo : getCommandPaieInfo,
				validePaiement : validePaiement
			};
		} ]);