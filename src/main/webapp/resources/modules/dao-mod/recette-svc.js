eComBioApp.factory('recetteSvc', [
		'$rootScope',
		'restBackendSvc',
		'$window',
		function($rootScope, restBackendSvc, $window) {
			
			var getRecetteBySearchName = function(searchString, listCategories,
					listCategoriesChoix,page,saison) {
				var restAdress = "recette/filter?";
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
				$rootScope.$broadcast('debug', restAdress);
				restBackendSvc.getItemsByUrl(restAdress).then(function(data) {
					var listRecette = data.data;
					angular.forEach(listRecette, function(recette, key) {
						recette['quotite']=0;
						recette['prixTotal']=0;
					});
					$rootScope.$broadcast('listRecettesSupplied',listRecette);
				}, function(reason) {
					$rootScope.$broadcast('debug', reason);
					if (reason.status == 404) {
						$rootScope.$broadcast('listRecettesSupplied', '');
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