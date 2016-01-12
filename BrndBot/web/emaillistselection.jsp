<%-- 
    Document   : emaillistselection
    Created on : Jan 9, 2016, 11:56:23 AM
    Author     : Syed Akmal at IntBit Technologies.
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@page import="com.controller.SqlMethods"%>
<%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="js/angular.min.js" type="text/javascript"></script>
    <script src="js/configurations.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <title>BrndBot - Email List Selection</title>    
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="stylesheet" type="text/css" href="css/slat.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <jsp:include page="basejsp.jsp" />
    <style>
        #emladdrstxtarea,#clktoupload,#upload,#emaildetailscontbtn,#emaildetailsid,#backemaildetails{display:none;}
    </style>
    <%!
        String emailSubject = "";
        String emailList = "";
        String htmlData = "";
        String emailAddresses = "";
        String iframeName = "";
        String iframeUrl="";
        String draft_id = "0";
    %>
    <%
        sqlmethods.session = request.getSession(true);

        emailSubject = (String) sqlmethods.session.getAttribute("email_subject");
        //emailAddresses = (String) sqlmethods.session.getAttribute("email_addresses");
        htmlData = (String) sqlmethods.session.getAttribute("htmldata");
        iframeName = (String) sqlmethods.session.getAttribute("iframeName");
        iframeUrl="/BrndBot/DownloadHtmlServlet?file_name="+iframeName+".html";
        draft_id = "0";
        if (!request.getParameter("draftid").equals("null")){
                    draft_id = (String)request.getParameter("draftid");
                    out.println();
        }
    %>    
    <script>
        var draft_id = 0;
         $(document).ready(function () {
                    draft_id = <%= draft_id %>;
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
                        
                        if(email_addresses!=="")
                        {   
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
                            $("#toaddress").val(email_addresses);
                            $("#emaillistdiv").hide();
                            $("#toaddress").val(email_addresses);
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
        function emailSettings($scope, $http){
            
               
                
            
                $scope.getEmailSettings = function(){
                
                var email_settings = {"type": "get"};
                
                $http({
                        method : 'POST',
                        url : 'EmailSettingsServlet',
                        headers: {'Content-Type': 'application/json'},
                        data: email_settings
                    }).success(function (data, status, headers, config) {
                        $scope.email_settings = data;
                        if (data === error) {
                            alert(data);
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available! Problem fetching the data.");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
                };
                
                $scope.setScheduling = function () {
                    var schedule_id = $("#email_actions").val();
                    var from_name = $("#name").val();
                    var email_subject = $("#subject").val();
                    var to_email_addresses = $("#toaddress").val();
                    var from_email_address = $("#formaddress").val();
                    var reply_to_email_address = $("#email").val();
                    var program_id = $("#programs").val();
                    var email_body = formattedHTMLData;
                    var email_list = $("#chooseEmailList").val();
                    var schedule_desc = "none";
                    var iframe_name = $("#iframe_name").val();
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
                            url: 'ScheduleEmail',
                            headers: {'Content-Type': 'application/json'},
                            data: email_scheduling
                        }).success(function (data) {                            
                            if (data != "") {
                               $http({
                               method: 'POST',
                               url: getHost() + "deleteEmailDraft.do?draftid="+draft_id
                               }).success(function (data) {
                                   alert("Your Email has been Scheduled Successfully");
                                   document.location.href = "dashboard.jsp";
                                
                                }).error(function (data) {
                                    alert("No data available! Problem fetching the data.");
                                });
                            }
                        }).error(function (data) {
                            alert("No data available! Problem fetching the data.");
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
                            url: 'ScheduleEmailActions',
                            headers: {'Content-Type': 'application/json'},
                            data: email_scheduling
                        }).success(function (data) {
                            if (data != "") {
                               $http({
                               method: 'POST',
                               url: getHost() + "deleteEmailDraft.do?draftid="+draft_id
                               }).success(function (data) {
                                alert("Your Email has been Scheduled Successfully.");
                                document.location.href = "dashboard.jsp";
                                
                                }).error(function (data) {
                                    alert("No data available! Problem fetching the data.");
                                });
                            }
                        }).error(function (data) {
                            alert("No data available! Problem fetching the data.");
                        });
                        
                    }
                    
                };
                
                $scope.getProgramNames = function() {
                    $http({
                       method: 'GET',
                       url:getHost() + 'getAllUserMarketingPrograms.do'
                    }).success(function (data){
//                        alert(JSON.stringify(data));
                        $scope.marketing_programs = data;
                    }).error(function (data){
                        alert("Request not successful!");
                    });
                };
                
                $scope.getActions = function (program_id) {
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledActions?programid='+ program_id + '&type='+ getemail()
                    }).success(function (data) {
                        $scope.email_actions = data;
                    }).error(function (data) {
                        alert("Request not successful!");
                    });
                };
            }
        </script>
   <script>
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
                                url: getHost() + "GetEmailLists",
                                data: {
                                    update: "emailsForEmailList",
                                    list_name: List_name
                                },
                                success: function(result){
                                    var i = 0;
                                    
                                    for(i=0; i<result.user_emailAddresses.length; i++){
                                        if (result.user_emailAddresses[i].emailAddress!= ""){
                                            emails = result.user_emailAddresses[i].emailAddress+ "," + emails;
                                     }
                                    }
                                    for(i=0; i<result.mindbody_emailAddresses.length; i++){
                                        if (result.mindbody_emailAddresses[i] != ""){
                                            emails = result.mindbody_emailAddresses[i] + "," + emails;
                                        }
                                    }                                    
                                    $("#emailaddresses").val(emails);
                                    $("#toaddress").val(emails);
                                }
                        });
                    }
                });
           
