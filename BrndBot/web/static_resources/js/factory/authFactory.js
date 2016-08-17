/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

factoryApp.factory('authenticatedServiceFactory', function ($http, $q) {
    var service = {};
    service.makeCall = function (methodType, URL, data, authType) {
        var deffered = $q.defer();
        var config = "";
        config = {headers: {'Content-type': 'application/json'}};

            var userId = localStorage.getItem("userId");
//            alert(JSON.stringify(userId));
        if (authType === "UPLOADIMAGE")
        {
            if (methodType === "POST") {
                $http.post(URL, data, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                }).then(function (putData) {
                    deffered.resolve(putData.data);
                }, function (error) {
                });
            }
        } else {
            if (methodType === "GET") {
                $http.get(URL, data, config).then(function (getData) {
                    deffered.resolve(getData.data);
                }, function (error) {

                });
            } else if (methodType === "POST") {
                $http.post(URL, data, config).then(function (putData) {
                    deffered.resolve(putData.data);
                }, function (error) {
                });
            }
        }
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
        return deffered.promise;
    };
    return service;
});