/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
    
       $(document).ready(function () {
           $("#addOrganization").click(function (){
              $("#addOrganizationPopup").show();
              $("#addOrganizationPopupDiv").show();
              $("#addEmailPopupDiv").show();
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
    
    $scope.addOrganization = function () {
                    
                    var organizationName = $("#organizationName").val();
                    var organizationType = $("#organizationType").val();
                    var organization = {"organizationName": organizationName,"organizationTypeId": organizationType};
                   if(organizationName===""){

                       alert(enterOrganizationName);
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
            var deleteOrganization=confirm(deleteOrganizationPrompt);
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
               for ( var i = 0; i <= data.d.channelDetailsList.length; i++) {
                    var obj = data.d.channelDetailsList[i];
                    if(data.d.channelDetailsList[i].channelName === emailChannel){
                        $scope.emailDetails =data.d.channelDetailsList[i].categoryDetailsList;
                    }
                    if(data.d.channelDetailsList[i].channelName === printChannel){
                         $scope.printDetails =data.d.channelDetailsList[i].categoryDetailsList;
                    }
                     if(data.d.channelDetailsList[i].channelName === imageChannel){
                         $scope.imageDetails =data.d.channelDetailsList[i].categoryDetailsList;
                         //alert(JSON.stringify(data.d.channelDetailsList[i].categoryDetailsList));
                    }
                }
            }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });         
    };      
    
    
      $scope.addEmailCategory = function () {
          
            var organizationId=$("#organizationId").val();
            var categoryName = $("#categoryName").val();
            var category ={"categoryName" : categoryName,"channelId":emailChannelId,"orgnizationId":organizationId}
            if(categoryName===""){
                alert(enterCategoryName);
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
                    alert(emailName+"\t"+eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    };
    
    $scope.addPrintCategory = function () {
        
            var organizationId=$("#organizationId").val();
            var printCategory = $("#printCategory").val();
            var category ={"categoryName" : printCategory,"channelId":printChannelId,"orgnizationId":organizationId}
            if(printCategory===""){
                alert(enterCategoryName);
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
                    alert(printName+"\t"+eval(JSON.stringify(data.d.operationStatus.messages)));
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
             alert(enterCategoryName);
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
                    alert(imageName+"\t"+eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    };    
    
    
    $scope.getAllCategoryDetails= function (){
         var categoryId=$("#categoryIdTag").val();
        $http({
                    method: 'GET',
                    url: getHost() + '/getCategoryByCategoryId.do?categoryId='+categoryId,
                }).success(function (data)
                { 
                    $scope.categoryDetails= data.d.categoryDetails;
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
        getAllSubCategories();
    }
    
    $scope.deleteCategory= function (categoryId){
        var organizationId=$("#organizationIdTag").val();
        var deleteCategory=confirm(deleteCategoryPrompt);
            if(deleteCategory===true)
            {
               $http({
                    method : 'GET',
                    url : getHost()+ '/deleteCategory.do?categoryId='+categoryId,
                }).success(function(data, status, headers, config) {
                    $scope.categoryDetails= data.d.categoryDetails;
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                     window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });     
            }
    }
    
    
    
     $scope.getAllSubCategories= function (){
        
        var categoryId = $("#categoryIdTag").val();
        var subCategoryId = $("#subCategoryIdTag").val();
               $http({
                    method : 'GET',
                    url : getHost()+ '/getAllSubCategoriesByCategoryId.do?categoryId='+categoryId,
                }).success(function(data, status, headers, config) {
                    for(var i=0;i<data.d.details.length;i++){
                        if(data.d.details[i].subCategoryId==subCategoryId)
                        {
                            $scope.subCategoryDetailsTitle= data.d.details[i];
                        }   
                    }
                    $scope.subCategoryDetails= data.d.details;    
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
    }
    
    $scope.deleteSubCategory= function (subCategoryId){
        var organizationId=$("#organizationIdTag").val();
        var categoryId=$("#categoryIdTag").val();
        var deleteSubCategory=confirm(deleteSubCategoryPrompt);
            if(deleteSubCategory===true)
            {
               $http({
                    method : 'GET',
                    url : getHost()+ '/deleteSubCategory.do?subCategoryId='+subCategoryId,
                }).success(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    if (window.location.href.indexOf("emailsubcategorydetails.jsp") > -1) {
                         window.open(getHost() + 'adminv2/emailsubcategory.jsp?organizationId='+organizationId+'&categoryId='+categoryId, "_self");
                    }
                    if (window.location.href.indexOf("printsubcategorydetails.jsp") > -1) {
                         window.open(getHost() + 'adminv2/printsubcategory.jsp?organizationId='+organizationId+'&categoryId='+categoryId, "_self");
                    }
                    if (window.location.href.indexOf("imagesubcategorydetails.jsp") > -1) {
                         window.open(getHost() + 'adminv2/imagesubcategory.jsp?organizationId='+organizationId+'&categoryId='+categoryId, "_self");
                    }
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });     
            }
    }
    
    
    $scope.getAllExternalSourceKeywordLookups= function (){
        $http({
                    method : 'GET',
                    url : getHost()+ '/getAllExternalSourceKeywordLookups.do',
                }).success(function(data, status, headers, config) {
                    for(var i=0;i<data.d.details.length;i++)
                    {
                        $scope.ExternalSourceKeywordLookups= data.d.details[i];
                    }
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
    }
    
    
    $scope.addSubCategory = function () {
            var organizationId=$("#organizationIdTag").val();
            var subCategoryName=$("#subCategoryName").val();    
            var categoryId = $("#categoryIdTag").val();
            var externalSourceKeywordLookupId=$("#optionalExternalSource").val();
            var subCategory ={ "subCategoryId" : 0, "subCategoryName" : subCategoryName, "categoryId" : categoryId, "externalSourceKeywordLookupId" : externalSourceKeywordLookupId }
             if(subCategoryName===""){
             alert(enterSubCategoryName);
             $("#subCategoryName").focus();
            }else{
               
            $.ajax({
                    method: 'POST',
                    url: getHost() + '/saveSubCategory.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(subCategory)
                }).success(function (data)
                { 
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    if (window.location.href.indexOf("emailsubcategory.jsp") > -1) {
                         window.open(getHost() + 'adminv2/emailsubcategory.jsp?organizationId='+organizationId+'&categoryId='+categoryId, "_self");
                    }
                    if (window.location.href.indexOf("printsubcategory.jsp") > -1) {
                         window.open(getHost() + 'adminv2/printsubcategory.jsp?organizationId='+organizationId+'&categoryId='+categoryId, "_self");
                    }
                    if (window.location.href.indexOf("imagesubcategory.jsp") > -1) {
                         window.open(getHost() + 'adminv2/imagesubcategory.jsp?organizationId='+organizationId+'&categoryId='+categoryId, "_self");
                    }
                   
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    }; 
   
    $scope.getAllExternalSourceKeywordLookups= function (){
       $http({
                   method : 'GET',
                   url : getHost()+ '/getAllExternalSourceKeywordLookups.do',
               }).success(function(data, status, headers, config) {
                   for(var i=0;i<data.d.details.length;i++)
                   {
                       $scope.ExternalSourceKeywordLookups= data.d.details[i];
                   }
               }).error(function(data, status, headers, config) {
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
               });  
   }
      $scope.getAllEmailBlocks= function (){
           var organizationId=$("#organizationId").val();
  
               $http({
                    method : 'GET',
                    url : getHost()+ '/getAllEmailBlocksByOrganizationId.do?organizationId='+organizationId,
                }).success(function(data, status, headers, config) {                  
                $scope.emailBlocks= data.d.details;
                }).error(function(data, status, headers, config) {
               alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
    }
    
    
    $scope.addEmailBlock = function () {
            var organizationId=$("#organizationIdTag").val();
            var emailBlockName=$("#emailBlockName").val(); 
            var externalSourceKeywordLookupId=$("#optionalExternalSource").val();
            var emailCategory ={ "emailBlockName" : emailBlockName,  "externalSourceKeywordLookupId" : externalSourceKeywordLookupId ,"organizationId":organizationId}
             if(emailBlockName===""){
             alert(enterEmailBlock);
             $("#emailBlockName").focus();
            }else{
               
            $.ajax({
                    method: 'POST',
                    url: getHost() + '/saveEmailBlock.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(emailCategory)
                }).success(function (data)
                { 
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");

                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });                         
            }
    }; 
    
     $scope.getEmailBlock= function (){
         var emailBlockId=$("#emailBlockId").val();
        $http({
                    method: 'GET',
                    url: getHost() + '/getAllEmailBlocksById.do?emailBlockId='+emailBlockId
                }).success(function (data)
                {
                   for(var i=0;i<data.d.details.length;i++)
                   {
                    $scope.emailBlocksTitle= data.d.details[i];
                   } 
                    $scope.emailBlockDetails= data.d.details;
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
        
    }
    
     $scope.deleteEmailBlock= function (){
         var emailBlockId=$("#emailBlockId").val();
        var organizationId=$("#organizationIdTag").val();
        var deleteEmailBlock=confirm(deleteEmailBlockPrompt);
            if(deleteEmailBlock===true)
            {
               $http({
                    method : 'GET',
                    url : getHost()+ '/deleteEmailBlock.do?emailBlockId='+emailBlockId,
                }).success(function(data, status, headers, config) {
                    $scope.getEmailBlock= data.d.details;
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                     window.open(getHost() + 'adminv2/organizationdetails.jsp?organizationId='+organizationId, "_self");
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });     
            }
    }
    
    
    $scope.getEmailModelBySubCategoryId= function (){
         var subCategoryId=$("#subCategoryIdTag").val();
        $http({
                    method: 'GET',
                    url: getHost() + '/getAllEmailModelBySubCategory.do?subCategoryId='+subCategoryId
                }).success(function (data)
                {
                    $scope.emailModels= data.d.details;
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
        
    }
    $scope.getImageModelBySubCategoryId= function (){
         var subCategoryId=$("#subCategoryIdTag").val();
        $http({
                    method: 'GET',
                    url: getHost() + '/getAllImageModelBySubCategory.do?subCategoryId='+subCategoryId
                }).success(function (data)
                {
                    $scope.imageModels= data.d.details;
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
        
    }
    $scope.getPrintModelBySubCategoryId= function (){
         var subCategoryId=$("#subCategoryIdTag").val();
        $http({
                    method: 'GET',
                    url: getHost() + '/getAllPrintModelBySubCategory.do?subCategoryId='+subCategoryId
                }).success(function (data)
                {   
                    $scope.printModels= data.d.details;
//                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
        
    }
    
    $scope.getAllNonAddedEmailModelsBySubCategoryId= function (subCategoryID){
        $http({
                    method: 'GET',
                    url: getHost() + '/getAllNonAddedEmailModels.do?subCategoryId='+subCategoryID
                }).success(function (data)
                {   
                    $("#relateEmailTemplateAddButton").show().css('pointer-events','none');
                    $scope.nonAddedEmailModelsBySubCategory= data.d.details;
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
    }
    
    $scope.getAllNonAddedImageModelsBySubCategoryId= function (subCategoryID){
        $http({
                    method: 'GET',
                    url: getHost() + '/getAllNonAddedImageModels.do?subCategoryId='+subCategoryID
                }).success(function (data)
                {   
                    $("#relateImageTemplateAddButton").show();
                    $scope.nonAddedImageModelsBySubCategory= data.d.details;
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
    }
    $scope.getAllNonAddedPrintModelsBySubCategoryId= function (subCategoryID){
        $http({
                    method: 'GET',
                    url: getHost() + '/getAllNonAddedPrintModels.do?subCategoryId='+subCategoryID
                }).success(function (data)
                {
                    $("#relatePrintTemplateAddButton").show();
                    $scope.nonAddedPrintModelsBySubCategory= data.d.details;
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
    }
    var selectedList="";
    $scope.select= function(item) {
               var selectedItem="";
	       $scope.selected = item; 
               selectedItem=item;
               selectedList=selectedItem;
               $("#relateEmailTemplateAddButton").css('pointer-events','auto');
	};
    $scope.isActive = function(item) {
	       return $scope.selected === item;
	};
        
    $scope.relateEmailTemplateSubCategory= function (){
        var subCategoryId=$("#subCategoryIdTag").val();
        var categoryId=$("#categoryIdTag").val();
        var organizationId=$("#organizationIdTag").val();
        var emailModelId=eval(JSON.stringify(selectedList.emailModelId));
        var emailTemplateRelate ={ "emailModelId" : emailModelId,  "subCategoryId" : parseInt(subCategoryId)}
            
         $.ajax({
                    method: 'POST',
                    url: getHost() + '/saveSubCategoryEmailModel.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(emailTemplateRelate)
                }).success(function (data)
                {   
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                       window.open(getHost() + 'adminv2/emailsubcategorydetails.jsp?organizationId='+organizationId+'&categoryId='+categoryId+'&subCategoryId='+subCategoryId, "_self");
                    
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });   
        
        
    };
    
     $scope.relatePrintTemplateSubCategory= function (){
        var subCategoryId=$("#subCategoryIdTag").val();
        var categoryId=$("#categoryIdTag").val();
        var organizationId=$("#organizationIdTag").val();
        var printModelId=eval(JSON.stringify(selectedList.printModelId));
        var printTemplateRelate ={ "printModelId" : printModelId,  "subCategoryId" : parseInt(subCategoryId)}
            
         $.ajax({
                    method: 'POST',
                    url: getHost() + '/saveSubCategoryPrintModel.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(printTemplateRelate)
                }).success(function (data)
                {   
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                       window.open(getHost() + 'adminv2/printsubcategorydetails.jsp?organizationId='+organizationId+'&categoryId='+categoryId+'&subCategoryId='+subCategoryId, "_self");
                    
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });          
    };
    $scope.deleteSubCategoryEmailModel= function (subCategoryEmailModelId){ 
            var subCategoryId=$("#subCategoryIdTag").val();
            var categoryId=$("#categoryIdTag").val();
            var organizationId=$("#organizationIdTag").val();
            
            var deleteEmailTemplate=confirm(deleteSubCategoryRelationPrompt);
            if(deleteEmailTemplate===true)
            {
                $http({
                    method: 'GET',
                    url: getHost() + '/deleteSubCategoryEmailModel.do?subCategoryEmailModelID='+subCategoryEmailModelId
                }).success(function (data)
                {   
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'adminv2/emailsubcategorydetails.jsp?organizationId='+organizationId+'&categoryId='+categoryId+'&subCategoryId='+subCategoryId, "_self");
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });  
            }
    };
    
    $scope.getAllRecurringByOrganizationId = function (){
        
            var organizationId=$("#organizationIdTag").val();
        $http({
                method: 'GET',
                url: getHost() + '/getAllRecurringByOrganizationId.do?organizationId='+organizationId
            }).success(function (data)
            {   
                $scope.allRecurringEmails=data.d.details;
            }).error(function(data){
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            }); 
    };
    
    $scope.getAllNonRecurringEmail=function (){
        
        $http({
                method: 'GET',
                url: getHost() + '/getAllNonRecurringEmail.do?recurringEmailTemplateId='+recurringEmailTemplateId
            }).success(function (data)
            {   
                alert(JSON.stringify(data.d.details[1]));
                $scope.nonrecurringEmails=data.d.details;
            }).error(function(data){
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            }); 
        
    };
    
    $scope.deleteOrganizationRecurringEmail=function (organizationRecurringEmailLookupId){
            alert(organizationRecurringEmailLookupId);
            var  deleteconfirm=confirm("Yes");
            if(deleteconfirm==true){
            $http({
                method: 'GET',
                url: getHost() + '/deleteOrganizationRecurringEmail.do?organizationRecurringEmailLookupId='+organizationRecurringEmailLookupId
            }).success(function (data)
            {   
                alert(JSON.stringify(data.d.details[0]));
                $scope.deleteRecurringEmails=data.d.details;
            }).error(function(data){
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            }); 
        }
    };
}

