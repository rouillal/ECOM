eComBioApp.factory('searchCommandeSvc', [ '$rootScope','categorieSvc','productSvc','$window',function($rootScope,categorieSvc,productSvc,$window) {
	var listCategories = categorieSvc.getAllCategories();
	var searchCommandeString ="";
	var currentPage=0;
	var listeTris = [{'name':'alpha','libelle':'Noms'},{'name':'prixup','libelle':'Prix croissants'},{'name':'prixdown','libelle':'Prix décroissants'}];
	var currentTri = {'name':'alpha','libelle':'Noms'};
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
	
	var doSearch = function() {
		productSvc.getCommandeBySearchName(searchCommandeString,listCategories,listCategoriesChoix,currentPage,isSaison,currentTri.name);
	}
	
	var changeListCategoriesChoix = function(listCategoriesChoixChanged) {
		listCategoriesChoix = listCategoriesChoixChanged;
		reinitPageDueToNewSearch();
		doSearch();
	}
	
	var changeSaison = function(saisonChanged) {
		isSaison = saisonChanged;
		reinitPageDueToNewSearch();
		doSearch();
	}
		
	var getSearchString = function() {
		return searchCommandeString;
	}
	
	var setSearchString = function(newSearchString) {
		if (newSearchString != searchCommandeString) {
			searchCommandeString = newSearchString;
			reinitPageDueToNewSearch();
			doSearch();
		}
	}
	
	var getCommandesInit = function() {
		doSearch();
	}
	
	var setSelectedPage = function(newSelectedPage) {
		if (currentPage != newSelectedPage) {
			currentPage = newSelectedPage;
			doSearch();
		}
	}
	
	var setSelectedTri  = function(newSelectedTri) {
		currentTri = newSelectedTri;
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
		getCommandesInit : getCommandesInit,
		getlisteTris : getlisteTris,
		getCurrentPage : getCurrentPage,
		pagedown : pagedown,
		pageup : pageup,
		getCurrentTri : getCurrentTri,
		setCurrentTri : setCurrentTri
	};
		} ]);