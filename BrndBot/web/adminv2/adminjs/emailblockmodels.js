/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

function emailBlocksController($scope,$http){
    $scope.emailBlocksModel =function(){
        
        $http({
                            method : 'GET',
                            url : getHost()+'/getAllEmailBlockModel.do'
                        }).success(function(data, status, headers, config) {
                        $scope.emailBlocksModelData = data.d.details;  
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });     
        
    };
    
    
    $scope.addEmailBlock = function () {
                    
                    var emailModelName = $("#emailModelName").val();
                    var htmlData = $("#edit").froalaEditor('html.get');
                    var imageFileName = $("#imageFileName").val();
        
                    var emaiModelNames = {"emailBlockModelName":emailModelName,"htmlData": htmlData,"imageFileName":imageFileName};
                    if(emailModelName===""){
                    alert(enterBlockName);
                       $("#emailBlockModelName").focus();
                       return false;
                    }
                     if(imageFileName===""){
                    alert(uploadImageFile);
                       $("#imageFileName").focus();
                    }
                    else{
                    $http({
                            method: 'POST',
                            url: getHost() + '/saveBlockModel.do',
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(emaiModelNames)
                        }).success(function (data, status, headers, config)
                        {  
         
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'adminv2/emailblockmodels.jsp', "_self");
                        }).error(function(data, status, headers, config){
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });                         
                    }
    };
    
    
    $scope.deleteEmailBlocks= function (emailBlockModelId){
       var emailBlockIdTag= $("#emailBlockIdTag").val();
        var deleteEmailBlocks=confirm(deleteEmailBlockPrompt);
            if(deleteEmailBlocks===true)
            {
               $http({
                    method : 'GET',
                    url : getHost()+ '/deleteBlockModel.do?emailBlockModelId='+emailBlockIdTag,
                }).success(function(data, status, headers, config) {
                     $scope.getEmailModelById= data.d.details;                     
                     alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                     window.open(getHost() + 'adminv2/emailblockmodels.jsp',"_self");
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });     
            }
    }
    
    
     $scope.getEmailModelById = function () {
                    var emailBlockIdTag= $("#emailBlockIdTag").val();
                    $http({
                            method : 'GET',
                            url : getHost()+'/getEmailBlockModelById.do?emailBlockModelId='+emailBlockIdTag
                        }).success(function(data, status, headers, config) {
                            $('.fr-element').html(eval(JSON.stringify(data.d.details[0].htmlData)));
                        $scope.getEmailModels = data.d.details[0];  
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });      
    };
    
    $scope.updateEmailBlockModel = function () {
        
                    var emailBlockIdTag= $("#emailBlockIdTag").val();
                    var emailBlockModelName= $("#emailModelNameTag").val();
                    var htmlData= $("#edit").froalaEditor('html.get');
                    var imageFileName= $("#imageFileName").val();
                    
                    var emailBlockModel = {"emailBlockModelId":emailBlockIdTag,"emailBlockModelName": emailBlockModelName,
                                           "htmlData":htmlData,"imageFileName":imageFileName};
                    $http({
                            method : 'POST',
                            url : getHost()+'/updateEmailBlockModel.do',
                            contentType: "application/json",
                            data: JSON.stringify(emailBlockModel)
                        }).success(function(data, status, headers, config) {                            
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'adminv2/emailblockmodels.jsp', "_self"); 
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });      
    };
    
}


