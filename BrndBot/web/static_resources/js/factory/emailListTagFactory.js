
//************************ @author Syed Muzamil @ Intbit *************************//


factoryApp.factory('emailListTagFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var emailListTagFactoryObject = {};
    
    emailListTagFactoryObject.getAllEmailListTags = function (companyId) {
        var deffered = $q.defer();
        var url = configurationService.getAllEmailListTagsURL();
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    emailListTagFactoryObject.getAllEmailListTagsForFranchise = function (franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.getAllEmailListTagsForFranchiseURL()+"?franchiseId=" + franchiseId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListTagFactoryObject.getAllEmailListsAndTagsForFranchise = function (franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.getAllEmailListsAndTagsForFranchiseURL()+"?franchiseId=" + franchiseId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    emailListTagFactoryObject.saveEmailListTag = function (EmailListTag) {
        var deffered = $q.defer();
        var url = configurationService.saveEmailListTagURL();
        authenticatedServiceFactory.makeCall("POST", url, EmailListTag, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListTagFactoryObject.saveOrUpdateTagAndEmailList = function (tagAndEmailList) {
        var deffered = $q.defer();
        var url = configurationService.saveOrUpdateTagAndEmailListURL();
        authenticatedServiceFactory.makeCall("POST", url, tagAndEmailList, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    return emailListTagFactoryObject;
    
});
