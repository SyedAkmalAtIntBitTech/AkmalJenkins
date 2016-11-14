marketingFlowApp.controller("marketingController", ['$scope', '$location', '$filter', '$sce', 'marketingFactory', 'companyMarketingProgramFactory', 'yourPlanFactory', 'companyFactory', 'settingsFactory', 'companyMarketingProgramFactory', 'marketingRecurringEmailFactory', 'emailFactory', 'emailListFactory', 'appSessionFactory', 'externalContentFactory', 'blockModelFactory', 'onboardingFactory', 'utilFactory', 'uploadImageFactory','companyImagesFactory','imageFactory', function ($scope, $location, $filter, $sce, marketingFactory, companyMarketingProgramFactory, yourPlanFactory, companyFactory, settingsFactory, companyMarketingProgramFactory, marketingRecurringEmailFactory, emailFactory, emailListFactory, appSessionFactory, externalContentFactory, blockModelFactory, onboardingFactory, utilFactory,uploadImageFactory,companyImagesFactory,imageFactory) {
        $scope.marketingCategoryId = "";
        $scope.marketingProgramId = "";
        $scope.past = "";
        $scope.endDate = "";
        $scope.programId = "";
        $scope.ddSelectedUser = '0';
        $scope.master_facebook = getfacebook();
        $scope.master_twitter = gettwitter();
        $scope.master_email = getemail();
        $scope.master_note = getnote();
        $scope.randomIframeFilename = event.timeStamp;
        $scope.actionNameValidation = actionNameValidation;
        $scope.actionDropdownValidation = actionDropdownValidation;
        $scope.actionDateValidation = actionDateValidation;
        $scope.lesserDateValidation = lesserDateValidation;
//      $scope.greaterDateValidation = greaterDateValidation;
        $scope.campaignNameValidation = campaignNameValidation;
        $scope.campaignDateValidation = campaignDateValidation;
        $scope.descriptionValidation = descriptionValidation;
        $scope.scheduleTimeValidation = scheduleTimeValidation;
        $scope.subjectValidation = subjectValidation;
        $scope.fromAddressValidation = fromAddressValidation;
        $scope.fromNameValidation = fromNameValidation;
        $scope.replyToValidation = replyToValidation;
        $scope.automatedEmailListValidation = automatedEmailListValidation;
        $scope.automationDayValidation = automationDayValidation;
        $scope.emailValidation = emailValidation;
        $scope.linkNameValidation = linkNameValidation;
        $scope.linkUrlValidation = linkUrlValidation;
        $scope.isRecurring = false;
        $scope.programDate = "";
        $scope.randomIframeFilename = event.timeStamp;
        $scope.showCampaignDetails = false;
        $scope.showCampaignActions = true;
        $scope.actionTypeValidation = false;
        $scope.dateLesser = false;
        $scope.timePickerVal = false;
        $scope.validateEmailId = false;
        $scope.clickedRemoveAction = false;
        $scope.setEmailToThisAction = "Save Email to this Action";
        $scope.dateValidation = false;
        $scope.validateLinkName = false;
        $scope.validateLinkUrl = false;
        $scope.addBlockCount = 0;
        $scope.changeUsers = false;
        $scope.companyName = "";
        $scope.editSavedEmail = false;
        $scope.isCurrentCompanyInFranchise = false;
        $scope.isCurrentCompanyAFranchiseHeadquarter = false;
        $scope.moreThanOneUser = false;
        $scope.userColor="";
        $scope.userInitials="";
        var userSortInfo={userSortName:"",userColor:""};
        $scope.selectImageId = "";
        $scope.addBlockStyle = true;

        $scope.getCompanyStatus = function () {
            appSessionFactory.isCurrentCompanyInFranchise().then(function (isCurrent) {
                $scope.isCurrentCompanyInFranchise = isCurrent;
            });
            appSessionFactory.isCurrentCompanyAFranchiseHeadquarter().then(function (isHead) {
                $scope.isCurrentCompanyAFranchiseHeadquarter = isHead;
            });
        };
        this.tab = 1;

        this.selectTab = function (setTab) {
            this.tab = setTab;
        };

        this.isSelected = function (checkTab) {
            return this.tab === checkTab;
        };
        
        $scope.isDeletePromptOpen = function(flag){
            $scope.clickedDeleteAction = flag;
        };
        
        $scope.companyAddressDetails = {};
        $scope.marketingFlowObject = kEmailFlowObject;
        $scope.emailListName = "";

        $scope.ddSelectAction = {
            text: "Select"
        };

        $scope.ddSelectActionOptions = [
            {
                text: 'Select',
                value: '0'
            }, {
                text: 'Facebook Post',
                value: 'Facebook'
            }, {
                text: 'Twitter Post',
                value: 'Twitter'
            }, {
                text: 'Email',
                value: 'Email'
            }, {
                text: 'Task',
                value: 'Reminder'
            }
        ];

        $scope.ddSelectUserOptions = [{
                text: 'Select',
                value: '0'
            }
        ];

        $scope.openChangeUserDD = function (flag) {
            $scope.changeUsers = flag;
        };

        $scope.ddSelectUser = {text: "Select"};


        $scope.chooseUserOnChange = function (actionValue) {
            $scope.ddSelectedUser = actionValue.value;
        };

        $scope.getUserDetails = function () {
            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                $scope.companyName = kGlobalCompanyObject.companyName;
                $scope.userFirstName = kGlobalCompanyObject.userFirstName;
                $scope.userLastName = kGlobalCompanyObject.userLastName;
                
                $scope.getUserDetailsByUserId(kGlobalCompanyObject.userId);
                kGlobalCompanyObject.userHashId = '';
                appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {});
                appSessionFactory.getDashboardMessage().then(function (message) {
                    if (message)
                    {
                        growl(message);
                        appSessionFactory.clearDashboardMessage().then(function (message) {
                        });
                    }
                    appSessionFactory.getUser().then(function (kGlobalCompanyObject) {
                        $scope.hasMultipleCompany = kGlobalCompanyObject.hasMultipleCompany;
                    });
                });
            });
        };
        
        
        $scope.showCompanyList = function () {
            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                kGlobalCompanyObject.userHashId = "";
                appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {
                });
            });
            window.location = getHost() + "user/loading";
        };

        $scope.displayCampaignDetails = function () {
            $scope.campaignDetailsClass = 'activeCampaign';
            $scope.campaignActionsClass = '';
            $scope.showCampaignDetails = true;
            $scope.showCampaignActions = false;
        };

        $scope.displayCampaignActions = function () {
            $scope.campaignActionsClass = 'activeCampaign';
            $scope.campaignDetailsClass = '';
            $scope.showCampaignActions = true;
            $scope.showCampaignDetails = false;
        };


        $scope.backToPreviousPage = function (previousPage) {
            $location.path("/" + previousPage);
        };

        $scope.initEmailAutomation = function () {
            $('#edit').froalaEditor({
                key: FroalaLicenseKey
            });
        };
        $scope.rediectToCreateCampaign = function (pageName) {
            $location.path("/" + pageName);
        };
        $scope.redirect = function (pageName)
        {
            $scope.marketingCategoryId = $scope.marketingFlowObject.marketingCategoryId;
            $location.path("/" + pageName);
        };
        $scope.redirectProgram = function (pageName)
        {
            $scope.marketingCategoryId = kEmailFlowObject.marketingCategoryId;
            $scope.marketingProgramId = kEmailFlowObject.marketingProgramId;
            $scope.htmlData = kEmailFlowObject.htmlData;
            $location.path("/" + pageName);
        };
        $scope.redirectToActions = function (pageName, programId, past, endData)
        {
            $scope.isProgramArchived = false;
            $scope.past = past;
            $scope.endDate = endData;
            $scope.programId = programId;
            $location.path("/" + pageName);
        };
        $scope.redirectToEmailAutomation = function (pageName, add, programId, zero, editSavedEmail)
        {
//            $scope.programId = programId;
            if ($scope.action_template_status !== 'Approved') {
                $scope.add = add;
                $scope.type = add;
                $scope.zero = zero;
                $scope.entityId = zero;
                $scope.closePopup();
                if (editSavedEmail)
                    $scope.editSavedEmail = true;
                else
                    $scope.editSavedEmail = false;
                $location.path("/" + pageName);
            } else {
                growl(actionAlreadyScheduled);
            }
        };

        $scope.getAllUsersInCompany = function () {
            yourPlanFactory.allUsersInCompanyGet().then(function (data) {
//                $scope.allUsers = data.d.details;
                $scope.ddSelectUserOptions = [{text: 'Select', value: '0'}];

                for (var i = 0; i < data.d.details.length; i++) {
                    $scope.ddSelectUserOptions.push({"text": data.d.details[i].firstName + " " + data.d.details[i].lastName, "value": data.d.details[i].userId});
                }
            });
            yourPlanFactory.noOfUsersInCompanyGet().then(function (data) {
                var noOfUsersInCompany = data.d.details;
                if (parseInt(noOfUsersInCompany) > 1) {
                    $scope.moreThanOneUser = true;
                }
            });
        };

        $scope.ChangeUserOnChange = function (changedValue) {
            $scope.ddSelectedUser = changedValue.value;
            $scope.changeAssignedTo($scope.schedule_id);
            $scope.openChangeUserDD(false);
        };


        $scope.getAllMarketingPrograms = function (forward) {
            marketingFactory.companyMarketingCategoriesGet().then(function (data) {
                $scope.header = "Please choose a type of Marketing Campaign";
                $scope.pageName = "marketing";
                $scope.forward = forward;
                $scope.marketingCategories = data.d.details;
            });
        };

        $scope.displayMarketingProgramByCategoryId = function (forward) {
            if ($scope.marketingCategoryId === '') {
                $location.path("/" + "createmarketingprogram");
            } else {
                marketingFactory.marketingProgramsGet($scope.marketingCategoryId).then(function (data) {
                    $scope.pageName = "marketingPrograms";
                    $scope.forward = forward;
                    $scope.displayAllMarketingPrograms = data.d.details;
                    $scope.header = "Select a Marketing Campaign";
                });
            }
        };

        $scope.saveMarketingProgramValidation = function (programName, programDateTime) {
            if (!programName) {
                $("#campaignname").focus();
                $scope.programName = "";
                return false;
            }
            if (!programDateTime) {
                $("#programdatetime").focus();
                $scope.programDateTime = "";
                return false;
            }
            return true;
        };

        $scope.saveMarketingProgram = function (programName, programUrl, programUrlName, programDateTime) {
            if ((programUrl === undefined) || (programUrlName === undefined)) {
                programUrl = "";
                programUrlName = "";
            }
            if ($scope.saveMarketingProgramValidation(programName, programDateTime))
            {
                var programDate = new Date(programDateTime);
                var data = {"program_name": programName,
                    "program_date_time": programDate,
                    "program_url": programUrl,
                    "program_url_name": programUrlName,
                    "marketing_category_id": $scope.marketingCategoryId.toString(),
                    "marketing_program_id": $scope.marketingProgramId.toString()
                };
                companyMarketingProgramFactory.setMarketingProgramPost(data).then(function (data) {

                    var responseData = data.operationStatus;
                    var dataId = data.id;
                    if (responseData.statusCode == "DataError") {

                        growl(responseData.messages[0]);
                    } else if (responseData.statusCode == "Success") {

                        $scope.redirectToActions("marketingprogramactions", dataId, 0, "");
                        growl(responseData.messages[0]);
                    }


                });
            }
        };

        $scope.getUserMarketingProgramsOpen = function (forward) {
            companyMarketingProgramFactory.listAllMarketingProgramGet("Open").then(function (data) {
                $scope.currentPrograms = data.programs;
                $scope.forward = forward;
            });
        };

        $scope.getUserMarketingProgramsClosed = function (forward) {
            companyMarketingProgramFactory.listAllMarketingProgramGet("Closed").then(function (data) {
                $scope.pastPrograms = data.programs;
                $scope.forward = forward;
            });
        };

        this.selectTab = function (setTab) {
            this.tab = setTab;
        };
        this.isSelected = function (checkTab) {
            return this.tab === checkTab;
        };

        $scope.isActive = function (tabName) {
            if ($scope.currProgramsDiv)
                return tabName === 'currentCampaign';
            else
                return tabName === 'archivedCampaign';
        };

        $scope.getProgramActions = function (forward)
        {
            $scope.isOneTimeActionsEmpty = false;
            $scope.isEmailAutomationActionsEmpty = false;
            if ($scope.programId === '') {
                $location.path("/" + "marketingprogramlists");
            } else {
                companyMarketingProgramFactory.alluserMarketingProgramGet($scope.programId).then(function (data) {
                    $scope.displayCampaignActions();
                    $scope.programs = data;
                    var emailAutomationActions = data.emailautomation;
                    var oneTimeActions = data.programactions;
                    if (oneTimeActions.length == 0) {
                        $scope.isOneTimeActionsEmpty = true;
                    }
                    if (emailAutomationActions.length == 0) {
                        $scope.isEmailAutomationActionsEmpty = true;
                    }

                    console.log(JSON.stringify(data));
                    var dateEpoch = data.programdetails.dateOfEvent;
                    $scope.programDate = moment(dateEpoch).format(KGlobalDatePickerFormate);
                    $("#progactdatepicker").val($scope.programDate);
                    $scope.template_status = data.emailautomation;
                    $scope.actionType = "Email";
                    $scope.forward = forward;
                    $scope.hideUntilLoad = true;
                });
            }
        };
        $scope.resetActionForm = function () {
            $("#addactiontitle").val("");
            $("#datepicker").val("");
            $("#timepicker1").val("");


            $scope.ddSelectActionOptions = [
                {
                    text: 'Select',
                    value: '0'
                }, {
                    text: 'Facebook Post',
                    value: 'Facebook'
                }, {
                    text: 'Twitter Post',
                    value: 'Twitter'
                }, {
                    text: 'Email',
                    value: 'Email'
                }, {
                    text: 'Task',
                    value: 'Reminder'
                }
            ];
        };

        $scope.ShowAddAction = function ()
        {
            $scope.resetActionForm();
            $scope.fadeClass = 'fadeClass';
            $scope.fade = true;
            $scope.addAction = true;
        };

        $scope.closeOverlay = function ()
        {
            $scope.fadeClass = '';
            $scope.addAction = false;
            $scope.chooseActionTypeOnChange({"text": "Select", "value": "0"});
            $scope.closePopup();
        };
        $scope.formatDate = function (programDate) {
            var dateArray = programDate.split('-');
            var month = dateArray[1];
            var day = dateArray[0];
            var year = dateArray[2];
            var programEndDate = year + "-" + month + "-" + day;
//            var newDate = moment(programEndDate).format('YYYY-MM-DD');
            return programEndDate;
        };

        $scope.addActionValidation = function (addTitle, datePicker, actionType) {
            var actionTime1 = $("#timepicker1").val();
            if (!addTitle) {
                $scope.addTitle = "";
                $("#addactiontitle").focus();
                return false;
            }
            if (actionType.text === "Select") {
                $scope.actionTypeValidation = true;
                $(".invalidDropdown").css('border-color', '#F85A5A');
                return false;
            }
            if (!datePicker) {
                $("#datepicker").focus();
                $scope.datePicker = "";
                return false;
            }
            if (!actionTime1) {
                $scope.timePickerVal = true;
//                $("#datepicker").focus();
                return false;
            }
            return true;
        };

        $scope.chooseActionTypeOnChange = function (actionValue) {
            $scope.ddSelectAction = actionValue;
            if (actionValue.value) {
                $scope.actionTypeValidation = false;
                $(".invalidDropdown").css('border-color', '#c9c9c9');
            }
        };

        $scope.getNames = function (userName) {
            var user = [];
            user = userName.split(" ");
            return user;
        };
        $scope.changeAssignedTo = function (scheduleId) {

            var assignToDetails = {"scheduleId": scheduleId, "userAssignToId": $scope.ddSelectedUser};
            yourPlanFactory.changeAssigedToPOST(assignToDetails).then(function (data) {
                var userName = data.d.message;
                var user = [];
                user = $scope.getNames(userName);
                $scope.assignedFirstName = user[0];
                $scope.assignedLastName = user[1];
                $scope.assignedToInitialChars = data.d.id;

                $scope.changeUsers = false;
                $scope.getProgramActions('emailautomation');
                $scope.getActionComments(scheduleId);
            });
        };

        $scope.addActionComment = function (scheduleId, comment) {
            if (!comment) {
                growl("comment not added, please add the comment");
                $("#comment").focus();
            } else {
                var commentDetails = {"scheduleId": scheduleId, "comment": comment};
                $scope.getUserDetailsByUserId(scheduleId);
                yourPlanFactory.addActionCommentPOST(commentDetails).then(function (data) {
                    $scope.getActionComments(scheduleId);
                    $("#comment").val("");
                });
            }
        };

        $scope.getActionComments = function (scheduleId) {
            yourPlanFactory.actionCommentsGet(scheduleId).then(function (data) {
                $scope.comments = data.d.details;
//                $scope.userColor=userSortInfo.userColor;
//                $scope.userInitials=userSortInfo.userSortName;
            });
        };

        $scope.removeActionComment = function (scheduleId, commentId) {
            yourPlanFactory.removeActionComment(commentId).then(function (data) {
                growl(eval(JSON.stringify(data.d.operationStatus.messages[0])));
                $scope.getActionComments(scheduleId);
            });
        };

        $scope.closeChangeAssignedToPopup = function () {
            $scope.changeUsers = false;
        };

        $scope.AddAction = function (addTitle, datePicker, timePicker, actionType)
        {
            if ($scope.addActionValidation(addTitle, datePicker, actionType))
            {
                if (!$scope.ddSelectedUser)
                    $scope.ddSelectedUser = "0";
                $scope.timePickerVal = false;
                var actionTime1 = $("#timepicker1").val();
                var actionDateTime1 = datePicker.toLocaleString() + " " + actionTime1.toLocaleString();
                var endDate = $scope.programDate;
                var end = new Date(endDate);
                var fromDate = new Date(actionDateTime1);
                var todayDate = new Date();

                if (fromDate < todayDate) {
                    $scope.dateLesser = true;
                    return false;
                }

                $scope.dateLesser = false;
                var actiondate = "1970-01-01";
                var emaildate = datePicker;
                var currDate = moment(emaildate).format(kGlobalDateFormat);
                var nDate = $scope.programDate;
                var start = moment(nDate);
                var end1 = moment(currDate);
                var days = start.diff(end1, "days");
//                var actionDateTime = $("#timepicker1").val().replace(/ /g, '');
                utilFactory.getEpoch(actiondate, actionTime1).then(function (dateTimeEpoch) {
                    var action = {"title": addTitle, "actiontype": actionType.value,
                        "type": "save", "description": "", "marketingType": $scope.programId,
                        "action_date": dateTimeEpoch, "days": days, "userAssignToId": $scope.ddSelectedUser};
                    companyMarketingProgramFactory.addActionPost(action).then(function (data) {
                        $scope.closeOverlay();
                        $scope.getProgramActions('emailautomation');
                        growl("Action created succesfully");
                    });
                });
            }
        };

        $scope.hideSaveButton = function () {
            $scope.showUpdateBtn = false;
        };
        $scope.showSaveButton = function () {
            $scope.showUpdateBtn = true;
        };

        $scope.closePopup = function () {
            $scope.hideSaveButton();
            $scope.hideReminderSaveButton();
            $scope.reminderSectionClass = '';
            $scope.emailsectionClass = '';
            $scope.fadeClass = '';
//            $location.path("/marketingprogramactions");
        };
        $scope.setTab = function (tabName) {
//            if (tabName === 'actionDetails') {
//                $scope.top_subnav_link_active_actionDetail_Class = 'top-subnav-link-active-detail-Class';
//                $scope.top_subnav_link_active_notesDetail_Class = '';
//                $scope.top_subnav_link_active_savedDetail_Class = '';
//                $scope.generalActions = true;
//                $scope.generalSavedDetails = false;
//                $scope.generalNotes = false;
//            }
//            if (tabName === 'savedDetails') {
//                $scope.top_subnav_link_active_savedDetail_Class = 'top-subnav-link-active-detail-Class';
//                $scope.top_subnav_link_active_actionDetail_Class = '';
//                $scope.top_subnav_link_active_notesDetail_Class = '';
//                $scope.generalSavedDetails = true;
//                $scope.generalActions = false;
//                $scope.generalNotes = false;
//            }
//            if (tabName === 'notes') {
//                $scope.top_subnav_link_active_notesDetail_Class = 'top-subnav-link-active-detail-Class';
//                $scope.top_subnav_link_active_actionDetail_Class = '';
//                $scope.top_subnav_link_active_savedDetail_Class = '';
//                $scope.generalNotes = true;
//                $scope.generalActions = false;
//                $scope.generalSavedDetails = false;
//            }
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

        $scope.hideShowEditDot = function (flag) {
            $scope.editAutomationHint = flag;
        };

        $scope.getRecurringScheduleDetails = function (schedule_id, template_status, till_date, schedule_time, entity_type, schedule_title, schedule_desc, date_status, assignedFirstName, assignedLastName, assignedToInitialChars, days)
        {
            
            $scope.isRecurring = true;
            $scope.savedEmail = false;
            $scope.schedule_id = schedule_id;
            $scope.generalSavedDetails = true;
            $scope.generalNotes = false;
            $scope.generalActions = false;
            $scope.assignedFirstName = assignedFirstName;
            $scope.assignedLastName = assignedLastName;
            $scope.assignedToInitialChars = assignedToInitialChars;
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
            $scope.pushedEmail = false;
            $scope.savedPreheader = 'Saved';
            $scope.savedHeader = getemail();
            $scope.hideShowEditDot(false);
            $scope.recurringScheduleData = {schedule_title: schedule_title, schedule_desc: schedule_desc,
                schedule_id: schedule_id, entities_list_name: "",
                email_template_status: template_status, schedule_type: "Recurring Email",
                marketing_program_name: "", user_marketing_program_id: $scope.programId,
                days: days, entities_selected_time: $filter('date')(schedule_time, "hh:mm a"), entities_subject: "",
                entities_from_name: "", entities_reply_to_email_address: ""};
            yourPlanFactory.scheduledEmailGet(schedule_id).then(function (data) {
                $scope.fadeClass = 'fadeClass';
                $scope.emailsectionClass = 'emailsectionClass';
                $scope.recurringEntitiesDetails = JSON.parse(data.d.details);
                $scope.recurringScheduleData.entities_subject = $scope.recurringEntitiesDetails.subject;
                $scope.recurringScheduleData.entities_list_name = $scope.recurringEntitiesDetails.email_list_name;
                $scope.recurringScheduleData.entities_from_name = $scope.recurringEntitiesDetails.from_name;
                $scope.recurringScheduleData.entities_reply_to_email_address = $scope.recurringEntitiesDetails.reply_to_email_address;
                $scope.recurringScheduleData.days = days;
                $scope.recurringScheduleData.entities_selected_time = $filter('date')(schedule_time, "hh:mm a");
                if ($scope.recurringEntitiesDetails.email_list_name === '0' || $scope.recurringEntitiesDetails.email_list_name == '' || $scope.recurringEntitiesDetails.email_list_name == undefined) {
                    $scope.hideShowEmailList = false;
                } else {
                    $scope.hideShowEmailList = true;
                }

                settingsFactory.getAllPreferencesGet().then(function (footerDetails) {
                    var footerInfo = $scope.getUserFooter(JSON.parse(footerDetails.d.details));
                    //                var iframe = document.getElementById('iframeForRecurringEmail');
                    if ($scope.recurringEntitiesDetails.body) {
                        $scope.savedEmail = true;
                        $scope.savedTemplateHeader = "SAVED EMAIL PREVIEW";
                        $scope.deleteScheduleButton = "Remove Saved Email";
                        $scope.htmlbody = $scope.recurringEntitiesDetails.html_body.replace(/contenteditable="true" /g, 'contenteditable="false"');
                        $('#recurringEmailHtmlBody').empty().append($scope.htmlbody).append(footerInfo);
                        //iframe.contentDocument.body.innerHTML = $scope.htmlbody;
                    } else {
//                        $scope.redirectToEmailAutomation('emailautomation','template','', schedule_id);
                        $scope.savedEmail = false;
                        $scope.actionTypeNoTemplateMessage = "No emails saved to this action.";
                    }
                });
            });
            $scope.getActionComments(schedule_id);
            
            $scope.hideGifImage=true;
            var statsData = {"programId": $scope.programId, "actionId": schedule_id};
            emailFactory.recurringEmailHistoryStatsGet(statsData).then(function (stats) {
                if (stats.d.operationStatus.statusCode !== "DataError") {
                    $scope.hideGifImage=false;
                    $scope.tagsDetails = stats.d.details[0].sendGridStats;
                    $scope.tagerror = "";
                } else {
                    $scope.hideGifImage=false;
                    $scope.tagerror = categoryLoadDelay;
                }
            });
        };

        $scope.showReminderSaveButton = function () {
            $scope.showReminderUpdateBtn = true;
        };

        $scope.hideReminderSaveButton = function () {
            $scope.showReminderUpdateBtn = false;
        };

        $scope.getScheduleDetails = function (schedule_id, template_status, schedule_date, entity_type, schedule_title, schedule_desc, schedule_time, assignedFirstName, assignedLastName, assignedToInitialChars, action_status, days, marketingName)
        {
            if (template_status === "Complete" && entity_type === getemail())
                $scope.savedPreheader = 'Sent';
            else
                $scope.savedPreheader = 'Saved';
            $scope.isRecurring = false;
            $scope.savedEmail = false;
            $scope.schedule_id = schedule_id;
            $scope.generalSavedDetails = true;
            $scope.generalNotes = false;
            $scope.generalActions = false;
            $scope.assignedFirstName = assignedFirstName;
            $scope.assignedLastName = assignedLastName;
            $scope.assignedToInitialChars = assignedToInitialChars;
            $scope.emailsectionClass = 'emailsectionClass';
            $scope.fadeClass = 'fadeClass';
            $scope.action_template_status = template_status;
            $scope.generalActionDetailsHeader = entity_type;
            $scope.scheduledTo = 'POST';
            $scope.setTab('savedDetails');
            $scope.masterActionType = entity_type;
            $scope.savedDetailsAddTemplateButton = "Go to Dashboard";
            $scope.savedDetailsAddTemplateLink = "dashboard";
            $scope.templateApproveButton = "Approve to Be Sent on Schedule";
            $scope.templateDisapproveButton = "Disapprove";
            $scope.savedHeader = 'Post';
            $scope.actionDate = schedule_date;
            $scope.pushedEmail = false;
            var time = $filter('date')(schedule_time, "hh:mm a");
            $("#emaildatetime").val($filter('date')(schedule_date, "MMM dd yyyy"));
            $("#emaildatetime1").val($filter('date')(schedule_date, "MMM dd yyyy"));

            if (entity_type === getnote()) {
                $scope.marketingTab.selectTab(5);
                $scope.reminderSectionClass = 'reminderSectionClass';
                $scope.savedReminderTab = true;
                $scope.isTask = true;
                $scope.setTab('savedReminder');
            }

            $scope.scheduleData = {schedule_title: schedule_title, entities_selected_time: schedule_date,
                schedule_id: schedule_id, schedule_desc: schedule_desc,
                email_template_status: template_status, schedule_type: entity_type,
                marketing_program_name: marketingName, user_marketing_program_id: $scope.programId,
                days: days, schedule_time: time};
            $("#timepickertextbox").val(time);
//            growl(JSON.stringify($scope.scheduleData));
            if (entity_type === getemail()) {
                $scope.scheduledTo = 'SEND';
                $scope.savedHeader = getemail();
                yourPlanFactory.scheduledEmailGet($scope.scheduleData.schedule_id).then(function (data) {

                    settingsFactory.getAllPreferencesGet().then(function (footerDetails) {
                        var footerInfo = $scope.getUserFooter(JSON.parse(footerDetails.d.details));
                        $scope.entitiesdetails = JSON.parse(data.d.details);
                        //                    growl(JSON.stringify($scope.entitiesdetails));
//                      var iframe = document.getElementById('iframeForAction');

                        if (data.d.details != "{}") {
                            $scope.savedEmail = true;
                            $scope.savedTemplateHeader = "SAVED EMAIL PREVIEW";
                            $scope.deleteScheduleButton = "Remove Saved Email";
                            $scope.html_body = $scope.entitiesdetails.html_body.replace(/contenteditable="true" /g, 'contenteditable="false"');
                            $('#emailHtmlBody').empty().append($scope.html_body).append(footerInfo);
//                          iframe.contentDocument.body.innerHTML = $scope.entitiesdetails.body;
                        } else {
                            $scope.savedEmail = false;
                            $scope.actionTypeNoTemplateMessage = "No emails saved to this action.";
                        }
                    });
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

                            var iframe = document.getElementById('iframeForAction');
                            if (JSON.stringify(data.d.details[0]) != "{}") {
                                $scope.entitiesdetails = data.d.details[0];
                                $scope.savedEmail = true;
                                if (entity_type === gettwitter())
                                {
                                    $scope.savedTemplateHeader = "SAVED TWITTER PREVIEW";
                                } else if (entity_type === getfacebook())
                                    $scope.savedTemplateHeader = "SAVED FACEBOOK PREVIEW";
                                $scope.deleteScheduleButton = "Remove Saved Post";

                                iframe.contentDocument.body.innerHTML = htmlData;
                            } else {
                                $scope.savedEmail = false;
                                $scope.actionTypeNoTemplateMessage = "No post saved to this action.";
                            }
                        });
                    });
                });
            }
            $scope.getActionComments(schedule_id);
            
            $scope.hideGifImage=true;
            var statsData = {"programId": $scope.programId, "actionId": schedule_id, "scheduleDateTime": schedule_date};
            emailFactory.emailHistoryStatsGet(statsData).then(function (stats) {
                if (stats.d.operationStatus.statusCode !== "DataError") {
                    $scope.tagsDetails = stats.d.details[0].sendGridStats;
                    $scope.tagerror = "";
                } else {
                    $scope.tagerror = categoryLoadDelay;
                }
                $scope.hideGifImage=false;
            });

        };

        $scope.updateAction = function (scheduleUpdatedData) {
            var actiontype = scheduleUpdatedData.schedule_type;//$("#email_schedule_type").val();
            var schedule_id = scheduleUpdatedData.schedule_id;//$("#email_scheduleid").val();
            var title = scheduleUpdatedData.schedule_title;//$("#email_edit_title").val();

            var actiondate = $("#emaildatetime").val();
            var actionTime1 = "";
            if (actiontype === getnote()) {
               actionTime1 = $("#tasktimepickertextbox").val();
            }
            else{
                actionTime1 = $("#timepickertextbox").val();
            }
            if (!title) {
                $("#email_edit_title").focus();
                return false;
            }
            if (!actiondate) {
                $scope.generalTimeAction = true;

                return false;
            } else {
                var actionDateTime1 = actiondate.toLocaleString() + " " + actionTime1.toLocaleString();
                var fromDate = new Date(actionDateTime1);
                var todayDate = new Date();
                var endDate = $scope.programDate;
                var endDay = new Date(endDate);
                if (fromDate < todayDate) {
                    $scope.dateLesser = true;
                    return false;
                }
//                else if (fromDate > endDay) {
//                    growl("The selected date is greater than program date, please change the date");
//                    return false;
//                }
            }

            var actiondate = "1970-01-01";
            var emaildate = $("#emaildatetime").val();
            var currDate = moment(emaildate).format(kGlobalDateFormat);
            var nDate = $scope.programDate;
            var start = moment(nDate);
            var end = moment(currDate);
            var days = start.diff(end, "days");
//            var actionDateTime = $("#timepickertextbox").val().replace(/ /g, '');
            utilFactory.getEpoch(actiondate, actionTime1).then(function (dateTimeEpoch) {
                var description = scheduleUpdatedData.schedule_desc;
                var action = {
                    "schedule_id": schedule_id.toString(), "type": "update",
                    "title": title, "actiontype": actiontype,
                    "description": description, "action_date": dateTimeEpoch, "days": days.toString()
                };
                yourPlanFactory.addActionPost(action).then(function (data) {
                    $scope.dateLesser = false;
                    $scope.closePopup();
                    $scope.getProgramActions();
                    $scope.getActionComments(schedule_id);
                    growl("Action Saved");
                });
            });
        };

        $scope.Approval = function (entity_id, template_status, entity_type) {
            var approval_type = {"entity_id": entity_id.toString(),
                "template_status": template_status,
                "entity_type": entity_type};

            companyMarketingProgramFactory.approveStatusPost(approval_type).then(function (data) {
                if (data.toString() == "true") {
                    if ($scope.action_template_status == "Template Saved") {
                        $scope.action_template_status = "Approved";
//                        $scope.scheduleData.email_template_status = 'Approved';
                    } else if ($scope.action_template_status == "Complete") {
                        $scope.action_template_status = "No Template";
                    } else if ($scope.action_template_status == "No Template") {
                        $scope.action_template_status = "Complete";
                    } else {
                        $scope.action_template_status = "Template Saved";
//                        $scope.scheduleData.email_template_status = 'Template Saved';
                    }
                    $scope.getProgramActions('emailautomation');
                    growl(templetestatussaved);
                    $scope.getActionComments(entity_id);
                } else {
                    growl(savingrecordproblem, "error");
                }
            });

        };

        $scope.deleteSchedule = function (schedules_to_delete, type, section, isRecurring) {
            if ($scope.action_template_status === 'Approved' && type === "remove") {
                growl(actionAlreadyScheduled);
                $scope.promptHideShow(false);
            } else {
                var message;
                var requestBody;
                var responseMessage;
                if (type === "deleteMultiple") {
                    message = multideleteconfirm;
                    requestBody = {"type": "deleteSelected",
                        "schedule_ids": selected_schedules_to_delete, "entity_type": "null"};
                    responseMessage = multideletesuccess;
                } else if (type === "delete") {
                    message = singledeleteconfirm;
                    requestBody = {"type": "delete",
                        "schedule_ids": schedules_to_delete, "entity_type": section,
                        "isRecurring": isRecurring};
                    responseMessage = singledeletesuccess;
                } else if (type === "remove") {
                    message = removecnfirm;
                    requestBody = {"type": "removetemplate",
                        "schedule_ids": schedules_to_delete, "entity_type": section,
                        "isRecurring": isRecurring};
                    responseMessage = multideletesuccess;
                }
                yourPlanFactory.changeSchedulePost(requestBody).then(function (data) {
                    $scope.closePopup();
                    $scope.getProgramActions('emailautomation');
                    $scope.getActionComments(schedules_to_delete);
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

                $scope.getProgramActions('emailautomation');
            });
        };

        $scope.editFooter = function () {
            $scope.emailFooterPopupDetails = true;
        };

        $scope.hideEmailFooterPopupDetails = function () {
            $scope.emailFooterPopupDetails = false;
        };

//        $scope.getFooterDetails = function () {
//            settingsFactory.getAllPreferencesGet().then(function (data) {
//                $scope.footerDetails = JSON.parse(data.d.details).companyProfile;
//                $scope.company = $scope.footerDetails;
//            });
//        };

        $scope.changeFooterDetails = function (company) {
            if ($scope.validateCompanyAddress(company)) {

                var footerWebsiteUrl = "";
                if (company.websiteUrl)
                    footerWebsiteUrl = company.websiteUrl;
                var footerFacebookUrl = "";
                if (company.facebookUrl)
                    footerFacebookUrl = company.facebookUrl;
                var footerTwitterUrl = "";
                if (company.twitterUrl)
                    footerTwitterUrl = company.twitterUrl;
                var footerInstagramUrl = "";
                if (company.instagramUrl)
                    footerInstagramUrl = company.instagramUrl;
                var footerPopupDetails = {"facebookUrl": footerFacebookUrl, "twitterUrl": footerTwitterUrl, "instagramUrl": footerInstagramUrl, "websiteUrl": footerWebsiteUrl};
                $scope.emailFooterPopupDetails = false;
                settingsFactory.setFooterPost(footerPopupDetails).then(function (data) {
                    $scope.getFooterDetails();
                });

                var companyAddress = {"addressLine1": company.addressLine1, "addressLine2": company.addressLine2, "city": company.city, "state": company.state, "zipcode": company.zipCode, "country": company.country};
                onboardingFactory.saveCompanyAddress(companyAddress).then(function (data) {
                    growl("company Saved.");
                });
            }
        };
        $scope.validateCompanyAddress = function (companyData) {
            if (!companyData.addressLine1) {
                $scope.companyAddressDetails.addressLine1 = "";
                $("#addressLine1").focus();
                return false;
            }
//            else if (!companyData.addressLine2) {
//                $scope.companyAddressDetails.addressLine2 = "";
//                $("#addressLine2").focus();
//                return false;
//            }
            else if (!companyData.city) {
                $scope.companyAddressDetails.city = "";
                $("#city").focus();
                return false;
            } else if (!companyData.state) {
                $scope.companyAddressDetails.state = "";
                $("#state").focus();
                return false;
            } else if (!companyData.zipCode) {
                $scope.companyAddressDetails.zipCode = "";
                $("#zipcode").focus();
                return false;
            } else if (!companyData.country) {
                $scope.companyAddressDetails.country = "";
                $("#country").focus();
                return false;
            }
            return true;
        };

        $scope.emailPreviewOnClick = function () {
            $("#tinymceEditorBody").find("p").removeAttr("style").css("margin", "0px");
            var rendomIframeFilename = "";
            rendomIframeFilename = event.timeStamp;
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $(".emailAutomationFade").show();
                $scope.emailPreviewPopup = true;
                var footerData = JSON.parse(data.d.details);
                if (footerData)
                {
                    $("#fadeEmailAutomationPreview").show();
                    var footer = $scope.userFooter(footerData);
                    var sendData = {
                        htmlString: $('#tinymceEditorBody').html() + footer,
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
                    ;
                }
                ;
            });
        };

        $scope.closeEmailPreviewPopup = function ()
        {
            $scope.emailPreviewPopup = false;
            $(".emailAutomationFade").hide();
        };

        $scope.userFooter = function (footerData) {
            var companyAddress = "";
            if (footerData.companyAddress)
            {
                if (footerData.companyAddress[0].addressLine1) {
                    companyAddress = footerData.companyAddress[0].addressLine1 + "<br/>";
                }
                if (footerData.companyAddress[0].addressLine2) {
                    companyAddress = companyAddress + footerData.companyAddress[0].addressLine2 + "<br/>";
                }
                if (footerData.companyAddress[0].city)
                {
                    companyAddress = companyAddress + footerData.companyAddress[0].city;
                }
                if (footerData.companyAddress[0].state) {
                    companyAddress = companyAddress + ", " + footerData.companyAddress[0].state + "\t\t";
                }
                if (footerData.companyAddress[0].zipCode) {
                    companyAddress = companyAddress + footerData.companyAddress[0].zipCode + "<br/>";
                }
                if (footerData.companyAddress[0].country) {
                    companyAddress = companyAddress + footerData.companyAddress[0].country;
                }
            }

            var returnFooter = "";
            var footer = kGlobalFooterTop;

            var footerFB = kGlobalFooterFB;

            var footerTwitter = kGlobalFooterTwitter;

            var footerWebsite = kGlobalFooterWebsite;

            var footerInstagram = kGlobalFooterInstagram;

            var footerMiddle = kGlobalFooterMiddle;

            var footerAddress = kGlobalFooterAddress;

            var footerClose = kGlobalFooterBottom;

            returnFooter = footer;
            if (footerData.companyProfile) {
                if (footerData.companyProfile.facebookUrl)
                    returnFooter += footerFB.replace("$$$footerFB$$$", footerData.companyProfile.facebookUrl);
                if (footerData.companyProfile.twitterUrl)
                    returnFooter += footerTwitter.replace("$$$footerTwitter$$$", footerData.companyProfile.twitterUrl);
                if (footerData.companyProfile.instagramUrl)
                    returnFooter += footerInstagram.replace("$$$footerInstagram$$$", footerData.companyProfile.instagramUrl);
            }

            returnFooter += footerMiddle;

            if (footerData.companyProfile) {
                if (footerData.companyProfile.websiteUrl)
                    returnFooter += footerWebsite.replace("$$$footerWebsite$$$", footerData.companyProfile.websiteUrl);
            }

            returnFooter += footerAddress.replace("$$$footerAddress$$$", companyAddress);

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


        $scope.getEmailTemplates = function (isTemplateClick) {
            $scope.blockdivheader = false;
            $scope.styledivheader = false;
            $scope.recurringDivheader = true;
            $scope.styletab = "emailSideBar-tab";
            $scope.blocktab = "emailSideBar-tab";
            $scope.recurringtab = "emailSideBar-tab-active";
            $("#emailautomationcontent").hide();
            $("#emlautomeditorcontainer").show();
            marketingRecurringEmailFactory.allRecurringEmailTemplatesGet().then(function (data) {
                $scope.recuring_email_templates = JSON.parse(JSON.stringify(data));
                if (!isTemplateClick && !$scope.editSavedEmail)
                    $scope.showHTMLData($scope.recuring_email_templates[0].html_data, $scope.recuring_email_templates[0].template_id);
            });
            $scope.recurringTemplateOnClick(0);
        };

        $scope.showHTMLData = function (html_data, id) {
            var $iframe = $('.fr-iframe');
            externalContentFactory.layoutEmailModelGet(id, false, 0, true).then(function (data) {
                var emailData = JSON.parse(data.d.details);
                var editorHtml = $('#tinymceEditorBody').html();
                if (editorHtml.contains('id="defaultblock1"'))
                {
                    $("#tinymceEditorBody").empty();
                    $("#tinymceEditorBody").append("<div id=defaultblock1 class=module><div class=view>" + emailData.htmldata + "</div></div");

                } else {
                    $("#tinymceEditorBody").append("<div id=defaultblock1 class=module><div class=view>" + emailData.htmldata + "</div></div");
                }
                $scope.templateId = id;
                if ($scope.companyName.indexOf("Dailey") >= 0) {
                    $scope.launchTinyMceEditorForOnlyImage();
                } else {
                    $scope.launchTinyMceEditor();
                }
                $scope.recurringTemplateOnClick(0);
            });
        };
        $scope.launchTinyMceEditor = function () {
            tinymce.EditorManager.editors = [];
            tinymce.init({
                selector: 'td.mce-content-body',
                width: 400,
                inline: true,
                convert_urls: false,
                plugins: [
                    'advlist custombutton autolink lists link image charmap print preview anchor',
                    'searchreplace visualblocks code fullscreen',
                    'insertdatetime media table contextmenu paste',
                    'template paste textcolor colorpicker textpattern imagetools'
                ],
                toolbar1: 'undo | bold italic | alignleft aligncenter alignright | link forecolor | alignleft aligncenter alignright alignjustify | fontselect fontsizeselect ',
                menubar: false
            });
            $('.innerbg').mouseenter(function (event) {
                $("#colpic").css({position: "absolute", top: event.pageY, left: "20px"}).css(" z-index", 30000).show();
                seldiv = $(this).parents('[bb-bgcolor]');
            });
            $(document).click(function () {
                $("#colpic").hide();
            });
            $('.view').find('table:first').find('td:first').mouseenter(function () {
                $(this).find('table:first').addClass('template-border-Active');
            });
            $('.view').find('table:first').find('td:first').mouseleave(function () {
                $(this).find('table:first').removeClass('template-border-Active');
            }); 
            $('.img_upload').click(function () {
                $scope.getAllCompanyImages();
                $scope.addBlockStyle = false;
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
            });
            $('.img_edit').click(function () {
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
                launchEditor($scope.selectImageId);
            });
            $('.img_link').click(function () {
                $scope.getAllCompanyImages();
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
                $scope.addLinkPopup = true;
            });
            $('td img').mouseenter(function (event) {
                $(this).parent('td').find('div:first').css("opacity", 1);
            });
            $('td div img').mouseleave(function (event) {
                $(this).parent('td').find('div:first').css("opacity", 0);
            });
            $('td div .uploader_wrap').mouseenter(function (event) {
                $(this).parent('td').find('div:first').css("opacity", 1);
            });
            $('td div .uploader_wrap').mouseleave(function (event) {
                $(this).parent('td').find('div:first').css("opacity", 0);
            });

        };
        function launchEditor(id, src) {
            featherEditor.launch({
                image: id,
                url: src,
                cropPresets: [
                    'Custom',
                    'Original',
                    ['680x330', '68:33'],
                    ['350x350', '35:35'],
                    ['310x370', '31:37']
                ]
            });
            return false;
        }
        var featherEditor = new Aviary.Feather({
            apiKey: getAviaryApiKey(),
            apiVersion: 3,
            theme: 'dark', // Check out our new 'light' and 'dark' themes!
            tools: 'all',
            appendTo: '',
            onSave: function (imageID, newURL) {
                var img = document.getElementById(imageID);
                var data = {folderName: "aviary",
                    imageUrl: newURL
                };
                uploadImageFactory.uploadByImageUrlPost(data).then(function (response) {
                    img.src = responseText.link;
                    featherEditor.close();
                });
            },
            onError: function (errorObj) {
                alert(errorObj.message);
            }
        });
        $scope.addLinkToImage = function (link) {
            $("#" + $scope.selectImageId).wrap('<a href=' + link + '></a>');
            $scope.addLinkPopup = false;
        };
        $scope.getAllCompanyImages = function () {
            companyImagesFactory.companyImagesGet().then(function (getData) {
                $scope.datalists = getData.d.details;
                $scope.currentCompanyId = getData.d.details[0].fkCompanyId.companyId;
            });
        };
        $scope.changeTemplateImage = function (imageId) {
            $scope.addBlockStyle = true;
            var imageSrc = $("#" + imageId).attr("src");
            $("#" + $scope.selectImageId).attr("src", imageSrc);
        };
        $scope.showImageUploadPopup = function () {
            $("#filesToUpload").trigger("click");
        };
        $scope.uploadFile = function () {
            imageFactory.saveImagePost(event.target.files[0]).then(function (data) {
                $scope.getAllCompanyImages();

            });
        };
        $scope.launchTinyMceEditorForOnlyImage = function () {
            tinymce.EditorManager.editors = [];
            tinymce.init({
                selector: 'td.mce-content-body',
                extended_valid_elements: 'img[class|id|src|style|border=0|alt|title|hspace|vspace|width|height|max-width|max-height|align|onmouseover|onmouseout|name]',
                width: 400,
                convert_urls: false,
                inline: true,
                plugins: [
                    'advlist autolink lists link image',
                    'media table',
                    'imagetools'
                ],
                toolbar1: 'undo | bold italic | link',
                menubar: false
            });
            $('.innerbg').mouseenter(function (event) {
                $("#colpic").css({position: "absolute", top: event.pageY, left: "20px"}).css(" z-index", 30000).show();
                seldiv = $(this).parents('[bb-bgcolor]');
            });
            $(document).click(function () {
                $("#colpic").hide();
            });
            $('.view').find('table:first').find('td:first').mouseenter(function () {
                $(this).find('table:first').addClass('template-border-Active');
            });
            $('.view').find('table:first').find('td:first').mouseleave(function () {
                $(this).find('table:first').removeClass('template-border-Active');
            });
             $('.img_upload').click(function () {
                $scope.getAllCompanyImages();
                $scope.addBlockStyle = false;
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
            });
            $('.img_edit').click(function () {
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
                launchEditor($scope.selectImageId);
            });
            $('.img_link').click(function () {
                $scope.getAllCompanyImages();
                $scope.selectImageId = $(this).parent('div').next('img').attr('id');
                $scope.addLinkPopup = true;
            });
            $('td img').mouseenter(function (event) {
                $(this).parent('td').find('div:first').css("opacity", 1);
            });
            $('td div img').mouseleave(function (event) {
                $(this).parent('td').find('div:first').css("opacity", 0);
            });
            $('td div .uploader_wrap').mouseenter(function (event) {
                $(this).parent('td').find('div:first').css("opacity", 1);
            });
            $('td div .uploader_wrap').mouseleave(function (event) {
                $(this).parent('td').find('div:first').css("opacity", 0);
            });
        };
        $scope.getFooterDetails = function () {
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $scope.footerDetails = JSON.parse(data.d.details).companyProfile;
                $scope.company = $scope.footerDetails;
            });
        };

        $scope.ddSelectEmailListAutomationData = {
            text: "Please select an email list"
        };

        $scope.showEmailList = function () {
            $scope.ddSelectEmailListAutomationDataOptions = [];

            appSessionFactory.getCompany().then(function (companyObject) {

                emailListFactory.getAllEmailListNames(companyObject.companyId).then(function (data) {
                    $scope.emailLists = data.d.details;

                    var emailAutomationData = $scope.emailLists;
//                        parseData.allEmailListWithNoOfContacts.user;
                    for (var i = 0; i < emailAutomationData.length; i++)
                    {
                        var emailAutomationObject = {};
                        emailAutomationObject["text"] = emailAutomationData[i].emailListName;
                        emailAutomationObject["value"] = emailAutomationData[i].emailListId;
                        $scope.ddSelectEmailListAutomationDataOptions.push(emailAutomationObject);
                    }
                });


            });

        };

        $scope.ddSelectDateAutomationData = {
            text: "Select"
        };
        $scope.getEntityDetails = function () {
            $scope.showEmailList();
            $scope.ddSelectDateAutomationDataOptions = [];
            $scope.automationData = {};
            var days = [];
            for (var i = 1; i <= 31; i++) {
                days.push(i);
                var dateAutomationObject = {};
                dateAutomationObject["text"] = i;
                dateAutomationObject["value"] = i;
                $scope.ddSelectDateAutomationDataOptions.push(dateAutomationObject);
            }
            $scope.days = days;
            $scope.automationData.selectedEmailList = "0";
            $scope.automationData.selectedDay = 1;
            var entity_details = {"entity_id": $scope.entityId.toString()};
            if ($scope.type === 'add') {

                $scope.automationData.date = 'Sun Dec 31 2200';
                $scope.automationEditor = false;
                $scope.emailPreviewPopup = false;
                $scope.ddSelectEmailListAutomationData = {
                    text: "Please select an email list"
                };
                $scope.ddSelectDateAutomationData = {
                    text: "Select"
                };
            } else {
                marketingRecurringEmailFactory.getRecurringEntityPost(entity_details).then(function (data) {
                    $scope.recurringEmailValidation(data);
                    if ($scope.type === "template")
                    {
                        if ($scope.error === 0)
                        {
                            $scope.automationEditor = true;
                            $scope.emailPreviewPopup = false;
                            $scope.entityNoEmailTemplate = false;
                            $scope.templateId = data.recurring_email_template_id;
                        } else {
                            $scope.templateId = data.recurring_email_template_id;
                            $scope.automationEditor = false;
                            $scope.emailPreviewPopup = false;
                            $scope.entityNoEmailTemplate = true;
                        }
                    }
                    if ($scope.type === 'edit') {
                        $scope.automationEditor = false;
                        $scope.emailPreviewPopup = false;
//                        $scope.entityNoEmailTemplate = true;
                        if (data.recurring_email_template_id) {
                            $scope.templateId = data.recurring_email_template_id;
                            $scope.entityNoEmailTemplate = false;
                        } else {
                            $scope.entityNoEmailTemplate = true;
                        }
                    }
//                    $scope.entity_details = data;
                    $scope.automationData.title = data.recurring_email_title;
                    $scope.automationData.description = data.recurring_email_description;
                    $scope.automationData.selectedDay = data.recurring_email_days;
                    $scope.automationData.selectedEmailList = data.recurring_email_email_list_name;
                    $scope.automationData.time = $filter('date')(data.recurring_email_time, "hh:mm a");
                    $scope.automationData.subject = data.recurring_email_subject;
                    $scope.automationData.fromName = data.recurring_email_from_name;
                    $scope.automationData.fromAddress = data.recurring_email_from_address;
                    $scope.automationData.replyAddress = data.recurring_email_reply_to_email_address;
                    $scope.ddSelectDateAutomationData.text = data.recurring_email_days;
                    if (data.recurring_email_email_list_name)
                        $scope.ddSelectEmailListAutomationData.text = data.recurring_email_email_list_name;
                    else
                    {
                        $scope.ddSelectEmailListAutomationData = {
                            text: "Please select an email list"
                        };
                    }

                    //Skips this if recurring action was created from Admin
                    if ($scope.automationData.selectedEmailList) {
                        var emailListDropDownSelect = {"text": $scope.automationData.selectedEmailList, "value": $scope.automationData.selectedEmailList};
                        $scope.emailListOnChange(emailListDropDownSelect);
                    }
                    if (data.recurring_email_days) {
                        var daysDropDownSelect = {"text": data.recurring_email_days, "value": data.recurring_email_days};
                        $scope.setDays(daysDropDownSelect);
                    }
                    console.log(JSON.stringify(data));
                    //TODO Sandeep
                    $scope.froalaHtmlData = data.recurring_email_body;
//                    $('#edit').froalaEditor('html.set', '' + $scope.froalaHtmlData + '');
                    $("#tinymceEditorBody").empty().append($scope.froalaHtmlData);
                    if ($scope.companyName.indexOf("Dailey") >= 0) {
                        $scope.launchTinyMceEditorForOnlyImage();
                    } else {
                        $scope.launchTinyMceEditor();
                    }

                });
            }
        };

        $scope.setDays = function (selectedDay) {
            $scope.emailAutomationDayValidation = false;
            $scope.selectedDay = selectedDay.value;
        };

        $scope.emailListOnChange = function (emailListName) {
            $scope.emailAutomationListValidation = false;
            $scope.emailLists = "";

            emailListFactory.getContactsOfEmailList(emailListName.value).then(function (emailListData) {
                var emailListDetails = emailListData.d.details;
                var emails = "";
                if (emailListDetails != null){
                    for (var i = 0; i < emailListDetails.length; i++) {
                        emails = emailListDetails[i].fkContactId.emailAddress;
                        $scope.emailLists = $scope.emailLists + emails + ",";
                    }
                }else {
                    growl("no email contacts present in the email list, kindly update the email list");
                }
                $scope.emailListName = emailListName.text;
                $scope.automationData.selectedEmailList = $scope.emailListName;
            });
        };        
        $scope.recurringEmailValidation = function (data) {
            $scope.error = 0;

            if (!data.recurring_email_title) {
                $("#recuring_email_title").focus();
                $scope.error++;
            }
            if (!data.recurring_email_description) {
                $("#recuring_description").focus();
                $scope.error++;
            }
            if (data.recurring_email_days === "0" || data.recurring_email_days === null || typeof data.recurring_email_days === 'undefined') {
                if (error === 0) {
                }
                $("#days").focus();
                $scope.error++;
            }
            if (!data.recurring_email_time) {
                $("#timepicker1").focus();
                $scope.error++;
            }
            if (!data.recurring_email_till_date) {
                $("#datepicker").focus();
                $scope.error++;
            }

            if (data.recurring_email_email_list_name === "0" || data.recurring_email_email_list_name === null || typeof data.recurring_email_email_list_name === 'undefined') {
                if (error === 0) {
                }
                $("#emaillist").focus();
                $scope.error++;
            }
            if (data.recurring_email_subject === "" || data.recurring_email_subject === null || typeof data.recurring_email_subject === "undefined") {
                if (error === 0) {
                }
                $("#subject").focus();
                $scope.error++;
            }
            if (data.recurring_email_from_name === "" || data.recurring_email_from_name === null || typeof data.recurring_email_from_name === "undefined") {
                if (error === 0) {
                }
                $("#from_name").focus();
                $scope.error++;
            }
            if (data.recurring_email_from_address === "" || data.recurring_email_from_address === null || typeof data.recurring_email_from_address === "undefined") {
                if (error === 0) {
                }
                $("#from_address").focus();
                $scope.error++;
            }
            if (data.recurring_email_reply_to_email_address === "" || data.recurring_email_reply_to_email_address === null || typeof data.recurring_email_reply_to_email_address === "undefined") {
                if (error === 0) {
                }
                $("#reply_to_address").focus();
                $scope.error++;
            }
            return $scope.error;
        };

        $scope.getUserFooter = function (footerData) {
            var companyAddress = "";
            if (footerData.companyAddress)
            {
                if (footerData.companyAddress[0].addressLine1) {
                    companyAddress = footerData.companyAddress[0].addressLine1 + "<br/>";
                }
                if (footerData.companyAddress[0].addressLine2) {
                    companyAddress = companyAddress + footerData.companyAddress[0].addressLine2 + "<br/>";
                }
                if (footerData.companyAddress[0].city)
                {
                    companyAddress = companyAddress + footerData.companyAddress[0].city;
                }
                if (footerData.companyAddress[0].state) {
                    companyAddress = companyAddress + ", " + footerData.companyAddress[0].state + "\t\t";
                }
                if (footerData.companyAddress[0].zipCode) {
                    companyAddress = companyAddress + footerData.companyAddress[0].zipCode + "<br/>";
                }
                if (footerData.companyAddress[0].country) {
                    companyAddress = companyAddress + footerData.companyAddress[0].country;
                }
            }

            var returnFooter = "";
            var footer = kGlobalFooterTop;

            var footerFB = kGlobalFooterFB;

            var footerTwitter = kGlobalFooterTwitter;

            var footerWebsite = kGlobalFooterWebsite;

            var footerInstagram = kGlobalFooterInstagram;

            var footerMiddle = kGlobalFooterMiddle;

            var footerAddress = kGlobalFooterAddress;

            var footerClose = kGlobalFooterBottom;


            returnFooter = footer;
            if (footerData.companyProfile) {
                if (footerData.companyProfile.facebookUrl)
                    returnFooter += footerFB.replace("$$$footerFB$$$", footerData.companyProfile.facebookUrl);
                if (footerData.companyProfile.twitterUrl)
                    returnFooter += footerTwitter.replace("$$$footerTwitter$$$", footerData.companyProfile.twitterUrl);
                if (footerData.companyProfile.instagramUrl)
                    returnFooter += footerInstagram.replace("$$$footerInstagram$$$", footerData.companyProfile.instagramUrl);
            }
            returnFooter += footerMiddle;
            if (footerData.companyProfile) {
                if (footerData.companyProfile.websiteUrl)
                    returnFooter += footerWebsite.replace("$$$footerWebsite$$$", footerData.companyProfile.websiteUrl);
            }
            returnFooter += footerAddress.replace("$$$footerAddress$$$", companyAddress);

            returnFooter += footerClose;

            return returnFooter;
        };

