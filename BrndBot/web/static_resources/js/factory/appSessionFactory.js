//************************ @author Syed Ilyas @ Intbit *************************

factoryApp.factory('appSessionFactory', function ($q) {
    var AppSessionFactoryObject = {};
    
    //namespace details
    var emailObjectName = "email";
    var companyObjectName = "companyDetails";
    var dashboardMessageVarName = "dashboardMessage";
    
    
    AppSessionFactoryObject.setEmail = function (emailObject) {
        var deffered = $q.defer();
        var data = false;
        if(emailObject)
        {
            localStorage.setItem(emailObjectName,JSON.stringify(emailObject));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.getEmail = function () {
        var deffered = $q.defer();
        var requestedValue = kGlobalEmailObject;
        
            var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
            if(emailLocalObject){
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
    
    
    AppSessionFactoryObject.setCompany = function (companyObject) {
        var deffered = $q.defer();
        var data = false;
        if(companyObject)
        {
            localStorage.setItem(companyObjectName,JSON.stringify(companyObject));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.getCompany = function () {
        var deffered = $q.defer();
        var requestedValue = kGlobalCompanyObject;
        
            var companyLocalObject = JSON.parse(localStorage.getItem(companyObjectName));
            if(companyLocalObject){
               requestedValue = companyLocalObject;
            }
        
        deffered.resolve(requestedValue);
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
        if(message)
        {
            localStorage.setItem(dashboardMessageVarName,message);
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.getDashboardMessage = function () {
        var deffered = $q.defer();
        var requestedValue = "";
        
            var sessionMessage = localStorage.getItem(dashboardMessageVarName);
            if(sessionMessage){
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
    
    
    return AppSessionFactoryObject;
});