settingFlowApp.controller("controllerUserChanges", ['$scope', '$window', '$location', 'signupFactory', 'settingsFactory', 'assetsFactory', 'onboardingFactory', function ($scope, $window, $location, signupFactory, settingsFactory, assetsFactory, onboardingFactory) {

        $scope.inputType = 'password';
        $scope.colorFrom = "custom";
        $scope.newPasswordValidation = newPasswordValidation;
        $scope.confirmPasswordValidation = confirmPasswordValidation;
        $scope.confirmPasswordMissmatch = confirmPasswordMissmatch;
        $scope.logoValidation = logoValidation;
        $scope.showPaletteChangePopUp = "";
        $scope.passwordText="";

        // Hide & show password function
        $scope.hideShowPassword = function () {
            if ($scope.inputType == 'password')
                $scope.inputType = 'text';
            else
                $scope.inputType = 'password';
        };

        $scope.accountSettingsValidation = function (password, confirmPassword) {
            if (!password) {
                $scope.password = "";
                $("#newpassword").focus();
                return false;
            }
            if (!confirmPassword) {
                $scope.confirmPassword = "";
                $("#confirmpassword").focus();
                return false;
            }
            if($scope.isConfirmPasswordSame(confirmPassword))
            return true;
        };
        
        $scope.setPassword = function (password){
            $scope.passwordText = password;
        };
        
        $scope.isConfirmPasswordSame = function (cPassword){
            if(cPassword === ""){
                $scope.isConfirmPasswordSamePassword = true;
                return false;
            }else if($scope.passwordText === cPassword){
                $scope.isConfirmPasswordSamePassword = false;
                return true;
            }else{
                $scope.isConfirmPasswordSamePassword = true;
                return false;
            }
        };
        
        $scope.changePassword = function (password, confirmPassword) {
            if ($scope.accountSettingsValidation(password, confirmPassword))
            {
                var password_object = {"password": password, "confirmpassword": confirmPassword, "type": "update"};
                signupFactory.resetPasswordPost(password_object).then(function (data) {
                    growl("Password changed successfully");
                    $scope.status = data;
                });
            }
        };

        $scope.setTab = function (type) {
            if (type == 'account') {
                $scope.userAccountSetClass = 'active';
                $scope.userLogoSetClass = '';
            }
            if (type == 'logo') {
                $scope.userAccountSetClass = '';
                $scope.userLogoSetClass = 'active';
            }
        };

        $scope.userLogoValidation = function (logoImage) {
            if (!logoImage) {
                $scope.logoImage = "";
                $("#uploadlogo").focus();
                return false;
            }
            return true;
        };

        $scope.changeLogo = function (logoImage) {
            var file = logoImage;
//            growl(file);
            if ($scope.userLogoValidation(logoImage))
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
        $scope.selectTheme = function (color1, color2, color3, color4) {
            $("#colorPalete1").css("background-color", color1);
            $("#colorPalete2").css("background-color", color2);
            $("#colorPalete3").css("background-color", color3);
            $("#colorPalete4").css("background-color", color4);
        };
        $scope.showColors = function () {
            $scope.setTab('logo');
            settingsFactory.getColorsURLGet().then(function (data) {
                $scope.user_preferences_colors = data.d.details;
            });
            assetsFactory.allColorThemesGet().then(function (data) {
                $scope.themes = data.d.details;
            });
        };
        $scope.getColorID = function (color) {
            $('.palette-colorswab-selected').css("background-color", color);

        };

        $scope.getLogoColors = function () {
            onboardingFactory.colorsForLogoGet().then(function (data) {
                $scope.color = data.d.details;
            });
        };
        $scope.saveColorPalette = function () {
            var color1 = $("#colorPalete1").css("backgroundColor");
            var color2 = $("#colorPalete2").css("backgroundColor");
            var color3 = $("#colorPalete3").css("backgroundColor");
            var color4 = $("#colorPalete4").css("backgroundColor");
            if ((color1 === "rgba(0, 0, 0, 0)") || (color2 === "rgba(0, 0, 0, 0)") || (color3 === "rgba(0, 0, 0, 0)") || (color4 === "rgba(0, 0, 0, 0)")) {
                growl("Please choose all 4 colors.");
            }
            $("#color1").css("backgroundColor", color1);
            $("#color2").css("backgroundColor", color2);
            $("#color3").css("backgroundColor", color3);
            $("#color4").css("backgroundColor", color4);
            $scope.showPaletteChangePopUp = false;
            settingsFactory.setColorsPost(color1, color2, color3, color4).then(function (data) {
//                growl(JSON.stringify(data.d.operationStatus.messages));
            });
        };
        $scope.clearColorPalette = function () {
            var bgColor = "background-color";
            $("#color1").css(bgColor, "");
            $("#color2").css(bgColor, "");
            $("#color3").css(bgColor, "");
            $("#color4").css(bgColor, "");
        };


        $scope.createUserPreferences = function (themecolor1, themecolor2, themecolor3, themecolor4)
        {
            growl(themecolor1);
            growl(themecolor2);
            growl(themecolor3);
            growl(themecolor4);
//            color1 = "rgb(255, 0, 255)";
//            color2 = "rgb(255, 0, 255)";
//            color3 = "rgb(255, 0, 255)";
//            color4 = "rgb(255, 0, 255)";
            settingsFactory.setColorsPost(color1, color2, color3, color4).then(function (data) {
                $scope.status = data;
                growl(data.d.operationStatus.messages[0]);
                $scope.showColors();
            });
        };
        $('#picker').colpick({
            flat: true,
            layout: 'hex',
            onSubmit: function (hsb, hex, rgb, el) {
                //for haking hex value growl(hex);
                $('.palette-colorswab-selected').css("background-color", "#" + hex);
                $('.palette-colorswab-selected').val("#" + hex);

            }
        });

        $scope.color1 = function ()
        {
        };

        $scope.stepsModel = [];

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
