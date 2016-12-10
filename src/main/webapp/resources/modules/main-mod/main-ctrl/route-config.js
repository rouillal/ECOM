eComBioApp
		.config([
				'$routeProvider',
				'$locationProvider',
				function($routeProvider, $locationProvider) {
					$routeProvider
							.when(
									'/catalogue',
									{
										templateUrl : 'resources/modules/produit-modules/produit-catalog-mod/produit-catalog.html',
										controller : 'ProduitCatalogCtrl'
									})
							.when(
									'/recette',
									{
										templateUrl : 'resources/modules/recette-modules/recette-catalog-mod/recette-catalog.html',
										controller : 'RecetteCatalogCtrl'
									})
							.when(
									'/panier',
									{
										templateUrl : 'resources/modules/panier-mod/panier.html',
										controller : 'PanierCtrl'
									})
							.when(
									'/commande',
									{
										templateUrl : 'resources/modules/commande-modules/commande-crea-mod/commande-crea.html',
										controller : 'CommandeCreaCtrl'
									})
							.when(
									'/gestion',
									{
										templateUrl : 'resources/modules/commande-modules/commande-admin-mod/commande-admin.html',
										controller : 'CommandeAdminCtrl'
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
								redirectTo : '/catalogue'
							});

					$locationProvider.html5Mode(false);

				} ]);