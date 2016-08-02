brndBotSignupApp.config(function ($routeProvider) {
    $routeProvider
            .when('/signin', {
                title: 'BrndBot - Login',
                templateUrl: 'signup/loginform.html'
            })
            .when('/signup/resetpassword', {
                title: 'BrndBot - Reset-Password',
                templateUrl: 'signup/resetpassword.html'
            })
            .when('/signup/changepassword', {
                title: 'BrndBot - Reset-Password',
                templateUrl: 'signup/changepassword.html'
            })
            .when('/signup/company', {
                title: 'BrndBot-On Boarding',
                templateUrl: 'signup/company.html'
            })
            .when('/signup/datasource', {
                title: 'BrndBot-On Boarding',
                templateUrl: 'signup/datasource.html'
            })
            .when('/signup/uploadlogo', {
                title: 'BrndBot-On Boarding',
                templateUrl: 'signup/uploadlogo.html'
            })
            .when('/signup/choosepalette', {
                title: 'BrndBot- Color Palette',
                templateUrl: 'signup/choosepalette.html'
            })
            .otherwise({redirectTo: '/signin'});
}).run(function ($rootScope) {
        $rootScope.$on("$routeChangeSuccess", function (event, currentRoute) {
            document.title = currentRoute.title;
        });
});