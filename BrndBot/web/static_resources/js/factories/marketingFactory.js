
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('marketingFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.marketingProgramsGetService = function (marketingCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.marketingProgramsURL();
        var data = '{"marketingCategoryId":"' + marketingCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.nonAddedMarketingProgramsGetService = function (marketingCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedMarketingURL();
        var data = '{"marketingCategoryId":"' + marketingCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allMarketingCategoryGetService = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.allMarketingCategoryURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveMarketingPostService = function (marketingCategoryDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveMarketingURL();
        var data = '{"marketingCategoryDetails":"' + marketingCategoryDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteMarketingCategoryGetService = function (marketingCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.deleteMarketingCategoryURL();
        var data = '{"marketingCategoryId":"' + marketingCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.marketingCategoryIdGetService = function (marketingCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.marketingCategoryIdURL();
        var data = '{"marketingCategoryId":"' + marketingCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allMarketingProgramsGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.allMarketingProgramsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveMarketingProgramPostService = function (marketingProgramActionsDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveMarketingProgramURL();
        var data = '{"marketingProgramActionsDetails":"' + marketingProgramActionsDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateMarketingProgramPostService = function (marketingProgramActionsDetails) {
        var deffered = $q.defer();
        var url = configurationService.updateMarketingProgramURL();
        var data = '{"marketingProgramActionsDetails":"' + marketingProgramActionsDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveMarketingCategoryPostService = function (marketingProgramActionsDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveMarketingCategoryURL();
        var data = '{"marketingProgramActionsDetails":"' + marketingProgramActionsDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteMarketingCategoryProgramGetService = function (marketingCategoryProgramId) {
        var deffered = $q.defer();
        var url = configurationService.deleteMarketingCategoryProgramURL();
        var data = '{"marketingCategoryProgramId":"' + marketingCategoryProgramId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.marketingProgramActionsIdGetService = function (marketingProgramId) {
        var deffered = $q.defer();
        var url = configurationService.marketingProgramActionsIdURL();
        var data = '{"marketingProgramId":"' + marketingProgramId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.companyMarketingCategoriesGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.companyMarketingCategoriesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    return service;
});



