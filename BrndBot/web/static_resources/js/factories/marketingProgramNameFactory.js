
// **************** @author Arfa Shakeel @ Intbit **************** //

app.factory('marketingProgramNameFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function () {
        var deffered = $q.defer();
        var url = configurationService.marketingProgramNameURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    return service;
});