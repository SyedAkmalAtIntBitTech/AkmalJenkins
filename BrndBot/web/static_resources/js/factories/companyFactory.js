
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('companyFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.companyGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.companyURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.nonAddedGroupsGetService = function (companyId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedGroupsURL();
        var data = '{"companyId":"' + companyId + '"}';
        authenticatedServiceFactory.makeCall("GET", url,data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.companyDetailsGetService = function (companyId) {
        var deffered = $q.defer();
        var url = configurationService.companyDetailsURL();
        var data = '{"companyId":"' + companyId + '"}';
        authenticatedServiceFactory.makeCall("GET", url,data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allBlocksForCompanyGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.allBlocksForCompanyURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveGroupPostService = function (companyDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveGroupURL();
        var data = '{"companyDetails":"' + companyDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url,data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteGroupGetService = function (organizationCompanyLookupId) {
        var deffered = $q.defer();
        var url = configurationService.deleteGroupURL();
        var data = '{"organizationCompanyLookupId":"' + organizationCompanyLookupId + '"}';
        authenticatedServiceFactory.makeCall("GET", url,data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return service;
});