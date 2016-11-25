eComBioApp.factory('productSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		'imgProviderSvc',
		function($rootScope, restBackendSvc, $window,imgProviderSvc) {
			
			var getProductBySearchName = function(searchString, listCategories,
					listCategoriesChoix, page,saison,tri) {
				var restAdress = "/filter?";
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
				
				if (saison) {
					restAdress += 'saison=1&';
				}
				
				restAdress += 'page=' + page;
				restAdress += '&tri=' + tri;
				$rootScope.$broadcast('debug', restAdress);
				restBackendSvc.getItemsByUrl('produit'+restAdress).then(function(data) {
					var listProduit = data.data;
					angular.forEach(listProduit, function(produit, key) {
						produit['quotite']=0;
						produit['prixTotal']=0;
						produit['url']=imgProviderSvc.getImage(produit.filename);
					});
					$rootScope.$broadcast('listProductsSupplied',listProduit);
				}, function(reason) {
					$rootScope.$broadcast('debug', reason);
					if (reason.status == 404) {
						$rootScope.$broadcast('listProductsSupplied', '');
					} else {
						alert('Failed: ' + reason);
					}
				});
				restBackendSvc.getItemsByUrl('produit/page'+restAdress).then(function(data) {
					var pageMax = data.data;
					$rootScope.$broadcast('pageMaxReset',pageMax);
				}, function(reason) {
					$rootScope.$broadcast('debug', reason);
					alert('Failed: ' + reason);
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
				getProductBySearchName : getProductBySearchName
			};
		} ]);