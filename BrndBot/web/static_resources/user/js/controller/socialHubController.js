socialhubFlowApp.controller("controllerSocial", ['$scope', '$rootScope', '$location', 'settingsFactory', 'socialPostFactory', function ($scope, $rootScope, $location, settingsFactory, socialPostFactory) {

        $scope.getFacebookDetails = function () {
            var fbdata = JSON.stringify({access_token_method: "getAccessToken"});
            settingsFactory.facebookPost(fbdata).then(function (data) {
                var facebookDetails = data.d.message.split(",");
                var facebookDetailsArray = facebookDetails;
                if (facebookDetailsArray[1] === "null") {
                    $scope.managepage=true;
                    $scope.user_profile_page = " - ";
                    $scope.fb_default_page_name = " - ";
                    $("#facebook").text("Login");
                    $("#fbclear").hide();
                } else {
                    $scope.user_profile_page = facebookDetailsArray[1];
                    $scope.fb_default_page_name = facebookDetailsArray[2];
                    $("#fbclear").show();
                    $scope.managepage=false;
                }
            });
        };

        $scope.clearFacebookDetails = function () {
            var data = JSON.stringify({access_token_method: "clearFacebookDetails"});
            settingsFactory.facebookPost(data).then(function (data) {
                alert(JSON.stringify(data));
                $scope.getFacebookDetails();
            });
        };
        
        
        $scope.getManagePage = function () {
            var data = JSON.stringify({redirectUrl: "user/social"});
            settingsFactory.fbLoginPost(data).then(function (data) {
                window.location = data.d.details[0];
            });
        };
        
        
        $scope.checkForCode = function () {
            var code = $scope.getUrlParameter("code");
            if (typeof code !== "undefined") {
                settingsFactory.fbGetTokenGet(code).then(function (data) {
                    $scope.setDefaultManagePage=true;
                    $scope.facebook = true;
                    $scope.managepagesettings = true;
                    $scope.fbPagesDetails = data.d.details[0].fbPages;
                    $scope.fbProfileName = data.d.details[0].user_profile_name;
                });
            }
        };
        
        $scope.getUrlParameter = function (sParam) {
            var sPageURL = decodeURIComponent(window.location.search.substring(1)),
                    sURLVariables = sPageURL.split('&'),
                    sParameterName,
                    i;

            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');

                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : sParameterName[1];
                }
            }
        };


        $scope.getTwitterDetails = function () {
            var twitterdata = JSON.stringify({access_token_method: "getAccessToken"});
            settingsFactory.twitterPost(twitterdata).then(function (data) {
                var twitterData = data.d.message.split(",");
                var twitterprofileName = twitterData[2];
                if (typeof twitterprofileName === "undefined") {
                    $scope.twitterProfileName = " - ";
                    $("#twitterLogoutButton").hide();
                    $("#twitterLogInButton").show();
                } else {
                    $scope.twitterProfileName = twitterprofileName;
                    $("#twitterLogoutButton").show();
                    $("#twitterLogInButton").hide();
                }
            });
        };
        
        $scope.getAuthURLFromSocialHub = function () {
            alert("dsfghjkl");
            $scope.showTwitterPopup = true;
            var data = {redirectUrl: "user/social"};
            settingsFactory.twitterLoginGet(data).then(function (data) {
//                alert(JSON.stringify(data));
                $scope.getTwitterPin = data.d.details[0];
            });
        };
       

        $scope.setTwitterAccessTokenFromSocialHub = function (pinCode) {
            var pin = pinCode;
            settingsFactory.twitterGetTokenGet(pin).then(function (data) {
                if (pin.length > 0) {
                    $location.path('user/social');
                    $scope.showTwitterPopup = false;
                    $scope.getTwitterDetails(); 
                } else {
                    alert(pinerror);
                    $("#pinTextBox").focus();
                }
            });
        };
 
 
        $scope.clearTwitterDetails = function () {
            var data = JSON.stringify({access_token_method: "clearTwitterDetails"});
            alert(data);
            settingsFactory.twitterPost(data).then(function (data) {
                $scope.getTwitterDetails();
            });
        };
        

        $scope.postToSelectedPage = function () {
            var addDafaultmanagePage = $("#setDefaultManagePage").prop('checked');
//            var data = JSON.stringify({access_token_method: "setAccessToken"});
            var data = "";
            if (addDafaultmanagePage) {
                data = JSON.stringify({
                    access_token_method: "setAccessToken",
                    access_token: $rootScope.CurrentFbAccessToken,
                    default_page_name: $rootScope.CurrentFbPageName,
                    fb_user_profile_name: $rootScope.FbProfileName
                });
                $scope.facebook = false;
                window.location = getHost() + "user/social";
            } else {
                alert("Please select any page and set as Default.");
            }
            settingsFactory.facebookPost(data).then(function (data) {
            });
        };
        
        $scope.setPageAccessToken = function (accessToken, pageName, profileName) {
           $rootScope.CurrentFbAccessToken = accessToken;
           $rootScope.CurrentFbPageName = pageName;
           $rootScope.FbProfileName = profileName;
       };

        $scope.hideFbPopup = function ()
        {
            $scope.managepagesettings = false;
        };

        $scope.closeTwitterPopup = function ()
        {
            $scope.showTwitterPopup = false;
        };

    }]);