//        $scope.getUserFooter = function (fb, twitter, website, instagram, address) {
//            var returnFooter = "";
//            var footer = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" height=\"100%\" width=\"100%\" bgcolor=\"#EEEEEE\" style=\"border-collapse:collapse;\"><tr><td valign=\"top\"> <center style=\"width: 100%;\"> <div style=\"max-width: 680px;\"> <!--[if (gte mso 9)|(IE)]> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\" align=\"center\"> <tr> <td> <![endif]--> <!-- Atom Body: BEGIN --> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" bgcolor=\"#EEEEEE\" width=\"100%\" style=\"max-width: 680px;\"> <tr> <td style=\"padding-top:15px;\" class=\"mobile-padding\"> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"100%\" style=\"max-width: 300px; background-color:#inherit\" class=\"mobile-padding\"> <tr>";
//
//            var footerFB = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerFB$$$\"><img src=\"" + getHost() + "images/Facebook_Filled.png" + "\" alt=\"Facebook Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Facebook </td> </tr> </table> </td>";
//
//            var footerTwitter = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerTwitter$$$\"><img src=\"" + getHost() + "images/Twitter_Filled.png" + "\" alt=\"Twitter Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Twitter </td> </tr> </table> </td>";
//
//            var footerWebsite = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerWebsite$$$\"><img src=\"" + getHost() + "images/Website_Filled.png" + "\" alt=\"Website Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Website </td> </tr> </table> </td>";
//
//            var footerInstagram = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerInstagram$$$\"><img src=\"" + getHost() + "images/Insta_Filled.png" + "\" alt=\"Instagram Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Instagram </td> </tr> </table> </td>";
//
//            var footerMiddle = "</tr> </table> </td> </tr>";
//
//            var footerAddress = "<!--HEADER: BEGIN--> <tr> <td style=\"font-family: sans-serif; font-size: 12px; mso-height-rule: exactly; line-height: 120%; text-align:center; color: #555555; padding: 20px 55px 20px 55px;\" class=\"fluid mobile-padding\"> $$$footerAddress$$$ </td> </tr> <!--HEADER: END-->";
//
//            var footerClose = "</table> <!--[if (gte mso 9)|(IE)]> </td> </tr> </table> <![endif]--> </div> </center> </td></tr></table>";
//
//
//            returnFooter = footer;
//            if (fb !== "")
//                returnFooter += footerFB.replace("$$$footerFB$$$", fb);
//            if (twitter !== "" && typeof twitter !== "undefined")
//                returnFooter += footerTwitter.replace("$$$footerTwitter$$$", twitter);
//
//            if (website !== "" && typeof website !== "undefined")
//                returnFooter += footerWebsite.replace("$$$footerWebsite$$$", website);
//
//            if (instagram !== "" && typeof instagram !== "undefined")
//                returnFooter += footerInstagram.replace("$$$footerInstagram$$$", instagram);
//
//            returnFooter += footerMiddle;
//
//            if (address !== "" && typeof address !== "undefined")
//                returnFooter += footerAddress.replace("$$$footerAddress$$$", address);
//
//            returnFooter += footerClose;
//
//            return returnFooter;
//        };


        $scope.recuringActionValidation = function () {
            var recurring_email_title = $scope.automationData.title;
            var recurring_email_description = $scope.automationData.description;
            var schedule_time = $("#timepicker1").val();
            var subject = $scope.automationData.subject;
            var from_name = $scope.automationData.fromName;
            var from_address = $scope.automationData.fromAddress;
            var reply_to_address = $scope.automationData.replyAddress;

            if (!recurring_email_title) {
                $scope.automationTime = false;
                $scope.automationData = {title: "", description: recurring_email_description, time: schedule_time, subject: subject, fromName: from_name, replyAddress: reply_to_address};
                $("#recuring_email_title").focus();
                growl("email title not entered");
                return false;
            }
            if (!recurring_email_description) {
                $scope.automationTime = false;
                $scope.automationData = {title: recurring_email_title, description: "", time: schedule_time, subject: subject, fromName: from_name, replyAddress: reply_to_address};
                $("#recuring_description").focus();
                growl("email description not entered");
                return false;
            }
            if (!$scope.selectedDay) {
                $scope.automationTime = false;
                $scope.emailAutomationDayValidation = true;
                growl("selected day not entered");
                return false;
            }
            if (!$scope.automationData.selectedEmailList) {
                $scope.automationTime = false;
                $scope.emailAutomationListValidation = true;
                growl("emailListName not entered");
                return false;
            }
            if (!schedule_time) {
//                $scope.automationData = {title: recurring_email_title, description: recurring_email_description, time: "", subject: subject};
                $scope.automationTime = true;
                growl("schedule_time not entered");
                return false;
            }
            if (!subject) {
                $scope.automationTime = false;
                $scope.automationData = {title: recurring_email_title, description: recurring_email_description, time: schedule_time, subject: "", fromName: from_name, replyAddress: reply_to_address};
                growl("subject not entered");
                $("#recuring_email_subject").focus();
                return false;
            }
            if (!from_name) {
                $scope.automationTime = false;
                $scope.automationData = {title: recurring_email_title, description: recurring_email_description, time: schedule_time, subject: subject, fromName: "", replyAddress: reply_to_address};
                growl("from name not entered");
                $("#recurring_from_name").focus();
                return false;
            }
            if (!from_address) {
                $scope.automationTime = false;
                $scope.automationData = {title: recurring_email_title, description: recurring_email_description, time: schedule_time, subject: subject, fromName: from_name, fromAddress: "", replyAddress: reply_to_address};
                $("#recurring_from_address").focus();
                return false;
            }
            if (!reply_to_address) {
                $scope.automationTime = false;
                $scope.automationData = {title: recurring_email_title, description: recurring_email_description, time: schedule_time, subject: subject, fromName: from_name, replyAddress: ""};
                growl("reply to address not entered");
                $("#reply_address").focus();
                return false;
            }
            return true;
        };

        $scope.replyAddressValidation = function () {
            var reply_to_address = $scope.automationData.replyAddress;
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            var result = reply_to_address.replace(/\s/g, "").split(/,|;/);
            for (var i = 0; i < result.length; i++) {
                if (!regex.test(result[i])) {
                    $("#reply_address").focus();
                    $scope.validateEmailId = "true" + "'" + result[i] + "'";
                    return false;
                }
            }
            $scope.validateEmailId = false;
            return true;
        };

        $scope.fromAddressEmailValidation = function () {
            $scope.automationData.fromAddress;
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            var result = $scope.automationData.fromAddress.replace(/\s/g, "").split(/,|;/);
            for (var i = 0; i < result.length; i++) {
                if (!regex.test(result[i])) {
                    $("#recurring_from_address").focus();
                    $scope.validateFromEmailAddress = true;
                    return false;
                }
            }
            $scope.validateFromEmailAddress = false;
            return true;
        };

        $scope.addUpdateRecuringAction = function () {
            $("#tinymceEditorBody").find("p").removeAttr("style").css("margin", "0px");
            if ($scope.recuringActionValidation())
            {
                if ($scope.fromAddressEmailValidation())
                {
                    if ($scope.replyAddressValidation())
                    {
                        //                    var days = $scope.automationData.selectedDay;
                        if (!$scope.ddSelectedUser)
                        {
                            $scope.ddSelectedUser = "0";
                        }
                        
                        var days = $scope.selectedDay;
                        var emaillist = $scope.automationData.selectedEmailList;
                        var subject = $scope.automationData.subject;
                        var from_name = $scope.automationData.fromName;
                        var from_address = $scope.automationData.fromAddress;
                        var reply_to_address = $scope.automationData.replyAddress;
                        var recurring_email_title = $scope.automationData.title;
                        var recurring_email_description = $scope.automationData.description;

                        var till_date = '2200-12-31';
                        var newtime = "00:00:00";
                        var schedule_time = $("#timepicker1").val();
                        utilFactory.getEpoch(till_date, newtime).then(function (till_date_epoch) {
                            
                        $scope.froalaHtmlData = $("#tinymceEditorBody").html();
                        if ($scope.type === 'add') {
                            var recurring_action = {
                                "days": $scope.selectedDay.toString(),
                                "emaillist": $scope.emailListName.toString(),
                                "subject": subject,
                                "from_name": from_name,
                                "from_address": from_address,
                                "reply_to_address": reply_to_address,
                                "recurring_email_title": recurring_email_title,
                                "recurring_email_description": recurring_email_description,
                                "till_date_epoch": till_date_epoch,
                                "schedule_time_epoch": schedule_time,
                                "program_id": $scope.programId.toString(),"userAssignToId": $scope.ddSelectedUser
                            };
                            
                            marketingRecurringEmailFactory.addRecurringActionPost(recurring_action).then(function (data) {

                                if (data.d.operationStatus.statusCode === "Success") {
                                    var recurringEntity=JSON.parse(data.d.message);
                                    $scope.redirectToEmailAutomation('emailautomation','template','',recurringEntity.scheduleEntityListId,true);
                                    $scope.automationEditor=true;
                                    $scope.entityNoEmailTemplate=false;
                                    growl("Details saved succesfully.");
                                } else {
                                    growl("Problem saving the record!");
                                }
                            });
                        } else if (($scope.type === 'template') && ($scope.entityNoEmailTemplate === true)) {
                            $(".page-content-container").css('width', '100%');
                            var recurring_action = {
                                "entity_id": $scope.entityId.toString(),
                                "days": days.toString(), "emaillist": emaillist,
                                "subject": subject, "from_name": from_name,
                                "from_address": from_address,
                                "reply_to_address": reply_to_address,
                                "recurring_email_title": recurring_email_title,
                                "recurring_email_description": recurring_email_description,
                                "till_date_epoch": till_date_epoch,
                                "schedule_time_epoch": schedule_time,
                                "program_id": $scope.programId.toString(),"userAssignToId": $scope.ddSelectedUser
                            };
                            marketingRecurringEmailFactory.addupdateRecurringActionPost(recurring_action).then(function (data) {

                                if ((data === true) && ($scope.entityNoEmailTemplate === true)) {
                                    growl("Details saved succesfully.");
                                    $scope.automationEditor = true;
                                    $scope.entityNoEmailTemplate = false;
                                } else {
                                    growl("Problem saving the record!");
                                }
                            });
                        } else if (($scope.type === 'edit') && ($scope.entityNoEmailTemplate === true)) {
                            $(".page-content-container").css('width', '90%');
                            var recurring_action = {
                                "entity_id": $scope.entityId.toString(),
                                "days": days.toString(), "emaillist": emaillist,
                                "subject": subject, "from_name": from_name,
                                "from_address": from_address,
                                "reply_to_address": reply_to_address,
                                "recurring_email_title": recurring_email_title,
                                "recurring_email_description": recurring_email_description,
                                "till_date_epoch": till_date_epoch,
                                "schedule_time_epoch": schedule_time,
                                "program_id": $scope.programId.toString(),"userAssignToId": $scope.ddSelectedUser
                            };

                            marketingRecurringEmailFactory.addupdateRecurringActionPost(recurring_action).then(function (data) {
                                if ((data === true)) {
                                    growl("Details saved succesfully.");
                                    $location.path("/marketingprogramactions");
                                    $scope.getProgramActions('emailautomation');
                                } else {
                                    growl("Problem saving the record!");
                                }
                            });

                        } else if ((($scope.type === 'template') && ($scope.entityNoEmailTemplate === false)) || (($scope.type = 'edit') && ($scope.entityNoEmailTemplate === false))) {
                            $(".page-content-container").css('width', '90%');

                            settingsFactory.getAllPreferencesGet().then(function (data) {
                                var footerData = JSON.parse(data.d.details);

                                if (!footerData.companyAddress) {
                                    $scope.editFooter();
                                    return false;
                                } else
                                {
                                    var footer = $scope.userFooter(footerData);

                                    var recurring_action = {
                                        "entity_id": $scope.entityId.toString(),
                                        "template_id": $scope.templateId, "html_data": $scope.froalaHtmlData + footer,
                                        "html_body": $scope.froalaHtmlData,
                                        "days": days.toString(), "emaillist": emaillist,
                                        "subject": subject, "from_name": from_name,
                                        "from_address": from_address,
                                        "reply_to_address": reply_to_address,
                                        "recurring_email_title": recurring_email_title,
                                        "recurring_email_description": recurring_email_description,
                                        "till_date_epoch": till_date_epoch,
                                        "schedule_time_epoch": schedule_time,
                                        "program_id": $scope.programId.toString(),"userAssignToId": $scope.ddSelectedUser
                                    };
                                    
                                    marketingRecurringEmailFactory.updateRecurringActionPost(recurring_action).then(function (data) {
                                        if ((data === true)) {
                                                growl("Details saved succesfully.");
                                                $location.path("/marketingprogramactions");
                                                $scope.getProgramActions('emailautomation');
                                                //                                        window.open(getHost() + 'user/marketingprogramactions?program_id=' + program_id + '&past=0&program_date=' + program_end_date, "_self");
                                            } else {
                                                growl("Problem saving the record!", "error");
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                }
            }
        };

        $scope.disableArchive = function () {
            $scope.isProgramArchived = true;
        };

        $scope.endMarketingProgram = function () {
            var delconf = confirm("Do you really want to End this Program?");
            if (delconf === true) {
                var programData = {"program_id": parseFloat($scope.programId)};
                companyMarketingProgramFactory.endMarketingProgramPost(programData).then(function (data) {
                    if (data) {
                        $location.path("/marketingprogramlists");
                    } else {
//                        growl(genericError,"error");
                    }
                });
            }
        };
        
        $scope.updateUserProgram = function (programs) {
            if ($scope.validate_program_link_details()) {
                $scope.dateValidation = false;
                $scope.validateLinkName = false;
                $scope.validateLinkUrl = false;
                var program = $scope.programId.toString();
                var program_name = programs.programdetails.programName;
                var event_date = $("#progactdatepicker").val();
                var event_date_epoch = Date.parse(event_date);
                var link_url = programs.programdetails.linktodestination;
                var link_name = programs.programdetails.link_name;

                var program_details = {"program_id": program, "date_of_event": event_date_epoch,
                    "link_url": link_url, "link_name": link_name, "program_name": program_name};
                companyMarketingProgramFactory.updateUserProgramPost(program_details).then(function (data) {
                    if (data) {
                        growl(programdetailssaved);
                    } else {
                        growl(savingrecordproblem,"error");
                    }
                });
            }
            ;
        };

        $scope.validate_program_link_details = function () {
            var myRegExp = /^(?:(?:https?|ftp):\/\/)(?:\S+(?::\S*)?@)?(?:(?!10(?:\.\d{1,3}){3})(?!127(?:\.\d{1,3}){3})(?!169\.254(?:\.\d{1,3}){2})(?!192\.168(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/[^\s]*)?$/i;
            var event_date = $("#progactdatepicker").val();
            var link_url = $("#link_url").val();
            var link_name = $("#link_name").val();

            if (!event_date) {
                $("#progactdatepicker").focus();
                $scope.dateValidation = true;
                return false;
            }
            if ((!link_url) || (!myRegExp.test(link_url))) {
                $("#link_url").focus();
                $("#link_url").val('http://');
                $scope.validateLinkUrl = true;
                return false;
            }
            if (!link_name) {
                $scope.validateLinkUrl = false;
                $("#link_name").focus();
                $scope.validateLinkName = true;
                return false;
            }
            return true;
        };

        $scope.promptHideShow = function (flag) {
            $scope.clickedRemoveAction = flag;
        };

        $scope.saveEmailByActionId = function (scheduleData) {
//            localStorage.setItem("email_Schedule_Id",id);
            appSessionFactory.clearEmail().then(function (checkCleared) {
                appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                    kGlobalEmailObject.entityScheduleId = scheduleData.schedule_id;
                    kGlobalEmailObject.entityScheduleTitle = scheduleData.schedule_title;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                        if (data === true)
                            window.open(getHost() + 'user/baseemaileditor#/emailcategory', "_self");
                    });
                });
            });
        };

        $scope.editSavedEmail = function (scheduleId, entitiesdetails) {
            if ($scope.action_template_status !== 'Approved') {
                appSessionFactory.clearEmail().then(function (checkCleared) {
                    kGlobalEmailObject.entityScheduleId = scheduleId;
                    kGlobalEmailObject.emailScheduleId = entitiesdetails.schedule_email_id;
                    kGlobalEmailObject.emailSubject = entitiesdetails.subject;
                    kGlobalEmailObject.preheader = entitiesdetails.preheader;
                    kGlobalEmailObject.toEmailAddresses = entitiesdetails.to_email_addresses;
                    kGlobalEmailObject.htmlBody = entitiesdetails.body;
                    kGlobalEmailObject.emailListName = entitiesdetails.email_list_name;
                    kGlobalEmailObject.fromName = entitiesdetails.from_name;
                    kGlobalEmailObject.fromAddress = getDefaultEmailId();
                    kGlobalEmailObject.replyToEmailAddress = entitiesdetails.reply_to_email_address;
                    kGlobalEmailObject.htmlBody = entitiesdetails.html_body;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (saved) {
                        if (saved === true)
                            window.open(getHost() + 'user/baseemaileditor#/emailsubjects', "_self");
                    });
                });
            } else {
                growl(actionAlreadyScheduled);
            }
        };
        $scope.showBlocks = function () {
            $scope.styledivheader = false;
            $scope.recurringDivheader = false;
            $scope.blockdivheader = true;
            $scope.styletab = "emailSideBar-tab";
            $scope.recurringtab = "emailSideBar-tab";
            $scope.blocktab = "emailSideBar-tab-active";
            companyFactory.allNonMindbodyBlocksForCompanyGet().then(function (data) {
                $scope.blockLists = data.d.details;
            });
            $scope.recurringTemplateOnClick(0);
        };
        $scope.blockOnClick = function (id) {
            $scope.id = id;
            $scope.id = 'editor-block-slat';
            $scope.setBlockActive = 'editor-block-slat-selected';
            $scope.activeBlock = id;
            $("#stylelist").css("display", "none");
            $("#blklist").css("display", "block");
            $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
            $(":button").removeAttr("disabled");
            $("#styletab").css("background-color", "transparent").css("color", "#19587c");
        };
        $scope.recurringTemplateOnClick = function (id) {
            $scope.id = id;
            $scope.id = 'editor-block-slat';
            $scope.setBlockActive = 'editor-block-slat-selected';
            $scope.activeBlock = id;
            $("#stylelist").css("display", "none");
            $("#blklist").css("display", "block");
            $("#blocktab").css("background-color", "#ffffff").css("color", "#19587c");
            $(":button").removeAttr("disabled");
            $("#styletab").css("background-color", "transparent").css("color", "#19587c");
        };
        $scope.didChooseBlock = function (block) {
            blockModelFactory.allEmailBlockModelGet(block.emailBlockId, true).then(function (data) {
                $scope.firstTemplateForBlock = data.d.details[0].emailBlockModelLookupId;
                $scope.isBlockClicked = "true";
                $scope.htmlBlockId = "";
                $scope.selectedBlockId = block.emailBlockId;
                ++$scope.addBlockCount;
                $scope.htmlTagId = "block" + $scope.addBlockCount;
                if (block.externalSourceKeywordLookupId === 0)
                {
                    $scope.emailMindBodyPopup = false;
                    appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                        kGlobalEmailObject.mindbodyId = "0";
                        appSessionFactory.setEmail(kGlobalEmailObject).then(function () {
                        });
                    });
                    $scope.addHTMLInEmailEditor($scope.firstTemplateForBlock);
                    $scope.loadingOverlay = false;
                    $scope.blockOnClick(0);
                    $scope.showStyles('true');
                } else
                {
                    $scope.hideMindbodyOverlay = false;
                    $scope.emailMindBodyPopup = true;
                    $("#fade").show();
                    $scope.overlayFade = true;
                    $scope.loadingOverlay = true; //start Loading Overlay
                    $scope.emailScrollyDiv = false;
                    externalContentFactory.activatedGet(block.externalSourceKeywordLookupId).then(function (data) {
                        var externalData = JSON.stringify(data.d.details);

                        if (externalData === "[true]") {
                            externalContentFactory.listDataGet(block.externalSourceKeywordLookupId).then(function (listData) {
                                var parseData = JSON.parse(listData.d.details);
                                $scope.mindbodyDataList = parseData;
                                $("#fade").show();
                                $('#slider-button').click();
                                $scope.loadingOverlay = false; //start Loading Overlay
                                $scope.hideMindbodyOverlay = true;
                                $scope.emailScrollyDiv = true;
                                $scope.showStyles();
                            });
                        }
                    });
                }
            });
        };
        $scope.addHTMLInEmailEditor = function (templateId) {
            var mindbodyId = 0;
            appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                if (!kGlobalEmailObject.mindbodyId) {
                } else {
                    mindbodyId = kGlobalEmailObject.mindbodyId;
                }
                externalContentFactory.layoutEmailModelGet(templateId, $scope.isBlockClicked, mindbodyId).then(function (data) {
                    var emailData = JSON.parse(data.d.details);
                    if ($scope.isBlockClicked === "false") {

                        var editorHtml = $('#tinymceEditorBody').html();
                        if (editorHtml.contains('id="defaultblock1"')) {
                            $("#defaultblock1").html('<div class="view">' + emailData.htmldata + '</div>');
                        } else
                        {
                            var styleHtml = '<div id=defaultblock1 class=module onclick="angular.element(this).scope().blockIdOnSelected(defaultblock1,0,' + mindbodyId + ')"><div class="view">' + emailData.htmldata + '</div></div>';
                            $("#tinymceEditorBody").append(styleHtml);
                        }
                        if ($scope.companyName.indexOf("Dailey") >= 0) {
                            $scope.launchTinyMceEditorForOnlyImage();
                        } else {
                            $scope.launchTinyMceEditor();
                        }
                    } else {
                        var editorHtml = $('#tinymceEditorBody').html();
                        if (editorHtml.contains('id="' + $scope.htmlTagId + '"')) {
                            $("#" + $scope.htmlTagId).html('<div class="view">' + emailData.htmldata + '</div>');
                            if ($scope.companyName.indexOf("Dailey") >= 0) {
                                $scope.launchTinyMceEditorForOnlyImage();
                            } else {
                                $scope.launchTinyMceEditor();
                            }
                        } else
                        {
                            var BlockHtml = '<div id=' + $scope.htmlTagId + '  class=module onclick=angular.element(this).scope().blockIdOnSelected(' + $scope.htmlTagId + ',' + $scope.selectedBlockId + ',' + mindbodyId + ')><div class="view">' + emailData.htmldata + '</div></div>';
                            $("#tinymceEditorBody").append(BlockHtml);
                            if ($scope.companyName.indexOf("Dailey") >= 0) {
                                $scope.launchTinyMceEditorForOnlyImage();
                            } else {
                                $scope.launchTinyMceEditor();
                            }
                        }
                    }
                });
            });
        };
        $scope.blockIdOnSelected = function (selectedBlock, blockId, mindbodyId) {
            $scope.htmlTagId = selectedBlock.id;
        };
        $scope.showStyles = function (isClick) {
            if (isClick === "true")
            {
                $scope.blockdivheader = false;
                $scope.recurringDivheader = false;
                $scope.styledivheader = true;
                $scope.blocktab = "emailSideBar-tab";
                $scope.recurringtab = "emailSideBar-tab";
                $scope.styletab = "emailSideBar-tab-active";
            }
            if ($scope.isBlockClicked === "true" || $scope.htmlBlockId !== "defaultblock1")
            {
                blockModelFactory.allEmailBlockModelGet($scope.selectedBlockId, true).then(function (data) {
                    $scope.datalistsstyles = data.d.details;
                });
            }
            $scope.recurringTemplateOnClick(0);
        };       
        $scope.getUserDetailsByUserId = function (userId){
            appSessionFactory.getAllUsersUnderCompany().then(function (KGlobalAllUserUnderCompanyObject){
                for(var i=0; i< KGlobalAllUserUnderCompanyObject.userList.length;i++){
                    if(userId === KGlobalAllUserUnderCompanyObject.userList[i].userId){
                        var userFisetName = KGlobalAllUserUnderCompanyObject.userList[i].firstName;
                        var userLastName = KGlobalAllUserUnderCompanyObject.userList[i].lastName;
                        var userSignature = userFisetName.charAt(0)+ userLastName.charAt(0);
                        userSortInfo.userSortName = userSignature.toUpperCase();
                        userSortInfo.userColor = KGlobalAllUserUnderCompanyObject.userList[i].userColor;
                    }
                }
                $scope.userColor=userSortInfo.userColor;
                $scope.userInitials=userSortInfo.userSortName;
            });
        };
        $scope.hidePopup = function (popupName) {
            if (popupName === "sendOrSchedulePopup") {
                $scope.postTypeSelectionPopUp = false;
            } else if (popupName === "schedulePopup") {
                $scope.schedulePopup = false;
            } else if (popupName === "addlinkpopup") {
                $scope.addLinkPopup = false;
            }
        };

    }]);