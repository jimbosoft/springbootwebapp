 (function() {
    angular.module('rubixPricing').factory('versionService', function($http, $q) 
    {
        return {
            getVersionService: function () 
            {
                var deferred = $q.defer();

                $http({method: 'GET', url: '/versionDetails',
                    headers: {
                    	'Content-Type': '*',
                        'Access-Control-Allow-Origin': '*',
                        'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
                        'Access-Control-Allow-Headers': 'Content-Type, X-Requested-With'
                    }}).
                    success(function (data, status, headers, config) {
                    	console.log("Version WORKING!!!" + data);
                        deferred.resolve(data);
                    }).
                    error(function (data, status, headers, config) {
                        deferred.reject(status);
                    });
                return deferred.promise;
            }
        };
    });
})();