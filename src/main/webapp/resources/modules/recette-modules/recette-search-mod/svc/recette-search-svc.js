eComBioApp.factory('recetteSearchSvc', [ '$rootScope','categorieRecetteSvc','saisonSvc','compositionSvc','recetteSvc','$window',function($rootScope,categorieRecetteSvc,saisonSvc,compositionSvc,recetteSvc,$window) {
	var listCategoriesRecette = categorieRecetteSvc.getAllCategorieRecettes();
	var listSaison = saisonSvc.getAllSaisons();
	var listComposition = compositionSvc.getAllCompositions();
	var searchRecetteString ="";
	var currentPage=0;
	var listeTris = [{'name':'alpha','libelle':'noms'},{'name':'diff','libelle':'difficulté'},{'name':'cout','libelle':'coût'}];
	var currentTri = {'name':'alpha','libelle':'Noms'};
	
	var initListChoix = function(listSrc) {
		var listChoixRet = [];
		angular.forEach(listSrc, function(cat, key) {
			listChoixRet.push(false);
		});
		return listChoixRet;
	}
	
	var reinitPageDueToNewSearch = function() {
		currentPage=0;
		$rootScope.$broadcast('reinitPageDueToNewSearch',currentPage);
	}
	
	var doSearch = function() {
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage,currentTri.name);
	}
	
	var listCategoriesRecetteChoix = initListChoix(listCategoriesRecette);
	var listSaisonChoix = initListChoix(listSaison);
	var listCompositionChoix = initListChoix(listComposition);
	
	var getListCategoriesRecette = function() {
		return listCategoriesRecette;
	}
	var getListCategoriesRecetteChoix = function() {
		return listCategoriesRecetteChoix;
	}
	var changeListCategoriesRecetteChoix = function(listCategoriesRecetteChanged) {
		listCategoriesRecetteChoix = listCategoriesRecetteChanged;
		reinitPageDueToNewSearch();
		doSearch();
	}
	
	var getListSaison = function() {
		return listSaison;
	}
	var getListSaisonChoix = function() {
		return listSaisonChoix;
	}
	var changeListSaisonChoix = function(listSaisonChanged) {
		listSaisonChoix = listSaisonChanged;
		reinitPageDueToNewSearch();
		doSearch();
	}
	
	var getListComposition = function() {
		return listComposition;
	}
	var getListCompositionChoix = function() {
		return listCompositionChoix;
	}
	var changeListCompositionChoix = function(listCompositionChoixChanged) {
		listCompositionChoix = listCompositionChoixChanged;
		reinitPageDueToNewSearch();
		doSearch();
	}
	
	var getSearchRecetteString = function() {
		return searchRecetteString;
	}
	
	var setSearchRecetteString = function(newSearchString) {
		if (newSearchString != searchRecetteString) {
			searchRecetteString = newSearchString;
			reinitPageDueToNewSearch();
			doSearch();
		}
	}
	
	var getRecettesInit = function() {
		doSearch();
		return [];
	}
	
	var setSelectedPage = function(newSelectedPage) {
		if (currentPage != newSelectedPage) {
			currentPage = newSelectedPage;
			doSearch();
		}
	}
	
	var setSelectedTri  = function(newSelectedTri) {
		selectedTri = newSelectedTri;
	}
	
	var getlisteTris = function() {
		return listeTris;
	}
	
	var getCurrentPage = function() {
		return currentPage;
	}
	
	var pagedown = function() {
		if (currentPage >0) {
			currentPage -= 1;
			doSearch();
		}
		return currentPage;
	}
	
	var pageup = function() {
		if (currentPage < 99) {
			currentPage += 1;
			doSearch();
		}
		return currentPage;
	}
	
	var getCurrentTri = function() {
		return currentTri;
	}
	
	var setCurrentTri = function(newTri) {
		currentTri = newTri;
		doSearch();
		reinitPageDueToNewSearch();
	}
	
	$rootScope.$on('listCategorieRecettesSupplied', function(event,listCategoriesRecettesSupplied) {
		listCategoriesRecette = listCategoriesRecettesSupplied;
		listCategoriesRecetteChoix = initListChoix(listCategoriesRecette);
		$rootScope.$broadcast('listCategorieRecettesCritSupplied');
	});
	
	$rootScope.$on('listSaisonsSupplied', function(event,listSaisonsSupplied) {
		listSaison = listSaisonsSupplied;
		listSaisonChoix = initListChoix(listSaison);
		$rootScope.$broadcast('listSaisonsCritSupplied');
	});
	
	$rootScope.$on('listCompositionsSupplied', function(event,listCompositionsSupplied) {
		listComposition = listCompositionsSupplied;
		listCompositionChoix = initListChoix(listComposition);
		$rootScope.$broadcast('listCompositionsCritSupplied');
	});
	
	return {
		getListCategoriesRecette : getListCategoriesRecette,
		getListCategoriesRecetteChoix : getListCategoriesRecetteChoix,
		changeListCategoriesRecetteChoix : changeListCategoriesRecetteChoix,
		getListSaison : getListSaison,
		getListSaisonChoix : getListSaisonChoix,
		changeListSaisonChoix : changeListSaisonChoix,
		getListComposition : getListComposition,
		getListCompositionChoix : getListCompositionChoix,
		changeListCompositionChoix : changeListCompositionChoix,
		getSearchRecetteString : getSearchRecetteString,
		setSearchRecetteString : setSearchRecetteString,
		getRecettesInit : getRecettesInit,
		getlisteTris : getlisteTris,
		getCurrentPage : getCurrentPage,
		pagedown : pagedown,
		pageup : pageup,
		getCurrentTri : getCurrentTri,
		setCurrentTri : setCurrentTri
	};
		} ]);