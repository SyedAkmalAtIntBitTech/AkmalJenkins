<%-- 
    Document   : socialmediapreview
    Created on : 21 Jul, 2015, 7:05:16 PM
    Author     : sandeep-kumar
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! 
    SqlMethods sql_methods = new SqlMethods();
    String imageName = "";
    String isFacebook = "";
    String isTwitter = "";
    String accesstoken = "";
    String[] twitteracesstoken = {"", ""};

%>
<%
    try {
    sql_methods.session = request.getSession();
     imageName = (String)sql_methods.session.getAttribute("image_file_name");
//    String imageName=request.getParameter("imageName");
        
    isFacebook = request.getParameter("isFacebook");
     isTwitter = request.getParameter("isTwitter");


    if (isFacebook.equalsIgnoreCase("true")) {
        accesstoken = request.getParameter("fbaccessTokenSend");
    }
    if (isTwitter.equalsIgnoreCase("true")) {
        twitteracesstoken = request.getParameter("twaccessTokenSend").split(",");
    }
    }catch (Exception e){
        System.out.println(e.getCause());
        System.out.println(e.getMessage());
    }finally {
        sql_methods.closeConnection();
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
            <jsp:include page="leftmenu.html"/>
            <div class="row">
                <p id="txtpost">POST TO SOCIAL MEDIA</p>
                <div class="col-sm-2 col-sm-offset-1">
                    <p class="psttxt ptpos">What social media would you like to post on?</p>
        
                    <img class="socialimage" id="facebookimage" src="images/fb_icon.png"><span id="facebookcancel" class="glyphicon glyphicon-remove-sign"> </span>
                    <img class="socialimage" id="twitterimage" src="images/twitter.jpeg"><span id="twittercancel" class="glyphicon glyphicon-remove-sign"></span><br><br><br>
                    <div id="fbtextcontainer">
                    Post text<input type="text" class="hideinputborder" id="posttext" placeholder="post text goes here">
                    <br><br>
                    <input type="button" class="btn btn-default" id="chnagetolinkpost" value="CHANGE TO LINK POST"><br><br>
                    <div id="linkpostdiv">
                        <p class="psttxt"> Link Title</p><input class="hideinputborder"  id="title" type="text" placeholder="post text goes here"><br>
                        <p class="psttxt"> Link Description</p><input class="hideinputborder" id="description" type="text" placeholder="post text goes here"><br>
                        <p class="psttxt"> Link URL</p><input class="hideinputborder" type="text" id="url" placeholder="post text goes here" ><br>

                        <input type="button" class="btn btn-default" id="removelink" value="REMOVE LINK"><br><br>
                    </div></div>
                    <input type="button" class="btn btn-primary" id="posttofb" value="POST TO SOCIAL MEDIA">
                </div>

                <div class="col-sm-4 col-sm-offset-1 " id="fabookpreviewdiv">
                    <p id="fbprev"> Facebook Preview</p><br>
                    <img id="companyimage" class="companyimage" src="images/logo.png">
                    <p>Company name</p><br>
                    <img id="facebookpreviewimage" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=<%=imageName%>'>
                </div>

                <div class="col-sm-4" id="twitterpreviewdiv">
                    <p id="fbprev"> Twitter Preview</p><br>
                    <img id="companyimage" class="companyimage" src="images/logo.png">
                    <textarea class="hideinputborder" maxlength="140" id="twittertext" placeholder="Twitter Text goes here until it reaches 140 characters long"></textarea><br><br>
                    <img id="facebookpreviewimage" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=<%=imageName%>'>
                </div>
            </div>
             <input type="hidden" id="imageToPost" name="imageToPost" value='<%=imageName%>'/>  
            <input type="hidden" id="accesstoken" name="accesstoken" value='<%=accesstoken%>'/>
            <input type="hidden" id="twittweraccestoken" name="twittweraccestoken" value='<%=twitteracesstoken[0]%>'>
            <input type="hidden" id="twitterTokenSecret" name="twitterTokenSecret" value='<%=twitteracesstoken[1]%>'>
            <input type="hidden" id="isFacebook" name="isFacebook" value='<%= isFacebook%>'/>
            <input type="hidden" id="isTwitter" name="isTwitter" value='<%= isTwitter%>'/>

        </div>
        <script>
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
                    var image_name= $("#imageToPost").val();

                    if (isFacebook == "true" || isTwitter == "true") {
                        $.ajax({
                            url: 'PostToSocial',
                            method: 'post',
                            data: {
                                imageToPost: image_name,
                                accesstoken: $("#accesstoken").val(),
                                postText: $("#posttext").val(),
                                title: $("#title").val(),
                                description: $("#description").val(),
                                url: $("#url").val(),
                                twittweraccestoken: $("#twittweraccestoken").val(),
                                twitterTokenSecret: $("#twitterTokenSecret").val(),
                                text: $("#twittertext").val(),
                                isFacebook: isFacebook,
                                isTwitter: isTwitter,
                                imagePost: image_name
                            },
                            success: function (responseText) {
//                            $("#tokenHere").html(responseText);
//                                alert(image_name);
                                alert("Your post has been published successfully");
                                 document.location.href = "dashboard.jsp";
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
