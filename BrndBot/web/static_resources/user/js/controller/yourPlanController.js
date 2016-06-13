
yourPlanFlowApp.controller("yourPlanController", ['$scope', '$location', 'yourPlanFactory', function ($scope, $location, yourPlanFactory) {
  $scope.getCampaigns = function(){
        
  yourPlanFactory.scheduledEntitiesGet("2016-05-03","2016-06-10").then(function (data) {
     
    
   var yourplanData=JSON.parse(JSON.stringify(data.d.details));
  $scope.entitySet= JSON.stringify(JSON.parse(yourplanData).entitydata[9]);
  $scope.nodata = eval(JSON.stringify(JSON.parse(yourplanData).noactionsmessage));
  
  alert(JSON.stringify(JSON.parse(yourplanData).entitydata[9].dataArray));
     
  });
  }
 
    }]);

       