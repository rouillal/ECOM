eComBioApp.factory('commandeSearchSvc', [ '$rootScope','$window','commandeSvc',function($rootScope,$window,commandeSvc) {
	var searchDateLivraison='15/12/2016'; // Mettre date du jour !!!
	var searchEnt=true;
	var searchDom=true;
	var currentPage=0;
	var listeTris = [{'name':'alpha','libelle':'Noms'}];
	var currentTri = {'name':'alpha','libelle':'Noms'};
	
	var reinitPageDueToNewSearch = function() {
		currentPage=0;
		$rootScope.$broadcast('reinitPageDueToNewSearch',currentPage);
	}
	
	var doSearch = function() {
		commandeSvc.getCommandesByDateLivraison(searchDateLivraison,searchEnt,searchDom,currentPage,currentTri.name);
	}
	
	var changeDateLivraison = function(dateLivraisonChanged) {
		searchDateLivraison = dateLivraisonChanged;
		doSearch();
	}
		
	var getDateLivraison = function() {
		return searchDateLivraison;
	}
	
	var changeSearchEnt = function(searchEntChanged) {
		searchEnt = searchEntChanged;
		doSearch();
	}
		
	var getSearchEnt = function() {
		return searchEnt;
	}
	
	var changeSearchDom = function(searchDomChanged) {
		searchDom = searchDomChanged;
		doSearch();
	}
		
	var getSearchDom = function() {
		return searchDom;
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
		changeSearchEnt : changeSearchEnt,
		getSearchEnt : getSearchEnt,
		changeSearchDom : changeSearchDom,
		getSearchDom : getSearchDom,
		getCommandesInit : getCommandesInit,
		getlisteTris : getlisteTris,
		getCurrentPage : getCurrentPage,
		pagedown : pagedown,
		pageup : pageup,
		getCurrentTri : getCurrentTri,
		setCurrentTri : setCurrentTri
	};
		} ]);