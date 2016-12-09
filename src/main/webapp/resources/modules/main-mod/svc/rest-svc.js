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
		'$window',
		function($http, $q, $location, $rootScope,$window) {
			var backandGlobalUrlRoot = 'http://' + $location.host()
					+ ':8080/ECOM/rest/';
			var backandGlobalUrl = [backandGlobalUrlRoot + 'produit',
			backandGlobalUrlRoot + 'categorie'];

			function getItems(rubrique) {
				var request = $http({
					method : 'GET',
					url : backandGlobalUrl[rubrique]

				});

				return sendRequest(request);
			}
			
			function getItemsByUrl(url) {
				var urlTmp = backandGlobalUrlRoot + url;
				var request = $http({
					method : 'GET',
					url : urlTmp
				});

				return sendRequest(request);
			}
			
			function createItem(url, body) {
				var urlTmp = backandGlobalUrlRoot + url;
				//$window.alert('hhelo');
				var request = $http({
					method : 'POST',
					data : body,
					url : urlTmp
				});
				return sendRequest(request);
			}
			
			function updateItem(url, body) {
				var urlTmp = backandGlobalUrlRoot + url;
				var request = $http({
					method : 'PUT',
					data : body,
					url : urlTmp
				});
				return sendRequest(request);
			}

			function deleteItem(urlid) {
				var urlTmp = backandGlobalUrlRoot + url;
				var request = $http({
					method : 'DELETE',
					url : urlid
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
			
			function sendRequest2(config) {
				var deferred = $q.defer();

				config.then(function(response) {
					deferred.resolve(response);
					$rootScope.$broadcast('datasupplied', response);
				}, function(error) {
					$rootScope.$broadcast('debug', error);
					if (error.status == 404) {
						$rootScope.$broadcast('datasupplied', response);
					} else {
						$rootScope.$broadcast('anomalieTechnique', error);
						console.log("error");
						deferred.reject(error);
					}
				});
				return deferred.promise;
			}

			return {
				createItem : createItem,
				getItems : getItems,
				getItemsByUrl : getItemsByUrl,
				updateItem : updateItem,
				deleteItem : deleteItem
			};
		} ]);