
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('recurringEmailFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.saveRecurringEmailPostService = function (recurringEmailDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveRecurringEmailURL();
        var data = '{"recurringEmailDetails":"' + recurringEmailDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveOrganizationRecurringEmailPostService = function (recurringEmailDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveOrganizationRecurringEmailURL();
        var data = '{"recurringEmailDetails":"' + recurringEmailDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteRecurringEmailGetService = function (recurringEmailTemplateId) {
        var deffered = $q.defer();
        var url = configurationService.deleteRecurringEmailURL();
        var data = '{"recurringEmailTemplateId":"' + recurringEmailTemplateId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteOrganizationRecurringEmailGetService = function (organizationRecurringEmailLookupId) {
        var deffered = $q.defer();
        var url = configurationService.deleteOrganizationRecurringEmailURL();
        var data = '{"organizationRecurringEmailLookupId":"' + organizationRecurringEmailLookupId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateRecurringEmailPostService = function (recurringEmailTemplate) {
        var deffered = $q.defer();
        var url = configurationService.updateRecurringEmailURL();
        var data = '{"recurringEmailTemplate":"' + recurringEmailTemplate + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.recurringEmailTemplateIdGetService = function (recurringEmailTemplateId) {
        var deffered = $q.defer();
        var url = configurationService.recurringEmailTemplateIdURL();
        var data = '{"recurringEmailTemplateId":"' + recurringEmailTemplateId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allRecurringOrganizationGetService = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.allRecurringOrganizationURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allNonAddedRecurringEmailGetService = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.allNonAddedRecurringEmailURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allRecurringEmailsGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.allRecurringEmailsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    
    
    return service;
});


