/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

function userController($scope, $http ) {
    var marketingProgramId="";
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
    $scope.CheckExternalSource = function (subCategoryId,externalSourceName, externalSourceKeywordLookupId){
        var categoryid=$("#categoryId").val();
        if(externalSourceName === "Mindbody")
        {
            window.open(getHost() + 'user/emailexternalsource?categoryId='+categoryid+'&subCategoryId='+subCategoryId+'&externalSourceName='+externalSourceName+'&LookupId='+externalSourceKeywordLookupId, "_self");
        }
        else
        {
            window.open(getHost() + 'user/emailsubjects?categoryId='+categoryid+'&subCategoryId='+subCategoryId, "_self");
        }
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
        var marketingCategoryId= $("#marketingCategoryId").val();
//                localStorage.getItem("marketingCategoryId");
         
        $http({
            method: 'GET',
            url: getHost() + '/getMarketingProgramsByCategoryId.do?marketingCategoryId='+marketingCategoryId
        }).success(function (data, status, headers, config) {
//           alert(JSON.stringify(data));
            $scope.displayMarketingPrograms = data.d.details;
            var marketingProgramId= JSON.stringify(data.d.details[0].marketingProgramId);
            setTimeout(function () {
                $("#"+marketingProgramId).click();
              
            }, 0);
           for(var i=0;i<=data.d.details.length;i++)
           {
              $("#htmlData").text(data.d.details[i].htmlData); 
           }
        }).error(function (data, status, headers, config) {
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));

        });

    };
    
    $scope.saveMarketingProgram = function (marketingCategoryId){
       window.open(getHost() + 'user/usermarketingprogram?marketingCategoryId='+marketingCategoryId+"&marketingProgramId="+marketingProgramId, "_self");
    };
    
    $scope.showhtmldata = function(id,htmlData){
         $("#htmlData").text(htmlData);
        marketingProgramId = id;
        $(".programList").css("background-color", "#fff").css("color", "#3f3f42");
        $(".nameList"+id).css("background-color","#5cc1a3").css("color","#f6f7f7");  
       
    };

}