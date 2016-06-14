socialhubFlowApp.controller("controllerSocial", ['$scope', '$location','settingsFactory' , function ($scope, $location,settingsFactory) {

        $scope.getFacebookDetails = function () { 
        var fbdata= JSON.stringify({access_token_method: "getAccessToken"});
        settingsFactory.facebookPost(fbdata).then(function (data){
//            alert(JSON.stringify(data.d.message)); 
            var facebookDetails = data.d.message.split(",");
            var facebookDetailsArray = facebookDetails;
            if (facebookDetailsArray[1] === "null") {
                $scope.user_profile_page = " - ";
                $scope.fb_default_page_name = " - ";
                $("#facebook").text("Login");
            } else {
                $scope.user_profile_page = facebookDetailsArray[1];
                $scope.fb_default_page_name = facebookDetailsArray[2];
                $("#fbclear").show();
            }
        });
        };
        
        
        $scope.getTwitterDetails = function () { 
        var twitterdata= JSON.stringify({access_token_method: "getAccessToken"});
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
        if (confirm(clearconfirm)) {
            $("#fbclear").hide();
            $http({
                method: 'POST',
                url: getHost() + 'settings/facebookDetails.do',
                data: JSON.stringify({
                    access_token_method: "clearFacebookDetails"
                })
            }).success(function (data, status, headers, config) {
                alert(detailsclear);
                $scope.getFacebookDetails();
            }).error(function (data, status, headers, config) {
                alert(nodataerror);
            });
        }
    };
    
 

}]);

