
marketingFlowApp.controller("marketingController", ['$scope', '$location', '$filter', '$sce', 'marketingFactory', 'companyMarketingProgramFactory', 'yourPlanFactory', 'companyFactory', 'settingsFactory', 'emailListFactory', 'companyMarketingProgramFactory', 'marketingRecurringEmailFactory', function ($scope, $location, $filter, $sce, marketingFactory, companyMarketingProgramFactory, yourPlanFactory, companyFactory, settingsFactory, emailListFactory, marketingRecurringEmailFactory) {

        var entity_id = 0;
        var type = "";
        var program_id = "";
        var emailLists="";
        var to_email_addresses="";
        var schedule_time = "";
        var schedule_date = "";
        var email_list_name = "";
        var template_id = 0;
        var days = 0;
        var entity_no_email_template = "true";
        var program_end_date="";
        
        $scope.marketingCategoryId = "";
        $scope.marketingProgramId = "";
        $scope.past = "";
        $scope.endDate = "";
        $scope.programId = "";
        $scope.redirect = function (pageName, marketingCategoryId)
        {
            $scope.marketingCategoryId = marketingCategoryId;
            $location.path("/" + pageName);
        };
        $scope.redirectProgram = function (pageName, marketingCategoryId, marketingProgramId, htmlData)
        {
            $scope.marketingCategoryId = marketingCategoryId;
            $scope.marketingProgramId = marketingProgramId;
            $scope.htmlData = htmlData;
            $location.path("/" + pageName);
        };
        $scope.redirectToActions = function (pageName, programId, past, endData)
        {
            $scope.past = past;
            $scope.endDate = endData;
            $scope.programId = programId;
            $location.path("/" + pageName);
        };

        $scope.redirectToEmailAutomation = function (pageName, add, programId, zero)
        {
            $scope.programId = programId;
            $scope.add = add;
            $scope.zero = zero;
            $location.path("/" + pageName);
        };

        $scope.getAllMarketingPrograms = function (forward) {
            marketingFactory.companyMarketingCategoriesGet().then(function (data) {
                $scope.header = "Please choose a marketing program type.";
                $scope.pageName = "marketing";
                $scope.forward = forward;
                $scope.marketingCategories = data.d.details;
            });

        };

        $scope.displayMarketingProgramByCategoryId = function (forward) {

            marketingFactory.marketingProgramsGet($scope.marketingCategoryId).then(function (data) {
                $scope.pageName = "marketingPrograms";
                $scope.forward = forward;
                $scope.displayAllMarketingPrograms = data.d.details;
                $scope.header = "Select a Category";

            });

        };

        $scope.saveMarketingProgram = function (programName, programUrl, programUrlName, programDateTime) {
            var data = {"program_name": programName,
                "program_date_time": programDateTime,
                "program_url": programUrl,
                "program_url_name": programUrlName,
                "marketing_category_id": $scope.marketingCategoryId.toString(),
                "marketing_program_id": $scope.marketingProgramId.toString()
            };

            companyMarketingProgramFactory.setMarketingProgramPost(data).then(function (data) {

            });
        };

        $scope.getUserMarketingProgramsOpen = function (forward) {
            companyMarketingProgramFactory.listAllMarketingProgramGet("Open").then(function (data) {
                $scope.currentPrograms = data.programs;
                $scope.currProgramsDiv = true;
                $scope.pastProgramsDiv = false;
                $scope.forward = forward;
            });

        };

        $scope.getUserMarketingProgramsClosed = function (forward) {
            companyMarketingProgramFactory.listAllMarketingProgramGet("Closed").then(function (data) {
                $scope.pastPrograms = data.programs;
                $scope.currProgramsDiv = false;
                $scope.pastProgramsDiv = true;
                $scope.forward = forward;
            });

        };
        $scope.showPastPrograms = function () {
            $scope.currProgramsDiv = false;
            $scope.pastProgramsDiv = true;

        };
        $scope.showCurrentPrograms = function () {
            $scope.currProgramsDiv = true;
            $scope.pastProgramsDiv = false;

        };

        $scope.getProgramActions = function (forward)
        {

            companyMarketingProgramFactory.alluserMarketingProgramGet($scope.programId).then(function (data) {
                $scope.template_status = data.emailautomation;
                $scope.programs = data;
                $scope.actionType = "Email";
                $scope.forward = forward;
            });
        };


        $scope.ShowAddAction = function ()
        {
            $scope.fadeClass = 'fadeClass';
            $scope.fade = true;
            $scope.addAction = true;
        };

        $scope.closeOverlay = function ()
        {
            $scope.fadeClass = '';
            $scope.addAction = false;
        };

        $scope.AddAction = function (addTitle, datePicker, timePicker, actionType)
        {
            var actiondate = datePicker;
            var actionDateTime = $("#timepicker1").val().replace(/ /g, '');
            var l = actiondate.toLocaleString() + " " + actionDateTime.toLocaleString();
            var myDate = new Date(l);
            var days = 0;
            var schedule_time = Date.parse(l);
            var myEpoch = schedule_time;
            var days = 0;
            var action = {"title": addTitle, "actiontype": actionType, "type": "save", "description": "", "marketingType": $scope.programId, "action_date": myEpoch, "days": days};
            companyMarketingProgramFactory.addActionPost(action).then(function (data) {
            });
        };

        $scope.showEmailList = function () {
            emailListFactory.emailListGet("null", "allEmailListWithNoOfContacts").then(function (data) {
                alert(JSON.stringify(data));
                var parseData = JSON.parse(data.d.details);
                $scope.emailLists_user = parseData.allEmailListWithNoOfContacts.user;
                $scope.emailLists_mindbody = parseData.allEmailListWithNoOfContacts.mindbody;
            });
        };


        $("#back").click(function () {
            window.open(getHost() + 'user/marketingprogramactions?past=0&program_id=' + program_id + '&program_date=' + program_end_date, "_self");
        });


        $scope.getEntityDetails = function () {
            $scope.showEmailList();
            var entity_details = {"entity_id": entity_id};
            if (type === 'add') {
                $("#datepicker").val('Sun Dec 31 2200');
                $("#datepickerdiv").hide();
                $(".page-content-container").css('width', '90%');
                $("#emailautomationcontent").show();
                $("#emlautomeditorcontainer").hide();
                $("#emailautomation").hide();
                $("#editpreviewtemplatebottom").hide();
            }
            else {

                marketingRecurringEmailFactory.getRecurringEntityPost().then(function (data) {
                    var data = JSON.stringify(entity_details);

                $("#emaillist option:contains(" + data.recurring_email_email_list_name + ")").attr('selected', 'selected');
                var error = 0;

                if (data.recurring_email_title === "") {
                    alert("Enter the title.");
                    $("#recuring_email_title").focus();
                    error++;
                }
                if (data.recurring_email_description === "") {
                    alert("Enter the description.");
                    $("#recuring_email_description").focus();
                    error++;
                }
                if (data.recurring_email_days === "0" || data.recurring_email_days === null || typeof data.recurring_email_days === 'undefined') {
                    if (error === 0) {
                        alert("Please select the day.");
                    }
                    $("#days").focus();
                    error++;
                }
                if (data.recurring_email_time === "") {
                    alert("Select the time.");
                    $("#timepicker1").focus();
                    error++;
                }
                if (data.recurring_email_till_date === "") {
                    alert("Till date not selected! Please select the date.");
                    $("#datepicker").focus();
                    error++;
                }

                if (data.recurring_email_email_list_name === "0" || data.recurring_email_email_list_name === null || typeof data.recurring_email_email_list_name === 'undefined') {
                    if (error === 0) {
                        alert("Please select the email list.");
                    }
                    $("#emaillist").focus();
                    error++;
                }
                if (data.recurring_email_subject === "" || data.recurring_email_subject === null || typeof data.recurring_email_subject === "undefined") {
                    if (error === 0) {
                        alert("Enter the subject.");
                    }
                    $("#subject").focus();
                    error++;
                }
                if (data.recurring_email_from_name === "" || data.recurring_email_from_name === null || typeof data.recurring_email_from_name === "undefined") {
                    if (error === 0) {
                        alert("Enter the from name.");
                    }
                    $("#from_name").focus();
                    error++;
                }
                if (data.recurring_email_reply_to_email_address === "" || data.recurring_email_reply_to_email_address === null || typeof data.recurring_email_reply_to_email_address === "undefined") {
                    if (error === 0) {
                        alert("Please Enter Valid reply-to-address.");
                    }
                    $("#reply_to_address").focus();
                    error++;
                }
                if (type === "template")
                {
                    if (error === 0)
                    {
                        $("#emailautomationcontent").hide();
                        $("#emailautomation").show();
                        $("#emlautomeditorcontainer").show();
                        $("#editpreviewtemplatebottom").show();
                        entity_no_email_template = "false";
                    } else {
                        entity_no_email_template = "true";
                        $("#emailautomationcontent").show();
                        $("#emlautomeditorcontainer").hide();
                        $("#emailautomation").hide();
                        $("#editpreviewtemplatebottom").hide();
                    }
                }
                if (type === 'edit') {
                    var entity_details = {"entity_id": entity_id};
                    $("#emailautomationcontent").show();
                    $("#emlautomeditorcontainer").hide();
                    $("#emailautomation").hide();
                    $("#editpreviewtemplatebottom").hide();
                }
                $scope.entity_details = data;
                days = data.recurring_email_days;
                email_list_name = data.recurring_email_email_list_name;
                var emailAddress = JSON.parse(data.recurring_email_to_email_addresses);
                var email_list_Addresses = eval(JSON.stringify(emailAddress.emailAddresses[0]));
                $("#emaillist option:contains(" + email_list_name + ")").attr('selected', 'selected');
//                  $('#emaillist option[value='+email_list_name+"-"+email_list_Addresses+']').attr('selected','selected');
//                    $("#emaillist").val(email_list_name+"-"+email_list_Addresses);
                $('#days').val(days);
//                    alert($("#emaillist").val());
                if (data.recurring_email_template_id !== null) {
                    template_id = data.recurring_email_template_id;
                    entity_no_email_template = "false";
                } else {
                    entity_no_email_template = "true";
                }
                html_data = data.recurring_email_body;
                $('#edit').froalaEditor('html.set', '' + html_data + '');
//                    alert(data.recuring_email_email_list_name);
                showEmailListName(data.recurring_email_email_list_name);

//                }).error(function(){  //remove brace
////                    alert("Problem fetching the data!");
//                });
             });
                }

//            }
        };
        
        
//   $(document).ready(function (){
//       for(var i=1; i<=31; i++){
//                if ( i === days){
//                    $('#days').append('<option value='+i+' selected>'+ i + '</option>');
//                }else {
//                    $('#days').append('<option value='+i+'>'+ i + '</option>');
//                }
//
//            }
//       $(".row").css("display","block");
//       $("#emlautomeditorcontainer").hide();
//       $("#templatetab").css("background-color","#ffffff").css("color","#19587c");
//
//       $("#emaillist").change(function () {
//
//           var List_name = $("#emaillist").val();
//           
//           emailListFactory.emailListGet("List_name", "emailsForEmailList").then(function (data) {
//               var emailListName = $.parseJSON(data.d.details);                   
//                   for(var i=0;i<emailListName.user_emailAddresses.length;i++){
//                          var emails = emailListName.user_emailAddresses[i];
//                          emailLists=emailLists+eval(JSON.stringify(emails.emailAddress))+",";
//                      }
//               });
//       });
//
//   if (type === 'edit'){
//        $(".page-content-container").css('width','90%');
//       var entity_details = {"entity_id": entity_id};                 
//       $("#emailautomationcontent").show();
//       $("#emlautomeditorcontainer").hide();
//       $("#emailautomation").hide();
//       $("#editpreviewtemplatebottom").hide();
//              
//       
//       marketingRecurringEmailFactory.getRecurringEntityPost(entity_details).then(function (data) {
//                    $("#days").val(entity_details.recurring_email_days);
//                    $("#emaillist option:contains(" + entity_details.recurring_email_email_list_name + ")").attr('selected', 'selected');
//                    $("#subject").val(entity_details.recurring_email_subject);
//                    $("#from_name").val(entity_details.recurring_email_from_name);
//                    $("#reply_to_address").val(entity_details.recurring_email_reply_to_email_address);
//                    $("#recuring_email_title").val(entity_details.recurring_email_title);
//                    $("#recuring_email_description").val(entity_details.recurring_email_description);
//                    var epochToTime = new Date(entity_details.recurring_email_time);
//                    var recurring_email_time = moment(epochToTime).format('hh : mm : a');
//                    $("#timepicker1").val(recurring_email_time);
//                    angular.element(document.getElementById('emailautomation')).scope().setDateNTime(result.recurring_email_time, result.recurring_email_till_date, result.recurring_email_email_list_name);
//                });
//               
//            }else if (type === 'template'){
//                
//                
//            }else if (type === 'add'){
//                $("#emailautomationcontent").show();
//                $("#emlautomeditorcontainer").hide();
//                $("#emailautomation").hide();
//                $("#editpreviewtemplatebottom").hide();
//            }
//                       
//            });
            
           
            
        /*
         * Bring all the email list from the database
         */
        $scope.checkUserPreferences = function () {
            marketingRecurringEmailFactory.getUserPreferencesGet().then(function (data) {
            });

//            $http({
//                method: 'GET',
//                url: getHost() + 'getUserPreferences'
//            }).success(function(data, status, headers, config) {
//                if (data != ""){
//                    alert("Please enter from address and reply to email address in email settings.");
//                }
//            }).error(function(){
////                alert("Problem fetching the data!");
//            });

        };



        /*
         * Bring all the recuring email templates from the database
         */
        $scope.getEmailTemplates = function () {
            $("#emailautomationcontent").hide();
            $("#emlautomeditorcontainer").show();

            marketingRecurringEmailFactory.allRecurringEmailTemplatesGet().then(function (data) {
                $scope.recuring_email_templates = data;
            });
 
        };




        $scope.addUpdateRecuringAction = function () {
            alert("...");
            if (validate()) {

                var days = $("#days").val();
                var emaillist = $("#emaillist").val();
//                var to_email_addresses = emailLists.split(',');
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                var recuring_email_title = $("#recuring_email_title").val();
                var recuring_email_description = $("#recuring_email_description").val();

                var till_date = $("#datepicker").val();
                program_end_date = $("#program_end_date").val();
                var schedule_time = $("#timepicker1").val().replace(/ /g, '');
                var till_date_epoch = Date.parse(till_date);
                var schedule_time_epoch = Date.parse(schedule_time);
//                var html_data = $('#edit').froalaEditor('html.get');
                var html_data ="";

                if (type === 'add') {
                    var recuring_action = JSON.stringify({
                        "days": days, 
                        "emaillist": emaillist,
//                        "to_email_addresses": to_email_addresses,
                        "subject": subject, 
                        "from_name": from_name,
                        "reply_to_address": reply_to_address,
                        "recurring_email_title": recuring_email_title,
                        "recurring_email_description": recuring_email_description,
                        "till_date_epoch": till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id": program_id
                    });
                    
                    
                    marketingRecurringEmailFactory.addRecurringActionPost(recuring_action).then(function (data) {
                    alert(JSON.stringify(data));
                        if (data === "true") {
                            alert("save");
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'user/marketingprogramactions?program_id=' + program_id + '&past=0&program_date=' + program_end_date, "_self");
                        } else {
                            alert("Problem saving the record!");
                        }
                    });



                } else if ((type === 'template') && (entity_no_email_template === "true")) {
                    $(".page-content-container").css('width', '100%');
                    var recuring_action = JSON.stringify({
                        "entity_id": entity_id,
                        "days": days, "emaillist": emaillist,
                        "to_email_addresses": to_email_addresses,
                        "subject": subject, "from_name": from_name,
                        "reply_to_address": reply_to_address,
                        "recurring_email_title": recuring_email_title,
                        "recurring_email_description": recuring_email_description,
                        "till_date_epoch": till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id": program_id
                    });

                    marketingRecurringEmailFactory.addupdateRecurringActionPost(recuring_action).then(function (data) {
                        alert(JSON.stringify(data));
                        if ((data === "true") && (entity_no_email_template === "true")) {
                            alert("Details saved succesfully.");
                            $("#emailautomationcontent").hide();
                            $scope.showEmailAutomation = true;
                            $scope.emailEditorContainer = true;
                            $scope.emailRecurringPreviewTemplate = true;
                            entity_no_email_template = "false";
                        }
                        else {
                            alert("Problem saving the record!");
                        }

                    });



                } else if ((type === 'edit') && (entity_no_email_template === "true")) {
                    $(".page-content-container").css('width', '90%');
                    var recuring_action = JSON.stringify({
                        "entity_id": entity_id,
                        "days": days, "emaillist": emaillist,
                        "to_email_addresses": to_email_addresses,
                        "subject": subject, "from_name": from_name,
                        "reply_to_address": reply_to_address,
                        "recurring_email_title": recuring_email_title,
                        "recurring_email_description": recuring_email_description,
                        "till_date_epoch": till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id": program_id
                    });


                    marketingRecurringEmailFactory.addupdateRecurringActionPost(recuring_action).then(function (data) {
                        alert(JSON.stringify(data));
                        if ((data === "true")) {
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'user/marketingprogramactions?program_id=' + program_id + '&past=0&program_date=' + program_end_date, "_self");
                        } else {
//                  alert("Problem saving the record!");
                        }
                    });




                } else if (((type === 'template') && (entity_no_email_template === 'false')) || ((type = 'edit') && (entity_no_email_template === 'false'))) {
                    $(".page-content-container").css('width', '90%');


                    settingsFactory.getAllPreferencesGet().then(function (data) {
                        var footerData = JSON.parse(data.d.details);
                        if (!footerData.userProfile) {
                            $("#emailFooterPopup").show();
                        }
                        else {
                            if (!footerData.userProfile.address) {
                                $("#emailFooterPopup").show();
                            }
                            else {
                                var footer = UserFooter(footerData.userProfile.facebookUrl, footerData.userProfile.twitterUrl,
                                        footerData.userProfile.websiteUrl, footerData.userProfile.instagramUrl,
                                        footerData.userProfile.address);
                                var sendData = JSON.stringify({
                                    htmlString: $('#edit').froalaEditor('html.get') + footer,
                                    iframeName: rendomIframeFilename.toString()
                                });

                                var recuring_action = JSON.stringify({
                                    "entity_id": entity_id,
                                    "template_id": template_id, "html_data": html_data + footer,
                                    "days": days, "emaillist": emaillist,
                                    "to_email_addresses": to_email_addresses,
                                    "subject": subject, "from_name": from_name,
                                    "reply_to_address": reply_to_address,
                                    "recurring_email_title": recuring_email_title,
                                    "recurring_email_description": recuring_email_description,
                                    "till_date_epoch": till_date_epoch,
                                    "schedule_time_epoch": schedule_time,
                                    "program_id": program_id
                                });


                                marketingRecurringEmailFactory.updateRecurringActionPost(recuring_action).then(function (data) {
                                    alert("fifth");
                                    alert(JSON.stringify(data));
                                    if ((data === "true")) {
                                        alert("Details saved succesfully.");
                                        window.open(getHost() + 'user/marketingprogramactions?program_id=' + program_id + '&past=0&program_date=' + program_end_date, "_self");
                                    } else {
//                            alert("Problem saving the record!");
                                    }
                                });
                            }
                        }
                    });
                }
//            }

                $scope.showHTMLData = function(html_data, id){
                var $iframe = $('.fr-iframe');
//                         $(".fr-iframe").empty();
                $('#edit').froalaEditor('html.set',''+html_data+'');
//                $iframe.contents().find("body").empty();
//                $iframe.contents().find("body").append(html_data);
                template_id = id;
                };
               

             function showEmailListName(email_list_name){
                    setTimeout(function() 
                    {

                      $('#emaillist option[value='+email_list_name+']').attr("selected", "selected");
                      $("#emaillist").change();

                    for(i=1; i<=31; i++){
                        if ( i === days){
                            $('#days').append('<option value='+i+' selected>'+ i + '</option>');
                        }else {
                            $('#days').append('<option value='+i+'>'+ i + '</option>');
                        }
                    }
                    }, 500);
                }
//
                }
        };



        $scope.closePopup = function () {
            $scope.reminderSectionClass = '';
            $scope.emailsectionClass = '';
            $scope.fadeClass = '';
        };
        $scope.setTab = function (tabName) {
            if (tabName === 'actionDetails') {
                $scope.top_subnav_link_active_actionDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_notesDetail_Class = '';
                $scope.top_subnav_link_active_savedDetail_Class = '';
                $scope.generalActions = true;
                $scope.generalSavedDetails = false;
                $scope.generalNotes = false;
            }
            if (tabName === 'savedDetails') {
                $scope.top_subnav_link_active_savedDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_actionDetail_Class = '';
                $scope.top_subnav_link_active_notesDetail_Class = '';
                $scope.generalSavedDetails = true;
                $scope.generalActions = false;
                $scope.generalNotes = false;
            }
            if (tabName === 'notes') {
                $scope.top_subnav_link_active_notesDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_actionDetail_Class = '';
                $scope.top_subnav_link_active_savedDetail_Class = '';
                $scope.generalNotes = true;
                $scope.generalActions = false;
                $scope.generalSavedDetails = false;
            }
            if (tabName === 'reminderDetails') {
                $scope.top_subnav_link_active_reminderDetail_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_savedReminder_Class = '';
                $scope.reminderDetailsTab = true;
                $scope.savedReminderTab = false;
            }
            if (tabName === 'savedReminder') {
                $scope.top_subnav_link_active_savedReminder_Class = 'top-subnav-link-active-detail-Class';
                $scope.top_subnav_link_active_reminderDetail_Class = '';
                $scope.savedReminderTab = true;
                $scope.reminderDetailsTab = false;
            }
        };
        $scope.getScheduleDetails = function (schedule_id, template_status, schedule_date, entity_type, schedule_title, schedule_desc, schedule_time, action_status, days, marketingName)
        {
//        $scope.entities_selected_time =schedule_time;
//        $scope.entities_selected_time = $filter('date')(schedule_time, "MMM dd yyyy");
            $scope.savedEmail = false;
            $scope.schedule_id = schedule_id;
            $scope.generalSavedDetails = true;
            $scope.generalNotes = false;
            $scope.generalActions = false;
            $scope.emailsectionClass = 'emailsectionClass';
            $scope.fadeClass = 'fadeClass';
            $scope.email_template_status = template_status;
            $scope.generalActionDetailsHeader = entity_type;
            $scope.scheduledTo = 'POST';
            $scope.setTab('savedDetails');
            $scope.masterActionType = entity_type;
//        if (entity_type === getnote()) {
//            $scope.reminderSectionClass='reminderSectionClass';
//            $scope.savedReminderTab=true;
//            $scope.setTab('savedReminder');
//        }

            if (entity_type === getemail()) {
                $scope.scheduledTo = 'SEND';
            }

            var date = "";//$scope.entities_selected_time;
            var time = $filter('date')(schedule_time, "hh:mm a")
//var time = schedule_time;
            $scope.scheduleData = {schedule_title: schedule_title, entities_selected_time: date,
                schedule_id: schedule_id, schedule_desc: schedule_desc,
                email_template_status: template_status, schedule_type: entity_type,
                marketing_program_name: marketingName, user_marketing_program_id: $scope.programId,
                days: days, schedule_time: time};
//                $('#emailcontentiframe').contents().find('html').html(data.body); 
            alert($scope.scheduleData.schedule_id);
            if (entity_type === getemail()) {
                yourPlanFactory.scheduledEmailGet($scope.scheduleData.schedule_id).then(function (data) {
                    $scope.entitiesdetails = JSON.parse(data.d.details);
                    var iframe = document.getElementById('iframeForAction');

                    if ($scope.entitiesdetails != "{}") {
                        $scope.savedEmail = true;
                        $scope.savedTemplateHeader = "SAVED EMAIL PREVIEW";
                        $scope.deleteScheduleButton = "Remove Saved Email";
                        iframe.contentDocument.body.innerHTML = $scope.entitiesdetails.body;
                    } else {
                        $scope.savedEmail = false;
                    }
                });
            } else {
                companyFactory.currentCompanyGet().then(function (companyData) {
                    $scope.companyId = companyData.d.details[0];
                    var postData = {
                        access_token_method: "getAccessToken"
                    };
                    settingsFactory.twitterPost(postData).then(function (twitterData) {
                        var twitterData = twitterData.d.message.split(",");
                        $scope.twitterprofileName = twitterData[2];
                        if (!$scope.twitterprofileName)
                            $scope.twitterprofileName = "--";
                        yourPlanFactory.scheduledSocialPost($scope.scheduleData.schedule_id).then(function (data) {

                            $scope.entitiesdetails = JSON.parse(data.d.details);
                            var iframe = document.getElementById('iframeForAction');

                            if (data.d.details != "{}") {
                                $scope.savedEmail = true;
                                if (entity_type === gettwitter())
                                {
                                    $scope.savedTemplateHeader = "SAVED TWITTER PREVIEW";
                                } else if (entity_type === getfacebook())
                                    $scope.savedTemplateHeader = "SAVED FACEBOOK PREVIEW";
                                $scope.deleteScheduleButton = "Remove Saved Post";

                                var htmlData = "<style>\n"
                                        + ".twitter-post-preview {\n"
                                        + "    width: 494px;\n"
                                        + "    height: 340px !important;\n"
                                        + "    margin: auto;\n"
                                        + "    padding: 12px 12px 0px 12px;\n"
                                        + "    float: none;\n"
                                        + "    border: 1px solid rgb(223, 224, 228);\n"
                                        + "    border-radius: 3px;\n"
                                        + "    background-color: #FFFFFF;\n"
                                        + "}\n"
                                        + ".Facebook-preview {\n"
                                        + "    width: 494px;\n"
                                        + "    height: 455px;\n"
                                        + "    margin: auto;\n"
                                        + "    padding: 12px 12px 0px 12px;\n"
                                        + "    float: none;\n"
                                        + "    border: 1px solid rgb(223, 224, 228);\n"
                                        + "    border-radius: 3px;\n"
                                        + "    background-color: #FFFFFF;\n"
                                        + "    overflow: auto;\n"
                                        + "    margin-bottom: 65px;\n"
                                        + "}"
                                        + "\n"
                                        + ".Facebook-preview-header {\n"
                                        + "    width: 500px;\n"
                                        + "    height: 40px;\n"
                                        + "    float: left;\n"
                                        + "    margin-bottom: 11px;\n"
                                        + "}\n"
                                        + "\n"
                                        + ".Facebook-preview-profpic {\n"
                                        + "    width: 40px;\n"
                                        + "    height: 40px;\n"
                                        + "    margin-right: 8px;\n"
                                        + "    float: left;\n"
                                        + "    background-color: beige;\n"
                                        + "}\n"
                                        + "\n"
                                        + ".Facebook-preview-profpic > img {\n"
                                        + "    width: 100%;\n"
                                        + "    height: 100%;\n"
                                        + "}\n"
                                        + "\n"
                                        + ".Facebook-preview-name-container {\n"
                                        + "    width: 445px;\n"
                                        + "    height: 100%;\n"
                                        + "    float: inherit;\n"
                                        + "    color: #365899;\n"
                                        + "    font-weight: bold;\n"
                                        + "}\n"
                                        + "\n"
                                        + ".Twitter-preview-name-container {\n"
                                        + "    width: 445px;\n"
                                        + "    height: 100%;\n"
                                        + "    float: inherit;\n"
                                        + "}\n"
                                        + "\n"
                                        + ".Facebook-preview-usercontent {\n"
                                        + "    font-family: helvetica, arial;\n"
                                        + "    font-weight: normal;\n"
                                        + "    line-height: 19.32px;\n"
                                        + "    color: rgb(20, 24, 35);\n"
                                        + "    font-size: 14px;\n"
                                        + "    float: left;\n"
                                        + "}\n"
                                        + "\n"
                                        + ".Facebook-link-container {\n"
                                        + "    box-shadow: rgba(0, 0, 0, 0.0980392) 0px 0px 0px 1.5px inset, rgba(0, 0, 0, 0.0470588) 0px 1px 1px 0px;\n"
                                        + "    color: rgb(20, 24, 35);\n"
                                        + "    border: 1px solid rgba(0, 0, 0, 0.0980392);\n"
                                        + "    float: left;\n"
                                        + "    width: 100%;\n"
                                        + "    margin-top: 10px;\n"
                                        + "}\n"
                                        + "img#prevfbimg {\n"
                                        + "    width: 100%;\n"
                                        + "    height: 245px;\n"
                                        + "    margin-left: -2px;\n"
                                        + "}"
                                        + ".Facebook-preview-image {\n"
                                        + "    width: 100%;\n"
                                        + "    height: 244px;\n"
                                        + "    background-color: #5CC2A5;\n"
                                        + "    float: left;\n"
                                        + "}"
                                        + ".Facebook-preview-link-container {\n"
                                        + "    width: 100%;\n"
                                        + "    padding: 10px 12px 10px 12px;\n"
                                        + "    float: left;\n"
                                        + "    box-sizing: border-box;\n"
                                        + "    overflow: hidden;\n"
                                        + "}"
                                        + ".Facebook-preview-link-title {\n"
                                        + "    font-family: Georgia, 'lucida grande', tahoma;\n"
                                        + "    font-size: 18px;\n"
                                        + "    color: rgb(20, 24, 35);\n"
                                        + "    font-weight: 500;\n"
                                        + "    line-height: 22px;\n"
                                        + "    margin-bottom: 5px;\n"
                                        + "}"
                                        + ".Facebook-preview-link-description {\n"
                                        + "    color: rgb(20, 24, 35);\n"
                                        + "    direction: ltr;\n"
                                        + "    display: block;\n"
                                        + "    font-family: sans-serif;\n"
                                        + "    font-size: 12px;\n"
                                        + "    line-height: 16px;\n"
                                        + "    max-height: 80px;\n"
                                        + "    overflow-x: hidden;\n"
                                        + "    overflow-y: hidden;\n"
                                        + "    word-wrap: break-word;\n"
                                        + "    zoom: 1;\n"
                                        + "    text-overflow: ellipsis;\n"
                                        + "}"
                                        + ".Facebook-preview-link-url {\n"
                                        + "    color: rgb(145, 151, 163);\n"
                                        + "    direction: ltr;\n"
                                        + "    display: block;\n"
                                        + "    font-family: sans-serif;\n"
                                        + "    font-size: 14px;\n"
                                        + "    line-height: 16.08px;\n"
                                        + "    padding-top: 9px;\n"
                                        + "    margin-bottom: 5px;\n"
                                        + "}"
                                        + "</style>";
                                if (entity_type === gettwitter()) {
                                    htmlData += "<div class=\"twitter-post-preview\">\n"
                                            + "    <div class=\"Facebook-preview-header\">\n"
                                            + "        <div class=\"Facebook-preview-profpic\">\n"
                                            + "            <img id=\"twitter_preview_profpic\" src=\"/BrndBot/downloadImage?imageType=COMPANY_LOGO&amp;companyId=" + $scope.companyId + "&amp;imageName=companylogo.png\">\n"
                                            + "        </div>\n"
                                            + "        <div class=\"Twitter-preview-name-container\">\n"
                                            + "            <div class=\"Facebook-preview-name ng-binding\"><strong>" + $scope.twitterprofileName + "</strong><br>" + $scope.entitiesdetails.metadata.text + " " + $scope.entitiesdetails.metadata.shorturl + "</div>\n"
                                            + "        </div>\n"
                                            + "    </div>\n";
//                    if($scope.entitiesdetails.metadata.shorturl) {
//                    htmlData += "    <div class=\"Facebook-preview-usercontent ng-binding\">"+$scope.entitiesdetails.metadata.shorturl+"</div>\n"
//                    }
                                    htmlData += "    <div class=\"Facebook-link-container\">\n"
                                            + "        <div ng-show=\"entitiesdetails.image_type == 'gallery'\">\n"
                                            + "            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageName=" + $scope.entitiesdetails.image_name + "&imageType=GALLERY&companyId=" + $scope.companyId + "\">\n"
                                            + "        </div>\n"
                                            + "    </div>\n"
                                            + "</div>";
                                } else if (entity_type === getfacebook()) {
                                    htmlData += "<div class=\"Facebook-preview\">\n"
                                            + "                                <div class=\"Facebook-preview-header\">\n"
                                            + "                                    <div class=\"Facebook-preview-profpic\"><img id=\"fb_preview_profpic\" src=\"/BrndBot/downloadImage?imageType=COMPANY_LOGO&amp;companyId=" + $scope.companyId + "&amp;imageName=companylogo.png\"></div>\n"
                                            + "                                    <div class=\"Facebook-preview-name-container\">\n"
                                            + "                                        <div class=\"Facebook-preview-name ng-binding\">" + $scope.entitiesdetails.metadata.ManagedPage + "</div>\n"
                                            + "                                    </div>\n"
                                            + "                                </div>\n"
                                            + "                                <div class=\"Facebook-preview-usercontent ng-binding\">" + $scope.entitiesdetails.metadata.post_text + "</div>\n"
                                            + "                                <div class=\"Facebook-link-container\">\n"
                                            + "                                    <div class=\"Facebook-preview-image\">\n"
                                            + "                                        <div ng-show=\"entitiesdetails.image_type == 'gallery'\">\n"
                                            + "                                            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageType=GALLERY&amp;imageName=" + $scope.entitiesdetails.image_name + "&amp;companyId=" + $scope.companyId + "\">\n"
                                            + "                                        </div>\n"
                                            + "                                        <div ng-show=\"entitiesdetails.image_type == 'layout'\" style=\"display: none;\">\n"
                                            + "                                            <img id=\"prevfbimg\" src=\"/BrndBot/downloadImage?imageType=LAYOUT_IMAGES&amp;imageName=13.jpg\">\n"
                                            + "                                        </div>\n"
                                            + "                                        <div ng-show=\"entitiesdetails.image_type == 'url'\" style=\"display: none;\">\n"
                                            + "                                            <img id=\"prevfbimg\" src=\"13.jpg\">\n"
                                            + "                                        </div>\n"
                                            + "                                        \n"
                                            + "                                    </div>\n"
                                            + "                                    <div class=\"Facebook-preview-link-container\">\n"
                                            + "                                        <div class=\"Facebook-preview-link-title ng-binding\">" + $scope.entitiesdetails.metadata.title + "</div>\n"
                                            + "                                        <div class=\"Facebook-preview-link-description ng-binding\">" + $scope.entitiesdetails.metadata.description + "</div>\n"
                                            + "                                        <div class=\"Facebook-preview-link-url ng-binding\">" + $scope.entitiesdetails.metadata.url + "</div>\n"
                                            + "                                    </div>\n"
                                            + "                                </div>\n"
                                            + "                            </div>";
                                }

                                iframe.contentDocument.body.innerHTML = htmlData;
                            } else {
                                $scope.savedEmail = false;
                            }
                        });
                    });
                });
            }

        };

        $scope.updateAction = function (scheduleUpdatedData) {

            var actiontype = scheduleUpdatedData.schedule_type;//$("#email_schedule_type").val();
            var schedule_id = scheduleUpdatedData.schedule_id;//$("#email_scheduleid").val();
            var title = scheduleUpdatedData.schedule_title;//$("#email_edit_title").val();
            var actiondate = "Mon Jan 01 1970";//$("#emaildatetime").val();
            var days = scheduleUpdatedData.days;//$("#emaildays").val();
            var actionDateTime = scheduleUpdatedData.schedule_time;//$("#timepickeremail").val().replace(/ /g,'');
            var l = actiondate.toLocaleString() + " " + actionDateTime.toLocaleString();
            var schedule_time = Date.parse(l);
            var myEpoch = schedule_time;
            var description = "";
//        if (!validateemailaction()) {
            var action = {
                "schedule_id": schedule_id.toString(), "type": "update",
                "title": title, "actiontype": actiontype,
                "description": description, "action_date": myEpoch, "days": days.toString()
            };
            yourPlanFactory.addActionPost(action).then(function (data) {
                alert(actionsaved);
//                $scope.getCampaigns();
            });
//        }
        };

        $scope.Approval = function (entity_id, template_status, entity_type) {

            var approval_type = {"entity_id": entity_id.toString(),
                "template_status": template_status,
                "entity_type": entity_type};

            companyMarketingProgramFactory.approveStatusPost(approval_type).then(function (data) {
                if (data.toString() == "true") {
                    alert(templetestatussaved);
                    $scope.getCampaigns();
                } else {
                    alert(savingrecordproblem);
                }
            });
            $http({
                method: 'POST',
                url: getHost() + 'approveStatus',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(approval_type)
            }).success(function (data, status, headers, config) {

                if (data == "true") {
                    alert(templetestatussaved);
                    window.open(getHost() + 'user/marketing', "_self");
                } else {
                    alert(savingrecordproblem);
                }
            }).error(function (data, status, headers, config) {

                alert(nodataerror);
            });

        };

        $scope.deleteSchedule = function (schedules_to_delete, type, section, isRecurring) {
            var message;
            var requestBody;
            var responseMessage;
            if (type == "deleteMultiple") {
                message = multideleteconfirm;
                requestBody = {"type": "deleteSelected",
                    "schedule_ids": selected_schedules_to_delete, "entity_type": "null"};
                responseMessage = multideletesuccess;
            } else if (type == "delete") {
                message = singledeleteconfirm;
                requestBody = {"type": "delete",
                    "schedule_ids": schedules_to_delete, "entity_type": section,
                    "isRecurring": isRecurring};
                responseMessage = singledeletesuccess;
            } else if (type == "remove") {
                message = removecnfirm;
                requestBody = {"type": "removetemplate",
                    "schedule_ids": schedules_to_delete, "entity_type": section,
                    "isRecurring": isRecurring};
                responseMessage = multideletesuccess;
            }

            if (confirm(message)) {
                alert(JSON.stringify(responseMessage))
                yourPlanFactory.changeSchedulePost(requestBody).then(function (data) {
                    $scope.getCampaigns();
                    $scope.closePopup();
                });
            }
        };

        $scope.updateActionNote = function (scheduleId) {

            var actiontype = getemail();
            var action = {
                "schedule_id": scheduleId.toString(), "type": "updatenotesyourplan", "actiontype": actiontype,
                "description": $scope.scheduleData.schedule_desc
            };
            yourPlanFactory.addActionPost(action).then(function (data) {
                alert(JSON.stringify(data));
            });
        };

    }]);

       