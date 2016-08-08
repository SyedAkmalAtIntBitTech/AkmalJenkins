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
                title: 'Category',
                templateUrl: 'emailcategory.html'
            })
            .when('/emailsubcategory', {
                title: 'Sub-category',
                templateUrl: 'emailsubcategory.html'
            })
            .when('/emailexternalsource', {
                title: 'External Source',
                templateUrl: 'emailexternalsource.html'
            })
            .when('/emailsubjects', {
                title: 'BrndBot - Email Subject',
                templateUrl: 'emailsubjects.html'
            })            
            .when('/emaileditor', {
                title: 'BrndBot - Email Editor',
                templateUrl: 'emaileditor.html'
            }) 
            .when('/emaillistselection', {
                title: 'BrndBot - Email List Selection',
                templateUrl: 'emaillistselection.html'
            })    
            .when('/emaildetails', {
                title: 'BrndBot - Email Details',
                templateUrl: 'emaildetails.html'
            })    
            .otherwise({title: 'Category',redirectTo: '/emailcategory'});
}).run(function ($rootScope) {
        $rootScope.$on("$routeChangeSuccess", function (event, currentRoute) {
            document.title = currentRoute.title;
        });
});
socialFlowApp.config(function ($routeProvider) {
    $routeProvider    
            .when('/socialsequence', {
                title: 'Category',
                templateUrl: 'basesocialsequence.html',
                controller: 'socialController'
            })
            .when('/facebookpost', {
                title: 'Category',
                templateUrl: 'facebookpost.html'
            })
            .when('/twitterpost', {
                title: 'Category',
                templateUrl: 'twitterpost.html'
            })
            
            .otherwise({title: 'Category',redirectTo: '/socialsequence'});
}).run(function ($rootScope) {
        $rootScope.$on("$routeChangeSuccess", function (event, currentRoute) {
            document.title = currentRoute.title;
        });
});

yourPlanFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/marketing', {
                templateUrl: 'marketing.html',
            })
                               
            .otherwise({redirectTo: '/yourplan'});
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
                title: 'BrndBot - Create Marketing Program',
                templateUrl: 'basemarketingprogram.html'
               
    }) 
    .when('/marketingprogram', {
                title: 'Category',
                templateUrl: 'marketingprogram.html'
    })
    .when('/usermarketingprogram', {
                title: 'BrndBot - User Marketing Program',
                templateUrl: 'usermarketingprogram.html'    
                
    }) 
    .when('/marketingprogramlists', {
                title: 'BrndBot - Marketing Programs lists',
                templateUrl: 'marketingprogramlists.html'    
                
    }) 
    .when('/marketingprogramactions', {
                title: 'BrndBot - Marketing Program actions',
                templateUrl: 'marketingprogramactions.html'
                           
    }) 
     .when('/emailautomation', {
                title: 'BrndBot - Email Automation',
                templateUrl: 'emailautomation.html'
    }) 
    
    .otherwise({redirectTo: '/createmarketingprogram'});
}).run(function ($rootScope) {
        $rootScope.$on("$routeChangeSuccess", function (event, currentRoute) {
            document.title = currentRoute.title;
        });
});



marketinghubFlowApp.config(function($routeProvider){
    $routeProvider 
    .when('/marketinghub', { 
                title: 'BrndBot - Marketing Hub',
                templateUrl: 'marketinghub.html'          
    })
    .when('/emaildraft', { 
                title: 'BrndBot - Email Drafts',
                templateUrl: 'emaildraft.html'
    })
    .when('/emailhistory', { 
                title: 'BrndBot - Email History',
                templateUrl: 'emailhistory.html'
    })
    .when('/emaillist', { 
                title: 'BrndBot - Email Lists',
                templateUrl: 'emaillist.html'
    })
    .when('/emailsetting', { 
                title: 'BrndBot - Email Settings',
                templateUrl: 'emailsetting.html'
    })
    .when('/footersetting', { 
                title: 'BrndBot - Email Footer Settings',
                templateUrl: 'footersetting.html'
    })
    .when('/emaillistdetails', { 
                title: 'BrndBot - Email List Details',
                templateUrl: 'emaillistdetails.html'          
    })
    
    .otherwise({title: 'BrndBot - Email Lists',redirectTo: '/emaillist'});
    }).run(function ($rootScope) {
        $rootScope.$on("$routeChangeSuccess", function (event, currentRoute) {
            document.title = currentRoute.title;
        });
});

imagesFlowApp.config(function($routeProvider){
    $routeProvider
    .when('/imagegallery', {
                templateUrl: 'imagegallery.html'
               
    })
    
    .otherwise({redirectTo: '/baseimagegallery'});
    
});
