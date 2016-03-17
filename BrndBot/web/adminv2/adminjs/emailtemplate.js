/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
    $(document).ready(function () {   
    if(window.location.href.indexOf("edit=yes") > -1) 
    {
        $("#uploadOnEdit").show();
        $("#nameThisTemplate").hide();
        $("#selectOrgranization").show();
        $("#editTitle").show();
        $("#createTitle").hide();
        $("#createNewTemplate").hide();
         $("#saveTemplate").show();
       
    }
    if(window.location.href.indexOf("edit=no") > -1) 
    {
        $("#uploadOnEdit").show();
        $("#nameThisTemplate").show();
        $("#selectOrgranization").hide();  
        $("#editTitle").hide();
        $("#createTitle").show();
         $("#createNewTemplate").show();
         $("#saveTemplate").hide();
         $("#updateTemplate").hide();
       
    }
});

 function emailTemplateController($scope,$http){
     $scope.emailTemplateModel = function () {
        
                    $http({
                            method : 'GET',
                            url : getHost()+'/getAllEmailModel.do'
                        }).success(function(data, status, headers, config) {
                            alert(JSON.stringify(data.d.details));
                        $scope.emailTemplates = data.d.details;  
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });
       
    };
    
    $scope.addEmailTemplate = function () {
                    
                    var emailModelName = $("#emailModelName").val();
                    var htmlData = $("#edit").froalaEditor('html.get');
                    var imageFileName = $("#imageFileName").val();
                    var modelNames = {"emailModelName": emailModelName,"htmlData": htmlData,"imageFileName":imageFileName};
                    if(emailModelName===""){
                    alert("Please enter Template Name!");
                       $("#emailModelName").focus();
                       return false;
                    }
                     if(imageFileName===""){
                    alert("Please Upload an Image!");
                       $("#imageFileName").focus();
                    }
                    else{
                    $.ajax({
                            method: 'POST',
                            url: getHost() + '/saveEmailModel.do',
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(modelNames)
                        }).success(function (data, status, headers, config)
                        {  
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'adminv2/emailtemplates.jsp', "_self");
                        }).error(function(data, status, headers, config){
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });                         
                    }
    };
    
    
    $scope.editEmailTemplate = function () {
        
                    var emailModelId= $("#emailModelIdTag").val();
                    var emailModelName = $("#emailModelName").val();
                    var htmlData = $("#edit").froalaEditor('html.get');
                    var imageFileName = $("#imageFileName").val();
                    var editEmailTemplate = {"emailModelId":emailModelId, "emailModelName": emailModelName,"htmlData": htmlData,"imageFileName":imageFileName};
                    
                    $.ajax({
                            method: 'POST',
                            url: getHost() + '/editEmailModel.do',
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(editEmailTemplate)
                        }).success(function (data, status, headers, config)
                        {  
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));                       
                            window.open(getHost() + 'adminv2/emailtemplates.jsp', "_self");
                        }).error(function(data, status, headers, config){
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        }); 
    };
     
     
 }
       

