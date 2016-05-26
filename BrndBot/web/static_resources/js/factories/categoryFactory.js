
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('categoryFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function (categoryId) {
        var deffered = $q.defer();
        var url = configurationService.categoryURL();
        var data = '{"categoryId":"' + categoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.channelGetService = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.channelURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveCategoryPostService = function (categoryDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCategoryURL();
        var data = '{"categoryDetails":"' + categoryDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateCategoryPostService = function (categoryId,categoryDetails) {
        var deffered = $q.defer();
        var url = configurationService.updateCategoryURL();
        var data = '{"categoryId":"' + categoryId + '","categoryDetails":"' + categoryDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteCategoryGetService = function (categoryId) {
        var deffered = $q.defer();
        var url = configurationService.deleteCategoryURL();
        var data = '{"categoryId":"' + categoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allCompanyCategoriesGetService = function (channelId) {
        var deffered = $q.defer();
        var url = configurationService.allCompanyCategoriesURL();
        var data = '{"channelId":"' + channelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return service;
});