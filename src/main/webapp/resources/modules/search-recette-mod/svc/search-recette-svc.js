eComBioApp.factory('searchRecetteSvc', [ '$rootScope','categorieRecetteSvc','saisonSvc','compositionSvc','recetteSvc','$window',function($rootScope,categorieRecetteSvc,saisonSvc,compositionSvc,recetteSvc,$window) {
	var listCategoriesRecette = categorieRecetteSvc.getAllCategorieRecettes();
	var listSaison = saisonSvc.getAllSaisons();
	var listComposition = compositionSvc.getAllCompositions();
	var searchRecetteString ="";
	var currentPage=0;
	var listeTris = [{'name':'alpha','libelle':'noms'},{'name':'diff','libelle':'difficulté'},{'name':'cout','libelle':'coût'}];
	var currentTriIndex = 0;
	
	var initListChoix = function(listSrc) {
		var listChoixRet = [];
		angular.forEach(listSrc, function(cat, key) {
			listChoixRet.push(false);
		});
		return listChoixRet;
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
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
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
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
	}
	
	var changeIsSaison = function() {
		listSaison,listSaisonChoix = !listSaison,listSaisonChoix;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
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
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
	}
	
	var getIsSaison = function() {
		return listSaison,listSaisonChoix;
	}
	
	var reinitPageDueToNewSearch = function() {
		currentPage=0;
		$rootScope.$broadcast('reinitPageDueToNewSearch',currentPage);
	}
	
	var changeSaison = function(saisonChanged) {
		isSaison = saisonChanged;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
	}
		
	var getSearchString = function() {
		return searchRecetteString;
	}
	
	var setSearchString = function(newSearchString) {
		if (newSearchString != searchRecetteString) {
			searchRecetteString = newSearchString;
			reinitPageDueToNewSearch();
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
		}
	}
	
	var getRecettesInit = function() {
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
		return [];
	}
	
	var setSelectedPage = function(newSelectedPage) {
		if (currentPage != newSelectedPage) {
			currentPage = newSelectedPage;
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
		}
	}
	
	var setSelectedTriIndex  = function(newSelectedTriIndex) {
		selectedTriIndex = newSelectedTriIndex;
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
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
		}
		return currentPage;
	}
	
	var pageup = function() {
		if (currentPage < 99) {
			currentPage += 1;
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,listSaison,listSaisonChoix,listComposition,listCompositionChoix,currentPage);
		}
		return currentPage;
	}
	
	var getCurrentTriIndex = function() {
		return currentTriIndex;
	}
	
	var setCurrentTriIndex = function(newTriIndex) {
		currentTriIndex = newTriIndex;
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
		
		getIsSaison : getIsSaison,
		changeSaison : changeSaison,
		getSearchString : getSearchString,
		setSearchString : setSearchString,
		getRecettesInit : getRecettesInit,
		getlisteTris : getlisteTris,
		getCurrentPage : getCurrentPage,
		pagedown : pagedown,
		pageup : pageup,
		getCurrentTriIndex : getCurrentTriIndex,
		setCurrentTriIndex : setCurrentTriIndex
	};
		} ]);