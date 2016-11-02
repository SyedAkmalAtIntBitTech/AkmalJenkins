/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

angular.module('marketingprogramota',[]).controller('marketingProgramsController',function($scope,$http)
{
    var globalMarketingActionId="";                      
    $scope.indexvalue ="";       
    var globalActionsArray =[];
    $scope.marketingProgramActions="";
//    var marketingprogram=$scope.markeingActions;
    $scope.marketingProgramId = null;
    $scope.getMarketingProgramId = function(){
        var qs = (function(a) {
            if (a == "") return {};
            var b = {};
            for (var i = 0; i < a.length; ++i)
            {
                var p=a[i].split('=', 2);
                if (p.length == 1)
                    b[p[0]] = "";
                else
                    b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
            }
            return b;
        })(window.location.search.substr(1).split('&'));

        $scope.marketingProgramId = qs["marketingProgramId"];    
        
    };
    $scope.closeOneTimeActionPopUp=function (){
        $("#addMarketingProgramsPopup").hide(); 
        $("#addOrganizationPopupDiv").hide();
        $(".overflowhide").css("overflow","auto");
    };
    $scope.closeEditActionPopUp=function (){
        $("#editMarketingProgramsPopup").hide(); 
        $("#addOrganizationPopupDiv").hide();
        $(".overflowhide").css("overflow","auto");
    };
    $scope.saveChangedOneTimeAction= function (){
        $("#editMarketingProgramsPopup").hide();
        $("#addOrganizationPopupDiv").hide();
        $(".overflowhide").css("overflow","auto");
    };
    
    $scope.getlistnum = function (index,action) {
              $(".overflowhide").css("overflow","hidden").animate({scrollTop:0}, '10', 'swing');
              $("#editMarketingProgramsPopup").show();
              $("#addMarketingProgramsPopup").hide();
              $("#addOrganizationPopupDiv").show();
              if(action=='recurring'){
                $("#actionTypeOneTimeActions option:contains(Email)").attr('selected', 'selected');
                $("#actionTypeOneTimeActions").attr("disabled", "disabled");
              }else{
                $("#actionTypeOneTimeActions").attr("disabled", false);
              }
              $scope.indexvalue =index;
    };
    
    $scope.addRecurringActions = function () {
              $("#createRecurringActionPopUpTitle").show();
              $("#createOneTimeActionPopUpTitle").hide();
              $("#createRecurringActionButton").show();
              $("#createOneTimeActionButton").hide();
              $(".overflowhide").css("overflow","hidden").animate({scrollTop:0}, '10', 'swing');
              $("#editMarketingProgramsPopup").hide();
              $("#addMarketingProgramsPopupDiv").show();
              $("#addMarketingProgramsPopup").show();
//              $("#newActionTypeOneTimeActions option:contains(Email)").attr('selected', 'selected');
//              $("#newActionTypeOneTimeActions").attr("disabled", "disabled");
               $("#recurringActionSelect").show();
               $("#oneTimeAction").hide();
               $("#recurringActionDays").show();
               $("#oneTimeActionActionDays").hide();
    };
    $scope.addOneTimeActions = function () {
              $("#createRecurringActionPopUpTitle").hide();
              $("#createOneTimeActionPopUpTitle").show();
              $("#createRecurringActionButton").hide();
              $("#createOneTimeActionButton").show();
              $(".overflowhide").css("overflow","hidden").animate({scrollTop:0}, '10', 'swing');
              $("#editMarketingProgramsPopup").hide();
              $("#addMarketingProgramsPopupDiv").show();
              $("#addMarketingProgramsPopup").show();
              $("#recurringActionSelect").hide();
              $("#oneTimeAction").show();
              $("#recurringActionDays").hide();
               $("#oneTimeActionActionDays").show();
    };
       
    
    $scope.getMarketingProgramActionsById = function (){
        
        var marketingProgramId=$scope.marketingProgramId
        if(window.location.href.indexOf("marketingprogramdetails?marketingProgramId") > -1) {
            $("#updateMarketingProgramSaveButton").show();
            $("#addMarketingProgramSaveButton").hide();
         }
         if(window.location.href.indexOf("marketingprogramdetails?add=yes") > -1) {
            $("#addMarketingProgramSaveButton").show();
            $("#updateMarketingProgramSaveButton").hide();
         }
                     
        $http({
                    method : 'GET',
                    url : getHost()+'/getMarketingProgramActionsById?marketingProgramId='+marketingProgramId
                }).success(function(data, status, headers, config) {
                    
                    $scope.getAllMarketingProgram = data.d.details[0];
                    var marketingActionId=JSON.stringify(data.d.details[0].marketingActionId);
                    globalMarketingActionId=marketingActionId;
                    $scope.getAllMarketingProgramActions = data.d.details[0].marketingActions;
                    var marketingProgramsOld= $scope.getAllMarketingProgramActions;
                    var newArray = $.merge(globalActionsArray, marketingProgramsOld);
                    $scope.marketingProgramActions=newArray;
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });
                        
    };
    
    
    $scope.deleteProgramAction = function (indexvalue){
        alert(programActionDeleted);
        delete globalActionsArray[indexvalue];
        var delNullArray = globalActionsArray.filter(function(x){return x !== null});
        globalActionsArray=delNullArray;
        $scope.marketingProgramActions=globalActionsArray;
        $scope.closeEditActionPopUp();
    };
    
    $scope.createOneTimeAction = function (is_recurring){
        var newOneTimeActionName = $("#newOneTimeActionName").val();
        var newActionTypeOneTimeActions= "";
        if(is_recurring)
            newActionTypeOneTimeActions = "Email";
        else
            newActionTypeOneTimeActions = $("#newActionTypeOneTimeActions").val();
        var newActionsNoOfDays = $("#newActionsNoOfDays").val();
        var newActionTime = $("#newActionTime").val();
        if (newOneTimeActionName === ""){
            alert("Name not entered, please enter the name");
            $("#newOneTimeActionName").focus();
            return false;
        }
        
        if (newActionsNoOfDays === ""){
            alert("No of days not entered, please enter no of days");
            $("#newActionsNoOfDays").focus();
            return false;
        }

        if (newActionTime === ""){
            alert("Time not entered, please enter the time");
            $("#newActionTime").focus();
            return false;
        }else {
            $scope.newMarketingAction={"days": newActionsNoOfDays,"description": "","time": newActionTime,"is_recurring": is_recurring,
                                        "title": newOneTimeActionName,"type": newActionTypeOneTimeActions,"tilldate": ""
                                       };                                   
            globalActionsArray.push($scope.newMarketingAction);
            $scope.marketingProgramActions=globalActionsArray;
            $("#newOneTimeActionName").val('');
    //        $("#newActionTypeOneTimeActions").val('');
            $("#newActionsNoOfDays").val('');
            $("#newActionTime").val('');
            $scope.closeOneTimeActionPopUp();
        }

    };
    
    
    $scope.saveMarketingProgramActions = function (){
        $scope.marketingProgramActions=globalActionsArray;
//        var marketingProgramId=$("#marketingProgramIdTag").val();
        var ProgramActions=angular.copy($scope.marketingProgramActions); // angular.copy() will remove the $$hashkey from JSONArray
        
        
        var allProgramActionsStringify=JSON.stringify(ProgramActions);
        var allProgramActions=JSON.parse(allProgramActionsStringify);
        var marketingProgramName = $("#marketingProgramName").val();
        var marketingProgramHtml= $("#marketingProgramHtml").val();
        var marketingProgramdetails = {"marketingProgramName": marketingProgramName,"htmlData": marketingProgramHtml,"marketingActions": allProgramActions};
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
                            url: getHost() + 'saveMarketingProgramActions',
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(marketingProgramdetails)
                        }).success(function (data, status, headers, config)
                        {  
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'admin/marketingprogram', "_self");
                        }).error(function(data, status, headers, config){
                            alert(JSON.stringify(data));
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });                         
                    }
        
    };
    
    
    $scope.updateMarketingProgramActions=function (){
        var marketingProgramId=$("#marketingProgramIdTag").val();
        var allProgramActions=angular.copy($scope.marketingProgramActions); // angular.copy() will remove the $$hashkey from JSONArray
        var marketingProgramName = $("#marketingProgramName").val();
        var marketingProgramHtml= $("#marketingProgramHtml").val();
        var updatemarketingProgramdetails = {"marketingProgramId":marketingProgramId,"marketingProgramName": marketingProgramName,"htmlData": marketingProgramHtml,"marketingActionId": globalMarketingActionId,"marketingActions": allProgramActions};
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
                            url: getHost() + '/updateMarketingProgramActions?marketingProgramId='+marketingProgramId,
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(updatemarketingProgramdetails)
                        }).success(function (data, status, headers, config)
                        {  
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'admin/marketingprogramdetails?marketingProgramId='+marketingProgramId, "_self");
                        }).error(function(data, status, headers, config){
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });                         
                    }
        
    };
});