
// **************** @author Arfa Shakeel @ Intbit **************** //

factoryApp.factory('subCategoryFactory', function ($q,authenticatedServiceFactory, configurationService) {
    var subCategoryFactoryObject = {};
    subCategoryFactoryObject.saveSubCategoryPost = function (subCategoryDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveSubCategoryURL();
        var data = '{"subCategoryDetails":"' + subCategoryDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    subCategoryFactoryObject.subCategoryGet = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.subCategoryURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    subCategoryFactoryObject.deleteSubCategoryGet = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.deleteSubCategoryURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    subCategoryFactoryObject.allSubCategoriesGet = function (categoryId) {
        var deffered = $q.defer();
        var url = configurationService.allSubCategoriesURL();
        var data = '{"categoryId":"' + categoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    subCategoryFactoryObject.externalSourceGet = function () {
        var deffered = $q.defer();
        var url = configurationService.externalSourceURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    subCategoryFactoryObject.allExternalSourcesGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allExternalSourcesURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return subCategoryFactoryObject;
});