eComBioApp.factory('userInfoSvc', [ '$rootScope', 'restBackendSvc', '$window','commandeSvc','cookieStoreSvc',
                                    function($rootScope,restBackendSvc,$window,commandeSvc,cookieStoreSvc) {

	var userInfo = cookieStoreSvc.getStoredLocalItem('userInfo');
	commandeSvc.setCommandInfo(userInfo);
	$rootScope.$broadcast('userConnectionChanged');
	if (typeof userInfo == 'undefined') {
		userInfo = {'nom':'','prenom':'','mail':'biotobealive@gmail.com','adresse':'','cp':'','ville':'','psw':'','typeClient':'n'};
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
					userInfo = data.data;
					cookieStoreSvc.storeLocalItem('userInfo',userInfo);
					commandeSvc.setCommandInfo(userInfo);
					$rootScope.$broadcast('userConnectionChanged');
					$("#myModalSignin").modal('hide');
				}, function(error) {
					if (error.status == 403) {
						$rootScope.$broadcast('userAlreadyExist');
					} else {
						$rootScope.$broadcast('anomalieTechnique',reason);
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
				$rootScope.$broadcast('anomalieTechnique',reason);
			}
		});
	};
	
	var isAdmin = function() {
		return userInfo.typeClient=='a';
	};
	
	var isGestion = function() {
		return userInfo.typeClient=='a'||userInfo.typeClient=='g';
	};
	
	var deconnect = function() {
		userInfo = {'nom':'','prenom':'','mail':'','adresse':'','cp':'','ville':'','psw':'','typeClient':'n'};
		cookieStoreSvc.storeLocalItem('userInfo',userInfo);
		commandeSvc.setCommandInfo(userInfo);
		$rootScope.$broadcast('userConnectionChanged');
	};

	return {
		getUserInfo : getUserInfo,
		valideSaisieUserInfo : valideSaisieUserInfo,
		retrieveUserInfo : retrieveUserInfo,
		getUserInfoPrenom : getUserInfoPrenom,
		isAdmin : isAdmin,
		isGestion : isGestion,
		deconnect : deconnect
	};
} ]);