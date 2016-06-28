
marketingFlowApp.controller("marketingController", ['$scope', '$location', '$filter', '$sce', 'marketingFactory', 'companyMarketingProgramFactory', 'yourPlanFactory', 'companyFactory', 'settingsFactory', 'companyMarketingProgramFactory', 'marketingRecurringEmailControllerFactory', 'emailFactory', function ($scope, $location, $filter, $sce, marketingFactory, companyMarketingProgramFactory, yourPlanFactory, companyFactory, settingsFactory, companyMarketingProgramFactory, marketingRecurringEmailControllerFactory, emailFactory) {
        $scope.marketingCategoryId = "";
        $scope.marketingProgramId = "";
        $scope.past = "";
        $scope.endDate = "";
        $scope.programId = "";

        $scope.initEmailAutomation = function () {
            $('#edit').froalaEditor({
                key: FroalaLicenseKey
            });
        };
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
        $scope.redirectToActions = function (pageName, programId, past, endData)
        {

            $scope.past = past;
            $scope.endDate = endData;
            $scope.programId = programId;
            $location.path("/" + pageName);


        };

        $scope.redirectToEmailAutomation = function (pageName, add, programId, zero)
        {

            $scope.programId = programId;
            $scope.add = add;
            $scope.zero = zero;
            $location.path("/" + pageName);

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

        $scope.getProgramActions = function (forward)
        {

            companyMarketingProgramFactory.alluserMarketingProgramGet($scope.programId).then(function (data) {
                $scope.template_status = data.emailautomation;
                $scope.programs = data;
                $scope.actionType = "Email";
                $scope.forward = forward;


            });

        };


        $scope.ShowAddAction = function ()
        {
            $scope.fadeClass = 'fadeClass';
            $scope.fade = true;
            $scope.addAction = true;

        }

        $scope.closeOverlay = function ()
        {
            $scope.fadeClass = '';
            $scope.addAction = false;

        }

        $scope.AddAction = function (addTitle, datePicker, timePicker, actionType)
        {
            var actiondate = datePicker;
            var actionDateTime = $("#timepicker1").val().replace(/ /g, '');
            var l = actiondate.toLocaleString() + " " + actionDateTime.toLocaleString();
            var myDate = new Date(l);
            var days = 0;
            var schedule_time = Date.parse(l);
            var myEpoch = schedule_time;
            var days = 0;
            var action = {"title": addTitle, "actiontype": actionType, "type": "save", "description": "", "marketingType": $scope.programId, "action_date": myEpoch, "days": days};
            companyMarketingProgramFactory.addActionPost(action).then(function (data) {


            });
        };

        $scope.addUpdateRecuringAction = function ()
        {
            marketingRecurringEmailControllerFactory.recurringEmailTemplatePost().then(function (data) {


            });

        };
        $scope.closePopup = function () {
            $scope.reminderSectionClass = '';
            $scope.emailsectionClass = '';
            $scope.fadeClass = '';
        };
        $scope.setTab = function (tabName) {
            if (tabName === 'actionDetails') {
                $scope.top_subnav_link_active_actionDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_notesDetail_Class = '';
                $scope.top_subnav_link_active_savedDetail_Class = '';
                $scope.generalActions = true;
                $scope.generalSavedDetails = false;
                $scope.generalNotes = false;
            }
            if (tabName === 'savedDetails') {
                $scope.top_subnav_link_active_savedDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_actionDetail_Class = '';
                $scope.top_subnav_link_active_notesDetail_Class = '';
                $scope.generalSavedDetails = true;
                $scope.generalActions = false;
                $scope.generalNotes = false;
            }
            if (tabName === 'notes') {
                $scope.top_subnav_link_active_notesDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_actionDetail_Class = '';
                $scope.top_subnav_link_active_savedDetail_Class = '';
                $scope.generalNotes = true;
                $scope.generalActions = false;
                $scope.generalSavedDetails = false;
            }
            if (tabName === 'reminderDetails') {
                $scope.top_subnav_link_active_reminderDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_savedReminder_Class = '';
                $scope.reminderDetailsTab = true;
                $scope.savedReminderTab = false;
            }
            if (tabName === 'savedReminder') {
                $scope.top_subnav_link_active_savedReminder_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_reminderDetail_Class = '';
                $scope.savedReminderTab = true;
                $scope.reminderDetailsTab = false;
            }
        };

        $scope.getRecurringScheduleDetails = function (schedule_id, template_status, till_date, schedule_time, entity_type, schedule_title, schedule_desc, date_status, days)
        {
            $scope.isRecurring = true;
            $scope.savedEmail = false;
            $scope.schedule_id = schedule_id;
            $scope.generalSavedDetails = true;
            $scope.generalNotes = false;
            $scope.generalActions = false;
            $scope.emailsectionClass = 'emailsectionClass';
            $scope.fadeClass = 'fadeClass';
            $scope.action_template_status = template_status;
            $scope.generalActionDetailsHeader = "Recurring Email";
            $scope.scheduledTo = 'POST';
            $scope.setTab('savedDetails');
            $scope.masterActionType = "Recurring Email";
            $scope.savedDetailsAddTemplateButton = "Save an email to this action";
            $scope.savedDetailsAddTemplateLink = "#/marketingprogramactions";
            $scope.templateApproveButton = "Play";
            $scope.templateDisapproveButton = "Pause";
            $scope.recurringScheduleData = {schedule_title: schedule_title, schedule_desc: schedule_desc,
                schedule_id: schedule_id, entities_list_name: "",
                email_template_status: template_status, schedule_type: "Recurring Email",
                marketing_program_name: "", user_marketing_program_id: $scope.programId,
                days: days, entities_selected_time: $filter('date')(schedule_time, "HH:mm a"), entities_subject: "",
                entities_from_name: "", entities_reply_to_email_address: ""};

            yourPlanFactory.scheduledEmailGet(schedule_id).then(function (data) {
                $scope.recurringEntitiesDetails = JSON.parse(data.d.details);

                $scope.recurringScheduleData.entities_subject = $scope.recurringEntitiesDetails.subject;
                $scope.recurringScheduleData.entities_list_name = $scope.recurringEntitiesDetails.email_list_name;
                $scope.recurringScheduleData.entities_from_name = $scope.recurringEntitiesDetails.from_name;
                $scope.recurringScheduleData.entities_reply_to_email_address = $scope.recurringEntitiesDetails.reply_to_email_address;

                var iframe = document.getElementById('iframeForAction');
                if ($scope.recurringEntitiesDetails.body) {
                    $scope.savedEmail = true;
                    $scope.savedTemplateHeader = "SAVED EMAIL PREVIEW";
                    $scope.deleteScheduleButton = "Remove Saved Email";
                    iframe.contentDocument.body.innerHTML = $scope.recurringEntitiesDetails.body;
                } else {
                    $scope.savedEmail = false;
                    $scope.actionTypeNoTemplateMessage = "No emails saved to this action.";
                }
            });
        };
//            emailautomation.scheduledEntityListId,emailautomation.status, emailautomation.tillDate,emailautomation.dateTime,emailautomation.actionType,emailautomation.programTemplateName,emailautomation.description,emailautomation.postDateStatus,emailautomation.days
        $scope.getScheduleDetails = function (schedule_id, template_status, schedule_date, entity_type, schedule_title, schedule_desc, schedule_time, action_status, days, marketingName)
        {
//        $scope.entities_selected_time =schedule_time;
//        $scope.entities_selected_time = $filter('date')(schedule_time, "MMM dd yyyy");
            $scope.isRecurring = false;
            $scope.savedEmail = false;
            $scope.schedule_id = schedule_id;
            $scope.generalSavedDetails = true;
            $scope.generalNotes = false;
            $scope.generalActions = false;
            $scope.emailsectionClass = 'emailsectionClass';
            $scope.fadeClass = 'fadeClass';
            $scope.action_template_status = template_status;
            $scope.generalActionDetailsHeader = entity_type;
            $scope.scheduledTo = 'POST';
            $scope.setTab('savedDetails');
            $scope.masterActionType = entity_type;
            $scope.savedDetailsAddTemplateButton = "Go to Dashboard";
            $scope.savedDetailsAddTemplateLink = "dashboard";
            $scope.templateApproveButton = "Approve";
            $scope.templateDisapproveButton = "Disapprove";
//        if (entity_type === getnote()) {
//            $scope.reminderSectionClass='reminderSectionClass';
//            $scope.savedReminderTab=true;
//            $scope.setTab('savedReminder');
//        }

            if (entity_type === getemail()) {
                $scope.scheduledTo = 'SEND';
            }

            var date = "";//$scope.entities_selected_time;
            var time = $filter('date')(schedule_time, "hh:mm a")
//var time = schedule_time;
            $scope.scheduleData = {schedule_title: schedule_title, entities_selected_time: date,
                schedule_id: schedule_id, schedule_desc: schedule_desc,
                email_template_status: template_status, schedule_type: entity_type,
                marketing_program_name: marketingName, user_marketing_program_id: $scope.programId,
                days: days, schedule_time: time};
//                $('#emailcontentiframe').contents().find('html').html(data.body); 
            if (entity_type === getemail()) {
                yourPlanFactory.scheduledEmailGet($scope.scheduleData.schedule_id).then(function (data) {
                    $scope.entitiesdetails = JSON.parse(data.d.details);
                    var iframe = document.getElementById('iframeForAction');

                    if ($scope.entitiesdetails != "{}") {
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
            var actiondate = "Mon Jan 01 1970";//$("#emaildatetime").val();
            var days = scheduleUpdatedData.days;//$("#emaildays").val();
            var actionDateTime = scheduleUpdatedData.schedule_time;//$("#timepickeremail").val().replace(/ /g,'');
            var l = actiondate.toLocaleString() + " " + actionDateTime.toLocaleString();
            var schedule_time = Date.parse(l);
            var myEpoch = schedule_time;
            var description = "";
//        if (!validateemailaction()) {
            var action = {
                "schedule_id": schedule_id.toString(), "type": "update",
                "title": title, "actiontype": actiontype,
                "description": description, "action_date": myEpoch, "days": days.toString()
            };
            yourPlanFactory.addActionPost(action).then(function (data) {
                alert(actionsaved);
//                $scope.getCampaigns();
            });
//        }
        };

        $scope.Approval = function (entity_id, template_status, entity_type) {

            var approval_type = {"entity_id": entity_id.toString(),
                "template_status": template_status,
                "entity_type": entity_type};

            companyMarketingProgramFactory.approveStatusPost(approval_type).then(function (data) {
                if (data.toString() == "true") {
                    if ($scope.action_template_status == "Template Saved")
                        $scope.action_template_status = "Approved";
                    else
                        $scope.action_template_status = "Template Saved";
                    alert(templetestatussaved);
                    $scope.getCampaigns();
                } else {
                    alert(savingrecordproblem);
                }
            });
//        $http({
//            method: 'POST',
//            url: getHost()+'approveStatus',
//            headers: {'Content-Type':'application/json'},
//            data: JSON.stringify(approval_type)
//        }).success(function (data, status, headers, config) {
//           
//          if (data == "true"){
//            alert(templetestatussaved);
//            window.open(getHost() + 'user/marketing', "_self");
//          }else {
//              alert(savingrecordproblem);
//          }
//        }).error(function (data, status, headers, config) {
//  
//            alert(nodataerror);
//        });      

        };

        $scope.deleteSchedule = function (schedules_to_delete, type, section, isRecurring) {
            var message;
            var requestBody;
            var responseMessage;
            if (type == "deleteMultiple") {
                message = multideleteconfirm;
                requestBody = {"type": "deleteSelected",
                    "schedule_ids": selected_schedules_to_delete, "entity_type": "null"};
                responseMessage = multideletesuccess;
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
                yourPlanFactory.changeSchedulePost(requestBody).then(function (data) {
                    $scope.getCampaigns();
                    $scope.closePopup();
                });
            }
        };

        $scope.updateActionNote = function (scheduleId) {

            var actiontype = getemail();
            var action = {
                "schedule_id": scheduleId.toString(), "type": "updatenotesyourplan", "actiontype": actiontype,
                "description": $scope.scheduleData.schedule_desc
            };
            yourPlanFactory.addActionPost(action).then(function (data) {
                alert(JSON.stringify(data));
            });
        };

        $scope.editFooter = function () {
            $scope.emailFooterPopupDetails = true;
        };

        $scope.hideEmailFooterPopupDetails = function () {
            $scope.emailFooterPopupDetails = false;
        };

        $scope.getFooterDetails = function () {
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
                $scope.company = $scope.footerDetails;
            });
        };

        $scope.changeFooterDetails = function (company) {
            var footerAddress = company.address;
            var footerWebsiteUrl = company.websiteUrl;
            var footerFacebookUrl = company.facebookUrl;
            var footerTwitterUrl = company.twitterUrl;
            var footerInstagramUrl = company.instagramUrl;
            var footerPopupDeatils = '{"footerFacebookUrl":"' + footerFacebookUrl + '","footerTwitterUrl":"' + footerTwitterUrl + '","footerInstagramUrl":"' + footerInstagramUrl + '","footerWebsiteUrl":"' + footerWebsiteUrl + '","footerAddress":"' + footerAddress + '"}';
            $scope.emailFooterPopupDetails = false;
            settingsFactory.setFooterPost(footerDetails).then(function (data) {
                $scope.getFooterDetails();
            });
        };

        $scope.emailPreviewOnClick = function () {
            var rendomIframeFilename = "";
            rendomIframeFilename = event.timeStamp;
            settingsFactory.getAllPreferencesGet().then(function (data) {
                alert(JSON.stringify(data));
                $("#fade").show();
                $scope.emailPreviewPopup = true;
                var footerData = JSON.parse(data.d.details);
                if (!footerData.userProfile) {
                    $("#emailFooterPopup").show();
                }
                else {
                    if (!footerData.userProfile.address) {
                        $("#emailFooterPopup").show();
                    }
                    else {
                        $("#email_previewdiv").show();
//                $("#fade").show();
//                $scope.emailPreviewPopup = true;
                        var footer = $scope.userFooter(footerData.userProfile.facebookUrl, footerData.userProfile.twitterUrl,
                                footerData.userProfile.websiteUrl, footerData.userProfile.instagramUrl,
                                footerData.userProfile.address);
                        var sendData = {
                            htmlString: $('#edit').froalaEditor('html.get') + footer,
                            iframeName: rendomIframeFilename.toString()
                        };
                        emailFactory.previewServletPost(sendData).then(function () {
                            $scope.overlayFade = true;
                            $("#dynamictable5").empty();
                            $("#dynamictable6").empty();
                            var iframePath = getHost() + "download/HTML?fileName=" + rendomIframeFilename + ".html";
                            $("#dynamictable5").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                            $("#dynamictable6").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                        });
                    }
                    ;
                }
                ;
            });
        };

        $scope.closeEmailPreviewPopup = function ()
        {
            $scope.emailPreviewPopup = false;
            $("#fade").hide();
        };

        $scope.userFooter = function (fb, twitter, website, instagram, address) {
            var returnFooter = "";
            var footer = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" height=\"100%\" width=\"100%\" bgcolor=\"#EEEEEE\" style=\"border-collapse:collapse;\"><tr><td valign=\"top\"> <center style=\"width: 100%;\"> <div style=\"max-width: 680px;\"> <!--[if (gte mso 9)|(IE)]> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\" align=\"center\"> <tr> <td> <![endif]--> <!-- Atom Body: BEGIN --> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" bgcolor=\"#EEEEEE\" width=\"100%\" style=\"max-width: 680px;\"> <tr> <td style=\"padding-top:15px;\" class=\"mobile-padding\"> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"100%\" style=\"max-width: 300px; background-color:#inherit\" class=\"mobile-padding\"> <tr>";

            var footerFB = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerFB$$$\"><img src=\"" + getHost() + "images/Facebook_Filled.png" + "\" alt=\"Facebook Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Facebook </td> </tr> </table> </td>";

            var footerTwitter = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerTwitter$$$\"><img src=\"" + getHost() + "images/Twitter_Filled.png" + "\" alt=\"Twitter Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Twitter </td> </tr> </table> </td>";

            var footerWebsite = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerWebsite$$$\"><img src=\"" + getHost() + "images/Website_Filled.png" + "\" alt=\"Website Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Website </td> </tr> </table> </td>";

            var footerInstagram = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerInstagram$$$\"><img src=\"" + getHost() + "images/Insta_Filled.png" + "\" alt=\"Instagram Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Instagram </td> </tr> </table> </td>";

            var footerMiddle = "</tr> </table> </td> </tr>";

            var footerAddress = "<!--HEADER: BEGIN--> <tr> <td style=\"font-family: sans-serif; font-size: 12px; mso-height-rule: exactly; line-height: 120%; text-align:center; color: #555555; padding: 20px 55px 20px 55px;\" class=\"fluid mobile-padding\"> $$$footerAddress$$$ </td> </tr> <!--HEADER: END-->";

            var footerClose = "</table> <!--[if (gte mso 9)|(IE)]> </td> </tr> </table> <![endif]--> </div> </center> </td></tr></table>";


            returnFooter = footer;
            if (fb !== "")
                returnFooter += footerFB.replace("$$$footerFB$$$", fb);
            if (twitter !== "" && typeof twitter !== "undefined")
                returnFooter += footerTwitter.replace("$$$footerTwitter$$$", twitter);

            if (website !== "" && typeof website !== "undefined")
                returnFooter += footerWebsite.replace("$$$footerWebsite$$$", website);

            if (instagram !== "" && typeof instagram !== "undefined")
                returnFooter += footerInstagram.replace("$$$footerInstagram$$$", instagram);

            returnFooter += footerMiddle;

            if (address !== "" && typeof address !== "undefined")
                returnFooter += footerAddress.replace("$$$footerAddress$$$", address);

            returnFooter += footerClose;

            return returnFooter;
        };

        $("#templatetab").click(function () {
            $("#blklist").hide();
            $("#blockdivheader").hide();
            $("#textTab").show();
            $("#stylediv").show();

            $("#templatetab").removeClass("emailSideBar-tab").addClass("emailSideBar-tab-active").css("background-color", "#ffffff").css("color", "#19587c");
            $("#templatediv").removeClass("emailSideBar-tab-active").addClass("emailSideBar-tab").css("background-color", "transparent").css("color", "#19587c");
        });
        $("#templatediv").click(function () {
            $("#stylediv").hide();
            $("#textTab").hide();
            $("#blockdivheader").show();
            $("#blklist").show();
            $("#templatediv").removeClass("emailSideBar-tab").addClass("emailSideBar-tab-active").css("background-color", "#ffffff").css("color", "#19587c");
            $("#templatetab").removeClass("emailSideBar-tab-active").addClass("emailSideBar-tab").css("background-color", "transparent").css("color", "#19587c");
        });

        $scope.blockdivheader = true;
        $scope.textdivheader = false;
        $scope.addtemplatetab = "emailSideBar-tab-active";
        $scope.texttab = "emailSideBar-tab";
        $scope.showText = function (isClick) {
            if (isClick === "true")
            {
                $scope.blockdivheader = false;
                $scope.textdivheader = true;
                $scope.addtemplatetab = "emailSideBar-tab";
                $scope.texttab = "emailSideBar-tab-active";
            }
//            if ($scope.isBlockClicked === "true" || $scope.htmlBlockId !== "defaultblock1")
//            {
//                blockModelFactory.allEmailBlockModelGet($scope.selectedBlockId).then(function (data) {
//                    $scope.datalistsstyles = data.d.details;
//                });
//            } else
//            {
//                modelFactory.EmailModelsIdGet($scope.subCategoryId).then(function (data) {
//                    $scope.datalistsstyles = data.d.details;
//                });
//            }
        };

        $scope.showTemplates = function () {
            $scope.blockdivheader = true;
            $scope.textdivheader = false;
            $scope.texttab = "emailSideBar-tab";
            $scope.addtemplatetab = "emailSideBar-tab-active";
            companyFactory.allBlocksForCompanyGet().then(function (data) {
                $scope.blockLists = data.d.details;
            });
        };


        $scope.getEmailTemplates = function () {
            $("#emailautomationcontent").hide();
            $("#emlautomeditorcontainer").show();
            marketingRecurringEmailControllerFactory.allRecurringEmailTemplatesGet().then(function (data) {

            });
        };

        $scope.showHTMLData = function (html_data, id) {
            var $iframe = $('.fr-iframe');
            $('#edit').froalaEditor('html.set', '' + html_data + '');
            template_id = id;
        };
        $scope.getFooterDetails = function () {
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
            });
        };


    }]);

       