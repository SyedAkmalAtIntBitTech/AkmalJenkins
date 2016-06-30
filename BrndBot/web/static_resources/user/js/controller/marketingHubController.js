marketinghubFlowApp.controller("marketingHubController", ['$scope', '$location', 'settingsFactory', 'emailListFactory', 'emailDraftFactory', 'emailFactory', function ($scope, $location, settingsFactory, emailListFactory, emailDraftFactory, emailFactory) {

//$scope.emailhubHeader = true;
        $scope.addEmailListButton = true;
        $scope.saveEmailSettingsButton = false;
        $scope.deletDraftsButton = false;
        $("#removeselactions").hide;
        $scope.showDeleteEmailList = false;
        $scope.emailListName = "";


        $scope.displayAllEmailDrafts = function () {
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
            if (count === 0)
            {
                $("#deleteEmaildraft").hide();
            }
        };

        $scope.selectedEmailCheckbox = function (emailListID) {
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
                $("#delcontact").show();
                $(".gray-button").show();
                $("#addcontact").hide();
                $("#addcontacts").hide();
            }
            if (count === 0)
            {
                $scope.showDeleteEmailList = false;
                $("#removeselactions").hide();
                $("#delcontact").hide();
                $(".gray-button").hide();
                $("#addcontact").show();
                $("#addcontacts").show();
            }
        }

        $scope.deleteDrafts = function (type, id)
        {
            var delid = id + ",";
            var message;
            var requestBody;
            if (type === "deleteMultiple") {
                message = multidraftconfirm;
                requestBody = {"type": "deleteSelected",
                    "draft_ids": selected_emaildrafts_to_delete, "entity_type": "null"};
                $("#deleteEmaildraft").hide();
            } else if (type === "delete") {
                message = singledraftconfirm;
                requestBody = {"type": "delete",
                    "draft_ids": delid};
                $scope.savedEmailDraftPopup = false;
                $("#fade").hide();
                $scope.displayAllEmailDrafts();
            }
            emailDraftFactory.deleteEmailDraftsPost(requestBody).then(function (data) {
                $scope.displayAllEmailDrafts();
            });
        };

        $scope.editDrafts = function (draft_id, category_id, email_subject, sub_category_id, mindbodyId, lookupId) {
            var draftdetails = {"draftid": draft_id, "email_subject": email_subject, "category_id": category_id,
                "sub_category_id": sub_category_id};
            emailDraftFactory.getEmailDraftGet(draft_id).then(function (data) {
                alert(JSON.stringify(data));
                if (data === "false") {
                    alert(draftsavingerror);
                } else {
                    window.open(getHost() + 'user/emaileditor?draftId=' + draft_id + '&emailSubject=' + email_subject + '&categoryId=' + category_id + '&subCategoryId=' + sub_category_id + '&mindbodyId=' + mindbodyId + '&LookupId=' + lookupId, "_self");

                }
            });
        };

        $scope.getEmailSettings = function () {
            $("#savesetbtn").show();
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
            settingsFactory.saveEmailSettingsPost(email_settings).then(function (data) {
            });
        };

        $scope.getFooterDetails = function () {
            $scope.saveEmailSettingsButton = false;
            $scope.addEmailListButton = false;
            $scope.deletDraftsButton = false;
            settingsFactory.getAllPreferencesGet().then(function (data) {
                $scope.footerDetails = JSON.parse(data.d.details).userProfile;
                $scope.company = $scope.footerDetails;
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
            $scope.deletDraftsButton = false;
            $scope.addEmailListButton = false;
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
            $scope.addEmailListButton = true;
            $("#addemlstbtn").show();
            $("#deleteEmailList").hide();
            $scope.saveEmailSettingsButton = false;
            $scope.emallistdetails = true;
            emailListFactory.emailListGet("null", "allEmailListWithNoOfContacts").then(function (data) {
                var parseData = JSON.parse(data.d.details);
                $scope.emailLists = parseData.allEmailListWithNoOfContacts.user;
                $scope.emailListsMindbody = parseData.allEmailListWithNoOfContacts.mindbody;
            });
        }

        $scope.createEmailList = function (email)
        {
            var emailListDetails = {"emailListName": email.listName, "defaultFromName": email.deafultFromName, "listDescription": email.listDescription, "update": "addEmailList"};
            emailListFactory.emailListSavePost(emailListDetails).then(function (data) {
                alert(JSON.stringify(data));
                $scope.createEmailListPopup = false;
                $("#fade").hide();
                $scope.emailListGet();
                $scope.showDeleteEmailList = true;
                $("#deleteEmailList").show();

            });
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
                        alert(JSON.stringify(data));
                        $scope.emailListGet();
                    }
                    ;

                });
            }
            ;
        };

        $scope.updateList = function () {
            alert($scope.emailListName);
            $("#showList").show();
            $("#importListli").removeClass("top-subnav-link-active");
            $("#importList").removeClass("h3-active-subnav");
            $("#emailListli").addClass("top-subnav-link-active");
            $("#emailList").addClass("h3-active-subnav");
            $(".page-background").css("background-color", "#fff");
            var list_name = $("#get_list_name").val();
//            var get_list_name = emailListName;
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
                } else {
                    alert(browsernotsupporthtml5);
                }
            } else {
                alert(cvsfileerror);
            }
        }

