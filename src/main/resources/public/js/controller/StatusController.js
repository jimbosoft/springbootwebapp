(function() {
	app.controller('StatusController',
		    		
    function StatusController($scope, serverHealthService) 
	{
		serverHealthService.getServerHealthService().then(
				function(serverHealthDetails) 
				{
    				$scope.date = new Date();
					$scope.clusterDetails = serverHealthDetails; 
				},
					function(statusCode) { console.log(statusCode);}
		);
		                
		serverHealthService.getClusterNodes().then(
				function(clusterNodeNames) 
				{
				//	console.log("Got cluster here"); 
					$scope.clusterNodes = clusterNodeNames; 
				},
		        function(statusCode) 
		        { 
					console.log(statusCode);
				}
		);
	}
    );
})();
