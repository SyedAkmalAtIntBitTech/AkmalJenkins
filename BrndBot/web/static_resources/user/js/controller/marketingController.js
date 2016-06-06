
marketingFlowApp.controller("marketingController", ['$scope', '$location', 'marketingFactory', function ($scope,$location, marketingFactory) { 
        marketingFactory.allMarketingProgramsGet().then(function (data) {
        $scope.getAllMarketingPrograms = function(){
        $(".pane_content").css("display","block");
        $scope.paneContent
        $scope.marketingPrograms = data.d.details;
              
    };    
         
        });

  

}]);
