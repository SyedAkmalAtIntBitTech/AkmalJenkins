
dashboardFlowApp.controller("dashboardController", ['$scope', '$location', 'categoryFactory', function ($scope,$location, categoryFactory) {
    $scope.emailChannelId = 3;
    $scope.printChannelId = 2;
    $scope.imageChannelId = 1;
    $scope.forward="";
    $scope.redirect= function(pageName)
    {
        $scope.forward=pageName;
        $location.path("/"+pageName);         
        
    };
    $scope.getCategories= function(forward)
    {
        categoryFactory.allCompanyCategoriesGet(emailChannelId).then(function (data) {
            $scope.header="Select Category";
            $scope.forward="emailsubcategory";
            $scope.displayAllCategories=data.d.details;
        });
    };

}]);