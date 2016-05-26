
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('companyMarketingProgramFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.setMarketingProgramPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.setMarketingProgramURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.listAllMarketingProgramGetService = function (programType) {
        var deffered = $q.defer();
        var url = configurationService.listAllMarketingProgramURL();
        var data = '{"programType":"' + programType + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.alluserMarketingProgramGetService = function (program_id) {
        var deffered = $q.defer();
        var url = configurationService.alluserMarketingProgramURL();
        var data = '{"program_id":"' + program_id + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.getAllUserMarketingProgramsGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllUserMarketingProgramsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.getAllUserMarketingProgramsUserIdGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllUserMarketingProgramsUserIdURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    service.getAllUserMarketingProgramsSessionIdGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllUserMarketingProgramsSessionIdURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    service.updateUserProgramPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.updateUserProgramURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.approveStatusRecurringPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.approveStatusRecurringURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.approveStatusPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.approveStatusURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.endMarketingProgramPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.endMarketingProgramURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    
    
    return service;
});
