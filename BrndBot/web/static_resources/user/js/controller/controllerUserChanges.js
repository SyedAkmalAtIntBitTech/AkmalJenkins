settingFlowApp.controller("controllerUserChanges", ['$scope', '$window', '$location', 'signupFactory', 'settingsFactory', 'assetsFactory', 'onboardingFactory', function ($scope, $window, $location, signupFactory, settingsFactory, assetsFactory, onboardingFactory) {
 
  $scope.inputType = 'password';
  
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
                debugAlert(JSON.stringify(data));
                $scope.status = data;
            });
        };

        $scope.showColors = function () {
           
            settingsFactory.getColorsURLGet().then(function (data) {
                
                $scope.user_preferences_colors = data.d.details;
            });

            assetsFactory.allColorThemesGet().then(function (data) {
                $scope.themes = data.d.details;
            });
        };

        $scope.getLogoColors = function () {
            onboardingFactory.colorsForLogoGet().then(function (data) {
                $scope.color = data.d.details;
            });
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

    }]);
