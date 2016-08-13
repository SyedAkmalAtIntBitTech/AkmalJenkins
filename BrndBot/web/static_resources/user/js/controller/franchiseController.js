franchiseHubApp.controller("franchiseController", ['$scope', '$window', '$location', function ($scope, $window, $location) {
        
        $scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };
    
           $scope.viewPushedEmailDetails = function ()
        {
            $scope.showPushedEmailDetails = true;
            $location.path("/pushedemaildetails");
        };
        
}]);