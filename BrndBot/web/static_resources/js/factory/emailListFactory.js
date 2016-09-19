
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('emailListFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var emailListFactoryObject = {};

    emailListFactoryObject.emailListGet = function (emailListName,requestMap) {
        var deffered = $q.defer();
        var url = configurationService.emailListURL()+"?emailListName="+emailListName+"&update="+requestMap;
//        var data = '{"emailListName":"' + emailListName + '","requestMap":"' + requestMap + '"}';
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.getAllEmailListNames = function (companyId) {
        var deffered = $q.defer();
        var url = configurationService.getAllEmailListNamesURL()+"?companyId="+companyId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.getAllEmailListWithNoOfContactsForUser = function (companyId) {
        var deffered = $q.defer();
        var url = configurationService.emailListGetWithNoOfContactsForUserURL()+"?companyId="+companyId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.getAllEmailListWithNoOfContactsForMindBody = function (companyId) {
        var deffered = $q.defer();
        var url = configurationService.getAllEmailListWithNoOfContactsForMindBodyURL()+"?companyId="+companyId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.addEmailListContact = function (data) {
        var deffered = $q.defer();
        var url = configurationService.addEmailListContactURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.getContactsOfEmailList = function (data) {
        var deffered = $q.defer();
        var url = configurationService.getContactsOfEmailListURL()+"?emailListId="+data;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.editContact = function (data) {
        var deffered = $q.defer();
        var url = configurationService.editContactURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.deleteEmailList = function (data) {
        var deffered = $q.defer();
        var url = configurationService.deleteEmailListURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
   
    emailListFactoryObject.createEmailList = function (data) {
        var deffered = $q.defer();
        var url = configurationService.createEmailListURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.addContactList = function (data) {
        var deffered = $q.defer();
        var url = configurationService.addContactListURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.deleteContactList = function (data) {
        var deffered = $q.defer();
        var url = configurationService.deleteContactListURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };    
    
    emailListFactoryObject.emailListSavePost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.emailListSaveURL();
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    emailListFactoryObject.emailListTagsForFranchiseGet = function (franchiseId) {
        var deffered = $q.defer();
        var url = configurationService.emailListTagsForFranchiseURL()+ "?franchiseId="+ franchiseId;
        authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    
    return emailListFactoryObject;
});



