/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

function userController($scope, $http) {

    $scope.displayCategory = function () {
        $http({
            method: 'GET',
            url: getHost() + '/getAllCompanyCategories.do?channelId='+emailChannelId
        }).success(function (data, status, headers, config) {
            $scope.displayAllCategories = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));

        });

    };

$scope.displaySubCategory = function () {
   var categoryId= $("#categoryId").val();
        $http({
            method: 'GET',
            url: getHost() + '/getAllSubCategoriesByCategoryId.do?categoryId='+categoryId
        }).success(function (data, status, headers, config) {
            $scope.displayAllSubCategories = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));

        });

    };

}