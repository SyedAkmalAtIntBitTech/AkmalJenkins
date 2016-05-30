
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('onboardingFactory', function ($q, authenticatedServiceFactory, configurationService) {
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
    onboardingFactoryObject.saveUserPost = function (usersDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveUserURL();
        var data = '{"usersDetails":"' + usersDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveStudioIdPost = function (studioId) {
        var deffered = $q.defer();
        var url = configurationService.saveStudioIdURL();
        var data = '{"studioId":"' + studioId + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
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
        var data = '{"companyDetails":"' + companyDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.colorsForLogoGet = function () {
        var deffered = $q.defer();
        var url = configurationService.colorsForLogoURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveCompanyLogoPost = function (companyLogoDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCompanyLogoURL();
        var data = '{"companyLogoDetails":"' + companyLogoDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return onboardingFactoryObject;
});


