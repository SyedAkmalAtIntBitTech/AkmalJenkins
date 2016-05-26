
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('companyImagesFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.companyImagesGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.companyImagesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.deleteCompanyImagesPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.deleteCompanyImagesURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return service;
});
