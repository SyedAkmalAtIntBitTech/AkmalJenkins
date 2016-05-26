
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('scheduleActionsFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function () {
        var deffered = $q.defer();
        var url = configurationService.getActionsURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.scheduleEmailPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduleEmailURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.scheduleEmailActionsPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduleEmailActionsURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.scheduleSocialPostActionsPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduleSocialPostActionsURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.scheduleSocialPostPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduleSocialPostURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return service;
});