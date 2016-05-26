
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('onboardingFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.userPostService = function (usersDetails) {
        var deffered = $q.defer();
        var url = configurationService.usersURL();
        var data = '{"usersDetails":"' + usersDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveUserPostService = function (usersDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveUserURL();
        var data = '{"usersDetails":"' + usersDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveStudioIdPostService = function (studioId) {
        var deffered = $q.defer();
        var url = configurationService.saveStudioIdURL();
        var data = '{"studioId":"' + studioId + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.completedActivationGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.completedActivationURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveCompanyPostService = function (companyDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCompanyURL();
        var data = '{"companyDetails":"' + companyDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.colorsForLogoGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.colorsForLogoURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveCompanyLogoPostService = function (companyLogoDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCompanyLogoURL();
        var data = '{"companyLogoDetails":"' + companyLogoDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return service;
});