//            $(".hamburger,.cross").hide();
            $("#programs").change(function(){
                    
                program_id = $("#programs").val();
                angular.element(document.getElementById('emailSettings')).scope().getActions(program_id);
                if (parseInt(program_id) == 0){
                    $("#email_actions").attr("disabled", false);

                    document.getElementById('schedule_title').disabled=false;
                    document.getElementById('schedule_date').disabled=false;
                    document.getElementById('schedule_time').disabled=false;
                }else {
                    $("#email_actions").attr("disabled", false);

                    document.getElementById('schedule_title').disabled=true;
                    document.getElementById('schedule_date').disabled=true;
                    document.getElementById('schedule_time').disabled=true;

                }

            });
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

            if (id === "ipad") {
                $(".iphoneshow").css("background-image", imageUrl).css("display", 'block').css("width", "239px").css("height", "300px")
                        .css("border-color", "transparent").css("margin-left", "150px").css("margin-top", "-80px").css("background-color","#FFF");
                $('#dynamictable').css("width","770px").css("height","958px").css("top","-465px").css("left","-300px").css("-webkit-transform"," scale(0.265)");
            }
            else if (id === "imac")
            {
                $(".iphoneshow").css("background-image", imageUrl)
                        .css("display", 'block').css("height", "413px").css("width", "295px").css("margin-left", "150px").css("margin-top", "-80px")
                        .css("border-color", "transparent").css("background-color","#FFF");
                 $('#dynamictable').css("width","768px").css("height","615px").css("top","-316px").css("left","-272px").css("-webkit-transform","scale(0.3326)");
            }
