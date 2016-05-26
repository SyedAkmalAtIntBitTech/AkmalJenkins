
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('subCategoryFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function (subCategoryDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveSubCategoryURL();
        var data = '{"subCategoryDetails":"' + subCategoryDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.subCategoryGetService = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.subCategoryURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteSubCategoryGetService = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.deleteSubCategoryURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allSubCategoriesGetService = function (categoryId) {
        var deffered = $q.defer();
        var url = configurationService.allSubCategoriesURL();
        var data = '{"categoryId":"' + categoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.externalSourceGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.externalSourceURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allExternalSourcesGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.allExternalSourcesURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return service;
});