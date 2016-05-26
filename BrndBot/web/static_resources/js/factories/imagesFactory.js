
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('imageFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.imageIdGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.imageIdURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.saveImagePostService = function () {
        var deffered = $q.defer();
        var url = configurationService.saveImageURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return service;
});