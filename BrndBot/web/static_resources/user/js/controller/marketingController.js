
marketingFlowApp.controller("marketingController", ['$scope', '$location', 'marketingFactory', 'companyMarketingProgramFactory',function ($scope,$location, marketingFactory, companyMarketingProgramFactory) { 
      
        $scope.marketingCategoryId="";
        $scope.marketingProgramId="";
        $scope.redirect= function(pageName,marketingCategoryId)
        {   
                 
            $scope.marketingCategoryId=marketingCategoryId;
            $location.path("/"+pageName);         
            

        };
         $scope.redirectProgram= function(pageName,marketingCategoryId,marketingProgramId,htmlData)
        {   
          
            $scope.marketingCategoryId=marketingCategoryId;
            $scope.marketingProgramId=marketingProgramId;
            $scope.htmlData=htmlData;
            $location.path("/"+pageName);         
            

        };
        
        $scope.getAllMarketingPrograms = function(forward){
            
            marketingFactory.companyMarketingCategoriesGet().then(function (data) {
                $scope.header="Please choose a marketing program type.";
                $scope.pageName="marketing";              
              
                $scope.forward=forward;
                $scope.marketingCategories = data.d.details;
            });

        };    
        
         $scope.displayMarketingProgramByCategoryId = function(forward){
                marketingFactory.marketingProgramsGet($scope.marketingCategoryId).then(function (data) {
                $scope.pageName="marketingPrograms";
                $scope.forward=forward;
                $scope.displayAllMarketingPrograms=data.d.details;
                $scope.header="Select a Category";
  
            });

        };    
        
         $scope.saveMarketingProgram = function(programName,programUrl,programUrlName,programDateTime){
             programDateTime="2015-2-12"
            
              var data = {"program_name": programName, 
                                           "program_date_time": programDateTime,
                                           "program_url":programUrl,
                                           "program_url_name":programUrlName,
                                           "marketing_category_id":$scope.marketingCategoryId.toString(),
                                           "marketing_program_id":$scope.marketingProgramId.toString()
                                          };
                               
                companyMarketingProgramFactory.setMarketingProgramPost(data).then(function (data) {
                 // alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                 alert(data.d.operationStatus.messages);
});
        };    
        
}]);
