marketinghubFlowApp.controller("marketingHubController", ['$scope', '$location', 'settingsFactory', 'emailListFactory', 'emailDraftFactory', 'emailFactory', 'appSessionFactory', function ($scope, $location, settingsFactory, emailListFactory, emailDraftFactory, emailFactory, appSessionFactory) {

//$scope.emailhubHeader = true;
        $scope.fadeClass = '';
        $scope.emailsectionClass = '';
        $scope.userSettings = false;
        var count = 0;
        var selectedemailids = "";
        $scope.addEmailListButton = true;
        $scope.saveEmailSettingsButton = false;
        $scope.deletDraftsButton = false;
        $("#removeselactions").hide;
        $scope.deSelectCheckboxButton = false;
        $scope.selectCheckboxButton = false;
        $scope.showDeleteEmailList = false;
        $scope.emailListName = "";
        $scope.type = "";
        $scope.emailId = "";
        $scope.firstName = "";
        $scope.lastName = "";
        $scope.selectedEmail = "";
        $scope.unsubscribePopup = false;
        $scope.overlayFade = false;
        $scope.emailListValidation = emailListValidation;
        $scope.emailDescriptionValidation = emailDescriptionValidation;
        $scope.fromAddressValidation = fromAddressValidation;
        $scope.emailAddressValidation = emailAddressValidation;
        $scope.emailaddrValidation = emailaddrValidation;
        $scope.fromNameValidation = fromNameValidation;
        $scope.fromNameCheck = false;
        $scope.companyAddressValidation = companyAddressValidation;
        $scope.email = {};
        $scope.company = {};
        $scope.showEmailListDetails = false;
        $scope.uploadCsvValidation = uploadCsvValidation;
        $scope.validCsvFileValidation = validCsvFileValidation;
        $scope.invalidCsvValidation = invalidCsvValidation;
        $scope.replyToValidation = replyToValidation;
        $scope.importContactValidation = importContactValidation;
        $scope.emailIdVal = false;
        $scope.validateEmail = false;
        $scope.emailIdVal = false;
        $scope.createEmailValidation = false;
        $scope.csvValidation = false;
        $scope.csvFileValidation = false;
        $scope.csvInvalidValidation = false;
        $scope.emailExists = false;
        $scope.listDescription = "";
        $scope.listName = "";
        $scope.deafultFromName = "";
        $scope.replyEmailValidation = false;
        $scope.replyToAddress = false;
        $scope.isCurrentCompanyInFranchise = false;
        $scope.isCurrentCompanyAFranchiseHeadquarter = false;
        var userSortInfo={userSortName:"",userColor:""};
        $scope.userSettings=false;
        
        $scope.showSettingsPopup = function (flag){
            $scope.userSettings = flag;
        };


        $scope.getCompanyStatus = function() {
            appSessionFactory.isCurrentCompanyInFranchise().then(function (isCurrent){
                $scope.isCurrentCompanyInFranchise = isCurrent;
            });
            appSessionFactory.isCurrentCompanyAFranchiseHeadquarter().then(function (isHead){
                $scope.isCurrentCompanyAFranchiseHeadquarter = isHead;
            });
        };
        
        this.tab = 1;

        this.selectTab = function (setTab){
           this.tab = setTab;  
        };

        this.isSelected = function(checkTab) {
            return this.tab === checkTab;
        };
         
         
        $scope.showHideUserSettings = function (flag) {
            $scope.userSettings = flag;
        };

        var generalEmailList = "General";
        $scope.getUserDetails = function () {
            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                $scope.getUserDetailsByUserId(kGlobalCompanyObject.userId);
                $scope.companyName = kGlobalCompanyObject.companyName;
                $scope.userFirstName = kGlobalCompanyObject.userFirstName;
                $scope.userLastName = kGlobalCompanyObject.userLastName;

                kGlobalCompanyObject.userHashId = 'undefined';
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

        $scope.displayAllEmailDrafts = function () {
            $scope.emailDraftDetails = true;
            $scope.showDataOverlay = true;
            $scope.activeEmailDrafts = 'activeTab';
            $scope.activeEmailHistory = '';
            $scope.activeEmailSettings = '';
            $scope.activeEmailList = '';
            $scope.emaildrafts = '';
            $scope.emaildropdown = false;
            $scope.saveEmailSettingsButton = false;
            $scope.addEmailListButton = false;
            $scope.showDeleteEmailList = false;
            var isPushed = false;
            emailDraftFactory.displayAllEmailDraftsGet(isPushed).then(function (data) {
                if (data.nodrafts === "yes") {
                    $scope.emailDraftDetails = false;
                    $scope.showDataOverlay=false;
                    $scope.emaildraftnumber = '0';
                    $scope.emaildraftsstatus = "No email drafts present";
                } else {
                    $scope.emaildrafts = data.emaildrafts;
                    $scope.showDataOverlay = false;
                    $scope.emailDraftDetails = true;
                }
            });
        };

        $scope.emailDraftCheckbox = function (id) {
            $scope.selectedEmail = id;
            var count = 0;
            var selected_emaildrafts_to_delete = "";

            content = '<input type="checkbox" id="' + 'entityid' + id + '" hidden="">';
            var htm = $("#" + id).html();
            var selected_schedule_id = id;
            if (htm.contains('class="check-icon"')) {
                selected_emaildrafts_to_delete = selected_emaildrafts_to_delete.replace(selected_schedule_id + ",", "");
                count -= 1;
                $("#" + id).html(content);
            } else
            {
                selected_emaildrafts_to_delete = selected_schedule_id + "," + selected_emaildrafts_to_delete;
                count += 1;
                $("#" + id).html(content + '<img src="../images/checkbox-check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#" + id).toggleClass('selection-icon');
            $("#" + id).toggleClass('selection-icon-selected');
            if (count > 0)
            {
                $scope.deletDraftsButton = true;
                $("#deleteEmaildraft").show();
            }
            if (count === -1)
            {
                $("#deleteEmaildraft").hide();
            }
        };
        $scope.selectedEmailCheckbox = function (emailListID) {
            var content = '<input type="checkbox" name="deleteid" value="' + emailListID + '" hidden="" id="deleteid"' + emailListID + '" checked>';
            var content1 = '<input type="checkbox" name="deleteid" value="' + emailListID + '" hidden="" id="deleteid"' + emailListID + '">';
            var htm = $("#" + emailListID).html();
            if (htm.contains('class="check-icon"')) {
                count -= 1;
                $("#" + emailListID).html(content1);
                selectedemailids = selectedemailids.replace(emailListID + ",", "");
            } else {
                selectedemailids = emailListID + "," + selectedemailids;
                count += 1;
                $("#" + emailListID).html(content + '<img src="../images/checkbox-check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#" + emailListID).toggleClass('selection-icon');
            $("#" + emailListID).toggleClass('selection-icon-selected');
            if (count > 0)
            {
                $scope.showDeleteEmailList = true;
                $("#removeselactions").show();
            }
            if (count < 1)
            {
                $scope.showDeleteEmailList = false;
                $("#removeselactions").hide();
            }
        };

//        $scope.unsel = function () {
//            count = 0;
//            var htm = $(".selection-icon-selected").html();
//            if (htm.contains('class="check-icon"')) {
//                $(".selection-icon-selected").html('');
//            }
//            $(".selection-icon-selected").addClass('selection-icon');
//            $('.selection-icon').removeClass('selection-icon-selected');
//            if (count === 0)
//            {
//                $scope.showDeleteEmailList = false;
//                $("#removeselactions").hide();
////                $(".gray-button").hide();
////                $("#addcontact").show();
////                $("#addcontacts").show();
//            }
//        };

        var selectedemailids = "";
        var count = 0;
        $scope.selectedEmailListCheckbox = function (id) {
            var content = '<input type="checkbox" name="deleteid" value="' + id + '" hidden="" id="deleteid"' + id + '" checked>';
            var content1 = '<input type="checkbox" name="deleteid" value="' + id + '" hidden="" id="deleteid"' + id + '">';
            var htm = $("#" + id).html();
            if (htm.contains('class="check-icon"')) {
                count -= 1;
                $("#" + id).html(content1);
                selectedemailids = selectedemailids.replace(id + ",", "");
            } else {
                selectedemailids = id + "," + selectedemailids;
                count += 1;
                $("#" + id).html(content + '<img src="../images/checkbox-check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#" + id).toggleClass('selection-icon');
            $("#" + id).toggleClass('selection-icon-selected');
            if (count > 0)
            {
                $scope.deSelectCheckboxButton = true;
                $scope.selectCheckboxButton = true;
                $("#addcontact").hide();
                $("#addcontacts").hide();
            }
            if (count <= 0)
            {
                $scope.deSelectCheckboxButton = false;
                $scope.selectCheckboxButton = false;
                $("#addcontact").show();
                $("#addcontacts").show();
            }
        };

        $scope.deSelectCheckbox = function () {
            count = 0;
            selectedemailids = "";
            var htm = $(".selection-icon-selected").html();
            if (htm.contains('class="check-icon"')) {
                $(".selection-icon-selected").html('');
            }
            $(".selection-icon-selected").addClass('selection-icon');
            $('.selection-icon').removeClass('selection-icon-selected');
            if (count === 0)
            {
                $scope.deSelectCheckboxButton = false;
                $scope.selectCheckboxButton = false;
                $("#addcontact").show();
                $("#addcontacts").show();
            }
        };

        $scope.deleteDrafts = function (type, id)
        {
            var delid = id + ",";
            var message;
            if ($scope.selectedEmail == "") {
                $scope.selectedEmail = id;
            }
            //            var selected_emaildrafts_to_delete = id;
            var requestBody;
            if (type === "deleteMultiple") {
                message = multidraftconfirm;
                requestBody = {"type": "deleteSelected",
                    "draft_ids": $scope.selectedEmail.toString(), "entity_type": "null"};
                $scope.deletDraftsButton = false;
            } else if (type === "delete") {
                message = singledraftconfirm;
                requestBody = {"type": "delete",
                    "draft_ids": $scope.selectedEmail.toString()};
            }
            emailDraftFactory.deleteEmailDraftsPost(requestBody).then(function (data) {
                growl("Data deleted successfully");
                $scope.displayAllEmailDrafts();
                $scope.savedEmailDraftPopup = false;
                $scope.deletDraftsButton = false;
                $scope.selectedEmail = "";
                $scope.closeSavedEmailDraftPopup();
            });
        };
        $scope.editDrafts = function (draft_id, category_id, email_subject, sub_category_id, mindbodyId, lookupId) {
            kGlobalEmailObject.draftId = draft_id;
            kGlobalEmailObject.categoryId = category_id;
            kGlobalEmailObject.emailSubject = email_subject;
            kGlobalEmailObject.subCategoryId = sub_category_id;
            kGlobalEmailObject.mindbodyId = mindbodyId;
            kGlobalEmailObject.lookupId = lookupId;
//            var draftdetails = {"draftid": draft_id, "email_subject": email_subject, "category_id": category_id,
//                "sub_category_id": sub_category_id, "mindbodyId": mindbodyId, "lookupId": lookupId};
            appSessionFactory.clearEmail().then(function (data) {
                appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
                });
            });
//            localStorage.setItem("emailDraftData", JSON.stringify(draftdetails));
            emailDraftFactory.getEmailDraftGet(draft_id).then(function (data) {
                if (data === "false") {
                    growl("There was a problem while saving the draft! Please try again later", "error");
                } else {
                    window.open(getHost() + 'user/baseemaileditor#/emaileditor', "_self");
                }
            });
        };
        $scope.getEmailSettings = function () {

            $scope.emaildropdown = false;
            $("#savesetbtn").show();
//            $("#removeselactions").hide();
            $scope.showDeleteEmailList = false;
            $scope.saveEmailSettingsButton = true;
            $scope.addEmailListButton = false;
            $scope.deletDraftsButton = false;
            settingsFactory.getEmailSettingsGet().then(function (data) {
                $scope.emailSettingsDetails = true;
                $scope.email_settingsData = JSON.parse(data.d.details);
                $scope.email_settings = $scope.email_settingsData;
                if($scope.email_settings == null) {
                    $scope.email_settings = {};
                    $scope.email_settings.from_address = 'mail@brndbot.com';
                }
            });
        };

        $scope.emailSettingsValidation = function (email_settings) {
            if (!email_settings.from_address) {
//                $scope.email_settings = {reply_email_address: "", from_address: from_address};
                $scope.fromAddressCheck = true;
                $("#from_address").focus();
                return false;
            }
            if (!email_settings.reply_email_address) {
//                $scope.email_settings = {reply_email_address: "", from_address: from_address};
                $scope.replyToAddress = true;
                $("#reply_address").focus();
                return false;
            }

            if (!email_settings.from_name) {
//                $scope.email_settings = {reply_email_address: "", from_address: from_address};
                $scope.fromNameCheck = true;
                $("#from_name").focus();
                return false;
            }

            var reply_email_address = email_settings.reply_email_address;
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            var result = reply_email_address.replace(/\s/g, "").split(/,|;/);
            for (var i = 0; i < result.length; i++) {
                if (!regex.test(result[i])) {
                    $scope.replyToAddress = false;
                    $("#reply_address").focus();
                    $scope.replyEmailValidation = "true" + "'" + result[i] + "'";
                    return false;
                }
            }
            $scope.replyEmailValidation = false;
            $scope.fromNameCheck = false;
            return true;
        };

        $scope.saveEmailSettings = function (email_settings) {
            if ($scope.emailSettingsValidation(email_settings))
            {
                if ($scope.emailSettingAdressValidation(email_settings.from_address))
                {
                    var from_address = email_settings.from_address;
                    var reply_email_address = email_settings.reply_email_address;
                    var emailSettingsData = {"from_address": from_address, "reply_email_address": reply_email_address, "from_name": email_settings.from_name};
                    settingsFactory.saveEmailSettingsPost(emailSettingsData).then(function (data) {
                        $scope.replyToAddress = false;
                        $scope.getEmailSettings();
                        growl("Settings saved successfully");
                    });
                }
            }
        };
        $scope.getFooterDetails = function () {
            $scope.emaildropdown = false;
//            $scope.saveEmailSettingsButton = false;
            $scope.saveEmailSettingsButton = true;
            $scope.addEmailListButton = false;
            $scope.deletDraftsButton = false;
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $scope.footerDetails = JSON.parse(data.d.details).companyProfile;
                $scope.company = $scope.footerDetails;
            });
        };



        $scope.changeFooterDetails = function (company) {
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
            $scope.getFooterDetails();
            settingsFactory.setFooterPost(footerPopupDetails).then(function (data) {
                $scope.getFooterDetails();
                growl("Settings saved successfully");
            });

        };
        $scope.emailFooterPopup = function ()
        {
            $scope.emailFooterPopupDetails = true;
        };
        $scope.hideEmailFooterPopupDetails = function ()
        {
            $scope.emailFooterPopupDetails = false;
        };
        $scope.displayEmailHistory = function () {
            $scope.email_history = "";
            $scope.showDeleteEmailList = false;
            $scope.emaildropdown = false;
            $scope.deletDraftsButton = false;
            $scope.addEmailListButton = false;
            $scope.saveEmailSettingsButton = false;
            $scope.hideGifImage = true;
            emailFactory.sendEmailGet().then(function (data) {
                $scope.email_history = data.d.details;
                $scope.hideGifImage = false;
            });
        };

        $scope.setTab = function (tabName) {
//            if (tabName === 'actionDetails') {
//                $scope.top_subnav_link_active_actionDetail_Class = 'top-subnav-link-active-detail-Class';
//                $scope.top_subnav_link_active_notesDetail_Class = '';
//                $scope.top_subnav_link_active_emailHistoryDetail_Class = '';                
//                $scope.top_subnav_link_active_savedDetail_Class = '';
//                $scope.generalActions = true;
//                $scope.generalSavedDetails = false;
//                $scope.generalNotes = false;
//            }
//            if (tabName === 'savedDetails') {
//                $scope.top_subnav_link_active_savedDetail_Class = 'top-subnav-link-active-detail-Class';
//                $scope.top_subnav_link_active_actionDetail_Class = '';
//                $scope.top_subnav_link_active_notesDetail_Class = '';
//                $scope.top_subnav_link_active_emailHistoryDetail_Class = '';   
//                $scope.generalSavedDetails = true;
//                $scope.generalActions = false;
//                $scope.generalNotes = false;
//            }
            if (tabName === 'emailHistory') {
                $scope.top_subnav_link_active_notesDetail_Class = '';
                $scope.top_subnav_link_active_actionDetail_Class = '';
                $scope.top_subnav_link_active_savedDetail_Class = '';
                $scope.top_subnav_link_active_emailHistoryDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.generalNotes = false;
                $scope.generalActions = true;
                $scope.generalSavedDetails = false;
            }
        };
        $scope.closePopup = function () {
            $scope.emailsectionClass = '';
            $scope.fadeClass = '';
        };

        $scope.getHistoryDetails = function (details) {
            $scope.tagsDetails = "";
            $scope.fadeClass = 'fadeClass';
            $scope.emailsectionClass = 'emailsectionClass';
            $scope.scheduledTo = 'POST';
            $scope.setTab('emailHistory');
            $scope.masterActionType = 'Email';
            $scope.setEmailToThisAction = "Save Email to this Action";
            $scope.savedHeader = 'Post';
            $scope.savedEmail = true;
            $scope.savedTemplateHeader = "SAVED EMAIL PREVIEW";
            $scope.deleteScheduleButton = "Remove Saved Email";
            $scope.isEmailHistory = true;
            $scope.hideGifImage=true;
            var categories = {"categories": details.emailTag};
            emailFactory.tagsDetailsGet(details.emailSentHistoryId).then(function (data) {
                if (data.d.operationStatus.statusCode !== "DataError") {
                    $scope.tagsDetails = data.d.details[0].sendGridStats;
                    $scope.tagerror = "";
                } else {
                    $scope.tagerror = categoryLoadDelay;
                }
                $scope.hideGifImage=false;
            });
        };
        $scope.showDraftPopup = function (Id, categoryId, emailSubject, editdate, subCategoryId, mindbodyId, lookupId)
        {   
            $("#fadePushedEmail").show();
            $scope.savedDraftPopup = true;
            emailDraftFactory.getEmailDraftGet(Id).then(function (data) {
                if (data === "") {
                    $scope.emaildraftsstatus = noemaildraft;
                } else {
                    $scope.htmlbody = data.htmlbody.replace(/contenteditable="true" /g, 'contenteditable="false"');
                    ;
                    $('#draftshow').empty().append($scope.htmlbody);
                }
            });
            $scope.id = Id;
            $scope.categoryid = categoryId;
            $scope.emailsubject = emailSubject;
            $scope.editdate = editdate;
            $scope.subcategoryid = subCategoryId;
            $scope.mindbodyId = mindbodyId;
            $scope.lookupId = lookupId;
            $('#slider-button').click();
        };
        $scope.closeSavedEmailDraftPopup = function ()
        {
            $scope.savedDraftPopup = false;
            $("#fadePushedEmail").hide();
        };
        $scope.addEmailList = function ()
        {
            $("#list_name").val("");
            $("#list_description").val("");
            $("#default_from_name").val("");
            $("#fade").show();
            $scope.createEmailListPopup = true;
        };
        $scope.closeCreateEmailList = function ()
        {
            $scope.createEmailListPopup = false;
            $("#fade").hide();
        };
        $scope.emailListGet = function () {
            $scope.activeEmailList = 'activeTab';
            $scope.activeEmailHistory = '';
            $scope.activeEmailSettings = '';
            $scope.activeEmailDrafts = '';
            $scope.emailListsMindbody = '';
            $scope.hideGifImage = true;

            $scope.emaildropdown = false;
            $scope.addEmailListButton = true;
            $("#addemlstbtn").show();
//            $("#deleteEmailList").hide();
            $scope.showEmailListDetails = false;
            $scope.saveEmailSettingsButton = false;
            $scope.deletDraftsButton = false;
            $scope.emallistdetails = true;
            $scope.noMindbodyIndexedList = false;
            appSessionFactory.getCompany().then(function (companyObject) {

                emailListFactory.getAllEmailListWithNoOfContactsForUser(companyObject.companyId).then(function (data) {
                    $scope.emailLists = data.d.details;
                });
                emailListFactory.getAllEmailListWithNoOfContactsForMindBody(companyObject.companyId).then(function (data) {
                    if(data.d.operationStatus.statusCode!=="DataError") {
                        $scope.emailListsMindbody = data.d.details;
                    } else {
                        $scope.noMindbodyIndexedList = true;
                    }
                    $scope.hideGifImage = false;
                });
            });
        };

        $scope.emailValidation = function (email) {
            if (!email.listName) {
                $scope.email = {listName: "", listDescription: email.listDescription, deafultFromName: email.deafultFromName};
                $("#list_name").focus();
                return false;
            }
            if (!email.listDescription) {
                $scope.email = {listName: email.listName, listDescription: "", deafultFromName: email.deafultFromName};
                $("#list_description").focus();
                return false;
            }
            if (!email.deafultFromName) {
                $scope.email = {listName: email.listName, listDescription: email.listDescription, deafultFromName: ""};
                $("#default_from_name").focus();
                return false;
            }
            return true;
        };
        
        $scope.emailSettingAdressValidation = function (email) {
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            if (!regex.test(email)) {
                    $("#from_address").focus();
                    $scope.fromAddressCheck = true;
                    return false;
            }
            $scope.fromAddressCheck = false;
            return true;
        };
        
        
        $scope.emailAdressValidation = function (email) {
            var deafultFromName = email.deafultFromName;
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            var result = deafultFromName.replace(/\s/g, "").split(/,|;/);
            for (var i = 0; i < result.length; i++) {
                if (!regex.test(result[i])) {
                    $("#default_from_name").focus();
                    $scope.createEmailValidation = "true" + "'" + result[i] + "'";
                    return false;
                }
            }
            $scope.createEmailValidation = false;
            return true;
        };

        $scope.createEmailList = function (email)
        {
            if ($scope.emailValidation(email))
            {
                if ($scope.emailAdressValidation(email))
                {
                    var emailListDetails = {"emailListName": email.listName, "emailListDescription": email.listDescription, "defaultFromAddress": email.deafultFromName, "emailListType": generalEmailList, "emailListTags": ['General']};

                    emailListFactory.createEmailList(emailListDetails).then(function (data) {
                        growl("Email list created successfully");
                        $scope.createEmailListPopup = false;
                        $("#fade").hide();
                        $scope.emailListGet();
                        $scope.showDeleteEmailList = false;
                        $("#deleteEmailList").show();
                    });
                }
            }
        };

        $scope.deleteEmailList = function ()
        {
            var noofemaillist;
            var selected_email_lists = "";
            $("input[type=checkbox]:checked").each(function () {
                selected_email_lists += $(this).val() + ",";
                noofemaillist = selected_email_lists.split(',');
            });
            noofemaillist.pop();
            var emailLists = {"ids": noofemaillist};
            emailListFactory.deleteEmailList(emailLists).then(function (data) {
                $scope.showDeleteEmailList = false;
                growl("Email list deleted successfully");
                $scope.emailListGet();
            });
        };

        $scope.showUpdateList = function ()
        {
//            $scope.activeEmailListContacts = 'activeTab';
//            $scope.activeImportContacts = '';
            $scope.updateList();
            $("#addcontact").show();
            $scope.showAddContactPopup = false;
        };

        $scope.checkEmailList = function () {
            if ($scope.showEmailListDetails == false) {
                $location.path("/basemarketinghub#/emaillist");
            }
        };

        $scope.updateList = function (type) {
            $scope.mindbody_emailAddresses = $scope.user_emailAddresses = "";
            $scope.showDataLoader = true;
            $scope.hideGifImage = true;
            $scope.noContacts = false;
            $scope.activeEmailListContacts = 'activeTab';
            $scope.activeImportContacts = '';
            $scope.showEmailListContacts = true;
            $scope.noUserContactList = false;
            $scope.noMindbodyContactList = false;
            $scope.user_emailAddresses = '';
            $scope.mindbody_emailAddresses = '';
            $("#importListli").removeClass("top-subnav-link-active");
            $("#importList").removeClass("h3-active-subnav");
            $("#emailListli").addClass("top-subnav-link-active");
            $("#emailList").addClass("h3-active-subnav");
            $(".page-background").css("background-color", "#fff");
            var list_name = $scope.emailListName;
            $("#tab4").hide();
            $("#email_list_name").val(list_name);
//            $scope.type = type;
            $scope.selected_email_listname = list_name;
            if($scope.type=='user'){
                emailListFactory.getContactsOfEmailList($scope.emailListId).then(function (data) { 
                    if (!data.d.details) {
                        $scope.noContacts = true;
                    }else{
                        $scope.user_emailAddresses = data.d.details;
                        $scope.mindbody_emailAddresses = '';
                        $scope.noContacts = false;
                    }
                    $scope.showDataLoader = false;
                    $scope.hideGifImage = false;
                });
            }
            if($scope.type=='mindbody'){
                emailListFactory.getContactsOfEmailList($scope.emailListId).then(function (data) { 
                    if (!data.d.details) {
                        $scope.noContacts = true;
                    }else{
                        $scope.mindbody_emailAddresses = data.d.details;
                        $scope.user_emailAddresses = '';
                        $scope.noContacts = false;
                    }           
                    $scope.showDataLoader = false;
                    $scope.hideGifImage = false;
                });
            }
        };

        $scope.emailIdValidation = function (email) {
            $scope.emailIdVal = false;
            var emailAddressVal = email.emailId;
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            var result = emailAddressVal.replace(/\s/g, "").split(/,|;/);
            for (var i = 0; i < result.length; i++) {
                if (!regex.test(result[i])) {
                    $("#emailId").focus();
                    $scope.validateEmail = "true" + "'" + result[i] + "'";
                    return false;
                }
            }
            $scope.validateEmail = false;
            return true;
        };

        $scope.updateEmailID = function (email) {
            if (!email.emailId) {
                $("emailId").focus();
                $scope.emailIdVal = true;
                return false;
            } else if ($scope.emailIdValidation(email))
            {
                $scope.emailIdVal = false;
                $("#addcontact").show();
                var email_list_name = $scope.emailListName;
                var email_address = $("#emailId").val();
                var firstName = email.firstName;
                var lastName = email.lastName;
                var emailListId = $scope.emailListId;
                var contactId = $scope.contactId;
                var type = $scope.type;
                if ($scope.validateEmailListPopup(email)) {
                    var emaildetails;
                    if (type === "add") {
                        emaildetails = {"emailAddress": email_address, "firstName": firstName, "lastName": lastName, "emailListId": emailListId};
                        emailListFactory.addEmailListContact(emaildetails).then(function (data) {
                            $("#addContactButton").unbind('click');
                            growl(datasaved);
                            $scope.marketingHubTab.selectTab(5);
                            $scope.type='user';
                            $scope.updateList("user");
                            $scope.overlayFade = false;
                            $scope.showAddContactPopup = false;
                            $("#addContactButton").unbind('click');
                        });
                    } else if (type === "update") {
                        emaildetails = {"emailListId": $scope.emailListId, "emailAddress": email_address,
                            "firstName": firstName, "lastName": lastName, "contactId": contactId};
                        emailListFactory.editContact(emaildetails).then(function (data) {
                            growl(datasaved);
                            $scope.type='user';
                            $scope.updateList("user");
                            $scope.showAddContactPopup = false;
                            $scope.overlayFade = false;
                        });
                    }

                }
            }
        };

        $scope.unsubscribeEmailList = "";
        $scope.uploadEmailListOnClick = function () {
            var reg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            var fileUpload = document.getElementById("fileid");
            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;

            if (regex.test(fileUpload.value.toLowerCase())) {
                if (typeof (FileReader) != "undefined") {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var table = document.createElement("table");
                        var rows = e.target.result.split("\n");
                        for (var j = 1; j < rows.length - 1; j++)
                        {
                            var csvvalue = rows[j].split(",");

                            var temp = csvvalue[0];
                            if (j == 1)
                                $scope.unsubscribeEmailList = temp;
                            else
                                $scope.unsubscribeEmailList = $scope.unsubscribeEmailList + "," + temp;
                        }
                        $scope.unsubscribeNowOnClick();
                    }
                    reader.readAsText(fileUpload.files[0]);

                } else {
                    growl("This browser does not support HTML5!", "error");
                }
            } else {
//                growl("Please upload a valid CSV file!");
                $scope.csvValidation = false;
                $scope.csvFileValidation = true;
            }

        };
        $scope.unsubscribeNowOnClick = function () {
            //If no file is selected

            var error = 0;
            var emailLists = [];
            if (!$scope.unsubscribeEmailList)
            {
                $scope.csvFileValidation = false;
//                growl("Please choose a valid csv file.");
                $scope.csvValidation = true;
                return false;
            }
            //if file is blank
            if ($scope.unsubscribeEmailList)
            {
                if ($scope.unsubscribeEmailList.length == 0)
                {
                    $scope.csvFileValidation = false;
//                    growl("Please choose a valid csv file.");
                    $scope.csvValidation = true;
                    return false;
                }
            }
            $scope.csvFileValidation = false;
            var emailListData = $scope.unsubscribeEmailList.split(",");
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

            for (var i = 0; i < emailListData.length; i++) {
                var emailID = $.trim(emailListData[i]);
                if (regex.test(emailID)) {
                    emailLists.push(emailID);
                } else
                {
                    error++;
                }
            }
            if (error != 0) {
//                growl("Some of the email addresses are invalid in the csv file and they have been excluded.");
                $scope.csvInvalidValidation = true;
            }
            var unsubscribeData = {"emailList": emailLists};
            settingsFactory.unSubscribeEmails(unsubscribeData).then(function (data) {
                growl(data.d.operationStatus.messages[0]);
                $scope.hideUnsubscribeEmailsPopup();
            });
        };


        $scope.validateEmailListPopup = function (email)
        {
            var email_address = $("#emailId").val();
//            var firstName = email.firstName;
//            var lastName = email.lastName;
            var error = 0;
            if (email_address === "")
            {
                error++;
                growl(noemail, "error");
                $("#emailId").focus();
                return false;
            }
            if (email_address !== "")
            {
                var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                if (re.test(email_address)) {
                } else
                {
                    error++;
                    growl(emailerror, "error");
                    $("#emailId").focus();
                    return false;
                }
            }
//            if (firstName !== "")
//            {
//                if (lastName === "")
//                {
//                    error++;
//                    growl(lastnameerror);
//                    $("#lastName").focus();
//                    return false;
//                }
//            }
            if (error === 0)
            {
                return true;
            }
        };

        $scope.uploadCsv = function () {
            var fileUpload = document.getElementById("fileUpload");
            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
            if (regex.test(fileUpload.value.toLowerCase())) {
                if (typeof (FileReader) !== "undefined") {
                    $scope.csvUpload = false;
                    $scope.importContacts = false;
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var table = document.createElement("table");
                        var rows = e.target.result.split("\n");
                        if ($('#textArea').val() === "") {
                            $('#textArea').val(rows);
                        } else {
                            $('#textArea').val($('#textArea').val() + rows);
                        }
                    }
                    reader.readAsText(fileUpload.files[0]);
                }
            } else {
                $scope.importContacts = false;
                $scope.csvUpload = true;
            }
        };
        $scope.updateEmailList = function () {
            var emailaddrestextarea = $("textArea").val();
            var reg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            var toemailvalid = reg.test(emailaddrestextarea);
            if ($("textArea").val() === '') {
//                growl("No Contacts to import!, Please Enter atleast One Contact.");
                $scope.importContacts = true;
                $("#textArea").focus();
                return false;
            }
            if ($("textArea").val() !== "") {

                var split = emailaddrestextarea.split(",");
                var lines = [];
                $.each($('#textArea').val().split(/\n/), function (i, line) {
                    if (line) {
                        lines.push(line);
                    }
                });
                for (var i = 0; i < split.length; i++) {
//                    growl(split[i]+"  split length"+split.length);
                    var email = split[i].trim();
                    if (reg.test(email) !== "")
                    {
                        if (email !== "")
                        {
                            if (reg.test(split[i]) === false) {
                                growl("Contacts not Valid! Please Enter Valid Email Address \n\n'" + split[i] + "'\t is Invalid Email id.\nCheck and remove if there is any spaces.", "error");
                                $("#textArea").focus();
                                return false;
                            }
                        }
                    }
                }
            }
            var firstName = "";
            var lastName = "";
            var emailListId = $scope.emailListId;
            var emaildetails = {"emailAddress": emailaddrestextarea, "firstName": firstName, "lastName": lastName, "emailListId": emailListId};
            emailListFactory.addContactList(emaildetails).then(function (data) {
//                alert(JSON.stringify(data));
                growl(datasaved);
                $scope.marketingHubTab.selectTab(5);
                $('#textArea').val('');
                $('#fileUpload').val('');
                $scope.showUpdateList();

            });
        };

        $scope.showDropDown = function ()
        {
//                $("#emaildropdown").css("display","block");
            $scope.emaildropdown = true;
        };

        $scope.addContactDetails = function (type, email, id, fname, lname, addedDate)
        {
            $("fade").show();
            $scope.overlayFade = true;
            $scope.email = {
                emailId: email,
                firstName: fname,
                lastName: lname
            };
            $("#fade").hide();
            $scope.showAddContactPopup = true;
            $scope.contactId = id;
            if (type === "update")
            {
                $scope.emailId = email;
            } else
            {
                $scope.emailId = "";
            }
            if (fname !== "")
            {
                $scope.firstName = fname;
            } else
            {
                $scope.firstName = "";
            }
            if (lname !== "")
            {
                $scope.lastName = lname;
            } else
            {
                $scope.lastName = "";
            }
            $scope.type = type;
            $scope.addedDate = addedDate;
        };
        $scope.showAddContacts = function () {
            count = 0;
            $("#addcontact").show();
            $("#showList").hide();
            $("#tab4").show();
            $("#importListli").addClass("top-subnav-link-active");
            $("#importList").addClass("h3-active-subnav");
            $("#emailListli").removeClass("top-subnav-link-active");
            $("#emailList").removeClass("h3-active-subnav");
            $("#emailListli").addClass("top-subnav-links");
            $("#emailList").addClass("h3");
            $("#tab3").hide();
            $("#tab4").show();
            $scope.showAddContactPopup = false;
            $scope.showEmailListContacts = false;
            $scope.deSelectCheckboxButton = false;
            $scope.selectCheckboxButton = false;
//            $scope.activeEmailListContacts = ''
//            $scope.activeImportContacts = 'activeTab';
        };
        $scope.viewEmailListDetails = function (email, type)
        {
            $scope.isMINDBODYEmailLIstDetails = true;
            if(type==="user")
                $scope.isMINDBODYEmailLIstDetails = false;
            $scope.overlayFade = false;
            $scope.emailListName = email.emailListName;
            $scope.emailListId = email.emailListId;
            $scope.type = type;
            $scope.showEmailListDetails = true;
            $scope.emallistdetails = false;
            $("#delcontact").hide();
            $location.path("/emaillistdetails");
            $scope.showUpdateList();
        };

        $scope.deleteSelected = function () {
            if (selectedemailids !== "") {
                var noOfContactList;
                var selected_email_lists = "";
                $("input[type=checkbox]:checked").each(function () {
                    selected_email_lists += $(this).val() + ",";
                    noOfContactList = selected_email_lists.split(',');
                });
                noOfContactList.pop();
                var deleteContacts = {"ids": noOfContactList};
                emailListFactory.deleteContactList(deleteContacts).then(function (data) {
                    $scope.deSelectCheckboxButton = false;
                    $scope.selectCheckboxButton = false;
                    $("#addcontact").show();
                    selectedemailids = "";
                    growl(data.d.operationStatus.messages[0]);
                    $scope.updateList();
                    $scope.showAddContactPopup = false;
                });
            } else {
                growl(emailnotselected);
            }
        };

        $scope.selemlcheckbox = function (id) {
            $scope.showDeleteEmailList = true;

            var content = '<input type="checkbox" name="deleteid" value="' + id + '" hidden="" id="deleteid"' + id + '" checked>';
            var content1 = '<input type="checkbox" name="deleteid" value="' + id + '" hidden="" id="deleteid"' + id + '">';
            var htm = $("#" + id).html();
            if (htm.contains('class="check-icon"')) {
                count -= 1;
                $("#" + id).html(content1);
                selectedemailids = selectedemailids.replace(id + ",", "");
            } else {
                selectedemailids = id + "," + selectedemailids;
                count += 1;
                $("#" + id).html(content + '<img src="../images/checkbox-check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#" + id).toggleClass('selection-icon');
            $("#" + id).toggleClass('selection-icon-selected');
            if (count > 0)
            {
                $("#removeselactions").show();
                $("#delcontact").show();
                $(".gray-button").show();
                $("#addcontact").hide();
                $("#addcontacts").hide();
            }
            if (count == 0)
            {
                $("#removeselactions").hide();
                $("#delcontact").hide();
                $(".gray-button").hide();
                $("#addcontact").show();
                $("#addcontacts").show();
            }


        };

//        $scope.uploadEmailListOnClick = function () {
//            var reg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//            var fileUpload = document.getElementById("fileid");
//            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
//            $scope.unsubscribeEmailList = "";
//            if (regex.test(fileUpload.value.toLowerCase())) {
//                if (typeof (FileReader) != "undefined") {
//                    var reader = new FileReader();
//                    reader.onload = function (e) {
//                        var table = document.createElement("table");
//                        var rows = e.target.result.split("\n");
//                        for (var j = 0; j < rows.length; j++)
//                        {
//                            var csvvalue = rows[j].split(",");
//
//                            for (var i = 0; i < csvvalue.length; i++)
//                            {
//                                var temp = csvvalue[i];
//                                if (j == 0 && i == 0)
//                                    $scope.unsubscribeEmailList = temp;
//                                else
//                                    $scope.unsubscribeEmailList = $scope.unsubscribeEmailList + "," + temp;
//                            }
//                        }
//                        growl("Emails from CSV file read successfully, please click Unsubscribe Now button to process them.")
//                    }
//                    reader.readAsText(fileUpload.files[0]);
//
//                } else {
//
//                    growl("This browser does not support HTML5!");
//                }
//            } else {
//                growl("Please upload a valid CSV file!");
//            }
//
//        };
//    
//        $scope.unsubscribeNowOnClick = function () {
//            //If no file is selected
//            if (!$scope.unsubscribeEmailList)
//            {
//                growl("Please choose a valid csv file.");
//                return false;
//            }
//            //if file is blank
//            if ($scope.unsubscribeEmailList)
//            {
//                if ($scope.unsubscribeEmailList.length == 0)
//                {
//                    growl("Please choose a valid csv file.");
//                    return false;
//                }
//            }
//            var emailListData = $scope.unsubscribeEmailList.split(",");
//            $http({
//                method: 'POST',
//                url: getHost() + 'settings/saveUnsubscribeEmails',
//                data: emailListData
//            }).success(function (data) {
//                growl(data.d.operationStatus.messages[0]);
//                hideUnsubscribeEmailsPopup();
//            }).error(function (data, status) {
//                growl(requesterror);
//            });
//        }; 

        $scope.openUnsubscribeEmailsPopup = function () {
            $scope.csvInvalidValidation = false;
            $scope.csvFileValidation = false;
            $scope.unsubscribePopup = true;
            growl("Please make sure email addresses are in first column of the csv file.");
        };

        $scope.hideUnsubscribeEmailsPopup = function () {
            $scope.csvFileValidation = false;
            $scope.csvValidation = false;
            $scope.csvInvalidValidation = false;
            $scope.unsubscribePopup = false;
        };

        $scope.closeAddContactPopup = function () {
            $scope.showAddContactPopup = false;
            $scope.overlayFade = false;
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
                $scope.userColor=userSortInfo.userColor;
                $scope.userInitials=userSortInfo.userSortName;
                }
            });
        };
        
    }]);
