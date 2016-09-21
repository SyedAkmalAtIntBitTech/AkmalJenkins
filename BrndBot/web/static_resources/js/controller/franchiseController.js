
franchiseHubApp.controller("franchiseController", ['$scope', '$window', '$location', 'franchiseFactory','redirectFactory','appSessionFactory','yourPlanFactory', function ($scope, $window, $location, franchiseFactory, redirectFactory, appSessionFactory, yourPlanFactory) {
       
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
                });
            });
        };

        $scope.setTab = function (tabName) {
            if (tabName === 'savedDetails') {
                $scope.top_subnav_link_active_savedDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_actionDetail_Class = '';
                $scope.top_subnav_link_active_notesDetail_Class = '';
                $scope.pushedEmailSavedDetails = true;
                $scope.associtedAcccounts = false;
            }
            if (tabName === 'associtedAcccounts') {
                $scope.top_subnav_link_active_notesDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_actionDetail_Class = '';
                $scope.top_subnav_link_active_savedDetail_Class = '';
                $scope.associtedAcccounts = true;
                $scope.pushedEmailSavedDetails = false;
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
        $scope.getScheduleDetails = function (scheduleTitle, scheduledEntityListId, entityId, pushedactionDateTime)
        {
            $scope.dateLesser = false;
//        $scope.entities_selected_time =schedule_time;
//            var nDate = new Date(action_date + " 10:30 am"); //10:30 am save DST
//            $scope.calculatedProgramDate = $scope.addDays(nDate, days);
            $scope.entitiesSelectedDate = $filter('date')(pushedactionDateTime, "MMM dd yyyy");
            $scope.pushedSavedEmail = false;
            $scope.pushedEmailSavedDetails = true;
            $scope.associtedAcccounts = false;
            $scope.emailsectionClass = 'emailsectionClass';
            $scope.fadeClass = 'fadeClass';
            $scope.pushedEmail = true;
            var entity_type = 'Email';
//            $scope.action_template_status = template_status;
            $scope.generalActionDetailsHeader = 'Email';
            $scope.scheduledTo = 'POST';
            $scope.setTab('savedDetails');
            $scope.masterActionType = 'Email';
//            $scope.templateApproveButton = "Approve";
//            $scope.templateDisapproveButton = "Disapprove";
//            $scope.savedDetailsAddTemplateButton = "Go to Dashboard";
//            $scope.savedDetailsAddTemplateLink = "dashboard";
//            $scope.setEmailToThisAction="Save Email to this Action";
            $scope.isRecurring = false;
            if (entity_type === getnote()) {
                $scope.reminderSectionClass = 'reminderSectionClass';
                $scope.savedReminderTab = true;
                $scope.setTab('savedReminder');
            }
//            var date = $scope.entities_selected_time;
////            var time = $filter('date')(schedule_time, "hh : mm : a");
////            $("#emaildatetime").val($filter('date')(action_date, "MMM dd yyyy"));
////            $("#emaildatetime1").val($filter('date')(action_date, "MMM dd yyyy"));
            $scope.scheduleData = {scheduleTitle: scheduleTitle, entitiesSelectedDateTime: pushedactionDateTime,
                scheduleId: scheduledEntityListId};
////                $('#emailcontentiframe').contents().find('html').html(data.body); 
//            $scope.globalScheduleData = $scope.scheduleData;
//
            if (entity_type === getemail()) {
                $scope.scheduledTo = 'SEND';
                  $scope.savedHeader = getemail();
//                yourPlanFactory.scheduledEmailGet($scope.scheduleData.schedule_id).then(function (data) {
//                    $scope.entitiesdetails = JSON.parse(data.d.details);
                    var iframe = document.getElementById('iframeForAction');
//                    
//                    if (data.d.details != "{}") {
                        $scope.pushedSavedEmail = true;
                        $scope.clickedRemoveAction = false;
//                        $scope.savedTemplateHeader = "SAVED EMAIL PREVIEW";
                        $scope.deleteScheduleButton = "Remove Saved Email";
                        iframe.contentDocument.body.innerHTML = $scope.entitiesdetails.body;
                        $scope.iframeLoad();
//                    } else {
//                        $scope.savedEmail = false;
//                        $scope.actionTypeNoTemplateMessage = "No emails saved to this action.";
//                    }
//                });
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
    }]);
