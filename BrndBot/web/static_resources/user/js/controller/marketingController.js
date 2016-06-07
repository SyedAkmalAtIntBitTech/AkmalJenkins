
marketingFlowApp.controller("marketingController", ['$scope', '$location', 'marketingFactory', 'companyMarketingProgramFactory',function ($scope,$location, marketingFactory, companyMarketingProgramFactory) { 
        $scope.marketingCategoryId="";
        $scope.redirect= function(pageName,marketingCategoryId)
        {   
                 
            $scope.marketingCategoryId=marketingCategoryId;
            $location.path("/"+pageName);         
            

        };
         $scope.redirectProgram= function(pageName,marketingCategoryId,marketingProgramId,htmlData)
        {   
            alert(marketingProgramId);            
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
        
         $scope.displayMarketingProgramByCategoryId = function(forwardone){
                marketingFactory.marketingProgramsGet(1).then(function (data) {
                $scope.pageName="marketingPrograms";
                $scope.forward=forwardone;
                $scope.displayAllMarketingPrograms=data.d.details;
                $scope.header="Select a Category";
  
            });

        };    
        
         $scope.saveMarketingProgram = function(){
             alert();
                companyMarketingProgramFactory.setMarketingProgramPost().then(function (data) {
                    alert(JSON.stringify(data));
//                $scope.pageName="marketingPrograms";
//                $scope.forward=forwardone;
//                $scope.displayAllMarketingPrograms=data.d.details;
//                $scope.header="Select a Category";
  
            });

        };    
        
}]);
