
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('uploadImageFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var uploadImageFactoryObject = {};
    uploadImageFactoryObject.uploadByAdminPost = function () {
        var deffered = $q.defer();
        var url = configurationService.uploadByAdminURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    uploadImageFactoryObject.uploadByUserPost = function () {
        var deffered = $q.defer();
        var url = configurationService.uploadByUserURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    uploadImageFactoryObject.uploadByImageUrlPost = function (imageDetails) {
        var deffered = $q.defer();
        var url = configurationService.uploadByImageUrlURL();
        authenticatedServiceFactory.makeCall("POST", url, imageDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return uploadImageFactoryObject;
});