
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
    imageFactoryObject.saveImagePost = function (file) {
        var deffered = $q.defer();
        var fd = new FormData();
        fd.append('file', file);
        var url = configurationService.saveImageURL();
        authenticatedServiceFactory.makeCall("POST", url,fd, "UPLOADIMAGE").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return imageFactoryObject;
});