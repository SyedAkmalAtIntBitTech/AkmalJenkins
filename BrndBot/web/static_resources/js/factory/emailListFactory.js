
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
    emailListFactoryObject.emailListSavePost = function (emailListObject) {
       var deffered = $q.defer();
       var url = configurationService.emailListURL()+"?emailListName="+emailListName+"&update="+requestMap;
       authenticatedServiceFactory.makeCall("GET", url, "", "").then(function (data) {
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
    return emailListFactoryObject;
});



