<%-- 
    Document   : socialmediapreview
    Created on : 21 Jul, 2015, 7:05:16 PM
    Author     : sandeep-kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String isFacebook = request.getParameter("isFacebook");
    String isTwitter = request.getParameter("isTwitter");
    String accesstoken = "";
    String[] twitteracesstoken = {"", ""};
    if (isFacebook.equalsIgnoreCase("true")) {
        accesstoken = request.getParameter("fbaccessTokenSend");
    }
    if (isTwitter.equalsIgnoreCase("true")) {
        twitteracesstoken = request.getParameter("twaccessTokenSend").split(",");
    }


%>
<!DOCTYPE html>
<html>
    <head>
        <title>social media preview</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/glyphiconiconstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <!--        <script src="js/socialmedia.js" type="text/javascript"></script>-->
        <style>
            .socialimage{
                width: 30px;
                height: 30px;
                margin-left:  5px;
            } 
            #maincontainer li{
                display: inline-table;
                position: relative;
                left: 15px;
                top: 100px;


            }
            .hideinputborder{
                background-color:transparent;
                border: 0px solid;
                height:30px;
                width:260px;
            }
            .hideinputborder:focus{
                outline: none;
            }

            #chnagetolinkpost,#removelink,#posttofb{
                border-radius: 15px;
            }
            .facebookpreview{
                /*             top: -100px;*/
            }

            #linkpostdiv{
                display: none;
            }
            #facebookpreviewimage{
                width: 300px;
                height: 200px;
            }
            .row{
                margin-top: 100px;
                margin-left: 50px;
            }
            .companyimage{
                width: 40px;
                height: 40px;
            }
            #twittertext{
                width: 250px;
                height: 50px;
            }
        </style>
        
    </head>

    <body>
        <div class="container-fluid">
            <div id="sidebar-wrapper" class="col-md-1">
                <nav class="navbar navbar-default " role="navigation">
                    <img src="images/logo.png"  alt="logo" class="img-responsive logo" width="50" ><br>
                </nav>
            </div>
            <div class="row">
                <div class="col-sm-3 col-sm-offset-1">
                   
                    What social media would you like to promote?<br><br>
                    <img class="socialimage" id="facebookimage" src="images/fb_icon.png"><span id="facebookcancel" class="glyphicon glyphicon-remove-sign"> </span>
                    <img class="socialimage" id="twitterimage" src="images/twitter.jpeg"><span id="twittercancel" class="glyphicon glyphicon-remove-sign"></span><br><br><br>
                    <div id="fbtextcontainer">
                    post test<input type="text" class="hideinputborder" id="posttext" placeholder="post text goes here">
                    <br><br>
                    <input type="button" class="btn btn-default" id="chnagetolinkpost" value="CHANGE TO LINK POST"><br><br>
                    <div id="linkpostdiv">
                        Link Title<input class="hideinputborder"  id="title" type="text" placeholder="post text goes here"><br>
                        Link Description<input class="hideinputborder" id="description" type="text" placeholder="post text goes here"><br>
                        Link URL<input class="hideinputborder" type="text" id="url" placeholder="post text goes here" ><br>

                        <input type="button" class="btn btn-default" id="removelink" value="REMOVE LINK"><br><br>
                    </div></div>
                    <input type="button" class="btn btn-primary" id="posttofb" value="POST TO SOCIAL MEDIA">
                </div>

                <div class="col-sm-4" id="fabookpreviewdiv">
                    Facebook Preview<br>
                    <img id="companyimage" class="companyimage" src="images/logo.png">
                    <p>Company name</p><br>
                    <img id="facebookpreviewimage" src="images/Blackandwhite.jpg">
                </div>

                <div class="col-sm-3" id="twitterpreviewdiv">
                    Twitter Preview<br>
                    <img id="companyimage" class="companyimage" src="images/logo.png">
                    <textarea class="hideinputborder" maxlength="140" id="twittertext" placeholder="Twitter Text goes here until it reaches 140 characters long"></textarea><br><br>
                    <img id="facebookpreviewimage" src="images/Blackandwhite.jpg">
                </div>
            </div>

            <input type="hidden" id="accesstoken" name="accesstoken" value=<%=accesstoken%>>
            <input type="hidden" id="twittweraccestoken" name="twittweraccestoken" value=<%=twitteracesstoken[0]%>>
            <input type="hidden" id="twitterTokenSecret" name="twitterTokenSecret" value=<%=twitteracesstoken[1]%>>
            <input type="hidden" id="isFacebook" name="isFacebook" value=<%= isFacebook%>>
            <input type="hidden" id="isTwitter" name="isTwitter" value=<%= isTwitter%>>

        </div>
        <script>

            $(document).ready(function () {
                var isFacebook = $("#isFacebook").val();

                var isTwitter = $("#isTwitter").val();

                if (isFacebook == "false") {
                    $("#facebookimage").hide();
                    $("#fabookpreviewdiv").hide();
                    $("#facebookcancel").hide();
                    $("#fbtextcontainer").hide();


                }
                else {
                    $("#facebookimage").show();
                    $("#fabookpreviewdiv").show();
                    $("#fbtextcontainer").show();
                }

                if (isTwitter == "false") {
                    $("#twitterimage").hide();
                    $("#twitterpreviewdiv").hide();
                    $("#twittercancel").hide();

                }
                else {
                    $("#twitterimage").show();
                    $("#twitterpreviewdiv").show();

                }

                $("#chnagetolinkpost").click(function () {

                    $("#linkpostdiv").show();
                });

                $("#removelink").click(function () {
                    $("#title").val("");
                    $("#description").val("");
                    $("#url").val("");
                    $("#linkpostdiv").hide();
                });

                $("#twittercancel").click(function () {
                    $("#twittercancel").hide();
                    $("#twitterimage").hide();
                    $("#twitterpreviewdiv").hide();
                    $("#isTwitter").val("false");

                });

                $("#facebookcancel").click(function () {
                    $("#facebookcancel").hide();
                    $("#facebookimage").hide();
                    $("#fabookpreviewdiv").hide();
                    $("#isFacebook").val("false");
                    $("#fbtextcontainer").hide();
                });

                $("#posttofb").click(function () {

                    var isFacebook = $("#isFacebook").val();
                    var isTwitter = $("#isTwitter").val();
                    if (isFacebook == "true" || isTwitter == "true") {
                        $.ajax({
                            url: 'PostToSocial',
                            method: 'post',
                            data: {
                                accesstoken: $("#accesstoken").val(),
                                postText: $("#posttext").val(),
                                title: $("#title").val(),
                                description: $("#description").val(),
                                url: $("#url").val(),
                                twittweraccestoken: $("#twittweraccestoken").val(),
                                twitterTokenSecret: $("#twitterTokenSecret").val(),
                                text: $("#twittertext").val(),
                                isFacebook: isFacebook,
                                isTwitter: isTwitter
                            },
                            success: function (responseText) {
//                            $("#tokenHere").html(responseText);
                                alert("sucess");
                            }
                        });
                    }

                    else {
                        alert("select atleast one");
                    }

                });
                
                

            });

        </script>    
    </body> 
</html>
