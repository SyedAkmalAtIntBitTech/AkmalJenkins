
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('emailBlockFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function (emailBlockDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveEmailBlockURL();
        var data = '{"emailBlockDetails":"' + emailBlockDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteEmailBlockGetService = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailBlockURL();
        var data = '{"emailBlockId":"' + emailBlockId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    service.allEmailBlocksGetService = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.allEmailBlocksURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allEmailBlocksIdGetService = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.allEmailBlocksIdURL();
        var data = '{"emailBlockId":"' + emailBlockId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    return service;
});

