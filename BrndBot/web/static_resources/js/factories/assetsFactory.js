//************************ @author Tasmiya P.S @ Intbit *************************
app.factory('assetsFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.downloadImageGetService = function (imageName) {
        var deffered = $q.defer();
        var url = configurationService.downloadImageURL();
        var data = '{"imageName":"' + imageName + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.imageListGetService = function (imageType) {
        var deffered = $q.defer();
        var url = configurationService.imageListURL();
        var data = '{"imageType":"' + imageType + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.globalColorsGetService = function (globalColorsId) {
        var deffered = $q.defer();
        var url = configurationService.globalColorsURL();
        var data = '{"globalColorsId":"' + globalColorsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.globalImageGetService = function (globalImageId) {
        var deffered = $q.defer();
        var url = configurationService.globalImageURL();
        var data = '{"globalImageId":"' + globalImageId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.globalFontsGetService = function (globalFontsId) {
        var deffered = $q.defer();
        var url = configurationService.globalFontsURL();
        var data = '{"globalFontsId":"' + globalFontsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allColorThemesGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.allColorThemesURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allGlobalImageGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.allGlobalImageURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allFontsGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.allFontsURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveFontPostService = function (fontsObject) {
        var deffered = $q.defer();
        var url = configurationService.saveFontURL();
        var data = '{"fontsObject":"' + fontsObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.colorThemePostService = function (colorsObject) {
        var deffered = $q.defer();
        var url = configurationService.colorThemeURL();
        var data = '{"colorsObject":"' + colorsObject + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveGlobalImagePostService = function (imageObject) {
        var deffered = $q.defer();
        var url = configurationService.saveGlobalImageURL();
        var data = '{"imageObject":"' + imageObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateGlobalImagePostService = function (imageObject) {
        var deffered = $q.defer();
        var url = configurationService.updateGlobalImageURL();
        var data = '{"imageObject":"' + imageObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateFontPostService = function (fontsObject) {
        var deffered = $q.defer();
        var url = configurationService.updateFontURL();
        var data = '{"fontsObject":"' + fontsObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateColorThemePostService = function (colorsObject) {
        var deffered = $q.defer();
        var url = configurationService.updateColorThemeURL();
        var data = '{"colorsObject":"' + colorsObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteFontGetService = function (globalFontsId) {
        var deffered = $q.defer();
        var url = configurationService.deleteFontURL();
        var data = '{"globalFontsId":"' + globalFontsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteGlobalImageGetService = function (globalImageId) {
        var deffered = $q.defer();
        var url = configurationService.deleteGlobalImageURL();
        var data = '{"globalImageId":"' + globalImageId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.imageUniquenessGetService = function (globalImageName) {
        var deffered = $q.defer();
        var url = configurationService.imageUniquenessURL();
        var data = '{"globalImageName":"' + globalImageName + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteColorThemeGetService = function (globalColorsId) {
        var deffered = $q.defer();
        var url = configurationService.deleteColorThemeURL();
        var data = '{"globalColorsId":"' + globalColorsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    return service;
});  
