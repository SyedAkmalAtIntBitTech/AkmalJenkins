settingFlowApp.controller("controllerUserChanges", ['$scope', '$window', '$location', 'signupFactory', 'settingsFactory', 'assetsFactory', 'onboardingFactory', function ($scope, $window, $location, signupFactory, settingsFactory, assetsFactory, onboardingFactory) {
        $scope.changePassword = function (password, confirmpassword) {
            var password_object = {"password": password, "confirmpassword": confirmpassword, "type": "update"};
            signupFactory.resetPasswordPost(password_object).then(function (data) {
                debugAlert(JSON.stringify(data));
                $scope.status = data;
            });
        };

        $scope.showColors = function () {
            settingsFactory.getColorsURLGet().then(function (data) {
                debugAlert(JSON.stringify(data));
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

    }]);
