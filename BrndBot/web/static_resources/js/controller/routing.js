brndBotSignupApp.config(function ($routeProvider) {
    $routeProvider
            .when('/signin', {
                templateUrl: 'signup/loginform.html',
                controller: 'onboardingController'
            })
            .when('/signup/resetpassword', {
                templateUrl: 'signup/resetpassword.html',
                controller: 'onboardingController'
            })
            .when('/signup/company', {
                templateUrl: 'signup/company.html',
                controller: 'onboardingController'
            })
            .when('/signup/datasource', {
                templateUrl: 'signup/datasource.html',
                controller: 'onboardingController'
            })
            .when('/signup/uploadlogo', {
                templateUrl: 'signup/uploadlogo.html',
                controller: 'onboardingController'
            })
            .when('/signup/choosepalette', {
                templateUrl: 'signup/choosepalette.html',
                controller: 'onboardingController'
            })
            .otherwise({redirectTo: '/signin'});
});
