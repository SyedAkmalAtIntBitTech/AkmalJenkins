
yourPlanFlowApp.controller("yourPlanController", ['$scope', '$location', 'yourPlanFactory', function ($scope, $location, yourPlanFactory) {
    $scope.entities_selected_time = "";
    $scope.master_facebook = getfacebook();
    $scope.master_twitter = gettwitter();
    $scope.master_email = getemail();
    $scope.master_note = getnote();
        
    $scope.getCampaigns = function(){
       
  yourPlanFactory.scheduledEntitiesGet("2016-05-03","2016-06-10").then(function (data) {
      
                    var parseJSON=JSON.parse(data.d.details);
                    $scope.entityS = JSON.parse(JSON.stringify(parseJSON));
                    $scope.today_date = moment(new Date()).format('YYYY-MM-DD');
                    $scope.tomorrow_date = moment($scope.addDays(new Date(), 1)).format('YYYY-MM-DD');
                    $scope.entitySet = parseJSON.entitydata;
                    $scope.nodata = parseJSON.noactionsmessage;
      
     
  });
  };
$scope.addDays = function(theDate, days) {
    return new Date(theDate.getTime() + days * 24 * 60 * 60 * 1000);
};
     $scope.mySplit = function(string, nb) {
      var array = string.split('-');
      return array[nb];
    }
     $scope.myYear = function(string) {
        var month="";
        if(string=='01')
            return month="Jan";
        if(string=='02')
            return month="Feb";
        if(string=='03')
            return month="Mar";
        if(string=='04')
            return month="Apr";
        if(string=='05')
            return month="May";
        if(string=='06')
            return month="Jun";
        if(string=='07')
            return month="Jul";
        if(string=='08')
            return month="Aug";
        if(string=='09')
            return month="Sept";
        if(string=='10')
            return month="Oct";
        if(string=='11')
            return month="Nov";
        if(string=='12')
            return month="Dec";
    }
        

    }]);

       