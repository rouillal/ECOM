eComBioApp.factory('searchProductCriteriaSvc', [ '$rootScope',
		'productSvc', function($rootScope, productSvc) {
	var listSearchCategoriesCriteria = [];
	var searchProductStringCriteria ="se";
	
	var getSearchProductStringCriteria = function() {
		return searchProductStringCriteria;
	}
	
	var setSearchProductStringCriteria = function(newSearchString) {
		if (newSearchString != searchProductStringCriteria) {
			searchProductStringCriteria = newSearchString;
			productSvc.getProductBySearchName(searchProductStringCriteria);
		}
	}
	
	var setSelectedProduct  = function(newSelectedProduct) {
		
	}
	
	return {
		getSearchProductStringCriteria : getSearchProductStringCriteria,
		setSearchProductStringCriteria : setSearchProductStringCriteria
	};

		} ]);