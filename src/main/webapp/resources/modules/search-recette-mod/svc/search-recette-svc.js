eComBioApp.factory('searchRecetteSvc', [ '$rootScope','categorieSvc','recetteSvc','$window',function($rootScope,categorieSvc,recetteSvc,$window) {
	var listCategories = categorieSvc.getAllCategories();
	var searchRecetteString ="";
	var currentPage=0;
	var listeTris = [{'name':'alpha','libelle':'noms'},{'name':'prixup','libelle':'prix croissants'},{'name':'prixdown','libelle':'prix décroissants'}];
	var currentTriIndex = 0;
	var isSaison = false;
	
	var initListCategoriesChoix = function() {
		var listCategoriesChoixTmp = [];
		angular.forEach(listCategories, function(cat, key) {
			listCategoriesChoixTmp.push(false);
		});
		return listCategoriesChoixTmp;
	}
	
	var listCategoriesChoix = initListCategoriesChoix();
	
	var getListCategories = function() {
		return listCategories;
	}
	
	var getListCategoriesChoix = function() {
		return listCategoriesChoix;
	}
	
	var getIsSaison = function() {
		return isSaison;
	}
	
	var reinitPageDueToNewSearch = function() {
		currentPage=0;
		$rootScope.$broadcast('reinitPageDueToNewSearch',currentPage);
	}
	
	var changeListCategoriesChoix = function(listCategoriesChoixChanged) {
		listCategoriesChoix = listCategoriesChoixChanged;
		reinitPageDueToNewSearch();
		recetteSvc.getRecetteBySearchName(searchRecetteString,listCategories,listCategoriesChoix,currentPage,isSaison);
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
		listCategoriesChoix = initListCategoriesChoix();
		$rootScope.$broadcast('listCategoriesCritSupplied');
	});
	
	return {
		getListCategories : getListCategories,
		getListCategoriesChoix : getListCategoriesChoix,
		getIsSaison : getIsSaison,
		changeSaison : changeSaison,
		changeListCategoriesChoix : changeListCategoriesChoix,
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