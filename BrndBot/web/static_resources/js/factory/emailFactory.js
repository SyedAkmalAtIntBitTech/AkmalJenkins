
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('emailFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var emailFactoryObject = {};
    emailFactoryObject.sendEmail = function (sendEmailData) {
        var deffered = $q.defer();
        var url = configurationService.sendEmailPostURL();
        authenticatedServiceFactory.makeCall("POST", url, sendEmailData, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailFactoryObject.sendEmailGet = function () {
        var deffered = $q.defer();
        var url = configurationService.sendEmailGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailFactoryObject.tagsDetailsGet = function (data) {
        var deffered = $q.defer();
        var url = configurationService.tagsDetailsURL()+"?sentId="+data;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailFactoryObject.emailHistoryStatsGet = function (data) {
        var deffered = $q.defer();
        var url = configurationService.emailHistoryStatsURL()+"?actionId="+data.actionId+"&programId="+data.programId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailFactoryObject.previewServletPost = function (sendData) {
        var deffered = $q.defer();
        var url = configurationService.previewServletURL();
        authenticatedServiceFactory.makeCall("POST", url, sendData, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    return emailFactoryObject;
});