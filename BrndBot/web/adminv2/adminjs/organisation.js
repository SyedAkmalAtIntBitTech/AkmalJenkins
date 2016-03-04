/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
//$(document).ready(function (){
//    
//});
//var app = angular.module('myApp',[]);
function organizationcontroller($scope,$http) {
    
    $scope.organisation = function () {
        
                    $http({
                            method : 'GET',
                            url : '../getAllOrganizations.do'
                        }).success(function(data, status, headers, config) { 
                            $scope.organisationdetails = data.d.details;
                            alert(JSON.stringify(data.d.details));
                        }).error(function(data, status, headers, config) {
                                alert(nodataerror);
                        });
       
    };
       
}