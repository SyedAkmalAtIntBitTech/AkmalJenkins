
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('organizationFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function () {
        var deffered = $q.defer();
        var url = configurationService.allOrganizationURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allOrganizationGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.organizationURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveOrganizationPostService = function (organizationDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveOrganizationURL();
        var data = '{"organizationDetails":"' + organizationDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateOrganizationPostService = function (organizationDetails) {
        var deffered = $q.defer();
        var url = configurationService.updateOrganizationURL();
        var data = '{"organizationDetails":"' + organizationDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteOrganizationGetService = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.deleteOrganizationURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.getOrganizationGetService = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.getOrganizationURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return service;
});