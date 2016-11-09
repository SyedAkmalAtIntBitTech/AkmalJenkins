/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

factoryApp.factory('behaviorFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var behaviorFactoryObject = {};
    behaviorFactoryObject.revenueCategoryGet = function () {
        var deffered = $q.defer();
        var url = configurationService.revenueCategoryGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    behaviorFactoryObject.serviceCategoryGet = function () {
        var deffered = $q.defer();
        var url = configurationService.serviceCategoryGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    behaviorFactoryObject.pricingOptionGet = function () {
        var deffered = $q.defer();
        var url = configurationService.pricingOptionGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    behaviorFactoryObject.dollarAmountGet = function () {
        var deffered = $q.defer();
        var url = configurationService.dollarAmountGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
});
