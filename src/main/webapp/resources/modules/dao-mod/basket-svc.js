eComBioApp
		.factory(
				'basketSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope, restBackendSvc,$window) {
							var getProductBySearchName = function(searchString) {
								$window.alert("ef");
								restBackendSvc
											.getItemsByUrl("produit/cat?parameter="+searchString)
											.then(
													function(data) {
														$rootScope.$broadcast(
																'datasupplied',
																"product");
													});
							}
							
							return {
								getProductBySearchName : getProductBySearchName
							};
						} ]);