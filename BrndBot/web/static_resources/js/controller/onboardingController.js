/* Sandeep-Kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
brndBotSignupApp.controller("onboardingController", ['$scope', '$location', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory','externalContentFactory', function ($scope, $location, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory,externalContentFactory) {
        function validateSignUp()
        {
            var emailId = $scope.signUpEmail;
            var password = $scope.signUpPassword;
            var rePassword = $scope.signUpConfirmPassword;
            if ($.trim(emailId).length === 0) {
                alert(emptyemail);
                emailId.focus();
                return false;
            }
            if (!(validateEmail(emailId))) {
                alert(wrongemail);
                $("#emailId").focus();
                return false;
            }
            if (password === "") {
                alert(passworderror);
                $("#password").focus();
                return false;
            }
            if (rePassword === "") {
                alert(repasswordempty);
                $("#rePassword").focus();
                return false;
            }

            if (password !== rePassword) {
                alert(confirmpassworderror);
                $("#rePassword").focus();
                $("#rePassword").val('');
                return false;
            }
            return true;
        };

        $scope.saveUser = function (userDetails) {
            onboardingFactory.saveUserPost(userDetails).then(function (data) {
                alert(JSON.stringify(data));
                var message = data.d.message;
                if (message === "true")
                {
                    $("#signform").submit();
                    $location.path("/signup/company");
                }
            });
        };
        $scope.getOrganizations = function () {
            organizationFactory.organizationGet().then(function (data) {
                $scope.defaultOrganisation = [{organizationId: 0, organizationName: 'Please select an industry'}];
                $scope.organizations = $scope.defaultOrganisation.concat(data.d.details);
                $scope.organizationId = $scope.organizations[0].organizationId;
            });
        };
        $scope.saveCompany = function (companyName, organizationId) {
            var companyDetails = {"companyName": companyName, "organizationId": organizationId};
            onboardingFactory.saveCompanyPost(JSON.stringify(companyDetails)).then(function (data) {
                alert(JSON.stringify(data));
                $location.path("/signup/datasource");
            });
        };
        $scope.getAllServices = function () {
            subCategoryFactory.allExternalSourcesGet().then(function (data) {
                alert(JSON.stringify(data));
                $scope.services = data.d.details;
                $scope.thirdPartyService = data.d.details[0].externalSourceId;
            });
        };
        $scope.getActivationLink = function (studioId) {
            alert(JSON.stringify(studioId));
            onboardingFactory.saveStudioIdPost(studioId).then(function (data) {
                alert(JSON.stringify(data));
                var studioIdSaved = eval(JSON.stringify(data.d.message));
                if (studioIdSaved === "true") {
                    externalContentFactory.activationLinkGet().then(function (data){
                       alert(JSON.stringify(data.d.details)); 
                       $scope.activationLink = data.d.details[0];
                    });
                }
            });
        };
        $scope.saveServices = function () {
            onboardingFactory.completedActivationGet().then(function (data) {
                alert(JSON.stringify(data));
              var studioIdSaved = eval(JSON.stringify(data.d.message));
                if (studioIdSaved === "true") {
                    $location.path("/signup/uploadlogo");
                }
                
            });
        };
        $scope.uploadLogo = function () {
            $scope.imageTypeData = {};
            $scope.imgDataObj = {};
            onboardingFactory.saveCompanyLogoPost(imageTypeData, imgDataObj).then(function (data) {

            });
        };
        $scope.getColorsFromLogo = function () {
            onboardingFactory.colorsForLogoGet().then(function (data) {

            });
        };
        $scope.getAllThemes = function () {
            onboardingFactory.allColorThemesGet().then(function (data) {

            });
        };
        $scope.saveColorPalette = function () {
            $scope.color1 = {};
            $scope.color2 = {};
            $scope.color3 = {};
            $scope.color4 = {};
            settingsFactory.setColorsPost(color1, color2, color3, color4).then(function (data) {

            });
        };

    }]);