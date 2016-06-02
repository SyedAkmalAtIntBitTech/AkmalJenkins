/* Sandeep-Kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
brndBotSignupApp.controller("onboardingController", ['$scope', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', function ($scope, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory) {
        $scope.saveUser = function (userDetails) {
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