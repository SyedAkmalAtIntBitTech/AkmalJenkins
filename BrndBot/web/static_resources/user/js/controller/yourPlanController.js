
yourPlanFlowApp.controller("yourPlanController", ['$scope', '$location', 'yourPlanFactory', function ($scope, $location, yourPlanFactory) {
  $scope.getCampaigns = function(){
       
  yourPlanFactory.scheduledEntitiesGet("2016-05-03","2016-06-10").then(function (data) {
   var yourplanData=JSON.parse(JSON.stringify(data.d.details));
    var entityDetails =  JSON.stringify(JSON.parse(yourplanData).entitydata);
     $scope.entitySet = entityDetails;
     var noData = JSON.stringify(JSON.parse(yourplanData).noactionsmessage)

//   $scope.nodata = eval(noData);
//   alert(noData);
  
  alert($scope.entitySet);
     
  });
  }
 
    }]);

       