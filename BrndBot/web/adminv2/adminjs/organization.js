/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
       $(document).ready(function () {
           $("#addorganization").click(function (){
              $("#addOrganizationPopup").show();
              $("#addOrganizationPopupDiv").show();
           });
           
//            $("#createorg").click(function (){
//              $("#addorganizationpopup").hide();
//           });
           $("#addOrganizationPopupDiv").click(function (){
               $("#addOrganizationPopup").hide();
                $("#addOrganizationPopupDiv").hide();
           });
       });
function organizationcontroller($scope,$http) {
    
    $scope.organization = function () {
        
                    $http({
                            method : 'GET',
                            url : getHost()+'/getAllOrganizations.do'
                        }).success(function(data, status, headers, config) {
                            $scope.organizationDetails = data.d.details;  
                        }).error(function(data, status, headers, config) {
                                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });
       
    };
    
    $scope.addorganization = function () {
                    
                    var organizationName = $("#organizationName").val();
                    var organizationType = $("#organizationType").val();
                    var organization = {"organizationName": organizationName,"organizationTypeId": organizationType};
                   if(organizationName===""){
                       alert("Please enter Organization Name!");
                       $("#organizationName").focus();
                   }else{
                    $.ajax({
                            method: 'POST',
                            url: getHost() + '/saveOrganization.do',
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(organization)
                        }).success(function (data, status, headers, config)
                        {  
                            alert(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
                            window.open(getHost() + 'adminv2/organization.jsp', "_self");
                        }).error(function(data, status, headers, config){
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });                         
                    }
    };
    
    
    
    
    
    $scope.organizationdetails= function (){
        
        
        var organizationId=$("#organizationIdTag").val();
        $http({
                method : 'GET',
                url : getHost()+'/getOrganizationById.do?organizationId='+organizationId
            }).success(function(data, status, headers, config) {
                var organizationTypeId=JSON.stringify(data.d.details[0].organizationTypeId);
                $("#organizationDetailsTypeId > [value=" +organizationTypeId+ "]").attr("selected", "true");
                $scope.organizationDetails = data.d.details[0];
            }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });    
        
        
        $scope.deleteOrganization=function (organizationId){
            var deleteOrganization=confirm("Do you want to delete this Organization?");
            if(deleteOrganization===true)
            {
               $http({
                    method : 'GET',
                    url : getHost()+'/deleteOrganization.do?organizationId='+organizationId
                }).success(function(data, status, headers, config) {
                    $scope.organizationDetails = data.d.details;
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organization.jsp', "_self");
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });     
            }
        }
        
        
        $scope.updateOrganization=function (){
            var organizationId=$("#organizationIdTag").val();
            var organizationName=$("#organizationNameDiv").text();
            var organizationTypeId=$("#organizationDetailsTypeId").val();
            var updateorg = {
                             "organizationId": organizationId,
                             "organizationName": organizationName,
                             "organizationTypeId": organizationTypeId
                            };
             $http({
                    method : 'POST',
                    url : getHost()+'/updateOrganization.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: updateorg
                }).success(function(data, status, headers, config) { 
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");
                      
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });   
        }
        
        
        
        
    };
       
}