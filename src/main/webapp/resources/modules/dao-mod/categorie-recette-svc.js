eComBioApp
		.factory(
				'categorieRecetteSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope, restBackendSvc,$window) {
							var getAllCategorieRecettes = function() {
								restBackendSvc
											.getItemsByUrl("categorieRecette")
											.then(
													function(data) {
														$windows.alert('555');
														$rootScope.$broadcast(
																'listCategorieRecettesSupplied',
																data.data);
														$windows.alert('listCategorieRecettesSupplied in the b000000000');
													});
								return [];
							}
							
							return {
								getAllCategorieRecettes : getAllCategorieRecettes
							};
						} ]);