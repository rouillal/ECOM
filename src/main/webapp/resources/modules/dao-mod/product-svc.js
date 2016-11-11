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
							
							var getProductBySearchName = function(searchString,listCategories,listCategoriesChoix,page) {
								var restAdress="produit/filter?";
								var categoSelected = '';
								angular.forEach(listCategories, function(categorie, key) {
									if (listCategoriesChoix[key]) {
										if (categoSelected != '') {
											categoSelected += ',';
										}
										categoSelected += categorie.id;
									} 
								});
								if (categoSelected != '') {
									restAdress += 'cat='+categoSelected;
								}
								if (searchString != '') {
									if (categoSelected != '') {
										restAdress += '&';
									}
									restAdress += 'search='+searchString;
								}
								if ((categoSelected != '')||(searchString != '')) {
									restAdress += '&';
								}
								restAdress += 'page='+page;
								$rootScope.$broadcast(
										'debug',
										restAdress);
								restBackendSvc
											.getItemsByUrl(restAdress)
											.then(
													function(data) {
														$rootScope.$broadcast(
																'listProductsSupplied',
																data.data);
													}, function (reason) {
														$rootScope.$broadcast('debug', reason);
														if (reason.status == 404) {
															$rootScope.$broadcast(
																	'listProductsSupplied','');
														} else {
															alert('Failed: ' + reason);
														}
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