
yourPlanFlowApp.controller("yourPlanController", ['$scope', '$location', 'yourPlanFactory', function ($scope, $location, yourPlanFactory) {
    $scope.entities_selected_time = "";
    $scope.master_facebook = getfacebook();
    $scope.master_twitter = gettwitter();
    $scope.master_email = getemail();
    $scope.master_note = getnote();
    
    // use scope.onPikadaySelect for older scope syntax
    $scope.onPikadaySelect = function onPikadaySelect(pikaday) {
      alert(pikaday.toString());
    };
  
var user_selected_date = '';
       var picker = new Pikaday(
                            {
                                field: document.getElementById('jumptodatepicker'),
                                firstDay: 1,
                                minDate: new Date('2000-01-01'),
                                maxDate: new Date('2050-12-31'),
                                yearRange: [2000,2050],
                                onSelect: function() {
                                    var mydate=this.getMoment();
                                   var mydt=mydate.toLocaleString();
                                    var myDate = new Date(mydt);
                                    $scope.setCurrentDate(myDate);
                                }                                
                            });
                            
    $scope.setCurrentDateActions = function (){
      
    };
    $scope.setCurrentDate = function(selected_date) {    
        $(".delete-button").hide();
        $("#liPriority").show();
        user_selected_date = selected_date;
        angular.element(document.getElementById('yourPlanController')).scope().getCampaigns();
    };
    function addDays(theDate, days) {
    return new Date(theDate.getTime() + days * 24 * 60 * 60 * 1000);
}

//$(document).ready(function ()
//{
//     $(".calendar-dropdown").click(function (){$("#jumptodatepicker").trigger( "click" );});
//    
//    var picker = new Pikaday(
//                            {
//                                field: document.getElementById('jumptodatepicker'),
//                                firstDay: 1,
//                                minDate: new Date('2000-01-01'),
//                                maxDate: new Date('2050-12-31'),
//                                yearRange: [2000,2050],
//                                onSelect: function() {
//                                    var mydate=this.getMoment();
//                                   var mydt=mydate.toLocaleString();
//                                    var myDate = new Date(mydt);
//                                    setCurrentDate(myDate);
//                                }                                
//                            });
//                            
//  function setCurrentDate(selected_date) {    
//    $(".delete-button").hide();
//    $("#liPriority").show();
//    user_selected_date = selected_date;
//    angular.element(document.getElementById('controllerMarketingCampaign')).scope().getCampaigns();
//}
    
//});

           



    $scope.getCampaigns = function(){
         var curr_date = '';
        var tomorrowDate = '';
        var new_date = '';
        if (user_selected_date !== "") {
            curr_date = moment(user_selected_date).format('YYYY-MM-DD');
            tomorrowDate = moment(addDays(user_selected_date, 1)).format('YYYY-MM-DD');
            new_date = moment(addDays(user_selected_date, 15)).format('YYYY-MM-DD');
        } else {
            curr_date = moment(new Date()).format('YYYY-MM-DD');
            tomorrowDate = moment(addDays(new Date(), 1)).format('YYYY-MM-DD');
            new_date = moment(addDays(new Date(), 15)).format('YYYY-MM-DD');
        }
        latest_date=curr_date;
        var invalid= "Invalid date";
  yourPlanFactory.scheduledEntitiesGet(curr_date,new_date).then(function (data) {
      
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
        
    $scope.ShowAddAction = function()
    { 
        $scope.isYourplan = true;
        $scope.fade = true;
        $scope.addAction = true;
    }
    
    $scope.closeOverlay = function()
    {
        $scope.fade = false;
        $scope.addAction = false;
        
    }
        
    $scope.AddAction = function(addTitle,datePicker,timePicker,actionType)
    {
        var actiondate = datePicker;
        var actionDateTime=$("#timepicker1").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var myDate = new Date(l);
        var days=0;
        var schedule_time = Date.parse(l);
        var myEpoch = schedule_time;
        var days=0;
        var action = {"title": addTitle, "actiontype": actionType,"type": "save","description":0,"marketingType":0,"action_date": myEpoch,"days":days};
        yourPlanFactory.addActionPost(action).then(function (data) {
       
    });
    }
    }]);

       