/* Sandeep-Kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
brndBotSignupApp.controller("onboardingController", ['$scope', '$location', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', 'externalContentFactory', 'settingsFactory', 'assetsFactory', function ($scope, $location, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory, externalContentFactory, settingsFactory, assetsFactory) {
        $scope.imageSrc = "images/uploadPhoto.svg";
        $scope.colorFrom = "custom";
        $scope.organizationValidation = false;
        $scope.companyValidation = companyValidation;
        $scope.dropdownValidation = dropdownValidation;
        
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
        }
        ;

        $scope.saveUser = function (userDetails) {
            onboardingFactory.saveUserPost(userDetails).then(function (data) {
                var message = data.d.message;
                if (message === "true")
                {
                    $("#signform").submit();
                    $location.path("/signup/company");
                }
            });
        };

        $scope.ddSelectOrganizationOptions = [];

        $scope.ddSelectOrganization = {
            text: "Please select an organization"
        };

        $scope.getOrganizations = function () {
            organizationFactory.organizationGet().then(function (data) {

                $scope.defaultOrganisation = [{organizationId: 0, organizationName: 'Please select an industry'}];
                $scope.organizations = $scope.defaultOrganisation.concat(data.d.details);
                $scope.organizationId = $scope.organizations[0].organizationId;
//                alert(JSON.stringify(data));
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
        
        $scope.validationcode = function (companyName, organizationId) {
            if (!companyName) {
                $scope.companyName = "";
                $("#companyName").focus();
                return false;
            }
            else if (!organizationId) {
                $scope.organizationValidation = true;
                $("#dropdownValue").focus();
                return false;
            }
            else
            {
                return true;
            }
        };
        
        $scope.saveCompany = function (companyName, organizationId) {
            if ($scope.validationcode(companyName, organizationId))                        
            {
                var companyDetails = {"companyName": companyName, "organizationId": organizationId};
                onboardingFactory.saveCompanyPost(JSON.stringify(companyDetails)).then(function (data) {
                    $location.path("/signup/datasource");
                });
            }
        };
        
        $scope.selectedOrganization = function(selected)
        {
            if(selected.value){
                $scope.organizationValidation = false;
            }
        };
        
        $scope.ddSelectServicesOptions = [
        ];

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

        $scope.getActivationLink = function (studioId) {
            onboardingFactory.saveStudioIdPost(studioId).then(function (data) {
                var studioIdSaved = eval(JSON.stringify(data.d.message));
                if (studioIdSaved === "true") {
                    externalContentFactory.activationLinkGet().then(function (data) {
                        $scope.activationLink = data.d.details[0];
                    });
                }
            });
        };
        $scope.isMindbodyActivated = function(selectServices,studioId){
            var services = selectServices;
            if (services.text == 'None'){
                $scope.saveServices();
            }else if(services.text == 'Mindbody'){
             var studio = $("#mindbodyStudioId").val();
                if (studio == ""){
                    alert("no studio id entered, kindly enter the studio id");
                    $("#mindbodyStudioId").focus();
                    return false;
                }else {
                    onboardingFactory.isMindbodyActivated().then(function (data){
                    var activation=JSON.stringify(data.d.details[0]); 
                    globalActivation=activation;
                        if(globalActivation=="false")
                        {
                            alert("Mindbody not activated, kindly activate mindbody");
                            return false;
                        }else{
                            $scope.saveServices();
                        }
                    });
                }
            }
        };
        $scope.saveServices = function () {
            onboardingFactory.completedActivationGet().then(function (data) {
                var studioIdSaved = eval(JSON.stringify(data.d.message));
                alert(studioIdSaved);
//                if (studioIdSaved === "true") {
                $location.path("/signup/uploadlogo");
//                }

            });
        };
        $scope.uploadLogo = function () {
            var file = $scope.myFile;
            settingsFactory.changeLogoPost(file).then(function (data) {
                $location.path("signup/choosepalette");
            });
        };
        $scope.getColorsFromLogo = function () {
            $scope.activeColorLogo = 'selected';
            $scope.activeColorPicker = '';
            $scope.activeColorTheme = '';
            $scope.colorFrom = "logo";
            onboardingFactory.colorsForLogoGet().then(function (data) {
                $scope.color = data.d.details;
            });
        };
        $scope.getAllThemes = function () {
            $scope.activeColorTheme = 'selected';
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
            $scope.activeColorPicker = 'selected';
            $scope.activeColorTheme = '';
            $scope.activeColorLogo = '';
            $scope.colorFrom = "custom";
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
                alert("Please choose all 4 colors.");
            }
            settingsFactory.setColorsPost(color1, color2, color3, color4).then(function (data) {
            window.location = getHost()+"user/dashboard";
            });
        };
        //to display color picker
        $('#picker').colpick({
            flat: true,
            layout: 'hex',
            onSubmit: function (hsb, hex, rgb, el) {
                //for haking hex value alert(hex);
                $('.palette-colorswab-selected').css("background-color", "#" + hex);
                $('.palette-colorswab-selected').val("#" + hex);
            }
        });

        $scope.stepsModel = [];
        $scope.stepsModel.push("/BrndBot/user/images/upload-icon.svg");

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

    }]);