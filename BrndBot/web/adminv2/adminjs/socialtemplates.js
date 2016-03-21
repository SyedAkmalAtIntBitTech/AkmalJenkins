/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

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
}