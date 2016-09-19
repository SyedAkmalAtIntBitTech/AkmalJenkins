
franchiseHubApp.controller("franchiseController", ['$scope', '$window', '$location', 'franchiseFactory','redirectFactory','appSessionFactory', function ($scope, $window, $location, franchiseFactory, redirectFactory, appSessionFactory) {
       
        $scope.tab = 1;
        $scope.addFranchisePopup = false;
        $scope.addFranchisePopupDiv = false;
        $scope.editFranchisePopup = false;
        $scope.editFranchisePopupDiv = false;
        $scope.franchiseId = "";
        $scope.franchiseName = "";
        $scope.addCompanyPopup = false;

        $scope.redirectToEmailFlow = function (forwardone)
        {
//            appSessionFactory.clearEmail().then(function(checkCleared){
//                redirectFactory.redirectFlowTo(forwardone);
//                $window.location = getHost()+"user/"+forwardone;
//                var emailsubject=$scope.emailsubject;
//                if(emailsubject==='')
//                {
//                    $scope.emailSubjectError="Email Subject Required!";
//                }
//            });
            
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
        $scope.setTab = function(newTab){
              $scope.tab = newTab;
        };
        
        $scope.isSet = function(tabNum){
             return $scope.tab === tabNum;
        };
    }]);
