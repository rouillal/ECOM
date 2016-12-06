eComBioApp.factory('recetteSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		function($rootScope, restBackendSvc, $window) {
			// tri : alpha diff  cout
			var getRecetteBySearchName = function(searchString, listTypesRecette,
					listTypesRecetteChoix,isSaison,listComposition,
					listCompositionChoix,page) {
				var restAdress = "recette/filter?";
				restAdress += 'page=' + page;
				var typeSelected = '';
				angular.forEach(listTypesRecette, function(type, key) {
					if (listTypesRecetteChoix[key]) {
						if (typeSelected != '') {
							typeSelected += ',';
						}
						typeSelected += type.id;
					}
				});
				if (typeSelected != '') {
					restAdress += 'cat=' + typeSelected;
				}
				if (isSaison) {
					restAdress += 'saison=1&';
				}
				if (searchString != '') {
					restAdress += '&search=' + searchString;
				}
				var compoSelected = '';
				angular.forEach(listComposRecette, function(compo, key) {
					if (listComposRecetteChoix[key]) {
						if (compoSelected != '') {
							compoSelected += ',';
						}
						compoSelected += compo.id;
					}
				});
				$rootScope.$broadcast('debug', restAdress);
				//Mock
				var listRecette = [{'name':'Tofu en bolo','nbPersonnes':4,'tpsPreparation':15,'tpsCuisson':20,'cout':'Bon marché','difficulte':'Très facile','listeIngredients':'<ul><li>1 oignon,</li><li>2 carottes</li></ul>','preparation':'<ul><li>1.</li><li>2.</li><li>3.</li></ul>'},
				                   {'name':'Tofu2 en bolo','nbPersonnes':4,'tpsPreparation':15,'tpsCuisson':20,'cout':'Bon marché','difficulte':'Très facile','listeIngredients':'1 oignon','preparation':'<ul><li>1.</li><li>2.</li><li>3.</li></ul>'}];
				var listRecetteJson = angular.toJson(listRecette);
				//Fin Mock
				restBackendSvc.getItemsByUrl(restAdress).then(function(data) {
					var listRecette = data.data;
					$rootScope.$broadcast('listRecettesSupplied',listRecette);
				}, function(reason) {
					$rootScope.$broadcast('debug', reason);
					$rootScope.$broadcast('listRecettesSupplied',listRecette);
					if (reason.status == 404) {
						//$rootScope.$broadcast('listRecettesSupplied', '');
					} else {
						alert('Failed: ' + reason);
					}
				});
			}

			var getDetailsRecette = function(nameOrId) {
				restBackendSvc
						.getItemsByUrl("recette/id?parameter=" + nameOrId)
						.then(
								function(data) {
									$window.alert('Detail recette demande');
									$rootScope.$broadcast(
											'detailsRecetteSupplied', data);
								});
			}

			var createRecette = function(recette) {
				restBackendSvc.createItem(recette).then(function(data) {
					//inform with message
				});
			}

			var updateRecette = function(recette) {
				restBackendSvc.updateItem(recette).then(function(data) {
					//inform with message
				});
			}

			var removeItem = function(recette) {
				restBackendSvc.deleteItem(recette._links.self.href).then(
						function(data) {
							//inform with message
						});
			}

			return {
				getRecetteBySearchName : getRecetteBySearchName,
				getDetailsRecette : getDetailsRecette
			};
		} ]);