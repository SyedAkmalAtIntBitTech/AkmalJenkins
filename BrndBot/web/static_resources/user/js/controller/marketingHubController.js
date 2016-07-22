marketinghubFlowApp.controller("marketingHubController", ['$scope', '$location', 'settingsFactory', 'emailListFactory', 'emailDraftFactory', 'emailFactory', function ($scope, $location, settingsFactory, emailListFactory, emailDraftFactory, emailFactory) {

//$scope.emailhubHeader = true;
        $scope.addEmailListButton = true;
        $scope.saveEmailSettingsButton = false;
        $scope.deletDraftsButton = false;
        $("#removeselactions").hide;
        $scope.deSelectCheckboxButton = false;
        $scope.selectCheckboxButton = false;
        $scope.showDeleteEmailList = false;
        $scope.emailListName = "";
        $scope.type = "";
        $scope.emailId = "";
        $scope.firstName = "";
        $scope.lastName = "";
        $scope.selectedEmail = "";  
        $scope.unsubscribePopup = false;
        $scope.overlayFade = false;
        $scope.showEmailListDetails = false;
//        $scope.emailListValidation = emailListValidation;

        $scope.displayAllEmailDrafts = function () {
            $scope.activeEmailDrafts = 'activeTab';
            $scope.activeEmailHistory = '';
            $scope.activeEmailSettings = '';
            $scope.activeEmailList = '';
            $scope.emaildropdown = false;
            $scope.saveEmailSettingsButton = false;
            $scope.addEmailListButton = false;
            $scope.showDeleteEmailList = false;
            emailDraftFactory.displayAllEmailDraftsGet().then(function (data) {
                if (data.nodrafts === "yes") {
                    $scope.emaildraftnumber = '0';
                    $scope.emaildraftsstatus = "No email drafts present";
                } else {
                    $scope.emaildrafts = data.emaildrafts;
                    $scope.emailDraftDetails = true;
                }
            });
        };

        $scope.emailDraftCheckbox = function (id) {
            $scope.selectedEmail = id;
            var count = 0;
            var selected_emaildrafts_to_delete = "";

            content = '<input type="checkbox" id="' + 'entityid' + id + '" hidden="">';
            var htm = $("#" + id).html();
            var selected_schedule_id = id;
            if (htm.contains('class="check-icon"')) {
                selected_emaildrafts_to_delete = selected_emaildrafts_to_delete.replace(selected_schedule_id + ",", "");
                count -= 1;
                $("#" + id).html(content);
            } else
            {
                selected_emaildrafts_to_delete = selected_schedule_id + "," + selected_emaildrafts_to_delete;
                count += 1;
                $("#" + id).html(content + '<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#" + id).toggleClass('selection-icon');
            $("#" + id).toggleClass('selection-icon-selected');
            if (count > 0)
            {
                $scope.deletDraftsButton = true;
                $("#deleteEmaildraft").show();
            }
            if (count === -1)
            {
                $("#deleteEmaildraft").hide();
            }
        };
        $scope.selectedEmailCheckbox = function (emailListID) {
            var count = 0;
            var selectedemailids = "";
            var content = '<input type="checkbox" name="deleteid" value="' + emailListID + '" hidden="" id="deleteid"' + emailListID + '" checked>';
            var content1 = '<input type="checkbox" name="deleteid" value="' + emailListID + '" hidden="" id="deleteid"' + emailListID + '">';
            var htm = $("#" + emailListID).html();
            if (htm.contains('class="check-icon"')) {
                count -= 1;
                $("#" + emailListID).html(content1);
                selectedemailids = selectedemailids.replace(emailListID + ",", "");
            } else {
                selectedemailids = emailListID + "," + selectedemailids;
                count += 1;
                $("#" + emailListID).html(content + '<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#" + emailListID).toggleClass('selection-icon');
            $("#" + emailListID).toggleClass('selection-icon-selected');
            if (count > 0)
            {
                $scope.showDeleteEmailList = true;
                $("#removeselactions").show();
            }
            if (count === -1)
            {
                $scope.showDeleteEmailList = false;
                $("#removeselactions").hide();
            }
        };

//        $scope.unsel = function () {
//            count = 0;
//            var htm = $(".selection-icon-selected").html();
//            if (htm.contains('class="check-icon"')) {
//                $(".selection-icon-selected").html('');
//            }
//            $(".selection-icon-selected").addClass('selection-icon');
//            $('.selection-icon').removeClass('selection-icon-selected');
//            if (count === 0)
//            {
//                $scope.showDeleteEmailList = false;
//                $("#removeselactions").hide();
////                $(".gray-button").hide();
////                $("#addcontact").show();
////                $("#addcontacts").show();
//            }
//        };

        var selectedemailids = "";
        $scope.selectedEmailListCheckbox = function (id) {
            var count = 0;
            var content = '<input type="checkbox" name="deleteid" value="' + id + '" hidden="" id="deleteid"' + id + '" checked>';
            var content1 = '<input type="checkbox" name="deleteid" value="' + id + '" hidden="" id="deleteid"' + id + '">';
            var htm = $("#" + id).html();
            if (htm.contains('class="check-icon"')) {
                count -= 1;
                $("#" + id).html(content1);
                selectedemailids = selectedemailids.replace(id + ",", "");
            } else {
                selectedemailids = id + "," + selectedemailids;
                count += 1;
                $("#" + id).html(content + '<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#" + id).toggleClass('selection-icon');
            $("#" + id).toggleClass('selection-icon-selected');
            if (count > 0)
            {
                $scope.deSelectCheckboxButton = true;
                $scope.selectCheckboxButton = true;
                $("#addcontact").hide();
                $("#addcontacts").hide();
            }
            if (count === -1)
            {
                $scope.deSelectCheckboxButton = false;
                $scope.selectCheckboxButton = false;
                $("#addcontact").show();
                $("#addcontacts").show();
            }
        };

        $scope.deSelectCheckbox = function () {
            count = 0;
            var htm = $(".selection-icon-selected").html();
            if (htm.contains('class="check-icon"')) {
                $(".selection-icon-selected").html('');
            }
            $(".selection-icon-selected").addClass('selection-icon');
            $('.selection-icon').removeClass('selection-icon-selected');
            if (count === 0)
            {
                $scope.deSelectCheckboxButton = false;
                $scope.selectCheckboxButton = false;
                $("#addcontact").show();
                $("#addcontacts").show();
            }
        };

        $scope.deleteDrafts = function (type, id)
        {
            if (confirm("Are you sure, You want to Delete Email Draft(s)?")) {
                var delid = id + ",";
                var message;
                if($scope.selectedEmail==""){
                 $scope.selectedEmail=id;   
                }
                //            var selected_emaildrafts_to_delete = id;
                var requestBody;
                if (type === "deleteMultiple") {
                    message = multidraftconfirm;
                    requestBody = {"type": "deleteSelected",
                        "draft_ids": $scope.selectedEmail.toString(), "entity_type": "null"};
                    $scope.deletDraftsButton = false;
                } else if (type === "delete") {
                    message = singledraftconfirm;
                    requestBody = {"type": "delete",
                        "draft_ids": $scope.selectedEmail.toString()};
                }
                emailDraftFactory.deleteEmailDraftsPost(requestBody).then(function (data) {
                    $scope.displayAllEmailDrafts();
                    $scope.savedEmailDraftPopup = false;
                    $scope.deletDraftsButton = false;
                    $scope.selectedEmail="";
                    $scope.closeSavedEmailDraftPopup();
                });
            }
        };
        $scope.editDrafts = function (draft_id, category_id, email_subject, sub_category_id, mindbodyId, lookupId) {
            var draftdetails = {"draftid": draft_id, "email_subject": email_subject, "category_id": category_id,
                "sub_category_id": sub_category_id,"mindbodyId":mindbodyId,"lookupId":lookupId};
            localStorage.setItem("emailDraftData",JSON.stringify(draftdetails));
            emailDraftFactory.getEmailDraftGet(draft_id).then(function (data) {
                if (data === "false") {
                    alert(draftsavingerror);
                } else {
                    window.open(getHost() + 'user/baseemaileditor#/emaileditor',"_self");
                }
            });
        };
        $scope.getEmailSettings = function () {
            $scope.activeEmailSettings = 'activeTab';
            $scope.activeEmailHistory = '';
            $scope.activeEmailDrafts = '';
            $scope.activeEmailList = '';

            $scope.emaildropdown = false;
            $("#savesetbtn").show();
//            $("#removeselactions").hide();
            $scope.showDeleteEmailList = false;
            $scope.saveEmailSettingsButton = true;
            $scope.addEmailListButton = false;
            $scope.deletDraftsButton = false;
            settingsFactory.getEmailSettingsGet().then(function (data) {
                $scope.emailSettingsDetails = true;
                $scope.email_settings = JSON.parse(data.d.details);
            });
        };
        $scope.saveEmailSettings = function (email_settings) {
            var from_address = email_settings.from_address;
            var reply_email_address = email_settings.reply_email_address;
            var emailSettingsData = {"from_address": "mail@brndbot.com", "reply_email_address": reply_email_address};
            settingsFactory.saveEmailSettingsPost(emailSettingsData).then(function (data) {
                $scope.getEmailSettings();
            });
        };
        $scope.getFooterDetails = function () {
            $scope.emaildropdown = false;
//            $scope.saveEmailSettingsButton = false;
            $scope.saveEmailSettingsButton = true;
            $scope.addEmailListButton = false;
            $scope.deletDraftsButton = false;
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
                $scope.company = $scope.footerDetails;
            });
        };
        $scope.changeFooterDetails = function (company) {
            var footerAddress = "";
            if (company.address)
                footerAddress = company.address;
            var footerWebsiteUrl = "";
            if (company.websiteUrl)
                footerWebsiteUrl = company.websiteUrl;
            var footerFacebookUrl = "";
            if (company.facebookUrl)
                footerFacebookUrl = company.facebookUrl;
            var footerTwitterUrl = "";
            if (company.twitterUrl)
                footerTwitterUrl = company.twitterUrl;
            var footerInstagramUrl = "";
            if (company.instagramUrl)
                footerInstagramUrl = company.instagramUrl;
            var footerPopupDetails = {"facebookUrl": footerFacebookUrl, "twitterUrl": footerTwitterUrl, "instagramUrl": footerInstagramUrl, "websiteUrl": footerWebsiteUrl, "address": footerAddress};
            $scope.emailFooterPopupDetails = false;
            $scope.getFooterDetails();
            settingsFactory.setFooterPost(footerPopupDetails).then(function (data) {
                $scope.getFooterDetails();
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
            $scope.activeEmailHistory = 'activeTab';
            $scope.activeEmailSettings = '';
            $scope.activeEmailDrafts = '';
            $scope.activeEmailList = '';
            
            $scope.showDeleteEmailList = false;
            $scope.emaildropdown = false;
            $scope.deletDraftsButton = false;
            $scope.addEmailListButton = false;
            $scope.saveEmailSettingsButton = false;
            emailFactory.sendEmailGet().then(function (data) {
                $scope.email_history = JSON.parse(data.d.details);
            });
        };
        $scope.showDraftPopup = function (Id, categoryId, emailSubject, editdate, subCategoryId, mindbodyId, lookupId)
        {
            $("#fade").show();
            $scope.savedEmailDraftPopup = true;
            emailDraftFactory.getEmailDraftGet(Id).then(function (data) {
                if (data === "") {
                    $scope.emaildraftsstatus = noemaildraft;
                } else {
                    $scope.htmlbody = data.htmlbody;
                    $('#draftshow').empty().append(data.htmlbody);
                }
            });
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
            $scope.activeEmailList = 'activeTab';
            $scope.activeEmailHistory = '';
            $scope.activeEmailSettings = '';
            $scope.activeEmailDrafts = '';

            $scope.emaildropdown = false;
            $scope.addEmailListButton = true;
            $("#addemlstbtn").show();
            $("#deleteEmailList").hide();
            $scope.showEmailListDetails = false;
            $scope.saveEmailSettingsButton = false;
            $scope.deletDraftsButton = false;
            $scope.emallistdetails = true;
            emailListFactory.emailListGet("null", "allEmailListWithNoOfContacts").then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.emailLists = parseData.allEmailListWithNoOfContacts.user;
                $scope.emailListsMindbody = parseData.allEmailListWithNoOfContacts.mindbody;
            });
        };
//        $scope.email = {listName: "", listDescription:""};
////        $scope.email = {listDescription:""}
//        $scope.validationCode = function (emailListValue) {
//            if (emailListValue) {
////                if(emailListValue.listName){
//
//                if (!emailListValue.listName) {
//                    $("#list_name").focus();
//                    return false;
//                }
//                if (!emailListValue.listDescription) {
//                    $("#list_description").focus();
//                    return false;
//                }
//                if (!emailListValue.deafultFromName) {
//                    $("#default_from_name").focus();
//                    return false;
//                }
//            }
//
////                alert("das");
//
//            else
//            {
//                $("#list_name").focus();
//                return false;
//            }
//
////            } else {
////                $("#list_name").focus();
////                return false;
////            }
//
//        };
//        $scope.checkForNullValue = function (data) {
//            var returnValue = false;
//            if (data === "" || null)
//            {
//                returnValue = true;
//            }
//            return returnValue;
//        };

        $scope.createEmailList = function (email)
        {
//            if ($scope.validationCode(email))
//            {
//                alert("1");
                var emailListDetails = {"emailListName": email.listName, "defaultFromName": email.deafultFromName, "listDescription": email.listDescription, "update": "addEmailList"};
                emailListFactory.emailListSavePost(emailListDetails).then(function (data) {
                    $scope.createEmailListPopup = false;
                    $("#fade").hide();
                    $scope.emailListGet();
                    $scope.showDeleteEmailList = false;
                    $("#deleteEmailList").show();
                });
//            }
        };

        $scope.deleteEmailList = function ()
        {
            if (confirm("Are you sure, You want to Delete Email List?")) {
                var noofemaillist = "";
                var selected_email_lists = "";
                $("input[type=checkbox]:checked").each(function () {
                    selected_email_lists += $(this).val() + ",";
                    noofemaillist = selected_email_lists.split(',');
                });
                if (noofemaillist.length > 2) {
                    var EmailLists = {"update": "deleteAllEmailLists", "emailListName": selected_email_lists};
                } else {
                    var EmailLists = {"update": "deleteEmailLists", "emailListName": selected_email_lists};
                }
                emailListFactory.emailListSavePost(EmailLists).then(function (data) {
                    if (data.d.operationStatus.statusCode === "Success") {
                        $scope.emailListGet();
                    }
                });
            }
        };

        $scope.showUpdateList = function ()
        {
            $scope.updateList();
            $("#addcontact").show();
            $scope.showAddContactPopup = false;
        };
        
        $scope.checkEmailList = function(){
            if ($scope.showEmailListDetails == false){
                $location.path("/basemarketinghub#/emaillist");
            }
        };

        $scope.updateList = function () {
            $scope.showEmailListContacts = true;
            $("#importListli").removeClass("top-subnav-link-active");
            $("#importList").removeClass("h3-active-subnav");
            $("#emailListli").addClass("top-subnav-link-active");
            $("#emailList").addClass("h3-active-subnav");
            $(".page-background").css("background-color", "#fff");
            var list_name = $scope.emailListName;
            var type = $("#get_type").val();
            $("#tab4").hide();
            $("#email_list_name").val(list_name);
            emailListFactory.emailListGet($scope.emailListName, "emailsForEmailList").then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $(".page-background").css("overflow", "scroll");
                $(".page-background").css("background-color", "#EFF2F6");
                $scope.user_emailAddresses = parseData.user_emailAddresses;
                $scope.mindbody_emailAddresses = parseData.mindbody_emailAddresses;
                $scope.selected_email_listname = list_name;
                $scope.type = type;
                if (type === 'user') {
                    $("#tab1").hide();
                    $("#tab2").hide();
                    $("#tab3").show();
                    $("#addcontacts").show();
                    $("#deleteSelected").show();
                    $("#selectAll").show();
                    for (var i = 0; i <= data.user_emailAddresses.length; i++) {
                        var emailadd = data.user_emailAddresses[i];
                        if (emailadd.emailAddress === "") {
                            $("#NoContacts").css("display", "block");
                            setTimeout(function ()
                            {
                                $('input[type="checkbox"]').css("display", "none");
                            }, 100);
                        }
                    }
                } else if (type === 'mindbody') {
                    $("#addcontact").hide();
                    $("#email1").hide();
                    setTimeout(function ()
                    {
                        $('input[type="checkbox"]').css("display", "none");
                    }, 100);
                }
            });
        };

        $scope.updateEmailID = function (email) {
            $("#addcontact").show();
            $scope.showAddContactPopup = false;
            $scope.overlayFade = false;
            var email_list_name = $scope.emailListName;
            var email_address = $("#emailId").val();
            var firstName = email.firstName;
            var lastName = email.lastName;
            var type = $scope.type;
            if ($scope.validateEmailListPopup(email)) {
                var emaildetails;
                if (type === "add") {
                    emaildetails = {"update": "checkAvailability", "emailListName": email_list_name,
                        "emailAddress": email_address, "emailFirstName": firstName,
                        "emailLastName": lastName};
                    emailListFactory.emailListSavePost(emaildetails).then(function (data) {
                        $("#addContactButton").unbind('click');
                        var parseData = JSON.stringify(data.d.operationStatus.messages);
                        if (parseData === '["Email list could not be saved, please try again in sometime."]') {
                            emaildetails = {"update": "addEmailID", "emailListName": email_list_name,
                                "emailAddress": email_address, "emailFirstName": firstName,
                                "emailLastName": lastName};
                            emailListFactory.emailListSavePost(emaildetails).then(function (data) {
                                alert(datasaved);
                                $scope.updateList();
                                $scope.showAddContactPopup = false;
                                $("#addContactButton").unbind('click');
                            });
                        } else if (parseData === '["Email list saved successfully."]') {
                            alert(emailexist);
                            $("#addContactButton").unbind('click');
                        }
                    });
                } else if (type === "update") {
                    var id = $scope.uuid;
                    emaildetails = {"update": "updateEmailID", "emailUID": id, "emailListName": email_list_name,
                        "emailAddress": email_address, "emailFirstName": firstName,
                        "emailLastName": lastName};
                    emailListFactory.emailListSavePost(emaildetails).then(function (data) {
                        alert("datasaved");
                        $scope.updateList();
                        $scope.showAddContactPopup = false;
                    });
                }
                ;
            }
            ;
        };
        $scope.unsubscribeEmailList = "";
        $scope.uploadEmailListOnClick = function () {
            var reg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            var fileUpload = document.getElementById("fileid");
            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;

            if (regex.test(fileUpload.value.toLowerCase())) {
                if (typeof (FileReader) != "undefined") {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var table = document.createElement("table");
                        var rows = e.target.result.split("\n");
                        for (var j = 1; j < rows.length; j++)
                        {
                            var csvvalue = rows[j].split(",");

                            var temp = csvvalue[0];
                            if (j == 1)
                                $scope.unsubscribeEmailList = temp;
                            else
                                $scope.unsubscribeEmailList = $scope.unsubscribeEmailList + "," + temp;
                        }
                        $scope.unsubscribeNowOnClick();
                    }
                    reader.readAsText(fileUpload.files[0]);

                } else {
                    alert("This browser does not support HTML5!");
                }
            } else {
                alert("Please upload a valid CSV file!");
            }

        };
        $scope.unsubscribeNowOnClick = function () {
            //If no file is selected

            var error = 0;
            var emailLists = [];
            if (!$scope.unsubscribeEmailList)
            {
                alert("Please choose a valid csv file.");
                return false;
            }
            //if file is blank
            if ($scope.unsubscribeEmailList)
            {
                if ($scope.unsubscribeEmailList.length == 0)
                {
                    alert("Please choose a valid csv file.");
                    return false;
                }
            }
            var emailListData = $scope.unsubscribeEmailList.split(",");
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

            for (var i = 0; i < emailListData.length; i++) {
                var emailID = emailListData[i];
                if (regex.test(emailID)) {
                    emailLists.push(emailID);
                }
                else
                {
                    error++;
                }
            }
            if (error != 0) {
                alert("Some of the email addresses are invalid in the csv file and they have been excluded.");
            }

            settingsFactory.unSubscribeEmails(emailLists).then(function (data) {
                alert(data.d.operationStatus.messages[0]);
                $scope.hideUnsubscribeEmailsPopup();
            });
        };


        $scope.validateEmailListPopup = function (email)
        {
            var email_address = $("#emailId").val();
            var firstName = email.firstName;
            var lastName = email.lastName;
            var error = 0;
            if (email_address === "")
            {
                error++;
                alert(noemail);
                $("#emailId").focus();
                return false;
            }
            if (email_address !== "")
            {
                var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                if (re.test(email_address)) {
                }
                else
                {
                    error++;
                    alert(emailerror);
                    $("#emailId").focus();
                    return false;
                }
            }
            if (firstName !== "")
            {
                if (lastName === "")
                {
                    error++;
                    alert(lastnameerror);
                    $("#lastName").focus();
                    return false;
                }
            }
            if (error === 0)
            {
                return true;
            }
        };

        $scope.uploadCsv = function () {
            var fileUpload = document.getElementById("fileUpload");
            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
            if (regex.test(fileUpload.value.toLowerCase())) {
                if (typeof (FileReader) !== "undefined") {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var table = document.createElement("table");
                        var rows = e.target.result.split("\n");
                        if ($('#textArea').val() === "") {
                            $('#textArea').val(rows);
                        } else {
                            $('#textArea').val($('#textArea').val() + rows);
                        }
                    }
                    reader.readAsText(fileUpload.files[0]);
                }
            }
        };
        $scope.updateEmailList = function () {
            var emailaddrestextarea = $("textArea").val();
            var reg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            var toemailvalid = reg.test(emailaddrestextarea);
            if ($("textArea").val() === '') {
                alert("No Contacts to import!, Please Enter atleast One Contact.");
                $("#textArea").focus();
                return false;
            }
            if ($("textArea").val() !== "") {

                var split = emailaddrestextarea.split(",");
                var lines = [];
                $.each($('#textArea').val().split(/\n/), function (i, line) {
                    if (line) {
                        lines.push(line);
                    }
                });
                for (var i = 0; i < split.length; i++) {
//                    alert(split[i]+"  split length"+split.length);
                    var email = split[i].trim();
                    if (reg.test(email) !== "")
                    {
                        if (email !== "")
                        {
                            if (reg.test(split[i]) === false) {
                                alert(" Contacts not Valid! Please Enter Valid Email Address \n\n'" + split[i] + "'\t is Invalid Email id.");
                                $("#textArea").focus();
                                return false;
                            }
                        }
                    }
                }
            }
            var email_list_name = $scope.emailListName;
            var Emails = {"emailListName": email_list_name, "emailAddresses": emailaddrestextarea, "update": "UpdateEmailList"};
            emailListFactory.emailListSavePost(Emails).then(function (data) {
                if (data === "true") {
                    alert(datasaved);
                    $scope.updateList();
                } else if (data === error) {
                    alert(data);
                }
            });
        };

        $scope.showDropDown = function ()
        {
//                $("#emaildropdown").css("display","block");
            $scope.emaildropdown = true;
        };

        $scope.addContactDetails = function (type, email, id, fname, lname)
        {
            $("fade").show();
            $scope.overlayFade = true;
            $scope.email = {
                emailId: email,
                firstName: fname,
                lastName: lname
            };
            $("#fade").hide();
            $scope.showAddContactPopup = true;
            $scope.uuid = id;
            if (type === "update")
            {
                $scope.emailId = email;
            } else
            {
                $scope.emailId = "";
            }
            if (fname !== "")
            {
                $scope.firstName = fname;
            } else
            {
                $scope.firstName = "";
            }
            if (lname !== "")
            {
                $scope.lastName = lname;
            } else
            {
                $scope.lastName = "";
            }
            $scope.type = type;
        };
        $scope.showAddContacts = function () {
            count = 0;
            $(".delete-button").hide();
            $(".gray-button").hide();
            $("#showList").hide();
            $("#tab4").show();
            $("#importListli").addClass("top-subnav-link-active");
            $("#importList").addClass("h3-active-subnav");
            $("#emailListli").removeClass("top-subnav-link-active");
            $("#emailList").removeClass("h3-active-subnav");
            $("#emailListli").addClass("top-subnav-links");
            $("#emailList").addClass("h3");
            $("#tab3").hide();
            $("#tab4").show();
            $scope.showAddContactPopup = false;
            $scope.showEmailListContacts = false;
        };
        $scope.viewEmailListDetails = function (listName, type)
        {
            $scope.overlayFade = false;
            $scope.emailListName = listName;
            $scope.type = type;
            $scope.showEmailListDetails = true;
            $scope.emallistdetails = false;
            $("#delcontact").hide();
            $location.path("/emaillistdetails");
        };

        $scope.deleteSelected = function () {
            var email_list_name = $scope.emailListName;
            if (confirm("Are you sure, You want to delete contact?")) {
//                var email_list_name = "";
                if (selectedemailids !== "") {
                    email_list_name = $("#email_list_name").val();
                    var Emails = {"update": "deleteEmailInEmailList", "emailListName": email_list_name, "emailAddresses": selectedemailids};
                    emailListFactory.emailListSavePost(Emails).then(function (data) {
                        $scope.updateList(email_list_name);
                        selectedemailids = "";
//                        $location.path("/emaillistdetails");
                        $scope.updateList();
                        $scope.showAddContactPopup = false;
                    }).error(function (error)
                    {
                        alert(JSON.stringify(error));
                    });
                } else {
                    alert(emailnotselected);
                }
            }
        };

        $scope.selemlcheckbox = function (id) {
            $scope.showDeleteEmailList = true;

            var content = '<input type="checkbox" name="deleteid" value="' + id + '" hidden="" id="deleteid"' + id + '" checked>';
            var content1 = '<input type="checkbox" name="deleteid" value="' + id + '" hidden="" id="deleteid"' + id + '">';
            var htm = $("#" + id).html();
            if (htm.contains('class="check-icon"')) {
                count -= 1;
                $("#" + id).html(content1);
                selectedemailids = selectedemailids.replace(id + ",", "");
            } else {
                selectedemailids = id + "," + selectedemailids;
                count += 1;
                $("#" + id).html(content + '<img src="images/check.svg" class="check-icon" style="cursor:pointer;"/>');
            }
            $("#" + id).toggleClass('selection-icon');
            $("#" + id).toggleClass('selection-icon-selected');
            if (count > 0)
            {
                $("#removeselactions").show();
                $("#delcontact").show();
                $(".gray-button").show();
                $("#addcontact").hide();
                $("#addcontacts").hide();
            }
            if (count == 0)
            {
                $("#removeselactions").hide();
                $("#delcontact").hide();
                $(".gray-button").hide();
                $("#addcontact").show();
                $("#addcontacts").show();
            }


        };

//        $scope.uploadEmailListOnClick = function () {
//            var reg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//            var fileUpload = document.getElementById("fileid");
//            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
//            $scope.unsubscribeEmailList = "";
//            if (regex.test(fileUpload.value.toLowerCase())) {
//                if (typeof (FileReader) != "undefined") {
//                    var reader = new FileReader();
//                    reader.onload = function (e) {
//                        var table = document.createElement("table");
//                        var rows = e.target.result.split("\n");
//                        for (var j = 0; j < rows.length; j++)
//                        {
//                            var csvvalue = rows[j].split(",");
//
//                            for (var i = 0; i < csvvalue.length; i++)
//                            {
//                                var temp = csvvalue[i];
//                                if (j == 0 && i == 0)
//                                    $scope.unsubscribeEmailList = temp;
//                                else
//                                    $scope.unsubscribeEmailList = $scope.unsubscribeEmailList + "," + temp;
//                            }
//                        }
//                        alert("Emails from CSV file read successfully, please click Unsubscribe Now button to process them.")
//                    }
//                    reader.readAsText(fileUpload.files[0]);
//
//                } else {
//
//                    alert("This browser does not support HTML5!");
//                }
//            } else {
//                alert("Please upload a valid CSV file!");
//            }
//
//        };
//    
//        $scope.unsubscribeNowOnClick = function () {
//            //If no file is selected
//            if (!$scope.unsubscribeEmailList)
//            {
//                alert("Please choose a valid csv file.");
//                return false;
//            }
//            //if file is blank
//            if ($scope.unsubscribeEmailList)
//            {
//                if ($scope.unsubscribeEmailList.length == 0)
//                {
//                    alert("Please choose a valid csv file.");
//                    return false;
//                }
//            }
//            var emailListData = $scope.unsubscribeEmailList.split(",");
//            $http({
//                method: 'POST',
//                url: getHost() + 'settings/saveUnsubscribeEmails',
//                data: emailListData
//            }).success(function (data) {
//                alert(data.d.operationStatus.messages[0]);
//                hideUnsubscribeEmailsPopup();
//            }).error(function (data, status) {
//                alert(requesterror);
//            });
//        }; 

        $scope.openUnsubscribeEmailsPopup = function () {
            $scope.unsubscribePopup = true;
            alert("Please make sure email addresses are in first column of the csv file.");
        };

        $scope.hideUnsubscribeEmailsPopup = function () {
            $scope.unsubscribePopup = false;
        };

        $scope.closeAddContactPopup = function () {
            $scope.showAddContactPopup = false;
            $scope.overlayFade = false;
        };
    }]);