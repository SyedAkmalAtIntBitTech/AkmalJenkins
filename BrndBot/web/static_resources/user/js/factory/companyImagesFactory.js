
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('companyImagesFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var companyImagesFactoryObject = {};
    companyImagesFactoryObject.companyImagesGet = function () {
        var deffered = $q.defer();
        var url = configurationService.companyImagesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    companyImagesFactoryObject.deleteCompanyImagesPost = function (companyImageId) {
        var deffered = $q.defer();
        var url = configurationService.deleteCompanyImagesURL()+"/"+companyImageId;
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return companyImagesFactoryObject;
});
