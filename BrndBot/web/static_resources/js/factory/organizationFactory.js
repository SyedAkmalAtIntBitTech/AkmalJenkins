
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('organizationFactory', function ($q,authenticatedServiceFactory, configurationService) {
    var organizationFactoryObject = {};
    organizationFactoryObject.allOrganizationGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allOrganizationURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    organizationFactoryObject.organizationGet = function () {
        var deffered = $q.defer();
        var url = configurationService.organizationURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    organizationFactoryObject.saveOrganizationPost = function (organizationDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveOrganizationURL();
        var data = '{"organizationDetails":"' + organizationDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    organizationFactoryObject.updateOrganizationPost = function (organizationDetails) {
        var deffered = $q.defer();
        var url = configurationService.updateOrganizationURL();
        var data = '{"organizationDetails":"' + organizationDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    organizationFactoryObject.deleteOrganizationGet = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.deleteOrganizationURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    organizationFactoryObject.getOrganizationGet = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.getOrganizationURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return organizationFactoryObject;
});