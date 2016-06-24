
// **************** @author Arfa Shakeel @ Intbit **************** //

    factoryApp.factory('marketingRecurringEmailControllerFactory', function ($q, authenticatedServiceFactory, configurationService) {
    
    var marketingRecurringEmailFactoryObject = {};
    marketingRecurringEmailFactoryObject.allRecurringEmailTemplatesGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allRecurringEmailTemplatesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
                alert(data);
        });
        return deffered.promise;
    };  
    marketingRecurringEmailFactoryObject.recurringEmailTemplatePost = function () {
        var deffered = $q.defer();
        var url = configurationService.recurringEmailTemplateURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingRecurringEmailFactoryObject.setRecurringEmailTemplatePost = function () {
        var deffered = $q.defer();
        var url = configurationService.setRecurringEmailTemplateURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    marketingRecurringEmailFactoryObject.deleteRecurringEmailTemplatePost = function () {
        var deffered = $q.defer();
        var url = configurationService.deleteRecurringEmailTemplateURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    marketingRecurringEmailFactoryObject.updateRecurringEmailTemplatePost = function () {
        var deffered = $q.defer();
        var url = configurationService.updateRecurringEmailTemplateURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    marketingRecurringEmailFactoryObject.setEmailTemplateRecurringActionPost = function () {
        var deffered = $q.defer();
        var url = configurationService.setEmailTemplateRecurringActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    marketingRecurringEmailFactoryObject.addRecurringActionPost = function () {
        var deffered = $q.defer();
        var url = configurationService.addRecurringActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingRecurringEmailFactoryObject.addupdateRecurringActionPost = function () {
        var deffered = $q.defer();
        var url = configurationService.addupdateRecurringActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingRecurringEmailFactoryObject.updateRecurringActionPost = function () {
        var deffered = $q.defer();
        var url = configurationService.updateRecurringActionURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingRecurringEmailFactoryObject.getUserPreferencesGet = function () {
        var deffered = $q.defer();
        var url = configurationService.getUserPreferencesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    marketingRecurringEmailFactoryObject.getRecurringEntityPost = function () {
        var deffered = $q.defer();
        var url = configurationService.getRecurringEntityURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return service;
});