/* Sandeep-Kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
brndBotSignupApp.controller("onboardingController", ['$scope', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', function ($scope, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory) {
        function validateSignUp()
        {  
            var emailId=$scope.signUpEmail;
            var password=$scope.signUpPassword;
            var rePassword=$scope.signUpConfirmPassword;
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
            if(validateSignUp()){
                var emailId = $('#emailId').val();
                var userPassword = $("#password").val();
                var userDetails={"userName":emailId,"userPassword":userPassword};
                $.ajax({
                    method: 'POST',
                    url: getHost() + '/onboarding/saveUser',
                    headers: {'Content-Type': 'application/json'},
                    data: JSON.stringify(userDetails)
                }).success(function (data)
                {                
                   var message= data.d.message;
                    if(message==="true")
                    {
                       $("#username").val(emailId);
                       $("#userpassword").val(userPassword);
                       $("#signform").submit();
                    }
                })
                .error(function (data, status) {
                    alert(eval(JSON.stringify(data.d.operationStatus.messages)));
                });
            }
            
            
            
            
            
            return false;
            onboardingFactory.saveUserPost(userDetails).then(function (data) {

            });
        };
        $scope.getOrganizations = function () {
            organizationFactory.organizationGet().then(function (data) {

            });
        };
        $scope.saveCompany = function () {
            $scope.companyDetails = {};
            onboardingFactory.saveCompanyPost($scope.companyDetails).then(function () {

            });
        };
        $scope.getAllServices = function () {
            subCategoryFactory.allExternalSourcesGet().then(function () {

            });
        };
        $scope.saveCompany = function () {
            onboardingFactory.completedActivationGet.then(function () {

            });
        };
        $scope.getActivationLink = function () {
            $scope.studioId = {};
            onboardingFactory.saveStudioIdPost(studioId).then(function () {

            });
        };
        $scope.saveServices = function () {
            onboardingFactory.completedActivationGet().then(function () {

            });
        };
        $scope.uploadLogo = function () {
            $scope.imageTypeData = {};
            $scope.imgDataObj = {};
            onboardingFactory.saveCompanyLogoPost(imageTypeData, imgDataObj).then(function () {

            });
        };
        $scope.getColorsFromLogo = function () {
            onboardingFactory.colorsForLogoGet().then(function () {

            });
        };
        $scope.getAllThemes = function () {
            onboardingFactory.allColorThemesGet().then(function () {

            });
        };
        $scope.saveColorPalette = function () {
            $scope.color1 = {};
            $scope.color2 = {};
            $scope.color3 = {};
            $scope.color4 = {};
            settingsFactory.setColorsPost(color1, color2, color3, color4).then(function () {

            });
        };

    }]);