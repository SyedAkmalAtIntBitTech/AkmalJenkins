<%-- 
    Document   : emailpreview
    Created on : 6 Aug, 2015, 3:57:37 PM
    Author     : sandeep-kumar
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="fonttypekit.jsp"%>
        <%@ include file="checksession.jsp" %>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <script src="js/leftmenuhamburger.js" type="text/javascript"></script>

        <link href="css/emailpreview.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="http://cdnjs.cloudflare.com/ajax/libs/fancybox/1.3.4/jquery.fancybox-1.3.4.css" />
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <style type="text/css">
            a.fancybox div {
                border: none;
                box-shadow: 0 1px 7px rgba(0,0,0,0.6);
                -o-transform: scale(1,1); -ms-transform: scale(1,1); -moz-transform: scale(1,1); -webkit-transform: scale(1,1); transform: scale(1,1); -o-transition: all 0.2s ease-in-out; -ms-transition: all 0.2s ease-in-out; -moz-transition: all 0.2s ease-in-out; -webkit-transition: all 0.2s ease-in-out; transition: all 0.2s ease-in-out;
            } 
            a.fancybox:hover div {
                position: relative; z-index: 999; -o-transform: scale(1.03,1.03); -ms-transform: scale(1.03,1.03); -moz-transform: scale(1.03,1.03); -webkit-transform: scale(1.03,1.03); transform: scale(1.03,1.03);
            }
        </style>
        <title>email preview</title>
        <style>
            #iphone{
                width: 25px;
                height: 50px;
            }
            #imac{
                width: 50px;
                height: 50px;
            }
            #ipad{
                width: 90px;
                height: 50px;
            }
            .images li{
                display: inline-table;
                margin-right: 10px;
                margin-top: 100px;
            }
            #popupschedule
            {
                display:none;
                position: fixed;
                width:350px;
                height:500px;
                top: 40%;
                left: 50%;
                margin-left:-155px;
                margin-top:-110px;
                border:5px solid #686868 ;
                background-color:#CDCDFF;
                padding:30px;
                z-index:102;
                font-family:Verdana;
                font-size:10pt;
                border-radius:10px;
                -webkit-border-radius:20px;
                -moz-border-radius:20px;
                font-weight:bold;
            }
            #content
            {
                height:auto;
                width:300px;
                margin:5px auto;
            }
            #content input{
                /*                width:200px;*/
            }
            #popupclose
            {
                margin:35px 0 0 80px;
                width:50px;

            }

            .vlightbox {
                display:-moz-inline-stack;
                display:none;
                zoom:1;
                *display:none;
                position:relative;
                vertical-align:top;
                margin:3px;
                width:160px;
                font-family:Trebuchet,Tahoma,Arial,sans-serif;
                font-size:11px;
                font-weight:normal;
                text-decoration:none;
                text-align:center;
                opacity:0.87;
            }
            .vlightbox {
                display:-moz-inline-stack;
                display:inline-block;
                zoom:1;
                *display:inline;
                position:relative;
                vertical-align:top;
                margin:3px;
                width:160px;
                font-family:Trebuchet,Tahoma,Arial,sans-serif;
                font-size:11px;
                font-weight:normal;
                text-decoration:none;
                text-align:center;
                opacity:0.87;
            }
            .content{
                position: relative;
                top: 95px;
                /*                margin-left: 60px;
                                zoom: 0.5;*/
            }
            #popup {
                /*               
                                width: 500px;
                                height: 50em;*/
            }
            .preview{

            }


        </style>
        <script type="text/javascript">
            var started;
            function showLightBox()
            {
                if (started)
                    return;
                started = setTimeout(function () {
                    Lightbox.start(document.getElementById('firstImage'));
                    started;
                }, 500);
            }
            function stopShowLightBox() {
                if (started) {
                    clearTimeout(started);
                    started = 0;
                }
            }
        </script>

        <%!        String emailSubject = "";
            String emailList = "";
            String htmlData = "";
            String emailAddresses = "";
        %>
        <%        sqlmethods.session = request.getSession(true);

            emailSubject = (String) sqlmethods.session.getAttribute("email_subject");
            emailList = (String) sqlmethods.session.getAttribute("email_list");
            emailAddresses = (String) sqlmethods.session.getAttribute("email_addresses");
            htmlData = (String) sqlmethods.session.getAttribute("htmldata");
        %>
        <script>
            function emailSettings($scope, $http) {

                $scope.getEmailSettings = function () {

                    var email_settings = {"type": "get"};

                    $http({
                        method: 'POST',
                        url: 'EmailSettingsServlet',
                        headers: {'Content-Type': 'application/json'},
                        data: email_settings
                    }).success(function (data, status, headers, config) {
                        $scope.email_settings = data;
                        if (data === error) {
                            alert(data);
                        }
                    }).error(function (data, status, headers, config) {
                        alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });

                };

                $scope.setScheduling = function () {

                    var from_name = $("#name").val();
                    var email_subject = $("#subject").val();
                    var to_email_addresses = $("#toaddress").val();
                    var from_email_address = $("#formaddress").val();
                    var reply_to_email_address = $("#email").val();
                    var email_body = formattedHTMLData;
                    var email_list = $("#email_list").val();
                    var schedule_title = $("#schedule_title").val();
                    var schedule = $("#schedule_time").val();
                    var schedule_desc = $("#schedule_desc").val();
                    console.log("Value selected from Component: " + schedule);
                    var schedule_time = Date.parse(schedule);
                    console.log("Epoch: " + schedule_time);

                    var dateObj = new Date(schedule_time);
                    console.log(dateObj.getTimezoneOffset());

                    var tzOffsetInMillis = dateObj.getTimezoneOffset() * 60 * 1000;

                    var newEpoch = schedule_time + tzOffsetInMillis;
                    console.log("New Epoch: " + newEpoch);

                    var email_scheduling = {"from_name": from_name, "email_subject": email_subject, "to_email_addresses": to_email_addresses, "from_email_address": from_email_address, "reply_to_email_address": reply_to_email_address, "email_list": email_list, "schedule_title": schedule_title, "schedule_time": newEpoch, "email_body": email_body, "schedule_desc": schedule_desc};
                    $http({
                        method: 'POST',
                        url: 'ScheduleEmail',
                        headers: {'Content-Type': 'application/json'},
                        data: email_scheduling
                    }).success(function (data) {
                        if (data != "") {
                            alert("details saved successfully");
                            document.location.href = "dashboard.jsp";
                        }
                    }).error(function (data) {
                        alert("No data available, problem fetching the data");
                    });
                };
                $scope.getActions = function(){
                    $http({
                        method: 'GET',
                        url: 'ScheduleEmail',
                    }).success(function (data) {
                        if (data != "") {
                            alert("details saved successfully");
                            document.location.href = "dashboard.jsp";
                        }
                    }).error(function (data) {
                        alert("No data available, problem fetching the data");
                    });
                };
            }

        </script>

        <script>
            var formattedHTMLData = "";
            $(document).ready(function () {
                $.ajax({
                    url: getHost() + "PreviewServlet",
                    method: "post",
                    data: {htmlString: $(".content").html()},
                    success: function (responseText) {
                        formattedHTMLData = responseText;
                        //show popup showing
    //                    alert(formattedHTMLData);
                        $(".content").empty();
                        $(".content").append("<iframe id='dynamictable' style='position:relative;background-color:#FFF;' src='/BrndBot/DownloadHtmlServlet?file_name=emailhtmltemplate.html'></iframe>");

                    }
                });
            });
            function show(id) {
                var imageUrl = $("#" + id).css("background-image");

                if (id === "ipad") {
                    $(".iphoneshow").css("background-image", imageUrl).css("display", 'block').css("width", "239px").css("height", "300px")
                            .css("border-color", "transparent").css("margin-left", "-55px").css("margin-top", "-80px").css("background-color", "#FFF");
                    $('#dynamictable').css("width", "768px").css("height", "987px").css("top", "-474px").css("left", "-300px").css("-webkit-transform", " scale(0.265)");
                }
                else if (id === "imac")
                {
                    $(".iphoneshow").css("background-image", imageUrl)
                            .css("display", 'block').css("height", "413px").css("width", "295px").css("margin-left", "-55px").css("margin-top", "-80px")
                            .css("border-color", "transparent").css("background-color", "#FFF");
                    $('#dynamictable').css("width", "768px").css("height", "620px").css("top", "-320px").css("left", "-272px").css("-webkit-transform", "scale(0.33)");
                }
                else if (id === "iphone") {
                    $(".iphoneshow").css("background-image", imageUrl)
                            .css("display", 'block').css("height", "370px").css("width", "415px").css("margin-left", "-55px").css("margin-top", "-80px")
                            .css("border-color", "transparent").css("background-color", "#FFF");
                    $('#dynamictable').css("width", "320px").css("height", "655px").css("top", "-276px").css("left", "-107px").css("-webkit-transform", "scale(0.47)");
                }
            }
            function displaySchedule() {
                if (validate()) {
                    $("#popupschedule").show();
                }
            }
            function hidepopup() {
                $("#popupschedule").hide();
                $("#schedule_title").val("");
                $("#schedule_time").val("");
            }
            function validate() {
                var from_name = $("#name").val();
                var email_subject = $("#subject").val();
                var to_email_addresses = $("#toaddress").val();
                var from_email_address = $("#formaddress").val();
                var reply_to_email_address = $("#email").val();
                var htmldata = formattedHTMLData;
                var email_list = $("#email_list").val();
                var schedule_title = $("#schedule_title").val("");
                var schedule_time = $("#schedule_time").val("");

                if (from_name == "") {
                    alert("from name not entered, please enter the from name");
                    $("#name").focus();
                    return false;
                }
                if (email_subject == "") {
                    alert("email subject name not entered, please enter the email subject");
                    $("#subject").focus();
                    return false;
                }
                if (to_email_addresses == "") {
                    alert("email addresses not entered, please enter the email addresses");
                    $("#toaddress").focus();
                    return false;
                }
                if (from_email_address == "") {
                    alert("from email addresses not entered, please enter the from email addresses");
                    $("#formaddress").focus();
                    return false;
                }
                if (reply_to_email_address == "") {
                    alert("reply to email addresses not entered, please enter the reply to email addresses");
                    $("#email").focus();
                    return false;
                }
                if (email_list == "") {
                    alert("email list not entered, please enter the email list");
                    $("#email_list").focus();
                    return false;
                }
                return true;
            }

            function sendEmail() {
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
                        email_list: $("#email_list").val()
                    },
                    success: function (responseText) {
                        $('#loadingGif').remove();

                        document.location.href = "emailsent.jsp";
                    },
                    error: function () {
                        alert("error");
                    }

                });

            }

        </script>
        <jsp:include page="basejsp.jsp" />
    </head>

    <body ng-app>
        <div class="row" ng-controller="emailSettings">
            <jsp:include page="leftmenu.html"/>
            <div class="col-md-4 col-md-offset-1" ng-init="getEmailSettings()">
                <p id="textgrt" class="MH1">SEND EMAIL PREVIEW</p>
                <p id="text2">go back</p>
                <form class="form-horizontal" id="emailform">
                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <input id="name" class="form-control simplebox" name="from_name" type="text" required>
                            <label>FROM NAME</label><br>
                        </div>
                    </div>
                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">                            
                            <input id="subject" class="form-control simplebox" name="email_subject" type="text" value='<%= emailSubject%>'>
                            <label>SUBJECT</label><br>
                        </div>
                    </div>

                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <input id="formaddress" class="form-control simplebox" name="from_email_address" type="text" required value="{{email_settings.from_address}}">
                            <label>FROM ADDRESS</label><br>
                        </div>
                    </div>
                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <input id="toaddress" class="form-control simplebox" name="email_addresses" type="text" value='<%= emailAddresses%>'>
                            <label>TO ADDRESS</label><br>
                        </div>
                    </div>

                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <input id="email" class="form-control simplebox" name="reply_to_email_address" type="text" required value="{{email_settings.reply_email_address}}">
                            <label>REPLY TO EMAIL</label><br><br>
                        </div>
                    </div>
                    <div  class="form-group">
                        <div class="col-md-3 col-md-offset-2">
                            <br><br><button type="button" onclick="sendEmail()" class="button button--moema button--text-thick button--text-upper button--size-s">SEND</button><br><br><br>
                        </div>
                        <div class="col-md-1 col-md-offset-1">
                            <br><br><button type="button" onclick="displaySchedule()" class="button button--moema button--text-thick button--text-upper button--size-s">SCHEDULE</button><br><br><br>
                        </div>
                    </div>

                    <input type="hidden" id="email_list" name="email_list" value='<%=emailList%>'>

                </form>
            </div>
            <div class="col-md-4">
                <!--
                                <ul class="images">
                                    <li><div id="iphone" class="img-responsive fancybox" onMouseOver="javascript:showLightBox()" onMouseOut="javascript:stopShowLightBox()" style="cursor: pointer;background-image: url('images/iphone 6 screen.png');"></div></li>
                                    <li><img id="imac" class="img-responsive fancybox" src="images/IMAC.png"></li>
                                    <li ><img id="ipad" class="img-responsive fancybox" src="images/IPAD3.png"></li>
                                
                                </ul>-->

                <ul class="images ">
                    <li><div id="iphone" class="img-responsive " onclick="show('iphone');" style="background-image: url('images/iphone 6 screen.png');background-repeat: no-repeat; -webkit-background-size: contain;"></div></li>
                    <li><div id="imac" class="img-responsive" onclick="show('imac');"  style="background-image: url('images/imac27.png');background-repeat: no-repeat; -webkit-background-size: contain;"></div></li>
                    <li><div id="ipad" class="img-responsive" onclick="show('ipad');"  style="background-image: url('images/IPAD3.png');background-repeat: no-repeat; -webkit-background-size: contain;"></div></li>
                </ul>

                <div id="popupschedule">
                    <div id="content">
<!--                                 Mapper file name<input type="text" id="mapperxml" required><br><br>
                            Layout file name<input type="text" id="layoutxml" required><br>-->
                        PLEASE SELECT A TIME FROM YOUR PLAN:
                        <select>
                            <option value=""></option>
                        </select>
                        
                        Title: <input type="text" class="form-control simplebox" id="schedule_title" name="schedule_title"><br>
                        Description: <textarea id="schedule_desc" class="form-control simplebox1" name="schedule_desc"></textarea><br>
                        Date : <input type="datetime-local" class="form-control simplebox" id="schedule_time" name="schedule_time"><br>

                        <input type="button" class="button button--moema button--text-thick button--text-upper button--size-s" ng-click="setScheduling()" value="Done"/>   
                        <input type="button" class="button button--moema button--text-thick button--text-upper button--size-s" id="hidepopup" value="Close" onclick="hidepopup()"/>   
                    </div>
                </div>


                <div class="iphoneshow img-responsive" id="popup" style="background-repeat: no-repeat; -webkit-background-size: contain; display: none;">
                    <div class="content">  
                        <%= htmlData%>
                    </div>
                </div>






            </div>
        </div>

    </body>
</html>
