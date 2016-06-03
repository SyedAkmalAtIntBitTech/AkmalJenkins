
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('settingsFactory', function ($q,authenticatedServiceFactory, configurationService) {
    var settingsFactoryObject = {};
//    settingsFactoryObject.getColorsURLGet = function () {
//        var deffered = $q.defer();
//        var url = configurationService.getColorsURL();
//        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.setColorsPost = function (color1, color2, color3, color4) {
//        var deffered = $q.defer();
//        var url = configurationService.setColorsURL();
//        var companyColors = {"color1": color1, "color2": color2, "color3": color3, "color4": color4};
//        authenticatedServiceFactory.makeCall("POST", url, companyColors, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.saveEmailSettingsPost = function () {
//        var deffered = $q.defer();
//        var url = configurationService.saveEmailSettingsURL();
//        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.getEmailSettingsGet = function () {
//        var deffered = $q.defer();
//        var url = configurationService.getEmailSettingsURL();
//        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.getAllPreferencesGet = function () {
//        var deffered = $q.defer();
//        var url = configurationService.getAllPreferencesURL();
//        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.globalAndUserColorsGet = function () {
//        var deffered = $q.defer();
//        var url = configurationService.globalAndUserColorsURL();
//        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.changeLogoPost = function () {
//        var deffered = $q.defer();
//        var url = configurationService.changeLogoURL();
//        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.facebookPost = function () {
//        var deffered = $q.defer();
//        var url = configurationService.facebookURL();
//        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.twitterPost = function () {
//        var deffered = $q.defer();
//        var url = configurationService.twitterURL();
//        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.fbLoginPost = function () {
//        var deffered = $q.defer();
//        var url = configurationService.fbLoginURL();
//        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.fbGetTokenGet = function (code) {
//        var deffered = $q.defer();
//        var url = configurationService.fbGetTokenURL() + "/" + code;
//        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.twitterLoginGet = function () {
//        var deffered = $q.defer();
//        var url = configurationService.twitterLoginURL();
//        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
//    settingsFactoryObject.twitterGetTokenGet = function (pin) {
//        var deffered = $q.defer();
//        var url = configurationService.twitterGetTokenURL() + "/" + pin;
//        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
//            deffered.resolve(data);
//        });
//        return deffered.promise;
//    };
    return settingsFactoryObject;
});


