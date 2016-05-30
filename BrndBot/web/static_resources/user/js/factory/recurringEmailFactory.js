
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('recurringEmailFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var recurringEmailFactoryObject = {};
    recurringEmailFactoryObject.saveRecurringEmailPost = function (recurringEmailDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveRecurringEmailURL();
        var data = '{"recurringEmailDetails":"' + recurringEmailDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    recurringEmailFactoryObject.saveOrganizationRecurringEmailPost = function (recurringEmailDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveOrganizationRecurringEmailURL();
        var data = '{"recurringEmailDetails":"' + recurringEmailDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    recurringEmailFactoryObject.deleteRecurringEmailGet = function (recurringEmailTemplateId) {
        var deffered = $q.defer();
        var url = configurationService.deleteRecurringEmailURL();
        var data = '{"recurringEmailTemplateId":"' + recurringEmailTemplateId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    recurringEmailFactoryObject.deleteOrganizationRecurringEmailGet = function (organizationRecurringEmailLookupId) {
        var deffered = $q.defer();
        var url = configurationService.deleteOrganizationRecurringEmailURL();
        var data = '{"organizationRecurringEmailLookupId":"' + organizationRecurringEmailLookupId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    recurringEmailFactoryObject.updateRecurringEmailPost = function (recurringEmailTemplate) {
        var deffered = $q.defer();
        var url = configurationService.updateRecurringEmailURL();
        var data = '{"recurringEmailTemplate":"' + recurringEmailTemplate + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    recurringEmailFactoryObject.recurringEmailTemplateIdGet = function (recurringEmailTemplateId) {
        var deffered = $q.defer();
        var url = configurationService.recurringEmailTemplateIdURL();
        var data = '{"recurringEmailTemplateId":"' + recurringEmailTemplateId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    recurringEmailFactoryObject.allRecurringOrganizationGet = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.allRecurringOrganizationURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    recurringEmailFactoryObject.allNonAddedRecurringEmailGet = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.allNonAddedRecurringEmailURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    recurringEmailFactoryObject.allRecurringEmailsGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allRecurringEmailsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return recurringEmailFactoryObject;
});


