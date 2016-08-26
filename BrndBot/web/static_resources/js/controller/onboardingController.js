/* Sandeep-Kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
brndBotSignupApp.controller("onboardingController", ['$scope', '$location', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', 'externalContentFactory', 'settingsFactory', 'assetsFactory', 'signupFactory', function ($scope, $location, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory, externalContentFactory, settingsFactory, assetsFactory, signupFactory) {
        $scope.imageSrc = "images/uploadPhoto.svg";
        $scope.colorFrom = "custom";
        $scope.organizationValidation = false;
        $scope.companyValidation = companyValidation;
        $scope.dropdownValidation = dropdownValidation;
        $scope.colorValidation = colorValidation;
        $scope.studioIdValidation = studioIdValidation;
        $scope.inActiveMindbodyValidation = inActiveMindbodyValidation;
        $scope.logoValidation = logoValidation;
        $scope.userHashId = "";
        $scope.userId = 0;
        $scope.hideDataOverlay = true;
        $scope.organizationId = "";
        $scope.emailAddressValidation = emailAddressValidation;
        $scope.emailaddrValidation = emailaddrValidation;
        $scope.passwordValidation = passwordValidation;
        $scope.confirmPasswordValidation = confirmPasswordValidation;
        $scope.confirmPasswordMissmatch = confirmPasswordMissmatch;
        $scope.uniqueUserValidation = uniqueUserValidation;
        $scope.uniqueUser = false;
        $scope.userDetails = {};
        $scope.user = {};

        function validateSignUp()
        {
            var emailId = $scope.signUpEmail;
            var password = $scope.signUpPassword;
            var rePassword = $scope.signUpConfirmPassword;
            if ($.trim(emailId).length === 0) {
                growl(emptyemail);
                emailId.focus();
                return false;
            }
            if (!(validateEmail(emailId))) {
                growl(wrongemail);
                $("#emailId").focus();
                return false;
            }
            if (password === "") {
                growl(passworderror);
                $("#password").focus();
                return false;
            }
            if (rePassword === "") {
                growl(repasswordempty);
                $("#rePassword").focus();
                return false;
            }

            if (password !== rePassword) {
                growl(confirmpassworderror);
                $("#rePassword").focus();
                $("#rePassword").val('');
                return false;
            }
            return true;
        }
        ;

        $scope.signupValidation = function (userDetails) {
            if (!userDetails.userName) {
                $scope.uniqueUser = false;
                $scope.userDetails = {userName: "", userPassword: userDetails.userPassword};
                $("#usernamesignup").focus();
                return false;
            }
            
            var uniqueuser = userDetails.userName;
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            var result = uniqueuser.replace(/\s/g, "").split(/,|;/);
            for (var i = 0; i < result.length; i++) {
                if (!regex.test(result[i])) {
                    $("#usernamesignup").focus();
                    $scope.createEmailValidation = "true" + "'" + result[i] + "'";
                    return false;
                }
            }

            if (!userDetails.userPassword) {
                $scope.uniqueUser = false;
                $scope.createEmailValidation = false;
                $scope.userDetails = {userName: userDetails.userName, userPassword: ""};
                $("#passwordsignup").focus();
                return false;
            }
            if ($scope.confirmPassword === ""||$scope.confirmPassword == null ) {
                $scope.createEmailValidation = false;
                $scope.confirmPassword = "";
                $("#confirmpasswordsignup").focus();
                return false;
            }
            if($scope.isConfirmPasswordSame($scope.confirmPassword))
            return true;
        };
        
        $scope.isConfirmPasswordSame = function (cPassword){
            if(cPassword === ""){
                $scope.confirmPasswordError=true;
                return false;
            }else if($scope.userDetails.userPassword === cPassword){
                $scope.isConfirmPasswordSamePassword=false;
                return true;
            }else{
                $scope.isConfirmPasswordSamePassword=true;
                return false;
            }
        };

        $scope.saveUser = function (userDetails) {
            $scope.uniqueUser = false;
            if ($scope.signupValidation(userDetails))
            {
                onboardingFactory.saveUserPost(userDetails).then(function (data) {
                    var message = data.d.message;
                    localStorage.setItem("userId",message);
//                    if (message === "true")
//                    {
                        $("#signform").submit();
                        $location.path("/signup/company");
//                    }
                });
            }
        };

        $scope.getUserId = function (){
            $scope.userHashId = $location.search().userid;
        };
        $scope.getLoggedInUserId = function(){
            onboardingFactory.getLoggedInUserId().then(function (data){
                localStorage.setItem("userId",data.d.details[0]);
                $scope.getAllUserCompanies(data.d.details[0]); 
            });
        };
        
        $scope.saveInvitedUser = function (userDetails) {
            
            var user = {"userName": userDetails.userName, "firstName": userDetails.firstName, 
                        "lastName": userDetails.lastName, "userPassword": userDetails.userPassword,
                        "invitationCode": $scope.userHashId}
            onboardingFactory.saveInvitedUserPost(user).then(function (data) {
                var message = data.d.message;
                var userId = data.d.id;
                localStorage.setItem("userId",userId);
                growl(message);
                window.location = getHost() + "login";
            });    
        };

        $scope.isUserUnique = function () {
            var emailId = $scope.userDetails.userName;
            var userEmailId = {"userName": emailId};
            onboardingFactory.userPost(userEmailId).then(function (data) {
                var isUserUnique = eval(JSON.stringify(data.d.message));
                if (isUserUnique === "false") {
                    $scope.uniqueUser = true;
                    $("#usernamesignup").focus();
                }else{
                    $scope.uniqueUser = false;
                }
            });
        };

        $scope.ddSelectOrganization = {
            text: "Please select an organization"
        };

        $scope.getOrganizations = function () {
            $scope.ddSelectOrganizationOptions = [
                {
                    text: "Please select an organization",
                    value: "0"
                }
            ];
            organizationFactory.organizationGet().then(function (data) {

                $scope.defaultOrganisation = [{organizationId: 0, organizationName: 'Please select an industry'}];
                $scope.organizations = $scope.defaultOrganisation.concat(data.d.details);
                $scope.organizationId = $scope.organizations[0].organizationId;

//                growl(JSON.stringify(data));
                //angular DD
                var organizationsData = data.d.details;
                for (var i = 0; i < organizationsData.length; i++) {

                    var organizationObject = {};
                    organizationObject["text"] = organizationsData[i].organizationName;
                    organizationObject["value"] = organizationsData[i].organizationId;
                    $scope.ddSelectOrganizationOptions.push(organizationObject);
                }
            });
        };

        $scope.validationcode = function (companyName,organizationId) {
            if (!companyName) {
                $scope.companyName = "";
                $("#companyName").focus();
                return false;
            }
            else if (!organizationId) {
                $scope.organizationValidation = true;
                return false;
            }
            return true;
        };

        $scope.saveCompany = function (companyAddressDetails,companyName,organizationId) {
            $scope.companyName = companyName;
            $scope.organizationId = organizationId;
            if ($scope.validationcode(companyName,organizationId))
            { 
                var userIdvalue = localStorage.getItem("userId");
                var companyDetails = {"userId":userIdvalue, "companyName": companyName, "organizationId": organizationId};
                onboardingFactory.saveCompanyPost(JSON.stringify(companyDetails)).then(function (data) {
                    var companyId = data.d.message;
                    localStorage.setItem("companyId",companyId);
                    //TODO Set the companyId in Auth factory file
                    onboardingFactory.saveCompanyAddress(companyAddressDetails).then(function (data){
                        alert(JSON.stringify(data));
                        $location.path("/signup/datasource");
                    });
                });
            }
        };

        $scope.selectedOrganization = function (organizationId)
        {
            if (organizationId.value) {
                $scope.organizationValidation = false;
            }
        };

//        $scope.ddSelectServicesOptions = [
//        ];

        $scope.ddSelectServices = {
            text: "None"
        };

//        $scope.getAllServices = function () {
//            subCategoryFactory.allExternalSourcesGet().then(function (data) {
//                $scope.services = data.d.details;
//                $scope.thirdPartyService = data.d.details[0].externalSourceId;
//            });
//        };

        $scope.getAllServices = function () {
            $scope.ddSelectServicesOptions = [
            ];
            subCategoryFactory.allExternalSourcesGet().then(function (data) {
                $scope.services = data.d.details;
                $scope.thirdPartyService = data.d.details[0].externalSourceId;
                var servicesData = data.d.details;
                for (var i = 0; i < servicesData.length; i++)
                {
                    var servicesObject = {};
                    servicesObject["text"] = servicesData[i].externalSourceName;
                    servicesObject["value"] = servicesData[i].externalSourceId;
                    $scope.ddSelectServicesOptions.push(servicesObject);
                }
            });
        };
        $scope.getAllUserCompanies = function (userId){
            onboardingFactory.getAllUserCompanies(userId).then(function(data){
               var detail = data.d.details;
               if (detail.length === 1){
                   var companyDetails = detail[0];
                   $scope.getAccountStatus(companyDetails);
                }else{
                   $scope.companies = data.d.details; 
                }
               $scope.hideDataOverlay = false;
            });
        };

        $scope.getAccountStatus = function(companyDetails){
            localStorage.setItem("companyDetails",JSON.stringify(companyDetails));
            localStorage.setItem("companyId", companyDetails.companyId);
            onboardingFactory.getAccountStatus(companyDetails).then(function(data){
               $scope.message = data.d.message; 
               if (data.d.message == 'Activated'){
                   window.location = getHost() + "user/dashboard";
               }else if (data.d.message == 'Deactivated'){
                   growl("your account has been deactivated, please contact system admin");
                   window.location = getHost() + "login";
               }
               $scope.hideDataOverlay = false;
            });
        };
        
        $scope.getActivationLink = function (studioId) {
            $scope.mindbodyActive = false;
            onboardingFactory.saveStudioIdPost(studioId).then(function (data) {
                var studioIdSaved = eval(JSON.stringify(data.d.message));
                if (studioIdSaved === "true") {
                    externalContentFactory.activationLinkGet().then(function (data) {                       
                        $scope.activationLink = data.d.details[0];
                        growl("Mindbody activated successfully");
                    });
                }
            });
        };

        $scope.isMindbodyActivated = function (selectServices, studioId) {
            var services = selectServices;
            if (services.text === 'None') {
                $scope.saveServices();
            } else if (services.text === 'Mindbody') {
                var studio = $("#mindbodyStudioId").val();
                if (studio === "") {
                    $scope.studioId = "";
                    $("#mindbodyStudioId").focus();
                    return false;
                }
                else {
                    onboardingFactory.isMindbodyActivated().then(function (data) {
                        var activation = JSON.stringify(data.d.details[0]);
                        globalActivation = activation;
                        if (globalActivation === "false")
                        {
//                            growl("Mindbody not activated, kindly activate mindbody");
                            $scope.mindbodyActive = true;
                            return false;
                        } else {
                            $scope.saveServices();
                        }
                    });
                }
            }
        };

        $scope.saveServices = function () {
            onboardingFactory.completedActivationGet().then(function (data) {
                var studioIdSaved = eval(JSON.stringify(data.d.message));
//                growl(studioIdSaved);
//                if (studioIdSaved === "true") {
                $location.path("/signup/uploadlogo");
//                }

            });
        };

        $scope.uploadLogoValidation = function (file) {
            if (!file) {
                $scope.myFile = "";
                return false;
            }
            return true;
        };

        $scope.uploadLogo = function (myFile) {
            var file = myFile;
            if ($scope.uploadLogoValidation(file))
            {
                settingsFactory.changeLogoPost(file).then(function (data) {
                    $location.path("signup/choosepalette");
                });
            }
        };

        $scope.getColorsFromLogo = function () {
            $scope.activeColorLogo = 'selected_Tab';
            $scope.activeColorPicker = '';
            $scope.activeColorTheme = '';
            $scope.colorFrom = "logo";
            onboardingFactory.colorsForLogoGet().then(function (data) {
                $scope.color = data.d.details;
            });
        };
        $scope.getAllThemes = function () {
            $scope.activeColorTheme = 'selected_Tab';
            $scope.activeColorPicker = '';
            $scope.activeColorLogo = '';
            $scope.colorFrom = "theme";
            assetsFactory.allColorThemesGet().then(function (data) {
                $scope.curPage = 0;
                $scope.pageSize = 10;
                $scope.themeColors = data.d.details;
            });
        };
        $scope.getColorFromPicker = function () {
            $scope.activeColorPicker = 'selected_Tab';
            $scope.activeColorTheme = '';
            $scope.activeColorLogo = '';
            $scope.colorFrom = "custom";
            $('#picker').colpick({
                flat: true,
                layout: 'hex',
                onSubmit: function (hsb, hex, rgb, el) {
                    //for haking hex value growl(hex);
                    $('.palette-colorswab-selected').css("background-color", "#" + hex);
                    $('.palette-colorswab-selected').val("#" + hex);

                }
            });
        };
        $scope.getColorID = function (color) {
            $('.palette-colorswab-selected').css("background-color", color);
        };
        $scope.selectTheme = function (color1, color2, color3, color4) {
            $("#color1").css("background-color", color1);
            $("#color2").css("background-color", color2);
            $("#color3").css("background-color", color3);
            $("#color4").css("background-color", color4);
        };
        $scope.clearColorPalette = function () {
            $scope.colorsAlert = false;
            var bgColor = "background-color";
            $("#color1").css(bgColor, "");
            $("#color2").css(bgColor, "");
            $("#color3").css(bgColor, "");
            $("#color4").css(bgColor, "");
        };

        $scope.saveColorPalette = function () {
            var color1 = $("#color1").css("backgroundColor");
            var color2 = $("#color2").css("backgroundColor");
            var color3 = $("#color3").css("backgroundColor");
            var color4 = $("#color4").css("backgroundColor");
            if ((color1 === "rgba(0, 0, 0, 0)") || (color2 === "rgba(0, 0, 0, 0)") || (color3 === "rgba(0, 0, 0, 0)") || (color4 === "rgba(0, 0, 0, 0)")) {
//               growl("Please choose all 4 colors.");
                $scope.colorsAlert = true;
                return false;
            }
            else {
                $scope.colorsAlert = false;
                settingsFactory.setColorsPost(color1, color2, color3, color4).then(function (data) {
                    window.location = getHost() + "user/dashboard";
                });
            }
        };

//
//        //to display color picker
//        $('#picker').colpick({
//            flat: true,
//            layout: 'hex',
//            onSubmit: function (hsb, hex, rgb, el) {
//                //for haking hex value growl(hex);
//                $('.palette-colorswab-selected').css("background-color", "#" + hex);
//                $('.palette-colorswab-selected').val("#" + hex);
//            }
//        });

        $scope.stepsModel = [];
        $scope.stepsModel.push("/BrndBot/images/upload-icon.svg");

        $scope.imageUpload = function (element) {
            var reader = new FileReader();
            reader.onload = $scope.imageIsLoaded;
            reader.readAsDataURL(element.files[0]);
        };

        $scope.imageIsLoaded = function (e) {
            $scope.$apply(function () {
                $scope.stepsModel = [];
                $scope.stepsModel.push(e.target.result);
            });
        };

        $scope.resetPassword = function (user) {
            if (!user.emailid) {
                $scope.user = {emailid: ""};
                $("#inputemail").focus();
                return false;
            }
            else {
                signupFactory.forgotPasswordPost(user).then(function (data) {
                    growl(eval(JSON.stringify(data.d.operationStatus.messages)));
                    $scope.status = data;
                    window.open(getHost(), "_self");
                });
            }
        };

        $scope.forgotChangePassword = function (user)
        {

            //Validate password
            var password_object = {"hashURL": $location.search().userid, "password": user.password, "type": "change"};
            signupFactory.resetPasswordPost(password_object).then(function (data) {
                growl(eval(JSON.stringify(data.d.operationStatus.messages)));
                $scope.status = data;
                window.open(getHost(), "_self");
            });
        };
    }]);