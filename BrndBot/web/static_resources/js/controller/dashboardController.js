dashboardFlowApp.controller("dashboardController", ['$scope', '$window', '$location', 'categoryFactory', 'subCategoryFactory', 'externalContentFactory', 'redirectFactory', 'SharedService', 'onboardingFactory', 'appSessionFactory', function ($scope, $window, $location, categoryFactory, subCategoryFactory, externalContentFactory, redirectFactory, SharedService, onboardingFactory, appSessionFactory) {
        $scope.emailChannelId = 3;
        $scope.printChannelId = 2;
        $scope.imageChannelId = 1;
        $scope.forward = "";
        $scope.categoryId = "";
        $scope.lookupId = "";
        $scope.externalSourceName = "";
        $scope.subCategoryId = "";
        $scope.mindbodyid = "";
        $scope.emailsubject = "";
        $scope.emailSubjectError = "";
        $scope.sharedData = "";
        $scope.companyName = "";
        $scope.userFirstName = "";
        $scope.userLastName = "";
        $scope.userRole = "";
        $scope.logourl = "";
        $scope.companyList = false;
        $scope.isCurrentCompanyInFranchise = false;
        $scope.isCurrentCompanyAFranchiseHeadquarter = false;
        $scope.neverShowUnsubscribeEmailpopup = true;
        var userSortInfo={userSortName:"",userColor:""};
        $scope.userFirstNameInitial=""
        $scope.userLastNameInitial=""
        $scope.pageTitle=""

        $scope.getCompanyStatus = function() {
            appSessionFactory.isCurrentCompanyInFranchise().then(function (isCurrent){
                $scope.isCurrentCompanyInFranchise = isCurrent;
            });
            appSessionFactory.isCurrentCompanyAFranchiseHeadquarter().then(function (isHead){
                $scope.isCurrentCompanyAFranchiseHeadquarter = isHead;
            });
        };

        $scope.getUserDetails = function () {
            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                $scope.getUserDetailsByUserId(kGlobalCompanyObject.userId);
                $scope.companyName = kGlobalCompanyObject.companyName;
                $scope.userFirstName = kGlobalCompanyObject.userFirstName;
                $scope.userLastName = kGlobalCompanyObject.userLastName;
                $scope.userFirstNameInitial = kGlobalCompanyObject.userFirstName.charAt(0);
                $scope.userLastNameInitial = kGlobalCompanyObject.userLastName.charAt(0);
                $scope.userRole = kGlobalCompanyObject.roleName;
                $scope.logourl = kGlobalCompanyObject.logourl;
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
                appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                    kGlobalCompanyObject.userHashId = "";
                    appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {
                    });
                });
                
            });
        };
        
        $scope.showCompanyList = function () {
            window.location = getHost() + "user/loading";
        };

//        $scope.getAllUserCompanies = function (){
//            onboardingFactory.getAllUserCompanies().then(function(data){
//               $scope.companies = data.d.details; 
//               $scope.hideDataOverlay = false;
//            });
//        };
//        
        $scope.redirect = function (redirect, categoryId, mindbody, lookupId, mindbodyid)
        {
            $scope.lookupId = lookupId;
            if (mindbody === '')
            {
                $scope.categoryId = categoryId;
            }
            if (mindbody === 'Mindbody')
            {
                $scope.externalSourceName = 'mindbody';
                redirect = $scope.forwardone;
                $scope.lookupId = lookupId;
                $scope.subCategoryId = categoryId;
            }
            if (mindbody === 'nonmindbody')
            {
                if (redirect === 'emailexternalsource')
                {
                    redirect = $scope.forwardtwo;
                    $scope.subCategoryId = categoryId;
                }
                $scope.categoryId = categoryId;
            }
            if (mindbodyid !== '')
            {
                $scope.mindbodyid = mindbodyid;
            }
            $location.path("/" + redirect);
        };
        $scope.redirectToEmailFlow = function (forwardone)
        {
            appSessionFactory.clearEmail().then(function (checkCleared) {
                redirectFactory.redirectFlowTo(forwardone);
                $window.location = getHost() + "user/" + forwardone;
                var emailsubject = $scope.emailsubject;
                if (emailsubject === '')
                {
                    $scope.emailSubjectError = "Email Subject Required!";
                }
            });
        };
        $scope.getCategories = function (forwardone)
        {
            categoryFactory.allCompanyCategoriesGet(emailChannelId).then(function (data) {
                $scope.pageName = "emailcategory";
                $scope.header = "Select Category";
                $scope.forwardone = forwardone;
                $scope.displayAllCategories = data.d.details;
            });
        };
        $scope.getSubCategories = function (forwardone, forwardtwo)
        {
            $scope.pageName = "emailsubcategory";
            subCategoryFactory.allSubCategoriesGet($scope.categoryId).then(function (data) {
                $scope.pageName = "emailsubcategory";
                $scope.header = "Select Subcategory";
                $scope.forwardone = forwardone;
                $scope.forwardtwo = forwardtwo;
                $scope.displayAllCategories = data.d.details;
            });
        };
        $scope.getActive = function (lookupId)
        {
            externalContentFactory.activatedGet(lookupId).then(function (data) {
                var minddata = JSON.stringify(data.d.details);
                if (minddata === "[true]") {
                    externalContentFactory.listDataGet(lookupId).then(function (data) {
                        var parseData = JSON.parse(data.d.details);
                        $scope.mindbodylist = parseData.mindbody_data;
                    });
                } else
                {

                }
            });
        };
        $scope.redirectFlow = function (pageName)
        {
            redirectFactory.redirectFlowTo(pageName);
        };


        $scope.redirectToMarketingProgram = function (pageName)
        {
            redirectFactory.redirectFlowTo(pageName);
        };
        $scope.checkForUnsubscribeEmail = function () {
            appSessionFactory.getPopupFlag().then(function (kGlobalPopupFlagsObject) {
                if (kGlobalPopupFlagsObject.emailUnsubscribe) {
                    $scope.neverShowUnsubscribeEmailpopup = true;
                    $scope.redirectToEmailFlow('baseemaileditor');
                } else {
                    $scope.neverShowUnsubscribeEmailpopup = false;
                }
            });
        };
        $scope.setUnsubscribeFlage = function (isChecked) {
            if (isChecked) {
                appSessionFactory.getPopupFlag().then(function (kGlobalPopupFlagsObject) {
                    kGlobalPopupFlagsObject.emailUnsubscribe = true;
                    appSessionFactory.setPopupFlag(kGlobalPopupFlagsObject).then(function (data) {
                        $scope.neverShowUnsubscribeEmailpopup = true;
                        $scope.redirectToEmailFlow('baseemaileditor');
                    });
                });
            } else {
                $scope.neverShowUnsubscribeEmailpopup = true;
                $scope.redirectToEmailFlow('baseemaileditor');
            }
        };
        
        $scope.getUserDetailsByUserId = function (userId){
            appSessionFactory.getAllUsersUnderCompany().then(function (KGlobalAllUserUnderCompanyObject){
                for(var i=0; i<= KGlobalAllUserUnderCompanyObject.userList.length;i++){
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