//            $scope.updateEmailList = function () {
//                    
//                    var emailaddrestextarea=$("textArea").val();
//                    var reg=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//                    var toemailvalid=reg.test(emailaddrestextarea);
//                    if($("textArea").val()===''){alert("No Contacts to import!, Please Enter atleast One Contact.");$("#textArea").focus(); return false;}
//                    if($("textArea").val() !== ""){
//
//                        var split = emailaddrestextarea.split(",");
//                        var lines = [];
//                        $.each($('#textArea').val().split(/\n/), function(i, line){
//                            if(line){
//                                lines.push(line);
//                            }
//                        });
//                        for (var i = 0; i < split.length; i++) {
//                            //alert(split[i]+"  split length"+split.length);
//                            var email=split[i].trim();
//                            if(reg.test(email) !== "")
//                            {
//                                if(email !== "")
//                                {
//                                    if(reg.test(split[i]) === false){
//                                        alert(" Contacts not Valid! Please Enter Valid Email Address \n\n'"+split[i]+"'\t is Invalid Email id.");
//                                        $("#textArea").focus();
//                                        return false;
//                                    } 
//                                }
//                            }
//                        }
//                    }
//                    var email_list_name = $("#email_list_name").val();
//                    var Emails = {"emailListName": email_list_name, "emailAddresses": email_list, "update": "UpdateEmailList"};
//                    emailListFactory.emailListSavePost()
//                    $http({
//                        method: 'POST',
//                        url: getHost() + '/emaillist/save',
//                        headers: {'Content-Type': 'application/json'},
//                        data: Emails
//                    }).success(function (data)
//                    {
//                        if (data === "true") {
//                            alert(datasaved);
//                            window.open(getHost() + 'emaillists.jsp', "_self");                            
//                        } else if (data === error) {
//                            alert(data);
//                        }
//                    });
//                };

        $scope.addContactDetails = function ()
        {
            
            $("#fade1").show();
            $scope.sho = true;
//            $("#addContact").show();
            
//            if (type === "update")
//            {
//                $("#emailId").val(email);
//            } else
//            {
//                $("#emailId").val("");
//            }
//            if (fname !== "")
//            {
//                $("#firstName").val(fname);
//            } else
//            {
//                $("#firstName").val("");
//            }
//            if (lname !== "")
//            {
//                $("#lastName").val(lname);
//            } else
//            {
//                $("#lastName").val("");
//            }

//            $("#type").val("user");
//            overlay();
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
        };

        $scope.viewEmailListDetails = function (listName, type)
        {
            $scope.emailListName = listName;
            $scope.showEmailListDetails = true;
            $scope.emallistdetails = false;
            $scope.emailhubHeader = false;
            $location.path("/emaillistdetails");
        };

    }]);

function fun(type, email, id, fname, lname)
{

    $("#fade").show();
    $("#addContact").show();
    $("#uuid").val(id);
    if (type === "update")
    {
        $("#emailId").val(email);
    } else
    {
        $("#emailId").val("");
    }
    if (fname !== "")
    {
        $("#firstName").val(fname);
    } else
    {
        $("#firstName").val("");
    }
    if (lname !== "")
    {
        $("#lastName").val(lname);
    } else
    {
        $("#lastName").val("");
    }

    $("#type").val(type);
    overlay();
}