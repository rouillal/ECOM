eComBioApp
		.factory(
				'panierSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope, restBackendSvc,$window) {
							var listePanier = [{id : 0, qt : 3, px : 23},{id : 2, qt : 8, px : 21}];
							
							var getListePanier = function() {
								return listePanier;
							};
							
							return {
								getListePanier : getListePanier
							};
						} ]);