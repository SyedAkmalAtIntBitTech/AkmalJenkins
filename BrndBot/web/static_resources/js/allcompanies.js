/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

       /*comment Andy Test 2 for the comment*/
       

function allCompaniesController($scope,$http){

    $scope.companyName = null;
    $scope.companyId = null;
    $scope.organizationName = null;
    
  $scope.allCompanyDetails = function (){
      
        $http({
             method: 'GET',
             url : getHost()+'getAllCompanies.do'            
        }).success(function(data, status, headers, config){
            
            $scope.company = data.d.details;            
        }).error(function(data, status, headers, config){
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));          
        });     
      
  };   
  
  $scope.companyDetailsByID = function(){
    var qs = (function(a) {
        if (a == "") return {};
        var b = {};
        for (var i = 0; i < a.length; ++i)
        {
            var p=a[i].split('=', 2);
            if (p.length == 1)
                b[p[0]] = "";
            else
                b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
        }
        return b;
    })(window.location.search.substr(1).split('&'));
    
    $scope.companyName = qs["companyName"];    // 123
    $scope.companyId = qs["companyId"];     // query string
    $scope.organizationName = qs["organizationName"]; 
    
  };

  $scope.companyEmailcategories= function (){
      
            var companyId=$scope.companyId;
            $http({
                method : 'GET',
                url : getHost()+'getCompanyDetailsById.do?companyId='+companyId
            }).success(function(data, status, headers, config) {
                $scope.emailCategoryNameList=[];
                $scope.printCategoryNameList=[];
                $scope.imageCategoryNameList=[];
                $scope.channelList=[];
               for ( var i = 0; i <= data.d.details.length; i++) {
                   
                   for(var j=0;j<data.d.details[i].channelDetailsList.length;j++){
                       $scope.channelList[j]=data.d.details[i].channelDetailsList[j];
                    
                      
                        for(var k=0;k<data.d.details[i].channelDetailsList[j].categoryDetailsList.length;k++)
                       {
                         if($scope.channelList[j].channelName === emailChannel){
           
                           $scope.emailCategoryNameList[k]=data.d.details[i].channelDetailsList[j].categoryDetailsList[k];
                       }
                        if($scope.channelList[j].channelName === printChannel){
                           
                             $scope.printCategoryNameList[k]=data.d.details[i].channelDetailsList[j].categoryDetailsList[k];
                        }
                        
                         if($scope.channelList[j].channelName === imageChannel){
                             
                             
                              $scope.imageCategoryNameList[k]=data.d.details[i].channelDetailsList[j].categoryDetailsList[k];
                       
                        
                       
                       }
                        
      
                       }
                   }
                   
                }
                
            }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });         
    };      
  
  
    
    $scope.emailBlockList= function (){       
            var companyId=$scope.companyId;
            $http({
                method : 'GET',
                url : getHost()+'getCompanyDetailsById.do?companyId='+companyId
            }).success(function(data, status, headers, config) {
               for ( var i = 0; i <= data.d.details.length; i++) {
                   for(var j=0;j<data.d.details[i].emailBlockDetailsList.length;j++){
                       $scope.emailBlocks=data.d.details[i].emailBlockDetailsList;
                       $scope.organizationNames=data.d.details[i];
                        $scope.organizationId=data.d.details[i];
                   }
                   
                }
                
            }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });         
    };      
    
    
    $scope.marketingCategory= function (){       
            var companyId=$scope.companyId;
            $http({
                method : 'GET',
                url : getHost()+'getCompanyDetailsById.do?companyId='+companyId
            }).success(function(data, status, headers, config) {
               
               for ( var i = 0; i <= data.d.details.length; i++) {
                 
                   for(var j=0;j<data.d.details[i].marketingCategoryDetailsList.length;j++){
                       $scope.marketingCategoryLists=data.d.details[i].marketingCategoryDetailsList;
                       $scope.organizationNames=data.d.details[i];
                  
                   }
                   
                }
                
            }).error(function(data, status, headers, config) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            });         
    };   
    
    
  
    
    $scope.groupsDisplay = function () {
       var companyId=$scope.companyId;
       $http({
               method : 'GET',
               url : getHost()+'getNonAddedGroups.do?companyId='+companyId
           }).success(function(data, status, headers, config) {

               $scope.groupDetail = data.d.details;  
               if(JSON.stringify($scope.groupDetail)=="null"){
                   $("#noGroup").show();
                   $("#noGroupMessage").empty().append(eval(JSON.stringify(data.d.operationStatus.messages)));
               }
           }).error(function(data, status, headers, config) {
                   alert(eval(JSON.stringify(data.d.operationStatus.messages)));
           });
    };
    
    
    var selectedListItems="";
    $scope.selectedItemsList= function(item) {
               var selectedItemList="";
	       $scope.selected = item; 
               selectedItemList=item;
            selectedListItems=selectedItemList;
            var sel =selectedListItems;
           
               $("#addGroupDetails").css('pointer-events','auto');
	};
        $scope.isActiveMode = function(item) {
	       return $scope.selected === item;
	};
        
          $scope.updateCompanyOrganization=function (){
            var organizationId=$("#organizationId").val();
            var companyId=$scope.companyId;
            var organizationName=$("#organizationName").val();
            var updateorganization = {"organizationId":organizationId,"companyId": companyId};
             $http({
                    method : 'POST',
                    url : getHost()+'saveGroup.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(updateorganization)
                }).success(function(data, status, headers, config) { 
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'admin/companydetails?companyId='+companyId, "_self");
                      
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });   
        };
        
        $scope.addGroupTemplate= function (){
        var companyId=$scope.companyId;      
        var organizationName=$("#organizationName").val();     
        var companyName=$("#companyName").text();       
        var organizationId=eval(JSON.stringify(selectedListItems.organizationId));
        var groupAddTemplate ={"companyId" : companyId, "organizationId" : organizationId}
         $.ajax({
                    method: 'POST',
                    url: getHost() + 'saveGroup.do',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(groupAddTemplate)
                }).success(function (data)
                {   
                       alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                       window.open(getHost() + 'admin/companydetails?companyId='+companyId+'&organizationName='+organizationName+'&companyName='+companyName,"_self");
                    
                }).error(function(data){
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });   
        
        
    };
    
     $scope.deleteGroup= function (){  
         var companyId=$scope.companyId;     
         var organizationName=$("#organizationName").val();     
         var companyName=$("#companyName").text();    
         var organizationId=$("#organizationId").val();
         var organizationCompanyLookupId =$("#organizationCompanyLookupId").val();
         var deleteList=confirm(deleteCompanyGroup);
            if(deleteList===true)
            {
               $http({
                    method : 'GET',
                    url : getHost()+ 'deleteGroup.do?organizationCompanyLookupId='+organizationCompanyLookupId,
                }).success(function(data, status, headers, config) {
                    $scope.groupDetails= data.d.details;
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                     window.open(getHost() + 'admin/companydetails?companyId='+companyId+'&organizationName='+organizationName+'&companyName='+companyName,"_self");
                }).error(function(data, status, headers, config) {
                        alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });     
            }
    }
    
    
        
    $scope.userGroups = function () {
    var companyId=$scope.companyId;

    $http({
            method : 'GET',
            url : getHost()+'getCompanyDetailsById.do?companyId='+companyId
        }).success(function(data, status, headers, config) {
            for ( var i = 0; i <= data.d.details.length; i++) {

                $scope.groupName = data.d.details[i].groupDetails;

            }
        }).error(function(data, status, headers, config) {
                alert(eval(JSON.stringify(data.d.operationStatus.messages)));
        });
    };

}
