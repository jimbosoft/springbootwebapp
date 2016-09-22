var app = angular.module('rubixPricing', ['ngResource', 'ngRoute', 'angularUtils.directives.dirPagination']);

(function() {
	app.config(function($routeProvider) {

		$routeProvider.when('/status', {
			templateUrl : 'status.html',
			controller : 'StatusController'
		});

		$routeProvider.otherwise({
			redirectTo : '/status'
		});
	});
})();