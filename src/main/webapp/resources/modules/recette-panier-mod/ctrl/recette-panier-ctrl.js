eComBioApp.controller('RecettePanierCtrl', [ '$scope','$window','panierSvc',function($scope,$window,panierSvc) {
	$scope.listeProduitsRecette = [];
	
	$scope.isMoinsProduitInactif = function(produitSelect) {
		return !(produitSelect.quotite > 0);
	};
	
	$scope.moinsProduit = function(produitSelect) {
		if (produitSelect.quotite > 0) {
			produitSelect.quotite -= 1;
			panierSvc.changeProduit(produitSelect,
					produitSelect.quotite);
		}
	};

	$scope.isPlusProduitInactif = function(produitSelect) {
		return false;
	};

	$scope.plusProduit = function(produitSelect) {
		produitSelect.quotite += 1;
		panierSvc.changeProduit(produitSelect,
				produitSelect.quotite);
	};
	
	$scope.supprimeLigne = function(produitSelect) {
		var ret = [];
		if (produitSelect.quotite > 0) {
			panierSvc.changeProduit(produitSelect,
					0);
		}
		angular.forEach($scope.listeProduitsRecette, function(produit, key) {
			if (produit.id != produitSelect.id) {
				ret.push(produit);
			}
		});
		$scope.listeProduitsRecette = ret;
	};
	
	$scope.$on('detailsRecetteSupplied', function(event,detailsRecetteSupplied) {
		//$scope.listeProduitsRecette = detailsRecetteSupplied;
		//Mock
		$scope.listeProduitsRecette =[{"id":3,"categorie":{"id":1,"name":"Fruits"},"name":"Abricot","variete":"Orangé de Provence bio","unite":"g","quantite":2000,"stock":22,"prix":5.2,"filename":"AbricotOrange","provenance":"La Ferme des Marmottes","dateCueillette":"16/12/2016","dureeConservation":4,"calories":49,"glucides":9,"fibres":2,"proteines":1,"quotite":0,"prixTotal":0,"url":"http://localhost:8080/ECOM/image?name=AbricotOrange"},{"id":2,"categorie":{"id":1,"name":"Fruits"},"name":"Abricot","variete":"Rouge du Roussillon bio","unite":"g","quantite":1000,"stock":53,"prix":4.16,"filename":"AbricotRoussillon","provenance":"La Ferme à Dédé","dateCueillette":"14/12/2016","dureeConservation":6,"calories":49,"glucides":9,"fibres":2,"proteines":1,"quotite":0,"prixTotal":0,"url":"http://localhost:8080/ECOM/image?name=AbricotRoussillon"},{"id":1,"categorie":{"id":1,"name":"Fruits"},"name":"Abricot","variete":"Bergeron bio","unite":"g","quantite":1000,"stock":78,"prix":7.37,"filename":"AbricotBergeron","provenance":"La Ferme à Dédé","dateCueillette":"15/12/2016","dureeConservation":5,"calories":49,"glucides":9,"fibres":2,"proteines":1,"quotite":0,"prixTotal":0,"url":"http://localhost:8080/ECOM/image?name=AbricotBergeron"},{"id":4,"categorie":{"id":3,"name":"Aromates"},"name":"Ail","variete":"Violet bio","unite":"g","quantite":1000,"stock":78,"prix":3.6,"filename":"AilViolet","provenance":"La Ferme des Marmottes","dateCueillette":"10/12/2016","dureeConservation":180,"calories":131,"glucides":22,"fibres":5,"proteines":8,"quotite":0,"prixTotal":0,"url":"http://localhost:8080/ECOM/image?name=AilViolet"},{"id":5,"categorie":{"id":1,"name":"Fruits"},"name":"Amande","variete":"Avola bio","unite":"g","quantite":200,"stock":121,"prix":2,"filename":"AmandeAvola","provenance":"La Ferme à Dédé","dateCueillette":"15/12/2016","dureeConservation":150,"calories":634,"glucides":1,"fibres":13,"proteines":25,"quotite":0,"prixTotal":0,"url":"http://localhost:8080/ECOM/image?name=AmandeAvola"},{"id":6,"categorie":{"id":1,"name":"Fruits"},"name":"Ananas","variete":"Cayenne bio","unite":"g","quantite":2400,"stock":18,"prix":3.1,"filename":"AnanasCayenne","provenance":"Ferme de Namières","dateCueillette":"15/12/2016","dureeConservation":3,"calories":53,"glucides":11,"fibres":2,"proteines":1,"quotite":0,"prixTotal":0,"url":"http://localhost:8080/ECOM/image?name=AnanasCayenne"}];
		angular.forEach($scope.listeProduitsRecette, function(produit, key) {
			produit.quotite=panierSvc.getPanierQuantite(produit);
			produit['quotite']=panierSvc.getPanierQuantite(produit);
		});
	});
	
} ]);