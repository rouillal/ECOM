eComBioApp.factory('searchRecetteSvc', [ '$rootScope','categorieSvc','recetteSvc','$window',function($rootScope,categorieSvc,recetteSvc,$window) {
	var listCategories = categorieSvc.getAllCategories();
	var listTypesRecette = [{'name':'Apéritif'},{'name':'Entrée'},{'name':'Plat'},{'name':'Dessert'},{'name':'Boisson'}];
	var listSaison = [{'name':'Printemps'},{'name':'Eté'},{'name':'Automne'},{'name':'Hiver'}];
	var listComposition = [{'name':'Sans gluten'},{'name':'Sans lactose'},{'name':'Végétalien'},{'name':'Végétarien'}];
	var searchRecetteString ="";
	var currentPage=0;
	var listeTris = [{'name':'alpha','libelle':'noms'},{'name':'prixup','libelle':'prix croissants'},{'name':'prixdown','libelle':'prix décroissants'}];
	var currentTriIndex = 0;
	var isSaison = false;
	
	var initListChoix = function(listSrc) {
		var listChoixRet = [];
		angular.forEach(listSrc, function(cat, key) {
			listChoixRet.push(false);
		});
		return listChoixRet;
	}
	
	var listCategoriesChoix = initListChoix(listCategories);
	var getListCategories = function() {
		return listCategories;
	}
	var getListCategoriesChoix = function() {
		return listCategoriesChoix;
	}
	
	var changeListCategoriesChoix = function(listCategoriesChoixChanged) {
		listCategoriesChoix = listCategoriesChoixChanged;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
	}
	
	var listTypesRecetteChoix = initListChoix(listTypesRecette);
	var getListTypesRecette = function() {
		return listTypesRecette;
	}
	var getListTypesRecetteChoix = function() {
		return listTypesRecetteChoix;
	}
	var changeListTypesRecetteChoix = function(listCategoriesChoixChanged) {
		listCategoriesChoix = listCategoriesChoixChanged;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
	}
	
	var listSaisonChoix = initListChoix(listSaison);
	var getListSaison = function() {
		return listSaison;
	}
	var getListSaisonChoix = function() {
		return listSaisonChoix;
	}
	var changeListSaisonChoix = function(listSaisonChoixChanged) {
		listSaisonChoix = listSaisonChoixChanged;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
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
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
	}
	
	var getIsSaison = function() {
		return isSaison;
	}
	
	var reinitPageDueToNewSearch = function() {
		currentPage=0;
		$rootScope.$broadcast('reinitPageDueToNewSearch',currentPage);
	}
	
	var changeSaison = function(saisonChanged) {
		isSaison = saisonChanged;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
	}
		
	var getSearchString = function() {
		return searchRecetteString;
	}
	
	var setSearchString = function(newSearchString) {
		if (newSearchString != searchRecetteString) {
			searchRecetteString = newSearchString;
			reinitPageDueToNewSearch();
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
		}
	}
	
	var getRecettesInit = function() {
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
	}
	
	var setSelectedPage = function(newSelectedPage) {
		if (currentPage != newSelectedPage) {
			currentPage = newSelectedPage;
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
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
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
		}
		return currentPage;
	}
	
	var pageup = function() {
		if (currentPage < 99) {
			currentPage += 1;
			recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
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
	
	$rootScope.$on('listCategoriesSupplied', function(event,listCategoriesSupplied) {
		listCategories = listCategoriesSupplied;
		listCategoriesChoix = initListChoix(listCategories);
		$rootScope.$broadcast('listCategoriesCritSupplied');
	});
	
	return {
		getListCategories : getListCategories,
		getListCategoriesChoix : getListCategoriesChoix,
		getListTypesRecette : getListTypesRecette,
		changeListCategoriesChoix : changeListCategoriesChoix,
		getListTypesRecetteChoix : getListTypesRecetteChoix,
		changeListTypesRecetteChoix : changeListTypesRecetteChoix,
		
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