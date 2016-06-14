
// **************** @author Arfa Shakeel @ Intbit **************** //

factoryApp.factory('blockModelFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var blockModelFactoryObject = {};
    blockModelFactoryObject.emailBlockModelLookupGet = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.emailBlockModelLookupURL();
        var data = '{"emailBlockId":"' + emailBlockId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    blockModelFactoryObject.allEmailBlockModelGet = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.allEmailBlockModelURL()+"?emailBlockId="+emailBlockId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    blockModelFactoryObject.emailBlockModelGet = function () {
        var deffered = $q.defer();
        var url = configurationService.emailBlockModelURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    blockModelFactoryObject.emailBlockModelIdGet = function (emailBlockModelId) {
        var deffered = $q.defer();
        var url = configurationService.emailBlockModelIdURL();
        var data = '{"emailBlockModelId":"' + emailBlockModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    blockModelFactoryObject.nonAddedEmailBlockModelGet = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedEmailBlockModelURL();
        var data = '{"emailBlockId":"' + emailBlockId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    blockModelFactoryObject.saveBlockModelPost = function (emailBlockModel) {
        var deffered = $q.defer();
        var url = configurationService.saveBlockModelURL();
        var data = '{"emailBlockModel":"' + emailBlockModel + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    blockModelFactoryObject.saveEmailBlockModelPost = function (emailBlockModel) {
        var deffered = $q.defer();
        var url = configurationService.saveEmailBlockModelURL();
        var data = '{"emailBlockModel":"' + emailBlockModel + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    blockModelFactoryObject.updateBlockModelPost = function (emailBlockModel) {
        var deffered = $q.defer();
        var url = configurationService.updateBlockModelURL();
        var data = '{"emailBlockModel":"' + emailBlockModel + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    blockModelFactoryObject.deleteBlockModelGet = function (emailBlockModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteBlockModelURL();
        var data = '{"emailBlockModelId":"' + emailBlockModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    blockModelFactoryObject.deleteEmailBlockModelGet = function (emailBlockModelLookupId) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailBlockModelURL();
        var data = '{"emailBlockModelLookupId":"' + emailBlockModelLookupId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return blockModelFactoryObject;
});

