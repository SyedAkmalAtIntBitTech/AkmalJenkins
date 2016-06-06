dashboardFlowApp.controller("dashboardController", ['$scope', '$location', 'categoryFactory','subCategoryFactory','redirectFactory', function ($scope,$location, categoryFactory, subCategoryFactory,redirectFactory) {
    $scope.emailChannelId = 3;
    $scope.printChannelId = 2;
    $scope.imageChannelId = 1;
    $scope.forward="";
    $scope.redirectFlow= function(pageName)
    {   
      redirectFactory.redirectFlowTo(pageName);        
    };
    $scope.categoryId="";
    $scope.redirect= function(pageName,categoryId1)
    {
        $scope.categoryId=categoryId1;
        $scope.forward=pageName;
        $location.path("/"+pageName);         
        
    };
    $scope.redirectToMarketingProgram= function(pageName)
    {
        $window.location=getHost()+"user/"+pageName;       
    };
    $scope.getCategories= function(forward)
    {
        categoryFactory.allCompanyCategoriesGet(emailChannelId).then(function (data) {
            $scope.header="Select Category";
            $scope.forward=forward;
            $scope.displayAllCategories=data.d.details;
        });
    };
    $scope.getSubCategories= function(forward)
    {
        alert(JSON.stringify($scope.categoryId)+"...");
        categoryFactory.allCompanyCategoriesGet($scope.categoryId).then(function (data) {
            $scope.header="Select Subcategory";
            $scope.forward=forward;
            $scope.displayAllCategories=data.d.details;
        });
    };
    
}]);