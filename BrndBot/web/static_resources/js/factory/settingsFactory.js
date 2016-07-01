
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('settingsFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var settingsFactoryObject = {};
    settingsFactoryObject.getColorsURLGet = function () {
        var deffered = $q.defer();
        var url = configurationService.getColorsURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.setColorsPost = function (color1, color2, color3, color4) {
        var deffered = $q.defer();
        var url = configurationService.setColorsURL();
        var companyColors = {"color1": color1, "color2": color2, "color3": color3, "color4": color4};
        authenticatedServiceFactory.makeCall("POST", url, JSON.stringify(companyColors), "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.saveEmailSettingsPost = function () {
        var deffered = $q.defer();
        var url = configurationService.saveEmailSettingsURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.getEmailSettingsGet = function () {
        var deffered = $q.defer();
        var url = configurationService.getEmailSettingsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.getAllPreferencesGet = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllPreferencesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.globalAndUserColorsGet = function () {
        var deffered = $q.defer();
        var url = configurationService.globalAndUserColorsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.changeLogoPost = function (file) {
        var deffered = $q.defer();
        var fd = new FormData();
        fd.append('file', file);
        var url = configurationService.saveLogoURL();
        authenticatedServiceFactory.makeCall("POST", url, fd, "UPLOADIMAGE").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.facebookPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.facebookURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.twitterPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.twitterURL();
        authenticatedServiceFactory.makeCall("POST", url, data).then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.fbLoginPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.fbLoginURL();
        authenticatedServiceFactory.makeCall("POST", url, data).then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.fbGetTokenGet = function (code) {
        var deffered = $q.defer();
        var url = configurationService.fbGetTokenURL() + "/" + code;
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.twitterLoginGet = function (data) {
        var deffered = $q.defer();
        var url = configurationService.twitterLoginURL();
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.twitterGetTokenGet = function (pin) {
        var deffered = $q.defer();
        var url = configurationService.twitterGetTokenURL() + "/" + pin;
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.setFooterPost = function (footerDetails) {
        var deffered = $q.defer();
        var url = configurationService.setFooterPostURL();
        var data = '{"footerDetails":"' + footerDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data).then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    settingsFactoryObject.sortenUrl = function (urlToSorten) {
        var deffered = $q.defer();
        $.ajax({
            url: "http://api.bit.ly/v3/shorten",
            async: false,
            data: {longUrl: urlToSorten, apiKey: getBitlyKey(), login: getBitlyUserName()},
            dataType: "jsonp",
            success: function (v)
            {
                deffered.resolve(v);
            }});
        return deffered.promise;
    };

    return settingsFactoryObject;
});


