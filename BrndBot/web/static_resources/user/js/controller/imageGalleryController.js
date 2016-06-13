imagesFlowApp.controller("imageGalleryController", ['$scope', '$window', '$location', 'signupFactory', 'settingsFactory', 'imagesFactory', 'onboardingFactory', function ($scope, $window, $location, signupFactory, settingsFactory, assetsFactory, onboardingFactory) {
 
alert();

$scope.changeLogo = function (logoImage) {
            var file = logoImage;
            alert(file);
            settingsFactory.changeLogoPost(file).then(function (data) {
                alert(JSON.stringify(data));
                $location.path("signup/choosepalette");
            });
        };
        }]);
