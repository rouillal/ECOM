eComBioApp.controller('MainCtrl', [ '$scope', '$window','productSvc','categorieSvc','panierSvc',
                                    function($scope, $window,productSvc,categorieSvc,panierSvc) {
	$scope.montantPanier = panierSvc.getMontantTotal();
	$scope.anomalieTechnique = "";
	
	$scope.$on('rafraichirPanier', function(event) {
		$scope.montantPanier = panierSvc.getMontantTotal();
	});

	$scope.$on('anomalieTechnique', function(event, msg) {
		$scope.anomalieTechnique = msg;
	});
}])
