eComBioApp.factory('userInfoSvc', [ '$rootScope', 'restBackendSvc', '$window','commandeSvc','cookieStoreSvc',
                                    function($rootScope,restBackendSvc,$window,commandeSvc,cookieStoreSvc) {

	var userInfo = cookieStoreSvc.getStoredLocalItem('userInfo');
	if (typeof userInfo != 'undefined') {
		userInfo = {'nom':'','prenom':'','mail':'biotobealive@gmail.com','adresse':'17 Rue des Marguerites','cp':'38000','ville':'Grenoble','psw':'xx','typeClient':'n'};
	}
	var userInfoToPersist = {'nom':'','prenom':'','mail':'','adresse':'','cp':'','ville':'','psw':''};
	
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
		userInfoToPersist.nom=userInfoParam.nom;
		userInfoToPersist.prenom=userInfoParam.prenom;
		userInfoToPersist.mail=userInfoParam.mail;
		userInfoToPersist.adresse=userInfoParam.adresse;
		userInfoToPersist.cp=userInfoParam.cp;
		userInfoToPersist.ville=userInfoParam.ville;
		userInfoToPersist.psw=userInfoParam.psw;
		var userInfoJson = angular.toJson(userInfoToPersist);
		restBackendSvc.createItem('connect', userInfoJson).then(
				function(data) {
					var userInfo = angular.toJson(data.data);
					cookieStoreSvc.storeLocalItem('userInfo',userInfo);
					commandeSvc.setCommandInfo(userInfo);
					$rootScope.$broadcast('userConnectionChanged');
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
			cookieStoreSvc.storeLocalItem('userInfo',userInfo);
			commandeSvc.setCommandInfo(userInfo);
			$rootScope.$broadcast('userConnectionChanged');
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
	
	var isAdmin = function() {
		return userInfo.typeClient=='a';
	};
	
	var isGestion = function() {
		return userInfo.typeClient=='a'||userInfo.typeClient=='g';
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