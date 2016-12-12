eComBioApp.factory('userInfoSvc', [ '$rootScope', 'restBackendSvc', '$window','commandeSvc',
                                    function($rootScope,restBackendSvc,$window,commandeSvc) {

	var userInfo = {'nom':'','prenom':'','mail':'biotobealive@gmail.com','adresse':'17 Rue des Marguerites','cp':'38000','ville':'Grenoble','psw':'xx'};
	
	var getInfoInit = function() {
		return userInfo;
	};
	
	var getUserInfo = function() {
		return userInfo;
	};
	
	var getUserInfoPrenom = function() {
		return userInfo.prenom;
	};

	var valideSaisieUserInfo = function(userInfoParam) {
		userInfo = userInfoParam; 
		var userInfoJson = angular.toJson(userInfo);
		restBackendSvc.createItem('connect', userInfoJson).then(
				function(data) {
					//var ff = angular.toJson(data.data);
					//$window.alert("Bravo, vous êtes inscrit chez nous"+ff);
					commandeSvc.setCommandInfo(userInfo);
					$rootScope.$broadcast('userInfoProvided');
					$("#myModalSignin").modal('hide');
				}, function(error) {
					if (error.status == 403) {
						$rootScope.$broadcast('userAlreadyExist');
					} else {
						/*var errorJson = angular.toJson(error);
						$rootScope.$broadcast('anomalieTechnique', errorJson);
						$window.alert('Failed: ' + errorJson);*/
					}
				});
	};

	var retrieveUserInfo = function(mailParam,pswParam) {
		var restAdress = 'connect?mail='+mailParam+'&psw='+pswParam;
		restBackendSvc.getItemsByUrl(restAdress).then(function(data) {
			userInfo = data.data;
			commandeSvc.setCommandInfo(userInfo);
			$rootScope.$broadcast('userConnectionChanged');
			$("#myModalConnect").modal('hide');
			$rootScope.$broadcast('userInfoProvided');
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
	
	var isAdmin = function() {
		return userInfo.nom=='Dupont';
	};
	
	var isGestion = function() {
		return userInfo.nom=='Dupont';
	};

	return {
		getUserInfo : getUserInfo,
		valideSaisieUserInfo : valideSaisieUserInfo,
		retrieveUserInfo : retrieveUserInfo,
		getUserInfoPrenom : getUserInfoPrenom,
		isAdmin : isAdmin,
		isGestion : isGestion
	};
} ]);