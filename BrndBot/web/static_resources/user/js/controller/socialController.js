/* sandeep-kumar
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

socialFlowApp.controller("socialController", ['$scope','$location','$window','subCategoryFactory','settingsFactory', 'organizationFactory', 'onboardingFactory','companyMarketingProgramFactory', function ($scope,$location,$window, subCategoryFactory, settingsFactory, organizationFactory, onboardingFactory,companyMarketingProgramFactory) {
        $scope.showTwitterPopup=false;
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
//                      $("#twitterSetPinPopUp").hide();
                        $scope.showTwitterPopup=false;
                        $location.path('/twitterpost');
                }
                alert(JSON.stringify(data.d.message));
            });
        };
        $scope.getUserImages = function (){ 
            
        };
        $scope.getUrls = function (){
            companyMarketingProgramFactory.getAllUserMarketingProgramsUserIdGet().then(function (data){
                $scope.urls= data;                
            });
        };
        $scope.postToFacebook = function(){
            
        };
        $scope.postToTwitter = function(){
            
        };
        $scope.checkForCode = function () {
            settingsFactory.fbGetTokenGet().then(function (data){   
                        alert(JSON.stringify(data.d));
                    });
//                var code = $scope.getUrlParameter("code");
//                if (typeof code !== "undefined") {
//                    settingsFactory.fbGetTokenGet().then(function (data){   
//                        alert(data);
//                    });
//                    $http({
//                        url: getHost() + 'settings/fbGetToken/' + code,
//                        method: "GET"
//                    }).success(function (data) {
//                        $("#fbmanagePagePopUp").show();
//                        $scope.fbPagesDetails = data.d.details[0].fbPages;
//                    });
//                }
        };
//        $scope.getUrlParameter = function(sParam) {
//            var sPageURL = decodeURIComponent($window.location.search.substring(1)),
//                    sURLVariables = sPageURL.split('&'),
//                    sParameterName,
//                    i;
//
//            for (i = 0; i < sURLVariables.length; i++) {
//                sParameterName = sURLVariables[i].split('=');
//
//                if (sParameterName[0] === sParam) {
//                    return sParameterName[1] === undefined ? true : sParameterName[1];
//                }
//            }
//        };
        $scope.changeTwitterPostType = function (){
            if($scope.showTwitterLink === true){
                $scope.showTwitterLink=false;
            }
            else{
                $scope.showTwitterLink=true;
            }
        };
            
        
    }]);
