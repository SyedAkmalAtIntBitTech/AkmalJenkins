marketinghubFlowApp.controller("marketingHubController", ['$scope', 'settingsFactory', 'emailListFactory', 'emailDraftFactory', function ($scope, settingsFactory, emailListFactory, emailDraftFactory) {
        
        $scope.emailsettingsdetails=false;
        $scope.emallistdetails=false;
        $scope.emailHistoryDetails=false;
//        $scope.emailDraftsDetails=false;
        $scope.emailSettings='';
        
        
        
        $scope.getAllEmailDrafts = function(){
        alert("draft");
        
        emailDraftFactory.displayAllEmailDraftsGet().then(function(data){  
        if (data.nodrafts === "yes"){
                    $scope.emaildraftnumber = '0';
                    $scope.emaildraftsstatus = "No email drafts present";
                }else {
                    $scope.emaildrafts = data.emaildrafts;
                } 
        });
        };


        $scope.emailListGet = function (emailListName,requestMap) {
            alert("getemaillist");
                 $scope.emallistdetails=true;
            emailListFactory.emailListGet(emailListName,requestMap).then(function (data) {
                 alert(JSON.stringify(data));
            });
        };
        
        $scope.getEmailSettings = function () {
            settingsFactory.getEmailSettingsGet().then(function (data) {
                alert("emailsettings");
                $scope.emailsettingsdetails=true;
            });

        };

        $scope.saveEmailSettings = function () {
            var from_address = $("#from_address").val();
            var reply_email_address = $("#reply_email_address").val();
            settingsFactory.saveEmailSettingsPost(from_address,reply_email_address).then(function (data) {
                alert(from_address);
                alert(reply_email_address);
            });
        };

//        $scope.saveEmailSettings = function (emailSettings) {
//            alert("...");
//            var from_address = emailSettings.fromAddress;
//            var reply_email_address = emailSettings.replyAddress;
//            alert(from_address);
//            alert(reply_email_address);
//            settingsFactory.saveEmailSettingsPost(email_settings).then(function (data) {
//            });
//        };
        
        $scope.getFooterDetails = function (){
            alert("footerDetails");
            settingsFactory.getAllPreferencesGet().then(function(data){
                alert("........."+JSON.stringify(data));
                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
                 
            });
       };
       
//       $scope.displayEmailHistory = function (){
//           alert("history");
//           emailFactory.sendEmailGet().then(function(data){
//           
//           if (data.d.details[0] === "[]"){
//                       
//                       $scope.email_history = JSON.parse(data.d.details);
//                       $("#nohistorydiv").empty().text(noemailhistory);
//                       $(".nohiswid").css("width","250px");
//                       $("#historydiv").hide();
//                   }else {
//                       $("#historydiv").show();
//                       $scope.email_history = JSON.parse(data.d.details);
//                   }
//                   $scope.emailHistoryDetails=true;
//           });
//       };
        
        $scope.emailFooterPopup = function()
        {
            $scope.emailFooterPopupDetails = true;
            
        };
        
        $scope.hideEmailFooterPopupDetails = function()
        {
            $scope.emailFooterPopupDetails = false;
        };
        
        
        
      $scope.changeFooterDetails = function (company){
          alert("popup");
          
          
      var footerAddress = company.name;
      var footerWebsiteUrl = company.websiteUrl;
      var footerFacebookUrl = company.facebookLink;
      var footerTwitterUrl = company.twitterLink;
      var footerInstagramUrl = company.instagramLink;
      var footerPopupDeatils = '{"footerFacebookUrl":"'+footerFacebookUrl+'","footerTwitterUrl":"'+footerTwitterUrl+'","footerInstagramUrl":"'+footerInstagramUrl+'","footerWebsiteUrl":"'+footerWebsiteUrl+'","footerAddress":"'+footerAddress+'"}';
      alert(footerPopupDeatils);
      settingsFactory.setFooterPost(company).then(function(data){

      });
      };
 
       



    }]);