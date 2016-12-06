eComBioApp
		.factory(
				'categorieRecetteSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope, restBackendSvc, $window) {
							var getAllCategorieRecettes = function() {
								restBackendSvc
										.getItemsByUrl("categorieRecette")
										.then(
												function(data) {
													$rootScope
															.$broadcast(
																	'listCategorieRecettesSupplied',
																	data.data);
												});
								return [];
							}

							return {
								getAllCategorieRecettes : getAllCategorieRecettes
							};
						} ]);