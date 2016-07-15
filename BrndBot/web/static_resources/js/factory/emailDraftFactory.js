
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('emailDraftFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var emailDraftFactoryObject = {};
    emailDraftFactoryObject.saveEmailDraftsPost = function (draftData) {
        var deffered = $q.defer();
        var url = configurationService.saveEmailDraftsURL();
        authenticatedServiceFactory.makeCall("POST", url, draftData,"").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };    
    emailDraftFactoryObject.updateEmailDraftPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.updateEmailDraftURL();
        authenticatedServiceFactory.makeCall("POST", url,data, "").then(function (data) {
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
    emailDraftFactoryObject.getEmailDraftGet = function (id) {
        var deffered = $q.defer();
        var url = configurationService.getEmailDraftURL()+'?draftid='+id;
//        var data = '{"draftid":"' + draftid + '"}';
        authenticatedServiceFactory.makeCall("GET", url,"").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailDraftFactoryObject.deleteEmailDraftsPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailDraftsURL();
        authenticatedServiceFactory.makeCall("POST", url, data,"").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailDraftFactoryObject.deleteEmailDraftPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailDraftURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return emailDraftFactoryObject;
});
