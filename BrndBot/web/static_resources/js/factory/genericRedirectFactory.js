/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
factoryApp.factory('redirectFactory', function ($q,$window, authenticatedServiceFactory, configurationService) {
    var redirectObject = {};
    redirectObject.redirectFlowTo = function (pageName) {
        var deffered = $q.defer();
        $window.location=getHost()+"user/"+pageName;  
        return deffered.promise;
    }; 
    return redirectObject;
});

