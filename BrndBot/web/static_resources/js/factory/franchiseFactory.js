//************************ @author Tasmiya P.S @ Intbit *************************
factoryApp.factory('franchiseFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var franchiseFactoryObject = {};
    franchiseFactoryObject.getAllFranchises = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllFranchisesURL();
        var data = '';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.getFranchisesForCompanyId = function (imageType) {
        var deffered = $q.defer();
        var url = configurationService.getFranchisesForCompanyIdURL();
        var data = '{"imageType":"' + imageType + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.getCompaniesForFranchiseId = function (globalColorsId) {
        var deffered = $q.defer();
        var url = configurationService.getCompaniesForFranchiseIdURL();
        var data = '{"globalColorsId":"' + globalColorsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.activateCompanyAsFranchise = function (globalImageId) {
       
        var deffered = $q.defer();
        var url = configurationService.activateCompanyAsFranchiseURL();
        var data = '{"globalImageId":"' + globalImageId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.associateCompanyToFranchise = function (globalFontsId) {
        var deffered = $q.defer();
        var url = configurationService.associateCompanyToFranchiseURL();
        var data = '{"globalFontsId":"' + globalFontsId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.removeCompanyFromFranchise = function () {
        var deffered = $q.defer();
        var url = configurationService.removeCompanyFromFranchiseURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.saveFranchise = function () {
        var deffered = $q.defer();
        var url = configurationService.saveFranchiseURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.updateFranchise = function () {
        var deffered = $q.defer();
        var url = configurationService.updateFranchiseURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    franchiseFactoryObject.deleteFranchise = function (fontsObject) {
        var deffered = $q.defer();
        var url = configurationService.deleteFranchiseURL();
        var data = '{"fontsObject":"' + fontsObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return franchiseFactoryObject;
});
