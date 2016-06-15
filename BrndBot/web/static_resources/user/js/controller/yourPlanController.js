
yourPlanFlowApp.controller("yourPlanController", ['$scope', '$location', 'yourPlanFactory', function ($scope, $location, yourPlanFactory) {
    $scope.entities_selected_time = "";
    $scope.master_facebook = getfacebook();
    $scope.master_twitter = gettwitter();
    $scope.master_email = getemail();
    $scope.master_note = getnote();
    $scope.emailsectionClass='';
    $scope.fadeClass='';
        
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
        $scope.entities_selected_time =schedule_time;
        
       
//        alert( $scope.entities_selected_time);
//        entity_type='Reminder';
        $scope.generalSavedDetails=true;
        $scope.generalNotes=false;
        $scope.generalActions=false;
        $scope.emailsectionClass='emailsectionClass';
        $scope.fadeClass='fadeClass';
        $scope.generalActionDetailsHeader=entity_type;        
        $scope.scheduledTo='POST';
        $scope.setTab('savedDetails');
        if (entity_type === getnote()) {
            $scope.reminderSectionClass='reminderSectionClass';
            $scope.savedReminderTab=true;
        }
        
        if (entity_type === getemail()) {
                $scope.scheduledTo='SEND';
        }
        
        $scope.scheduleData={schedule_title:schedule_title,entities_selected_time:date,
                             schedule_id:schedule_id,schedule_desc:schedule_desc,
                             email_template_status:template_status,schedule_type:entity_type,
                             marketing_program_name:marketingName,user_marketing_program_id:programId,
                             days:days,is_today_active:is_today_active,schedule_time:schedule_time};
        var date = new Date($scope.scheduleData.schedule_time);
//                $('#emailcontentiframe').contents().find('html').html(data.body); 

        yourPlanFactory.scheduledEmailGet($scope.scheduleData.schedule_id).then(function (data){
//            $scope.entitiesdetails = data;
//            alert(JSON.stringify(data.d.details));
    //                $scope.iframedata=data.body;
        });
//            $slider=2;
//            sliderDialog = "#emailsection";
//            
//            $("#emailpostsection").show();
//            $("#emailpostremove").show();
//            $("#emailactionsection").hide();
//            $("#emailnotesection").hide();
//            $("#emailactionsave").hide();
//            $("#emailnotesave").hide();
//
//            $("#emailaction1").removeClass("top-subnav-link-active-detail");
//            $("#emailaction1 a").removeAttr("class");
//            $("#emailnote1").removeClass("top-subnav-link-active-detail");
//            $("#emailnote1 a").removeAttr("class");
//            $("#emailpost1").removeClass("top-subnav-link-active-detail");
//            $("#emailpost1 a").removeAttr("class");
//
//            $("#emailpost1").addClass("top-subnav-link-active-detail");
//            $("#emailpost1 a").addClass("h3-subnav-subnav-active");
//            $("#emailaction1").addClass("top-subnav-links-detail");
//            $("#emailaction1 a").addClass("h3-subnav");
//            $("#emailnote1").addClass("top-subnav-links-detail");
//            $("#emailnote1 a").addClass("h3-subnav");
            
//            $(".time_pick").width('100%');
            
//            prevSliderDialog = "#emailsection";
//             $("#emailpost").click();
//            $http({
//                method: 'GET',
//                url: getHost() + 'GetScheduledEmailDetail?schedule_id=' + schedule_id
//            }).success(function (data) {
//                $scope.entitiesdetails = data;
//                if (data.body == undefined) {
//                    $("#emailapprove").hide();
//                    $("#mailtemplatesaved1").hide();
//                    $("#mailnotemplate1").show();
//                    $('#emailpostremove').hide();
//                    $("#noemailsdiv").show();
//                    $("#savedemailsdiv").hide();
//                } else {
//                    $("#emailapprove").show();
//                    $("#noemailsdiv").hide();
//                    $("#savedemailsdiv").show();
//                     $("#mailtemplatesaved1").show();
//                    $("#mailnotemplate1").hide();
//                    $('#emailpostremove').show();
//                }
//                var date = new Date(schedule_time);
//                $('#emailcontentiframe').contents().find('html').html(data.body);                 
//                $scope.entities_selected_time = date;
//                $scope.schedule_title = schedule_title;
//                $scope.schedule_id = schedule_id;
//                console.log(schedule_desc);
//                $scope.schedule_desc = schedule_desc;
//                $scope.email_template_status = template_status;
//                $scope.schedule_type = entity_type;
//                $scope.marketing_program_name = marketingName;
//                $scope.user_marketing_program_id = programId;
//                $scope.days = days;
//                $scope.is_today_active = is_today_active;
//            }).error(function (data) {
//                alert(requesterror);
//            });
//            $('#slider-button').click();
//        }
    };
    
    
    $scope.updateAction = function (scheduleUpdatedData) {
        
        var actiontype = scheduleUpdatedData.schedule_type;//$("#email_schedule_type").val();
        var schedule_id = scheduleUpdatedData.schedule_id;//$("#email_scheduleid").val();
        var title = scheduleUpdatedData.schedule_title;//$("#email_edit_title").val();
        var actiondate = scheduleUpdatedData.schedule_time;//$("#emaildatetime").val();
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
        if (!validateemailaction()) {
            var action = {
                "schedule_id": schedule_id, "type": "update",
                "title": title, "actiontype": actiontype,
                "description": description, "action_date": myEpoch, "days":days
            };
            alert(JSON.stringify(action));
//            $http({
//                method: 'POST',
//                url: getHost() + 'AddAction',
//                headers: {'Content-Type': 'application/json'},
//                data: JSON.stringify(action)
//            }).success(function (data)
//            {
//                $scope.status = data;
//                if (data != "") {
//                    alert(actionsaved);
//                    $("#change").val("1");
//                    $scope.getCampaigns();
//                }
//            }).error(function (data, status) {
//                alert(requesterror);
//            });
        }
    };
    
    $scope.updateActionNote = function (scheduleId){
        
        var actiontype = getemail();
            var action = {
                "schedule_id": scheduleId.toString(), "type": "updatenotesyourplan","actiontype": actiontype,
                "description": $scope.scheduleData.schedule_desc
            };    
            alert(JSON.stringify(action));
            yourPlanFactory.addActionPost(action).then(function (data){
                alert(JSON.stringify(data));
            });
//            $http({
//                method: 'POST',
//                url: getHost() + 'AddAction',
//                headers: {'Content-Type': 'application/json'},
//                data: JSON.stringify(action)
//            }).success(function (data)
//            {
//                $scope.status = data;
//                if (data != "") {
//                    alert(emailnotesaved);
//                    $("#change").val("1");
//                }
//            }).error(function (data, status) {
//                alert(reqemailnotesaved);
//            });

    };
    }]);

       