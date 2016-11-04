eComBioApp
		.factory(
				'productSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope, restBackendSvc,$window) {
							var getProductBySearchName = function(searchString) {
								$window.alert("ee");
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