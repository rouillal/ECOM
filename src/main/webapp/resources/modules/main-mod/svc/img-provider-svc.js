eComBioApp.factory('imgProviderSvc', [
		'$location',
		'$window',
		function($location, $window) {
			var imgUrlRoot = 'http://' + $location.host()
					+ ':8080/ECOM/image?name=';
			
			function getImage(nomFichier) {
				var response = imgUrlRoot + nomFichier;
				return response;
			}
			
			return {
				getImage : getImage
			};
		} ]);