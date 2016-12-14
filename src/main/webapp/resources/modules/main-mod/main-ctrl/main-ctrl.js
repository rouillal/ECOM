eComBioApp.controller('MainCtrl', [ '$scope', '$window','productSvc','categorieSvc','panierSvc', 'userInfoSvc','cookieStoreSvc',
                                    function($scope, $window,productSvc,categorieSvc,panierSvc,userInfoSvc,cookieStoreSvc) {
	$scope.montantPanier = panierSvc.getMontantTotal();
	$scope.anomalieTechnique = "";
	$scope.isUserDefined = userInfoSvc.isUserDefined();
	$scope.userInfo=userInfoSvc.getUserInfo();
	$scope.isGestion = userInfoSvc.isGestion();
	$scope.deconnect = function() {
		userInfoSvc.deconnect();
		$scope.isUserDefined = false;
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
		$scope.isUserDefined = userInfoSvc.isUserDefined();
	});
	
	$scope.$on('anomalieTechnique', function(event, msg) {
		$scope.anomalieTechnique = msg;
	});
	
}]);

eComBioApp.filter('unsafe', function($sce) { return $sce.trustAsHtml; });
