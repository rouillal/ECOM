eComBioApp
		.factory(
				'compositionSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope, restBackendSvc,$window) {
							var getAllCompositions = function() {
								restBackendSvc
											.getItemsByUrl("composition")
											.then(
													function(data) {
														$rootScope.$broadcast(
																'listCompositionsSupplied',
																data.data);
													});
								return [];
							}
							
							return {
								getAllCompositions : getAllCompositions
							};
						} ]);