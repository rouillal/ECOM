eComBioApp.factory('searchProductCriteriaSvc', [ '$rootScope','categorieSvc','productSvc','$window',function($rootScope,categorieSvc,productSvc,$window) {
	var listCategories = categorieSvc.getAllCategories();
	var searchProductStringCriteria ="";
	var page=0;
	var listeTris = [{'name':'alpha','libelle':'ordre aplphabetique'},{'name':'prixup','libelle':'prix croissants'}];
	var selectedTriIndex = 0;
	
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
	
	var changeListCategoriesChoix = function(listCategoriesChoixChanged) {
		listCategoriesChoix = listCategoriesChoixChanged;
		page=0;
		productSvc.getProductBySearchName(searchProductStringCriteria,listCategories,listCategoriesChoix,page);
	}
		
	var getSearchProductStringCriteria = function() {
		return searchProductStringCriteria;
	}
	
	var setSearchProductStringCriteria = function(newSearchString,listCategories,listCategoriesChoix) {
		if (newSearchString != searchProductStringCriteria) {
			searchProductStringCriteria = newSearchString;
			page=0;
			productSvc.getProductBySearchName(searchProductStringCriteria,listCategories,listCategoriesChoix,page);
		}
	}
	
	var setSelectedProduct  = function(newSelectedProduct) {
		
	}
	
	var setSelectedPage = function(newSelectedPage) {
		if (page != newSelectedPage) {
			page = newSelectedPage;
			productSvc.getProductBySearchName(searchProductStringCriteria,listCategories,listCategoriesChoix,page);
		}
	}
	
	var setSelectedTriIndex  = function(newSelectedTriIndex) {
		selectedTriIndex = newSelectedTriIndex;
	}
	
	$rootScope.$on('listCategoriesSupplied', function(event,listCategoriesSupplied) {
		listCategories = listCategoriesSupplied;
		listCategoriesChoix = initListCategoriesChoix();
		$rootScope.$broadcast('listCategoriesCritSupplied');
	});
	
	return {
		getListCategories : getListCategories,
		getListCategoriesChoix : getListCategoriesChoix,
		changeListCategoriesChoix : changeListCategoriesChoix,
		getSearchProductStringCriteria : getSearchProductStringCriteria,
		setSearchProductStringCriteria : setSearchProductStringCriteria
	};

		} ]);