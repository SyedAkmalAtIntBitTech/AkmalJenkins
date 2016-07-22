
//************************ @author Tasmiya P.S @ Intbit *************************

factoryApp.factory('utilFactory', function ($q) {
    var UtilFactoryObject = {};
    UtilFactoryObject.checkForNull = function (variable) {
        var deffered = $q.defer();
        var data = false;
        if(!variable)
            data = true;
        deffered.resolve(data);
        return deffered.promise;
    };
    return UtilFactoryObject;
});
