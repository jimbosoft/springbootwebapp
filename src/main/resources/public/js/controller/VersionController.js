
(function() {
    app.controller('VersionController',
    function VersionController($scope, versionService, $filter) 
    {
        versionService.getVersionService().then(
            function(data) {
            	$scope.date = new Date();
                $scope.versionData = data;                
            },
            function(statusCode) { console.log(statusCode);}
        );
    }
    );
    
})();

(function() {
    app.controller('mainCtrl',
    function MainController($scope, $rootScope, $location) {
  	  
  	  $scope.menu = [
  	    {label:'Home', route:'/'},
  	    {label:'About', route:'/about'},
  	    {label:'Contact', route:'/contact'}
  	   ]
  	  
  	  $scope.menuActive = '/';
  	  
  	  $rootScope.$on('$routeChangeSuccess', function(e, curr, prev) {
         $scope.menuActive = $location.path();
      });
})
})();
        
//	var app = angular.module('rubixPricing', ['ngRoute']);

   app.controller('homeCtrl', function($scope) {
		
		$scope.message = 'Everyone come and see how good I look!';
	});

	app.controller('aboutCtrl', function($scope) {
		$scope.message = 'Look! I am an about page.';
	});

	app.controller('contactCtrl', function($scope) {
		$scope.message = 'Contact us! JK. This is just a demo.';
	});