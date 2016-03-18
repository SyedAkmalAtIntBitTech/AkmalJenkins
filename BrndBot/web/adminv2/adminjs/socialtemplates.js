/* 
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

function printTemplates($scope,$http) {
    
    $scope.printTemplateScope =function(){
        
        $http({
            
            method : 'GET',
            url   :getHost()+'/getEmailTemplates.do'
        }).success(function(data , status, headers, config){
            $scope.printDetails =data.d.details;
            
        }).error(function(data , status, headers, config){
            alert(eval(JSON.stringify(data.d.operationStatus.messages)));
            
        })
        
    }


}