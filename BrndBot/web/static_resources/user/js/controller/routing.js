dashboardFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/dashboard', {
                templateUrl: 'basedashboard.html',
                controller: 'dashboardController'
            })
            .when('/emailcategory', {
                templateUrl: 'emailcategory.html'
            })
            .when('/emailsubcategory', {
                templateUrl: 'emailsubcategory.html'
            })
            .when('/emailsubjects', {
                templateUrl: 'emailsubjects.html'
            })
            .when('/emailexternalsource', {
                templateUrl: 'emailexternalsource.html'
            })
             .otherwise({redirectTo: '/dashboard'});
    });
    
emailFlowApp.config(function ($routeProvider) {
    $routeProvider
           
            .when('/emailsubjects', {
                templateUrl: 'user/emailsubjects.html',
                controller: 'userController'
            })
            .when('/emaileditor', {
                templateUrl: 'user/emaileditor.html',
                controller: 'userController'
            })
            .when('/emaillistselection', {
                templateUrl: 'user/emaillistselection.html',
                controller: 'userController'
            })
          
            .otherwise({redirectTo: '/signin'});
});

socialFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/socialsequence', {
                templateUrl: 'user/socialsequence.html',
                controller: 'isDefaultFbPageSet'
            })
            .when('/facebookpost', {
                templateUrl: 'user/facebookpost.html',
                controller: 'displayImageFromGallery'
            })
            .when('/twitterpost', {
                templateUrl: 'user/twitterpost.html',
                controller: 'displayImageFromGallery'
            })
                    
            .otherwise({redirectTo: '/signin'});
});

yourPlanFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/marketing', {
                templateUrl: 'user/marketing.html',
                controller: 'controllerMarketingCampaign'
            })
                               
            .otherwise({redirectTo: '/signin'});
});

settingFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/settings', {
                templateUrl: 'user/settings.html',
                controller: 'controllerUserChanges'
            })
                               
            .otherwise({redirectTo: '/signin'});
});

userGalleryApp.config(function($routeProvider){
    $routeProvider
            .when('/imagegallery', {
                templateUrl: 'user/imagegallery.html',
                controller: 'myCtrl'
            })
                               
            .otherwise({redirectTo: '/signin'});
});

marketingFlowApp.config(function($routeProvider){
  
    $routeProvider
    .when('/createmarketingprogram', {
                templateUrl: 'basemarketingprogram.html',
                controller: 'marketingController'
    }) 
    
    .otherwise({redirectTo: '/createmarketingprogram'});
});