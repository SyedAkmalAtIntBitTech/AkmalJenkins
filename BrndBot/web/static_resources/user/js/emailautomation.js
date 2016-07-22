    var emailLists="";
    var to_email_addresses="";
    var schedule_time = "";
    var schedule_date = "";
    var email_list_name = "";
    var template_id = 0;
    var days = 0;
    var entity_no_email_template = "true";
    var entity_id = 0;
    var type = "";
    var program_id = "";
    var program_end_date="";
    
    $(document).ready(function () {
         program_end_date=$("#program_end_date").val();
//         for(var i=1; i<=31; i++){
//                if ( i == days){
//                    $('#days').append('<option value='+i+' selected>'+ i + '</option>');
//                }else {
//                    $('#days').append('<option value='+i+'>'+ i + '</option>');
//                }
//
//            }
    $("#templatetab").click(function (){
           $("#blklist").hide();
           $("#blockdivheader").hide();
           $("#textTab").show();
           $("#stylediv").show();
           
           $("#templatetab").removeClass("emailSideBar-tab").addClass("emailSideBar-tab-active").css("background-color","#ffffff").css("color","#19587c");
           $("#templatediv").removeClass("emailSideBar-tab-active").addClass("emailSideBar-tab").css("background-color","transparent").css("color","#19587c");
       });
       $("#templatediv").click(function (){
           $("#stylediv").hide();
           $("#textTab").hide();
           $("#blockdivheader").show();
           $("#blklist").show();
           $("#templatediv").removeClass("emailSideBar-tab").addClass("emailSideBar-tab-active").css("background-color","#ffffff").css("color","#19587c");
           $("#templatetab").removeClass("emailSideBar-tab-active").addClass("emailSideBar-tab").css("background-color","transparent").css("color","#19587c");
       });  
    });
    
//    setTimeout(
//        function() 
//        {
          //do something special
         // alert("delay");
          //$("#select option").filter(".a0").attr('selected','selected');
    var entity_id = '<%= entity_id %>';
    var type = '<%= type %>';
    var program_id = '<%= program_id %>';
    
