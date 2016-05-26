
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('socialPostFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function () {
        var deffered = $q.defer();
        var url = configurationService.postToFacebookURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.postToTwitterPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.postToTwitterURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return service;
});
