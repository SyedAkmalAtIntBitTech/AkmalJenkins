/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

angular.module('marketingprogramota',[]).controller('marketingProgramsController',function($scope,$http)
{
//    $scope.marketingActions=[ {"days": "4","description": "Description","time": "03:30","is_recuring": false,
//                              "title": "new action","type": "Facebook"
//                             },
//                             {"days": "5","description": "Test","time": "14:30","is_recuring": true,
//                              "title": "action2","type": "Twitter"
//                             }
//                            ]                              
    $scope.indexvalue ="";       
    var globalActionsArray =[];
    $scope.marketingProgramActions="";
//    var marketingprogram=$scope.markeingActions;

    $scope.saveChangedOneTimeAction= function (){
        $("#editMarketingProgramsPopup").hide();
        $("#addOrganizationPopupDiv").hide();
        $(".overflowhide").css("overflow","auto");
    };
    
    $scope.getlistnum = function (index) {
              $(".overflowhide").css("overflow","hidden").animate({scrollTop:0}, '10', 'swing');
              $("#editMarketingProgramsPopup").show();
              $("#addMarketingProgramsPopup").hide();
              $("#addOrganizationPopupDiv").show();
              $scope.indexvalue =index;
    };
    
    $scope.addRecurringActions = function () {
              $(".overflowhide").css("overflow","hidden").animate({scrollTop:0}, '10', 'swing');
              $("#editMarketingProgramsPopup").hide();
              $("#addMarketingProgramsPopupDiv").show();
              $("#addMarketingProgramsPopup").show();
    };
    $scope.addOneTimeActions = function () {
              $(".overflowhide").css("overflow","hidden").animate({scrollTop:0}, '10', 'swing');
              $("#editMarketingProgramsPopup").hide();
              $("#addMarketingProgramsPopupDiv").show();
              $("#addMarketingProgramsPopup").show();
    };
       
    
    $scope.getMarketingProgramActionsById = function (){
                $http({
                            method : 'GET',
                            url : getHost()+'/getMarketingProgramActionsById.do?marketingProgramId=11'
                        }).success(function(data, status, headers, config) {
                            $scope.getAllMarketingProgram = data.d.details[0];
                            $scope.getAllMarketingProgramActions = data.d.details[0].marketingActions; 
//                            alert(JSON.stringify($scope.getAllMarketingProgramActions));
                            globalActionsArray.push($scope.getAllMarketingProgramActions);
                            $scope.marketingProgramActions=globalActionsArray[0];
//                            alert(JSON.stringify($scope.marketingProgramActions));
//                        $scope.getAllMarketingProgramActions;
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });
                        
    };
    
    $scope.createOneTimeAction = function (){
        var newOneTimeActionName = $("#newOneTimeActionName").val();
        var newActionTypeOneTimeActions= $("#newActionTypeOneTimeActions").val();
        var newActionsNoOfDays = $("#newActionsNoOfDays").val();
        var newActionTime = $("#newActionTime").val();
        $scope.newMarketingAction={"days": newActionsNoOfDays,"description": "","time": newActionTime,"is_recuring": false,
                                    "title": newOneTimeActionName,"type": newActionTypeOneTimeActions
                                   };                                   
        globalActionsArray.push($scope.newMarketingAction);
        alert(JSON.stringify(globalActionsArray)+"\n--1");
        globalActionsArray.push(angular.copy($scope.marketingProgramActions[0]));
        alert(JSON.stringify(globalActionsArray)+"\n--2");
        $scope.marketingProgramActions[0]=globalActionsArray;
        alert(JSON.stringify($scope.marketingProgramActions[0])+"\n--2");
        $("#newOneTimeActionName").val('')
        $("#newActionTypeOneTimeActions").val('');
        $("#newActionsNoOfDays").val('');
        $("#newActionTime").val('');
    };
    
    
    $scope.saveMarketingProgramActions = function (){
        var allProgramActions=angular.copy($scope.marketingProgramActions);
        var marketingProgramName = $("#marketingProgramName").val();
        var marketingProgramHtml= $("#marketingProgramHtml").val();
//        var marketingProgramActions=angular.copy($scope.getAllMarketingProgramActions);
        var marketingProgramdetails = {"marketingProgramName": marketingProgramName,"htmlData": marketingProgramHtml,"marketingActions": allProgramActions};
        alert(JSON.stringify(marketingProgramdetails));
        if(marketingProgramName===""){
            alert("Please enter Marketing program name!");
               $("#marketingProgramName").focus();
               return false;
        }
        if(marketingProgramHtml===""){
            alert("Please add Marketing program Html!");
               $("#marketingProgramHtml").focus();
               return false;
        } 
                    else{
                    $.ajax({
                            method: 'POST',
                            url: getHost() + '/saveMarketingProgramActions.do',
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(marketingProgramdetails)
                        }).success(function (data, status, headers, config)
                        {  
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'adminv2/marketingprogramdetails.jsp', "_self");
                        }).error(function(data, status, headers, config){
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });                         
                    }
        
    };
});