socialhubFlowApp.controller("controllerSocial", ['$scope', '$location', 'settingsFactory', 'socialPostFactory', function ($scope, $location, settingsFactory, socialPostFactory) {

        $scope.getFacebookDetails = function () {
            var fbdata = JSON.stringify({access_token_method: "getAccessToken"});
            settingsFactory.facebookPost(fbdata).then(function (data) {
//            alert(JSON.stringify(data.d.message)); 
                var facebookDetails = data.d.message.split(",");
                var facebookDetailsArray = facebookDetails;
                if (facebookDetailsArray[1] === "null") {
                    $scope.user_profile_page = " - ";
                    $scope.fb_default_page_name = " - ";
                    $("#facebook").text("Login");
                    $("#fbclear").hide();
                } else {
                    $scope.user_profile_page = facebookDetailsArray[1];
                    $scope.fb_default_page_name = facebookDetailsArray[2];
                    $("#fbclear").show();
                }
            });
        };

        $scope.clearFacebookDetails = function () {
            var data = JSON.stringify({access_token_method: "clearFacebookDetails"});
            alert(data);
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
                    alert(JSON.stringify(data));
                    $scope.facebook = true;
                    $scope.managepage = true;
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
//            alert(JSON.stringify(data.d.message)); 
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
            $scope.showTwitterPopup = true;
            var data = {redirectUrl: "user/social"};
            settingsFactory.twitterLoginGet(data).then(function (data) {
                $scope.getTwitterPin = data.d.details[0];
            });
        };
       

        $scope.setTwitterAccessTokenFromSocialHub = function (pinCode) {
            var pin = pinCode;
            settingsFactory.twitterGetTokenGet(pin).then(function (data) {
                if (pin.length > 0) {
                    alert(JSON.stringify(data));
                    window.location = getHost() + "user/social";
//                    $location.path('user/social');
//                $route.reload();
                } else {
                    alert(pinerror);
                    $("#pinTextBox").focus();
                }
            });
        };
        
        //  $scope.setTwitterAccessTokenFromSocialHub = function (pinCode) {
//        var pin = pinCode;
//        if (pin.length > 0) {
//            settingsFactory.twitterGetTokenGet(pin).then(function (data){
//                alert(JSON.stringify(data));
////                $location.path('/user/social');
//                $route.reload();
//                
//            });
//           
//            
////         $.ajax({
////            url: getHost() + 'settings/twitterGetToken/' + pin,
////            method: 'GET',
////            success: function (responseText) {
////                $("#twitterSetPinPopUpFromSocialhub").hide();
////                window.location = getHost() + "user/social";
////            },
////            error: function (jqXHR, textStatus, errorThrown) {
////                alert(JSON.stringify(jqXHR));
////            }
////         });
//
//         } else {
////            alert(pinerror);
////            $("#pinTextBox").focus();
//            alert("please");
//         }
//        };


        $scope.clearTwitterDetails = function () {
            var data = JSON.stringify({access_token_method: "clearTwitterDetails"});
            alert(data);
            settingsFactory.twitterPost(data).then(function (data) {
                alert(JSON.stringify(data));
                $scope.getTwitterDetails();
            });
        };
        

        $scope.postToSelectedPage = function () {
            var addDafaultmanagePage = $("#setDefaultManagePage").prop('checked');
            var data = JSON.stringify({access_token_method: "setAccessToken"});
            settingsFactory.facebookPost(data).then(function (data) {
                alert(JSON.stringify(data));
                if (addDafaultmanagePage) {

                } else {
                    alert("Please select any page and set as Default.");
                }
            });

        };


//        $scope.postToSelectedPage = function () {
//        var addDafaultmanagePage = $("#setDefaultManagePage").prop('checked');
//        if (addDafaultmanagePage) {
//            $http({
//                url: getHost() + 'settings/facebookDetails.do',
//                method: "POST",
//                data: JSON.stringify({
//                    access_token_method: "setAccessToken",
//                    access_token: localStorage.getItem("CurrentFbAccessToken"),
//                    default_page_name: localStorage.getItem("CurrentFbPageName"),
//                    fb_user_profile_name: localStorage.getItem("FbProfileName")
//                })
//            }).success(function (data) {
//                $("#fbmanagePagePopUp").hide();
//                window.location = getHost() + "user/social";
//            });
//        } else {
//            alert("Please select any page and set as Default.");
//        }
//
//    };

        $scope.hideFbPopup = function ()
        {
            $scope.facebook = false;
        };


        $scope.closeTwitterPopup = function ()
        {
            $scope.showTwitterPopup = false;
        };

    }]);

