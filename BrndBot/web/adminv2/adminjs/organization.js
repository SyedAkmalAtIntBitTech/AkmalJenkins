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
        
            var orgID=$("#orgid").val();

            $http({
                method : 'GET',
                url : getHost()+'/getAllOrganizationCategoryByOrganizationId.do?organizationId='+orgID
            }).success(function(data, status, headers, config) {
                $scope.emailDetails1 = data.d.channelDetailsList.categoryDetailsList;

                for ( var i = 0; i <= data.d.channelDetailsList[1].categoryDetailsList.length; i++) {

                    var obj = data.d.channelDetailsList[i];
                    if(data.d.channelDetailsList[i].channelName === "email"){
                        $scope.emailDetails =data.d.channelDetailsList[i].categoryDetailsList;
                      //  alert(JSON.stringify(data));
                    }
                    if(data.d.channelDetailsList[i].channelName === "print"){
                         $scope.printDetails =data.d.channelDetailsList[i].categoryDetailsList;
                    }
                     if(data.d.channelDetailsList[i].channelName === "image"){
                         $scope.imageDetails =data.d.channelDetailsList[i].categoryDetailsList;
                         //alert(JSON.stringify(data));
                    }
                }     
            }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });         
    };      
    
    
      $scope.addEmailCategory = function () {
          
            var orgID=$("#orgid").val();
            var catname = $("#catname").val();
            var category ={"categoryName" : catname,"channelId":2,"orgnizationId":orgID}
            if(catname===""){
                alert("Please enter category name.");
                $("#catname").focus();
            }else{
            $.ajax({
                    method: 'POST',
                    url: getHost() + '/saveCategory.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(category)
                }).success(function (data)
                { 
                    alert("Email\t"+eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?orgId='+orgID, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    };
    
    $scope.addPrintCategory = function () {
        
            var orgID=$("#orgid").val();
            var catprintname = $("#catprintname").val();
            var category ={"categoryName" : catprintname,"channelId":1,"orgnizationId":orgID}
            if(catprintname===""){
                alert("Please enter category name.");
                $("#catprintname").focus();
            }else{
            $.ajax({
                    method: 'POST',
                    url: getHost() + '/saveCategory.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(category)
                }).success(function (data)
                { 
                    alert("Print\t"+eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?orgId='+orgID, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    }; 
   
$scope.addImageCategory = function () {
    
        
            var orgID=$("#orgid").val();
            var catimagename = $("#imagecategory").val();
            var imagecat ={"categoryName" : catimagename,"channelId":4,"orgnizationId":orgID}
             if(catimagename===""){
             alert("Please enter category name.");
             $("#imagecategory").focus();
            }else{
               
            $.ajax({
                    method: 'POST',
                    url: getHost() + '/saveCategory.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(imagecat)
                }).success(function (data)
                { 
                    alert("Image\t"+eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?orgId='+orgID, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    };     
    
    
}

