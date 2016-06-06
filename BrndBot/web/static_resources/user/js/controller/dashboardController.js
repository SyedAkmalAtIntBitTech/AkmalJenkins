
<<<<<<< HEAD
dashboardFlowApp.controller("dashboardController", ['$scope', '$location','$window', 'categoryFactory', function ($scope,$location,$window, categoryFactory) {
=======
dashboardFlowApp.controller("dashboardController", ['$scope', '$location', 'categoryFactory','subCategoryFactory', function ($scope,$location, categoryFactory, subCategoryFactory) {
>>>>>>> e0c79fce85415f77f2b4d43bd296f90b8cffa15f
    $scope.emailChannelId = 3;
    $scope.printChannelId = 2;
    $scope.imageChannelId = 1;
    $scope.forward="";
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