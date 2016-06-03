
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('downloadFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var downloadFactoryObject = {};
    downloadFactoryObject.downloadGet = function () {
        var deffered = $q.defer();
        var url = configurationService.downloadURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return downloadFactoryObject;
});