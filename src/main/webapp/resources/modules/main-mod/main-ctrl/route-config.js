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
									'/basket',
									{
										templateUrl : 'resources/modules/basket-mod/basket.html',
										controller : 'BasketCtrl'
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