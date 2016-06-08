
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('companyFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var companyFactoryObject = {};
    
    companyFactoryObject.currentCompanyGet = function () {
        var deffered = $q.defer();
        var url = configurationService.currentCompanyURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyFactoryObject.companyGet = function () {
        var deffered = $q.defer();
        var url = configurationService.companyURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyFactoryObject.nonAddedGroupsGet = function (companyId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedGroupsURL();
        var data = '{"companyId":"' + companyId + '"}';
        authenticatedServiceFactory.makeCall("GET", url,data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyFactoryObject.companyDetailsGet = function (companyId) {
        var deffered = $q.defer();
        var url = configurationService.companyDetailsURL();
        var data = '{"companyId":"' + companyId + '"}';
        authenticatedServiceFactory.makeCall("GET", url,data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyFactoryObject.allBlocksForCompanyGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allBlocksForCompanyURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyFactoryObject.saveGroupPost = function (companyDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveGroupURL();
        var data = '{"companyDetails":"' + companyDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url,data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyFactoryObject.deleteGroupGet = function (organizationCompanyLookupId) {
        var deffered = $q.defer();
        var url = configurationService.deleteGroupURL();
        var data = '{"organizationCompanyLookupId":"' + organizationCompanyLookupId + '"}';
        authenticatedServiceFactory.makeCall("GET", url,data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return companyFactoryObject;
});