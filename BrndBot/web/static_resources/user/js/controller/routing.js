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
                templateUrl: 'basesocialsequence.html',
                controller: 'socialController'
            })
            .when('/facebookpost', {
                templateUrl: 'facebookpost.html',
                controller: 'displayImageFromGallery'
            })
            .when('/twitterpost', {
                templateUrl: 'twitterpost.html'
            })
            
            .otherwise({redirectTo: '/socialsequence'});
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
    .when('/marketingprogram', {
                templateUrl: 'marketingprogram.html'
    })
    .when('/usermarketingprogram', {
                templateUrl: 'usermarketingprogram.html',
                
    }) 
    
    .otherwise({redirectTo: '/createmarketingprogram'});
});