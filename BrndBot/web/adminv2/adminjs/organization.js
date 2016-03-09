/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

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
                    
                    var orgname = $("#orgname").val();
                    var orgtype = $("#orgtype").val();
                    var organization = {"organizationName": orgname,"organizationTypeId": orgtype};
                   if(orgname===""){
                       alert("Please Enter Organization Name!");
                       $("#orgname").focus();
                   }else{
                    $.ajax({
                            method: 'POST',
                            url: getHost() + '/saveOrganization.do',
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(organization)
                        }).success(function (data, status, headers, config)
                        {  
                            var messsage = eval(JSON.stringify(data.d.operationStatus.messages)); //eval() to get string without "" quotes
                            alert(messsage);
                            window.open(getHost() + 'adminv2/organization.jsp', "_self");
                        }).error(function(data, status, headers, config){
                            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                        });                         
                    }
    };
    
    
    
    
    
    $scope.organizationdetails= function (){
        
        
        var orgid=$("#orgidtag").val();
        $http({
                method : 'GET',
                url : getHost()+'/getOrganizationById.do?organizationId='+orgid
            }).success(function(data, status, headers, config) {
                var orgtypeid=JSON.stringify(data.d.details[0].organizationTypeId);
                $("#orgdetailstype > [value=" +orgtypeid+ "]").attr("selected", "true");
                $scope.organizationDetails = data.d.details[0];
            }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });    
        
        
        $scope.deleteOrganization=function (orgId){
            var delorg=confirm("Do you want to delete this Organization?");
            if(delorg===true)
            {
               $http({
                    method : 'GET',
                    url : getHost()+'/deleteOrganization.do?organizationId='+orgId
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
            var orgId=$("#orgidtag").val();
            var orgname=$("#orgnamediv").text();
            var orgtypeId=$("#orgdetailstype").val();
            var updateorg = {
                             "organizationId": orgId,
                             "organizationName": orgname,
                             "organizationTypeId": orgtypeId
                            };
             $http({
                    method : 'POST',
                    url : getHost()+'/updateOrganization.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: updateorg
                }).success(function(data, status, headers, config) { 
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?orgId='+orgId, "_self");
                      
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });   
        }
        
        
        
        
    };
       
}