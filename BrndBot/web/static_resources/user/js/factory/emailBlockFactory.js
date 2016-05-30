
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('emailBlockFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var emailBlockFactoryObject = {};
    emailBlockFactoryObject.saveEmailBlockPost = function (emailBlockDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveEmailBlockURL();
        var data = '{"emailBlockDetails":"' + emailBlockDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailBlockFactoryObject.deleteEmailBlockGet = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailBlockURL();
        var data = '{"emailBlockId":"' + emailBlockId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    emailBlockFactoryObject.allEmailBlocksGet = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.allEmailBlocksURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailBlockFactoryObject.allEmailBlocksIdGet = function (emailBlockId) {
        var deffered = $q.defer();
        var url = configurationService.allEmailBlocksIdURL();
        var data = '{"emailBlockId":"' + emailBlockId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    return emailBlockFactoryObject;
});

