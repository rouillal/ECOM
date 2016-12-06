eComBioApp.factory('categorieSvc', [ '$rootScope', 'restBackendSvc', '$window',
		function($rootScope, restBackendSvc, $window) {
			var getAllCategories = function() {
				restBackendSvc.getItemsByUrl("categorie").then(function(data) {
					$rootScope.$broadcast('listCategoriesSupplied', data.data);
				});
				return [];
			}

			return {
				getAllCategories : getAllCategories
			};
		} ]);