eComBioApp.controller('MainCtrl', [ '$scope', '$window','productSvc','categorieSvc','panierSvc', 'userInfoSvc','cookieStoreSvc',
                                    function($scope, $window,productSvc,categorieSvc,panierSvc,userInfoSvc,cookieStoreSvc) {
	$scope.montantPanier = panierSvc.getMontantTotal();
	$scope.user = userInfoSvc.getUserInfoPrenom();
	$scope.anomalieTechnique = "";
	
	$scope.$on('rafraichirPanier', function(event) {
		$scope.montantPanier = panierSvc.getMontantTotal();
	});
	
	$scope.$on('userInfoProvided', function(event) {
		$scope.user = userInfoSvc.getUserInfoPrenom();
	});

	$scope.$on('anomalieTechnique', function(event, msg) {
		$scope.anomalieTechnique = msg;
	});
	
}]);

eComBioApp.filter('unsafe', function($sce) { return $sce.trustAsHtml; });
