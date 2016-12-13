eComBioApp.factory('productSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		'imgProviderSvc',
		function($rootScope, restBackendSvc, $window,imgProviderSvc) {
			
			var getEmptyItem = function() {
				var emptyItem = {"categorie":{"id":1,"name":"Fruits"},"name":'',"variete":'',"unite":'',"quantite":0,"stock":0,"prix":0.0,"filename":'',"provenance":'',"dateCueillette":'',"dureeConservation":0,"calories":0,"glucides":0,"fibres":0,"proteines":0};
				return emptyItem;
			}
			
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
				restBackendSvc.getItemsByUrl('produit'+restAdress).then(function(data) {
					var listProduit = data.data;
					angular.forEach(listProduit, function(produit, key) {
						produit['quotite']=0;
						produit['prixTotal']=0;
						produit['url']=imgProviderSvc.getImage(produit.filename);
					});
					$rootScope.$broadcast('listProductsSupplied',listProduit);
				}, function(reason) {
					if (reason.status == 404) {
						$rootScope.$broadcast('listProductsSupplied', '');
					} else {
						$rootScope.$broadcast('anomalieTechnique',reason);
					}
				});
				restBackendSvc.getItemsByUrl('produit/page'+restAdress).then(function(data) {
					var pageMax = data.data;
					$rootScope.$broadcast('pageMaxProduitReset',pageMax);
				}, function(reason) {
					$rootScope.$broadcast('anomalieTechnique',reason);
				});
			}

			var createProduct = function(product) {
				var messageServeurJson = angular.toJson(product);
				restBackendSvc.createItem('produit',messageServeurJson).then(function(data) {
					$rootScope.$broadcast('createProduitOk');
				});
			}

			var updateProductToEdit = function(updateProductToEdit) {
				$rootScope.$broadcast('updateProductToEdit',updateProductToEdit);
			}
			
			var updateProduct = function(productUpdatedParam) {
				var productServeur = getEmptyItem();
				productServeur['id']=productUpdatedParam.id;
				productServeur.name=productUpdatedParam.name;
				productServeur.categorie=productUpdatedParam.categorie;
				productServeur.variete=productUpdatedParam.variete;
				productServeur.unite=productUpdatedParam.unite;
				productServeur.quantite=productUpdatedParam.quantite;
				productServeur.stock=productUpdatedParam.stock;
				productServeur.prix=productUpdatedParam.prix;
				productServeur.filename=productUpdatedParam.filename;
				productServeur.provenance=productUpdatedParam.provenance;
				productServeur.dateCueillette=productUpdatedParam.dateCueillette;
				productServeur.dureeConservation=productUpdatedParam.dureeConservation;
				productServeur.calories=productUpdatedParam.calories;
				productServeur.glucides=productUpdatedParam.glucides;
				productServeur.fibres=productUpdatedParam.fibres;
				productServeur.proteines=productUpdatedParam.proteines;
				var messageServeurJson = angular.toJson(productServeur);
				restBackendSvc.updateItem('produit',messageServeurJson).then(function(data) {
					//inform with message
					$rootScope.$broadcast('updateProduitOk');
				});
			}
			
			var updateStock = function(productUpdatedParam) {
				var productServeur = getEmptyItem();
				productServeur['id']=productUpdatedParam.id;
				productServeur.name=productUpdatedParam.name;
				productServeur.categorie=productUpdatedParam.categorie;
				productServeur.variete=productUpdatedParam.variete;
				productServeur.unite=productUpdatedParam.unite;
				productServeur.quantite=productUpdatedParam.quantite;
				productServeur.stock=productUpdatedParam.stock;
				productServeur.prix=productUpdatedParam.prix;
				productServeur.filename=productUpdatedParam.filename;
				productServeur.provenance=productUpdatedParam.provenance;
				productServeur.dateCueillette=productUpdatedParam.dateCueillette;
				productServeur.dureeConservation=productUpdatedParam.dureeConservation;
				productServeur.calories=productUpdatedParam.calories;
				productServeur.glucides=productUpdatedParam.glucides;
				productServeur.fibres=productUpdatedParam.fibres;
				productServeur.proteines=productUpdatedParam.proteines;
				var messageServeurJson = angular.toJson(productServeur);
				restBackendSvc.updateItem('produit',messageServeurJson).then(function(data) {
					//inform with message
					$rootScope.$broadcast('updateStockOk');
				});
			}

			var removeItem = function(product) {
				restBackendSvc.deleteItem(product._links.self.href).then(
						function(data) {
							//inform with message
						});
			}

			return {
				getEmptyItem : getEmptyItem,
				getProductBySearchName : getProductBySearchName,
				createProduct : createProduct,
				updateProductToEdit : updateProductToEdit,
				updateStock : updateStock,
				updateProduct : updateProduct,
				removeItem : removeItem
			};
		} ]);