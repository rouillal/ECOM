eComBioApp.factory('searchRecetteSvc', [ '$rootScope','categorieRecetteSvc','saisonSvc','compositionSvc','recetteSvc','$window',function($rootScope,categorieRecetteSvc,saisonSvc,compositionSvc,recetteSvc,$window) {
	var listCategoriesRecette = categorieRecetteSvc.getAllCategorieRecettes(); //[{'name':'Apéritif'},{'name':'Entrée'},{'name':'Plat'},{'name':'Dessert'},{'name':'Boisson'}];//
	var listComposition = compositionSvc.getAllCompositions(); //[{'name':'Sans gluten'},{'name':'Sans lactose'},{'name':'Végétalien'},{'name':'Végétarien'}];//
	var searchRecetteString ="";
	var currentPage=0;
	var listeTris = [{'name':'alpha','libelle':'noms'},{'name':'diff','libelle':'difficulté'},{'name':'cout','libelle':'coût'}];
	var currentTriIndex = 0;
	var searchRecetteIsSaison = false;
	
	var initListChoix = function(listSrc) {
		var listChoixRet = [];
		angular.forEach(listSrc, function(cat, key) {
			listChoixRet.push(false);
		});
		return listChoixRet;
	}
	
	var listCategoriesRecetteChoix = initListChoix(listCategoriesRecette);
	var getListCategoriesRecette = function() {
		return listCategoriesRecette;
	}
	var getListCategoriesRecetteChoix = function() {
		return listCategoriesRecetteChoix;
	}
	var changeListCategoriesRecetteChoix = function(listCategoriesRecetteChanged) {
		listCategoriesRecetteChoix = listCategoriesRecetteChanged;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,searchRecetteIsSaison,listComposition,listCompositionChoix,currentPage);
	}
	
	var changeIsSaison = function() {
		searchRecetteIsSaison = !searchRecetteIsSaison;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,searchRecetteIsSaison,listComposition,listCompositionChoix,currentPage);
	}
	
	var listCompositionChoix = initListChoix(listComposition);
	var getListComposition = function() {
		return listComposition;
	}
	var getListCompositionChoix = function() {
		return listCompositionChoix;
	}
	var changeListCompositionChoix = function(listCompositionChoixChanged) {
		listCompositionChoix = listCompositionChoixChanged;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,searchRecetteIsSaison,listComposition,listCompositionChoix,currentPage);
	}
	
	var getIsSaison = function() {
		return searchRecetteIsSaison;
	}
	
	var reinitPageDueToNewSearch = function() {
		currentPage=0;
		$rootScope.$broadcast('reinitPageDueToNewSearch',currentPage);
	}
	
	var changeSaison = function(saisonChanged) {
		isSaison = saisonChanged;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,searchRecetteIsSaison,listComposition,listCompositionChoix,currentPage);
	}
		
	var getSearchString = function() {
		return searchRecetteString;
	}
	
	var setSearchString = function(newSearchString) {
		if (newSearchString != searchRecetteString) {
			searchRecetteString = newSearchString;
			reinitPageDueToNewSearch();
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,searchRecetteIsSaison,listComposition,listCompositionChoix,currentPage);
		}
	}
	
	var getRecettesInit = function() {
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,searchRecetteIsSaison,listComposition,listCompositionChoix,currentPage);
		return [];
	}
	
	var setSelectedPage = function(newSelectedPage) {
		if (currentPage != newSelectedPage) {
			currentPage = newSelectedPage;
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,searchRecetteIsSaison,listComposition,listCompositionChoix,currentPage);
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
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,searchRecetteIsSaison,listComposition,listCompositionChoix,currentPage);
		}
		return currentPage;
	}
	
	var pageup = function() {
		if (currentPage < 99) {
			currentPage += 1;
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategoriesRecette,listCategoriesRecetteChoix,searchRecetteIsSaison,listComposition,listCompositionChoix,currentPage);
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
		$windows.alert('listCategorieRecettesSupplied in the b');
		listCategoriesRecette = listCategoriesRecettesSupplied;
		listCategoriesRecetteChoix = initListChoix(listCategoriesRecette);
		$rootScope.$broadcast('listCategorieRecettesCritSupplied');
	});
	
	$rootScope.$on('listSaisonsSupplied', function(event,listSaisonsSupplied) {
		$windows.alert('Saison in the b');
		listSaison = listSaisonsSupplied;
		listSaisonChoix = initListChoix(listSaison);
		$rootScope.$broadcast('listSaisonsCritSupplied');
	});
	
	$rootScope.$on('listCompositionsSupplied', function(event,listCompositionsSupplied) {
		listComposition = listCompositionsSupplied;
		$window.alert('RRK2');
		listCompositionChoix = initListChoix(listComposition);
		$window.alert('RRK2.5');
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