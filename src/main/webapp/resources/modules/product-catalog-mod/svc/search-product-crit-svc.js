eComBioApp.factory('searchProductCriteriaSvc', [ '$rootScope',
		'productSvc', function($rootScope, productSvc) {
	var listSearchCategoriesCriteria = [];
	var searchProductStringCriteria ="se";
	
	var getSearchProductStringCriteria = function() {
		return searchProductStringCriteria;
	}
	
	var setSearchProductStringCriteria = function(newSearchString,listCategories,listCategoriesChoix) {
		if (newSearchString != searchProductStringCriteria) {
			searchProductStringCriteria = newSearchString;
			productSvc.getProductBySearchName(searchProductStringCriteria,listCategories,listCategoriesChoix);
		}
	}
	
	var searchProductDirectly = function(listCategories,listCategoriesChoix) {
		productSvc.getProductBySearchName(searchProductStringCriteria,listCategories,listCategoriesChoix);
	}
	
	var setSelectedProduct  = function(newSelectedProduct) {
		
	}
	
	return {
		getSearchProductStringCriteria : getSearchProductStringCriteria,
		setSearchProductStringCriteria : setSearchProductStringCriteria,
		searchProductDirectly : searchProductDirectly
	};

		} ]);