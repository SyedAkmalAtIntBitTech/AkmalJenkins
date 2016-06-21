marketinghubFlowApp.controller("marketingHubController", ['$scope', 'settingsFactory', 'emailListFactory', 'emailDraftFactory', 'emailFactory', function ($scope, settingsFactory, emailListFactory, emailDraftFactory, emailFactory) {
        
        $scope.emailsettingsdetails=false;
        $scope.emaildraftdetails=false;
        $scope.emallistdetails=false;
//        $scope.emailHistoryDetails=false;
        $scope.emailSettings='';
        $scope.createEmailListPopup=false;
        
        
        
        $scope.displayAllEmailDrafts = function(){    
        emailDraftFactory.displayAllEmailDraftsGet().then(function(data){  
        if (data.nodrafts === "yes"){
                    $scope.emaildraftnumber = '0';
                    $scope.emaildraftsstatus = "No email drafts present";
                }else {
                    $scope.emaildrafts = data.emaildrafts;
                    $scope.emaildraftdetails=true;
                } 
                
        });
        };
        
        $scope.deleteDrafts = function(type,id)
        {
            var delid=id+",";
        var message;
        var requestBody;
        var responseMessage;         
        if (type === "deleteMultiple") {
            message = multidraftconfirm;
            requestBody = {"type": "deleteSelected",
                "draft_ids": selected_emaildrafts_to_delete, "entity_type": "null"};
            responseMessage = multidraftdeleted;
        } else if (type === "delete") {
            message = singledraftconfirm;
            requestBody = {"type": "delete",
                            "draft_ids": delid};
            responseMessage = singledraftdeleted;
        }
            emailDraftFactory.deleteEmailDraftPost().then(function(data){
            alert(JSON.stringify(data)); 
            });
        };
        
//        $scope.showdraftpopup = function(){
//            alert("popup");
////        $slider=2;
////        sliderDialog = "#emaildraftpopup";
////        prevSliderDialog = "#emaildraftpopup";    
//        emailDraftFactory.getEmailDraftGet().then(function(data){
//        if (data === ""){
//                        $scope.emaildraftsstatus = noemaildraft;
//                    }else {     
//                        $scope.htmlbody = data.htmlbody;
//                        $('#draftshow').empty().append(data.htmlbody);
//                    }    
//        });
//        $scope.id = Id;
//                $scope.categoryid = categoryId;
//                $scope.emailsubject = emailSubject;
//                $scope.editdate = editdate;
//                $scope.subcategoryid = subCategoryId;
//                $scope.mindbodyId = mindbodyId;
//                $scope.lookupId = lookupId;
//                $('#slider-button').click(); 
//        };


        $scope.emailListGet = function (emailListName,requestMap) {
                 $scope.emallistdetails=true;
            emailListFactory.emailListGet(emailListName,requestMap).then(function (data) {
                 alert(JSON.stringify(data));
            });
        };
        
        $scope.getEmailSettings = function () {
            settingsFactory.getEmailSettingsGet().then(function (data) {
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
//            settingsFactory.saveEmailSettingsPost().then(function (data) {
//            });
//        };
        
        $scope.getFooterDetails = function (){
            alert("footerDetails");
            settingsFactory.getAllPreferencesGet().then(function(data){
                alert("........."+JSON.stringify(data));
                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
                alert("......");
                 $scope.company = $scope.footerDetails;
                 
                alert(JSON.stringify($scope.company));
            });
       };
       
       $scope.displayEmailHistory = function (){
           alert("history");
           emailFactory.sendEmailGet().then(function(data){
               alert(JSON.stringify(data));
               $scope.email_history = JSON.parse(data.d.details);
//                   $scope.emailHistoryDetails=true;
           });
       };
        
        $scope.emailFooterPopup = function()
        {
            $scope.emailFooterPopupDetails = true;
            
        };
        
        $scope.hideEmailFooterPopupDetails = function()
        {
            $scope.emailFooterPopupDetails = false;
        };
        
        $scope.showDraftPopup = function()
        {
            alert("popup");
            $scope.showSavedEmailDraftPopup = true;
        };
        
        $scope.closeSavedEmailDraftPopup = function()
        {
            $scope.showSavedEmailDraftPopup = false;
        };
          
        
//        $scope.showdraftpopup = function (Id,categoryId,emailSubject,editdate,subCategoryId,mindbodyId,lookupId){
//            $slider=2;
////            alert("draftid.. "+Id+" ..categoryId.. "+categoryId+" ..mindbodyId.. "+mindbodyId+" ..lookupId.. "+lookupId+" ..emailSubject.. "+emailSubject);
//            sliderDialog = "#emaildraftpopup";
//            prevSliderDialog = "#emaildraftpopup";
//            emailDraftFactory.getEmailDraftGet(draftid).then(function(data){
//                    if (data === ""){
//                        $scope.emaildraftsstatus = noemaildraft;
//                    }else {     
//                        $scope.htmlbody = data.htmlbody;
//                        $('#draftshow').empty().append(data.htmlbody);
//                    }
//                }).error(function(data, status) {
//                    alert(nodataerror);
//                }); 
////                alert(Id);
//                $scope.id = Id;
//                $scope.categoryid = categoryId;
//                $scope.emailsubject = emailSubject;
//                $scope.editdate = editdate;
//                $scope.subcategoryid = subCategoryId;
//                $scope.mindbodyId = mindbodyId;
//                $scope.lookupId = lookupId;
//                $('#slider-button').click();                    
//           };
            
//            $scope.getEmailDraftsById = function(draftid)
//            {
//                    emailDraftFactory.getEmailDraftGet(draftid).then(function(data){
//        if (data === ""){
//                        $scope.emaildraftsstatus = noemaildraft;
//                    }else {     
//                        $scope.htmlbody = data.htmlbody;
//                        $('#draftshow').empty().append(data.htmlbody);
//                    }    
//        });
//        $scope.id = Id;
//                $scope.categoryid = categoryId;
//                $scope.emailsubject = emailSubject;
//                $scope.editdate = editdate;
//                $scope.subcategoryid = subCategoryId;
//                $scope.mindbodyId = mindbodyId;
//                $scope.lookupId = lookupId;
//                $('#slider-button').click(); 
//        };
        
        $("#closedraftpopup").click(function(){
                sliderDialog = "#emaildraftpopup";
                prevSliderDialog = "#emaildraftpopup";
                $('#slider-button').click();
            });
        
        
      $scope.changeFooterDetails = function (company){
      var footerAddress = company.address;
      var footerWebsiteUrl = company.websiteUrl;
      var footerFacebookUrl = company.facebookUrl;
      var footerTwitterUrl = company.twitterUrl;
      var footerInstagramUrl = company.instagramUrl;
      var footerPopupDeatils = '{"footerFacebookUrl":"'+footerFacebookUrl+'","footerTwitterUrl":"'+footerTwitterUrl+'","footerInstagramUrl":"'+footerInstagramUrl+'","footerWebsiteUrl":"'+footerWebsiteUrl+'","footerAddress":"'+footerAddress+'"}';
      alert(footerPopupDeatils);
      settingsFactory.setFooterPost(footerDetails).then(function(data){
          $scope.getFooterDetails();
      });
      };

        $scope.addEmailList = function()
        {
            $("#fade").show();
            $scope.createEmailListPopup = true;
        };
        
        $scope.closeCreateEmailList = function()
        {
            $scope.createEmailListPopup = false;
            $("#fade").hide();
        };
        
        $scope.createEmailList = function(email,emailListObject)
        {
            var list_name = email.listName;
            var list_description = email.listDescription;
            var default_from_name = email.deafultFromName;
            alert(list_name);
            alert(list_description);
            alert(default_from_name);
            emailListFactory.emailListSavePost(emailListObject).then(function(data){
                
            });
        };

    }]);