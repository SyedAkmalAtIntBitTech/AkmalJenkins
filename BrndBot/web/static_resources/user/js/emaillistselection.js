        function backeditor() {
        var email_subject = $("#email_subject").val();
        var subCategoryId=$("#subCategoryIdTag").val();
        var categoryId=$("#categoryIdTag").val();
        var mindbodydata=$("#mindbodydata").val();
        var LookupId=$("#LookupId").val();
            window.open(getHost() + 'user/emaileditor?subject='+email_subject+"&subCategoryId="+subCategoryId+"&categoryId="+categoryId+"&mindbodyId="+mindbodydata+"&LookupId="+LookupId, "_self");
        }
        var draft_id = "";
         $(document).ready(function () {
             
             function validateemailList(){
                                var emailaddresstextarea=$("#emailaddresses").val();
                                var reg=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                                var toemailvalid=reg.test(emailaddresstextarea);
                                var split=emailaddresstextarea.split(',');
                                if(emailaddresstextarea !== ""){
                                     var split = emailaddresstextarea.split(",");

                                     for (var i = 0; i < split.length; i++) {
                                         //alert(split[i]+"  split length"+split.length);
                                         var email=split[i].trim();
                                         if(reg.test(email) !== "")
                                         {
                                             if(email !== "")
                                             {
                                                 if(reg.test(split[i]) === false){
                                                     alert("Email Address is not Valid! Please Enter Valid Email Address \n\n'"+split[i]+"'\t is Invalid Email id.");
                                                     $("#emailaddresses").focus();
                                                     return false;
                                                }                                                
                                             }                       
                                         }
                                      }
                                 }
                                 return true;
                             }
             
             var iframeName = $("#iframeName1").val();
             var draft_id = $("#draft_id").val();
             $("#closesendpopup").click(function(){
                $("#sendpopup").hide();
                $("#fade").hide();
             });
             $("#schedule").click(function(){
                $("#sendpopup").hide();
                $("#schedulepopup").show();
             });
             $("#closeschedulepopup").click(function(){
                $("#sendpopup").show();
                $("#schedulepopup").hide();
             });
             
                    console.log(draft_id);
                    $("#backemaildetails").click(function (){
                            $("#emaillistselid").show();
                            $("#emaildetailsid").hide();
                            $("#emailIdContinueButton").show();
                            $("#emaildetailscontbtn").hide();
                            $("#backemaillist").show();
                            $("#backemaildetails").hide();
                    });
                    
                    
                     $("#emailIdContinueButton").click(function () {
                    var selectedEmail = $("#chooseEmailList").val();
                    if(selectedEmail !== "1")
                    {
                        var email_subject = "";
                        var email_addresses = $("#emailaddresses").val();
                        
                        if(email_addresses !== "")
                        {       
                                if(validateemailList()){
                                $("#toaddress").val(email_addresses);
                                $("#emaillistselid").hide();
                                $("#emaildetailsid").show();
                                $("#emailIdContinueButton").hide();
                                $("#emaildetailscontbtn").show();
                                $("#backemaillist").hide();
                                $("#backemaildetails").show();
                                $("#emaillistdiv").hide();
                                $("#emailSettings").show();
                                $("#emaillistdiv").hide();
                                $("#emailSettings").show();
                                var email_list = $("#chooseEmailList").val();
                                $.ajax({
                                    url: getHost() + "EmailTextDataServlet",
                                    data: {
                                        email_subject: email_subject,
                                        email_addresses: email_addresses,
                                        email_list : email_list
                                    },
                                    success: function(result){
                                    }
                                });
                            }
                        }
                        else
                        {
                            alert("Please select atleast one email list or add email manually.");
                            selectCsvFile();
                            $("#emailaddresses").focus();
                            return false;
                        }
                    }
                else{
                    var email_subject = "";
                    var email_addresses = $("#emailaddresses").val();
                    if(email_addresses!=="")
                    {  
                        if(validateemailList()){
                        $("#toaddress").val(email_addresses);
                        $("#emaillistselid").hide();
                        $("#emaildetailsid").show();
                        $("#emailIdContinueButton").hide();
                        $("#emaildetailscontbtn").show();
                        $("#backemaillist").hide();
                        $("#backemaildetails").show();
                        $("#emaillistdiv").hide();
                        $("#emailSettings").show();
                        $("#emaillistdiv").hide();
                        $("#emailSettings").show();
                        var email_list = $("#chooseEmailList").val();
                        $.ajax({
                            url: getHost() + "EmailTextDataServlet",
                            data: {
                                email_subject: email_subject,
                                email_addresses: email_addresses,
                                email_list : email_list
                            },
                            success: function(result){
                            }
                        });
                    }
//                            $("#toaddress").val(email_addresses);
//                            $("#emaillistdiv").hide();
//                            $("#toaddress").val(email_addresses);
//                            $("#emailSettings").show();
//                            var email_list = $("#chooseEmailList").val();
//                            $.ajax({
//                                url: getHost() + "EmailTextDataServlet",
//                                data: {
//                                    email_subject: email_subject,
//                                    email_addresses: email_addresses,
//                                    email_list : email_list
//                                },
//                                success: function(result){
//                                }
//                            });
                    }
                    else
                    {
                        alert("Please select at least one email list or add email manually.");
                        selectCsvFile();
                        $("#emailaddresses").focus();
                        return false;
                    }
                }
            });
                });
