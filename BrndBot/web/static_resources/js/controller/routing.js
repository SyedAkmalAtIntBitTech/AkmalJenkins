brndBotSignupApp.config(function ($routeProvider) {
    $routeProvider
            .when('/signin', {
                templateUrl: 'signup/loginform.html',
                controller: 'onboardingController'
            })
            .when('/register', {
                templateUrl: 'signup/registration.html',
                controller: 'onboardingController'
            })
            .when('/company', {
                templateUrl: 'signup/company.html',
                controller: 'onboardingController'
            })
            .when('/datasource', {
                templateUrl: 'signup/datasource.html',
                controller: 'onboardingController'
            })
            .when('/uploadlogo', {
                templateUrl: 'signup/uploadlogo.html',
                controller: 'onboardingController'
            })
            .when('/choosepalette', {
                templateUrl: 'signup/choosepalette.html',
                controller: 'onboardingController'
            })
            .otherwise({redirectTo: '/signin'});
});
