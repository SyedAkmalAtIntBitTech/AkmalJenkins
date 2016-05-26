
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('settingsFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.getColorsURLGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.getColorsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.setColorsPostService = function (companyColorsDetails) {
        var deffered = $q.defer();
        var url = configurationService.setColorsURL();
        var data = '{"companyColorsDetails":"' + companyColorsDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveEmailSettingsPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.saveEmailSettingsURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.getEmailSettingsGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.getEmailSettingsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.getAllPreferencesGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllPreferencesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.globalAndUserColorsGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.globalAndUserColorsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.changeLogoPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.changeLogoURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.facebookPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.facebookURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.twitterPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.twitterURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.fbLoginPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.fbLoginURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.fbGetTokenGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.fbGetTokenURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.twitterLoginGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.twitterLoginURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.twitterGetTokenGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.twitterGetTokenURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return service;
});


