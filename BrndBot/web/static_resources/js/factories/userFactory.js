
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('userFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.userWelcomePageGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.userWelcomePageURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.userJspPagesGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.userJspPagesURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return service;
});
