eComBioApp
		.config([
				'$routeProvider',
				'$locationProvider',
				function($routeProvider, $locationProvider) {
					$routeProvider
							.when(
									'/catalog',
									{
										templateUrl : 'resources/modules/product-catalog-mod/product-catalog.html',
										controller : 'ProductCatalogCtrl'
									})
							.when(
									'/recipe',
									{
										templateUrl : 'resources/modules/recipe-mod/recipe.html',
										controller : 'RecipeCtrl'
									})
							.when(
									'/panier',
									{
										templateUrl : 'resources/modules/panier-mod/panier.html',
										controller : 'PanierCtrl'
									})
							.when(
									'/connect',
									{
										templateUrl : 'resources/modules/connect-mod/connect.html',
										controller : 'ConnectCtrl'
									}).otherwise({
								redirectTo : '/catalog'
							});

					$locationProvider.html5Mode(false);

				} ]);