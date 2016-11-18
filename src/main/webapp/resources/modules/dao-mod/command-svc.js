eComBioApp.factory('commandSvc', [ '$rootScope', 'restBackendSvc', '$window',
		function($rootScope, restBackendSvc, $window) {

			var commandInfo = {'nom':'UU','prenom':'UUp','mail':'UU@gm.ll','livDom':false,'adresse':'1 rue GG','cp':'49002','ville':'Mope','date':'12/05/2016','heure':7};
			var commandPaieInfo = {'num':'123456789','mois':5,'annee':2016,'codeVerif':'789'};
			
			var getCommandInfo = function() {
				return commandInfo;
			};
			
			var getCommandPaieInfo = function() {
				return commandPaieInfo;
			};

			return {
				getCommandInfo : getCommandInfo,
				getCommandPaieInfo : getCommandPaieInfo
			};
		} ]);