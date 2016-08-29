//************************ @author Syed Ilyas @ Intbit *************************

factoryApp.factory('appSessionFactory', function ($q) {
    var AppSessionFactoryObject = {};
    
    //namespace details
    var emailObjectName = "email";
    var companyObjectName = "companyDetails";
    
    
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
    
    
    return AppSessionFactoryObject;
});