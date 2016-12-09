eComBioApp.factory('recetteSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		function($rootScope, restBackendSvc, $window) {
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
				$rootScope.$broadcast('debug', restAdress);
				// Mock
				/*
				 * var listRecette = [{'name':'Tofu en
				 * bolo','nbPersonnes':4,'tpsPreparation':15,'tpsCuisson':20,'cout':'Bon
				 * marché','difficulte':'Très facile','listeIngredients':'<ul><li>1
				 * oignon,</li><li>2 carottes</li></ul>','preparation':'<ul><li>1.</li><li>2.</li><li>3.</li></ul>'},
				 * {'name':'Tofu2 en
				 * bolo','nbPersonnes':4,'tpsPreparation':15,'tpsCuisson':20,'cout':'Bon
				 * marché','difficulte':'Très facile','listeIngredients':'1
				 * oignon','preparation':'<ul><li>1.</li><li>2.</li><li>3.</li></ul>'}];
				 * var listRecetteJson = angular.toJson(listRecette);
				 */
				// Fin Mock
				restBackendSvc.getItemsByUrl('recette'+restAdress).then(function(data) {
					var listRecette = data.data;
					$rootScope.$broadcast('listRecettesSupplied', listRecette);
				}, function(reason) {
					$rootScope.$broadcast('debug', reason);
					if (reason.status == 404) {
						$rootScope.$broadcast('listRecettesSupplied', '');
					} else {
						alert('Failed: ' + reason);
					}
				});
				restBackendSvc.getItemsByUrl('recette/page'+restAdress).then(function(data) {
					var pageMax = data.data;
					$rootScope.$broadcast('pageMaxRecetteReset',pageMax);
				}, function(reason) {
					//$rootScope.$broadcast('debug', reason);
					//alert('Failed: ' + reason);
				});
			}

			var getDetailsRecette = function(recetteId) {
				restBackendSvc
						.getItemsByUrl("recette/produits?id=" + recetteId)
						.then(
								function(data) {
									$rootScope
											.$broadcast(
													'detailsRecetteSupplied',
													data.data);
								},
								function(reason) {
									$rootScope.$broadcast('debug', reason);
									if (reason.status == 404) {
										$rootScope.$broadcast(
												'detailsRecetteSupplied', []);
									} else {
										alert('Failed: ' + reason);
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