marketinghubFlowApp.controller("marketingHubController", ['$scope', 'settingsFactory', 'emailListFactory', 'emailDraftFactory', 'emailFactory', function ($scope, settingsFactory, emailListFactory, emailDraftFactory, emailFactory) {

        $scope.emailsettingsdetails = false;
        $scope.emaildraftdetails = false;
        $scope.emallistdetails = false;
//        $scope.emailHistoryDetails=false;
        $scope.emailSettings = '';
        $scope.createEmailListPopup = false;

        $scope.displayAllEmailDrafts = function () {
            emailDraftFactory.displayAllEmailDraftsGet().then(function (data) {
                if (data.nodrafts === "yes") {
                    $scope.emaildraftnumber = '0';
                    $scope.emaildraftsstatus = "No email drafts present";
                } else {
                    $scope.emaildrafts = data.emaildrafts;
                    $scope.emaildraftdetails = true;
                }
            });
        };

        $scope.emailDraftCheckbox = function (id) {
            content = '<input type="checkbox" id="' + 'entityid' + id + '" hidden="">';
            var htm = $("#" + id).html();
            var selected_schedule_id = id;
            if (htm.contains('class="check-icon"')) {
                selected_emaildrafts_to_delete = selected_emaildrafts_to_delete.replace(selected_schedule_id + ",", "");
                count -= 1;
                $("#" + id).html(content);
            }
            else
            {
                selected_emaildrafts_to_delete = selected_schedule_id + "," + selected_emaildrafts_to_delete;
                count += 1;
                $("#" + id).html(content + '<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#" + id).toggleClass('selection-icon');
            $("#" + id).toggleClass('selection-icon-selected');
            if (count > 0)
            {
                $("#deleteEmaildraft").show();
            }
            if (count === 0)
            {
                $("#deleteEmaildraft").hide();
            }
        };

        $scope.deleteDrafts = function (type, id)
        {
            alert("...");
            var delid = id + ",";
            var message;
            var requestBody;
            var responseMessage;
            if (type === "deleteMultiple") {
                message = multidraftconfirm;
                requestBody = {"type": "deleteSelected",
                    "draft_ids": selected_emaildrafts_to_delete, "entity_type": "null"};
                responseMessage = multidraftdeleted;
                $("#deleteEmaildraft").hide()
            }
            else if (type === "delete") {
                message = singledraftconfirm;
                requestBody = {"type": "delete",
                    "draft_ids": delid};
                responseMessage = singledraftdeleted;
            }
//            var requestBody = {"type": "delete",
//                           "draft_ids": draftid+","
//                        };
            alert(JSON.stringify(requestBody));
            emailDraftFactory.deleteEmailDraftsPost(requestBody).then(function (data) {
                alert(JSON.stringify(data));
                $scope.displayAllEmailDrafts();
            });
        };

        $scope.editDrafts = function (draft_id, category_id, email_subject, sub_category_id, mindbodyId, lookupId) {
            var draftdetails = {"draftid": draft_id, "email_subject": email_subject, "category_id": category_id,
                "sub_category_id": sub_category_id};
            alert(JSON.stringify(draftdetails));
            emailDraftFactory.getEmailDraftGet(draft_id).then(function (data) {
                alert(JSON.stringify(data));
                if (data === "false") {
                    alert(draftsavingerror);
                } else {
//                    draftId=null&categoryId=18&subCategoryId=7&emailSubject=sub&mindbodyId=75031&LookupId=4
//                  location.getPath+ "user/emaileditor?draftId='+draft_id+'&emailSubject='+email_subject+'&categoryId='+category_id+'&subCategoryId='+sub_category_id+'&mindbodyId='+mindbodyId+'&LookupId='+lookupId, ";
                    window.open(getHost() + 'user/emaileditor?draftId=' + draft_id + '&emailSubject=' + email_subject + '&categoryId=' + category_id + '&subCategoryId=' + sub_category_id + '&mindbodyId=' + mindbodyId + '&LookupId=' + lookupId, "_self");

                }
            });
        };

        $scope.getEmailSettings = function () {
            settingsFactory.getEmailSettingsGet().then(function (data) {
                $scope.emailsettingsdetails = true;
            });
        };

        $scope.saveEmailSettings = function () {
            var from_address = $("#from_address").val();
            var reply_email_address = $("#reply_email_address").val();
            settingsFactory.saveEmailSettingsPost(from_address, reply_email_address).then(function (data) {
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

        $scope.getFooterDetails = function () {
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
                $scope.company = $scope.footerDetails;
//                alert(JSON.stringify($scope.company));
            });
        };

        $scope.changeFooterDetails = function (company) {
            var footerAddress = company.address;
            var footerWebsiteUrl = company.websiteUrl;
            var footerFacebookUrl = company.facebookUrl;
            var footerTwitterUrl = company.twitterUrl;
            var footerInstagramUrl = company.instagramUrl;
            var footerPopupDeatils = '{"footerAddress":"' + footerAddress + '","footerWebsiteUrl":"' + footerWebsiteUrl + '","footerFacebookUrl":"' + footerFacebookUrl + '","footerTwitterUrl":"' + footerTwitterUrl + '","footerInstagramUrl":"' + footerInstagramUrl + '"}';
            alert(footerPopupDeatils);
            $scope.emailFooterPopupDetails = false;
            $scope.getFooterDetails();
            settingsFactory.setFooterPost(footerDetails).then(function (data) {
            });
        };

        $scope.emailFooterPopup = function ()
        {
            $scope.emailFooterPopupDetails = true;
        };

        $scope.hideEmailFooterPopupDetails = function ()
        {
            $scope.emailFooterPopupDetails = false;
        };


        $scope.displayEmailHistory = function () {
            alert("history");
            emailFactory.sendEmailGet().then(function (data) {
//                if (data.d.details[0] === "[]"){
//                       
//                       $scope.email_history = JSON.parse(data.d.details);
//                       $("#nohistorydiv").empty().text(noemailhistory);
//                       $(".nohiswid").css("width","250px");
//                       $("#historydiv").hide();
//                   }else {
//                       $("#historydiv").show();
//                       $scope.email_history = JSON.parse(data.d.details);
//                   }
                alert("test" + JSON.stringify(data));
                $scope.email_history = JSON.parse(data.d.details);
//                   $scope.emailHistoryDetails=true;
            });
        };

        $scope.showDraftPopup = function (Id, categoryId, emailSubject, editdate, subCategoryId, mindbodyId, lookupId)
        {
            $("#fade").show();
            $scope.savedEmailDraftPopup = true;
//            $slider=2;
            alert("draftid.. " + Id + " ..categoryId.. " + categoryId + " ..mindbodyId.. " + mindbodyId + " ..lookupId.. " + lookupId + " ..emailSubject.. " + emailSubject);
//            sliderDialog = "#emaildraftpopup";
//            prevSliderDialog = "#emaildraftpopup";
            emailDraftFactory.getEmailDraftGet(Id).then(function (data) {
                if (data === "") {
                    $scope.emaildraftsstatus = noemaildraft;
                } else {
                    $scope.htmlbody = data.htmlbody;
                    $('#draftshow').empty().append(data.htmlbody);
                }
            });
//                alert(Id);
            $scope.id = Id;
            $scope.categoryid = categoryId;
            $scope.emailsubject = emailSubject;
            $scope.editdate = editdate;
            $scope.subcategoryid = subCategoryId;
            $scope.mindbodyId = mindbodyId;
            $scope.lookupId = lookupId;
            $('#slider-button').click();
        };

        $scope.closeSavedEmailDraftPopup = function ()
        {
            $scope.savedEmailDraftPopup = false;
            $("#fade").hide();
        };

        $scope.addEmailList = function ()
        {
            $("#fade").show();
            $scope.createEmailListPopup = true;
        };

        $scope.closeCreateEmailList = function ()
        {
            $scope.createEmailListPopup = false;
            $("#fade").hide();
        };
        
        $scope.emailListGet = function () {
            $("#addemlstbtn").show();
            $scope.emallistdetails = true;
            emailListFactory.emailListGet("?update=allEmailListWithNoOfContacts&emailListName=null").then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.emailLists = parseData.allEmailListWithNoOfContacts.user;
                $scope.emailListsMindbody = parseData.allEmailListWithNoOfContacts.mindbody;
            });
        };

        $scope.createEmailList = function (email)
        {
            var emailListDetails = {"emailListName": email.listName, "defaultFromName": email.deafultFromName, "listDescription":email.listDescription, "update": "addEmailList"};
            emailListFactory.emailListSavePost(emailListDetails).then(function (data) {
                            alert(JSON.stringify(data));
                            $scope.createEmailListPopup = false;
                            $("#fade").hide();
                            $scope.emailListGet();

            });
        };
        
        $scope.deleteEmailList = function ()
        {
           if (confirm("Are you sure, You want to Delete Email List?")){
                        var noofemaillist="";
                        var selected_email_lists="";
                        $("input[type=checkbox]:checked").each ( function() {
                            selected_email_lists +=$(this).val()+",";
                            noofemaillist=selected_email_lists.split(',');
                        });
                        if (noofemaillist.length>2){
                            var EmailLists = {"update":"deleteAllEmailLists", "emailListName": selected_email_lists};
                        }
                        else {
                            var EmailLists = {"update":"deleteEmailLists", "emailListName": selected_email_lists};
                        }
                        emailListFactory.emailListSavePost(EmailLists).then(function(data){
                            if (data.d.operationStatus.statusCode === "Success") {
                                alert(JSON.stringify(data));
                                $scope.emailListGet();
                            };
                            
                        });
                };
        };

    }]);