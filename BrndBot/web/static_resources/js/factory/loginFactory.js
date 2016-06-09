
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('LoginFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var LoginFactoryObject = {};
    LoginFactoryObject.signinGet = function () {
        var deffered = $q.defer();
        var url = configurationService.signinURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    LoginFactoryObject.resetPasswordGet = function () {
        var deffered = $q.defer();
        var url = configurationService.resetLoginPasswordURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    LoginFactoryObject.changePasswordGet = function () {
        var deffered = $q.defer();
        var url = configurationService.changePasswordURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    LoginFactoryObject.logoutGet = function () {
        var deffered = $q.defer();
        var url = configurationService.logoutURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    LoginFactoryObject.access_DeniedGet = function () {
        var deffered = $q.defer();
        var url = configurationService.access_DeniedURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    return LoginFactoryObject;
});
