
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('socialPostFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var socialPostFactoryObject = {};
    socialPostFactoryObject.facebookPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.postToFacebookURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    socialPostFactoryObject.twitterPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.postToTwitterURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return socialPostFactoryObject;
});
