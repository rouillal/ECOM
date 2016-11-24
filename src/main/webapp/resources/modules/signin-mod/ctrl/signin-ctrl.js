eComBioApp.controller('SigninCtrl', [ '$scope','$window', function($scope,$window) {
	$(window).ready(function(){
		$("#myBtnSign").click(function(){
			$("#myModalSignin").modal();
		});
		$("#myBtnConnexion").click(function(){
	        $("#myModalSignin").modal('hide');
	    });
	});
} ]);