
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('externalContentFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function () {
        var deffered = $q.defer();
        var url = configurationService.activatedGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.activationLinkGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.activationLinkURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };      
    service.listDataGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.listDataURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.layoutEmailModelGetService = function (emailModelId,isBlock,externalDataId) {
        var deffered = $q.defer();
        var url = configurationService.layoutEmailModelURL();
        var data = '{"emailModelId":"' + emailModelId + '","isBlock":"' + isBlock + '","externalDataId":"' + externalDataId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return service;
});