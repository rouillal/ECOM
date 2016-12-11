eComBioApp.factory('commandeSearchSvc', [ '$rootScope','$window','commandeSvc',function($rootScope,$window,commandeSvc) {
	var searchDateLivraison='15/12/2016';
	var currentPage=0;
	var listeTris = [{'name':'alpha','libelle':'Noms'}];
	var currentTri = {'name':'alpha','libelle':'Noms'};
	
	var reinitPageDueToNewSearch = function() {
		currentPage=0;
		$rootScope.$broadcast('reinitPageDueToNewSearch',currentPage);
	}
	
	var doSearch = function() {
		commandeSvc.getCommandesByDateLivraison(searchDateLivraison,currentPage,currentTri.name);
	}
	
	var changeDateLivraison = function(dateLivraisonChanged) {
		searchDateLivraison = dateLivraisonChanged;
		doSearch();
	}
		
	var getDateLivraison = function() {
		return searchDateLivraison;
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
	
	return {
		changeDateLivraison : changeDateLivraison,
		getDateLivraison : getDateLivraison,
		getCommandesInit : getCommandesInit,
		getlisteTris : getlisteTris,
		getCurrentPage : getCurrentPage,
		pagedown : pagedown,
		pageup : pageup,
		getCurrentTri : getCurrentTri,
		setCurrentTri : setCurrentTri
	};
		} ]);