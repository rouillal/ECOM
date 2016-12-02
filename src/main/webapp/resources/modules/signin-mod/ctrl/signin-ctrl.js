eComBioApp.controller('SigninCtrl', [ '$scope','$window','userInfoSvc', function($scope,$window,userInfoSvc) {
	$scope.signinInfo = {'nom':'Dupont','prenom':'Jean','mail':'dupont@gmail.com','adresse':'17 Rue des Marguerites','cp':'38000','ville':'Grenoble','psw':'xx'};
	$scope.signinPswCheck = 'xx2';
	$scope.erreurSignin='';
	
	$scope.isErrorMessage = function() {
		return $scope.erreurSignin != '';
	}
	
	$scope.doSignIn = function() {
		$scope.erreurSignin='';
		if ($scope.signinInfo.psw == '') {
			$scope.erreurSignin='Mot de passe à renseigner !';
		} else {
			if  ($scope.signinInfo.psw != $scope.signinPswCheck) {
				$scope.erreurSignin='Mot de passe de confirmation erroné !';
			} else {
				if  ($scope.signinInfo.nom == '') {
					$scope.erreurSignin='Nom à renseigner !';
				} else {
					if  ($scope.signinInfo.prenom == '') {
						$scope.erreurSignin='Prénom à renseigner !';
					} else {
						if  ($scope.signinInfo.adresse == '') {
							$scope.erreurSignin='Adresse à renseigner !';
						} else {
							if  ($scope.signinInfo.mail == '') {
								$scope.erreurSignin='Mail à renseigner !';
							} else {
								if  ($scope.signinInfo.cp == '') {
									$scope.erreurSignin='Code postal à renseigner !';
								} else {
									if  ($scope.signinInfo.ville == '') {
										$scope.erreurSignin='Ville à renseigner !';
									} else {
										if  ($scope.signinInfo.mail.indexOf('@') == -1) {
											$scope.erreurSignin='Adresse mail non valide !';
										} else {
											userInfoSvc.valideSaisieUserInfo($scope.signinInfo);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	$(window).ready(function(){
		$("#myBtnSign").click(function(){
			$("#myModalSignin").modal();
		});
		$("#myBtnSignin").click(function(){
			$("#myModalSignin").modal();
		});
		$("#myBtnConnexion").click(function(){
	        $("#myModalSignin").modal('hide');
	    });
	});
	
	$scope.$on('erreurSignin', function(event,messageErreur) {
		$scope.erreurSignin = messageErreur;
	});
	
} ]);