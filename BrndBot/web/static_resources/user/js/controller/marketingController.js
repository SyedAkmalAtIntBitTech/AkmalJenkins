
marketingFlowApp.controller("marketingController", ['$scope', '$location', '$filter', '$sce', 'marketingFactory', 'companyMarketingProgramFactory', 'yourPlanFactory', function ($scope, $location, $filter, $sce, marketingFactory, companyMarketingProgramFactory, yourPlanFactory) {
        $scope.marketingCategoryId = "";
        $scope.marketingProgramId = "";
        $scope.past = "";
        $scope.endDate ="";
        $scope.programId="";
        $scope.redirect = function (pageName, marketingCategoryId)
        {

            $scope.marketingCategoryId = marketingCategoryId;
            $location.path("/" + pageName);


        };
        $scope.redirectProgram = function (pageName, marketingCategoryId, marketingProgramId, htmlData)
        {

            $scope.marketingCategoryId = marketingCategoryId;
            $scope.marketingProgramId = marketingProgramId;
            $scope.htmlData = htmlData;
            $location.path("/" + pageName);


        };
         $scope.redirectToActions = function (pageName, programId,past,endData)
        {
            
            $scope.past = past;
            $scope.endDate = endData;
            $scope.programId = programId;
            $location.path("/"+pageName);


        };
        
         $scope.redirectToEmailAutomation = function(pageName, add, programId, zero)
         {
              
               $scope.programId = programId;
               $scope.add = add;
               $scope.zero = zero;
               $location.path("/"+pageName);
             
         };
         
        $scope.getAllMarketingPrograms = function (forward) {
            marketingFactory.companyMarketingCategoriesGet().then(function (data) {
                $scope.header = "Please choose a marketing program type.";
                $scope.pageName = "marketing";
                $scope.forward = forward;
                $scope.marketingCategories = data.d.details;
            });

        };

        $scope.displayMarketingProgramByCategoryId = function (forward) {
           
            marketingFactory.marketingProgramsGet($scope.marketingCategoryId).then(function (data) {
                $scope.pageName = "marketingPrograms";
                $scope.forward = forward;
                $scope.displayAllMarketingPrograms = data.d.details;
                $scope.header = "Select a Category";

            });

        };

        $scope.saveMarketingProgram = function (programName, programUrl, programUrlName, programDateTime) {
            var data = {"program_name": programName,
                "program_date_time": programDateTime,
                "program_url": programUrl,
                "program_url_name": programUrlName,
                "marketing_category_id": $scope.marketingCategoryId.toString(),
                "marketing_program_id": $scope.marketingProgramId.toString()
            };

            companyMarketingProgramFactory.setMarketingProgramPost(data).then(function (data) {

            });
        };

        $scope.getUserMarketingProgramsOpen = function (forward) {
            companyMarketingProgramFactory.listAllMarketingProgramGet("Open").then(function (data) {
                $scope.currentPrograms = data.programs;
                $scope.currProgramsDiv = true;
                $scope.pastProgramsDiv = false;
                $scope.forward = forward;
            });

        };

        $scope.getUserMarketingProgramsClosed = function (forward) {
            companyMarketingProgramFactory.listAllMarketingProgramGet("Closed").then(function (data) {
                $scope.pastPrograms = data.programs;
                $scope.currProgramsDiv = false;
                $scope.pastProgramsDiv = true;
                $scope.forward = forward;
            });

        };
        $scope.showPastPrograms = function () {
            $scope.currProgramsDiv = false;
            $scope.pastProgramsDiv = true;

        };
        $scope.showCurrentPrograms = function () {
            $scope.currProgramsDiv = true;
            $scope.pastProgramsDiv = false;

        };
        
        $scope.getProgramActions = function(forward)
        {
           
               companyMarketingProgramFactory.alluserMarketingProgramGet($scope.programId).then(function (data) {
                   
                 $scope.template_status=data.emailautomation;
                 $scope.programs = data;
                 $scope.actionType="Email";
                  $scope.forward = forward;
                  
                 
             });
            
        };
        
        
         $scope.ShowAddAction = function()
        { 
       $scope.fadeClass='fadeClass';
        $scope.fade = true;
        $scope.addAction = true;
        
        }
    
    $scope.closeOverlay = function()
    {
       $scope.fadeClass='';
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
        var action = {"title": addTitle, "actiontype": actionType,"type": "save","description":"","marketingType":$scope.programId,"action_date": myEpoch,"days":days};
        companyMarketingProgramFactory.addActionPost(action).then(function (data) {
            
      
    });
    };
    
    $scope.addUpdateRecuringAction = function()
    {
         marketingRecurringEmailControllerFactory.recurringEmailTemplatePost().then(function (data) {
             
             
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
   $scope.getScheduleDetails = function (schedule_id, template_status, schedule_date, entity_type , schedule_title, schedule_desc, schedule_time, action_status, days, marketingName) 
    { 
//        $scope.entities_selected_time =schedule_time;
//        $scope.entities_selected_time = $filter('date')(schedule_time, "MMM dd yyyy");
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
//        if (entity_type === getnote()) {
//            $scope.reminderSectionClass='reminderSectionClass';
//            $scope.savedReminderTab=true;
//            $scope.setTab('savedReminder');
//        }
        
        if (entity_type === getemail()) {
                $scope.scheduledTo='SEND';
        }
        
        var date = "";//$scope.entities_selected_time;
        var time = $filter('date')(schedule_time, "hh:mm a")
//var time = schedule_time;
        $scope.scheduleData={schedule_title:schedule_title,entities_selected_time:date,
                             schedule_id:schedule_id,schedule_desc:schedule_desc,
                             email_template_status:template_status,schedule_type:entity_type,
                             marketing_program_name:marketingName,user_marketing_program_id:$scope.programId,
                             days:days,schedule_time:time};
//                $('#emailcontentiframe').contents().find('html').html(data.body); 
alert($scope.scheduleData.schedule_id);
if (entity_type === getemail()) {
        yourPlanFactory.scheduledEmailGet($scope.scheduleData.schedule_id).then(function (data){
            $scope.entitiesdetails = JSON.parse(data.d.details);
             var iframe = document.getElementById('iframeForAction');
//                iframe.contentDocument.head.appendChild = ;
                iframe.contentDocument.body.innerHTML = $scope.entitiesdetails.body;
//            $scope.entitiesdetails = JSON.parse(JSON.stringify(data.d.details));
////            if (entity_type === getemail()) {                
                if($scope.entitiesdetails != "{}"){
                        $scope.savedEmail=true;
                }else{
                        $scope.savedEmail=false;
                }
////            }
        });
        } else {
            yourPlanFactory.scheduledSocialPost($scope.scheduleData.schedule_id).then(function (data){
                
                $scope.entitiesdetails = JSON.parse(data.d.details);
                var iframe = document.getElementById('iframeForAction');
//                iframe.contentDocument.head.appendChild = ;
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
                    + "</style>"
                    + "<div class=\"twitter-post-preview\">\n"
                    + "    <div class=\"Facebook-preview-header\">\n"
                    + "        <div class=\"Facebook-preview-profpic\">\n"
                    + "            <img id=\"twitter_preview_profpic\" src=\"/BrndBot/downloadImage?imageType=COMPANY_LOGO&amp;companyId=2&amp;imageName=companylogo.png\">\n"
                    + "        </div>\n"
                    + "        <div class=\"Facebook-preview-name-container\">\n"
                    + "            <div class=\"Facebook-preview-name ng-binding\">"+$scope.entitiesdetails.metadata.text+"</div>\n"
                    + "        </div>\n"
                    + "    </div>\n";
                    if($scope.entitiesdetails.metadata.shorturl) {
                    htmlData += "    <div class=\"Facebook-preview-usercontent ng-binding\">"+$scope.entitiesdetails.metadata.shorturl+"</div>\n"
                    }
                    htmlData+= "    <div class=\"Facebook-link-container\">\n"
                    + "        <div ng-show=\"entitiesdetails.image_type == 'gallery'\">\n"
                    + "            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageName="+$scope.entitiesdetails.image_name+"&imageType=GALLERY&companyId=2\">\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</div>";
                iframe.contentDocument.body.innerHTML = htmlData;
//                $scope.iframeDataHtml = $sce.trustAsHtml("<h1>test</h1>");
//                
//                $scope.iframedata = $sce.trustAsResourceUrl("http://www.w3schools.com");
            });
            
        }
        
    };
    
    $scope.updateAction = function (scheduleUpdatedData) {
        
        var actiontype = scheduleUpdatedData.schedule_type;//$("#email_schedule_type").val();
        var schedule_id = scheduleUpdatedData.schedule_id;//$("#email_scheduleid").val();
        var title = scheduleUpdatedData.schedule_title;//$("#email_edit_title").val();
        var actiondate = "Mon Jan 01 1970";//$("#emaildatetime").val();
        var days=scheduleUpdatedData.days;//$("#emaildays").val();
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
//                $scope.getCampaigns();
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
                alert(JSON.stringify(data));
            });
    };

    }]);

       