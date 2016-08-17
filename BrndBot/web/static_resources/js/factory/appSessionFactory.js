//************************ @author Syed Ilyas @ Intbit *************************

factoryApp.factory('appSessionFactory', function ($q) {
    var AppSessionFactoryObject = {};
    
    //namespace details
    var emailObjectName = "email";
    var email = { };
    AppSessionFactoryObject.setEmail = function (key, value) {
        var deffered = $q.defer();
        var data = false;
        if(key && value)
        {
//            var keyString = email+"."+key;
            var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
            if(emailLocalObject){
                email = emailLocalObject;
            }
            email[key] = value
            localStorage.setItem(emailObjectName,JSON.stringify(email));
            data = true;
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.getEmail = function (key) {
        var deffered = $q.defer();
        var requestedValue = "";
        if(key)
        {
            var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
            if(emailLocalObject){
                var value = emailLocalObject[key];
                if(value)
                    requestedValue = value;
            }
        }
        deffered.resolve(requestedValue);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.getAllEmail = function () {
        var deffered = $q.defer();
        var requestedValue;
        
            var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
            if(emailLocalObject){
               requestedValue = emailLocalObject;
            }
        
        deffered.resolve(requestedValue);
        return deffered.promise;
    };
    
    AppSessionFactoryObject.clearAllEmail = function () {
        var deffered = $q.defer();
        var data = true;
        localStorage.removeItem(emailObjectName);
        deffered.resolve(data);
        return deffered.promise;
    };
    AppSessionFactoryObject.clearKeyEmail = function (key) {
        var deffered = $q.defer();
        var data = true;
        var emailLocalObject = JSON.parse(localStorage.getItem(emailObjectName));
        if(emailLocalObject){
            if(emailLocalObject[key])
                emailLocalObject[key] = "";
            localStorage.setItem(emailLocalObject);
        }
        deffered.resolve(data);
        return deffered.promise;
    };
    return AppSessionFactoryObject;
});