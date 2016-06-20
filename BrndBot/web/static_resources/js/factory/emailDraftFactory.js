
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('emailDraftFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var emailDraftFactoryObject = {};
    emailDraftFactoryObject.saveEmailDraftsPost = function () {
        var deffered = $q.defer();
        var url = configurationService.saveEmailDraftsURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };    
    emailDraftFactoryObject.updateEmailDraftPost = function () {
        var deffered = $q.defer();
        var url = configurationService.updateEmailDraftURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailDraftFactoryObject.displayAllEmailDraftsGet = function () {
        var deffered = $q.defer();
        var url = configurationService.displayAllEmailDraftsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailDraftFactoryObject.getEmailDraftGet = function (draftid) {
        var deffered = $q.defer();
        var url = configurationService.getEmailDraftURL();
        var data = '{"draftid":"' + draftid + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailDraftFactoryObject.deleteEmailDraftsPost = function () {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailDraftsURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailDraftFactoryObject.deleteEmailDraftPost = function (draftid) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailDraftURL();
        var data = '{"draftid":"' + draftid + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return emailDraftFactoryObject;
});
