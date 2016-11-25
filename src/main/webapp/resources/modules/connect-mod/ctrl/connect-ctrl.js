eComBioApp.controller('ConnectCtrl', [ '$scope','$window','userInfoSvc', function($scope,$window,userInfoSvc) {
	$scope.connectInfo={'usrmail':'x@adress.com','psw':'A saisir'};
	
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
} ]);
