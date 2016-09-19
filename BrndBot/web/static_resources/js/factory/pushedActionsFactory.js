/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
//************************ @author SYED MUZAMIl @ Intbit *************************

factoryApp.factory('pushedActionsFactory', function ($q, authenticatedServiceFactory, configurationService) {
    var pushedActionsFactoryObject = {};
    
    pushedActionsFactoryObject.saveSchedulePushedActionsCompanies = function (pushedActionsDetails) {
    var deffered = $q.defer();
    var url = configurationService.emailListURL();
    authenticatedServiceFactory.makeCall("POST", url, pushedActionsDetails, "").then(function (data) {
        deffered.resolve(data);
    });
    return deffered.promise;
    };

    return pushedActionsFactoryObject;
});
