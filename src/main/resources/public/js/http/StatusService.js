(function() {
    angular.module('rubixPricing').factory('serverHealthService', function($http, $q) {
        return {
            getServerHealthService: function () {
                var deferred = $q.defer();

                $http({method: 'GET', url: '/clusterDetails',
                    headers: {
                        'Access-Control-Allow-Origin': '*',
                        'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
                        'Access-Control-Allow-Headers': 'Content-Type, X-Requested-With'
                    }}).
                    success(function (data, status, headers, config) {
                    	//console.log("WORKING!!!" + data);
                        deferred.resolve(data);
                    }).
                    error(function (data, status, headers, config) {
                        //console.log( "ERROR!!!!" );
                        deferred.reject(status);
                    });
                return deferred.promise;
            },
            getClusterNodes: function () {
	            var deferred = $q.defer();
	
	            $http({method: 'GET', url: '/clusterNodes'}).
	                success(function (data, status, headers, config) {
	                	//console.log("WORKING!!!" + data);
	                    deferred.resolve(data);
	                }).
	                error(function (data, status, headers, config) {
	                    //console.log( "ERROR!!!!" );
	                    deferred.reject(status);
	                });
	            return deferred.promise;
            }
        };
    });
 })();