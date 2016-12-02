eComBioApp.factory('userInfoSvc', [ '$rootScope', 'restBackendSvc', '$window','commandSvc',
		function($rootScope,restBackendSvc,$window,commandSvc) {
	
			var userInfo = {'nom':'Dupont','prenom':'Jean','mail':'dupont@gmail.com','adresse':'17 Rue des Marguerites','cp':'38000','ville':'Grenoble','psw':'xx'};
			
			var getUserInfo = function() {
				return userInfo;
			};
			
			var valideSaisieUserInfo = function(userInfoParam) {
				userInfo = userInfoParam; 
				var userInfoJson = angular.toJson(userInfo);
				restBackendSvc.createItem('connect', userInfoJson).then(
							function(data) {
								var ff = angular.toJson(data.data);
								$window.alert("Bravo, vous êtes inscrit chez nous"+ff);
								commandSvc.setCommandInfo(userInfo);
								$("#myModalSignin").modal('hide');
							}, function(error) {
								var errorJson = angular.toJson(error);
								$rootScope.$broadcast('anomalieTechnique', errorJson);
								$window.alert('Failed: ' + errorJson);
							});
			}
				
			var retrieveUserInfo = function(mailParam,pswParam) {
				var restAdress = 'connect?mail='+mailParam+'&psw='+pswParam;
				restBackendSvc.getItemsByUrl(restAdress).then(function(data) {
					userInfo = data.data;
					commandSvc.setCommandInfo(userInfo);
					$("#myModalConnect").modal('hide');
				}, function(error) {
					if (error.status == 404) {
						$rootScope.$broadcast('userNotFound');
					} else {
						var errorJson = angular.toJson(error);
						$rootScope.$broadcast('anomalieTechnique', errorJson);
						alert('Failed: ' + errorJson);
					}
				});
			};
			
			return {
				getUserInfo : getUserInfo,
				valideSaisieUserInfo : valideSaisieUserInfo,
				retrieveUserInfo : retrieveUserInfo
			};
		} ]);