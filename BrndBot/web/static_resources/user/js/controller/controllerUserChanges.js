settingFlowApp.controller("controllerUserChanges", ['$scope', '$window', '$location', 'signupFactory', 'settingsFactory', 'assetsFactory', 'onboardingFactory', function ($scope, $window, $location, signupFactory, settingsFactory, assetsFactory, onboardingFactory) {
 
  $scope.inputType = 'password';
   $scope.colorFrom = "custom";

  // Hide & show password function
  $scope.hideShowPassword = function(){
    if ($scope.inputType == 'password')
      $scope.inputType = 'text';
    else
      $scope.inputType = 'password';
  };
        $scope.changePassword = function (password,confirmPassword) {
            alert(password);
            alert(confirmPassword);
            
            var password_object = {"password": password, "confirmpassword": confirmPassword, "type": "update"};
            signupFactory.resetPasswordPost(password_object).then(function (data) {
//                debugAlert(JSON.stringify(data));
                $scope.status = data;
            });
        };
        $scope.changeLogo = function (logoImage) {
            var file = logoImage;
//            alert(file);
            settingsFactory.changeLogoPost(file).then(function (data) {
                alert(JSON.stringify(data));
                $location.path("signup/choosepalette");
            });
        };
        $scope.getColorsFromLogo = function () {
            $scope.colorFrom = "logo";
            onboardingFactory.colorsForLogoGet().then(function (data) {
                $scope.color = data.d.details;
            });
        };
        $scope.getAllThemes = function () {
            $scope.colorFrom = "theme";
            assetsFactory.allColorThemesGet().then(function (data) {
                $scope.curPage = 0;
                $scope.pageSize = 10;
                $scope.themeColors = data.d.details;
            });
        };
        $scope.getColorFromPicker = function () {
//            alert("frompicker");
            $scope.colorFrom = "custom";
            $('#picker').colpick({
            flat: true,
            layout: 'hex',
            onSubmit: function (hsb, hex, rgb, el) {
                //for haking hex value alert(hex);
                $('.palette-colorswab-selected').css("background-color", "#" + hex);
                $('.palette-colorswab-selected').val("#" + hex);

            }
        });
        };
        $scope.getColorID = function (color) {
//            alert(color);
            $('.palette-colorswab-selected').css("background-color", color);
        };
        $scope.selectTheme = function (color1, color2, color3, color4) {
            $("#color1").css("background-color", color1);
            $("#color2").css("background-color", color2);
            $("#color3").css("background-color", color3);
            $("#color4").css("background-color", color4);
        };
        $scope.showColors = function () {
//            alert("..");
            settingsFactory.getColorsURLGet().then(function (data) { 
//                alert(data);
                $scope.user_preferences_colors = data.d.details;
            });
            assetsFactory.allColorThemesGet().then(function (data) {
                $scope.themes = data.d.details;
            });
        };
        $scope.getColorID = function (color) {
            alert(color);
            $('.palette-colorswab-selected').css("background-color", color);
            
        };
        
        $scope.getLogoColors = function () {
            onboardingFactory.colorsForLogoGet().then(function (data) {
                $scope.color = data.d.details;
            });
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
                alert(JSON.stringify(data));
            });
        };
        $scope.clearColorPalette = function () {
            var bgColor = "background-color";
            $("#color1").css(bgColor, "");
            $("#color2").css(bgColor, "");
            $("#color3").css(bgColor, "");
            $("#color4").css(bgColor, "");
        };
        
        
       $scope.createUserPreferences = function (themecolor1,themecolor2,themecolor3,themecolor4)
        {
            alert(themecolor1);
            alert(themecolor2);
            alert(themecolor3);
            alert(themecolor4);
//            color1 = "rgb(255, 0, 255)";
//            color2 = "rgb(255, 0, 255)";
//            color3 = "rgb(255, 0, 255)";
//            color4 = "rgb(255, 0, 255)";
            settingsFactory.setColorsPost(color1,color2,color3,color4).then(function (data) {
                $scope.status = data;
                alert(data.d.operationStatus.messages[0]);
                $scope.showColors();
            });
        };
        $('#picker').colpick({
            flat: true,
            layout: 'hex',
            onSubmit: function (hsb, hex, rgb, el) {
                //for haking hex value alert(hex);
                $('.palette-colorswab-selected').css("background-color", "#" + hex);
                $('.palette-colorswab-selected').val("#" + hex);

            }
        });
        
        $scope.color1 = function()
        {
            alert();
            
        };
        
        
        
            $scope.stepsModel = [];

    $scope.imageUpload = function(element){
        var reader = new FileReader();
        reader.onload = $scope.imageIsLoaded;
        reader.readAsDataURL(element.files[0]);
    };

    $scope.imageIsLoaded = function(e){
        $scope.$apply(function() {
            $scope.stepsModel.push(e.target.result);
        });
    };

    }]);
