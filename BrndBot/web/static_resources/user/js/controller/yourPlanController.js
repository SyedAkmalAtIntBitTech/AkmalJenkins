
yourPlanFlowApp.controller("yourPlanController", ['$scope', '$location', '$filter', 'yourPlanFactory', 'companyFactory', 'settingsFactory', 'companyMarketingProgramFactory', function ($scope, $location, $filter, yourPlanFactory, companyFactory, settingsFactory, companyMarketingProgramFactory) {
    
    $scope.emailsectionClass='';
    $scope.fadeClass='';
    $scope.entities_selected_time = "";
    $scope.masterActionType = "";
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
            $scope.savedEmail = false;
            $scope.schedule_id = schedule_id;
            $scope.generalSavedDetails = true;
            $scope.generalNotes = false;
            $scope.generalActions = false;
            $scope.emailsectionClass = 'emailsectionClass';
            $scope.fadeClass = 'fadeClass';
            $scope.action_template_status = template_status;
            alert($scope.action_template_status);
            $scope.generalActionDetailsHeader = entity_type;
            $scope.scheduledTo = 'POST';
            $scope.setTab('savedDetails');
            $scope.masterActionType = entity_type;
            if (entity_type === getnote()) {
                $scope.reminderSectionClass = 'reminderSectionClass';
                $scope.savedReminderTab = true;
                $scope.setTab('savedReminder');
            }

            if (entity_type === getemail()) {
                $scope.scheduledTo = 'SEND';
            }

            var date = $scope.entities_selected_time;
            var time = $filter('date')(schedule_time, "hh:mm a")
            $scope.scheduleData = {schedule_title: schedule_title, entities_selected_time: date,
                schedule_id: schedule_id, schedule_desc: schedule_desc,
                email_template_status: template_status, schedule_type: entity_type,
                marketing_program_name: marketingName, user_marketing_program_id: programId,
                days: days, is_today_active: is_today_active, schedule_time: time};
//                $('#emailcontentiframe').contents().find('html').html(data.body); 


            if (entity_type === getemail()) {
                yourPlanFactory.scheduledEmailGet($scope.scheduleData.schedule_id).then(function (data) {
                    $scope.entitiesdetails = JSON.parse(data.d.details);
                    var iframe = document.getElementById('iframeForAction');
                    
                    if (data.d.details != "{}") {
                        $scope.savedEmail = true;
                        $scope.savedTemplateHeader = "SAVED EMAIL PREVIEW";
                        $scope.deleteScheduleButton = "Remove Saved Email";
                        iframe.contentDocument.body.innerHTML = $scope.entitiesdetails.body;
                    } else {
                        $scope.savedEmail = false;
                        $scope.actionTypeNoTemplateMessage = "No emails saved to this action.";
                    }
                });
            } else {
                companyFactory.currentCompanyGet().then(function (companyData) {
                    $scope.companyId = companyData.d.details[0];
                    var postData = {
                        access_token_method: "getAccessToken"
                    };
                    settingsFactory.twitterPost(postData).then(function (twitterData) {
                        var twitterData = twitterData.d.message.split(",");
                        $scope.twitterprofileName = twitterData[2];
                        if (!$scope.twitterprofileName)
                            $scope.twitterprofileName = "--";
                        yourPlanFactory.scheduledSocialPost($scope.scheduleData.schedule_id).then(function (data) {
                            $scope.entitiesdetails = JSON.parse(data.d.details);
                            
                            var iframe = document.getElementById('iframeForAction');
//                iframe.contentDocument.head.appendChild = ;
                            
                            if (data.d.details != "{}") {
                                $scope.savedEmail = true;
                                if (entity_type === gettwitter())
                                {
                                    $scope.savedTemplateHeader = "SAVED TWITTER PREVIEW";
                                } else if (entity_type === getfacebook())
                                    $scope.savedTemplateHeader = "SAVED FACEBOOK PREVIEW";
                                $scope.deleteScheduleButton = "Remove Saved Post";
                                
                                var htmlData = "<style>\n"
                                    + ".twitter-post-preview {\n"
                                    + "    width: 494px;\n"
                                    + "    height: 340px !important;\n"
                                    + "    margin: auto;\n"
                                    + "    padding: 12px 12px 0px 12px;\n"
                                    + "    float: none;\n"
                                    + "    border: 1px solid rgb(223, 224, 228);\n"
                                    + "    border-radius: 3px;\n"
                                    + "    background-color: #FFFFFF;\n"
                                    + "}\n"
                                    + ".Facebook-preview {\n"
                                    + "    width: 494px;\n"
                                    + "    height: 455px;\n"
                                    + "    margin: auto;\n"
                                    + "    padding: 12px 12px 0px 12px;\n"
                                    + "    float: none;\n"
                                    + "    border: 1px solid rgb(223, 224, 228);\n"
                                    + "    border-radius: 3px;\n"
                                    + "    background-color: #FFFFFF;\n"
                                    + "    overflow: auto;\n"
                                    + "    margin-bottom: 65px;\n"
                                    + "}"
                                    + "\n"
                                    + ".Facebook-preview-header {\n"
                                    + "    width: 500px;\n"
                                    + "    height: 40px;\n"
                                    + "    float: left;\n"
                                    + "    margin-bottom: 11px;\n"
                                    + "}\n"
                                    + "\n"
                                    + ".Facebook-preview-profpic {\n"
                                    + "    width: 40px;\n"
                                    + "    height: 40px;\n"
                                    + "    margin-right: 8px;\n"
                                    + "    float: left;\n"
                                    + "    background-color: beige;\n"
                                    + "}\n"
                                    + "\n"
                                    + ".Facebook-preview-profpic > img {\n"
                                    + "    width: 100%;\n"
                                    + "    height: 100%;\n"
                                    + "}\n"
                                    + "\n"
                                    + ".Facebook-preview-name-container {\n"
                                    + "    width: 445px;\n"
                                    + "    height: 100%;\n"
                                    + "    float: inherit;\n"
                                    + "    color: #365899;\n"
                                    + "    font-weight: bold;\n"
                                    + "}\n"
                                    + "\n"
                                    + ".Twitter-preview-name-container {\n"
                                    + "    width: 445px;\n"
                                    + "    height: 100%;\n"
                                    + "    float: inherit;\n"
                                    + "}\n"
                                    + "\n"
                                    + ".Facebook-preview-usercontent {\n"
                                    + "    font-family: helvetica, arial;\n"
                                    + "    font-weight: normal;\n"
                                    + "    line-height: 19.32px;\n"
                                    + "    color: rgb(20, 24, 35);\n"
                                    + "    font-size: 14px;\n"
                                    + "    float: left;\n"
                                    + "}\n"
                                    + "\n"
                                    + ".Facebook-link-container {\n"
                                    + "    box-shadow: rgba(0, 0, 0, 0.0980392) 0px 0px 0px 1.5px inset, rgba(0, 0, 0, 0.0470588) 0px 1px 1px 0px;\n"
                                    + "    color: rgb(20, 24, 35);\n"
                                    + "    border: 1px solid rgba(0, 0, 0, 0.0980392);\n"
                                    + "    float: left;\n"
                                    + "    width: 100%;\n"
                                    + "    margin-top: 10px;\n"
                                    + "}\n"
                                    + "img#prevfbimg {\n"
                                    + "    width: 100%;\n"
                                    + "    height: 245px;\n"
                                    + "    margin-left: -2px;\n"
                                    + "}"
                                    + ".Facebook-preview-image {\n"
                                    + "    width: 100%;\n"
                                    + "    height: 244px;\n"
                                    + "    background-color: #5CC2A5;\n"
                                    + "    float: left;\n"
                                    + "}"
                                    + ".Facebook-preview-link-container {\n"
                                    + "    width: 100%;\n"
                                    + "    padding: 10px 12px 10px 12px;\n"
                                    + "    float: left;\n"
                                    + "    box-sizing: border-box;\n"
                                    + "    overflow: hidden;\n"
                                    + "}"
                                    + ".Facebook-preview-link-title {\n"
                                    + "    font-family: Georgia, 'lucida grande', tahoma;\n"
                                    + "    font-size: 18px;\n"
                                    + "    color: rgb(20, 24, 35);\n"
                                    + "    font-weight: 500;\n"
                                    + "    line-height: 22px;\n"
                                    + "    margin-bottom: 5px;\n"
                                    + "}"
                                    + ".Facebook-preview-link-description {\n"
                                    + "    color: rgb(20, 24, 35);\n"
                                    + "    direction: ltr;\n"
                                    + "    display: block;\n"
                                    + "    font-family: sans-serif;\n"
                                    + "    font-size: 12px;\n"
                                    + "    line-height: 16px;\n"
                                    + "    max-height: 80px;\n"
                                    + "    overflow-x: hidden;\n"
                                    + "    overflow-y: hidden;\n"
                                    + "    word-wrap: break-word;\n"
                                    + "    zoom: 1;\n"
                                    + "    text-overflow: ellipsis;\n"
                                    + "}"
                                    + ".Facebook-preview-link-url {\n"
                                    + "    color: rgb(145, 151, 163);\n"
                                    + "    direction: ltr;\n"
                                    + "    display: block;\n"
                                    + "    font-family: sans-serif;\n"
                                    + "    font-size: 14px;\n"
                                    + "    line-height: 16.08px;\n"
                                    + "    padding-top: 9px;\n"
                                    + "    margin-bottom: 5px;\n"
                                    + "}"
                                    + "</style>";
                            if (entity_type === gettwitter()) {
                                htmlData += "<div class=\"twitter-post-preview\">\n"
                                        + "    <div class=\"Facebook-preview-header\">\n"
                                        + "        <div class=\"Facebook-preview-profpic\">\n"
                                        + "            <img id=\"twitter_preview_profpic\" src=\"/BrndBot/downloadImage?imageType=COMPANY_LOGO&amp;companyId=" + $scope.companyId + "&amp;imageName=companylogo.png\">\n"
                                        + "        </div>\n"
                                        + "        <div class=\"Twitter-preview-name-container\">\n"
                                        + "            <div class=\"Facebook-preview-name ng-binding\"><strong>" + $scope.twitterprofileName + "</strong><br>" + $scope.entitiesdetails.metadata.text + " " + $scope.entitiesdetails.metadata.shorturl + "</div>\n"
                                        + "        </div>\n"
                                        + "    </div>\n";
//                    if($scope.entitiesdetails.metadata.shorturl) {
//                    htmlData += "    <div class=\"Facebook-preview-usercontent ng-binding\">"+$scope.entitiesdetails.metadata.shorturl+"</div>\n"
//                    }
                                htmlData += "    <div class=\"Facebook-link-container\">\n"
                                        + "        <div ng-show=\"entitiesdetails.image_type == 'gallery'\">\n"
                                        + "            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageName=" + $scope.entitiesdetails.image_name + "&imageType=GALLERY&companyId=" + $scope.companyId + "\">\n"
                                        + "        </div>\n"
                                        + "    </div>\n"
                                        + "</div>";
                            } else if (entity_type === getfacebook()) {
                                htmlData += "<div class=\"Facebook-preview\">\n"
                                        + "                                <div class=\"Facebook-preview-header\">\n"
                                        + "                                    <div class=\"Facebook-preview-profpic\"><img id=\"fb_preview_profpic\" src=\"/BrndBot/downloadImage?imageType=COMPANY_LOGO&amp;companyId=" + $scope.companyId + "&amp;imageName=companylogo.png\"></div>\n"
                                        + "                                    <div class=\"Facebook-preview-name-container\">\n"
                                        + "                                        <div class=\"Facebook-preview-name ng-binding\">" + $scope.entitiesdetails.metadata.ManagedPage + "</div>\n"
                                        + "                                    </div>\n"
                                        + "                                </div>\n"
                                        + "                                <div class=\"Facebook-preview-usercontent ng-binding\">" + $scope.entitiesdetails.metadata.post_text + "</div>\n"
                                        + "                                <div class=\"Facebook-link-container\">\n"
                                        + "                                    <div class=\"Facebook-preview-image\">\n"
                                        + "                                        <div ng-show=\"entitiesdetails.image_type == 'gallery'\">\n"
                                        + "                                            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageType=GALLERY&amp;imageName=" + $scope.entitiesdetails.image_name + "&amp;companyId=" + $scope.companyId + "\">\n"
                                        + "                                        </div>\n"
                                        + "                                        <div ng-show=\"entitiesdetails.image_type == 'layout'\" style=\"display: none;\">\n"
                                        + "                                            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageType=LAYOUT_IMAGES&amp;imageName=13.jpg\">\n"
                                        + "                                        </div>\n"
                                        + "                                        <div ng-show=\"entitiesdetails.image_type == 'url'\" style=\"display: none;\">\n"
                                        + "                                            <img id=\"prevfbimg\" src=\"13.jpg\">\n"
                                        + "                                        </div>\n"
                                        + "                                        \n"
                                        + "                                    </div>\n"
                                        + "                                    <div class=\"Facebook-preview-link-container\">\n"
                                        + "                                        <div class=\"Facebook-preview-link-title ng-binding\">" + $scope.entitiesdetails.metadata.title + "</div>\n"
                                        + "                                        <div class=\"Facebook-preview-link-description ng-binding\">" + $scope.entitiesdetails.metadata.description + "</div>\n"
                                        + "                                        <div class=\"Facebook-preview-link-url ng-binding\">" + $scope.entitiesdetails.metadata.url + "</div>\n"
                                        + "                                    </div>\n"
                                        + "                                </div>\n"
                                        + "                            </div>";
                            }
                                
                                iframe.contentDocument.body.innerHTML = htmlData;
                            } else {
                                $scope.savedEmail = false;
                                $scope.actionTypeNoTemplateMessage = "No post saved to this action.";
                            }
                        });
                    });
                });
            }
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
    
    $scope.Approval = function(entity_id, template_status, entity_type){
        
        var approval_type = {"entity_id": entity_id.toString(), 
                             "template_status":template_status, 
                             "entity_type": entity_type};
                        
        companyMarketingProgramFactory.approveStatusPost(approval_type).then(function(data){
            if (data.toString() == "true"){
            alert(templetestatussaved);
            $scope.getCampaigns();
          }else {
              alert(savingrecordproblem);
          }
        });
        $http({
            method: 'POST',
            url: getHost()+'approveStatus',
            headers: {'Content-Type':'application/json'},
            data: JSON.stringify(approval_type)
        }).success(function (data, status, headers, config) {
           
          if (data == "true"){
            alert(templetestatussaved);
            window.open(getHost() + 'user/marketing', "_self");
          }else {
              alert(savingrecordproblem);
          }
        }).error(function (data, status, headers, config) {
  
            alert(nodataerror);
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
            alert(JSON.stringify(responseMessage))
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

       