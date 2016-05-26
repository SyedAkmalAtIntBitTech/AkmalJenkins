
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('LoginFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.signinGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.signinURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.resetPasswordGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.resetPasswordURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.changePasswordGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.changePasswordURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.logoutGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.logoutURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.access_DeniedGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.access_DeniedURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    return service;
});
