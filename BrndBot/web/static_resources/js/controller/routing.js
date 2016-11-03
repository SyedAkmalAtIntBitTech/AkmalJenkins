brndBotSignupApp.config(function ($routeProvider) {
    $routeProvider
            .when('/signin', {
                title: 'BrandBot - Login',
                templateUrl: 'signup/loginform.html'
            })
            .when('/signup/resetpassword', {
                title: 'BrandBot - Reset-Password',
                templateUrl: 'signup/resetpassword.html'
            })
            .when('/signup/changepassword', {
                title: 'BrandBot - Reset-Password',
                templateUrl: 'signup/changepassword.html'
            })
            .when('/signup/company', {
                title: 'BrandBot-On Boarding',
                templateUrl: 'signup/company.html'
            })
            .when('/signup/userregistration', {
                title: 'BrandBot - User Signup',
                templateUrl: 'signup/userregistration.html'
            })
            .when('/signup/datasource', {
                title: 'BrandBot-On Boarding',
                templateUrl: 'signup/datasource.html'
            })
            .when('/signup/uploadlogo', {
                title: 'BrandBot-On Boarding',
                templateUrl: 'signup/uploadlogo.html'
            })
            .when('/signup/choosepalette', {
                title: 'BrandBot- Color Palette',
                templateUrl: 'signup/choosepalette.html'
            })
            .otherwise({title: 'BrandBot - Login', redirectTo: '/signin'});
}).run(function ($rootScope) {
    $rootScope.$on("$routeChangeSuccess", function (event, currentRoute) {
        document.title = currentRoute.title;
    });
});


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
                title: 'BrandBot - Email Subject',
                templateUrl: 'emailsubjects.html'
            })
            .when('/emaileditor', {
                title: 'BrandBot - Email Editor',
                templateUrl: 'emaileditor.html'
            })
            .when('/emaillistselection', {
                title: 'BrandBot - Email List Selection',
                templateUrl: 'emaillistselection.html'
            })
            .when('/emaildetails', {
                title: 'BrandBot - Email Details',
                templateUrl: 'emaildetails.html'
            })
            .when('/franchisecompanies', {
                title: 'BrandBot - Franchise Companies',
                templateUrl: 'franchisecompanies.html'
            })
            .otherwise({title: 'Category', redirectTo: '/emailcategory'});
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

            .otherwise({title: 'Category', redirectTo: '/socialsequence'});
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
             .when('/userusersettings', {
                templateUrl: 'userusersettings.html'
            })
            .when('/userpalettesettings', {
                templateUrl: 'userpalettesettings.html'
            })
            .when('/settingsmanagedaccount', {
                templateUrl: 'settingsmanagedaccount.html'
            })
            .otherwise({redirectTo: '/useraccountsettings'});
});

userGalleryApp.config(function ($routeProvider) {
    $routeProvider
            .when('/imagegallery', {
                templateUrl: 'user/imagegallery.html',
            })

            .otherwise({redirectTo: '/signin'});
});

marketingFlowApp.config(function ($routeProvider) {

    $routeProvider
            .when('/createmarketingprogram', {
                title: 'BrandBot - Create Marketing Program',
                templateUrl: 'basemarketingprogram.html'

            })
            .when('/marketingprogram', {
                title: 'Category',
                templateUrl: 'marketingprogram.html'
            })
            .when('/usermarketingprogram', {
                title: 'BrandBot - User Marketing Program',
                templateUrl: 'usermarketingprogram.html'

            })
            .when('/marketingprogramlists', {
                title: 'BrandBot - Marketing Programs lists',
                templateUrl: 'marketingprogramlists.html'

            })
            .when('/marketingprogramactions', {
                title: 'BrandBot - Marketing Program actions',
                templateUrl: 'marketingprogramactions.html'

            })
            .when('/emailautomation', {
                title: 'BrandBot - Email Automation',
                templateUrl: 'emailautomation.html'
            })

            .otherwise({redirectTo: '/createmarketingprogram'});
}).run(function ($rootScope) {
    $rootScope.$on("$routeChangeSuccess", function (event, currentRoute) {
        document.title = currentRoute.title;
    });
});



marketinghubFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/marketinghub', {
                title: 'BrandBot - Marketing Hub',
                templateUrl: 'marketinghub.html'
            })
            .when('/emaildraft', {
                title: 'BrandBot - Email Drafts',
                templateUrl: 'emaildraft.html'
            })
            .when('/emailhistory', {
                title: 'BrandBot - Email History',
                templateUrl: 'emailhistory.html'
            })
            .when('/emaillist', {
                title: 'BrandBot - Email Lists',
                templateUrl: 'emaillist.html'
            })
            .when('/emailsetting', {
                title: 'BrandBot - Email Settings',
                templateUrl: 'emailsetting.html'
            })
            .when('/footersetting', {
                title: 'BrandBot - Email Footer Settings',
                templateUrl: 'footersetting.html'
            })
            .when('/emaillistdetails', {
                title: 'BrandBot - Email List Details',
                templateUrl: 'emaillistdetails.html'
            })

            .otherwise({title: 'BrandBot - Email Lists', redirectTo: '/emaillist'});
}).run(function ($rootScope) {
    $rootScope.$on("$routeChangeSuccess", function (event, currentRoute) {
        document.title = currentRoute.title;
    });
});

imagesFlowApp.config(function ($routeProvider) {
    $routeProvider
            .when('/imagegallery', {
                templateUrl: 'imagegallery.html'

            })

            .otherwise({redirectTo: '/baseimagegallery'});

});

franchiseHubApp.config(function ($routeProvider) {
    $routeProvider
            .when('/managedaccountdash', {
                title: 'BrandBot - Account Manager Dashboard',
                templateUrl: 'managedaccountdash.html'
            })

            .when('/managedaccounts', {
                title: 'BrandBot - Managed Accounts',
                templateUrl: 'managedaccounts.html'
            })
            .when('/pushedemailhistory', {
                title: 'BrandBot - Pushed Emails',
                templateUrl: 'pushedemailhistory.html'
            })
            .when('/pushedemaildrafts', {
                title: 'BrandBot - Pushed Emails',
                templateUrl: 'pushedemaildrafts.html'
            })
            .when('/emailtags', {
                title: 'BrandBot - Email Tags',
                templateUrl: 'emailtags.html'
            })
            .when('/pushedemaildetailbase', {
                title: 'BrandBot - Pushed Emails',
                templateUrl: 'pushedemaildetailbase.html'
            })
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
                title: 'BrandBot - Email Subject',
                templateUrl: 'emailsubjects.html'
            })
            .when('/emaileditor', {
                title: 'BrandBot - Email Editor',
                templateUrl: 'emaileditor.html'
            })
            .when('/emaillistselection', {
                title: 'BrandBot - Email List Selection',
                templateUrl: 'emaillistselection.html'
            })
            .when('/emaildetails', {
                title: 'BrandBot - Email Details',
                templateUrl: 'emaildetails.html'
            })


            .otherwise({title: 'BrandBot - Account Manager Dashboard', redirectTo: '/managedaccountdash'});
}).run(function ($rootScope) {
    $rootScope.$on("$routeChangeSuccess", function (event, currentRoute) {
        document.title = currentRoute.title;
    });
});
