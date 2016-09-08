
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('yourPlanFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var yourPlanFactoryObject = {};
    yourPlanFactoryObject.scheduledEntitiesGet = function (fromDate,toDate) {
        var deffered = $q.defer();
        var url = configurationService.scheduledEntitiesURL()+"?from="+ fromDate + "&to=" + toDate;
        authenticatedServiceFactory.makeCall("GET", url,"","").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    yourPlanFactoryObject.changeSchedulePost = function (data) {
        var deffered = $q.defer();
        var url = configurationService.changeScheduleURL();
        authenticatedServiceFactory.makeCall("POST", url,data,"").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    yourPlanFactoryObject.addActionPost = function (addActionDetails) {
        var deffered = $q.defer();
        var url = configurationService.addActionURL();        
        authenticatedServiceFactory.makeCall("POST", url, addActionDetails,"").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };

    yourPlanFactoryObject.allUsersInCompanyGet = function () {
        var deffered = $q.defer();
        var url = configurationService.allUsersInCompanyURL();
        authenticatedServiceFactory.makeCall("POST", url, "","").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    

    yourPlanFactoryObject.noOfUsersInCompanyGet = function () {
        var deffered = $q.defer();
        var url = configurationService.noOfUsersInCompanyURL();
        authenticatedServiceFactory.makeCall("POST", url, "","").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    

    yourPlanFactoryObject.scheduledEmailGet = function (schedule_id) {
        var deffered = $q.defer();
        var url = configurationService.scheduledEmailURL()+"?schedule_id="+schedule_id;
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    yourPlanFactoryObject.scheduledSocialPost = function (schedule_id) {
        var deffered = $q.defer();
        var url = configurationService.scheduledSocialPostURL()+"?schedule_id="+schedule_id;
        authenticatedServiceFactory.makeCall("GET", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    yourPlanFactoryObject.postToSocialPost = function () {
        var deffered = $q.defer();
        var url = configurationService.postToSocialURL();
        authenticatedServiceFactory.makeCall("POST", url, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };   
    return yourPlanFactoryObject;
});
