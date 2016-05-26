
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('yourPlanFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.scheduledEntitiesGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduledEntitiesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.changeSchedulePostService = function () {
        var deffered = $q.defer();
        var url = configurationService.changeScheduleURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.addActionPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.addActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.scheduledEmailGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduledEmailURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.scheduledSocialPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduledSocialPostURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.postToSocialPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.postToSocialURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return service;
});
