brndBotSignupApp.config(function ($routeProvider) {
    $routeProvider
            .when('/signin', {
                templateUrl: 'signup/loginform.html'
            })
            .when('/signup/resetpassword', {
                templateUrl: 'signup/resetpassword.html'
            })
             .when('/signup/changepassword', {
                templateUrl: 'signup/changepassword.html'
            })
            .when('/signup/company', {
                templateUrl: 'signup/company.html'
            })
            .when('/signup/userregistration', {
                templateUrl: 'signup/userregistration.html'
            })            
            .when('/signup/datasource', {
                templateUrl: 'signup/datasource.html'
            })
            .when('/signup/uploadlogo', {
                templateUrl: 'signup/uploadlogo.html'
            })
            .when('/signup/choosepalette', {
                templateUrl: 'signup/choosepalette.html'
            })
            .otherwise({redirectTo: '/signin'});
});
