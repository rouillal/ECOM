eComBioApp
		.config([
				'$routeProvider',
				'$locationProvider',
				function($routeProvider, $locationProvider) {
					$routeProvider
							.when(
									'/catalog',
									{
										templateUrl : 'resources/modules/produit-modules/produit-catalog-mod/produit-catalog.html',
										controller : 'ProductCatalogCtrl'
									})
							.when(
									'/recette',
									{
										templateUrl : 'resources/modules/recette-catalog-mod/recette-catalog.html',
										controller : 'RecetteCtrl'
									})
							.when(
									'/panier',
									{
										templateUrl : 'resources/modules/panier-mod/panier.html',
										controller : 'PanierCtrl'
									})
							.when(
									'/command',
									{
										templateUrl : 'resources/modules/command-mod/command.html',
										controller : 'CommandCtrl'
									})
							.when(
									'/signin',
									{
										templateUrl : 'resources/modules/signin-mod/signin.html',
										controller : 'SigninCtrl'
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