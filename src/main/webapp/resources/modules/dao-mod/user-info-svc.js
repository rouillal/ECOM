eComBioApp.factory('userInfoSvc', [ '$rootScope', 'restBackendSvc', '$window',
		function($rootScope,restBackendSvc,$window) {
	
			var userInfo = {'nom':'Dupont','prenom':'Jean','mail':'dupont@gmail.com','adresse':'17 Rue des Marguerites','cp':'38000','ville':'Grenoble','psw':'xx'};
			
			var getUserInfo = function() {
				return userInfo;
			};
			
			var valideSaisieUserInfo = function(userInfoParam) {
				userInfo = userInfoParam; 
				var userInfoJson = angular.toJson(userInfo);
				$window.alert("userInfoParam : "+userInfoJson);
				restBackendSvc.createItem('connect', userInfoJson).then(
							function(data) {
								$window.alert("Bravo, vous êtes inscrit chez nous");
							}, function(error) {
								var errorJson = angular.toJson(error);
								$rootScope.$broadcast('debug', errorJson);
								$window.alert('Failed: ' + errorJson);
							});
			}
				
			var retrieveUserInfo = function(mailParam,pswParam) {
				var restAdress = 'connect?mail='+mailParam+'&psw='+pswParam;
				$window.alert('restAdress to connect: ' + restAdress);
				restBackendSvc.getItemsByUrl(restAdress).then(function(data) {
					userInfo = data.data;
					$window.alert("Bravo, vous êtes connecté chez nous");
					$rootScope.$broadcast('userInfoSupplied',userInfo);
				}, function(error) {
					var errorJson = angular.toJson(error);
					$rootScope.$broadcast('debug', errorJson);
					$window.alert('Failed: ' + errorJson);
				});
			};
			
			return {
				getUserInfo : getUserInfo,
				valideSaisieUserInfo : valideSaisieUserInfo,
				retrieveUserInfo : retrieveUserInfo
			};
		} ]);