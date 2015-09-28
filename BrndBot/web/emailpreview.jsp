<%-- 
    Document   : emailpreview
    Created on : 6 Aug, 2015, 3:57:37 PM
    Author     : sandeep-kumar
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app>
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
                width:900px;
                height:400px;
                top: 40%;
                left: 30%;
                margin-left:-155px;
                margin-top:-110px;
                border:3px solid #888;
                background-color:#fcfcfc;
                padding:1px;
                z-index:1005;
                font-family:Verdana;
                font-size:10pt;
                border-radius:10px;
                -webkit-border-radius:20px;
                -moz-border-radius:20px;
                font-weight:bold;
            }
            .simpleinpbox{
                display:block;
                width:100%;
                padding:6px 12px;
                line-height:1.42857143;
                color:#555;
                border:1px solid #ccc;
                border-radius:4px;
                -webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075);
                box-shadow:inset 0 1px 1px rgba(0,0,0,.075);
                -webkit-transition:border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
                -o-transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;
                transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;
                background-color: #E3E4E8;
                color:#686868;

            }
            .selectsocialact{
                background-color: #E3E4E8;
                border: 1px solid #DADADA;
                height:30px;
                width:250px;
                border-radius: 5px;
                margin-left: 0px;
                color: #686868;
                font-size:18px;
                font-variant:normal;
            }
            #content
            {
                height:auto;
                width:850px;
                margin:5px auto;
                position:relative;
                left:-200px;
                top:-10px;
            }
            .black_overlay{
                display: none;
                position: absolute;
                top: 0%;
                left: 0%;
                width: 100%;
                height: 1000em;
                background-color: #E3E4E8;
                z-index:1000;
                -moz-opacity: 0.8;
                opacity:.80;
                filter: alpha(opacity=80);
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
                /*                                margin-left: 60px;
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
            function overlay() {
                if (validate()) {
                    document.getElementById('light').style.display = 'block';
                    document.getElementById('fade').style.display = 'block';
                    document.body.style.overflow = 'hidden';
                }
            }
        </script>

        <%!            String emailSubject = "";
            String emailList = "";
            String htmlData = "";
            String emailAddresses = "";
        %>
        <%  
            sqlmethods.session = request.getSession(true);
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

                    var schedule_id = "0";
                    var from_name = $("#name").val();
                    var email_subject = $("#subject").val();
                    var to_email_addresses = $("#toaddress").val();
                    var from_email_address = $("#formaddress").val();
                    var reply_to_email_address = $("#email").val();
                    var email_body = formattedHTMLData;
                    var email_list = $("#email_list").val();
                    var schedule_desc = "none";
                    
                    schedule_id = $("#email_actions").val();
                    console.log(schedule_id);
                    if (schedule_id == "0"){
                        var schedule_title = $("#schedule_title").val();
                        
                        var schedule = $("#schedule_time").val();
                        var dateepoch = Date.parse(schedule);

                        var newdate = new Date(dateepoch);

                        console.log("new date:" + newdate);
                        var schedule_hour = $("#hour").val();
                        var schedule_minute = $("#minute").val();
                        var schedule_AM = $("#AMPM").val();

                        if (schedule_AM == "PM"){
                            schedule_hour = parseInt(schedule_hour) + 12;
                        }
                        newdate.setHours(parseInt(schedule_hour));
                        newdate.setMinutes(parseInt(schedule_minute));

                        console.log("Value selected from Component: " + newdate);
                        var schedule_time = Date.parse(newdate);

                        console.log("Epoch: " + schedule_time);

                        var dateObj = new Date(schedule_time);
                        console.log(dateObj.getTimezoneOffset());

                        var tzOffsetInMillis = dateObj.getTimezoneOffset() * 60 * 1000;

                        var newEpoch = schedule_time;
                        console.log("New Epoch: " + newEpoch);

                        var email_scheduling = {
                            "from_name": from_name, 
                            "email_subject": email_subject, 
                            "to_email_addresses": to_email_addresses, 
                            "from_email_address": from_email_address, 
                            "reply_to_email_address": reply_to_email_address, 
                            "email_list": email_list, 
                            "schedule_title": schedule_title, 
                            "schedule_time": newEpoch, 
                            "email_body": email_body, 
                            "schedule_desc": schedule_desc
                        };
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
                            "schedule_desc": schedule_desc
                        };
                        $http({
                            method: 'POST',
                            url: 'ScheduleEmailActions',
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
                        
                    }
                    
                };
                $scope.getActions = function () {
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledActions?type=email'
                    }).success(function (data) {
                        $scope.email_actions = data;
                    }).error(function (data) {
                        alert("request not successful");
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
                    angular.element(document.getElementById('emailSettings')).scope().getActions();
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
            function validateact(){
               if(document.getElementById('email_actions').value === "0")
                {
                document.getElementById('schedule_title').disabled=false;
                document.getElementById('hour').disabled=false;
                document.getElementById('minute').disabled=false;
                document.getElementById('ampm').disabled=false;
                document.getElementById('schedule_time').disabled=false; 
                 }
            else{
                document.getElementById('schedule_title').disabled=true;
                document.getElementById('hour').disabled=true;
                document.getElementById('minute').disabled=true;
                document.getElementById('ampm').disabled=true;
                document.getElementById('schedule_time').disabled=true; 
                 }
        }

        </script>
        <jsp:include page="basejsp.jsp" />
    </head>

    <body>
        <div id="fade" class="black_overlay"></div>
        <div class="row" ng-controller="emailSettings" id="emailSettings">
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
                            <br><br><a href = "javascript:void(0)" ><button type="button" onclick="displaySchedule()" class="button button--moema button--text-thick button--text-upper button--size-s">SCHEDULE</button></a><br><br><br>
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




                <div id="popupschedule" style="display:none;">
                    <div id="content">
                        <!--                                 Mapper file name<input type="text" id="mapperxml" required><br><br>
                                                    Layout file name<input type="text" id="layoutxml" required><br>-->

                        <p class="SH2" style="width:600px;">PLEASE SELECT A TIME FROM YOUR PLAN</p> 
                        <div id="light" class="white_content"><a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
                                        document.getElementById('fade').style.display = 'none';
                                        document.body.style.overflow = 'scroll';" style="text-decoration:none;">
                                <p style="margin-left:740px;margin-top:-35px;cursor: pointer;" id="hidepopup" onclick="hidepopup()" ><img src="images/CloseIcon.svg" height="25" width="25"/></p></a></div>
                        <select class="SH1 selectsocialact" style="font-variant: normal;" name="email_actions" id="email_actions" onchange="validateact()">
                            <option value="0" style="background:#fff;">--SELECT--</option>
                            <option ng-repeat="actions in email_actions" value="{{actions.id}}">{{actions.schedule_title}}</option>
                        </select>
                        <p class="SH2" style="position:relative;top:10px;">OR</p>
                        <p class="SH2" style="position:relative;top:10px;width:700px;">PLEASE CREATE A NEW TITLE AND TIME TO ADD AN ACTION TO YOUR PLAN</p>                       
                        <br>
                        <input type="text" class="simpleinpbox SH2" id="schedule_title" name="schedule_title" placeholder="TITLE" style="font-variant: normal;"><br>
                        <input type="date" class="simpleinpbox selectsocialact" id="schedule_time" name="schedule_time" style="width:200px;">
                        <select name="hour" id="hour" class="selectsocialact" style="position:relative;width:50px;top:-30px;left:205px;">
                            <option value="00">00</option>
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                        </select>
                        <select name="minute" id="minute" class="selectsocialact" style="position:relative;width:50px;top:-30px;left:210px;">
                            <option value="00">00</option>
                            <option value="01">01</option>
                            <option value="02">02</option>
                            <option value="03">03</option>
                            <option value="04">04</option>
                            <option value="05">05</option>
                            <option value="06">06</option>
                            <option value="07">07</option>
                            <option value="08">08</option>
                            <option value="09">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                            <option value="21">21</option>
                            <option value="22">22</option>
                            <option value="23">23</option>
                            <option value="24">24</option>
                            <option value="25">25</option>
                            <option value="26">26</option>
                            <option value="27">27</option>
                            <option value="28">28</option>
                            <option value="29">29</option>
                            <option value="30">30</option>
                            <option value="31">31</option>
                            <option value="32">32</option>
                            <option value="33">33</option>
                            <option value="34">34</option>
                            <option value="35">35</option>
                            <option value="36">36</option>
                            <option value="37">37</option>
                            <option value="38">38</option>
                            <option value="39">39</option>
                            <option value="40">40</option>
                            <option value="41">41</option>
                            <option value="42">42</option>
                            <option value="43">43</option>
                            <option value="44">44</option>
                            <option value="45">45</option>
                            <option value="46">46</option>
                            <option value="47">47</option>
                            <option value="48">48</option>
                            <option value="49">49</option>                            
                            <option value="50">50</option>
                            <option value="51">51</option>
                            <option value="52">52</option>
                            <option value="53">53</option>
                            <option value="54">54</option>
                            <option value="55">55</option>
                            <option value="56">56</option>
                            <option value="57">57</option>
                            <option value="58">58</option>
                            <option value="59">59</option>
                        </select>
                        <select name="AMPM" id="AMPM" class="selectsocialact" style="position:relative;width:70px;top:-30px;left:210px;">
                            <option value="AM">AM</option>
                            <option value="PM">PM</option>
                        </select>
                        <input type="button" ng-click="setScheduling()" id ="schedulethepost" value="SCHEDULE" class="button button--moema button--text-thick button--text-upper button--size-s" style="width:170px;font-family:'proxima-nova',sans-serif;font-size:14px;" />  
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
