<%-- 
    Document   : selectpromotesocialmedia
    Created on : 17 Jul, 2015, 11:58:23 AM
    Author     : sandeep-kumar
--%>

<%@page import="facebook4j.Account" %>
<%@page import="facebook4j.Facebook" %>
<%@page import="facebook4j.FacebookException" %>
<%@page import="facebook4j.FacebookFactory" %>
<%@page import="facebook4j.PostUpdate" %>
<%@page import="facebook4j.ResponseList" %>
<%@page import="facebook4j.auth.AccessToken" %>
<%@page import="facebook4j.Account" %>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/glyphiconiconstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/socialmedia.js" type="text/javascript"></script>
        <title>social media</title>

        <style>
            .socialimage{
                width: 100px;
                height: 100px;
                margin-left:  5px;
            } 
            #popup
            {
                /*                   display:none;*/
                position: fixed;
                width: auto;
                height:300px;
                top: 30%;
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
                overflow: auto;
            }
            #twitterpopup{

                position: fixed;
                width: 450px;
                height:200px;
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
                text-align: center;

            }
            #content
            {
                height:auto;
                width:300px;
                height:500px;
                margin:5px auto;
            }
            #popupclose
            {
                margin:35px 0 0 80px;
                width:50px;

            }
            tr,td{
                margin-left: 2px;
                padding-right: 5px;
            }
        </style>
        <script >
            $(document).ready(function () {
                var myVar1 = '<%= request.getAttribute("objkey")%>';    /* retrieve json from request attribute */
                var mytest = eval('(' + myVar1 + ')');
//                alert(myVar1);      // display complete json

                var removecote = myVar1.replace("[", '').replace(/"/g, '').replace(']', '');
                var pages = removecote.split(",");
                //       alert(pages.length);

                if (myVar1 === "null") {
                    $("#popup").hide();
                }
                else {
//                    alert("contain value");
                    $("#popup").show();

                    for (var i = 0; i < pages.length; i = i + 3) {
                        $("#fbmanagepages").append("<tr  id=page#" + i + "><td>" + pages[i] + "</td><td><input type=hidden id=access" + i + " value=" + pages[i + 1] + "></td><td><img src=" + pages[i + 2] + "></td><td><input id=isDefault type=radio name=isDefault value=" + pages[i + 1] + ">Default</td></tr>");

                    }

                    $("#content").append(" <br><br><center><input id=close type=button class=btn btn-primary value=ok></center>");
                }



                $("tr").click(function () {
                    var id = this.id.split("#");

//                    alert(this.id);
                    var page = $("#" + this.id).text();
                    var accessToken = $("#access" + id[1]).val();
                    $("#pagenameSend").val(page);
                    $("#fbaccessTokenSend").val(accessToken);
                    $("#facebook").prop("checked", true);
                    $("#isFacebook").val("true");

                });

                var managed_page = "";
                var access_token;
                $("#close").click(function () {
                    $("#popup").hide();
                    $("#twitterpopup").hide();
                    $("#submitbutton").prop("disabled",false);
                    managed_page = $("#isDefault").val();
                    
                    if (managed_page != ""){
                        $.ajax({
                                url: 'SetUserFacebookAccessToken',
                                method: 'post',
//                                Type:"JSON",
                                data: {
                                    access_token:$("#isDefault").val()
                                },
                                success: function (responseText) {
    //                            $("#tokenHere").html(responseText);
                                    alert("sucess");
                                }
                            });
                    }
                    
            });
            });
        </script>
    </head>
<body>
    <div class="row">
        <div id="sidebar-wrapper" class="col-md-1">
            <nav class="navbar navbar-default " role="navigation">
                <img src="images/logo.png"  alt="logo" class="img-responsive logo" width="50" ><br>
            </nav>
        </div><!--/end left column-->

        <div class="col-md-10 col-md-offset-1">
            <p id="text3">Great!<br> 
                Which social media platform(s)<br>
                would you like to post it on?</p> 
            <ul id="promotebuttonlist">
                <li><img class="socialimage" src="images/fb_icon.png" /> </a><input type="checkbox" id="facebook" name="social" value="facebook"> </li>
                <li><img class="socialimage" src="images/twitter.jpeg" > <input type="checkbox" id="twitter" name="social" value="twitter"><br> </li>
                <li><div class="col-md-4 col-md-offset-6">
                        <form action="<%=request.getContextPath()%>/socialmediapreview.jsp" method="POST">
                            <input type="hidden" id="twaccessTokenSend" name="twaccessTokenSend">
                            <input type="text" id="fbaccessTokenSend" name="fbaccessTokenSend">
                            <input type="hidden" id="isFacebook" name="isFacebook" value="false">
                            <input type="hidden" id="isTwitter" name="isTwitter" value="false">
                            <input type="submit"  id="submitbutton" class="btn btn-primary" value="Continue" disabled>
                        </form> 
                    </div>
                </li>
            </ul>  
        </div>

        <div id="popup">
            <div id="content">
                <center>
                    <table id="fbmanagepages">
                    </table>
                </center>
            </div>   
        </div>
        <div id="twitterpopup" style="display: none">
            <div id="content">
                <p>Please click the url and get the pin</p>
                <div id="twitterlink">wait...</div>
                Enter the pin<input type="text" id="pinTextBox" name="pinTextBox"><br><br><br>
                <input id="setPin" type="button" class="btn btn-primary" value="ok">
            </div>   
        </div>        
    </div>
</body>
</html>
