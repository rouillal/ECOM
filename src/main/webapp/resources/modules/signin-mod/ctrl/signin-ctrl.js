eComBioApp.controller('SigninCtrl', [ '$scope','$window','userInfoSvc', function($scope,$window,userInfoSvc) {
	$scope.signinInfo = {'nom':'Dupont','prenom':'Jean','mail':'dupont@gmail.com','adresse':'17 Rue des Marguerites','cp':'38000','ville':'Grenoble','psw':'xx'};
	$scope.signinPswCheck = 'xx2';
	
	$scope.doSignIn = function() {
		if ($scope.signinInfo.psw == '') {
			$window.alert('Mot de passe à renseigner !');
		} else {
			if  ($scope.signinInfo.psw != $scope.signinPswCheck) {
				$window.alert('Mot de passe de confirmation erronné !');
			} else {
				userInfoSvc.valideSaisieUserInfo($scope.signinInfo);
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
} ]);