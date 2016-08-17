
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('onboardingFactory', function ($q,authenticatedServiceFactory, configurationService) {
    var onboardingFactoryObject = {};
    onboardingFactoryObject.userPost = function (usersDetails) {
        var deffered = $q.defer();
        var url = configurationService.usersURL();
        var data = '{"usersDetails":"' + usersDetails + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveUserPost = function (userDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveUserURL();
        authenticatedServiceFactory.makeCall("POST", url, userDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveInvitedUserPost = function (userDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveInvitedUserURL();
        authenticatedServiceFactory.makeCall("POST", url, userDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.getAllUserCompanies = function (userId) {
        var deffered = $q.defer();
        var url = configurationService.getAllUserCompaniesURL()+"?userId="+userId;
        authenticatedServiceFactory.makeCall("GET", url, userId, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    onboardingFactoryObject.userCompanyDetailsGet = function (userId) {
        var deffered = $q.defer();
        var url = configurationService.getUserCompanyDetailsURL()+"?userId="+userId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    onboardingFactoryObject.getLoggedInUserId = function (){
        var deffered = $q.defer();
        var url = configurationService.getLoggedInUserId();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    }
    onboardingFactoryObject.getAccountStatus = function (companyDetails) {
        var deffered = $q.defer();
        var url = configurationService.getAccountStatusURL();
        authenticatedServiceFactory.makeCall("POST", url, companyDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    onboardingFactoryObject.inviteUserPost = function (userDetails) {
        var deffered = $q.defer();
        var url = configurationService.inviteUserURL();
        authenticatedServiceFactory.makeCall("POST", url, userDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    onboardingFactoryObject.resendUserInvitePost = function (inviteId) {
        var deffered = $q.defer();
        var url = configurationService.resendUserInviteURL();
        authenticatedServiceFactory.makeCall("POST", url, inviteId, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    onboardingFactoryObject.editUserRolePost = function (userDetails) {
        var deffered = $q.defer();
        var url = configurationService.editUserRoleURL();
        authenticatedServiceFactory.makeCall("POST", url, userDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.removeUserPost = function (inviteId) {
        var deffered = $q.defer();
        var url = configurationService.removeUserURL();
        authenticatedServiceFactory.makeCall("POST", url, inviteId, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    onboardingFactoryObject.getInvitedUsersPost = function () {
        var deffered = $q.defer();
        var url = configurationService.getInvitedUsersURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    onboardingFactoryObject.saveStudioIdPost = function (studioId) {
        var deffered = $q.defer();
        var url = configurationService.saveStudioIdURL()+"?studioId="+studioId;
        authenticatedServiceFactory.makeCall("POST", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.isMindbodyActivated = function (){
        var deffered = $q.defer();
        var url = configurationService.activatedGetURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data){
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.completedActivationGet = function () {
        var deffered = $q.defer();
        var url = configurationService.completedActivationURL();
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveCompanyPost = function (companyDetails) {
        var deffered = $q.defer();
        var url = configurationService.saveCompanyURL();
        authenticatedServiceFactory.makeCall("POST", url, companyDetails, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.colorsForLogoGet = function () {
        var deffered = $q.defer();
        var url = configurationService.colorsForLogoURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    onboardingFactoryObject.saveCompanyLogoPost = function (imageTypeData,imgDataObj) {
        var deffered = $q.defer();
        var url = configurationService.saveCompanyLogoURL();
        var globalImage = {"imageType": imageTypeData, "imageData": imgDataObj.base64ImgString};
        authenticatedServiceFactory.makeCall("POST", url, globalImage, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return onboardingFactoryObject;
});


