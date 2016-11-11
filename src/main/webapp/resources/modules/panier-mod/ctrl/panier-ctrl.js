eComBioApp.controller('PanierCtrl', [ '$scope','$window', 'panierSvc', function($scope,$window,panierSvc) {
	$scope.listePanier = panierSvc.getListePanier();
	
	$scope.isMoinsProduitInactif=function(ligne) {
		return !(ligne.qt>0);
	};
	
	$scope.moinsProduit=function(ligne) {
		if (ligne.qt > 0) {
			ligne.qt-=1;
			panierSvc.changeProduit(ligne,ligne.qt);
		}
	};
	
	$scope.isPlusProduitInactif=function(ligne) {
		return false;
	};
	
	$scope.plusProduit=function(ligne) {
		ligne.qt+=1;
		panierSvc.changeProduit(ligne,ligne.qt);
	};
	
	$scope.supprimeLigne=function(ligne) {
		panierSvc.supprimeArticlePanier(ligne);
	};
	
	$scope.$on('rafraichirPanier', function(event) {
		$scope.listePanier = panierSvc.getListePanier();
	});
} ]);