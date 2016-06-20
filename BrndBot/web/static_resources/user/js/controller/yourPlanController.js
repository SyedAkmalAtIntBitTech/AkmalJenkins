
yourPlanFlowApp.controller("yourPlanController", ['$scope', '$location', '$filter', 'yourPlanFactory', function ($scope, $location, $filter, yourPlanFactory) {
    
    $scope.emailsectionClass='';
    $scope.fadeClass='';
    $scope.entities_selected_time = "";
    $scope.master_facebook = getfacebook();
    $scope.master_twitter = gettwitter();
    $scope.master_email = getemail();
    $scope.master_note = getnote();
    $scope.savedEmail=false;
    $scope.schedule_id='';
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
    };
     $scope.myYear = function(string) {
        var month="";
        if(string==='01')
            return month="Jan";
        if(string==='02')
            return month="Feb";
        if(string==='03')
            return month="Mar";
        if(string==='04')
            return month="Apr";
        if(string==='05')
            return month="May";
        if(string==='06')
            return month="Jun";
        if(string==='07')
            return month="Jul";
        if(string==='08')
            return month="Aug";
        if(string==='09')
            return month="Sept";
        if(string==='10')
            return month="Oct";
        if(string==='11')
            return month="Nov";
        if(string==='12')
            return month="Dec";
    };
        
    $scope.ShowAddAction = function()
    { 
        $scope.fadeClass='fadeClass';
        $scope.isYourplan = true;
        $scope.addAction = true;
    };
    
    $scope.closeOverlay = function()
    {
        $scope.fadeClass='';
        $scope.addAction = false;        
    };
        
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
        var action = {"title": addTitle, "actiontype": actionType,"type": "save","description":"","marketingType":0,"action_date": myEpoch,"days":days};
        yourPlanFactory.addActionPost(action).then(function (data) {
            $scope.getCampaigns();            
            $scope.closeOverlay();
        });
    };
    
    $scope.closePopup = function (){
        $scope.reminderSectionClass='';
        $scope.emailsectionClass='';
        $scope.fadeClass='';
    };
    
    $scope.setTab = function(tabName){
        if(tabName === 'actionDetails'){
            $scope.top_subnav_link_active_actionDetail_Class='top-subnav-link-active-detail-Class';
            $scope.top_subnav_link_active_notesDetail_Class='';
            $scope.top_subnav_link_active_savedDetail_Class='';
            $scope.generalActions=true;
            $scope.generalSavedDetails=false;
            $scope.generalNotes=false;
        }
        if(tabName === 'savedDetails'){
            $scope.top_subnav_link_active_savedDetail_Class='top-subnav-link-active-detail-Class';
            $scope.top_subnav_link_active_actionDetail_Class='';
            $scope.top_subnav_link_active_notesDetail_Class='';
            $scope.generalSavedDetails=true;
            $scope.generalActions=false;
            $scope.generalNotes=false;
        }
        if(tabName === 'notes'){
            $scope.top_subnav_link_active_notesDetail_Class='top-subnav-link-active-detail-Class';
            $scope.top_subnav_link_active_actionDetail_Class='';
            $scope.top_subnav_link_active_savedDetail_Class='';
            $scope.generalNotes=true;
            $scope.generalActions=false;
            $scope.generalSavedDetails=false;
        }
        if(tabName === 'reminderDetails'){
            $scope.top_subnav_link_active_reminderDetail_Class='top-subnav-link-active-detail-Class';
            $scope.top_subnav_link_active_savedReminder_Class='';
            $scope.reminderDetailsTab=true;
            $scope.savedReminderTab=false;
        }
        if(tabName === 'savedReminder'){
            $scope.top_subnav_link_active_savedReminder_Class='top-subnav-link-active-detail-Class';
            $scope.top_subnav_link_active_reminderDetail_Class='';
            $scope.savedReminderTab=true;
            $scope.reminderDetailsTab=false;
        }
    };
    
    $scope.getScheduleDetails = function (schedule_id, template_status, schedule_time, entity_type, schedule_title, schedule_desc, marketingName, programId, days, is_today_active) 
    { 
//        $scope.entities_selected_time =schedule_time;
        $scope.entities_selected_time = $filter('date')(schedule_time, "MMM dd yyyy");
        $scope.savedEmail=false;   
        $scope.schedule_id=schedule_id;
        $scope.generalSavedDetails=true;
        $scope.generalNotes=false;
        $scope.generalActions=false;
        $scope.emailsectionClass='emailsectionClass';
        $scope.fadeClass='fadeClass';
        $scope.email_template_status=template_status;
        $scope.generalActionDetailsHeader=entity_type;        
        $scope.scheduledTo='POST';
        $scope.setTab('savedDetails');
        if (entity_type === getnote()) {
            $scope.reminderSectionClass='reminderSectionClass';
            $scope.savedReminderTab=true;
            $scope.setTab('savedReminder');
        }
        
        if (entity_type === getemail()) {
                $scope.scheduledTo='SEND';
        }
        
        var date = $scope.entities_selected_time;
        var time = $filter('date')(schedule_time, "hh:mm a")
        $scope.scheduleData={schedule_title:schedule_title,entities_selected_time:date,
                             schedule_id:schedule_id,schedule_desc:schedule_desc,
                             email_template_status:template_status,schedule_type:entity_type,
                             marketing_program_name:marketingName,user_marketing_program_id:programId,
                             days:days,is_today_active:is_today_active,schedule_time:time};
//                $('#emailcontentiframe').contents().find('html').html(data.body); 

        yourPlanFactory.scheduledEmailGet($scope.scheduleData.schedule_id).then(function (data){
            $scope.entitiesdetails = JSON.parse(JSON.stringify(data.d.details));
//            if (entity_type === getemail()) {                
                if($scope.entitiesdetails != "{}"){
                        $scope.savedEmail=true;
                }else{
                        $scope.savedEmail=false;
                }
//            }
        });
    };
    
    
    $scope.updateAction = function (scheduleUpdatedData) {
        
        var actiontype = scheduleUpdatedData.schedule_type;//$("#email_schedule_type").val();
        var schedule_id = scheduleUpdatedData.schedule_id;//$("#email_scheduleid").val();
        var title = scheduleUpdatedData.schedule_title;//$("#email_edit_title").val();
        var actiondate = scheduleUpdatedData.entities_selected_time;//$("#emaildatetime").val();
        var days=scheduleUpdatedData.days;//$("#emaildays").val();
        if(days!="0")
        {
            actiondate="Mon Jan 01 1970";
        }
        var actionDateTime=scheduleUpdatedData.schedule_time;//$("#timepickeremail").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        var myEpoch = schedule_time;
        var description = "";
//        if (!validateemailaction()) {
            var action = {
                "schedule_id": schedule_id.toString(), "type": "update",
                "title": title, "actiontype": actiontype,
                "description": description, "action_date": myEpoch, "days":days.toString()
            };
            yourPlanFactory.addActionPost(action).then(function (data){
                alert(actionsaved);
                $scope.getCampaigns();
            });
//        }
    };
    
    $scope.updateActionNote = function (scheduleId){
        
        var actiontype = getemail();
            var action = {
                "schedule_id": scheduleId.toString(), "type": "updatenotesyourplan","actiontype": actiontype,
                "description": $scope.scheduleData.schedule_desc
            };    
            yourPlanFactory.addActionPost(action).then(function (data){
                $scope.getCampaigns();
            });
    };
    
    $scope.updateReminderDescription = function (schedule_id) {
        
        var actiontype = getnote();
        var reminderAction = {
            "schedule_id": schedule_id, "type": "updatenotes","actiontype": actiontype,
            "description": $scope.scheduleData.schedule_desc
        };
        yourPlanFactory.addActionPost(reminderAction).then(function (data){
                $scope.getCampaigns();
        });
    };
    
    $scope.deleteSchedule = function (schedules_to_delete, type, section, isRecurring) {
        var message;
        var requestBody;
        var responseMessage;
        if (type == "deleteMultiple") {
            message = multideleteconfirm;
            requestBody = {"type": "deleteSelected",
                "schedule_ids": selected_schedules_to_delete, "entity_type": "null"};
            responseMessage =multideletesuccess;
        } else if (type == "delete") {
            message = singledeleteconfirm;
            requestBody = {"type": "delete",
                            "schedule_ids": schedules_to_delete, "entity_type": section, 
                            "isRecurring": isRecurring};
            responseMessage = singledeletesuccess;
        } else if (type == "remove") {
            message = removecnfirm;
            requestBody = {"type": "removetemplate", 
                           "schedule_ids": schedules_to_delete, "entity_type": section, 
                           "isRecurring": isRecurring};
            responseMessage = multideletesuccess;
        }

        if (confirm(message)) {
            yourPlanFactory.changeSchedulePost(requestBody).then(function (data){
                $scope.getCampaigns();
                $scope.closePopup();
            });
        }
    };
    
    $scope.updateNote = function (scheduleData) {
        var schedule_id = scheduleData.schedule_id;//$("#note_scheduleid").val();
        var note_title = scheduleData.schedule_title;//$("#edit_note_title").val();
        var status ="no_template";
        var note_desc = "";
        var message = "Do you wan't to update the record?";
        var actiondate = scheduleData.entities_selected_time;//$("#datepickernote").val();
        var actionDateTime=scheduleData.schedule_time;//$("#timepickernote").val().replace(/ /g,'');
        var l=actiondate.toLocaleString() +" "+actionDateTime.toLocaleString();
        var schedule_time = Date.parse(l);
        var myEpoch = schedule_time;    
// 
        var schedule_details = {
            "type": getnote(),
            "schedule_id": schedule_id.toString(),
            "schedule_title": note_title,
            "schedule_desc": note_desc,
            "status": status,
            "schedule_time": myEpoch
        };
        alert(JSON.stringify(schedule_details));
        yourPlanFactory.changeSchedulePost(schedule_details).then(function (data){
            alert(JSON.stringify(data));
            $scope.getCampaigns();
        });
//       
//        $http({
//            method: 'POST',
//            url: getHost() + 'ChangeSchedule',
//            headers: {'Content-Type': 'application/json'},
//            data: JSON.stringify(schedule_details)
//        }).success(function (data)
//        {
//            $scope.status = data;
//            if (data != "") {
//                alert(detailssaved);
//                $("#change").val("1");
//            }
//        }).error(function (data, status) {
//            alert(requesterror);
//        });        
    };
}]);

       