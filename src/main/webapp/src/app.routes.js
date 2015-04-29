currencyConverterApp.config(['$routeProvider', function($routeProvider) {
	
    $routeProvider

        // route for the home page
        .when('/', {
            templateUrl : '/client/components/home/homeView.html',
            controller  : 'homeCtrl'
        });
}]);