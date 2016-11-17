eComBioApp.factory('productSvc', [ '$rootScope', 'restBackendSvc', '$window',
		function($rootScope, restBackendSvc, $window) {

			var getMontantTotal = function() {
				return montantTotal;
			};

			return {
				setSelectedProduit : setSelectedProduit,
				getSelectedProduit : getSelectedProduit,
				getListePanier : getListePanier,
				changeProduit : changeProduit,
				getPanierQuantite : getPanierQuantite,
				supprimeArticlePanier : supprimeArticlePanier,
				getMontantTotal : getMontantTotal
			};
		} ]);