
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('categoryFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var categoryFactoryObject = {};
    categoryFactoryObject.categoryGet = function (categoryId) {
        var deffered = $q.defer();
        var url = configurationService.categoryURL();
        var data = '{"categoryId":"' + categoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    categoryFactoryObject.channelGet = function (organizationId) {
        var deffered = $q.defer();
        var url = configurationService.channelURL();
        var data = '{"organizationId":"' + organizationId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    categoryFactoryObject.saveCategoryPost = function (categoryDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCategoryURL();
        var data = '{"categoryDetails":"' + categoryDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    categoryFactoryObject.updateCategoryPost = function (categoryId,categoryDetails) {
        var deffered = $q.defer();
        var url = configurationService.updateCategoryURL();
        var data = '{"categoryId":"' + categoryId + '","categoryDetails":"' + categoryDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    categoryFactoryObject.deleteCategoryGet = function (categoryId) {
        var deffered = $q.defer();
        var url = configurationService.deleteCategoryURL();
        var data = '{"categoryId":"' + categoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    categoryFactoryObject.allCompanyCategoriesGet = function (channelId) {
        var deffered = $q.defer();
        var url = configurationService.allCompanyCategoriesURL();
        url=url+"?channelId="+channelId;
        var data = "";
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return categoryFactoryObject;
});