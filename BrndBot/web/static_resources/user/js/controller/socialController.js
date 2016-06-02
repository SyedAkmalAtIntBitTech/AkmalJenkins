/* sandeep-kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
socialApp.controller("socialController", ['$scope', 'subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', function ($scope, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory) {
        $scope.getManagePage = function () {
            onboardingFactory.saveUserPost().then(function (data) {

            });
        };
        $scope.isDefaultTwitterAccountSet = function (){
            
        };
        $scope.getUserImages = function (){
            
        };
        $scope.getUrls = function (){
            
        };
        $scope.postToFacebook = function(){
            
        };
        $scope.postToTwitter = function(){
            
        };
        
    }]);
