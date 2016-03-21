/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
/* global printModelId */

$(document).ready(function () {   
    if(window.location.href.indexOf("edit=yes") > -1) 
    {
        $("#editTemplate").show();
        $("#organizationNameDiv").hide();
       $("#nameTemplate").show();
       $("#viewTemplate").hide();
       $("#createPrintTemplate").hide();
    }
    if(window.location.href.indexOf("edit=no") > -1) 
    {
        $("#editTemplate").hide();
        $("#nameTemplate").hide();
        $("#savePrintTemplate").hide();
    }
});
function printTemplates($scope,$http) {
    $scope.printTemplateScope =function(){
        $http({
            method : 'GET',
            url    :getHost()+'/getAllPrintModel.do'
        }).success(function(data , status, headers, config){
            $scope.printDisplay = data.d.details;         
        }).error(function(data , status, headers, config){ 
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            
        });
        
    }

$scope.addPrintTemplate = function () {
            var printModelName=$("#printModelName").val();    
            var printImageName =$("#printImageName").val();
            var printModels ={ "printModelName" : printModelName ,"imageFileName":printImageName}
             if(printModelName===""){
             alert("Please enter Print Model Name.");
             $("#printModelName").focus();
            }else{
               
            $.ajax({
                    method: 'POST',
                    url: getHost() + '/savePrintModel.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(printModels)
                }).success(function (data)
                { 
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/printtemplates.jsp?', "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    }; 
    
    
     $scope.getPrintModelById = function () {
                    var printModelIdTag= $("#printModelIdTag").val();
                    $http({
                            method : 'GET',
                            url : getHost()+'/getPrintModelById.do?printModelId='+printModelIdTag
                        }).success(function(data, status, headers, config) {
                        $scope.printModel = data.d.details[0];  
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });      
    };
    
    
}