//        $(document).ready(function (){$("#toaddress").click(function (){var addr=$("#toaddress").val();if(addr!==""){$("#toaddrlbl").css("left","-70px");}else{$("#toaddrlbl").css("left","0px");alert("data");}});});

// function validate(){
//            var from_name = $("#name").val();
//            var email_subject = $("#subject").val();
//            var to_email_addresses = $("#toaddress").val();
//            var from_email_address = $("#formaddress").val();
//            var reply_to_email_address = $("#email").val();
//            var htmldata = formattedHTMLData;
//            var email_list = $("email_list").val();
//            var schedule_title = $("#schedule_title").val("");
//            var schedule_time = $("#schedule_time").val("");
//            var reg=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//            var toemailvalid=reg.test(to_email_addresses);
//            var split=to_email_addresses.split(',');
//            var fromaddrvalid=reg.test(from_email_address);
//            var replytoaddrvalid=reg.test(reply_to_email_address);
//            if (from_name == ""){
//                alert("From name not entered! Please enter the from name.");
//                $("#name").focus();
//                return false;
//            }
//            if (email_subject == ""){
//                alert("Email subject name not entered! Please enter the email subject.");
//                $("#subject").focus();
//                return false;
//            }
//            if (from_email_address == ""){
//                alert("From email address not entered! Please enter the from email address.");
//                $("#formaddress").focus();
//                return false;
//            }
//            if(fromaddrvalid == false){
//                alert("From Address Not Valid! Please Enter valid Email id.");
//                $("#formaddress").focus();
//                return false;
//            }
//            if (to_email_addresses == ""){
//                alert("To address fieled not entered! Please enter the email address.");
//                $("#toaddress").focus();
//                return false;
//            }
//            if(to_email_addresses !== ""){
//                
//                var split = to_email_addresses.split(",");
//                              
//                for (var i = 0; i < split.length; i++) {
//                    //alert(split[i]+"  split length"+split.length);
//                    var email=split[i].trim();
//                    if(reg.test(email) !== "")
//                    {
//                        if(email !== "")
//                        {
//                            if(reg.test(split[i]) === false){
//                                alert(" To Address field is not Valid! Please Enter Valid Email Address \n\n'"+split[i]+"'\t is Invalid Email id.");
//                                $("#toaddress").focus();
//                                return false;
//                            } 
//                        }
//                    }
//                 }
//            }
//            if (reply_to_email_address == ""){
//                alert("Reply to email address not entered! Please enter the reply to email address.");
//                $("#email").focus();
//                return false;
//            }
//            if(replytoaddrvalid ==false){
//                alert("Reply to email address is not Valid! Please enter valid reply to email address.");
//                $("#email").focus();
//                return false;
//            }
//            if (email_list == ""){
//                alert("Email list not entered! Please enter the email list.");
//                $("#email_list").focus();
//                return false;
//            }
//            return true;
//            }
            function emailSettings($scope, $http){                
              
                $scope.setScheduling = function () {
                    
                    $("#schedulethepost").unbind('click');
                    var  draft_id = $("#draft_id").val();
                    var schedule_id = $("#email_actions").val();
                    var from_name = $("#name").val();
                    var email_subject = $("#subject").val();
                    var to_email_addresses = $("#toaddress").val();
                    var from_email_address = $("#formaddress").val();
                    var reply_to_email_address = $("#email").val();
                    var program_id = $("#programs").val();
                    var email_body = formattedHTMLData;
                    var email_list = $("#chooseEmailList").val();
                    var schedule_desc = "";
                    var iframe_name = $("#iframeName1").val();
                    console.log(schedule_id);
                    if (schedule_id == "0"){
                        if($("#schedule_title").val()=="")
                        {
                            alert("Please Enter Title.");
                            $("#schedule_title").focus();
                            return false;
                        }
                        if($("#schedule_date").val()=="")
                        {
                            alert("Please Choose Date.");
                            $("#schedule_date").focus();
                            return false;
                        }
                        if($("#schedule_time").val()=="")
                        {
                            alert("Please Chooose Time.");
                            $("#schedule_time").focus();
                            return false;
                        }
                        var schedule_title = $("#schedule_title").val();
                        var schedule_date = $("#schedule_date").val();
                        var schedule_time = $("#schedule_time").val().replace(/ /g,'');
//                        var schedule = $("#schedule_time").val();
                        var l=schedule_date.toLocaleString() +" "+schedule_time.toLocaleString();
                        var schedule_time = Date.parse(l);
                        console.log("Epoch: " + schedule_time);
                        var myEpoch = schedule_time;
                        console.log("New Epoch: " + myEpoch);
                        var email_scheduling = {
                            "from_name": from_name, 
                            "program_id": program_id,
                            "email_subject": email_subject, 
                            "to_email_addresses": to_email_addresses, 
                            "from_email_address": from_email_address, 
                            "reply_to_email_address": reply_to_email_address, 
                            "email_list": email_list, 
                            "schedule_title": schedule_title, 
                            "schedule_time": myEpoch, 
                            "email_body": email_body, 
                            "schedule_desc": schedule_desc,
                            "iframeName": iframe_name
                        };
                        
                        $http({
                            method: 'POST',
                            url:getHost() + 'actions/scheduleEmail',
                            headers: {'Content-Type': 'application/json'},
                            data: JSON.stringify(email_scheduling)
                        }).success(function (data) {  
                            
                            if (JSON.stringify(data) != "") {
                               $http({
                               method: 'POST',
                               url: getHost() + "deleteEmailDraft?draftid="+draft_id
                               }).success(function (data) {
                                   alert("Your Email has been Scheduled Successfully");
                                   document.location.href = getHost()+"user/dashboard";
                                   $("#schedulethepost").bind('click');
                                }).error(function (data) {
                                    alert(JSON.stringify(data));
                                    alert("No data available! Problem fetching the data.");
                                });
                            }
                        }).error(function (data) {alert(JSON.stringify(data));
                            alert("sads");
                        });
                    }else {
                        var email_scheduling = {
                            "from_name": from_name, 
                            "email_subject": email_subject, 
                            "to_email_addresses": to_email_addresses, 
                            "from_email_address": from_email_address, 
                            "reply_to_email_address": reply_to_email_address, 
                            "email_list": email_list,
                            "schedule_id":schedule_id,
                            "email_body": email_body,
                            "schedule_desc": schedule_desc,
                            "iframeName": iframe_name
                        };
                        $http({
                            method: 'POST',
                             url:getHost() + 'actions/scheduleEmailActions',
                            headers: {'Content-Type': 'application/json'},
                            data: JSON.stringify(email_scheduling)
                        }).success(function (data) {
                            if (JSON.stringify(data) != "") {
                               $http({
                               method: 'POST',
                               url: getHost() + "deleteEmailDraft?draftid="+draft_id
                               }).success(function (data) {
                                alert("Your Email has been Scheduled Successfully.");
                                document.location.href = getHost()+"user/dashboard";
                                $("#schedulethepost").bind('click');
                                }).error(function (data) {
                                    alert("No data available! Problem fetching the data.");
                                });
                            }
                        }).error(function (data) {
                            alert("error after 8");
                            alert("No data available! Problem fetching the data.");
                        });
                        
                    }
                    
                };
                
//                $scope.setScheduling = function () {
//                    draft_id= $("#draft_id").val();
//                    var schedule_id = $("#email_actions").val();
//                    var from_name = $("#name").val();
//                    var email_subject = $("#subject").val();
//                    var to_email_addresses = $("#toaddress").val();
//                    var from_email_address = $("#formaddress").val();
//                    var reply_to_email_address = $("#email").val();
//                    var program_id = $("#programs").val();
//                    var email_body = formattedHTMLData;
//                    var email_list = $("#chooseEmailList").val();
//                    var schedule_desc = "none";
//                    var iframe_name = $("#iframe_name").val();
//                    console.log(schedule_id);
//                    if (schedule_id == "0"){
//                        if($("#schedule_title").val()=="")
//                        {
//                            alert("Please Enter Title.");
//                            $("#schedule_title").focus();
//                            return false;
//                        }
//                        if($("#schedule_date").val()=="")
//                        {
//                            alert("Please Choose Date.");
//                            $("#schedule_date").focus();
//                            return false;
//                        }
//                        if($("#schedule_time").val()=="")
//                        {
//                            alert("Please Chooose Time.");
//                            $("#schedule_time").focus();
//                            return false;
//                        }
//                        var schedule_title = $("#schedule_title").val();
//                        var schedule_date = $("#schedule_date").val();
//                        var schedule_time = $("#schedule_time").val().replace(/ /g,'');
////                        var schedule = $("#schedule_time").val();
//                        var l=schedule_date.toLocaleString() +" "+schedule_time.toLocaleString();
//                        var schedule_time = Date.parse(l);
//                        console.log("Epoch: " + schedule_time);
//                        var myEpoch = schedule_time;
//                        console.log("New Epoch: " + myEpoch);
//                        var email_scheduling = {
//                            "from_name": from_name, 
//                            "program_id": program_id,
//                            "email_subject": email_subject, 
//                            "to_email_addresses": to_email_addresses, 
//                            "from_email_address": from_email_address, 
//                            "reply_to_email_address": reply_to_email_address, 
//                            "email_list": email_list, 
//                            "schedule_title": schedule_title, 
//                            "schedule_time": myEpoch, 
//                            "email_body": email_body, 
//                            "schedule_desc": schedule_desc,
//                            "iframeName": iframe_name
//                        };
//                        $http({
//                            method: 'POST',
//                            url: 'ScheduleEmail',
//                            headers: {'Content-Type': 'application/json'},
//                            data: email_scheduling
//                        }).success(function (data) {  alert(JSON.stringify(data));                          
//                            if (data != "") {
//                               $http({
//                               method: 'POST',
//                               url: getHost() + "deleteEmailDraft?draftid="+draft_id
//                               }).success(function (data) {
//                                   alert("Your Email has been Scheduled Successfully");
//                                   document.location.href = "dashboard.jsp";
//                                
//                                }).error(function (data) {
//                                    alert("No data available! Problem fetching the data.");
//                                });
//                            }
//                        }).error(function (data) {
//                            alert("No data available! Problem fetching the data.");
//                        });
//                    }else {
//                        var email_scheduling = {
//                            "from_name": from_name, 
//                            "email_subject": email_subject, 
//                            "to_email_addresses": to_email_addresses, 
//                            "from_email_address": from_email_address, 
//                            "reply_to_email_address": reply_to_email_address, 
//                            "email_list": email_list,
//                            "schedule_id":schedule_id,
//                            "email_body": email_body,
//                            "schedule_desc": schedule_desc,
//                            "iframeName": iframe_name
//                        };
//                        $http({
//                            method: 'POST',
//                            url: 'ScheduleEmailActions',
//                            headers: {'Content-Type': 'application/json'},
//                            data: email_scheduling
//                        }).success(function (data) {alert(JSON.stringify(data));
//                            if (data != "") {
//                               $http({
//                               method: 'POST',
//                               url: getHost() + "deleteEmailDraft?draftid="+draft_id
//                               }).success(function (data) {
//                                alert("Your Email has been Scheduled Successfully.");
//                                document.location.href = "dashboard.jsp";
//                                
//                                }).error(function (data) {
//                                    alert("No data available! Problem fetching the data.");
//                                });
//                            }
//                        }).error(function (data) {
//                            alert("No data available! Problem fetching the data.");
//                        });
//                        
//                    }
//                    
//                };
                
                $scope.getProgramNames = function() {
                    showOverlay();                     
                    $http({
                       method: 'GET',
                       url:getHost() + 'getAllUserMarketingPrograms'
                    }).success(function (data){
//                        alert(JSON.stringify(data));
                        $http({
                        method: 'POST',
                        url: getHost() + '/actions/getActions',
                        data: {
                            programid: "0",
                            type: getemail()
                        }
                        }).success(function (data1) {                            
                            hideOverlay(); 
                            var parseData=JSON.parse(JSON.parse(data1.d.details));
//                            alert(JSON.stringify(parseData));
                            $scope.email_actions = parseData;
                        }).error(function (data1) {
                            hideOverlay(); 
                            alert("Request not successful!");
                        });
//                        alert(JSON.stringify(data));
                        $scope.marketing_programs = data;
                    }).error(function (data){
                        hideOverlay(); 
                        alert("Request not successful!");
                    });
                };
                 $scope.getActions = function (program_id) {
                     
                   
                    $http({
                        method: 'GET',
                        url: getHost() + 'actions/getActions?programid='+ program_id + '&type='+ getemail()
                    }).success(function (data) {
                        alert(JSON.stringify(data));
                        $scope.email_actions = data;
                    }).error(function (data) {
                        alert("Request not successful!");
                    });
                }; 
                 $("#programs").change(function(){
                    program_id = $("#programs").val();
                    var data={"programid": program_id,"type": getemail()};
//                angular.element(document.getElementById('emailSettings')).scope().getActions(program_id);
                $http({
                        method: 'POST',
                        url: getHost() + 'actions/getActions',
                        data:JSON.stringify(data)
                    }).success(function (data) {
                     var emailActions=data.d.details;
                     var actions=eval(JSON.parse(emailActions));
                        
                        $scope.email_actions = actions;
                    }).error(function (data) {
                        alert("Request not successful!");
                    });
                
                if (parseInt(program_id) === 0){
                    $("#email_actions").attr("disabled", false);
                    document.getElementById('schedule_title').disabled=false;
                    document.getElementById('schedule_date').disabled=false;
                    document.getElementById('schedule_time').disabled=false;
                }
                if (parseInt(program_id) !== 0){
                    $("#email_actions").attr("disabled", false);
                    document.getElementById('schedule_title').disabled=true;
                    document.getElementById('schedule_date').disabled=true;
                    document.getElementById('schedule_time').disabled=true;

                }
               
            });
                              
            }
            
        var formattedHTMLData = "";
        var program_id = '0';
        setTimeout(
        function() 
        {
            formattedHTMLData=$("#dynamictable").contents().find("html").html();
          //do something special
        }, 1000);
       $(document).ready(function () {
           
           $("#chooseEmailList").change(function () {
               
               
//                    var x = document.getElementById("chooseEmailList").selectedIndex;
//                    var List_name = document.getElementsByTagName("option")[x].value;
                    var List_name = $("#chooseEmailList").val();
                    alert(List_name);
                    if (List_name == 1){
//                        $("#emailaddresses").hide();
//                        $("#drop-zone").hide();
//                        $("#clickHere").hide();
//                        $("#upload").hide();
//                        $("#dragtext").hide();
//                        $("#entertext").hide();
//                         $("#emailIdContinueButton").css("top","50px");
                       
                    }else {
                        var emails = "";
                        $("#email_list_name").val(List_name);   
                        
                        $.ajax({
                                method: 'GET',
                                url: getHost() + '/emaillist/get?update=emailsForEmailList&emailListName='+List_name,
                                success: function(result){        
                                
                                    var parseData=JSON.parse(result.d.details);
                                   var JSONData;
                                   if(JSON.stringify(parseData.mindbody_emailAddresses) === "[]")
                                       JSONData = parseData.user_emailAddresses;
                                   else
                                       JSONData = parseData.mindbody_emailAddresses;
                                   var JSONdataArray=JSON.stringify(JSONData);
                                    var i = 0;
                                    for(i=0; i<JSON.stringify(JSONData).length; i++){
                                        
                                        if (JSON.stringify(JSONData[i].emailAddress)!= ""){
                                            
                                            emails = eval(JSON.stringify(JSONData[i].emailAddress))+ "," + emails;
                                            $("#emailaddresses").val(emails);
                                            $("#toaddress").val(emails);
                                              selectCsvFile();     
                                     }
                                   
                                    }           
//                                    for(i=0; i<JSONdataArray.mindbody_emailAddresses.length; i++){
//                                        if (JSONdataArray.mindbody_emailAddresses[i] != ""){
//                                            emails = JSONdataArray.mindbody_emailAddresses[i] + "," + emails;
//                                        }
//                                    }           
                                },
                                error: function(error){
                                    
                                    alert(JSON.stringify(error));
                                }
                        });
                    }
                });
           
//            $(".hamburger,.cross").hide();
            
           $("#humbrgr").click(function (){
            if (confirm("Are you sure, You want to leave this page?")){
                
            }
            else{
                $("#crs").click(); 
               $(".navicons,#txtlog").hide().delay( 800 ).fadeIn( 600 );     
            }
            }
            );
           formattedHTMLData=$("#dynamictable").contents().find("html").html();
           show("iphone");
        });
        
        
        
        function displaySchedule() {
                if (validate()) {
                    angular.element(document.getElementById('emailSettings')).scope().getActions(program_id);
                    $("#popupschedule").show();
                    document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block';
                    document.body.style.overflow = 'hidden';
                }
                else {
                    document.getElementById('light').style.display = 'none';
                    document.getElementById('fade').style.display = 'none';
                    document.body.style.overflow = 'scroll';
                }
            }
            function hidepopup() {
                $("#popupschedule").hide();
                $("#schedule_title").val("");
                $("#schedule_time").val("");
            }
        function show(id) {
            formattedHTMLData=$("#dynamictable").contents().find("html").html();
            
            var imageUrl = $("#" + id).css("background-image");
//
//            if (id === "ipad") {
//                $(".iphoneshow").css("background-image", imageUrl).css("display", 'block').css("width", "239px").css("height", "300px")
//                        .css("border-color", "transparent").css("margin-left", "150px").css("margin-top", "-80px").css("background-color","#FFF");
//                $('#dynamictable').css("width","770px").css("height","958px").css("top","-465px").css("left","-300px").css("-webkit-transform"," scale(0.265)");
//            }
//            else if (id === "imac")
//            {
//                $(".iphoneshow").css("background-image", imageUrl)
//                        .css("display", 'block').css("height", "413px").css("width", "295px").css("margin-left", "150px").css("margin-top", "-80px")
//                        .css("border-color", "transparent").css("background-color","#FFF");
//                 $('#dynamictable').css("width","768px").css("height","615px").css("top","-316px").css("left","-272px").css("-webkit-transform","scale(0.3326)");
//            }
////            $("#"+id).toggleClass('selection-icon');
////            $("#"+id).toggleClass('selection-icon-selected');
//
//            else if (id === "iphone"){
//                $(".iphoneshow").css("background-image", imageUrl)
//                        .css("display", 'block').css("height", "370px").css("width", "415px").css("margin-left", "150px").css("margin-top", "-80px")
//                        .css("border-color", "transparent").css("background-color","#FFF");
//                 $('#dynamictable').css("width","640px").css("height","1024px").css("top","-465px").css("left","-249px").css("-webkit-transform","scale(0.278)");
//            }
            
        }
        
        function validate(){
            var from_name = $("#name").val();
            var email_subject = $("#subject").val();
            var to_email_addresses = $("#toaddress").val();
            var from_email_address = $("#formaddress").val();
            var reply_to_email_address = $("#email").val();
            var htmldata = formattedHTMLData;
            var email_list = $("email_list").val();
            var schedule_title = $("#schedule_title").val("");
            var schedule_time = $("#schedule_time").val("");
            var reg=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            var toemailvalid=reg.test(to_email_addresses);
            var split=to_email_addresses.split(',');
            var fromaddrvalid=reg.test(from_email_address);
            var replytoaddrvalid=reg.test(reply_to_email_address);
            if (email_subject == ""){
                alert("Email subject name not entered! Please enter the email subject.");
                $("#subject").focus();
                return false;
            }
            if (from_name == ""){
                alert("From name not entered! Please enter the from name.");
                $("#name").focus();
                return false;
            }
            if (from_email_address == ""){
                alert("From email address not entered! Please enter the from email address.");
                $("#formaddress").focus();
                return false;
            }
            if(fromaddrvalid == false){
                alert("From Address Not Valid! Please Enter valid Email id.");
                $("#formaddress").focus();
                return false;
            }
            if (to_email_addresses == ""){
                alert("To address fieled not entered! Please enter the email address.");
                $("#toaddress").focus();
                return false;
            }
            if(to_email_addresses !== ""){
                
                var split = to_email_addresses.split(",");
                              
                for (var i = 0; i < split.length; i++) {
                    //alert(split[i]+"  split length"+split.length);
                    var email=split[i].trim();
                    if(reg.test(email) !== "")
                    {
                        if(email !== "")
                        {
                            if(reg.test(split[i]) === false){
                                alert(" To Address field is not Valid! Please Enter Valid Email Address \n\n'"+split[i]+"'\t is Invalid Email id.");
                                $("#toaddress").focus();
                                return false;
                            } 
                        }
                    }
                 }
            }
            if (reply_to_email_address == ""){
                alert("Reply to email address not entered! Please enter the reply to email address.");
                $("#email").focus();
                return false;
            }
            if(replytoaddrvalid ==false){
                alert("Reply to email address is not Valid! Please enter valid reply to email address.");
                $("#email").focus();
                return false;
            }
            if (email_list == ""){
                alert("Email list not entered! Please enter the email list.");
                $("#email_list").focus();
                return false;
            }    
                $("#sendpopup").show();
                $("#fade").show();
                return true;
            }
            function sendEmail() {
                
                var draft_id = $("#draft_id").val();
                if(validate()){
                   
                $('<img id="loadingGif" src="images/YogaLoadingGif.gif"/>').appendTo('body').css("position", "absolute").css("top", "300px").css("left", "500px");
//                alert(formattedHTMLData);
                $.ajax({                    
                    url: getHost() + "/email/send",
                    type: "POST",
                    data: JSON.stringify({
                        from_name: $("#name").val(),
                        email_subject: $("#subject").val(),
                        email_addresses: $("#toaddress").val(),
                        from_email_address: $("#formaddress").val(),
                        reply_to_email_address: $("#email").val(),
                        htmldata: formattedHTMLData,
                        email_list: $("#email_list").val(),
                        iframeName: $("#iframe_name").val()
                    }),
                   
                    success: function (responseText) {
                       
                       if(responseText.d.message == "true") {
                        $.ajax({
                            url: getHost() + "deleteEmailDraft?draftid="+draft_id,
                            type: "POST",
                            success: function (responseText) {
                                if(responseText=="true")
                                {
                                    $('#loadingGif').remove();
                                    setTimeout(function () {
                                        alert(emailsend);
                                        window.location = "dashboard";
                                    }, 1000);
                                   
                                }
                            },
                            error: function () {
                                $('#loadingGif').remove();
                                alert("Error!");
                            }
                        });
                    }
                    },
                    error: function () {
                        $('#loadingGif').remove();
                        alert("Error!");
                    }
                });
            }
                $("#emaildetailsid").hide(); 
                $("#sendpopup").hide();
                $("#fade").hide();               
            }
            function validateact(){
               if(document.getElementById('email_actions').value === "0")
                {
                document.getElementById('schedule_title').disabled=false;
                document.getElementById('schedule_date').disabled=false;
                document.getElementById('schedule_time').disabled=false;
                 }
            else{
                document.getElementById('schedule_title').disabled=true;
                document.getElementById('schedule_date').disabled=true;
                document.getElementById('schedule_time').disabled=true;
                document.getElementById('schedule_title').value="";
                document.getElementById('schedule_time').value="";
                document.getElementById('schedule_date').value="";
                }                 
            }
        function EmailListSetting($scope, $http) {
            
            $scope.getEmailSettings = function(){
                showOverlay();
                $http({
                    method : 'GET',
                    url : getHost()+'/settings/getEmailSettings',
                    headers: {'Content-Type': 'application/json'}
                }).success(function (data, status, headers, config) {
                    var parseData=JSON.parse(data.d.details);
//                    alert(JSON.stringify(parseData));
                    $scope.email_settings = parseData;
                    hideOverlay();
                    if (data === error) {
                        alert(data);
                    }
                }).error(function (data, status, headers, config) {
                    hideOverlay();
                    alert("No data available! Problem fetching the data.");
                });
            };
        }
        
        function EmailListController($scope, $http) {
            
            $scope.addEmailList = function () {
                var email_list_name = $("#listname").val();
                var email_list = $("#textArea").val();

                var Emails = {"emailListName": email_list_name, "emailAddresses": email_list , "update": "addUpdateEmailList"};
                    $http({
                        method: 'POST',
                        url: getHost() + '/emaillist/save',
                        headers: {'Content-Type': 'application/json'},
                        data: Emails
                    }).success(function (data)
                    {
                        if (data === "true") {
                            alert("Data saved successfully.");
                           window.open(getHost() + 'emailsubject.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    }).error(function (e){
                        alert(nodataerror);
                    });
            };

            $scope.showEmailList = function () {
                    showOverlay();                
                    $(".emaillist").show();
                    $("#email_list_name").hide();

                    $http({
                        method: 'GET',
                        url: getHost() + '/emaillist/get?update=allEmailListWithNoOfContacts&emailListName=null'
                    }).success(function(data, status, headers, config) {
                        var parseData=JSON.parse(data.d.details);
//                        alert(JSON.stringify(parseData.allEmailListWithNoOfContacts.user)+"..... success....");
                        $scope.emailLists = parseData.allEmailListWithNoOfContacts.user;
                        $scope.emailLists_mindbody = parseData.allEmailListWithNoOfContacts.mindbody;
                        hideOverlay();
                        if (data === "true") {
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                        } else if (data === error) {
                            alert(data);
                        }
                    }).error(function(error) {
                        hideOverlay();
                        alert(JSON.stringify(error)+" ..... error ......");
                    });
            };

            $scope.clearfields = function() {
                    $("#email_list_name").val("");
                    $("#emailaddresses").val("");
                    $("#fileUpload").val("");
                    $("#chooseEmailList").val("");
                };
        }
        function upload() {
          
                var reg=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                var fileUpload = document.getElementById("fileid");
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
                if (regex.test(fileUpload.value.toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = document.createElement("table");
                            var rows = e.target.result.split("\n");
//                            alert(rows);
//                            var emailaddresstextarea=rows;
                                 var split=e.target.result.split(",");
                                 for (var i = 0; i < split.length; i++) {
                                     //alert(split[i]+"  split length"+split.length);
                                     var email=split[i].trim();
                                     if(reg.test(email) !== "")
                                     {
                                         if(email !== "")
                                         {
                                             if(reg.test(split[i]) === false){
                                                 alert("This file contains Invalid Email Address! \n'"+split[i]+"'\t is Invalid Email id in this file. \nPlease select a valid File.");
                                                 $("#fileid").val("");
                                                 $("#filetext").empty().append("Click to Select file");
                                                 return false;
                                            }                                                
                                         }                       
                                     }
                                  }
                            if ($('#emailaddresses').val() == "") {
                                $('#emailaddresses').val(rows);
                            } else {
                                $('#emailaddresses').val($('#emailaddresses').val() + rows);
                            }
                        }
                        reader.readAsText(fileUpload.files[0]);

                    } else {

                        alert("This browser does not support HTML5!");
                    }
                } else {
                    alert("Please upload a valid CSV file!");
                }

            }
            function selectCsvFile(){
//                $("#chooseEmailList").show();
//                 $('#chooseEmailList').val("1").trigger('change');
                 
                var x = document.getElementById("chooseEmailList").selectedIndex;
                var list_name = document.getElementsByTagName("option")[x].value;
                    if(list_name !== 1){
                        $("#emladdrstxtarea,#clktoupload").show();
                    $("#emailIdContinueButton").show();
                    $("#upload").show();
                    $("#emailIdContinueButton").show();
                    }
                if (list_name == 1){  
                    $("#emladdrstxtarea,#clktoupload").show();
                    $("#emailIdContinueButton").show();
                    $("#entertext").show();
                    $("#dragtext").show();
                    $("#emailaddresses").show();
//                    $("#emailaddresses").val('');
                    $("#drop-zone").show();
                    $("#clickHere").show();
                    $("#upload").show();
                    $(".fileclass").show();
                    $("#emailIdContinueButton").css("top","0px");
//                    $(function () {
//
//                    var dropZoneId = "drop-zone";
//                    var buttonId = "clickHere";
//                    var mouseOverClass = "mouse-over";
//
//                    var dropZone = $("#" + dropZoneId);
//                    var ooleft = dropZone.offset().left;
//                    var ooright = dropZone.outerWidth() + ooleft;
//                    var ootop = dropZone.offset().top;
//                    var oobottom = dropZone.outerHeight() + ootop;
//                    var inputFile = dropZone.find("input");
//
////                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
////                        e.preventDefault();
////                        e.stopPropagation();
////                        dropZone.addClass(mouseOverClass);
////                        var x = e.pageX;
////                        var y = e.pageY;
////
////                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
////                            inputFile.offset({ top: y - 15, left: x - 100 });
////                        } else {
////                            inputFile.offset({ top: -400, left: -400 });
////                        }
////
////                    }, true);
//
//                    if (buttonId != "") {
//                        var clickZone = $("#" + buttonId);
//
//                        var oleft = clickZone.offset().left;
//                        var oright = clickZone.outerWidth() + oleft;
//                        var otop = clickZone.offset().top;
//                        var obottom = clickZone.outerHeight() + otop;
//
//                        $("#" + buttonId).mousemove(function (e) {
//                            var x = e.pageX;
//                            var y = e.pageY;
//                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
//                                inputFile.offset({ top: y - 15, left: x - 160 });
//                            } else {
//                                inputFile.offset({ top: -400, left: -400 });
//                            }
//                        });
//                    }
//
//                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
//                        $("#" + dropZoneId).removeClass(mouseOverClass);
//                        alert("File have been added, Click on the upload button to load the csv file.");
//    //                    upload();
//                    }, true);
//                    });

                }else {
                   $("#emailIdContinueButton").show();
                    $("#entertext").show();
                    $("#dragtext").show();
                    $("#emailaddresses").show();
//                    $("#emailaddresses").val('');
                    $("#drop-zone").show();
                    $("#clickHere").show();
//                    $("#upload").show();
                    $("#emailIdContinueButton").css("top","0px");
//                    $(function () {
//
//                    var dropZoneId = "drop-zone";
//                    var buttonId = "clickHere";
//                    var mouseOverClass = "mouse-over";
//
//                    var dropZone = $("#" + dropZoneId);
//                    var ooleft = dropZone.offset().left;
//                    var ooright = dropZone.outerWidth() + ooleft;
//                    var ootop = dropZone.offset().top;
//                    var oobottom = dropZone.outerHeight() + ootop;
//                    var inputFile = dropZone.find("input");
//
//                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
//                        e.preventDefault();
//                        e.stopPropagation();
//                        dropZone.addClass(mouseOverClass);
//                        var x = e.pageX;
//                        var y = e.pageY;
//
//                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
//                            inputFile.offset({ top: y - 15, left: x - 100 });
//                        } else {
//                            inputFile.offset({ top: -400, left: -400 });
//                        }
//
//                    }, true);
//
//                    if (buttonId != "") {
//                        var clickZone = $("#" + buttonId);
//
//                        var oleft = clickZone.offset().left;
//                        var oright = clickZone.outerWidth() + oleft;
//                        var otop = clickZone.offset().top;
//                        var obottom = clickZone.outerHeight() + otop;
//
//                        $("#" + buttonId).mousemove(function (e) {
//                            var x = e.pageX;
//                            var y = e.pageY;
//                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
//                                inputFile.offset({ top: y - 15, left: x - 160 });
//                            } else {
//                                inputFile.offset({ top: -400, left: -400 });
//                            }
//                        });
//                    }
//
//                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
//                        $("#" + dropZoneId).removeClass(mouseOverClass);
//                        alert("The CSV file has been added, click on the upload button to load the CSV file in the form.");
//    //                    upload();
//                    }, true);
//                    });

                }

            }
 