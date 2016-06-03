
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('imageFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var imageFactoryObject = {};
    imageFactoryObject.imageIdGet = function (imageId) {
        var deffered = $q.defer();
        var url = configurationService.imageIdURL()+"/"+imageId;
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    imageFactoryObject.saveImagePost = function () {
        var deffered = $q.defer();
        var url = configurationService.saveImageURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return imageFactoryObject;
});