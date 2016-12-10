eComBioApp.factory('cookieStoreSvc', [ '$rootScope','$window','$cookies',
                                    function($rootScope,$window,$cookies) {
	
	var storeLocalString = function(name,info) {
		//$window.alert("to : "+name+" value : "+info);
		$cookies.put(name,info/*,{
		    expires: 600,
		    secure: true
		  }*/);
	}
	
	var getStoredLocalString = function(name) {
		//$window.alert("from : "+name+" value : "+$cookies.get(name));
		var ret=$cookies.get(name);
		if (typeof ret == 'undefined') {
			ret = '';
		}
		return ret;
	}
	
	var storeLocalItem = function(name,info) {
		var infoJson = angular.toJson(info);
		//$window.alert("to : "+name+" value : "+infoJson);
		$cookies.put(name,infoJson/*,{
			    expires: 600,
			    secure: true
			  }*/);
	}
	
	var getStoredLocalItem = function(name) {
		//$window.alert("from : "+name+" value : "+$cookies.get(name));
		var objetJson = $cookies.get(name);
		var ret = angular.fromJson(objetJson);
		if (typeof ret == 'undefined') {
			ret = [];
		}
		return ret;
	}

	return {
		storeLocalString : storeLocalString,
		getStoredLocalString : getStoredLocalString,
		storeLocalItem : storeLocalItem,
		getStoredLocalItem : getStoredLocalItem
	};
} ]);