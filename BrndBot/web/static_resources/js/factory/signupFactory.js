
// **************** @author Arfa Shakeel @ Intbit **************** //


factoryApp.factory('signupFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var signupFactoryObject = {};
    signupFactoryObject.signUpPageGet = function (jspFileName) {
        var deffered = $q.defer();
        var url = configurationService.signupURL()+"/"+jspFileName;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    signupFactoryObject.forgotPasswordPost = function () {
        var deffered = $q.defer();
        var url = configurationService.forgotPasswordURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    signupFactoryObject.resetPasswordPost = function () {
        var deffered = $q.defer();
        var url = configurationService.resetPasswordURL();
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return signupFactoryObject;
});
