dashboardFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/dashboard', {
                templateUrl: 'basedashboard.html',
                controller: 'dashboardController'
            })
            .when('/emailcategory', {
                templateUrl: 'emailcategory.html',
                controller: 'dashboardController'
            })
            .when('/emailsubcategory', {
                templateUrl: 'emailsubcategory.html',
                controller: 'dashboardController'
            })
            .when('/emailsubjects', {
                templateUrl: 'emailsubjects.html',
                controller: 'dashboardController'
            })
            .when('/emailexternalsource', {
                templateUrl: 'emailexternalsource.html',
                controller: 'dashboardController'
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
            .when('/marketingprogramlists', {
                templateUrl: 'user/marketingprogramlists.html',
                controller: 'controllerUserMarketingProgamsByStatus'
            })
              .when('/marketingprogramactions', {
                templateUrl: 'user/marketingprogramactions.html',
                controller: 'programactions'
            })
            .when('/emailautomation', {
                templateUrl: 'user/emailautomation.html',
                controller: 'emailautomation'
            })
            .when('/createmarketingprogram', {
                templateUrl: 'user/createmarketingprogram.html',
                controller: 'controllerMarketingCategories'
            })
            .when('/marketingprogram', {
                templateUrl: 'user/marketingprogram.html',
                controller: 'userController'
            })
            .when('/usermarketingprogram', {
                templateUrl: 'user/usermarketingprogram.html',
                controller: 'usermarketingprogram'
            })
             .when('/emaillists', {
                templateUrl: 'user/emaillists.html',
                controller: 'EmailListController'
            })
              .when('/emaillistsdetails', {
                templateUrl: 'user/emaillists.html',
                controller: 'EmailListController'
            })
                               
            .otherwise({redirectTo: '/signin'});
});