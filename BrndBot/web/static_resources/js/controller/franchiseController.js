
franchiseHubApp.controller("franchiseController", ['$scope', '$window', '$location', '$filter','franchiseFactory','redirectFactory','appSessionFactory','yourPlanFactory','emailDraftFactory', function ($scope, $window, $location, $filter, franchiseFactory, redirectFactory, appSessionFactory, yourPlanFactory, emailDraftFactory) {
       
        $scope.tab = 1;
        $scope.addFranchisePopup = false;
        $scope.addFranchisePopupDiv = false;
        $scope.editFranchisePopup = false;
        $scope.editFranchisePopupDiv = false;
        $scope.franchiseId = "";
        $scope.franchiseName = "";
        $scope.addCompanyPopup = false;
        $scope.pushedEmail = false;
        $scope.clickedRemoveAction = false;
        $scope.noPushedEmails = false;
        $scope.redirectToEmailFlow = function (forwardone)
        {
            appSessionFactory.clearEmail().then(function(checkCleared){
                appSessionFactory.getEmail().then(function (kGlobalEmailObject) {
                    kGlobalEmailObject.pushedEmail = true;
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function (data) {
    //                    alert(kGlobalEmailObject.pushedEmail);
                        $window.location = getHost() + "user/" + forwardone;
                    });
                });
            });
        };

        $scope.getFranchiseId = function () {
            var queryString = (function (a) {
                if (a == "")
                    return {};
                var b = {};
                for (var i = 0; i < a.length; ++i)
                {
                    var p = a[i].split('=', 2);
                    if (p.length == 1)
                        b[p[0]] = "";
                    else
                        b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
                }
                return b;
            })(window.location.search.substr(1).split('&'));

            $scope.franchiseId = queryString["franchiseId"];
            $scope.franchiseName = queryString["franchiseName"];
            $scope.getFranchiseHeadquarter();
        };
        
        $scope.getAllPushedEmailsForFranchise = function (){
            appSessionFactory.getCompany().then(function(kGlobalCompanyObject){
                var franchiseId = kGlobalCompanyObject.franchiseId;
                franchiseFactory.getAllPushedEmailsForFranchise(franchiseId).then(function (data){
                   $scope.allPushedEmails = data.d.details;
                   if (data.d.details.length == 0){
                       $scope.noPushedEmails = true;
                   }
                });
            });
        };

        $scope.setTab = function (tabName) {
            if (tabName === 'savedDetails') {
                $scope.top_subnav_link_active_savedDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_actionDetail_Class = '';
                $scope.top_subnav_link_active_notesDetail_Class = '';
                $scope.pushedEmailSavedDetails = true;
                $scope.associatedAccounts = false;
                $scope.pushedSavedEmail = true;
            }
            if (tabName === 'associatedAcccounts') {
                $scope.top_subnav_link_active_notesDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_actionDetail_Class = '';
                $scope.top_subnav_link_active_savedDetail_Class = '';
                $scope.associatedAccounts = true;
                $scope.pushedEmailSavedDetails = false;
                $scope.pushedSavedEmail = false;
                
            }
        };

        $scope.closePopup = function () {
            $scope.reminderSectionClass = '';
            $scope.emailsectionClass = '';
            $scope.fadeClass = '';
            $scope.hideSaveButton();
            $scope.hideReminderSaveButton();
        };        

        $scope.promptHideShow = function (flag){
            $scope.clickedRemoveAction = flag;
        };  
        $scope.hideSaveButton = function () {
            $scope.showUpdateBtn = false;
        };

        $scope.hideReminderSaveButton = function (){
            $scope.showReminderUpdateBtn=false;  
        };
        $scope.showReminderSaveButton = function (){
            $scope.showReminderUpdateBtn=true;
        };
        $scope.showSaveButton = function(){
            $scope.showUpdateBtn = true;
        };
        
        $scope.getScheduleDetails = function (scheduleTitle, scheduledEntityListId, entityId, pushedactionDateTime)
        {
            $scope.dateLesser = false;
            $scope.pushedSavedEmail = true;
            $scope.pushedEmailSavedDetails = true;
            $scope.associatedAcccounts = false;
            $scope.emailsectionClass = 'emailsectionClass';
            $scope.fadeClass = 'fadeClass';
            $scope.pushedEmail = true;
            var entity_type = 'Email';
            $scope.generalActionDetailsHeader = 'Email';
            $scope.scheduledTo = 'POST';
            $scope.masterActionType = 'Email';
            $scope.isRecurring = false;
            $scope.scheduleData = {scheduleTitle: scheduleTitle, entitiesSelectedDateTime: pushedactionDateTime,
                scheduleId: scheduledEntityListId};
            if (entity_type === getemail()) {
                $scope.scheduledTo = 'SEND';
                  $scope.savedHeader = getemail();
                yourPlanFactory.scheduledEmailGet($scope.scheduleData.scheduleId).then(function (data) {
                    $scope.entitiesdetails = JSON.parse(data.d.details);
                    var iframe = document.getElementById('iframeForAction1');
                    if (data.d.details != "{}") {
                        $scope.clickedRemoveAction = false;
                        $scope.savedTemplateHeader = "SAVED EMAIL PREVIEW";
                        iframe.contentDocument.body.innerHTML = $scope.entitiesdetails.body;
                        $scope.iframeLoad();
                    }
                });
                franchiseFactory.getAllAssociatedAccountForScheduledEntity(scheduledEntityListId).then(function (data){
                    $scope.associatedCompanies = data.d.details;
                    alert(JSON.stringify(data.d.details));
                });

            } 
//            growl($scope.isRecurring);
        };
        
        $scope.showEditFranchisePopup = function (franchiseId) {
            $scope.editFranchisePopup = true;
            $scope.editFranchisePopupDiv = true;
            $scope.franchiseId = franchiseId;
        };

        $scope.getAllFranchises = function () {
            franchiseFactory.getAllFranchises().then(function (data) {
                $scope.franchiseDetails = data.d.details;
            });
        };

        $scope.getFranchisesForCompanyId = function (companyId) {
            franchiseFactory.getFranchisesForCompanyId(companyId).then(function (data) {
                alert("Response:" + JSON.stringify(data));
            });
        };

        $scope.getFranchiseHeadquarter = function (franchiseId) {
            var franchiseId = $scope.franchiseId;
            franchiseFactory.getFranchiseHeadquarter(franchiseId).then(function (data) {
                $scope.headquarterCompany = data.d.message;
            });
        };

        $scope.getAllNonSelectedCompanies = function () {
            var franchiseId = $scope.franchiseId;
            franchiseFactory.getAllNonSelectedCompanies(franchiseId).then(function (data) {
                $scope.nonSelectedcompanies = data.d.details;
            });
        };

        $scope.activateCompanyAsFranchise = function (companyId) {

            var r = confirm("Do you want to make this company headquarter!");
            var franchiseId = $scope.franchiseId;
            var franchiseName = $scope.franchiseName;
            if (r == true) {

                franchiseFactory.activateCompanyAsFranchise(companyId, franchiseId).then(function (data) {
                    growl(eval(JSON.stringify(data.d.operationStatus.messages)));
                    window.open(getHost() + 'admin/franchiseCompanies?franchiseId=' + franchiseId + '&franchiseName=' + franchiseName, "_self");
                });
            }
        };

        $scope.getCompaniesForFranchiseId = function () {
            var franchiseId = $scope.franchiseId;
            franchiseFactory.getCompaniesForFranchiseId(franchiseId).then(function (data) {
                $scope.franchiseCompanies = data.d.details;
            });
        };

        $scope.associateCompanyToFranchise = function () {
            var franchiseId = $scope.franchiseId;
            var franchiseName = $scope.franchiseName;
            var companyId = $("#company option:selected").val();

            franchiseFactory.associateCompanyToFranchise(companyId, franchiseId).then(function (data) {
                growl(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/franchiseCompanies?franchiseId=' + franchiseId + '&franchiseName=' + franchiseName, "_self");
            });
        };

        $scope.removeCompanyFromFranchise = function (companyId) {
            var franchiseId = $scope.franchiseId;
            var franchiseName = $scope.franchiseName;
            franchiseFactory.removeCompanyFromFranchise(companyId, franchiseId).then(function (data) {
                growl(eval(JSON.stringify(data.d.operationStatus.messages)));
                window.open(getHost() + 'admin/franchiseCompanies?franchiseId=' + franchiseId + '&franchiseName=' + franchiseName, "_self");
            });
        };

        $scope.saveFranchise = function () {
            var franchiseName = $("#franchiseName").val();
            if (franchiseName === "") {

                growl(enterFranchiseName);
                $("#franchiseName").focus();
            } else {
                franchiseFactory.saveFranchise(franchiseName).then(function (data) {
                    growl(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
                    window.open(getHost() + 'admin/franchise', "_self");
                });
            }
        };

        $scope.updateFranchise = function () {
            var franchiseName = $("#editfranchiseName").val();
            var franchiseId = $scope.franchiseId;
            if (franchiseName === "") {

                growl(enterFranchiseName);
                $("#editfranchiseName").focus();
            } else {
                franchiseFactory.updateFranchise(franchiseId, franchiseName).then(function (data) {
                    growl(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
                    window.open(getHost() + 'admin/franchise', "_self");
                });
            }
        };

        $scope.deleteFranchise = function (franchiseId) {
            franchiseFactory.deleteFranchise(franchiseId).then(function (data) {
                growl(eval(JSON.stringify(data.d.operationStatus.messages))); //eval() is to get string without "" quotes                            
                window.open(getHost() + 'admin/franchise', "_self");
            });
        };

  //Setting tabs in baseaccountmanagerhub
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        };
        
        //Viewing details from Pushed Email History
        $scope.viewPushedEmailDetailBase = function ()
        {
            $scope.showPushedEmailDetails = true;
            $scope.showPushedEmailAccountDetails = false;
            $location.path("/pushedemaildetailbase");
        };
         //Setting tabs in pushedemailbase
//        $scope.setTabs = function(newTab){
//              $scope.tab = newTab;
//        };
        
        $scope.isSet = function(tabNum){
             return $scope.tab === tabNum;
        };
        
        $scope.displayAllPushedEmailDrafts = function () {
            $scope.activeEmailDrafts = 'activeTab';
            $scope.activeEmailHistory = '';
            $scope.activeEmailSettings = '';
            $scope.activeEmailList = '';
            $scope.emaildropdown = false;
            $scope.saveEmailSettingsButton = false;
            $scope.addEmailListButton = false;
            $scope.showDeleteEmailList = false;
            var isPushed = true;
            emailDraftFactory.displayAllEmailDraftsGet(isPushed).then(function (data) {
                if (data.nodrafts === "yes") {
                    $scope.emaildraftnumber = '0';
                    $scope.emaildraftsstatus = "No email drafts present";
                } else {
                    $scope.emaildrafts = data.emaildrafts;
                    $scope.emailDraftDetails = true;
                }
            });
        };
        $scope.showDraftPopup = function (Id, categoryId, emailSubject, editdate, subCategoryId, mindbodyId, lookupId)
        {
            $("#fade").show();
            $scope.savedEmailDraftPopup = true;
            emailDraftFactory.getEmailDraftGet(Id).then(function (data) {
                if (data === "") {
                    $scope.emaildraftsstatus = noemaildraft;
                } else {
                    $scope.htmlbody = data.htmlbody.replace(/contenteditable="true" /g, 'contenteditable="false"');;
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
            $scope.savedEmailDraftPopup = false;
            $("#fade").hide();
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
                appSessionFactory.clearEmail().then(function(data){
                    appSessionFactory.setEmail(kGlobalEmailObject).then(function(data){
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
        
    }]);
