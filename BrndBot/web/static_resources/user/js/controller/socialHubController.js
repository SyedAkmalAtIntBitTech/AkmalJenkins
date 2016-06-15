socialhubFlowApp.controller("controllerSocial", ['$scope', '$location', 'settingsFactory', 'socialPostFactory', function ($scope, $location, settingsFactory, socialPostFactory) {

        $scope.getFacebookDetails = function () {
        var fbdata = JSON.stringify({access_token_method: "getAccessToken"});
            settingsFactory.facebookPost(fbdata).then(function (data){
//            alert(JSON.stringify(data.d.message)); 
        var facebookDetails = data.d.message.split(",");
            var facebookDetailsArray = facebookDetails;
             if (facebookDetailsArray[1] === "null") {
        $scope.user_profile_page = " - ";
        $scope.fb_default_page_name = " - ";
             $("#facebook").text("Login");
             $("#fbclear").hide();
              $scope.getLoginDetails();

        } else {
        $scope.user_profile_page = facebookDetailsArray[1];
        $scope.fb_default_page_name = facebookDetailsArray[2];
             $("#fbclear").show();
        }
        });
        };
        
        $scope.getLoginDetails = function () {
                 alert("asdfah");
                 var data = { redirectUrl: "user/imagegallery" };
                 socialPostFactory.facebookPost(data).then (function(data) {      
              });
            };
        
        $scope.getTwitterDetails = function () {
        var twitterdata = JSON.stringify({access_token_method: "getAccessToken"});
                settingsFactory.twitterPost(twitterdata).then(function (data){
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


        $scope.clearFacebookDetails = function () {
        var data =JSON.stringify({ access_token_method: "clearFacebookDetails"});
            alert(data);
            settingsFactory.facebookPost(data).then(function (data){
                alert(JSON.stringify(data));
                $scope.getFacebookDetails();
            });
//            $("#login_button").show();
//            $("#facebook").hide();
//            $("#fbclear").hide();
        };
        

        $scope.clearTwitterDetails = function () {
        var data =JSON.stringify({ access_token_method: "clearTwitterDetails"});
            alert(data);
            settingsFactory.twitterPost(data).then(function (data){
                alert(JSON.stringify(data));
                $scope.getTwitterDetails();
            });
            

        };
        
        
        
        $scope.getManagePage = function () {
        $scope.facebook = true;
        alert("test");
        var data = JSON.stringify({ redirectUrl: "user/social" })
        .success(function (data) {
            alert("data");
            window.location = data.d.details[0];

        });
        
        };
        
    

                
//        $scope.getManagePage = function () {
//        $scope.facebook = true;
//        alert("...");
//        $http({
//            url: "http://localhost:8080/BrndBot/" + 'settings/fbAuthURL',
//            method: "POST",
//            data: JSON.stringify({
//                redirectUrl: "user/social"
//            })
//        }).success(function (data) {
//            alert("data");
//            window.location = data.d.details[0];
//
//        });
//    };
//    

//********************

//        $scope.getLoginDetails = function () {
//            alert("asdfah");
//            var data = { redirectUrl: "user/imagegallery" };
//        socialPostFactory.facebookPost(data).then (function(data) {      
//        });
//    };
//        
   
        $scope.getAuthURLFromSocialHub = function () {
        $scope.showTwitterPopup=true;
        var data = { redirectUrl: "user/social" };
        settingsFactory.twitterLoginGet(data).then(function (data){
            $scope.getTwitterPin = data.d.details[0];
        });
        };

        
        
        $scope.setTwitterAccessTokenFromSocialHub = function (pinCode) {
        var pin = pinCode;
        if (pin.length > 0) {
            settingsFactory.twitterGetTokenGet(pin).then(function (data){
                alert(JSON.stringify(data));
            });
//         $.ajax({
//            url: getHost() + 'settings/twitterGetToken/' + pin,
//            method: 'GET',
//            success: function (responseText) {
//                $("#twitterSetPinPopUpFromSocialhub").hide();
//                window.location = getHost() + "user/social";
//            },
//            error: function (jqXHR, textStatus, errorThrown) {
//                alert(JSON.stringify(jqXHR));
//            }
//         });

         } else {
            alert(pinerror);
            $("#pinTextBox").focus();
         }
        };

    $scope.closetwitterpopup = function()
    {
        $scope.twitterpopup = false;      
    };

        }]);

