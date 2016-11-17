eComBioApp.factory('productSvc', [ '$rootScope', 'restBackendSvc', '$window',
		function($rootScope, restBackendSvc, $window) {

			var commandInfo = {'nom':'UU','prenom':'UUp'};
			var getCommandInfo = function() {
				return commandInfo;
			};

			return {
				getCommandInfo : getCommandInfo
			};
		} ]);