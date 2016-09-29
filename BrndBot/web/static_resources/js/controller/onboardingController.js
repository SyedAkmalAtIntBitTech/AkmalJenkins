/* Sandeep-Kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
brndBotSignupApp.controller("onboardingController", ['$scope', '$location', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', 'externalContentFactory', 'settingsFactory', 'assetsFactory', 'signupFactory', 'appSessionFactory', function ($scope, $location, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory, externalContentFactory, settingsFactory, assetsFactory, signupFactory, appSessionFactory) {
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
        $scope.browserCompatibilityPopup = false;
        $scope.browserCompatibilityPopupDiv = false;
        $scope.loginForm = false;
        $scope.uniqueUser = false;
        $scope.userDetails = {};
        $scope.user = {};
        $scope.companyData = {};

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
        };
        
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
            if ($scope.confirmPassword === "" || $scope.confirmPassword == null) {
                $scope.createEmailValidation = false;
                $scope.confirmPassword = "";
                $("#confirmpasswordsignup").focus();
                return false;
            }
            if ($scope.isConfirmPasswordSame($scope.confirmPassword))
                return true;
        };

        $scope.isConfirmPasswordSame = function (cPassword) {
            if (cPassword === "") {
                $scope.confirmPasswordError = true;
                return false;
            } else if ($scope.userDetails.userPassword === cPassword) {
                $scope.isConfirmPasswordSamePassword = false;
                return true;
            } else {
                $scope.isConfirmPasswordSamePassword = true;
                return false;
            }
        };

        $scope.saveUser = function (userDetails) {
            $scope.uniqueUser = false;
            if ($scope.signupValidation(userDetails))
            {
                onboardingFactory.saveUserPost(userDetails).then(function (data) {
                    var userId = data.d.message;
                    appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                        kGlobalCompanyObject.userId = userId;
                        appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {
                            if (data) {
                                $("#signform").submit();
                                $location.path("/signup/company");
                            }
                            appSessionFactory.getCompany().then(function (kGlobalCompanyObject1) {
                            });

                        });
                    });
                });
            }
        };

        function checkBrowser() {
            if ((navigator.userAgent.indexOf("Opera") || navigator.userAgent.indexOf('OPR')) != -1)
            {
                $scope.browserCompatibilityPopup = true;
                $scope.browserCompatibilityPopupDiv = true;
            }
            else if (navigator.userAgent.indexOf("Chrome") != -1)
            {
                $scope.loginForm = true;
                $scope.browserCompatibilityPopup = false;
                $scope.browserCompatibilityPopupDiv = false;
            }
            else if (navigator.userAgent.indexOf("Safari") != -1)
            {
                $scope.loginForm = true;
                $scope.browserCompatibilityPopup = false;
                $scope.browserCompatibilityPopupDiv = false;
            }
            else if (navigator.userAgent.indexOf("Firefox") != -1)
            {
                $scope.browserCompatibilityPopup = true;
                $scope.browserCompatibilityPopupDiv = true;
                $scope.loginForm = false;
            }
            else if ((navigator.userAgent.indexOf("MSIE") != -1) || (!!document.documentMode == true)) //IF IE > 10
            {
                $scope.browserCompatibilityPopup = true;
                $scope.browserCompatibilityPopupDiv = true;
                $scope.loginForm = false;
            }
            else
            {
                $scope.browserCompatibilityPopup = true;
                $scope.browserCompatibilityPopupDiv = true;
                $scope.loginForm = false;
            }
        };
        $scope.closeOverlay = function(){
            $scope.browserCompatibilityPopup = false;
            $scope.browserCompatibilityPopupDiv = false;
            $scope.loginForm = true;

        };
        $scope.getUserId = function () {
            checkBrowser();
            $scope.userHashId = $location.search().userid;
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

            if (queryString["accessdenied"] !== undefined) {
                growl("user does not exist, please check the user name and password");
            }
//            appSessionFactory.clearAllSessions().then(function () {
                if ($location.search().userid) {
                    appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                        kGlobalCompanyObject.userHashId = $location.search().userid;
                        appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {
                        });
                    });
                } else {
                    appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                        kGlobalCompanyObject.userHashId = "";
                        appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {
                        });
                    });
                }
//            });
        };

        $scope.getLoggedInUserId = function () {
            onboardingFactory.getLoggedInUserId().then(function (data) {
                appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                    kGlobalCompanyObject.userId = data.d.details[0];
                    appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data1) {
                        if (data1) {
                            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                                var userHashId = kGlobalCompanyObject.userHashId;
                                if (userHashId != "") {
                                    var user = {"invitationCode": userHashId}
                                    onboardingFactory.saveInvitedUserPost(user).then(function (data2) {
                                        var message = data2.d.message;
                                        var userId = data2.d.id;
                                        kGlobalCompanyObject.userHashId = "";
                                        appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {});
                                        appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                                            kGlobalCompanyObject.userId = data2.d.id;
                                            appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data3) {
                                                $scope.getAllUserCompanies(userId);
                                            });
                                        });
                                    });
                                } else if (userHashId == "") {
                                    $scope.getAllUserCompanies(data.d.details[0]);
                                }
                            });
                        }
                    });
                });
            });
        };

        $scope.saveInvitedUser = function (userDetails) {

            var user = {"userName": userDetails.userName, "firstName": userDetails.firstName,
                "lastName": userDetails.lastName, "userPassword": userDetails.userPassword,
                "invitationCode": $scope.userHashId}
            onboardingFactory.saveInvitedUserPost(user).then(function (data) {
                var message = data.d.message;
                var userId = data.d.id;
                appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                    kGlobalCompanyObject.userId = data.d.id;
                    appSessionFactory.setCompany(kGlobalCompanyObject).then(function () {
                        data
                    });
                    growl(message);
                    window.location = getHost() + "login";
                });
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
                } else {
                    $scope.uniqueUser = false;
                }
            });
        };

        $scope.companyData.ddSelectOrganization = {
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

        $scope.validationCode = function (companyData) {

            if (!companyData.companyName) {
                $scope.companyData.companyName = "";
                $("#companyName").focus();
                return false;
            } else if (!companyData.ddSelectOrganization.value || companyData.ddSelectOrganization.value === "0") {
                $scope.organizationValidation = true;
                return false;
            }
            return true;
        };

        $scope.saveCompany = function (companyData) {
            $scope.companyName = companyData.companyName;
            $scope.organizationId = companyData.ddSelectOrganization.value;
            if ($scope.validationCode(companyData))
            {
                appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                    var userIdvalue = kGlobalCompanyObject.userId;
                    var companyDetails = {"userId": userIdvalue, "companyName": $scope.companyName, "organizationId": $scope.organizationId};

                    onboardingFactory.saveCompanyPost(companyDetails).then(function (data) {
                        var companyId = data.d.message;
                        if (parseInt(companyId) == 0) {
                            growl("company name already exist, please give some other company name");
                        } else {
                            kGlobalCompanyObject.companyId = companyId;

                            onboardingFactory.getAllUserCompanies(userIdvalue).then(function (data) {
                                var detail = data.d.details;
                                if (detail.length === 1) {
                                    var companyDetails = detail[0];
                                    appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                                        kGlobalCompanyObject.userId = companyDetails.userId;
                                        kGlobalCompanyObject.companyId = companyDetails.companyId;
                                        kGlobalCompanyObject.companyName = companyDetails.companyName;
                                        kGlobalCompanyObject.roleName = companyDetails.roleName;
                                        kGlobalCompanyObject.roleId = companyDetails.roleId;
                                        kGlobalCompanyObject.accountStatus = companyDetails.accountStatus;
                                        kGlobalCompanyObject.userEmailId = companyDetails.userEmailId;
                                        kGlobalCompanyObject.userFirstName = companyDetails.userFirstName;
                                        kGlobalCompanyObject.userLastName = companyDetails.userLastName;

                                        appSessionFactory.setCompany(kGlobalCompanyObject).then(function (data) {});
                                    });
                                } else {
                                    $scope.companies = data.d.details;
                                }
                            });

//                            appSessionFactory.setCompany(kGlobalCompanyObject).then(function(data){});
                            //TODO Set the companyId in Auth factory file
                            $location.path("/signup/datasource");
                        }
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
        $scope.getAllUserCompanies = function (userId) {
            onboardingFactory.getAllUserCompanies(userId).then(function (data) {
                var detail = data.d.details;
                appSessionFactory.getUser().then(function (kGlobalUserObject) {
                    if (detail.length === 1) {
                        kGlobalUserObject.hasMultipleCompany = false;
                        var companyDetails = detail[0];
                        $scope.getAccountStatus(companyDetails);
                    } else {
                        kGlobalUserObject.hasMultipleCompany = true;
                        $scope.companies = data.d.details;
                    }
                    appSessionFactory.setUser(kGlobalUserObject).then(function (data) {
                    $scope.hideDataOverlay = false;
                    });
                });
            });
        };

        $scope.getAccountStatus = function (companyDetails) {

            appSessionFactory.getCompany().then(function (kGlobalCompanyObject) {
                kGlobalCompanyObject.userId = companyDetails.userId;
                kGlobalCompanyObject.companyId = companyDetails.companyId;
                kGlobalCompanyObject.companyName = companyDetails.companyName;
                kGlobalCompanyObject.roleName = companyDetails.roleName;
                kGlobalCompanyObject.roleId = companyDetails.roleId;
                kGlobalCompanyObject.accountStatus = companyDetails.accountStatus;
                kGlobalCompanyObject.userEmailId = companyDetails.userEmailId;
                kGlobalCompanyObject.userFirstName= companyDetails.userFirstName;
                kGlobalCompanyObject.userLastName= companyDetails.userLastName;
                kGlobalCompanyObject.franchiseId= companyDetails.franchiseId;
                kGlobalCompanyObject.franchiseName= companyDetails.franchiseName;
                kGlobalCompanyObject.isHeadquarter= companyDetails.isHeadquarter;
                appSessionFactory.setCompany(kGlobalCompanyObject).then(function(data){
                    if (data){
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
                    }

                });

            });

        };

        $scope.getActivationLink = function (studioId) {
            $scope.mindbodyActive = false;
            onboardingFactory.saveStudioIdPost(studioId).then(function (data) {
                var studioIdSaved = eval(JSON.stringify(data.d.message));
                if (studioIdSaved === "true") {
                    externalContentFactory.activationLinkGet().then(function (data) {
                        $scope.activationLink = data.d.details[0];
                        if ($scope.activationLink) {
                            growl("Mindbody activated successfully.");
                        } else {
                            growl("Mindbody not activated.");
                        }
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
                } else {
                    onboardingFactory.isMindbodyActivated().then(function (data) {
                        var activation = JSON.stringify(data.d.details[0]);
                        globalActivation = activation;
                        if (globalActivation === "false")
                        {
                            growl("Mindbody not activated, kindly activate mindbody");
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
            $scope.defaultColors=[];
            for(var i=0,j=1;i<defaultFroalaColors.length;i=(i+2),j++){
                var colors="#"+defaultFroalaColors[i];
                $scope.defaultColors.push({"color":colors});
            }
            $scope.globalColors=$scope.defaultColors;

            $("#togglePaletteOnly").spectrum({
                showPaletteOnly: true,
                togglePaletteOnly: true,
                togglePaletteMoreText: 'more',
                togglePaletteLessText: 'less',
                color: 'blanchedalmond',
                palette: [
                    ["#000", "#444", "#666", "#999", "#ccc", "#eee", "#f3f3f3", "#fff"],
                    ["#f00", "#f90", "#ff0", "#0f0", "#0ff", "#00f", "#90f", "#f0f"],
                    ["#f4cccc", "#fce5cd", "#fff2cc", "#d9ead3", "#d0e0e3", "#cfe2f3", "#d9d2e9", "#ead1dc"],
                    ["#ea9999", "#f9cb9c", "#ffe599", "#b6d7a8", "#a2c4c9", "#9fc5e8", "#b4a7d6", "#d5a6bd"],
                    ["#e06666", "#f6b26b", "#ffd966", "#93c47d", "#76a5af", "#6fa8dc", "#8e7cc3", "#c27ba0"],
                    ["#c00", "#e69138", "#f1c232", "#6aa84f", "#45818e", "#3d85c6", "#674ea7", "#a64d79"],
                    ["#900", "#b45f06", "#bf9000", "#38761d", "#134f5c", "#0b5394", "#351c75", "#741b47"],
                    ["#600", "#783f04", "#7f6000", "#274e13", "#0c343d", "#073763", "#20124d", "#4c1130"]
                ]
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
            } else {
                $scope.colorsAlert = false;
                settingsFactory.setColorsPost(color1, color2, color3, color4).then(function (data) {
                    settingsFactory.getRendomColor().then(function (rendomColor){
                        var profileColor={userProfileColor:rendomColor}; 
                        settingsFactory.setUserProfileColor(profileColor).then(function (data){
                             window.location = getHost() + "user/dashboard";
                        });
                    });
                   
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
        $scope.stepsModel.push("");

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
            } else {
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
