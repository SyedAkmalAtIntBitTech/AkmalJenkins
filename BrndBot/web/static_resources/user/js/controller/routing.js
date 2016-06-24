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
            .when('/emailexternalsource', {
                templateUrl: 'emailexternalsource.html'
            })
            .when('/emailsubjects', {
                templateUrl: 'emailsubjects.html'
            })            
            .when('/emaileditor', {
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
            
            .when('/useraccountsettings', {
                templateUrl: 'useraccountsettings.html'
            })
            .when('/userlogosettings', {
                templateUrl: 'userlogosettings.html'
            })
            .when('/userpalettesettings', {
                templateUrl: 'userpalettesettings.html'
            })
            .otherwise({redirectTo: '/useraccountsettings'});
});

userGalleryApp.config(function($routeProvider){
    $routeProvider
            .when('/imagegallery', {
                templateUrl: 'user/imagegallery.html',
                
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
    .when('/marketingprogramlists', {
                templateUrl: 'marketingprogramlists.html'    
                
    }) 
    .when('/marketingprogramactions', {
                templateUrl: 'marketingprogramactions.html',
              
                
    }) 
    
    .otherwise({redirectTo: '/createmarketingprogram'});
});



marketinghubFlowApp.config(function($routeProvider){
    $routeProvider   
    .when('/marketinghub', {
                templateUrl: 'marketinghub.html'          
    }) 
    
    .otherwise({redirectTo: '/marketinghub'});
    });

imagesFlowApp.config(function($routeProvider){
    $routeProvider
    .when('/imagegallery', {
                templateUrl: 'imagegallery.html'
               
    })
    
    .otherwise({redirectTo: '/baseimagegallery'});
    
});
