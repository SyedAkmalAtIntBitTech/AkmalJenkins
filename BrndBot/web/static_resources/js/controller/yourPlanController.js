
yourPlanFlowApp.controller("yourPlanController", ['$scope', '$location', '$filter', 'yourPlanFactory', 'companyFactory', 'settingsFactory', 'companyMarketingProgramFactory', 'appSessionFactory', 'onboardingFactory', 'activityFactory', 'utilFactory', 'emailFactory', function ($scope, $location, $filter, yourPlanFactory, companyFactory, settingsFactory, companyMarketingProgramFactory, appSessionFactory, onboardingFactory, activityFactory, utilFactory, emailFactory) {

//$scope.iframeLoad = function (){
//    growl($('iframe').contents().find('body').height());
//    $('#iframeForAction').height($('iframe').contents().height());
//};

        $scope.emailsectionClass = '';
        $scope.fadeClass = '';
        $scope.fadeClasses = '';
        $scope.entities_selected_time = "";
        $scope.masterActionType = "";
        $scope.master_facebook = getfacebook();
        $scope.master_twitter = gettwitter();
        $scope.master_email = getemail();
        $scope.master_note = getnote();
        $scope.savedEmail = false;
        $scope.schedule_id = '';
        $scope.ddSelectedUser = '0';
        $scope.isRecurring = false;
        $scope.actionNameValidation = actionNameValidation;
        $scope.actionDropdownValidation = actionDropdownValidation;
        $scope.actionDateValidation = actionDateValidation;
        $scope.actionTimeValidation = actionTimeValidation;
        $scope.lesserDateValidation = lesserDateValidation;
        $scope.calculatedProgramDate = "";
        $scope.actionTypeValidation = false;
        $scope.dateLesser = false;
        $scope.timePickerVal = false;
        $scope.clickedRemoveAction = false;
        $scope.addUserSettings = false;
        $scope.moreThanOneUser = false;
        $scope.isTask = true;
        $scope.isCurrentCompanyInFranchise = false;
        $scope.isCurrentCompanyAFranchiseHeadquarter = false;
        $scope.userColor = "";
        $scope.userAssignmentPopUp = false;
//        $scope.userInitials = "";
        var userSortInfo = {userSortName: "", userColor: ""};

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

        $scope.isDeletePromptOpen = function (flag) {
            $scope.clickedDeleteAction = flag;
        };

//        $scope.changeUsers = false;
        $scope.footerData = "";
        $scope.ddSelectUserOptions = [{
                text: 'Select',
                value: '0'
            }
        ];

//        $scope.openChangeUserDD = function (flag) {
//            $scope.changeUsers = flag;
//        };

        $scope.ddSelectUser = {text: "Select"};


        $scope.chooseUserOnChange = function (actionValue) {
            $scope.ddSelectedUser = actionValue.value;
            
            
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

        $scope.ddSelectAction = {text: "Select"};

        $scope.showCompanyList = function () {
            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                kGlobalCompanyObject.userHashId = "";
                appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {
                });
            });
            window.location = getHost() + "user/loading";
        };
        // use scope.onPikadaySelect for older scope syntax
        $scope.onPikadaySelect = function onPikadaySelect(pikaday) {
            growl(pikaday.toString());
        };

        $scope.showCompanyList = function () {
            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                kGlobalCompanyObject.userHashId = "";
                appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {
                });
            });
            window.location = getHost() + "user/loading";
        };

        var user_selected_date = '';
        var picker = new Pikaday(
                {
                    field: document.getElementById('jumptodatepicker'),
                    firstDay: 1,
                    format: KGlobalDatePickerFormate,
                    minDate: new Date('2000-01-01'),
                    maxDate: new Date('2050-12-31'),
                    yearRange: [2000, 2050],
                    onSelect: function () {
                        var mydate = this.getMoment();
                        var mydt = mydate.toLocaleString();
                        var myDate = new Date(mydt);
                        $scope.setCurrentDate(myDate);
                    }
                });

        $scope.setCurrentDateActions = function () {
        };

        $scope.setCurrentDate = function (selected_date) {
//            $(".delete-button").hide();
            $("#liPriority").show();
            user_selected_date = selected_date;
            $scope.getCampaigns();
        };
        function addDays(theDate, days) {
            return new Date(theDate.getTime() + days * 24 * 60 * 60 * 1000);
        }

        $scope.getUserDetails = function () {
            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                $scope.companyName = kGlobalCompanyObject.companyName;
                $scope.userFirstName = kGlobalCompanyObject.userFirstName;
                $scope.userLastName = kGlobalCompanyObject.userLastName;

                $scope.getUserDetailsByUserId(kGlobalCompanyObject.userId);
                kGlobalCompanyObject.userHashId = '';
                appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {
                });
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


        $scope.getCampaigns = function () {
            var curr_date = '';
            var tomorrowDate = '';
            var new_date = '';
            if (user_selected_date !== "") {
                curr_date = moment(user_selected_date).format(kGlobalDateFormat);
                tomorrowDate = moment(addDays(user_selected_date, 1)).format(kGlobalDateFormat);
                new_date = moment(addDays(user_selected_date, 15)).format(kGlobalDateFormat);
            } else {
                curr_date = moment(new Date()).format(kGlobalDateFormat);
                tomorrowDate = moment(addDays(new Date(), 1)).format(kGlobalDateFormat);
                new_date = moment(addDays(new Date(), 15)).format(kGlobalDateFormat);
            }
            latest_date = curr_date;
            var invalid = "Invalid date";
            yourPlanFactory.scheduledEntitiesGet(curr_date, new_date).then(function (data) {

                var parseJSON = JSON.parse(data.d.details);
                $scope.entityS = JSON.parse(JSON.stringify(parseJSON));
                $scope.today_date = moment(new Date()).format(kGlobalDateFormat);
                $scope.tomorrow_date = moment($scope.addDays(new Date(), 1)).format(kGlobalDateFormat);
                $scope.entitySet = parseJSON.entitydata;
                $scope.nodata = parseJSON.noactionsmessage;

            });
        };
        $scope.addDays = function (theDate, days) {

            return new Date(theDate.getTime() + days * 24 * 60 * 60 * 1000);
        };
        $scope.mySplit = function (string, nb) {
            var array = string.split('-');
            return array[nb];
        };
        function formatDate(myDate) {
            var abbrMonths = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
            var abbrDays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

            function zeroPadding(val) {
                return val.toString().length === 1 ? "0" + val : val;
            }

//            return abbrDays[myDate.getDay()] + ", " + myDate.getDate() + " " + (abbrMonths[myDate.getMonth()]) +
//                " " + myDate.getFullYear() + ", " + zeroPadding(myDate.getHours()) + ":" +
//                zeroPadding(myDate.getMinutes()) + ":" + zeroPadding(myDate.getSeconds());
            return abbrDays[myDate.getDay()] + ", " + myDate.getDate() + " " + (abbrMonths[myDate.getMonth()]) +
                    " " + myDate.getFullYear();
        }

        $scope.getDayOfMonth = function (date) {
            var weekday = new Array(7);
            weekday[0] = "Sunday";
            weekday[1] = "Monday";
            weekday[2] = "Tuesday";
            weekday[3] = "Wednesday";
            weekday[4] = "Thursday";
            weekday[5] = "Friday";
            weekday[6] = "Saturday";
            var dayName = new Date(date + " " + "11:13:00");
            return weekday[dayName.getDay()];

        }

        $scope.myDay = function (string) {
            var n_date = new Date(string);
            var formattedDate = formatDate(n_date);
            return formattedDate;
        };

        $scope.myYear = function (string) {
            var month = "";
            if (string === '01')
                return month = "Jan";
            if (string === '02')
                return month = "Feb";
            if (string === '03')
                return month = "Mar";
            if (string === '04')
                return month = "Apr";
            if (string === '05')
                return month = "May";
            if (string === '06')
                return month = "Jun";
            if (string === '07')
                return month = "Jul";
            if (string === '08')
                return month = "Aug";
            if (string === '09')
                return month = "Sept";
            if (string === '10')
                return month = "Oct";
            if (string === '11')
                return month = "Nov";
            if (string === '12')
                return month = "Dec";
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
        $scope.chooseActionTypeOnChange = function (actionValue) {
            $scope.ddSelectAction = actionValue;
            if (actionValue.value) {
                $scope.actionTypeValidation = false;
                $(".invalidDropdown").css('border-color', '#c9c9c9');
            }
        };

        $scope.ShowAddAction = function ()
        {
            $scope.resetActionForm();

//            $scope.datePicker = "";
            $scope.fadeClass = 'fadeClass';
            $scope.isYourplan = true;
            $scope.addAction = true;
        };

        $scope.showInviteUsersPopup = function () {
            $scope.fadeClasses = 'fadeClasses';
            $scope.addUserSettings = true;
        };

        $scope.closeInviteUsersPopup = function () {
            $scope.addUserSettings = false;
            $scope.fadeClasses = '';
        };

        $scope.closeOverlay = function ()
        {
            $scope.fadeClass = '';
            $scope.addAction = false;
            $scope.chooseActionTypeOnChange({"text": "Select", "value": "0"});
            $scope.closePopup();
            $scope.isDeletePromptOpen(false);
        };

        $scope.inviteUser = function (userDetails) {
            if (!userDetails) {
                growl(noEmailAndRole);
            } else if (!userDetails.email) {
                growl(noEmail);
            } else if (!userDetails.adminRadio) {
                growl(noRole);
            } else {
                var roles = [];
                roles.push(userDetails.adminRadio);
                var invitation = {"userRoleLookUpId": "", "emailaddress": userDetails.email, "roles": roles, "task": 'invitation'};

                onboardingFactory.inviteUserPost(invitation).then(function (data) {
                    growl(data.d.message);
                    $scope.closeInviteUsersPopup();
                    //                $location.path("/settings/useraccountsettings");
                });
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
                $scope.getCampaigns();
                $scope.getActionComments(scheduleId);
            });
        };

        $scope.ChangeUserOnChange = function (changedValue) {
            $scope.ddSelectedUser = changedValue.value;
            $scope.changeAssignedTo($scope.schedule_id);
//                $scope.openChangeUserDD(false);
////            $scope.changeUsers = false;
        };

        $scope.addActionComment = function (scheduleId, comment) {
            if (!comment) {
                alert("comment not added, please add the comment");
                $("#comment").focus();
            } else {

                appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                    $scope.companyName = kGlobalCompanyObject.companyName;
                    $scope.userFirstName = kGlobalCompanyObject.userFirstName;
                    $scope.userLastName = kGlobalCompanyObject.userLastName;

                    var commentDetails = {"scheduleId": scheduleId, "comment": comment};
                    $scope.getUserDetailsByUserId(kGlobalCompanyObject.userId);
                    yourPlanFactory.addActionCommentPOST(commentDetails).then(function (data) {
                        $scope.getActionComments(scheduleId);
                    });
                });
            }
        };

        $scope.getActionComments = function (scheduleId) {
            yourPlanFactory.actionCommentsGet(scheduleId).then(function (data) {
                $scope.comments = data.d.details;
            });
        };

        $scope.removeActionComment = function (scheduleId, commentId) {
            yourPlanFactory.removeActionComment(commentId).then(function (data) {
                growl(eval(JSON.stringify(data.d.operationStatus.messages[0])));
                $scope.getActionComments(scheduleId);
            });
        };

        $scope.formatDate = function (programDate) {
            var dateArray = programDate.split('-');
            var month = dateArray[1];
            var day = dateArray[0];
            var year = dateArray[2];
            var programEndDate = year + "-" + month + "-" + day;
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
                $scope.datePicker = "";
                $("#datepicker").focus();
                return false;
            }
            if (!actionTime1) {
                $scope.timePickerVal = true;
//                $("#datepicker").focus();
                return false;
            }
            return true;
        };

        $scope.getAllUsersInCompany = function () {
            yourPlanFactory.allUsersInCompanyGet().then(function (data) {
//                $scope.allUsers = data.d.details;
//                $scope.ddSelectUserOptions.push({"text": "None", "value": 0});
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
        $scope.getActivityLog = function () {
            activityFactory.activitiesGet().then(function (data) {
                $scope.activityLog = data.d.details;
            });
        };
        $scope.AddAction = function (addTitle, datePicker, timePicker, actionType) {
            if ($scope.addActionValidation(addTitle, datePicker, actionType))
            {
                if (!$scope.ddSelectedUser)
                    $scope.ddSelectedUser = "0";
                $scope.timePickerVal = false;
                var actionTime1 = $("#timepicker1").val();
                var actionDateTime1 = datePicker.toLocaleString() + " " + actionTime1.toLocaleString();
                var fromDate = new Date(actionDateTime1);
                var todayDate = new Date();

                if (fromDate < todayDate) {
                    $scope.dateLesser = true;
                    return false;
                }

                $scope.dateLesser = false;
                var actiondate = datePicker;
                utilFactory.getEpoch(actiondate, actionTime1).then(function (dateTimeEpoch) {
                    var days = 0;
                    var action = {"title": addTitle, "actiontype": actionType.value, "type": "save",
                        "description": "", "marketingType": 0, "action_date": dateTimeEpoch, "days": days, "userAssignToId": $scope.ddSelectedUser};
                    yourPlanFactory.addActionPost(action).then(function (data) {
                        growl("Action Saved");
                        $scope.getCampaigns();
                        $scope.closeOverlay();
                    });
                });
            }
        };

        $scope.closePopup = function () {
            $scope.sentEmailDetailsPopupClass = '';
            $scope.reminderSectionClass = '';
            $scope.emailsectionClass = '';
            $scope.fadeClass = '';
            $scope.hideSaveButton();
            $scope.hideReminderSaveButton();
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
//            if (tabName === 'reminderDetails') {
//                $scope.top_subnav_link_active_reminderDetail_Class = 'top-subnav-link-active-detail-Class';
//                $scope.top_subnav_link_active_savedReminder_Class = '';
//                $scope.reminderDetailsTab = true;
//                $scope.savedReminderTab = false;
//            }
//            if (tabName === 'savedReminder') {
//                $scope.top_subnav_link_active_savedReminder_Class = 'top-subnav-link-active-detail-Class';
//                $scope.top_subnav_link_active_reminderDetail_Class = '';
//                $scope.savedReminderTab = true;
//                $scope.reminderDetailsTab = false;
//            }
        };
        $scope.hideSaveButton = function () {
            $scope.showUpdateBtn = false;
        };

        $scope.hideReminderSaveButton = function () {
            $scope.showReminderUpdateBtn = false;
        };
        $scope.showReminderSaveButton = function () {
            $scope.showReminderUpdateBtn = true;
        };
        $scope.showSaveButton = function () {
            $scope.showUpdateBtn = true;
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

        $scope.globalScheduleData = {};
        $scope.getScheduleDetails = function (schedule_id, template_status, schedule_time, entity_type, assignedFirstName, assignedLastName, assignedToInitialChars, schedule_title, schedule_desc, marketingName, programId, days, is_today_active, action_date)
        {
            if (template_status === "Complete" && entity_type === getemail())
                $scope.savedPreheader = 'Sent';
            else
                $scope.savedPreheader = 'Saved';
            $scope.dateLesser = false;
//        $scope.entities_selected_time =schedule_time;
            var nDate = new Date(action_date + " 10:30 am"); //10:30 am save DST
            $scope.calculatedProgramDate = $scope.addDays(nDate, days);
            $scope.entities_selected_time = $filter('date')(schedule_time, kGlobalDateFormat);
            $scope.savedEmail = false;
            $scope.schedule_id = schedule_id;
            $scope.assignedFirstName = assignedFirstName;
            $scope.assignedLastName = assignedLastName;
            $scope.assignedToInitialChars = assignedToInitialChars;
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
            $scope.templateApproveButton = "Approve";
            $scope.templateDisapproveButton = "Disapprove";
            $scope.savedDetailsAddTemplateButton = "Go to Dashboard";
            $scope.savedDetailsAddTemplateLink = "dashboard";
            $scope.setEmailToThisAction = "Save Email to this Action";
            $scope.savedHeader = 'Post';
            $scope.isRecurring = false;
            $scope.pushedEmail = false;
            if (entity_type === getnote()) {
                $scope.yourplanTab.selectTab(5);
                $scope.reminderSectionClass = 'reminderSectionClass';
                $scope.savedReminderTab = true;
                $scope.isTask = true;
                $scope.setTab('savedReminder');
                $scope.getActionComments(schedule_id)
            }
            var date = $scope.entities_selected_time;
            var time = $filter('date')(schedule_time, "hh:mm a");
            $("#emaildatetime").val(moment(action_date, "YYYY-MM-DD").format("MMM DD YYYY"));
            $("#emaildatetime1").val(moment(action_date, "YYYY-MM-DD").format("MMM DD YYYY"));
            $scope.scheduleData = {schedule_title: schedule_title, entities_selected_time: date,
                schedule_id: schedule_id, schedule_desc: schedule_desc,
                email_template_status: template_status, schedule_type: entity_type,
                marketing_program_name: marketingName, user_marketing_program_id: programId,
                days: days, is_today_active: is_today_active, schedule_time: time};
//                $('#emailcontentiframe').contents().find('html').html(data.body); 
            $("#timepickertextbox").val(time);
            $scope.globalScheduleData = $scope.scheduleData;

            if (entity_type === getemail()) {
                $scope.scheduledTo = 'SEND';
                $scope.savedHeader = getemail();
                yourPlanFactory.scheduledEmailGet($scope.scheduleData.schedule_id).then(function (data) {

                    settingsFactory.getAllPreferencesGet().then(function (footerDetails) {
                        var footerData = JSON.parse(footerDetails.d.details);
                        var footerInfo = $scope.getUserFooter(footerData);
                        $scope.entitiesdetails = JSON.parse(data.d.details);
                        //                  var iframe = document.getElementById('iframeForAction');

                        if (data.d.details != "{}") {
                            $scope.savedEmail = true;
                            $scope.savedTemplateHeader = "SAVED EMAIL PREVIEW";
                            $scope.deleteScheduleButton = "Remove Saved Email";
                            $scope.html_body = $scope.entitiesdetails.html_body.replace(/contenteditable="true" /g, 'contenteditable="false"');
                            $('#emailHtmlBody').empty().append($scope.html_body).append(footerInfo);
                            //                      iframe.contentDocument.body.innerHTML = $scope.entitiesdetails.body;
                            //                      $scope.iframeLoad();
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
                            $scope.entitiesdetails = data.d.details[0];
                            var iframe = document.getElementById('iframeForAction');
//                iframe.contentDocument.head.appendChild = ;

                            if (JSON.stringify(data.d.details[0]) != "{}") {
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
//                                    htmlData += "<div class=\"twitter-post-preview\">\n"
//                                            + "    <div class=\"Facebook-preview-header\">\n"
//                                            + "        <div class=\"Facebook-preview-profpic\">\n"
//                                            + "            <img id=\"twitter_preview_profpic\" src=\"/BrndBot/downloadImage?imageType=COMPANY_LOGO&amp;companyId=" + $scope.companyId + "&amp;imageName=companylogo.png\">\n"
//                                            + "        </div>\n"
//                                            + "        <div class=\"Twitter-preview-name-container\">\n"
//                                            + "            <div class=\"Facebook-preview-name ng-binding\"><strong>" + $scope.twitterprofileName + "</strong><br>" + $scope.entitiesdetails.metadata.text + " " + $scope.entitiesdetails.metadata.shorturl + "</div>\n"
//                                            + "        </div>\n"
//                                            + "    </div>\n";
////                    if($scope.entitiesdetails.metadata.shorturl) {
////                    htmlData += "    <div class=\"Facebook-preview-usercontent ng-binding\">"+$scope.entitiesdetails.metadata.shorturl+"</div>\n"
////                    }
//                                    htmlData += "    <div class=\"Facebook-link-container\">\n"
//                                            + "        <div ng-show=\"entitiesdetails.image_type == 'gallery'\">\n"
//                                            + "            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageName=" + $scope.entitiesdetails.image_name + "&imageType=GALLERY&companyId=" + $scope.companyId + "\">\n"
//                                            + "        </div>\n"
//                                            + "    </div>\n"
//                                            + "</div>";
                                } else if (entity_type === getfacebook()) {
//                                    htmlData += "<div class=\"Facebook-preview\">\n"
//                                            + "                                <div class=\"Facebook-preview-header\">\n"
//                                            + "                                    <div class=\"Facebook-preview-profpic\"><img id=\"fb_preview_profpic\" src=\"/BrndBot/downloadImage?imageType=COMPANY_LOGO&amp;companyId=" + $scope.companyId + "&amp;imageName=companylogo.png\"></div>\n"
//                                            + "                                    <div class=\"Facebook-preview-name-container\">\n"
//                                            + "                                        <div class=\"Facebook-preview-name ng-binding\">" + $scope.entitiesdetails.metadata.ManagedPage + "</div>\n"
//                                            + "                                    </div>\n"
//                                            + "                                </div>\n"
//                                            + "                                <div class=\"Facebook-preview-usercontent ng-binding\">" + $scope.entitiesdetails.metadata.post_text + "</div>\n"
//                                            + "                                <div class=\"Facebook-link-container\">\n"
//                                            + "                                    <div class=\"Facebook-preview-image\">\n"
//                                            + "                                        <div ng-show=\"entitiesdetails.image_type == 'gallery'\">\n"
//                                            + "                                            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageType=GALLERY&amp;imageName=" + $scope.entitiesdetails.image_name + "&amp;companyId=" + $scope.companyId + "\">\n"
//                                            + "                                        </div>\n"
//                                            + "                                        <div ng-show=\"entitiesdetails.image_type == 'layout'\" style=\"display: none;\">\n"
//                                            + "                                            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageType=LAYOUT_IMAGES&amp;imageName=13.jpg\">\n"
//                                            + "                                        </div>\n"
//                                            + "                                        <div ng-show=\"entitiesdetails.image_type == 'url'\" style=\"display: none;\">\n"
//                                            + "                                            <img id=\"prevfbimg\" src=\"13.jpg\">\n"
//                                            + "                                        </div>\n"
//                                            + "                                        \n"
//                                            + "                                    </div>\n"
//                                            + "                                    <div class=\"Facebook-preview-link-container\">\n"
//                                            + "                                        <div class=\"Facebook-preview-link-title ng-binding\">" + $scope.entitiesdetails.metadata.title + "</div>\n"
//                                            + "                                        <div class=\"Facebook-preview-link-description ng-binding\">" + $scope.entitiesdetails.metadata.description + "</div>\n"
//                                            + "                                        <div class=\"Facebook-preview-link-url ng-binding\">" + $scope.entitiesdetails.metadata.url + "</div>\n"
//                                            + "                                    </div>\n"
//                                            + "                                </div>\n"
//                                            + "                            </div>";
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
//            growl($scope.isRecurring);
            $scope.hideGifImage = true;
            if (template_status === "Complete" && entity_type === getemail()) {
                utilFactory.getEpoch(moment(action_date, "YYYY-MM-DD").format("MMM DD YYYY"), "12:00 AM").then(function (dateTimeEpoch) {
                    var statsData = {"programId": programId, "actionId": schedule_id, "scheduleDateTime": dateTimeEpoch};
                    emailFactory.emailHistoryStatsGet(statsData).then(function (stats) {
                        if (stats.d.operationStatus.statusCode !== "DataError") {
                            $scope.tagsDetails = stats.d.details[0].sendGridStats;
                            $scope.tagerror = "";
                        } else {
                            $scope.tagerror = categoryLoadDelay;
                        }
                        $scope.hideGifImage = false;
                    });
                });
            }
        };


        $scope.updateAction = function (scheduleUpdatedData) {
            var actiontype = scheduleUpdatedData.schedule_type;//$("#email_schedule_type").val();
            var schedule_id = scheduleUpdatedData.schedule_id;//$("#email_scheduleid").val();
            var title = scheduleUpdatedData.schedule_title;//$("#email_edit_title").val();
            var actiondate = "1970/01/01";
            var actionTime1 = "";
            if (actiontype === getnote()) {
                actionTime1 = $("#tasktimepickertextbox").val();
            } else {
                actionTime1 = $("#timepickertextbox").val();
            }
            var days = 0;
            if (scheduleUpdatedData.marketing_program_name === 'General') {
                actiondate = $("#emaildatetime").val();
                if (!title) {
//                    $scope.scheduleData = {schedule_title : ""};
                    $("#email_edit_title").focus();
                    return false;
                }
                if (!actiondate) {
                    $scope.generalTimeAction = true;
                    return false;
                } else {
                    var actionDateTime2 = actiondate.toLocaleString() + " " + actionTime1.toLocaleString();
                    var fromDate = new Date(actionDateTime2);
                    var todayDate = new Date();
                    if (fromDate < todayDate) {
                        $scope.dateLesser = true;
                        return false;
                    }
                }
            } else {
                var actiondate2 = $("#emaildatetime").val();

                if (!title) {
                    $("#email_edit_title").focus();
                    return false;
                }
                if (!actiondate2) {
                    $scope.generalTimeAction = true;
                    return false;
                } else {
                    var actionDateTime1 = actiondate2.toLocaleString() + " " + actionTime1.toLocaleString();
                    var fromDate = new Date(actionDateTime1);
                    var todayDate = new Date();
//                    var currDate = moment(emaildate).format('YYYY-MM-DD');
                    var endDay = new Date($scope.calculatedProgramDate);
                    if (fromDate < todayDate) {
                        $scope.dateLesser = true;
                        return false;
                    }
//                        else if (fromDate > endDay) {
//                            growl("The selected date is greater than program date, please change the date");
//                            return false;
//                        }
                }
                var emaildate = $("#emaildatetime").val();
                var currDate = moment(emaildate).format(kGlobalDateFormat);
                var nDate = moment($scope.calculatedProgramDate).format(kGlobalDateFormat);
                var start = moment(nDate);
                var end = moment(currDate);
                days = start.diff(end, "days");
            }
//            var actionDateTime = $("#timepickertextbox").val().replace(/ /g, '');
            utilFactory.getEpoch(actiondate, actionTime1).then(function (dateTimeEpoch) {
                var description = scheduleUpdatedData.schedule_desc;
//        if (!validateemailaction()) {
                var action = {
                    "schedule_id": schedule_id.toString(), "type": "update",
                    "title": title, "actiontype": actiontype,
                    "description": description, "action_date": dateTimeEpoch, "days": days.toString()
                };
                yourPlanFactory.addActionPost(action).then(function (data) {
                    $scope.dateLesser = false;
                    $scope.getActionComments(schedule_id);
                    $scope.closePopup();
                    $scope.getCampaigns();
                    growl("Action Saved");
                });
            });
//        }
        };

        $scope.updateActionNote = function (scheduleId) {

            var actiontype = getemail();
            var action = {
                "schedule_id": scheduleId.toString(), "type": "updatenotesyourplan", "actiontype": actiontype,
                "description": $scope.scheduleData.schedule_desc
            };
            yourPlanFactory.addActionPost(action).then(function (data) {
                $scope.getCampaigns();
            });
        };

        $scope.updateReminderDescription = function (schedule_id) {
            var actiontype = getnote();
            var reminderAction = {
                "schedule_id": schedule_id, "type": "updatenotes", "actiontype": actiontype,
                "description": $scope.scheduleData.schedule_desc
            };
            yourPlanFactory.addActionPost(reminderAction).then(function (data) {
                $scope.getCampaigns();
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
                        $scope.scheduleData.email_template_status = 'Approved';
                    } else if ($scope.action_template_status == "Complete") {
                        $scope.action_template_status = "No Template";
                    } else if ($scope.action_template_status == "No Template") {
                        $scope.action_template_status = "Complete";
                    } else {
                        $scope.action_template_status = "Template Saved";
                        $scope.scheduleData.email_template_status = 'Template Saved';
                    }
                    growl(templetestatussaved);
                    $scope.getCampaigns();
                    $scope.getActionComments(entity_id);
                } else {
                    growl(savingrecordproblem, "error");
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
//            growl(templetestatussaved);
//            window.open(getHost() + 'user/marketing', "_self");
//          }else {
//              growl(savingrecordproblem);
//          }
//        }).error(function (data, status, headers, config) {
//  
//            growl(nodataerror);
//        });      

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
                    growl(singledeletesuccess);
                } else if (type === "delete") {
                    message = singledeleteconfirm;
                    requestBody = {"type": "delete",
                        "schedule_ids": schedules_to_delete, "entity_type": section,
                        "isRecurring": isRecurring};
                    responseMessage = singledeletesuccess;
                    growl(singledeletesuccess);
                } else if (type === "remove") {
                    message = removecnfirm;
                    requestBody = {"type": "removetemplate",
                        "schedule_ids": schedules_to_delete, "entity_type": section,
                        "isRecurring": isRecurring};
                    responseMessage = multideletesuccess;
                    growl(singledeletesuccess);
                }

                yourPlanFactory.changeSchedulePost(requestBody).then(function (data) {
                    if (type === "remove") {
                        $scope.savedEmail = false;
                        $scope.action_template_status = "No Template";
                    } else {
                        $scope.closePopup();
                    }
//                        $scope.closePopup();
                    $scope.getCampaigns();
                });
                $scope.getActionComments(schedules_to_delete);
            }
        };



        $scope.updateReminder = function (scheduleData) {
            var schedule_id = scheduleData.schedule_id;//$("#note_scheduleid").val();
            var note_title = scheduleData.schedule_title;//$("#edit_note_title").val();
            var status = "no_template";
            var note_desc = $("#reminderdesc" + scheduleData.schedule_id).val();
            var message = "Do you wan't to update the record?";
            var actiondate = $("#emaildatetime1").val();
            var actionDateTime = $("#timepickertextbox").val();
            utilFactory.getEpoch(actiondate, actionDateTime).then(function (dateTimeEpoch) {
                var schedule_details = {
                    "type": getnote(),
                    "schedule_id": schedule_id.toString(),
                    "schedule_title": note_title,
                    "schedule_desc": note_desc,
                    "status": status,
                    "schedule_time": dateTimeEpoch
                };
                yourPlanFactory.changeSchedulePost(schedule_details).then(function (data) {
                    growl("Reminder updated");
                    $scope.closePopup();
                    $scope.getCampaigns();
                });
            });
        };

        $scope.promptHideShow = function (flag) {
            $scope.clickedRemoveAction = flag;
        };

        $scope.saveEmailByActionId = function (scheduleData) {
//            localStorage.setItem("email_Schedule_Id",id);
            appSessionFactory.clearEmail().then(function (checkCleared) {
                kGlobalEmailObject.entityScheduleId = scheduleData.schedule_id;
                kGlobalEmailObject.entityScheduleTitle = scheduleData.schedule_title;
                appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                    if (data === true)
                        window.open(getHost() + 'user/baseemaileditor#/emailcategory', "_self");
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

        $scope.getSentEmailDetails = function (scheduleId) {
            yourPlanFactory.getSentEmailDetails(scheduleId).then(function (sentEmailData) {
                $scope.sentEmailDetails = sentEmailData.d.details[0];
                var iframe = document.getElementById('sentEmailIframe');
                iframe.contentDocument.body.innerHTML = sentEmailData.d.details[0].htmlData;
                $scope.fadeClass = 'fadeClass';
                $scope.sentEmailDetailsPopupClass = 'sentEmailDetailsPopupClass';
                $scope.savedReminderTab = true;
            });
        };

        $scope.getUserDetailsByUserId = function (userId) {
            appSessionFactory.getAllUsersUnderCompany().then(function (KGlobalAllUserUnderCompanyObject) {
                for (var i = 0; i < KGlobalAllUserUnderCompanyObject.userList.length; i++) {
                    if (userId === KGlobalAllUserUnderCompanyObject.userList[i].userId) {
                        var userFisetName = KGlobalAllUserUnderCompanyObject.userList[i].firstName;
                        var userLastName = KGlobalAllUserUnderCompanyObject.userList[i].lastName;
                        var userSignature = userFisetName.charAt(0) + userLastName.charAt(0);
                        userSortInfo.userSortName = userSignature.toUpperCase();
                        userSortInfo.userColor = KGlobalAllUserUnderCompanyObject.userList[i].userColor;
                        $scope.userInitials = userSignature.toUpperCase();
                        $scope.userColor = KGlobalAllUserUnderCompanyObject.userList[i].userColor;
                    }
                }
            });
        };
        $scope.addDateTimeOnId = function (dateid, timeId) {
            utilFactory.AddDateTimePickerOnId(dateid, timeId).then(function (dateTimeEpoch) {
            });
        };
    }]);
