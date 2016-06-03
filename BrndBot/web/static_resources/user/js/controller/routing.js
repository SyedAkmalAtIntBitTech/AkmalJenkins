dashboardFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/dashboard', {
                templateUrl: 'basedashboard.html',
                controller: 'dashBoardController'
            })
            .when('/emailcategory', {
                templateUrl: 'emailcategory.html',
                controller: 'dashBoardController'
            })
            .otherwise({redirectTo: '/dashboard'});
    });
//            .when('/company', {
//                templateUrl: 'signup/company.html',
//                controller: 'onboardingController'
//            })
//            .when('/datasource', {
//                templateUrl: 'signup/datasource.html',
//                controller: 'onboardingController'
//            })
//            .when('/uploadlogo', {
//                templateUrl: 'signup/uploadlogo.html',
//                controller: 'onboardingController'
//            })
//            .when('/choosepalette', {
//                templateUrl: 'signup/choosepalette.html',
//                controller: 'onboardingController'
//            })
//            .otherwise({redirectTo: '/signin'});
            


