
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('onboardingFactory', function ($q,authenticatedServiceFactory, configurationService) {
    var onboardingFactoryObject = {};
    onboardingFactoryObject.userPost = function (usersDetails) {
        var deffered = $q.defer();
        var url = configurationService.usersURL();
        var data = '{"usersDetails":"' + usersDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveUserPost = function (userDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveUserURL();
        authenticatedServiceFactory.makeCall("POST", url, userDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveStudioIdPost = function (studioId) {
        var deffered = $q.defer();
        var url = configurationService.saveStudioIdURL()+"?studioId="+studioId;
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.completedActivationGet = function () {
        var deffered = $q.defer();
        var url = configurationService.completedActivationURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveCompanyPost = function (companyDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCompanyURL();
        authenticatedServiceFactory.makeCall("POST", url, companyDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.colorsForLogoGet = function () {
        var deffered = $q.defer();
        var url = configurationService.colorsForLogoURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveCompanyLogoPost = function (imageTypeData,imgDataObj) {
        var deffered = $q.defer();
        var url = configurationService.saveCompanyLogoURL();
        var globalImage = {"imageType": imageTypeData, "imageData": imgDataObj.base64ImgString};
        authenticatedServiceFactory.makeCall("POST", url, globalImage, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return onboardingFactoryObject;
});


