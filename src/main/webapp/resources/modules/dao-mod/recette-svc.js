eComBioApp.factory('recetteSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		'imgProviderSvc',
		function($rootScope, restBackendSvc, $window,imgProviderSvc) {
			// tri : alpha diff cout
			var selectedRecette;
			
			var getRecetteBySearchName = function(searchString,
					listCategoriesRecette, listCategoriesRecetteChoix,
					listSaisons, listSaisonChoix, listComposition,
					listCompositionChoix, page,tri) {
				var restAdress = "/filter?";
				restAdress += 'page=' + page;
				var categorieSelected = '';
				angular.forEach(listCategoriesRecette,
						function(categorie, key) {
							if (listCategoriesRecetteChoix[key]) {
								if (categorieSelected != '') {
									categorieSelected += ',';
								}
								categorieSelected += categorie.id;
							}
						});
				if (categorieSelected != '') {
					restAdress += '&cat=' + categorieSelected;
				}
				var saisonSelected = '';
				angular.forEach(listSaisons, function(saison, key) {
					if (listSaisonChoix[key]) {
						if (saisonSelected != '') {
							saisonSelected += ',';
						}
						saisonSelected += saison.id;
					}
				});
				if (saisonSelected != '') {
					restAdress += '&saison=' + saisonSelected;
				}
				if (searchString != '') {
					restAdress += '&search=' + searchString;
				}
				var compoSelected = '';
				angular.forEach(listComposition, function(compo, key) {
					if (listCompositionChoix[key]) {
						if (compoSelected != '') {
							compoSelected += ',';
						}
						compoSelected += compo.id;
					}
				});
				if (compoSelected != '') {
					restAdress += '&compo=' + compoSelected;
				}
				restAdress += '&tri=' + tri;
				restBackendSvc.getItemsByUrl('recette'+restAdress).then(function(data) {
					var listRecette = data.data;
					angular.forEach(listRecette, function(recette, key) {
						recette['url']=imgProviderSvc.getImage(recette.filename);
					});
					$rootScope.$broadcast('listRecettesSupplied', listRecette);
				}, function(reason) {
					if (reason.status == 404) {
						$rootScope.$broadcast('listRecettesSupplied', '');
					} else {
						$rootScope.$broadcast('anomalieTechnique',reason);
					}
				});
				restBackendSvc.getItemsByUrl('recette/page'+restAdress).then(function(data) {
					var pageMax = data.data;
					$rootScope.$broadcast('pageMaxRecetteReset',pageMax);
				}, function(reason) {
					$rootScope.$broadcast('anomalieTechnique',reason);
				});
			}

			var getDetailsRecette = function(recetteId) {
				restBackendSvc
						.getItemsByUrl("recette/produits?id=" + recetteId)
						.then(
								function(data) {
									var recette = data.data;
									recette['url']=imgProviderSvc.getImage(recette.filename);
									$rootScope
											.$broadcast(
													'detailsRecetteSupplied',
													recette);
								},
								function(reason) {
									if (reason.status == 404) {
										$rootScope.$broadcast(
												'detailsRecetteSupplied', []);
									} else {
										$rootScope.$broadcast('anomalieTechnique',reason);
									}
								});
			}

			var setSelectDetailsRecette = function(selectedRecetteParam) {
				selectedRecette=selectedRecetteParam;
				$rootScope.$broadcast('selectDetailsRecetteProvided', selectedRecette);
			}
			
			var createRecette = function(recette) {
				restBackendSvc.createItem(recette).then(function(data) {
					// inform with message
				});
			}

			var updateRecette = function(recette) {
				restBackendSvc.updateItem(recette).then(function(data) {
					// inform with message
				});
			}

			var removeItem = function(recette) {
				restBackendSvc.deleteItem(recette._links.self.href).then(
						function(data) {
							// inform with message
						});
			}

			return {
				getRecetteBySearchName : getRecetteBySearchName,
				getDetailsRecette : getDetailsRecette,
				setSelectDetailsRecette : setSelectDetailsRecette
			};
		} ]);