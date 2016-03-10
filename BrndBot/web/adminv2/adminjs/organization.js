
/* global emailChannel, printChannel, imageChannel */

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
                    
                    var organizationName = $("#organizationName").val();
                    var organizationType = $("#organizationType").val();
                    var organization = {"organizationName": organizationName,"organizationTypeId": organizationType};
                   if(organizationName===""){
                       alert("Please Enter Organization Name!");
                       $("#organizationName").focus();
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
        $scope.deleteOrganization=function (organizationID){
            $http({
                            method : 'GET',
                            url : getHost()+'/deleteOrganization.do'
                        }).success(function(data, status, headers, config) {
                            $scope.organizationDetails = data.d.details;    
                        }).error(function(data, status, headers, config) {
                                alert(nodataerror);
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
                $("#categoryname").focus();
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
            var imagecategory = $("#imagecategory").val();
            var imagecat ={"categoryName" : imagecategory,"channelId":imageChannelId,"orgnizationId":organizationId}
             if(imagecategory===""){
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
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    };     
    
    
}

