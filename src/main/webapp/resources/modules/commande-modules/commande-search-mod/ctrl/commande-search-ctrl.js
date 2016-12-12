eComBioApp.controller('CommandeSearchCtrl', [ '$scope','$window','commandeSearchSvc',function($scope,$window,commandeSearchSvc) {
	$scope.searchCommandeDate = commandeSearchSvc.getDateLivraison();
	$scope.searchEnt = commandeSearchSvc.getSearchEnt();
	$scope.searchDom = commandeSearchSvc.getSearchDom();
	$scope.debug = 'Url2 Rest Search à venir';
	
	$scope.searchCommandeByDate  = function() {
		commandeSearchSvc.changeDateLivraison($scope.searchCommandeDate);
	}
	
	$scope.$watch('searchCommandeDate', function() {
		commandeSearchSvc.changeDateLivraison($scope.searchCommandeDate);
	});
	
	$scope.$watch('searchEnt', function() {
		commandeSearchSvc.changeSearchEnt($scope.searchEnt);
	});
	
	$scope.$watch('searchDom', function() {
		commandeSearchSvc.changeSearchDom($scope.searchDom);
	});
	
	/*$scope.$on('listCategoriesCritSupplied', function(event) {
		$scope.listCategories = searchCommandeSvc.getListCategories();
		$scope.listCategoriesChoix=searchCommandeSvc.getListCategoriesChoix();
	});*/
	
	$scope.formatDate = function (date) {
	    function pad(n) {
	        return n < 10 ? '0' + n : n;
	    }

	    return date && pad(date.getDate())
	        + '/' + pad(date.getMonth() + 1)
	        + '/' + date.getFullYear();
	};

	$scope.parseDate = function (s) {
	    var tokens = /^(\d{2})-(\d{2})-(\d{4})$/.exec(s);

	    return tokens && new Date(tokens[1], tokens[2] - 1, tokens[3]);
	};
	
	$scope.seeCalendar = function(){
		$scope.seeCalend = true;
	}
	
	$scope.hideCalendar = function(){
		$scope.seeCalend = false;
	}
	
	$scope.$on('debug', function(event,message) {
		$scope.debug = message;
	});
} ]);