
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('modelFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.EmailModelURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.EmailModelsIdGetService = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.EmailModelsIdURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.nonAddedEmailModelsGetService = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedEmailModelsURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.nonAddedImageModelsGetService = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedImageModelsURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.nonAddedPrintModelsGetService = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedPrintModelsURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.printModelGetService = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.printModelURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.imageModelGetService = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.imageModelSubCategoryURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.emailModelGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.emailModelURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.emailModelIdGetService = function (emailModelId) {
        var deffered = $q.defer();
        var url = configurationService.emailModelIdURL();
        var data = '{"emailModelId":"' + emailModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.imageModelGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.imageModelURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.imageModelIdGetService = function (imageModelId) {
        var deffered = $q.defer();
        var url = configurationService.imageModelIdURL();
        var data = '{"imageModelId":"' + imageModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.allPrintModelGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.allPrintModelURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.printModelIdGetService = function (printModelId) {
        var deffered = $q.defer();
        var url = configurationService.printModelIdURL();
        var data = '{"printModelId":"' + printModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveEmailModelGetService = function (emailModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveEmailModelURL();
        var data = '{"emailModelDetails":"' + emailModelDetails + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.editEmailModelPostService = function (emailModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.editEmailModelURL();
        var data = '{"emailModelDetails":"' + emailModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteEmailModelGetService = function (emailModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailModelURL();
        var data = '{"emailModelId":"' + emailModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveCategoryEmailModelPostService = function (emailModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCategoryEmailModelURL();
        var data = '{"emailModelDetails":"' + emailModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteCategoryEmailModelGetService = function (subCategoryEmailModelID) {
        var deffered = $q.defer();
        var url = configurationService.deleteCategoryEmailModelURL();
        var data = '{"subCategoryEmailModelID":"' + subCategoryEmailModelID + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveCategoryPrintModelPostService = function (printModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCategoryPrintModelURL();
        var data = '{"printModelDetails":"' + printModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteCategoryPrintModelGetService = function (subCategoryPrintModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteCategoryPrintModelURL();
        var data = '{"subCategoryPrintModelId":"' + subCategoryPrintModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveCategoryImageModelPostService = function (imageModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCategoryImageModelURL();
        var data = '{"imageModelDetails":"' + imageModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteCategoryImageModelGetService = function (subCategoryImageModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteCategoryImageModelURL();
        var data = '{"subCategoryImageModelId":"' + subCategoryImageModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.savePrintModelPostService = function (printModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.savePrintModelURL();
        var data = '{"printModelDetails":"' + printModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.editPrintModelPostService = function (printModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.editPrintModelURL();
        var data = '{"printModelDetails":"' + printModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deletePrintModelGetService = function (printModelId) {
        var deffered = $q.defer();
        var url = configurationService.deletePrintModelURL();
        var data = '{"printModelId":"' + printModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveImageModelPostService = function (imageModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveImageModelURL();
        var data = '{"imageModelDetails":"' + imageModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.updateImageModelPostService = function (imageModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.updateImageModelURL();
        var data = '{"imageModelDetails":"' + imageModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteImageModelGetService = function (imageModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteImageModelURL();
        var data = '{"imageModelId":"' + imageModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return service;
});