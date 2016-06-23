
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('emailListFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var emailListFactoryObject = {};
    emailListFactoryObject.emailListGet = function (data) {
        var deffered = $q.defer();
        var url = configurationService.emailListURL()+data;
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    emailListFactoryObject.emailListSavePost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.emailListSaveURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return emailListFactoryObject;
});

