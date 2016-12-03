eComBioApp.controller('SigninCtrl', [ '$scope','$window','userInfoSvc', function($scope,$window,userInfoSvc) {
	$scope.signinInfo = {'nom':'Dupont','prenom':'Jean','mail':'dupont@gmail.com','adresse':'17 Rue des Marguerites','cp':'38000','ville':'Grenoble','psw':'xx'};
	$scope.signinPswCheck = 'xx';
	$scope.erreurSignin='';
	$scope.user = userInfoSvc.getUserInfoPrenom();

	$scope.isErrorMessage = function() {
		return $scope.erreurSignin != '';
	}

	$scope.doSignIn = function() {
		$scope.erreurSignin='';
		if  ($scope.signinInfo.psw != $scope.signinPswCheck) {
			$scope.erreurSignin='Mot de passe de confirmation erroné !';
		} 
		else {
			userInfoSvc.valideSaisieUserInfo($scope.signinInfo);
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
	
	$scope.$on('userAlreadyExist', function(event) {
		$scope.erreurSignin = "Ce compte existe déjà.";
	});
	
	$scope.$on('userInfoProvided', function(event) {
		$scope.user = userInfoSvc.getUserInfoPrenom();
	});

} ]);