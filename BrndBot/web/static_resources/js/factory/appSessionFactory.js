//************************ @author Syed Ilyas @ Intbit *************************

factoryApp.factory('appSessionFactory', function ($q) {
    var AppSessionFactoryObject = {};

    //namespace details
    var emailObjectName = "email";
    var fbPostObjectName = "fbPostData";

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
    return AppSessionFactoryObject;
});