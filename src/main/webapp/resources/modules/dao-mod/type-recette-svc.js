eComBioApp
		.factory(
				'typeRecetteSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope, restBackendSvc,$window) {
							var getAllTypeRecettes = function() {
								restBackendSvc
											.getItemsByUrl("typeRecette")
											.then(
													function(data) {
														$rootScope.$broadcast(
																'listTypeRecettesSupplied',
																data.data);
													});
								return [];
							}
							
							return {
								getAllTypeRecettes : getAllTypeRecettes
							};
						} ]);