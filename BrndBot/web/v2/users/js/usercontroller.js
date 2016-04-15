/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

function userController($scope, $http ) {

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
$scope.setCategoryID = function (id) {
    localStorage.setItem("categoryId",id);
    };
    
    $scope.marketingProgramCategory = function (marketingCategoryId) {
    localStorage.setItem("marketingCategoryId",marketingCategoryId);
    };

$scope.displaySubCategory = function () {
  
      var categoryId=  localStorage.getItem("categoryId");
        $http({
            method: 'GET',
            url: getHost() + '/getAllSubCategoriesByCategoryId.do?categoryId='+categoryId
        }).success(function (data, status, headers, config) {
            $scope.displayAllSubCategories = data.d.details;
              
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));

        });

    };
    
    $scope.displayMarketingProgram = function () {
        $http({
            method: 'GET',
            url: getHost() + '/getCompanyMarketingCategories.do'
        }).success(function (data, status, headers, config) {
            $scope.displayAllMarketingPrograms = data.d.details;
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));

        });

    };
    
    $scope.displayMarketingProgramByCategoryId = function () {
        var marketingCategoryId=  localStorage.getItem("marketingCategoryId");
        $http({
            method: 'GET',
            url: getHost() + '/getMarketingProgramsByCategoryId.do?marketingCategoryId='+marketingCategoryId
        }).success(function (data, status, headers, config) {
            $scope.displayMarketingPrograms = data.d.details;
            $("#htmlData").text(data.d.details[0].htmlData);           
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));

        });

    };
    $scope.showhtmldata = function(id,htmlData){
      $(".programList").css("background-color", "#fff").css("color","#3f3f42");
      $(".nameList"+id).css("background-color","#5cc1a3").css("color","#f6f7f7");      
    };

}