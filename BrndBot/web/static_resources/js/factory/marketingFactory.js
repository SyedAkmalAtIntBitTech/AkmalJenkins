
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('marketingFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var marketingFactoryObject = {};
    marketingFactoryObject.marketingProgramsGet = function (marketingCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.marketingProgramsURL()+"?marketingCategoryId="+marketingCategoryId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.nonAddedMarketingProgramsGet = function (marketingCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedMarketingURL();
        var data = '{"marketingCategoryId":"' + marketingCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.allMarketingCategoryGet = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.allMarketingCategoryURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.saveMarketingPost = function (marketingCategoryDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveMarketingURL();
        var data = '{"marketingCategoryDetails":"' + marketingCategoryDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.deleteMarketingCategoryGet = function (marketingCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.deleteMarketingCategoryURL();
        var data = '{"marketingCategoryId":"' + marketingCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.marketingCategoryIdGet = function (marketingCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.marketingCategoryIdURL();
        var data = '{"marketingCategoryId":"' + marketingCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.allMarketingProgramsGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allMarketingProgramsURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.saveMarketingProgramPost = function (marketingProgramActionsDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveMarketingProgramURL();
        var data = '{"marketingProgramActionsDetails":"' + marketingProgramActionsDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.updateMarketingProgramPost = function (marketingProgramActionsDetails) {
        var deffered = $q.defer();
        var url = configurationService.updateMarketingProgramURL();
        var data = '{"marketingProgramActionsDetails":"' + marketingProgramActionsDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.saveMarketingCategoryPost = function (marketingProgramActionsDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveMarketingCategoryURL();
        var data = '{"marketingProgramActionsDetails":"' + marketingProgramActionsDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.deleteMarketingCategoryProgramGet = function (marketingCategoryProgramId) {
        var deffered = $q.defer();
        var url = configurationService.deleteMarketingCategoryProgramURL();
        var data = '{"marketingCategoryProgramId":"' + marketingCategoryProgramId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.marketingProgramActionsIdGet = function (marketingProgramId) {
        var deffered = $q.defer();
        var url = configurationService.marketingProgramActionsIdURL();
        var data = '{"marketingProgramId":"' + marketingProgramId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    marketingFactoryObject.companyMarketingCategoriesGet = function () {
        var deffered = $q.defer();
        var url = configurationService.companyMarketingCategoriesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }; 
    return marketingFactoryObject;
});



