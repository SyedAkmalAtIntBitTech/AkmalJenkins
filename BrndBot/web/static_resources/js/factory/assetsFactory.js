//************************ @author Tasmiya P.S @ Intbit *************************
factoryApp.factory('assetsFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var assetsFactoryObject = {};
    assetsFactoryObject.downloadImageGet = function (imageName) {
        var deffered = $q.defer();
        var url = configurationService.downloadImageURL();
        var data = '{"imageName":"' + imageName + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.imageListGet = function (imageType) {
        var deffered = $q.defer();
        var url = configurationService.imageListURL();
        var data = '{"imageType":"' + imageType + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.globalColorsGet = function (globalColorsId) {
        var deffered = $q.defer();
        var url = configurationService.globalColorsURL();
        var data = '{"globalColorsId":"' + globalColorsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.globalImageGet = function (globalImageId) {
       
        var deffered = $q.defer();
        var url = configurationService.globalImageURL();
        var data = '{"globalImageId":"' + globalImageId + '"}';
        alert(data);
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.globalFontsGet = function (globalFontsId) {
        var deffered = $q.defer();
        var url = configurationService.globalFontsURL();
        var data = '{"globalFontsId":"' + globalFontsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.allColorThemesGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allColorThemesURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.allGlobalImageGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allGlobalImageURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.allFontsGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allFontsURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.saveFontPost = function (fontsObject) {
        var deffered = $q.defer();
        var url = configurationService.saveFontURL();
        var data = '{"fontsObject":"' + fontsObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.colorThemePost = function (colorsObject) {
        var deffered = $q.defer();
        var url = configurationService.colorThemeURL();
        var data = '{"colorsObject":"' + colorsObject + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.saveGlobalImagePost = function (imageObject) {
        var deffered = $q.defer();
        var url = configurationService.saveGlobalImageURL();
        var data = '{"imageObject":"' + imageObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.updateGlobalImagePost = function (imageObject) {
        var deffered = $q.defer();
        var url = configurationService.updateGlobalImageURL();
        var data = '{"imageObject":"' + imageObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.updateFontPost = function (fontsObject) {
        var deffered = $q.defer();
        var url = configurationService.updateFontURL();
        var data = '{"fontsObject":"' + fontsObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.updateColorThemePost = function (colorsObject) {
        var deffered = $q.defer();
        var url = configurationService.updateColorThemeURL();
        var data = '{"colorsObject":"' + colorsObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.deleteFontGet = function (globalFontsId) {
        var deffered = $q.defer();
        var url = configurationService.deleteFontURL();
        var data = '{"globalFontsId":"' + globalFontsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.deleteGlobalImageGet = function (globalImageId) {
        var deffered = $q.defer();
        var url = configurationService.deleteGlobalImageURL();
        var data = '{"globalImageId":"' + globalImageId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.imageUniquenessGet = function (globalImageName) {
        var deffered = $q.defer();
        var url = configurationService.imageUniquenessURL();
        var data = '{"globalImageName":"' + globalImageName + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    assetsFactoryObject.deleteColorThemeGet = function (globalColorsId) {
        var deffered = $q.defer();
        var url = configurationService.deleteColorThemeURL();
        var data = '{"globalColorsId":"' + globalColorsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return assetsFactoryObject;
});
