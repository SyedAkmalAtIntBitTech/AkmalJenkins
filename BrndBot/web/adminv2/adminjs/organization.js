/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
       $(document).ready(function () {
           $("#addOrganization").click(function (){
              $("#addOrganizationPopup").show();
              $("#addOrganizationPopupDiv").show();
           });
           

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
    
    
    $scope.emailcategories= function (){
        
            var organizationId=$("#organizationId").val();

            $http({
                method : 'GET',
                url : getHost()+'/getAllOrganizationCategoryByOrganizationId.do?organizationId='+organizationId
            }).success(function(data, status, headers, config) {
                $scope.emailDetails1 = data.d.channelDetailsList.categoryDetailsList;

                for ( var i = 0; i <= data.d.channelDetailsList[1].categoryDetailsList.length; i++) {

                    var obj = data.d.channelDetailsList[i];
                    if(data.d.channelDetailsList[i].channelName === emailChannel){
                        $scope.emailDetails =data.d.channelDetailsList[i].categoryDetailsList;
                      //  alert(JSON.stringify(data));
                    }
                    if(data.d.channelDetailsList[i].channelName === printChannel){
                         $scope.printDetails =data.d.channelDetailsList[i].categoryDetailsList;
                    }
                     if(data.d.channelDetailsList[i].channelName === imageChannel){
                         $scope.imageDetails =data.d.channelDetailsList[i].categoryDetailsList;
                         //alert(JSON.stringify(data));
                    }
                }     
            }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });         
    };      
    
    
      $scope.addEmailCategory = function () {
          
            var organizationId=$("#organizationId").val();
            var categoryName = $("#categoryName").val();
            var category ={"categoryName" : categoryName,"channelId":printChannelId,"orgnizationId":organizationId}
            if(categoryName===""){
                alert("Please enter category name.");
                $("#categoryName").focus();
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
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    };
    
    $scope.addPrintCategory = function () {
        
            var organizationId=$("#organizationId").val();
            var printCategory = $("#printCategory").val();
            var category ={"categoryName" : printCategory,"channelId":emailChannelId,"orgnizationId":organizationId}
            if(printCategory===""){
                alert("Please enter category name.");
                $("#printCategory").focus();
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
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    }; 
   
$scope.addImageCategory = function () {
    
        
            var organizationId=$("#organizationId").val();
            var imageCategory = $("#imageCategory").val();
            var imageCategory ={"categoryName" : imageCategory,"channelId":imageChannelId,"orgnizationId":organizationId}
             if(imageCategory===""){
             alert("Please enter category name.");
             $("#imagecategory").focus();
            }else{
               
            $.ajax({
                    method: 'POST',
                    url: getHost() + '/saveCategory.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(imageCategory)
                }).success(function (data)
                { 
                    alert("Image\t"+eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    };     
    
    
}

