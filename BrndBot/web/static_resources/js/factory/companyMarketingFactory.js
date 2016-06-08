
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('companyMarketingProgramFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var companyMarketingProgramFactoryObject = {};
    companyMarketingProgramFactoryObject.setMarketingProgramPost = function (programDetails) {
        var deffered = $q.defer();
        var data =programDetails;
        var url = configurationService.setMarketingProgramURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyMarketingProgramFactoryObject.listAllMarketingProgramGet = function (programType) {
        var deffered = $q.defer();
        var url = configurationService.listAllMarketingProgramURL();
        var data = '{"programType":"' + programType + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyMarketingProgramFactoryObject.alluserMarketingProgramGet = function (program_id) {
        var deffered = $q.defer();
        var url = configurationService.alluserMarketingProgramURL();
        var data = '{"program_id":"' + program_id + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyMarketingProgramFactoryObject.getAllUserMarketingProgramsGet = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllUserMarketingProgramsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyMarketingProgramFactoryObject.getAllUserMarketingProgramsUserIdGet = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllUserMarketingProgramsUserIdURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    companyMarketingProgramFactoryObject.getAllUserMarketingProgramsSessionIdGet = function () {
        var deffered = $q.defer();
        var url = configurationService.getAllUserMarketingProgramsSessionIdURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    companyMarketingProgramFactoryObject.updateUserProgramPost = function () {
        var deffered = $q.defer();
        var url = configurationService.updateUserProgramURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyMarketingProgramFactoryObject.approveStatusRecurringPost = function () {
        var deffered = $q.defer();
        var url = configurationService.approveStatusRecurringURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyMarketingProgramFactoryObject.approveStatusPost = function () {
        var deffered = $q.defer();
        var url = configurationService.approveStatusURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyMarketingProgramFactoryObject.endMarketingProgramPost = function () {
        var deffered = $q.defer();
        var url = configurationService.endMarketingProgramURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    return companyMarketingProgramFactoryObject;
});
