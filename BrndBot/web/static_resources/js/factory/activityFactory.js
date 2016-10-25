/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
factoryApp.factory('activityFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var activityFactoryObject = {};
    
        activityFactoryObject.activitiesGet = function () {
        var deffered = $q.defer();
        var url = configurationService.activitiesGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return activityFactoryObject;

});

