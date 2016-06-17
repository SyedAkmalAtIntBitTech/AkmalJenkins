marketinghubFlowApp.controller("marketingHubController", ['$scope', 'settingsFactory', 'emailListFactory', function ($scope, settingsFactory, emailListFactory) {
        
        $scope.emailsettingsdetails=false;
        $scope.emallistdetails=false;
        $scope.emailHistoryDetails=false;
        $scope.emailDrafts=false;   

        
        $scope.getEmailSettings = function () {
            settingsFactory.getEmailSettingsGet().then(function (data) {
                alert("emailsettings");
                $scope.emailsettingsdetails=true;
            });

        };

        $scope.saveEmailSettings = function () {
            alert(email_settings);
            alert($scope.email_settings);
//            var from_address = $("#from_address").val();
//            var reply_email_address = $("#reply_email_address").val();
            settingsFactory.saveEmailSettingsPost(from_address,reply_email_address).then(function (data) {
//                alert(from_address);
//                alert(reply_email_address);
            });
        };
        
        $scope.emailListGet = function (emailListName,requestMap) {
            alert("getemaillist");
                 $scope.emallistdetails=true;
            emailListFactory.emailListGet(emailListName,requestMap).then(function (data) {
                 alert(JSON.stringify(data));
            });
        };
       
//        $scope.marketingHubDetails = function()
//        {
////            $scope.emailsettingsdetails=false;
//            alert("ssd");
////                   
//
//        }



    }]);