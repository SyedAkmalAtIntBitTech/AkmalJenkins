
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('modelFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var modelFactoryObject = {};
    modelFactoryObject.emailModelGet = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.emailModelURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.EmailModelsIdGet = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.emailModelsIdURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.nonAddedEmailModelsGet = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedEmailModelsURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.nonAddedImageModelsGet = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedImageModelsURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.nonAddedPrintModelsGet = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.nonAddedPrintModelsURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.printModelGet = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.printModelURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.imageModelSubCategoryGet = function (subCategoryId) {
        var deffered = $q.defer();
        var url = configurationService.imageModelSubCategoryURL();
        var data = '{"subCategoryId":"' + subCategoryId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.emailModelGet = function () {
        var deffered = $q.defer();
        var url = configurationService.emailModelURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.emailModelIdGet = function (emailModelId) {
        var deffered = $q.defer();
        var url = configurationService.emailModelIdURL();
        var data = '{"emailModelId":"' + emailModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.imageModelGet = function () {
        var deffered = $q.defer();
        var url = configurationService.imageModelURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.imageModelIdGet = function (imageModelId) {
        var deffered = $q.defer();
        var url = configurationService.imageModelIdURL();
        var data = '{"imageModelId":"' + imageModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.allPrintModelGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allPrintModelURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.printModelIdGet = function (printModelId) {
        var deffered = $q.defer();
        var url = configurationService.printModelIdURL();
        var data = '{"printModelId":"' + printModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.saveEmailModelGet = function (emailModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveEmailModelURL();
        var data = '{"emailModelDetails":"' + emailModelDetails + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.editEmailModelPost = function (emailModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.editEmailModelURL();
        var data = '{"emailModelDetails":"' + emailModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.deleteEmailModelGet = function (emailModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailModelURL();
        var data = '{"emailModelId":"' + emailModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.saveCategoryEmailModelPost = function (emailModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCategoryEmailModelURL();
        var data = '{"emailModelDetails":"' + emailModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.deleteCategoryEmailModelGet = function (subCategoryEmailModelID) {
        var deffered = $q.defer();
        var url = configurationService.deleteCategoryEmailModelURL();
        var data = '{"subCategoryEmailModelID":"' + subCategoryEmailModelID + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.saveCategoryPrintModelPost = function (printModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCategoryPrintModelURL();
        var data = '{"printModelDetails":"' + printModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.deleteCategoryPrintModelGet = function (subCategoryPrintModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteCategoryPrintModelURL();
        var data = '{"subCategoryPrintModelId":"' + subCategoryPrintModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.saveCategoryImageModelPost = function (imageModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCategoryImageModelURL();
        var data = '{"imageModelDetails":"' + imageModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.deleteCategoryImageModelGet = function (subCategoryImageModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteCategoryImageModelURL();
        var data = '{"subCategoryImageModelId":"' + subCategoryImageModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.savePrintModelPost = function (printModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.savePrintModelURL();
        var data = '{"printModelDetails":"' + printModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.editPrintModelPost = function (printModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.editPrintModelURL();
        var data = '{"printModelDetails":"' + printModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.deletePrintModelGet = function (printModelId) {
        var deffered = $q.defer();
        var url = configurationService.deletePrintModelURL();
        var data = '{"printModelId":"' + printModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.saveImageModelPost = function (imageModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveImageModelURL();
        var data = '{"imageModelDetails":"' + imageModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.updateImageModelPost = function (imageModelDetails) {
        var deffered = $q.defer();
        var url = configurationService.updateImageModelURL();
        var data = '{"imageModelDetails":"' + imageModelDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    modelFactoryObject.deleteImageModelGet = function (imageModelId) {
        var deffered = $q.defer();
        var url = configurationService.deleteImageModelURL();
        var data = '{"imageModelId":"' + imageModelId + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return modelFactoryObject;
});