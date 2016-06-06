/* sandeep-kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

socialFlowApp.controller("socialController", ['$scope','$location','$window','subCategoryFactory', 'settingsFactory', 'organizationFactory', 'onboardingFactory', function ($scope,$location,$window, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory) {
        $scope.getManagePage = function () {
            var data=JSON.stringify({redirectUrl: "user/social"});
            settingsFactory.fbLoginPost(data).then(function(data){
                $window.location = data.d.details[0];               
           });
        
        };
        $scope.isDefaultTwitterAccountSet = function (){
            var data= JSON.stringify({access_token_method: "getAccessToken"});
            settingsFactory.twitterPost(data).then(function (data){
                var twitterAccessToken = data.d.message;
                if ((twitterAccessToken === null) || (twitterAccessToken === ""))
                {
                    settingsFactory.twitterLoginGet().then(function (data1){
                        alert(JSON.stringify(data1));
//                        $("#twitterSetPinPopUp").show();
//                        $("#twitterlink").html("<a href='" + responseText.d.details[0] + "' target='_blank'>get your pin</a>");
                    });
                } else {
//                    $("#twitterSetPinPopUp").hide();
//                        $window.location = getHost() + "user/twitterpost";
                        $location.path('/twitterpost');
                }
                alert(JSON.stringify(data.d.message));
            });
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
