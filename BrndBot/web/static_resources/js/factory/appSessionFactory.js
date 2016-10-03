//************************ @author Syed Ilyas @ Intbit *************************

factoryApp.factory('appSessionFactory', function ($q) {
    var AppSessionFactoryObject = {};

    //namespace details
    var emailObjectName = "email";
    var fbPostObjectName = "fbPostData";
    var companyObjectName = "companyDetails";
    var dashboardMessageVarName = "dashboardMessage";
    var userObjectName = "user";
    var popupObjectName = "popupFlags";

    AppSessionFactoryObject.setEmail = function (emailObject) {
        var deffered = $q.defer();
        var data = false;
        if (emailObject)
        {
            localStorage.setItem(emailObjectName, JSON.stringify(emailObject));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };

    AppSessionFactoryObject.getEmail = function () {
        var deffered = $q.defer();
        var requestedValue = kGlobalEmailObject;

        var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
        if (emailLocalObject) {
            requestedValue = emailLocalObject;
        }

        deffered.resolve(requestedValue);
        return deffered.promise;
    };

    AppSessionFactoryObject.clearEmail = function () {
        var deffered = $q.defer();
        var data = true;
        localStorage.removeItem(emailObjectName);
        deffered.resolve(data);
        return deffered.promise;
    };
    AppSessionFactoryObject.setFbPostData = function (fbPostDataObject) {
        var deffered = $q.defer();
        var data = false;
        if (fbPostDataObject)
        {
            localStorage.setItem(fbPostObjectName, JSON.stringify(fbPostDataObject));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    AppSessionFactoryObject.setCompany = function (companyObject) {
        var deffered = $q.defer();
        var data = false;
        if (companyObject)
        {
            localStorage.setItem(companyObjectName, JSON.stringify(companyObject));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    AppSessionFactoryObject.getFbPostData = function () {
        var deffered = $q.defer();
        var requestedValue = kGlobalFbPostDataObject;

        var fbPostDataLocalObject = JSON.parse(localStorage.getItem(fbPostObjectName));
        if (fbPostDataLocalObject) {
            requestedValue = fbPostDataLocalObject;
        }

        deffered.resolve(requestedValue);
        return deffered.promise;
    };

    AppSessionFactoryObject.getCompany = function () {
        var deffered = $q.defer();
        var requestedValue = kGlobalCompanyObject;

        var companyLocalObject = JSON.parse(localStorage.getItem(companyObjectName));
        if (companyLocalObject) {
            requestedValue = companyLocalObject;
        }

        deffered.resolve(requestedValue);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.isCurrentCompanyInFranchise = function(){
        var deffered = $q.defer();
        var requestValue = false;
        var franchiseId = 0;
        
        var companyLocalObject = JSON.parse(localStorage.getItem(companyObjectName));
        franchiseId = companyLocalObject.franchiseId;

        if (franchiseId == null){
            requestValue = false;
        }else {
            requestValue = true;            
        }
        deffered.resolve(requestValue);
        return deffered.promise;
    };

    AppSessionFactoryObject.isCurrentCompanyAFranchiseHeadquarter = function(){
        var deffered = $q.defer();
        var requestValue = false;
        var isHeadquarter = 0;
        
        var companyLocalObject = JSON.parse(localStorage.getItem(companyObjectName));
        isHeadquarter = companyLocalObject.isHeadquarter;

        if (isHeadquarter){
            requestValue = true;
        }else {
            requestValue = false;            
        }
        deffered.resolve(requestValue);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.clearCompany = function () {
        var deffered = $q.defer();
        var data = true;
        localStorage.removeItem(companyObjectName);
        deffered.resolve(data);
        return deffered.promise;
    };

    AppSessionFactoryObject.setDashboardMessage = function (message) {
        var deffered = $q.defer();
        var data = false;
        if (message)
        {
            localStorage.setItem(dashboardMessageVarName, message);
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };

    AppSessionFactoryObject.getDashboardMessage = function () {
        var deffered = $q.defer();
        var requestedValue = "";

        var sessionMessage = localStorage.getItem(dashboardMessageVarName);
        if (sessionMessage) {
            requestedValue = sessionMessage;
        }

        deffered.resolve(requestedValue);
        return deffered.promise;
    };

    AppSessionFactoryObject.clearDashboardMessage = function () {
        var deffered = $q.defer();
        var data = true;
        localStorage.removeItem(dashboardMessageVarName);
        deffered.resolve(data);
        return deffered.promise;
    };
    AppSessionFactoryObject.setUser = function (userObject) {
        var deffered = $q.defer();
        var data = false;
        if (userObject)
        {
            localStorage.setItem(userObjectName, JSON.stringify(userObject));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    AppSessionFactoryObject.getUser = function () {
        var deffered = $q.defer();
        var requestedValue = kGlobalUserObject;

        var userLocalObject = JSON.parse(localStorage.getItem(userObjectName));
        if (userLocalObject) {
            requestedValue = userLocalObject;
        }

        deffered.resolve(requestedValue);
        return deffered.promise;
    };
    AppSessionFactoryObject.setPopupFlag = function (popupObject) {
        var deffered = $q.defer();
        var data = false;
        if (popupObject)
        {
            localStorage.setItem(popupObjectName, JSON.stringify(popupObject));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    AppSessionFactoryObject.getPopupFlag = function () {
        var deffered = $q.defer();
        var requestedValue = kGlobalPopupFlagsObject;

        var popupLocalObject = JSON.parse(localStorage.getItem(popupObjectName));
        if (popupLocalObject) {
            requestedValue = popupLocalObject;
        }

        deffered.resolve(requestedValue);
        return deffered.promise;
    };

    AppSessionFactoryObject.clearAllSessions = function () {
        var deffered = $q.defer();
        var data = true;
        localStorage.removeItem(emailObjectName);
        localStorage.removeItem(fbPostObjectName);
        localStorage.removeItem(companyObjectName);
        localStorage.removeItem(dashboardMessageVarName);
        localStorage.removeItem(userObjectName);
        deffered.resolve(data);
        return deffered.promise;
    };
    return AppSessionFactoryObject;
});