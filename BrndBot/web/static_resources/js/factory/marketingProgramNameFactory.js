
// **************** @author Arfa Shakeel @ Intbit **************** //

app.factory('marketingProgramNameFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var marketingProgramFactoryObject = {};
    marketingProgramFactoryObject.marketingProgramNameGet = function () {
        var deffered = $q.defer();
        var url = configurationService.marketingProgramNameURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    return marketingProgramFactoryObject;
});