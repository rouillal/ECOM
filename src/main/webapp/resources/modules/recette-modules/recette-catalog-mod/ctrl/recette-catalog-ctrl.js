eComBioApp.controller('RecetteCatalogCtrl', [ '$scope','$window','recetteSearchSvc','recetteSvc', function($scope,$window,recetteSearchSvc,recetteSvc) {
	$scope.listRecettes = recetteSearchSvc.getRecettesInit();
	$scope.listProduitRecette=[];

	$scope.selectDetailsRecette = function(selectedRecetteParam) {
		//panierSvc.setSelectedRecette(selectedRecetteParam);
		recetteSvc.setSelectDetailsRecette(selectedRecetteParam); // ??
		recetteSvc.getDetailsRecette(selectedRecetteParam.id);
	}

	$scope.listeCout=[];
	for (i = 0; i < 3; i++) {
		var coutTmp = new Object();
		coutTmp['value'] = i;
		if (i==0)
			coutTmp['libelle'] = 'Bon marché';
		else if (i==1)
			coutTmp['libelle'] = 'Coûteux';
		else
			coutTmp['libelle'] = 'Onéreux';
		$scope.listeCout.push(coutTmp);
	}
	
	$scope.listeDifficult=[];
	for (i = 0; i < 3; i++) {
		var DiffTmp = new Object();
		coutTmp['value'] = i;
		if (i==0)
			DiffTmp['libelle'] = 'Très facile';
		else if (i==1)
			DiffTmp['libelle'] = 'Facile';
		else if (i==2)
			DiffTmp['libelle'] = 'Moyenne';
		else
			DiffTmp['libelle'] = 'Difficile';
		$scope.listeDifficult.push(DiffTmp);
	}

	$scope.$on('listRecettesSupplied', function(event,listRecettesReceived) {
		$scope.listRecettes = listRecettesReceived;
	});
} ]);