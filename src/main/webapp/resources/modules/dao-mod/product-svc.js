eComBioApp
		.factory(
				'productSvc',
				[
						'$rootScope',
						'restBackendSvc',
						'$window',
						function($rootScope, restBackendSvc,$window) {
							var getAllProducts = function() {
								restBackendSvc
											.getItemsByUrl("produit")
											.then(
													function(data) {
														$rootScope.$broadcast(
																'listProductsSupplied',
																data.data);
													});
							}
							
							var getProductBySearchName = function(searchString) {
								restBackendSvc
											.getItemsByUrl("produit/cat?parameter="+searchString)
											.then(
													function(data) {
														$rootScope.$broadcast(
																'listProductsSupplied',
																data.data);
													});
							}
							
							var getDetailsProduct  = function(nameOrId) {
								restBackendSvc
											.getItemsByUrl("produit/id?parameter="+nameOrId)
											.then(
													function(data) {
														$rootScope.$broadcast(
																'detailsProductSupplied',
																data);
													});
							}
							
							return {
								getAllProducts : getAllProducts,
								getProductBySearchName : getProductBySearchName,
								getDetailsProduct : getDetailsProduct
							};
						} ]);