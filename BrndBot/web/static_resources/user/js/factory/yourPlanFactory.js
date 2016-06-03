
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('yourPlanFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var yourPlanFactoryObject = {};
    yourPlanFactoryObject.scheduledEntitiesGet = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduledEntitiesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    yourPlanFactoryObject.changeSchedulePost = function () {
        var deffered = $q.defer();
        var url = configurationService.changeScheduleURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    yourPlanFactoryObject.addActionPost = function () {
        var deffered = $q.defer();
        var url = configurationService.addActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    yourPlanFactoryObject.scheduledEmailGet = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduledEmailURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    yourPlanFactoryObject.scheduledSocialPost = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduledSocialPostURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    yourPlanFactoryObject.postToSocialPost = function () {
        var deffered = $q.defer();
        var url = configurationService.postToSocialURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return yourPlanFactoryObject;
});
