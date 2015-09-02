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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <script type="text/javascript" src="js/angular.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <script src="js/leftmenuhamburger.js" type="text/javascript"></script>
        <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
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

    <%!
        String emailSubject = "";
        String emailList = "";
        String htmlData = "";
        SqlMethods sqlmethods = new SqlMethods();
    %>
    <%
        sqlmethods.session = request.getSession(true);

        emailSubject = (String) sqlmethods.session.getAttribute("email_subject");
        emailList = (String) sqlmethods.session.getAttribute("email_list");
        htmlData = (String) sqlmethods.session.getAttribute("htmldata");
    %>
    <script>
        function emailSettings($scope, $http){
            
            $scope.getEmailSettings = function(){
                
                var email_settings = {"type": "get"};
                
                $http({
                        method : 'POST',
                        url : 'EmailSettingsServlet',
                        headers: {'Content-Type': 'application/json'},
                        data: email_settings
                }).success(function(data, status, headers, config) {
                    $scope.email_settings = data;
                    if (data === error){
                        alert(data);
                    }
                }).error(function(data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                });        
                
            };
            
            $scope.setScheduling = function(){
                    
                var from_name = $("#name").val();
                var email_subject = $("#subject").val();
                var to_email_addresses = $("#toaddress").val();
                var from_email_address = $("#formaddress").val();
                var reply_to_email_address = $("#email").val();
                var htmldata = $("htmldata").val();
                var email_list = $("email_list").val();

                
                var email_scheduling = {}
                $http({
                        method : 'POST',
                        url : 'ScheduleEmail',
                        headers : {'Content-Type':'application/json'},
                        data: email_scheduling
                }).success(function(data){
                    
                }).error(function(data){
                    alert("No data available, problem fetching the data");
                });
            };
        }
        
    </script>
    
    <script>
        $(document).ready(function () {
            $.ajax({
                url: getHost() + "PreviewServlet",
                method: "post",
                data: {htmlString: $(".content").html()},
                success: function (responseText) {

                    //show popup showing
                    $(".content").empty();
                    $(".content").append(responseText);

                }
            });
        });
        function show(id) {
            var imageUrl = $("#" + id).css("background-image");

            if (id === "ipad") {
                $(".preview").css("width", "850px").css("height", "850px").css("overflow", "none").css("overflow", "hidden");
                $(".iphoneshow").css("background-image", imageUrl).css("display", 'block').css("width", "239px").css("height", "300px").css("overflow", "hidden")
                        .css("border-color", "transparent").css("background-color", "transparent").css("margin-left", "-55px").css("margin-top", "-80px");
                $(".content").css("margin-left", "-54px").css("margin-top", "-150px").css("zoom", "29.8%")
                        .css("width", "400px");
            }
            else if (id === "imac")
            {
                $(".preview").css("width", "550px").css("height", "950px").css("overflow", "hidden").css("align", "center");
                $(".iphoneshow").css("background-image", imageUrl)
                        .css("display", 'block').css("height", "413px").css("width", "295px").css("margin-left", "-55px").css("margin-top", "-80px")
                        .css("overflow-x", "hidden").css("border-color", "transparent").css("background-color", "transparent");
                $(".content").css("margin-left", "-33px").css("margin-top", "-132px").css("width", "440px").css("height", "920px").css("overflow", "none").css("zoom", "37%");

            }
            else {
                $(".preview").css("width", "550px").css("height", "950px").css("overflow", "hidden").css("align", "center");
                $(".iphoneshow").css("background-image", imageUrl)
                        .css("display", 'block').css("height", "370px").css("width", "195px").css("margin-left", "-55px").css("margin-top", "-80px")
                        .css("overflow-x", "hidden").css("border-color", "transparent").css("background-color", "transparent");
                $(".content").css("margin-left", "-97px").css("margin-top", "-122px").css("width", "400px").css("height", "920px").css("overflow", "none").css("zoom", "22%");

            }
        }

        function sendEmail() {
            alert("clicked");
            $.ajax({
                url: getHost() + "SendEmailServlet",
                method: "post",
                data: {
                    from_name: $("#name").val(),
                    email_subject: $("#subject").val(),
                    email_addresses: $("#toaddress").val(),
                    from_email_address: $("#formaddress").val(),
                    reply_to_email_address: $("#email").val(),
                    htmldata:$("htmldata").val() ,
                    email_list:$("email_list").val()
                },
                success: function (responseText) {

                    document.location.href = "emailsent.jsp";
                }

            });

        }

    </script>
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
                            <label>NAME</label><br>
                        </div>
                    </div>
                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">                            
                            <input id="subject" class="form-control simplebox" name="email_subject" type="text" value='<%= emailSubject %>'>
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
                            <input id="toaddress" class="form-control simplebox" name="email_addresses" type="text" value='<%=emailList %>'>
                            <label>TO ADDRESS</label><br>
                        </div>
                    </div>

                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <input id="email" class="form-control simplebox" name="reply_to_email_address" type="text" required value="{{email_settings.reply_email_address}}">
                            <label>EMAIL</label><br><br>
                        </div>
                    </div>
                    <div  class="form-group">
                        <div class="col-md-3 col-md-offset-2">
                            <br><br><button type="button" onclick="sendEmail()" class="button button--moema button--text-thick button--text-upper button--size-s">SEND</button><br><br><br>
                        </div>
                        <div class="col-md-1 col-md-offset-1">
                            <br><br> <button type="button"  class="button button--moema button--text-thick button--text-upper button--size-s" >SCHEDULE</button><br><br><br>
                        </div>
                    </div>
                            <input type="hidden" id="htmldata" value='<%=htmlData %>'> 
                            <input type="hidden" id="email_list" value='<%=emailList%>'>
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


                <div class="iphoneshow img-responsive"   id="popup" style="background-repeat: no-repeat; -webkit-background-size: contain; display: none;">
                    <div class="content">  
                        <%=htmlData %>
                    </div>
                </div>






            </div>
        </div>

    </body>
</html>
