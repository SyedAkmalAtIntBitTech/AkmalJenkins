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
<!--        <script src="js/socialeditor.js" type="text/javascript"></script>-->
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
        <%! 
            HttpServletRequest request;
            Object code = "";
            String ImageName="";
        %>
        <% 
            try{
                code = (Object)request.getAttribute("objkey");
                ImageName=request.getParameter("image");
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                
            }
        
        %>
        
           <script>
            $(document).ready(function () {
                     $(".cross").hide();
                            $(".menu").hide();
                            $(".hamburger").click(function () {
                                 $(".menu").slideToggle("slow", function () {
                                     $(".hamburger").hide();
                                             $(".cross").show();
                                 });
                             });
                            $(".cross").click(function () {
                                $(".menu").slideToggle("slow", function () {
                                $(".cross").hide();
                                        $(".hamburger").show();
                                });
                            });
                        });
        </script>
        
        
        <script>
            
            $(document).ready(function () {
                var myVar1 = '<%= code %>';    /* retrieve json from request attribute */
                var mytest = eval('(' + myVar1 + ')');
//                alert(JSON.stringify(myVar1));      // display complete json

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
                        $("#fbmanagepages").append("<tr  id=page#" + i + "><td>" + pages[i] + "</td><td><input type=hidden id=access" + i + " value=" + pages[i + 1] + "></td><td><img src=" + pages[i + 2] + "></td></tr>");
                    }

                    $("#content").append(" <br><center><input id=isdefault name=isdefault type=checkbox class=btn btn-primary value=default>Default</input></center>");
                    $("#content").append(" <br><center><input id=facebookok name=facebookok type=button class=btn btn-primary value=ok>&nbsp;&nbsp;<input id=close name=close type=button class=btn btn-primary value=cancel></center>");
                }
 
                var managed_page = "";
                var default_access_token;
                var check_default = "false";
                var check_default_managed_page;
                $("tr").click(function () {
                    var id = this.id.split("#");

//                    alert(this.id);
                    var page = $("#" + this.id).text();
                    var accessToken = $("#access" + id[1]).val();
                    $("#pagenameSend").val(page);
                    $("#fbaccessTokenSend").val(accessToken);
                    $("#fbdefaultAccessToken").val("true");
                    check_default = $("#fbdefaultAccessToken").val();
                    $("#facebook").prop("checked", true);
                    $("#isFacebook").val("true");

                });

                $("#isdefault").click(function () {
//                    managed_page = $("#isDefault").val();
//                    alert(check_default);
                    if (check_default == "true"){
                        default_access_token = $("#fbaccessTokenSend").val();
                    }
                    
//                    if (check_default == "true"){
//                        $.ajax({
//                                url: 'SetUserFacebookAccessToken',
//                                method: 'post',
//                                type:"JSON",
//                                data: {
//                                    method: "setAccessToken",
//                                    access_token:default_access_token
//                                },
//                                success: function (responseText) {
//    //                            $("#tokenHere").html(responseText);
//                                    alert("sucess");
//                                }
//                            });
//                    }
                    });

                $("#facebookok").click(function () {
//                    managed_page = $("#isDefault").val();
                    check_default_managed_page = document.getElementById("isdefault").checked;
                    
                    if ((check_default_managed_page == true) && (check_default == "true")){
//                       alert(default_access_token);
                        $.ajax({
                                url: 'ServletUserPreferencesFacebook',
                                method: 'post',
//                                type:"JSON",
                                data: {
                                   access_token_method: "setAccessToken",
                                   access_token:default_access_token
                                },
                                success: function (responseText) {
    //                            $("#tokenHere").html(responseText);
                                    alert("sucess");$("#popup").hide(); 
                                     $("#submitbutton").prop("disabled",false);
                                }
                            });
                    }else if((check_default_managed_page == false) && (check_default == "true")) { 
                        $("#popup").hide(); 
                        $("#submitbutton").prop("disabled",false);
                    }else {
                        alert("No default page selected");
                    }   
            });
            
            $("#close").click(function(){
                    $("#fbaccessTokenSend").val("") ;
                    $("#fbdefaultAccessToken").val("");
                    $("#isFacebook").val("false");
                    $("#facebook").prop("checked",false);
                    $("#popup").hide();
                    $("#twitterpopup").hide();
                    $("#submitbutton").prop("disabled",true);
            });   
           });
           
        </script>
     
    </head>
<body>
    <div class="row">
        <div id="sidebar-wrapper" class="col-md-1">
                    <nav class="navbar navbar-default " role="navigation">
                        <img src="images/logo.png"  alt="logo" class="img-responsive logo" width="50" ><br>
                        <button class="hamburger">&#9776;</button>
                        <button class="cross">&#9776;</button>
                        <ul class="nav nav-stacked menu">
                            <li><a href="dashboard.jsp"><span class="glyphicon glyphicon-home"></span></a><p id="text1">HOME</p></li>
                            <li><a href="emaillists.jsp"><span class="glyphicon glyphicon-envelope"></span></a><p id="text1">EMAIL</p></li>
                            <li><a href="social.html"><span class="glyphicon glyphicon-comment"></span></a><p id="text1">SOCIAL</p></li>
                            <li><a href="imagegallery.jsp"><span class="glyphicon glyphicon-picture"></span></a><p id="text1">IMAGE GALLERY</p></li>   
                            <li><a href="setting.html"><span class="glyphicon glyphicon-cog"></span></a><br></li> 
                            <li><br><a href="signout.jsp"><p id="text2">LOG OUT</p></a><br><br></li> 
                        </ul>
                        <!-- /.navbar-collapse -->
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
                            <input type="hidden" id="imageName" name="imageName" value ='<%=ImageName%>'/>
                            <input type="hidden" id="twaccessTokenSend" name="twaccessTokenSend" >
                            <input type="hidden" id="fbaccessTokenSend" name="fbaccessTokenSend">
                            <input type="hidden" id="fbdefaultAccessToken" name="fbdefaultAccessToken">
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
<!--                <input id="closetwitter" type="button" class="btn btn-primary" value="cancel">-->
            </div>   
        </div>        
    </div>
</body>
</html>
