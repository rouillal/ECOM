eComBioApp
		.factory(
				'globalDataSvc',
				[
						'$rootScope',
						'restBackendSvc',
						function($rootScope, restBackendSvc) {
							var rubrique = 0;
							var cached_items = [ false, false ];

							var liste_items = [ [], [] ];

							var getListItems = function(rubrique_order, forced) {
								var ret = liste_items[rubrique_order];
								if ((!cached_items[rubrique_order])
										|| (forced == true)) {
									restBackendSvc
											.getItems(rubrique_order)
											.then(
													function(data) {
														switch (rubrique_order
																.toString()) {
														case '0':
															liste_items[rubrique_order] = data.data;
															break;
														case '1':
															liste_items[rubrique_order] = data.data;
															break;
														}
														cached_items[rubrique_order] = true;
														$rootScope.$broadcast(
																'datasupplied',
																rubrique_order);
													});
								}
								return ret;
							}
							
							var initMainLists = function() {
								//Get all products
								getListItems(0,false);
								//Get all categories
								getListItems(1,false);
							}

							var persistItem = function(rubrique, item) {
								var creationcase = (item._links.self.href == " ");
								/*
								 * $rootScope.$broadcast('debug', creationcase);
								 */
								if (creationcase) {
									restBackendSvc.createItem(rubrique, item)
											.then(function(data) {
												getListItems(rubrique, true);
											});
								} else {
									restBackendSvc.updateItem(
											item._links.self.href, item).then(
											function(data) {
												getListItems(rubrique, true);
											});
								}
							}
							
							var removeItem = function(rubrique, selectedItem) {
								var nonecreationcase = !(selectedItem._links.self.href == " ");
								if (nonecreationcase) {
									restBackendSvc.deleteItem(
											selectedItem._links.self.href)
											.then(function(data) {
												getListItems(rubrique, true);
											});
								} else {
									getListItems(rubrique, true);
								}
							}
							
							return {
								getListItems : getListItems,
								initMainLists : initMainLists,
								persistItem : persistItem,
								removeItem : removeItem				
							};
						} ]);