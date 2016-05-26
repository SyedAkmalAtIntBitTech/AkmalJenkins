
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('uploadImageFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function () {
        var deffered = $q.defer();
        var url = configurationService.uploadByAdminURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.uploadByUserPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.uploadByUserURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    
    return service;
});