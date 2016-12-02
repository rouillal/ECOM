eComBioApp.controller('RecettePanierCtrl', [ '$scope','$window','panierSvc',function($scope,$window,panierSvc) {
	$scope.listeProduitsRecette = [];
	
	
	var getPanierQuantite = function(produit) {
		angular.forEach(listeProduitsRecette, function(produit, key) {
			produit.quotite=panierSvc.getPanierQuantite(produit);
			produit['quotite']=panierSvc.getPanierQuantite(produit);
		});
		return ret;
	};
	
	
	
	$scope.$on('detailsRecetteSupplied', function(event,detailsRecetteSupplied) {
		$window.alert('Recu detail recette');
		$scope.listeProduitsRecette = detailsRecetteSupplied.listeProduits;
	});
	
} ]);