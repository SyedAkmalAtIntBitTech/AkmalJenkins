
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('userFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var userFactoryObject = {};
    userFactoryObject.userWelcomePageGet = function () {
        var deffered = $q.defer();
        var url = configurationService.userWelcomePageURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    userFactoryObject.userJspPagesGet = function (jspFileName) {
        var deffered = $q.defer();
        var url = configurationService.userJspPagesURL()+"/"+jspFileName;
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return userFactoryObject;
});
