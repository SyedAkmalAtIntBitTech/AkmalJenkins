
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('signupFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function () {
        var deffered = $q.defer();
        var url = configurationService.signupURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.forgotPasswordPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.forgotPasswordURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.resetPasswordPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.resetPasswordURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return service;
});
