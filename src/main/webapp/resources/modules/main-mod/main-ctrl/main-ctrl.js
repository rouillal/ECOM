eComBioApp.controller('MainCtrl', [ '$scope', '$window','productSvc','categorieSvc','panierSvc', 'userInfoSvc','cookieStoreSvc',
                                    function($scope, $window,productSvc,categorieSvc,panierSvc,userInfoSvc,cookieStoreSvc) {
	$scope.montantPanier = panierSvc.getMontantTotal();
	$scope.user = userInfoSvc.getUserInfoPrenom();
	$scope.anomalieTechnique = "";
	$scope.userInfo=userInfoSvc.getUserInfo();
	$scope.isGestion = userInfoSvc.isGestion();
	$scope.deconnect = function() {
		userInfoSvc.deconnect();
	}
	
	$scope.isUserDefined = function(){
		return $scope.user.length > 0;
	}
	
	$scope.isAdmin = userInfoSvc.isAdmin();
	
	$scope.$on('rafraichirPanier', function(event,listePanierParam,montantTotalParam) {
		$scope.montantPanier = panierSvc.getMontantTotal();
	});
	
	$scope.$on('userConnectionChanged', function(event) {
		$scope.isGestion = userInfoSvc.isGestion();
		$scope.isAdmin = userInfoSvc.isAdmin();
		$scope.user = userInfoSvc.getUserInfoPrenom();
		$scope.userInfo=userInfoSvc.getUserInfo();
	});
	
	$scope.$on('anomalieTechnique', function(event, msg) {
		$scope.anomalieTechnique = msg;
	});
	
}]);

eComBioApp.filter('unsafe', function($sce) { return $sce.trustAsHtml; });
