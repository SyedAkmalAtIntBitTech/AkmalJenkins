/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

factoryApp.factory('behaviorFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var behaviorFactoryObject = {};
    behaviorFactoryObject.revenueCategoryGet = function (revenueType) {
        var deffered = $q.defer();
        var url = configurationService.revenueCategoryGetURL()+"?revenueType="+ revenueType;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    behaviorFactoryObject.serviceCategoryGet = function (scheduleType, onlineOnly) {
        var deffered = $q.defer();
        var url = configurationService.serviceCategoryGetURL()+"?scheduleType="+scheduleType+"&onlineOnly="+onlineOnly;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    behaviorFactoryObject.pricingOptionGet = function (programIds) {
        var deffered = $q.defer();
        var url = configurationService.pricingOptionGetURL()+"?programIds="+programIds;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    behaviorFactoryObject.siteLocationsGet = function () {
        var deffered = $q.defer();
        var url = configurationService.siteLocationsGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return behaviorFactoryObject;
});