//            $("#"+id).toggleClass('selection-icon');
//            $("#"+id).toggleClass('selection-icon-selected');

            else if (id === "iphone"){
                $(".iphoneshow").css("background-image", imageUrl)
                        .css("display", 'block').css("height", "370px").css("width", "415px").css("margin-left", "150px").css("margin-top", "-80px")
                        .css("border-color", "transparent").css("background-color","#FFF");
                 $('#dynamictable').css("width","640px").css("height","1024px").css("top","-465px").css("left","-249px").css("-webkit-transform","scale(0.278)");
            }
            
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
            return true;
            }
            function sendEmail() {
                
                if(validate()){
                $('<img id="loadingGif" src="images/YogaLoadingGif.gif" />').appendTo('body').css("position", "absolute").css("top", "300px").css("left", "500px");
               
                $.ajax({

                    url: getHost() + "SendEmailServlet",
                    type: "post",
                    data: {
                        from_name: $("#name").val(),
                        email_subject: $("#subject").val(),
                        email_addresses: $("#toaddress").val(),
                        from_email_address: $("#formaddress").val(),
                        reply_to_email_address: $("#email").val(),
                        htmldata: formattedHTMLData,
                        email_list: $("#email_list").val(),
                        iframeName: $("#iframe_name").val()
                    },
                    success: function (responseText) {
                       
                        
                        $.ajax({
                            url: getHost() + "deleteEmailDraft.do?draftid="+draft_id,
                            type: "post",
                            success: function (responseText) {
                                if(responseText=="true")
                                {
                                    $('#loadingGif').remove();
                                    document.location.href = "emailsent.jsp";
                                }
                            },
                            error: function () {
                                alert("Error!");
                            }        
                        });
                    },
                    error: function () {
                        alert("Error!");
                    }
                });
            }
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
        
        function EmailListController($scope, $http) {

                $scope.addEmailList = function () {
                    var email_list_name = $("#listname").val();
                    var email_list = $("#textArea").val();
                
                    var Emails = {"emailListName": email_list_name, "emailAddresses": email_list , "update": "addUpdateEmailList"};
                        $http({
                            method: 'POST',
                            url: getHost() + 'SetEmailLists',
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
                        });
                };

                $scope.showEmailList = function () {
                                $(".emaillist").show();
                                $("#email_list_name").hide();

                        var emailids = {"update": "allEmailListNames"};
                        $http({
                            method: 'GET',
                            url: getHost() + 'GetEmailLists?update=allEmailListNames'
                    }).success(function(data, status, headers, config) {
                            $scope.emailLists = data.user;
                            $scope.emailLists_mindbody = data.mindbody;
                            if (data === "true") {
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
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

                var fileUpload = document.getElementById("fileid");
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
                if (regex.test(fileUpload.value.toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = document.createElement("table");
                            var rows = e.target.result.split("\n");
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
                    $(function () {

                    var dropZoneId = "drop-zone";
                    var buttonId = "clickHere";
                    var mouseOverClass = "mouse-over";

                    var dropZone = $("#" + dropZoneId);
                    var ooleft = dropZone.offset().left;
                    var ooright = dropZone.outerWidth() + ooleft;
                    var ootop = dropZone.offset().top;
                    var oobottom = dropZone.outerHeight() + ootop;
                    var inputFile = dropZone.find("input");

                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                        dropZone.addClass(mouseOverClass);
                        var x = e.pageX;
                        var y = e.pageY;

                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
                            inputFile.offset({ top: y - 15, left: x - 100 });
                        } else {
                            inputFile.offset({ top: -400, left: -400 });
                        }

                    }, true);

                    if (buttonId != "") {
                        var clickZone = $("#" + buttonId);

                        var oleft = clickZone.offset().left;
                        var oright = clickZone.outerWidth() + oleft;
                        var otop = clickZone.offset().top;
                        var obottom = clickZone.outerHeight() + otop;

                        $("#" + buttonId).mousemove(function (e) {
                            var x = e.pageX;
                            var y = e.pageY;
                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                                inputFile.offset({ top: y - 15, left: x - 160 });
                            } else {
                                inputFile.offset({ top: -400, left: -400 });
                            }
                        });
                    }

                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
                        $("#" + dropZoneId).removeClass(mouseOverClass);
                        alert("File have been added, Click on the upload button to load the csv file.");
    //                    upload();
                    }, true);
                    });

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
                    $(function () {

                    var dropZoneId = "drop-zone";
                    var buttonId = "clickHere";
                    var mouseOverClass = "mouse-over";

                    var dropZone = $("#" + dropZoneId);
                    var ooleft = dropZone.offset().left;
                    var ooright = dropZone.outerWidth() + ooleft;
                    var ootop = dropZone.offset().top;
                    var oobottom = dropZone.outerHeight() + ootop;
                    var inputFile = dropZone.find("input");

                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                        dropZone.addClass(mouseOverClass);
                        var x = e.pageX;
                        var y = e.pageY;

                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
                            inputFile.offset({ top: y - 15, left: x - 100 });
                        } else {
                            inputFile.offset({ top: -400, left: -400 });
                        }

                    }, true);

                    if (buttonId != "") {
                        var clickZone = $("#" + buttonId);

                        var oleft = clickZone.offset().left;
                        var oright = clickZone.outerWidth() + oleft;
                        var otop = clickZone.offset().top;
                        var obottom = clickZone.outerHeight() + otop;

                        $("#" + buttonId).mousemove(function (e) {
                            var x = e.pageX;
                            var y = e.pageY;
                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                                inputFile.offset({ top: y - 15, left: x - 160 });
                            } else {
                                inputFile.offset({ top: -400, left: -400 });
                            }
                        });
                    }

                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
                        $("#" + dropZoneId).removeClass(mouseOverClass);
                        alert("The CSV file has been added, click on the upload button to load the CSV file in the form.");
    //                    upload();
                    }, true);
                    });

                }

            }
    </script>
