
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('externalContentFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var externalContentFactoryObject = {};
    externalContentFactoryObject.activatedGet = function (lookupId) {
        var deffered = $q.defer();
        var url = configurationService.activatedGetURL()+"?externalSourceKeywordLookupId="+lookupId;
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
        var url = configurationService.layoutEmailModelURL()+"?emailModelId="+ emailModelId+"&isBlock="+isBlock+"&externalDataId="+externalDataId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    externalContentFactoryObject.getFroalaEditor = function(data){
        var deffered = $q.defer();
        var editorHtml = data;
            deffered.resolve(editorHtml);
        return deffered.promise;
    };
    return externalContentFactoryObject;
});