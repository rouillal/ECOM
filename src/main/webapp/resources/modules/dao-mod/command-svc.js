eComBioApp.factory('commandSvc', [ '$rootScope', 'restBackendSvc', '$window',
		function($rootScope, restBackendSvc, $window) {

			var commandInfo = {'nom':'UU','prenom':'UUp','mail':'UU@gm.ll','adresse':'1 rue GG','cp':'49002','ville':'Mope','date':'12/05/2016','heure':16};
			
			var getCommandInfo = function() {
				return commandInfo;
			};

			return {
				getCommandInfo : getCommandInfo
			};
		} ]);