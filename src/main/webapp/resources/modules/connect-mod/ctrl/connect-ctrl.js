eComBioApp.controller('ConnectCtrl', [ '$scope','$window','userInfoSvc', function($scope,$window,userInfoSvc) {
	$scope.errorMessage='';
	$scope.connectInfo={'usrmail':'','psw':''};
	
	$scope.isErrorMessage = function() {
		return $scope.errorMessage != '';
	}
	
	$scope.doConnect = function() {
		userInfoSvc.retrieveUserInfo($scope.connectInfo.usrmail,$scope.connectInfo.psw);
	}
	
	$(window).ready(function(){
	    $("#myBtnConnect").click(function(){
	        $("#myModalConnect").modal();
	    });
	    $("#myBtnConnexion").click(function(){
	        $("#myModalConnect").modal();
	    });
	    $("#myBtnSignin").click(function(){
	        $("#myModalConnect").modal('hide');
	    });
	});
	
	$scope.$on('userNotFound', function(event) {
		$scope.errorMessage = "Identifiant et/ou mot de passe incorrect(s).";
	});
	
} ]);
