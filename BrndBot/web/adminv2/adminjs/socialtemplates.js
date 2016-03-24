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
    
    if(window.location.href.indexOf("imageTemplates=true") > -1) 
    {
        $("#printTemplatesDiv").hide();
        $("#imageTemplatesDiv").show();
    }
    if(window.location.href.indexOf("printTemplates=true") > -1) 
    {
        $("#imageTemplatesDiv").hide();
        $("#printTemplatesDiv").show();
    }
    
    
});
function socialTemplates($scope,$http) {
    $scope.printTemplateScope =function(){
        $http({
            method : 'GET',
            url    :getHost()+'/getAllPrintModel.do'
        }).success(function(data , status, headers, config){
            $scope.printDisplay = data.d.details;  
        }).error(function(data , status, headers, config){ 
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            
        }); 
    
    };
    $scope.imageTemplateScope =function(){
        $http({
            method : 'GET',
            url    :getHost()+'/getAllImageModel.do'
        }).success(function(data , status, headers, config){
            $scope.imageDisplay = data.d.details;         
        }).error(function(data , status, headers, config){ 
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            
        });
    };

$scope.addPrintTemplate = function () {
            var printModelName=$("#saveprintModelName").val();    
            var printImageName =$("#printImageName").val();
//            var modelFileName = $().val();  ///Need to add these data.
//            var layoutFileName= $().val();
            var printModels ={ "printModelName" : printModelName ,"imageFileName":printImageName,
//                                "modelFileName":modelFileName,"layoutFileName":layoutFileName
                            }
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
                    window.open(getHost() + 'adminv2/addprinttemplate.jsp?edit=no', "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    }; 
    
    $scope.getAllSettings= function (){
        $http({
                method : 'GET',
                url : getHost()+'/getAllExternalSourceKeywordLookups.do'
             }).success(function(data, status, headers, config) {
            $scope.externalSourceKeyWord = data.d.details[0];  
            }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
    });      

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