</head>    

<body ng-app>
    <!--SideNav-->
    <div class="content-main" ng-controller="EmailListController" ng-init="showEmailList()">
    <%@include file="navbarv2.jsp" %>
        
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <div class="exit-button-detail">
                   <a class=" exit-button-icon" href="" id="backemaillist">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                   </a>
                   <a class=" exit-button-icon" href="" id="backemaildetails">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
                   </a>
            </div>
            <div class="page-title-with-back page-title-font">Email List Selection</div>
            <!--<div class="page-cta-container">
                <a href="" class="gray-button fleft">
                    <div class=" md-button">  End Marketing Program</div>    
                </a>
            </div>-->
        </div>
        <!--<div class="page-subnav-bar-with-dropdown"> 
              <div class="subnav-dropdown">
                 <span class="hub-dropdown-text">Email</span>
                  <object type="image/svg+xml" data="/Icons/dropdown-icon.svg" class="dropdown-icon" style="cursor:pointer;"> </object>
            </div>
            <div class="top-subnav-tabs-container">
                <ul class="top-subnav-nav-elements">
                    <li class="top-subnav-link-active"> <a class="h3-active-subnav">Email Drafts</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email Analytics</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email History</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email Lists</a></li>
                    <li class="top-subnav-links"> <a class="h3">Email Settings</a></li>
                </ul>
            </div>
        </div>-->
    </div>
        <!--Main Content GENERIC--> 
        <div class="sequence-page-background" id="emaillistselid">
        <div class="sequence-page-content-container" >
            <div class="sequence-page-header">Who do you want to send this email to?</div>
            <div class="email-list-selection fleft">
               <div class="col-1of1 fleft unit">
                    <div class="col-9of10 fleft ">
                        <div class="h2 col-1of1">
                            <select class="chooseEmailList col-1of1" id="chooseEmailList" name="chooseEmailList">
                                <option value="1">Manual</option>
                                <option style="background:#fff;" ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                                <option style="background:#fff;" ng-repeat ="Lists in emailLists_mindbody" value="{{Lists}}">{{Lists}}</option>
                            </select>
                        </div>
                        <!--<div class="p chooseList-subtext col-1of1">Choose a recipient list</div>-->
                    </div>
                   <div class="col-9of10 fleft pushUp-30">
                       <input type="button" id="addCsvFileButton" onclick="selectCsvFile()" class="md-button add-button22" value="Add CSV or Email Manually"></input>
                   </div>
                   <div class="col-2of4 fleft pushUp-30" id="clktoupload"> 
                       <div class="h3 col-1of1" id="dragtext" hidden="true">Drag and drop a csv file here and click to upload:</div>
                       <!--<div class="h3 col-1of1 "> Drop files here...</div>-->
                       <div id="drop-zone" class="col-1of4 pushUp-30">
                            <div id="clickHere">
                                <label for="fileid" class="h4 clktosel">Click to Select file </label>      
                                       <input type="file" id="fileid" name="file"  class="" style="display:none;" onchange="fileselected()"/>
                                <!--<div id="fileselect" class="h4">Click to Select file</div>-->                          
                            </div>
                        </div>
                    </div>
                   <div  class="col-2of4 fleft pushUp-30 lftpad-10" id="emladdrstxtarea">
                       <div class="h3 col-1of1" id="entertext" hidden="true">Enter Email Addresses manually here:</div>
                       <textarea class="emailaddresstextarea pushUp" id="emailaddresses"></textarea>
                   </div>
                   
                </div>
                <div class="col-15of4 fleft unit pushUp-30">
                      <input type="button" id="upload" value="Upload" onclick="upload()" class="md-button add-action-button"/>      
                   </div>
                    </div>
                </div>
