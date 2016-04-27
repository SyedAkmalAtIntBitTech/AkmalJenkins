/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
 $(document).ready(function () {   
    if(window.location.href.indexOf("edit=yes") > -1) 
    {
        $("#editEmailTemplate").show();
        $("#createEmailTemplate").hide();
        $("#deleteTemplate").show();
        $("#createTemplate").hide();
        $("#editTemplate").show();
        $("#addHtmlData").hide();
        $("#editHtmlData").show();
        $("#createNewEmailTemplate").hide();
        $("#saveEmailTemplate").show();
       
    }
    if(window.location.href.indexOf("edit=no") > -1) 
    {
        $("#editEmailTemplate").hide();
        $("#createEmailTemplate").show();
        $("#deleteTemplate").hide();
        $("#createTemplate").show();
        $("#editTemplate").hide();
        $("#addHtmlData").show();
        $("#editHtmlData").hide();
        $("#createNewEmailTemplate").show();
         $("#saveEmailTemplate").hide();
       
    }
});

function recurringEmail($scope,$http) {
           
    $scope.recurring = function () {
                    $http({
                            method : 'GET',
                            url : getHost()+'/getAllRecurringEmails.do'
                        }).success(function(data, status, headers, config) {
                            $scope.recurringEmailDetail = data.d.details;  
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });
       
    };
    
     $scope.createEmailTemplate= function (){
        var htmlData=$("#htmlData").val();
        var emailTemplateName=$("#emailTemplateName").val();
        var templateData ={ "templateName" : emailTemplateName,  "htmlData" : htmlData};
        if(emailTemplateName==="")
        {
           alert(recurringEmailName);
           $("#emailTemplateName").focus();
           return false;
        }
         $.ajax({
                    method: 'POST',
                    url: getHost() + '/saveRecurringEmail.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(templateData)
                }).success(function (data)
                {  
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                       window.open(getHost() + 'admin/recurringemails', "_self");
                    
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });          
    };
    
     $scope.getRecurringTemplate = function () {
     var recurringEmailTemplateId=$("#recurringEmailTemplateId").val();
                    $http({
                            method : 'GET',
                            url : getHost()+'/getRecurringEmailTemplateById.do?recurringEmailTemplateId='+recurringEmailTemplateId
                        }).success(function(data, status, headers, config) {
                         
                            $scope.getRecurringTemplateDetails = data.d.details[0];  
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });
       
    };
    
    
     $scope.saveEmailTemplate= function (){
        var recurringEmailTemplateId=eval($("#recurringEmailTemplateId").val());
        var htmlData=$("#htmlDataEdit").val();
        var emailTemplateName=$("#editEmailTemplateName").val();
        var templateData ={"recurringEmailTemplateId":recurringEmailTemplateId, "templateName" : emailTemplateName,  "htmlData" : htmlData};
         $.ajax({
                    method: 'POST',
                    url: getHost() + '/updateRecurringEmail.do?recurringEmailTemplateId='+recurringEmailTemplateId,
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(templateData)
                }).success(function (data)
                {  
                    //alert(JSON.stringify(data));
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                       window.open(getHost() + 'admin/recurringemails', "_self");
                    
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });          
    };
    
     $scope.deleteRecurringEmail = function () {
     var recurringEmailTemplateId=$("#recurringEmailTemplateId").val();
      var deleteEmailTemplates=confirm(deleteEmailTemplate);
            if(deleteEmailTemplates===true)
            {
                    $http({
                            method : 'GET',
                            url : getHost()+'/deleteRecurringEmail.do?recurringEmailTemplateId='+recurringEmailTemplateId
                        }).success(function(data, status, headers, config) {
                            $scope.getRecurringTemplateDetails = data.d.details;  
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                            window.open(getHost() + 'admin/recurringemails', "_self");
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });
            }
    };
    
}   