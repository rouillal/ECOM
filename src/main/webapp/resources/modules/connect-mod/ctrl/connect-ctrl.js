eComBioApp.controller('ConnectCtrl', [ '$scope','$window', function($scope,$window) {
	$(window).ready(function(){
	    $("#myBtnConnect").click(function(){
	        $("#myModalConnect").modal();
	    });
	    $("#myBtnSignin").click(function(){
	        $("#myModalConnect").modal('hide');
	    });
	});
} ]);
