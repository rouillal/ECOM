eComBioApp.config([ '$httpProvider', '$locationProvider',
		function($httpProvider, $locationProvider) {
			$locationProvider.html5Mode(false);
			$httpProvider.defaults.useXDomain = true;
			delete $httpProvider.defaults.headers.common['X-Requested-With'];
		} ]);

eComBioApp.factory('restBackendSvc', [
		'$http',
		'$q',
		'$location',
		'$rootScope',
		function($http, $q, $location, $rootScope) {
			var backandGlobalUrlRoot = 'http://' + $location.host()
					+ ':8080/ECOM/rest/fruit';

			function getItems() {
				var request = $http({
					method : 'GET',
					url : backandGlobalUrlRoot

				});

				return sendRequest(request);
			}

			function sendRequest(config) {
				var deferred = $q.defer();

				config.then(function(response) {
					deferred.resolve(response);
					$rootScope.$broadcast('datasupplied', response);
				}, function(error) {
					$rootScope.$broadcast('anomalieTechnique', error);
					console.log("error");
					deferred.reject(error);
				});
				return deferred.promise;
			}

			return {
				getItems : getItems
			};
		} ]);