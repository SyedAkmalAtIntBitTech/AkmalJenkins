
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
    signupFactoryObject.forgotPasswordPost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.forgotPasswordURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    signupFactoryObject.resetPasswordPost = function (resetData) {
        var deffered = $q.defer();
        var url = configurationService.resetPasswordURL();
        authenticatedServiceFactory.makeCall("POST", url, resetData, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    signupFactoryObject.updateUser = function (userDetails) {
        var deffered = $q.defer();
        var url = configurationService.updateUserURL();
        authenticatedServiceFactory.makeCall("POST", url, userDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return signupFactoryObject;
});