//        }, 1000);
        
    
    function emailautomation($scope, $http){
        $("#datepicker").val('Sun Dec 31 2200');$("#datepickerdiv").hide();
         $scope.showEmailList = function () {             
            $.ajax({
                method: 'GET',
                async: false,
                 url: getHost() + 'emaillist/get?update=allEmailListWithNoOfContacts&emailListName=null'
            }).success(function (data, status, headers, config) {
                var parseData=JSON.parse(data.d.details);
//                alert(JSON.stringify(parseData.allEmailListWithNoOfContacts.user[0].emailListName));
                $scope.emailLists_user = parseData.allEmailListWithNoOfContacts.user;
                $scope.emailLists_mindbody = parseData.allEmailListWithNoOfContacts.mindbody;
            }).error(function(error){
                alert(JSON.stringify(error));
//                alert("Problem fetching the data!");
            });
        };
        $("#back").click(function (){
            window.open(getHost() + 'user/marketingprogramactions?past=0&program_id='+program_id+'&program_date='+program_end_date, "_self");
        });
        $scope.getEntityDetails = function (){
            $scope.showEmailList();
            var entity_details = {"entity_id": entity_id};
                if (type === 'add'){
                    
                    $("#datepicker").val('Sun Dec 31 2200');$("#datepickerdiv").hide();
//                $.ajax({
//                method: 'GET',
//                url: getHost() + 'emaillist/get?update=allEmailListWithNoOfContacts&emailListName=null'
//                }).success(function (data, status, headers, config) {
//                    alert(JSON.stringify(parseData.allEmailListWithNoOfContacts.user[0].emailListName));
//                    
//                    var parseData=JSON.parse(parseData.allEmailListWithNoOfContacts);
//                    $scope.emailLists_user = parseData.user;
//                    $scope.emailLists_mindbody = parseData.mindbody;
//                }).error(function(error){
//                    alert(JSON.stringify(error));
//    //                alert("Problem fetching the data!");
//                });
                $(".page-content-container").css('width','90%');
                $("#emailautomationcontent").show();
                $("#emlautomeditorcontainer").hide();
                $("#emailautomation").hide();
                $("#editpreviewtemplatebottom").hide();               
            }
            else{
                $http({
                    method: 'POST',
                    url: getHost() + 'getRecurringEntity',
                    headers: {'Content-Type':'application/json'},
                    data: JSON.stringify(entity_details)
                }).success(function(data, status){
//                    alert(JSON.stringify(data));
                    $("#emaillist option:contains("+data.recurring_email_email_list_name+")").attr('selected', 'selected');
                    var error=0;
                    
                    if (data.recurring_email_title === ""){
                        alert("Enter the title.");
                        $("#recuring_email_title").focus();
                        error++;
                    }
                    if (data.recurring_email_description === ""){
                        alert("Enter the description.");
                        $("#recuring_email_description").focus();
                        error++;
                    }
                    if (data.recurring_email_days === "0" || data.recurring_email_days === null || typeof data.recurring_email_days === 'undefined') {
                        if(error===0){alert("Please select the day.");}
                        $("#days").focus();
                        error++;
                    }
                    if (data.recurring_email_time === ""){
                        alert("Select the time.");
                        $("#timepicker1").focus();
                        error++;
                    }
                    if (data.recurring_email_till_date === ""){
                        alert("Till date not selected! Please select the date.");
                        $("#datepicker").focus();
                        error++;
                    }

                    if (data.recurring_email_email_list_name === "0" || data.recurring_email_email_list_name === null || typeof data.recurring_email_email_list_name === 'undefined') {
                        if(error===0){alert("Please select the email list.");}
                        $("#emaillist").focus();
                        error++;
                    }
                    if (data.recurring_email_subject === "" ||data.recurring_email_subject === null || typeof data.recurring_email_subject === "undefined") {
                        if(error===0){alert("Enter the subject.");}
                        $("#subject").focus();
                        error++;
                    }
                    if (data.recurring_email_from_name === "" ||data.recurring_email_from_name === null || typeof data.recurring_email_from_name === "undefined"){
                        if(error===0){alert("Enter the from name.");}
                        $("#from_name").focus();
                        error++;
                    }        
                    if(data.recurring_email_reply_to_email_address === "" || data.recurring_email_reply_to_email_address === null || typeof data.recurring_email_reply_to_email_address === "undefined"){
                        if(error===0){alert("Please Enter Valid reply-to-address.");}
                        $("#reply_to_address").focus();
                        error++;
                    }
                    if(type==="template")
                    {
                        if(error===0)
                        {
                             $("#emailautomationcontent").hide();
                            $("#emailautomation").show();
                            $("#emlautomeditorcontainer").show();
                            $("#editpreviewtemplatebottom").show();
                            entity_no_email_template = "false";
                        }else {
                            entity_no_email_template = "true";
                            $("#emailautomationcontent").show();
                            $("#emlautomeditorcontainer").hide();
                            $("#emailautomation").hide();
                            $("#editpreviewtemplatebottom").hide();                        
                        }
                    }
                    if (type === 'edit'){
                        var entity_details = {"entity_id": entity_id};                    
                        $("#emailautomationcontent").show();
                        $("#emlautomeditorcontainer").hide();
                        $("#emailautomation").hide();
                        $("#editpreviewtemplatebottom").hide();
                    }
                    $scope.entity_details = data;
                    days = data.recurring_email_days;
                    email_list_name=data.recurring_email_email_list_name;    
                    var emailAddress=JSON.parse(data.recurring_email_to_email_addresses);
                    var email_list_Addresses=eval(JSON.stringify(emailAddress.emailAddresses[0]));
                    $("#emaillist option:contains("+email_list_name+")").attr('selected', 'selected');
//                  $('#emaillist option[value='+email_list_name+"-"+email_list_Addresses+']').attr('selected','selected');
//                    $("#emaillist").val(email_list_name+"-"+email_list_Addresses);
                    $('#days').val(days);
//                    alert($("#emaillist").val());
                    if (data.recurring_email_template_id != null){
                        template_id = data.recurring_email_template_id;
                        entity_no_email_template = "false";
                    }else {
                        entity_no_email_template = "true";
                    }
                    html_data = data.recurring_email_body;
                    $('#edit').froalaEditor('html.set',''+html_data+'');
//                    alert(data.recuring_email_email_list_name);
                    showEmailListName(data.recurring_email_email_list_name);
                    
                }).error(function(){
//                    alert("Problem fetching the data!");
                });
            }
                 
//            }
        };
        /*
        * Bring all the email list from the database
        */
       $scope.checkUserPreferences = function(){
           
            $http({
                method: 'GET',
                url: getHost() + 'getUserPreferences'
            }).success(function(data, status, headers, config) {
                if (data != ""){
                    alert("Please enter from address and reply to email address in email settings.");
                }
            }).error(function(){
//                alert("Problem fetching the data!");
            });
           
       };
       
       

        /*
         * Bring all the recuring email templates from the database
         */
         $scope.getEmailTemplates = function(){
               
                $("#emailautomationcontent").hide();
                $("#emlautomeditorcontainer").show();
                $http({
                    method: 'GET',
                    url: getHost() + 'getAllRecurringEmailTemplates'
                }).success(function(data, status){
                    $scope.recuring_email_templates = data;
                }).error(function(error){
                    alert(JSON.stringify(error));
//                    alert("Problem fetching the data!");
                });

        };

        $scope.addUpdateRecuringAction = function(){
           
            if (validate()){
                
                var days = $("#days").val();                
//                var emaillistwithAddress = $("#emaillist").val().split('-');
//                var emaillist=emaillistwithAddress[0];
//                to_email_addresses=emaillistwithAddress[1].split(',');
                var emaillist=$("#emaillist").val();
                var to_email_addresses=emailLists.split(',');
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                var recuring_email_title = $("#recuring_email_title").val();
                var recuring_email_description = $("#recuring_email_description").val();

                var till_date = $("#datepicker").val();
                program_end_date=$("#program_end_date").val();
                var schedule_time=$("#timepicker1").val().replace(/ /g,'');
//                        var schedule_time=$("#timepicker1").val();
                var till_date_epoch = Date.parse(till_date);
                var schedule_time_epoch = Date.parse(schedule_time);
//                var $iframe = $('.fr-iframe');
                var html_data = $('#edit').froalaEditor('html.get');
//                var html_data = $iframe.contents().find("html").html();
//                html_data = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">" + html_data + "</html>";
//                alert(emaillist);
                if ( type == 'add'){
                    var recuring_action = {
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": to_email_addresses,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recurring_email_title":recuring_email_title,
                        "recurring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };
                    $http({ 
                        method: 'POST',
                        url: getHost()+'/addRecurringAction',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if (data === "true") {
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'user/marketingprogramactions?program_id='+ program_id + '&past=0&program_date='+program_end_date, "_self");
                        }else {
//                            alert("Problem saving the record!");
                        }
                    }).error(function (data, status, headers, config) {
                        alert(JSON.stringify(data));
//                        alert("No data available! Problem fetching the data.");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });

                    }else if((type == 'template') && (entity_no_email_template == "true")){
                        $(".page-content-container").css('width','100%');
                    var recuring_action = {
                        "entity_id" : entity_id, 
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": to_email_addresses,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recurring_email_title":recuring_email_title,
                        "recurring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };
                    $http({
                        method: 'POST',
                        url: getHost()+'addupdateRecurringAction',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if((data == "true") && (entity_no_email_template == "true")) {
                            alert("Details saved succesfully.");
                            $("#emailautomationcontent").hide();
                            $("#emailautomation").show();
                            $("#emlautomeditorcontainer").show();
                            $("#editpreviewtemplatebottom").show();
                            entity_no_email_template = "false";
                        }else {
//                            alert("Problem saving the record!");
                        }
                    }).error(function (data, status, headers, config) {
                        alert(JSON.stringify(data));
//                        alert("No data available! Problem fetching the data.");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });

                }else if ((type == 'edit') && (entity_no_email_template == "true")){
                    $(".page-content-container").css('width','90%');
                    var recuring_action = {
                        "entity_id" : entity_id, 
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": to_email_addresses,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recurring_email_title": recuring_email_title,
                        "recurring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };
                   
                    $http({
                        method: 'POST',
                        url: getHost()+'/addupdateRecurringAction',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if ((data === "true")) {
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'user/marketingprogramactions?program_id='+ program_id + '&past=0&program_date='+program_end_date, "_self");
                        }else {
//                            alert("Problem saving the record!");
                        }
                    }).error(function (data, status, headers, config) {
                        alert(JSON.stringify(data));
//                        alert("No data available! Problem fetching the data.");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                    
                }else if(((type == 'template') && (entity_no_email_template == 'false')) || ((type = 'edit') && (entity_no_email_template == 'false'))){
                     $(".page-content-container").css('width','90%');
                     $.ajax({
                        method : 'GET',
                        url : getHost() + 'settings/getAllPreferences'
                    }).success(function(data, status) {
                        var footerData = JSON.parse(data.d.details);
                            if(!footerData.userProfile){
                                $("#emailFooterPopup").show();
                            }
                         else{
                             if(!footerData.userProfile.address){
                                $("#emailFooterPopup").show();
                            }
                            else{
                        var footer = UserFooter(footerData.userProfile.facebookUrl,footerData.userProfile.twitterUrl,
                                footerData.userProfile.websiteUrl,footerData.userProfile.instagramUrl,
                                footerData.userProfile.address);
                        var sendData=JSON.stringify({
                                        htmlString: $('#edit').froalaEditor('html.get')+footer,
                                        iframeName: rendomIframeFilename.toString()
                                        });
                        
                    var recuring_action = {
                        "entity_id" : entity_id, 
                        "template_id" : template_id, "html_data": html_data+footer,
                        "days":days, "emaillist":emaillist, 
                        "to_email_addresses": to_email_addresses,
                        "subject":subject, "from_name":from_name,
                        "reply_to_address":reply_to_address,
                        "recurring_email_title":recuring_email_title,
                        "recurring_email_description":recuring_email_description,
                        "till_date_epoch":till_date_epoch,
                        "schedule_time_epoch": schedule_time,
                        "program_id" :program_id 
                    };           
                    $http({
                        method: 'POST',
                        url: getHost()+'updateRecurringAction',
                        headers: {'Content-Type':'application/json'},
                        data: JSON.stringify(recuring_action)
                    }).success(function (data, status, headers, config) {
                        if ((data === "true")) {
                            alert("Details saved succesfully.");
                            window.open(getHost() + 'user/marketingprogramactions?program_id='+program_id+'&past=0&program_date='+program_end_date, "_self");
                        }else {
//                            alert("Problem saving the record!");
                        }
                        
                    }).error(function (data, status, headers, config) {
                        alert(JSON.stringify(data));
//                        alert("No data available! Problem fetching the data.");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                    
                     }
                 }
                });
                }
            }
            
             function showEmailListName(email_list_name){
                    setTimeout(function() 
                    {
                      //do something special
                     // alert("delay");
                      //$("#select option").filter(".a0").attr('selected','selected');

                      $('#emaillist option[value='+email_list_name+']').attr("selected", "selected");
                      $("#emaillist").change();

                    for(i=1; i<=31; i++){
                        if ( i == days){
                            $('#days').append('<option value='+i+' selected>'+ i + '</option>');
                        }else {
                            $('#days').append('<option value='+i+'>'+ i + '</option>');
                        }

                    }


                    }, 500);

                }
            };

        $scope.showHTMLData = function(html_data, id){
                var $iframe = $('.fr-iframe');
//                         $(".fr-iframe").empty();
                $('#edit').froalaEditor('html.set',''+html_data+'');
//                $iframe.contents().find("body").empty();
//                $iframe.contents().find("body").append(html_data);
                template_id = id;
        };
        
                      $scope.getFooterDetails = function (){
                      $http({
                             method : 'GET',
                             url : getHost() + '/settings/getAllPreferences'
                         }).success(function(data, status) {
                             $scope.footerDetails = JSON.parse(data.d.details).userProfile;
                         });
                    };
                   $scope.changeFooterDetails = function (){
                   var address = $("#footerAddress").val();
                   var websiteurl = $("#footerWebsiteUrl").val();;
                   var facebookurl = $("#footerFacebookUrl").val();;
                   var twitterUrl = $("#footerTwitterUrl").val();;
                   var instagramUrl = $("#footerInstagramUrl").val();
                   var footerData = '{"facebookUrl":"'+facebookurl+'","twitterUrl":"'+twitterUrl+'","instagramUrl":"'+instagramUrl+'","websiteUrl":"'+websiteurl+'","address":"'+address+'"}';
                   if(address){
                       $http({
                             method: 'POST',
                             url: getHost() + 'settings/setFooter',
                             data: footerData
                         }).success(function (data) {
                                 alert(detailssaved);
                                 $("#emailFooterPopup").hide();
                         }).error(function (data, status) {
                             alert(requesterror);
                         });
                   }
                     else{
                         alert("please enter the Address");
                         $("#footerAddress").focus();
                     }

                } ;
    }


   $(document).ready(function (){
       for(var i=1; i<=31; i++){
                if ( i == days){
                    $('#days').append('<option value='+i+' selected>'+ i + '</option>');
                }else {
                    $('#days').append('<option value='+i+'>'+ i + '</option>');
                }

            }
       $(".row").css("display","block");
       $("#emlautomeditorcontainer").hide();
       $("#templatetab").css("background-color","#ffffff").css("color","#19587c");

       $("#emaillist").change(function () {

           var List_name = $("#emaillist").val();
           $.ajax({
               url: getHost() + "/emaillist/get?update=emailsForEmailList&emailListName="+List_name,
               method: 'GET',                    
//               data: {
//                   update: "emailsForEmailList",
//                   list_name: List_name
//               },
               success: function(data){
//                   alert(JSON.stringify(data.d.details));
                   var emailListName = $.parseJSON(data.d.details);                   
                   for(var i=0;i<emailListName.user_emailAddresses.length;i++){
                          var emails = emailListName.user_emailAddresses[i];
                          emailLists=emailLists+eval(JSON.stringify(emails.emailAddress))+",";
                   }
//                   alert(emailLists);
//                   var emailListNames=JSON.stringify(emailListName.user_emailAddresses);
//                   alert(emailListName.user_emailAddresses.length);
//                   var i = 0;
//                   var emails = emailListName.user_emailAddresses;
//                   alert(emails);
               },
               error: function (errorThrown) {
                    alert(JSON.stringify(errorThrown));                        
                }
           });

       });

   if (type == 'edit'){
        $(".page-content-container").css('width','90%');
       var entity_details = {"entity_id": entity_id};                 
       $("#emailautomationcontent").show();
       $("#emlautomeditorcontainer").hide();
       $("#emailautomation").hide();
       $("#editpreviewtemplatebottom").hide();
       //                
                $.ajax({
                    url: getHost() + "getRecurringEntity",
                    method: 'POST',
                    dataType: 'json',
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    data: JSON.stringify(entity_details),
                    success: function(entity_details){                   
                        $("#days").val(entity_details.recurring_email_days);
                        $("#emaillist option:contains("+entity_details.recurring_email_email_list_name+")").attr('selected', 'selected');
                        $("#subject").val(entity_details.recurring_email_subject);
                        $("#from_name").val(entity_details.recurring_email_from_name);
                        $("#reply_to_address").val(entity_details.recurring_email_reply_to_email_address);
                        $("#recuring_email_title").val(entity_details.recurring_email_title);
                        $("#recuring_email_description").val(entity_details.recurring_email_description);
                        var epochToTime = new Date(entity_details.recurring_email_time);
                         var recurring_email_time = moment(epochToTime).format('hh : mm : a');
                        $("#timepicker1").val(recurring_email_time);                        
                        angular.element(document.getElementById('emailautomation')).scope().setDateNTime(result.recurring_email_time, result.recurring_email_till_date, result.recurring_email_email_list_name);
                    },
                    error: function (error) {
                     alert(eval(JSON.stringify(error.statusText)));   
                    }
                });

                
            }else if (type == 'template'){
                
                
            }else if (type == 'add'){
                $("#emailautomationcontent").show();
                $("#emlautomeditorcontainer").hide();
                $("#emailautomation").hide();
                $("#editpreviewtemplatebottom").hide();
            }
            
            
            });
            function validate(){
                

                var emlval = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
                
                var days = $("#days").val();
                var emaillist=($("#emaillist").val());
//                var emaillisttext = $("#emaillist :selected").text();alert(emaillisttext);
//                var emaillistwithAddress = $("#emaillist").val().split('-');
//                var emaillist=emaillistwithAddress[0];
//                to_email_addresses=emaillistwithAddress[1].split(',');
                
                var subject = $("#subject").val();
                var from_name = $("#from_name").val();
                var reply_to_address = $("#reply_to_address").val();
                var recuring_email_title = $("#recuring_email_title").val();
                var recuring_email_description = $("#recuring_email_description").val();
                
                var till_date = $("#datepicker").val();
                var schedule_time=$("#timepicker1").val().replace(/ /g,'');
                if (recuring_email_title === ""){
                    alert("Enter the title.");
                    $("#recuring_email_title").focus();
                    return false;
                }
                
                if (recuring_email_description === ""){
                    alert("Enter the description.");
                    $("#recuring_email_description").focus();
                    return false;
                }
                if (days === "0") {
                    alert("Please select the day.");
                    $("#days").focus();
                    return false;
                }
//                if (emaillisttext === "") {
//                    alert("Please select the email list text.");
//                    emaillisttext.focus();
//                    return false;
//                }
                if (schedule_time === ""){
                    alert("Select the time.");
                    $("#timepicker1").focus();
                    return false;
                }
                if (till_date === ""){
                    alert("Till date not selected! Please select the date.");
                    $("#datepicker").focus();
                    return false;
                }
               
                if (emaillist === "0") {
                    alert("Please select the email list.");
                    $("#emaillist").focus();
                    return false;
                }
                if (emaillist === null) {
                    alert("Please select the email list.");
                    $("#emaillist").focus();
                    return false;
                }

                if (subject === "") {
                    alert("Enter the subject.");
                    $("#subject").focus();
                    return false;
                }
                if (from_name === ""){
                    alert("Enter the from name.");
                    $("#from_name").focus();
                    return false;
                }        
                
                if((reply_to_address === "")||(!emlval.test(reply_to_address))){
                    alert("Please Enter Valid reply-to-address.");
                    $("#reply_to_address").focus();
                    return false;
                }
                return true;
            };

 
    $(function(){
      $('#edit').froalaEditor({
        key: FroalaLicenseKey
      });
//       $("#templatetab").click(function (){
//       $("#templatetab").css("background-color","#ffffff").css("color","#19587c");
//       });
    });
  
       function hideEmailFooterPopup(){
                 $("#emailFooterPopup").hide();
               } 
       function editFooter(){
            $("#emailFooterPopup").show();
        }
     var rendomIframeFilename="";
       $(document).ready(function () {
            $("#closePrev").click(function(){
                $("#email_previewdiv").hide();
                $('#fade').hide();                
            });
            rendomIframeFilename=event.timeStamp;
            $("#emailpreview").click(function(){
                $("#email_previewdiv").hide();
                $('#fade').hide(); 
                        $.ajax({
                            method : 'GET',
                            url : getHost() + 'settings/getAllPreferences'
                        }).success(function(data, status) {
                            var footerData = JSON.parse(data.d.details);
                            if(!footerData.userProfile){
                                $("#emailFooterPopup").show();
                            }
                         else{
                             if(!footerData.userProfile.address){
                                $("#emailFooterPopup").show();
                            }
                            else{
                                $("#email_previewdiv").show();
//                                $("#email_previewdiv").show();
                                var footer = UserFooter(footerData.userProfile.facebookUrl,footerData.userProfile.twitterUrl,
                                        footerData.userProfile.websiteUrl,footerData.userProfile.instagramUrl,
                                        footerData.userProfile.address);
                            var sendData = {
                                        htmlString: $('#edit').froalaEditor('html.get')+footer,
                                        iframeName: rendomIframeFilename.toString()
                                    };

                            $.ajax({
                                    method: "POST",
                                    url: getHost() + "email/previewServlet",                                
                                    data: JSON.stringify(sendData),
                                    success: function (responseText) {
                                        $("#dynamictable5").empty();
                                        $("#dynamictable6").empty();
                                        var iframePath = getHost() +"download/HTML?fileName="+rendomIframeFilename+".html";
                                        $("#dynamictable5").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                                        $("#dynamictable6").append("<iframe style='width:100%;height:100%;position:relative;background-color:#FFF;border:none;' src='" + iframePath + "'></iframe>");
                                    }
                            }).error(function (error){alert(JSON.stringify(error));});
                            $("#fade").show();
                           }
                       }
                    });
            });
          
        });
                function UserFooter(fb,twitter,website,instagram,address){
                        var returnFooter ="";
                        var footer = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" height=\"100%\" width=\"100%\" bgcolor=\"#EEEEEE\" style=\"border-collapse:collapse;\"><tr><td valign=\"top\"> <center style=\"width: 100%;\"> <div style=\"max-width: 680px;\"> <!--[if (gte mso 9)|(IE)]> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"680\" align=\"center\"> <tr> <td> <![endif]--> <!-- Atom Body: BEGIN --> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" bgcolor=\"#EEEEEE\" width=\"100%\" style=\"max-width: 680px;\"> <tr> <td style=\"padding-top:15px;\" class=\"mobile-padding\"> <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"100%\" style=\"max-width: 300px; background-color:#inherit\" class=\"mobile-padding\"> <tr>";

                        var footerFB = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerFB$$$\"><img src=\""+getHost()+"images/Facebook_Filled.png"+"\" alt=\"Facebook Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Facebook </td> </tr> </table> </td>";

                        var footerTwitter = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerTwitter$$$\"><img src=\""+getHost()+"images/Twitter_Filled.png"+"\" alt=\"Twitter Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Twitter </td> </tr> </table> </td>";

                        var footerWebsite = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerWebsite$$$\"><img src=\""+getHost()+"images/Website_Filled.png"+"\" alt=\"Website Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Website </td> </tr> </table> </td>";

                        var footerInstagram = "<td width=\"20%\" style=\"padding:10px; text-align:center;\"> <table > <tr> <td style=\"text-align:center;\"> <a href=\"$$$footerInstagram$$$\"><img src=\""+getHost()+"images/Insta_Filled.png"+"\" alt=\"Instagram Icon\" style=\"border: 0;width: 50px;\" class=\"\"></a> </td> </tr> <tr> <td style=\"padding: 8px 5px 0px 5px; text-align: center; font-family: arial; font-size: 11px; mso-height-rule: exactly; line-height: 100%; color: #GGG; font-weight: normal\"> Instagram </td> </tr> </table> </td>";

                        var footerMiddle = "</tr> </table> </td> </tr>";

                        var footerAddress = "<!--HEADER: BEGIN--> <tr> <td style=\"font-family: sans-serif; font-size: 12px; mso-height-rule: exactly; line-height: 120%; text-align:center; color: #555555; padding: 20px 55px 20px 55px;\" class=\"fluid mobile-padding\"> $$$footerAddress$$$ </td> </tr> <!--HEADER: END-->";

                        var footerClose = "</table> <!--[if (gte mso 9)|(IE)]> </td> </tr> </table> <![endif]--> </div> </center> </td></tr></table>";


                        returnFooter = footer;
                        if(fb != "")
                            returnFooter+=footerFB.replace("$$$footerFB$$$",fb);
                        if(twitter != "" && typeof twitter != "undefined")
                            returnFooter+=footerTwitter.replace("$$$footerTwitter$$$",twitter);

                        if(website != "" && typeof website != "undefined")
                            returnFooter+=footerWebsite.replace("$$$footerWebsite$$$",website);

                        if(instagram != "" && typeof instagram != "undefined")
                            returnFooter+=footerInstagram.replace("$$$footerInstagram$$$",instagram);

                        returnFooter+=footerMiddle;

                        if(address != "" && typeof address != "undefined")
                            returnFooter+=footerAddress.replace("$$$footerAddress$$$",address);

                        returnFooter+=footerClose;

                        return returnFooter;
                    }

    function showEmailListName(email_list_name){
        setTimeout(
        function() 
        {
          //do something special
         // alert("delay");
          //$("#select option").filter(".a0").attr('selected','selected');
          $('#emaillist option[value='+email_list_name+']').attr("selected", "selected");
          $("#emaillist").change();

//            for(i=1; i<=31; i++){
//                if ( i == days){
//                    $('#days').append('<option value='+i+' selected>'+ i + '</option>');
//                }else {
//                    $('#days').append('<option value='+i+'>'+ i + '</option>');
//                }
//
//            }
        }, 1000);

    }