<!--                <div class="col-1of1 fleft unit pushUp-15">
                    <div class="selection-container col-5p fleft"> 
                        <div id="uploademailaddrs" class="chooseList-icon" onclick="selcheckbox(this.id)"></div>
                    </div>
                    <div class="col-9of10 fleft">
                        <div class="h2 col-1of1">Enter or upload email addresses</div>
                        <div class="p chooseList-subtext col-1of1">Choose a recipient list</div>
                    </div>
                </div>-->
           
            
            <!--Inner Content Conatiner GENERIC-->
            
        </div>
        <div class="sequence-page-background" id="emaildetailsid">
            <div class="sequence-page-content-container">
                <div class="sequence-page-header">Email Details</div>
                    <div class="email-detail-selection col-1of1 fleft">
<!--                       <div class="col-1of1 fleft">
                            <div class="col-9of10 fleft">
                                <div class="h4" style="">
                                    Enter a name for this Email
                                </div>
                                <input id="emailName" class="input-field-textfield col-8of10" placeholder="Enter Name of email" type="text"></input>
                            </div>
                        </div>-->
                        <div class="col-1of1 fleft pushUp">
                            <div class="col-9of10 fleft">
                                <div class="h4" style="">
                                   Enter a subject line for this Email:
                                </div>
                                <input id="subject" class="input-field-textfield col-8of10" placeholder="Enter Subject of email" type="text"></input>
                            </div>
                        </div>
                         <div class="cols-2 fleft col-9of10 pushUp">
                             <div class="input-field-container col-3of10 fleft pushright">
                                <div class="h4"> From Name </div>
                                <input id="name" class="input-field-textfield input-placeholder" placeholder="Enter From Name" type="text"></input>
                            </div>
                             <div class="input-field-container col-3of10 fleft">
                                <div class="h4"> From Email Address </div>
                                <input id="formaddress" class="input-field-textfield input-placeholder" placeholder="Enter From  Email Address" type="text"></input>
                            </div>
                        </div>
                        <div class="cols-2 fleft col-9of10 pushUp">
                             <div class="input-field-container col-3of10 fleft pushright">
                                <div class="h4">To Email Address</div>
                                <input id="toaddress" class="input-field-textfield input-placeholder" placeholder="Enter To Email Address" type="text"></input>
                            </div>
                             <div class="input-field-container col-3of10 fleft">
                                <div class="h4"> Reply To Email Address</div>
                                <input id="email" class="input-field-textfield input-placeholder" placeholder="Enter Reply To Email Address" type="text"></input>
                            </div>
                        </div>
                        <div class="input-field-container col-1of8 fleft pushUp-50">
                        <div class="h2 curpointer">Preview</div>
                        </div>
                    <!--Inner Content Conatiner GENERIC-->
                </div>
            </div>
        </div>
  
        <!--CTA Bar-->
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container-lg">
                    <div class="bottom-continue-button button-text-1" id="emaildetailscontbtn" onclick="validate();">Continue</div>
                    <div class="bottom-continue-button button-text-1" id="emailIdContinueButton">Continue</div>
            </div>
        </div>
        </div>
    <!--</div>-->
  
        <!--CTA Bar-->
        
    <!--</div>-->
<!--</div>-->
    </body>
</html>