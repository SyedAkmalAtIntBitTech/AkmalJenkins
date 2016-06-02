/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

factoryApp.factory('authenticatedServiceFactory', function ($window, $http, $q) {
    var service = {};
    service.makeCall = function (methodType, URL, data, authType) {

//        var deffered = $q.defer();
//        var config = "";
//        if (authType === "Bearer")
//        {
//            var token = sessionConfigurationService.getBearerToken();
//            config = {headers: {
//                    'Authorization': token,
//                    'Accept': 'application/json',
//                    'Content-type': 'application/json',
//                },
//                withCredentials: true,
//                async: false
//            };
//        }
//        else
//        {
//            var token = sessionConfigurationService.getBasicToken();
//            config = {headers: {
//                    'Authentication': token,
//                    'Accept': 'application/json',
//                    'Content-type': 'application/json',
//                },
//                withCredentials: true,
//                async: false
//            };
//        }
//        if (methodType === "GET") {
//            var request = $http.get(URL, data, config).then(function (getData) {
//                debugAlert("GET method response" + JSON.stringify(getData));
//                if (sessionConfigurationService.isInvalidToken(getData))
//                {
//                    $window.location.href = '/GoatWebFrontEnd/login.html';
//                }
//                else 
//                    deffered.resolve(getData.data);
//            }, function (error) {
//                if (sessionConfigurationService.isInvalidToken(error))
//                {
//                    $window.location.href = '/GoatWebFrontEnd/login.html';
//                }
//            });
//        }
//        else if (methodType === "POST") {
//            $http.post(URL, data, config).then(function (putData) {
//                if (sessionConfigurationService.isInvalidToken(putData))
//                {
//                    $window.location.href = '/GoatWebFrontEnd/login.html';
//                }
//                else 
//                    deffered.resolve(putData.data);
//            }, function (error) {
//                if (sessionConfigurationService.isInvalidToken(error))
//                {
//                    $window.location.href = '/GoatWebFrontEnd/login.html';
//                }
//            });
//        }
//        else if (methodType === "DELETE") {
//            $http.delete(URL, data, config).then(function (deleteData) {
//                if (sessionConfigurationService.isInvalidToken(deleteData))
//                {
//                    $window.location.href = '/GoatWebFrontEnd/login.html';
//                }
//                else 
//                    deffered.resolve(deleteData.data);
//            }, function (error) {
//                if (sessionConfigurationService.isInvalidToken(error))
//                {
//                    $window.location.href = '/GoatWebFrontEnd/login.html';
//                }
//            });
//        }
//        else if (methodType === "PUT") {
//            $http.put(URL, config).then(function (putData) {
//                if (sessionConfigurationService.isInvalidToken(putData))
//                {
//                    $window.location.href = '/GoatWebFrontEnd/login.html';
//                }
//                else 
//                    deffered.resolve(putData.data);
//            }, function (error) {
//                if (sessionConfigurationService.isInvalidToken(error))
//                {
//                    $window.location.href = '/GoatWebFrontEnd/login.html';
//                }
//            });
//        }
//        return deffered.promise;
    };
    return service;
});