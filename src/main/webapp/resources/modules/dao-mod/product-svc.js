eComBioApp.factory('productSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		function($rootScope, restBackendSvc, $window) {
			
			var getProductBySearchName = function(searchString, listCategories,
					listCategoriesChoix, page) {
				var restAdress = "produit/filter?";
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
					restAdress += 'cat=' + categoSelected;
				}
				if (searchString != '') {
					if (categoSelected != '') {
						restAdress += '&';
					}
					restAdress += 'search=' + searchString;
				}
				if ((categoSelected != '') || (searchString != '')) {
					restAdress += '&';
				}
				restAdress += 'page=' + page;
				$rootScope.$broadcast('debug', restAdress);
				restBackendSvc.getItemsByUrl(restAdress).then(function(data) {
					$rootScope.$broadcast('listProductsSupplied', data.data);
				}, function(reason) {
					$rootScope.$broadcast('debug', reason);
					if (reason.status == 404) {
						$rootScope.$broadcast('listProductsSupplied', '');
					} else {
						alert('Failed: ' + reason);
					}
				});
			}

			var getDetailsProduct = function(nameOrId) {
				restBackendSvc
						.getItemsByUrl("produit/id?parameter=" + nameOrId)
						.then(
								function(data) {
									$rootScope.$broadcast(
											'detailsProductSupplied', data);
								});
			}

			var createProduct = function(product) {
				restBackendSvc.createItem(product).then(function(data) {
					//inform with message
				});
			}

			var updateProduct = function(product) {
				restBackendSvc.updateItem(product).then(function(data) {
					//inform with message
				});
			}

			var removeItem = function(product) {
				restBackendSvc.deleteItem(product._links.self.href).then(
						function(data) {
							//inform with message
						});
			}

			return {
				getProductBySearchName : getProductBySearchName,
				getDetailsProduct : getDetailsProduct
			};
		} ]);