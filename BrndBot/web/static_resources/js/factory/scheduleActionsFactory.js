
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('scheduleActionsFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var scheduleActionsFactoryObject = {};
    scheduleActionsFactoryObject.getActionsPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.getActionsURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    scheduleActionsFactoryObject.scheduleEmailPost = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduleEmailURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    scheduleActionsFactoryObject.scheduleEmailActionsPost = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduleEmailActionsURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    scheduleActionsFactoryObject.scheduleSocialPostActionsPost = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduleSocialPostActionsURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    scheduleActionsFactoryObject.scheduleSocialPostPost = function () {
        var deffered = $q.defer();
        var url = configurationService.scheduleSocialPostURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return scheduleActionsFactoryObject;
});