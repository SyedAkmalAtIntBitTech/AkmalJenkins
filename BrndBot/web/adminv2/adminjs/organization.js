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
                                alert(nodataerror);
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
                        }).success(function (data)
                        { 
                            alert("Organization saved successfully.");
                            window.open(getHost() + 'adminv2/organization.jsp', "_self");

                        }).error(function(e){
                            alert(JSON.stringify(e));
                        });                         
                    }
    };
    
    
    
    
    
    $scope.organizationdetails= function (){
        $scope.deleteOrganization=function (orgId){
            $http({
                            method : 'GET',
                            url : getHost()+'/deleteOrganization.do'
                        }).success(function(data, status, headers, config) {
                            $scope.organizationDetails = data.d.details;    
                        }).error(function(data, status, headers, config) {
                                alert(nodataerror);
                        });
            alert(orgId);
        }
    };
    
    
    $scope.emailcategories= function (){
                        $http({
                            method : 'GET',
                            url : getHost()+'/getAllOrganizationCategoryByOrganizationId.do?organizationId=1'
                        }).success(function(data, status, headers, config) { 
                            $scope.emailDetails1 = data.d.channelDetailsList.categoryDetailsList;
                            for ( var i = 0; i < data.d.channelDetailsList.length; i++) {
                                var obj = data.d.channelDetailsList[i];
                                if(data.d.channelDetailsList[i].channelName === "email"){
                                  alert(JSON.stringify(data.d.channelDetailsList[i].categoryDetailsList));
                                     $scope.emailDetails =data.d.channelDetailsList[i].categoryDetailsList;
                                }
                                if(data.d.channelDetailsList[i].channelName === "print"){
                                    // alert(JSON.stringify(data.d.channelDetailsList[i].categoryDetailsList));
                                     $scope.printDetails =data.d.channelDetailsList[i].categoryDetailsList;
                                }
//                                $scope.emailDetails =obj;
//                                alert(JSON.stringify(obj));
                            
                            }
//                            $scope.emailDetails1=data.d.channelDetailsList[0].categoryDetailsList[0];
////                            var emaildets=JSON.stringify(data.d.channelDetailsList[0].categoryDetailsList[0]);
//                            alert(JSON.stringify(data.d.channelDetailsList[0].categoryDetailsList[0].categoryName));
                            
                        }).error(function(data, status, headers, config) {
                                alert(JSON.stringify(data));
                        });         
    };      
    
    
      $scope.addEmailCategory = function () {
          alert();
                    
                    var orgname = $("#catname").val();
                    
                    var organization = {"organizationName": catname,"organizationTypeId": orgtype};
                   if(orgname===""){
                       alert("Please Enter Category Name!");
                       $("#catname").focus();
                   }else{
                    $.ajax({
                            method: 'POST',
                            url: getHost() + '/saveCategory.do',
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(organization)
                        }).success(function (data)
                        { 
                            alert("Category saved successfully.");
                            window.open(getHost() + 'adminv2/organization.jsp', "_self");

                        }).error(function(e){
                            alert(JSON.stringify(e));
                        });                         
                    }
    };
    
    
    
    
    
}