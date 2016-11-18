eComBioApp.factory('searchProductSvc', [ '$rootScope','categorieSvc','productSvc','$window',function($rootScope,categorieSvc,productSvc,$window) {
	var listCategories = categorieSvc.getAllCategories();
	var searchProductString ="";
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
		productSvc.getProductBySearchName(searchProductString,listCategories,listCategoriesChoix,currentPage,isSaison);
	}
	
	var changeSaison = function(saisonChanged) {
		isSaison = saisonChanged;
		reinitPageDueToNewSearch();
		productSvc.getProductBySearchName(searchProductString,listCategories,listCategoriesChoix,currentPage,isSaison);
	}
		
	var getSearchString = function() {
		return searchProductString;
	}
	
	var setSearchString = function(newSearchString) {
		if (newSearchString != searchProductString) {
			searchProductString = newSearchString;
			reinitPageDueToNewSearch();
			productSvc.getProductBySearchName(searchProductString,listCategories,listCategoriesChoix,currentPage,isSaison);
		}
	}
	
	var getProductsInit = function() {
		productSvc.getProductBySearchName(searchProductString,listCategories,listCategoriesChoix,currentPage,isSaison);
	}
	
	var setSelectedPage = function(newSelectedPage) {
		if (currentPage != newSelectedPage) {
			currentPage = newSelectedPage;
			productSvc.getProductBySearchName(searchProductString,listCategories,listCategoriesChoix,currentPage,isSaison);
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
			productSvc.getProductBySearchName(searchProductString,listCategories,listCategoriesChoix,currentPage,isSaison);
		}
		return currentPage;
	}
	
	var pageup = function() {
		if (currentPage < 99) {
			currentPage += 1;
			productSvc.getProductBySearchName(searchProductString,listCategories,listCategoriesChoix,currentPage,isSaison);
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
		getProductsInit : getProductsInit,
		getlisteTris : getlisteTris,
		getCurrentPage : getCurrentPage,
		pagedown : pagedown,
		pageup : pageup,
		getCurrentTriIndex : getCurrentTriIndex,
		setCurrentTriIndex : setCurrentTriIndex
	};
		} ]);