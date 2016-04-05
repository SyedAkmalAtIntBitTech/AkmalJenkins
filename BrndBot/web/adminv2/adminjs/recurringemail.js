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
                            url : getHost()+'/getAllRecurringByOrganizationId.do?organizationId=1'
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
           alert(RecurringEmailName);
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
                       window.open(getHost() + 'adminv2/recurringemails.jsp', "_self");
                    
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });          
    };
    
}   