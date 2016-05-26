
//************************ @author Tasmiya P.S @ Intbit *************************

app.factory('emailListFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var service = {};
    service.emailListGetService = function (emailListName,requestMap) {
        var deffered = $q.defer();
        var url = configurationService.emailListURL();
        var data = '{"emailListName":"' + emailListName + '","requestMap":"' + requestMap + '"}';
        authenticatedServiceFactory.makeCall("GET", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    service.emailListSavePostService = function (emailListObject) {
        var deffered = $q.defer();
        var url = configurationService.emailListSaveURL();
        var data = '{"emailListObject":"' + emailListObject + '"}';
        authenticatedServiceFactory.makeCall("POST", url, data, "").then(function (data) {
            deffered.resolve(data);
        });
        return deffered.promise;
    };
    return service;
});

