dashboardFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/dashboard', {
                templateUrl: 'basedashboard.html',
                controller: 'dashboardController'
            })
            .otherwise({redirectTo: '/dashboard'});
    });
    
emailFlowApp.config(function ($routeProvider) {
    $routeProvider            
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
            .when('/emailsubjects', {
                templateUrl: 'emailsubjects.html'
            }) 
            .when('/baseemaileditor', {
                templateUrl: 'emaileditor.html'
            })
            .when('/emaillistselection', {
                templateUrl: 'emaillistselection.html'
            })            
            .otherwise({redirectTo: '/emailcategory'});
});

socialFlowApp.config(function ($routeProvider) {
    $routeProvider    
            .when('/socialsequence', {
                templateUrl: 'basesocialsequence.html',
                controller: 'socialController'
            })
            .when('/facebookpost', {
                templateUrl: 'facebookpost.html'
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
                templateUrl: 'basemarketingprogram.html'
               
    }) 
    .when('/marketingprogram', {
                templateUrl: 'marketingprogram.html'
    })
    .when('/usermarketingprogram', {
                templateUrl: 'usermarketingprogram.html'
            
               
        
                
    }) 
    
    .otherwise({redirectTo: '/createmarketingprogram'});
});