
// **************** @author Arfa Shakeel @ Intbit **************** //

app.factory('marketingRecurringEmailControllerFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.allRecurringEmailTemplatesGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.allRecurringEmailTemplatesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    service.recurringEmailTemplatePostService = function () {
        var deffered = $q.defer();
        var url = configurationService.recurringEmailTemplateURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.setRecurringEmailTemplatePostService = function () {
        var deffered = $q.defer();
        var url = configurationService.setRecurringEmailTemplateURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    service.deleteRecurringEmailTemplatePostService = function () {
        var deffered = $q.defer();
        var url = configurationService.deleteRecurringEmailTemplateURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    service.updateRecurringEmailTemplatePostService = function () {
        var deffered = $q.defer();
        var url = configurationService.updateRecurringEmailTemplateURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    service.setEmailTemplateRecurringActionPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.setEmailTemplateRecurringActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    service.addRecurringActionPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.addRecurringActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.addupdateRecurringActionPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.addupdateRecurringActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateRecurringActionPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.updateRecurringActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.getUserPreferencesGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.getUserPreferencesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    service.getRecurringEntityPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.getRecurringEntityURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    
    return service;
});