
// **************** @author Arfa Shakeel @ Intbit **************** //
 
app.factory('blockModelFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.emailBlockModelLookupURL();
        var data = '{"emailBlockId":"' + emailBlockId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allEmailBlockModelGetService = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.allEmailBlockModelURL();
        var data = '{"emailBlockId":"' + emailBlockId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.emailBlockModelGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.emailBlockModelURL();
        authenticatedServiceFactory.makeCall("GET", url,  "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.emailBlockModelIdGetService = function (emailBlockModelId) {
        var deffered = $q.defer();
        var url = configurationService.emailBlockModelIdURL();
        var data = '{"emailBlockModelId":"' + emailBlockModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.nonAddedEmailBlockModelGetService = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedEmailBlockModelURL();
        var data = '{"emailBlockId":"' + emailBlockId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveBlockModelPostService = function (emailBlockModel) {
        var deffered = $q.defer();
        var url = configurationService.saveBlockModelURL();
        var data = '{"emailBlockModel":"' + emailBlockModel + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveEmailBlockModelPostService = function (emailBlockModel) {
        var deffered = $q.defer();
        var url = configurationService.saveEmailBlockModelURL();
        var data = '{"emailBlockModel":"' + emailBlockModel + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateBlockModelPostService = function (emailBlockModel) {
        var deffered = $q.defer();
        var url = configurationService.updateBlockModelURL();
        var data = '{"emailBlockModel":"' + emailBlockModel + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteBlockModelGetService = function (emailBlockModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteBlockModelURL();
        var data = '{"emailBlockModelId":"' + emailBlockModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteEmailBlockModelGetService = function (emailBlockModelLookupId) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailBlockModelURL();
        var data = '{"emailBlockModelLookupId":"' + emailBlockModelLookupId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return service;
});

