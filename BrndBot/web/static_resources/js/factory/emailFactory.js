
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('emailFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var emailFactoryObject = {};
    emailFactoryObject.sendEmail = function () {
        var deffered = $q.defer();
        var url = configurationService.sendEmailPostURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
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