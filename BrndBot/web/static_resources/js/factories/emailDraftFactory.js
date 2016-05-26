
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('emailDraftFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.saveEmailDraftsPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.saveEmailDraftsURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };    
    service.updateEmailDraftPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.updateEmailDraftURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.displayAllEmailDraftsGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.displayAllEmailDraftsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.getEmailDraftGetService = function (draftid) {
        var deffered = $q.defer();
        var url = configurationService.getEmailDraftURL();
        var data = '{"draftid":"' + draftid + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteEmailDraftsPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailDraftsURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteEmailDraftPostService = function (draftid) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailDraftURL();
        var data = '{"draftid":"' + draftid + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    
    return service;
});
