eComBioApp.controller('ConnectCtrl', [ '$scope','$window','userInfoSvc', function($scope,$window,userInfoSvc) {
	$scope.errorMessage='';
	$scope.connectInfo={'usrmail':'biotobealive@gmail.com','psw':'xx'};
	$scope.user = userInfoSvc.getUserInfoPrenom();
	
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
	    //$("#myBtnSignin").click(function(){
	      //  $("#myModalConnect").modal('hide');
	    //});
	});
	
	$scope.$on('userNotFound', function(event) {
		$scope.errorMessage = "Identifiant et/ou mot de passe incorrect(s).";
	});
	
	$scope.$on('userConnectionChanged', function(event) {
		$scope.user = userInfoSvc.getUserInfoPrenom();
	});
	
} ]);
