
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('externalContentFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var externalContentFactoryObject = {};
    externalContentFactoryObject.activatedGet = function () {
        var deffered = $q.defer();
        var url = configurationService.activatedGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    externalContentFactoryObject.activationLinkGet = function () {
        var deffered = $q.defer();
        var url = configurationService.activationLinkURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };      
    externalContentFactoryObject.listDataGet = function (externalSourceKeywordLookupId) {
        var deffered = $q.defer();
        var url = configurationService.listDataURL()+"/"+externalSourceKeywordLookupId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    externalContentFactoryObject.layoutEmailModelGet = function (emailModelId,isBlock,externalDataId) {
        var deffered = $q.defer();
        var url = configurationService.layoutEmailModelURL();
        var data = '{"emailModelId":"' + emailModelId + '","isBlock":"' + isBlock + '","externalDataId":"' + externalDataId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return externalContentFactoryObject;
});