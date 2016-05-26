
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('downloadFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.downloadGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.downloadURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return service;
});
