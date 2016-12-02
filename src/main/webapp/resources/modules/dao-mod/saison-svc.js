eComBioApp
		.factory(
				'saisonSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope, restBackendSvc,$window) {
							var getAllSaisons = function() {
								restBackendSvc
											.getItemsByUrl("saison")
											.then(
													function(data) {
														$rootScope.$broadcast(
																'listSaisonsSupplied',
																data.data);
													});
								return [];
							}
							
							return {
								getAllSaisons : getAllSaisons
							};
						} ]);