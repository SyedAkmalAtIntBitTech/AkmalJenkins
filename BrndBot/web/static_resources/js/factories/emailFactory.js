
// **************** @author Arfa Shakeel @ Intbit **************** //


app.factory('emailFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.makeCall = function () {
        var deffered = $q.defer();
        var url = configurationService.sendEmailPostURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.sendEmailGetService = function () {
        var deffered = $q.defer();
        var url = configurationService.sendEmailGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.previewServletPostService = function () {
        var deffered = $q.defer();
        var url = configurationService.previewServletURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };  
    return service;
});