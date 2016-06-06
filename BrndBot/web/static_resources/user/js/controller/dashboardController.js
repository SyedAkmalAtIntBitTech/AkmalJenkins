
dashboardFlowApp.controller("dashboardController", ['$scope', '$location', 'categoryFactory','subCategoryFactory', function ($scope,$location, categoryFactory, subCategoryFactory) {
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