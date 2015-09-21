<%-- 
    Document   : socialmediapreview
    Created on : 21 Jul, 2015, 7:05:16 PM
    Author     : sandeep-kumar
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="checksession.jsp" %>

<%! 
    SqlMethods sql_methods = new SqlMethods();
    String imageName = "";
    String logoImageName=null;
    String companyName="";
    String isFacebook = "";
    String isTwitter = "";
    String accesstoken = "";
    String[] twitteracesstoken = {"",""};

%>
<%
    try {
    sql_methods.session = request.getSession();
     imageName = (String)sql_methods.session.getAttribute("image_file_name");
     user_id = (Integer)sql_methods.session.getAttribute("UID");
     logoImageName =(String)sql_methods.session.getAttribute("ImageFileName");
     companyName =(String)sql_methods.session.getAttribute("company");
        
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
         <%@ include file="fonttypekit.jsp"%>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/glyphiconiconstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js" type="text/javascript"></script>
        <!--        <script src="js/socialmedia.js" type="text/javascript"></script>-->
        <style>
            #mask {
  position: absolute;
  left: 0;
  top: 0;
  z-index: 9000;
  background-color: #000;
  display: none;
}

#boxes .window {
  position: absolute;
  left: 0;
  top: 0;
  width: 440px;
  height: 200px;
  display: none;
  z-index: 9999;
  padding: 20px;
  border-radius: 15px;
  text-align: center;
}

#boxes #dialog {
  width: 750px;
  height: 300px;
  padding: 10px;
  background-color: #000;
  font-family: 'Segoe UI Light', sans-serif;
  font-size: 15pt;
}

#popupfoot {
  font-size: 16pt;
  position: absolute;
  bottom: 0px;
  width: 250px;
  left: 250px;
}
            
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
                position:relative;
                
            }
            .hideinputborder:focus{
                outline: none;
            }

            #chnagetolinkpost,#removelink,#posttofb{
                border-radius: 15px;
            }
            #socialschedule{
                border-radius: 15px;
                margin-top: 20px;
            }
            .facebookpreview{
                /*             top: -100px;*/
            }
            #popupschedule
            {
                display:none;
                position: fixed;
                width:350px;
                height:400px;
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
            #popupclose
            {
                margin:35px 0 0 80px;
                width:50px;

            }

            #linkpostdiv{
                display: none;
            }
            #facebookpreviewimage{
                width: 325px;
                height: 171px;
            }
            #twitterpreviewimage{
                width: 334px;
                height: 168px;
                position:relative;
                left:50px;
            }
            .row{
                margin-top: 41px;
                margin-left: 97px;
            }
            .companyimage{
                width: 40px;
                height: 40px;
            }
            #twittertext{
                width: 330px;
                height: 50px;
            }
            .fbfooter ul{
              
                
            }
            .fbfooter li{
                display: inline-table;
                font-size: 8px;
            }
            .fbText{
                font-size: 10px;
                margin-top: 5px;
            }
            .linkPostStyle{
                outline: 2px #cccccc solid;
                width: 325px;
                height: 255px;
                
            }
            .fbtable td{
              padding: 5px;  
            }
            .likeComment td{
                padding: 5px;  
                font-size: 10px;
                font-weight: bold;
            }
            .fbuserDestable  tr:nth-child(1) td {
    padding-top: 0;
}
          .link_title{
                font-family:AGaramondPro-Regular;
                font-size:  13.8px;
                color: #676767;
                font-style: normal;
                text-align: left;
                line-height: 16.6px;
                letter-spacing: 0em;
                opacity: 1;
            }
            .link_description{
                font-family:LucidaGrande;
                font-size:  9px;
                color: #676767;
                font-style: normal;
                text-align: left;
                line-height: 10.8px;
                letter-spacing: 0em;
                opacity: 1;
            }
        </style>

        <script>
            function displaySchedule(){
                $("#popupschedule").show();
            }
            function hidepopup(){
                $("#popupschedule").hide();
                $("#schedule_title").val("");
                $("#schedule_time").val("");
            }

            
        </script>
       <jsp:include page="basejsp.jsp" />
    </head>

    <body>
        <div class="container-fluid">
            <jsp:include page="leftmenu.html"/>
            <div class="col-md-10 col-md-offset-0 row">
                <p id="txtpost" class="MH1">POST TO SOCIAL MEDIA</p><div style="position:relative;left:8em;" class="col-md-0 col-md-offset-10"><a href="selectpromotesocialmedia.jsp"><p id="edit" class="BT2">go back</p></a></div>
                <div class="col-sm-3 col-sm-offset-1">
                    <p class="psttxt ptpos">What social media would you like to post on?</p>
        
                    <img class="socialimage" id="facebookimage" src="images/fb_icon.png"><span id="facebookcancel" class="glyphicon glyphicon-remove-sign"> </span>
                    <img class="socialimage" id="twitterimage" src="images/twitter.jpeg"><span id="twittercancel" class="glyphicon glyphicon-remove-sign"></span><br><br><br>
                    <div id="fbtextcontainer">
<!--                        <div class="forfb">
                    Post text<input type="text" class="hideinputborder" id="posttext" placeholder="post text goes here">
                    </div>
                    <br><br>-->
                    <input type="button" class="btn btn-default" id="chnagetolinkpost" value="CHANGE TO LINK POST"><br><br>
                    <div id="linkpostdiv">
                        <div class="forfb">
                        <p class="psttxt"> Link Title</p><input class="hideinputborder"  id="title" type="text" placeholder="post text goes here"><br>
                        <p class="psttxt"> Link Description</p><input class="hideinputborder" id="description" type="text" placeholder="post text goes here"><br>
                        </div>
                        <p class="psttxt"> Link URL</p><input class="hideinputborder" type="text" id="url" placeholder="post text goes here" ><br>

                        <input type="button" class="btn btn-default" id="removelink" value="REMOVE LINK"><br><br>
                    </div>
                    </div>
                    <input type="button" class="btn btn-primary" id="posttofb" value="POST TO SOCIAL MEDIA">
                    <br>
                    <input type="button" class="btn btn-primary" id="socialschedule" value="SCHEDULE" onclick="displaySchedule()">
                    
                </div>

                <div class="col-sm-3 col-sm-offset-0 " id="fabookpreviewdiv">
                     <p id="fbprev"> Facebook Preview</p><br>
                       <div id="previewchield" style="height:400px;width:350px;padding:7px; border-color:red;border:2px #cccccc solid;border-radius:10px;">
                           <p style="font-weight:600;font-size:10px;bottom:-10px;position:relative;"> Everyone liked this.</p>
                         <hr style="height:0.5px;background-color:#000;">
                         <table class="fbtable">
                             <tr>
                                 <td><img id="companyimage" class="companyimage" src="/BrndBot/DownloadImage?image_type=USER_LOGO&user_id=<%= user_id%>&image_name=<%= logoImageName%>"></td>
                                 <td><p><%=companyName%></p></td>
                             </tr>
                         </table>
                             <textarea type="text" class="hideinputborder" id="posttext" placeholder="post text goes here" style="resize:none;width:325px;"></textarea><br><br> 
                       <div id="fblinkpostDiv">
                       <img id="facebookpreviewimage" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=<%=imageName%>'>
                       <table class="fbuserDestable"> 
                           <tr><td><input id="link_title" type="text" class="link_title hideinputborder" placeholder="Link Title"><td></tr>
                           <tr><td><input id="link_description" type="text" class="link_description hideinputborder" placeholder="Link Description"></td></tr>
                           <tr><td><input class="hideinputborder" type="text" id="Linkurl" placeholder="Link URL"></td></tr> 
                      
                       </table>
                       </div>
                       <p class="fbText">100 Likes 8 Comments 9 Shares</p>
                       <table class="likeComment">
                           <tr>
                           <td>Like</td>
                           <td>Comment</td>
                           <td>Share</td>
                          </tr>
                       </table>
                       </div>
                </div>

                <div class="col-sm-3 col-sm-offset-2" id="twitterpreviewdiv">
                    <p id="fbprev"> Twitter Preview</p><br>
                    <div style="height:320px;width:420px;padding:7px; border-color:red;border:2px #cccccc solid;border-radius:10px;">
                    <table class="fbtable">
                             <tr>
                                 <td><img id="companyimage" class="companyimage" src="/BrndBot/DownloadImage?image_type=USER_LOGO&user_id=<%= user_id%>&image_name=<%= logoImageName%>"></td>
                                 <td><p><%=companyName%></p></td>
                             </tr>
                         </table>
                    <textarea class="hideinputborder" maxlength="140" id="twittertext" placeholder="Twitter Text goes here until it reaches 140 characters long"  style="left:50px;resize: none"></textarea>
                    <img id="twitterpreviewimage" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=<%=imageName%>'>
                    </div>
                </div>
            </div>
            <input type="hidden" id="imageToPost" name="imageToPost" value='<%=imageName%>'/>  
            <input type="hidden" id="accesstoken" name="accesstoken" value='<%=accesstoken%>'/>
            <input type="hidden" id="twittweraccestoken" name="twittweraccestoken" value='<%=twitteracesstoken[0]%>'>
            <input type="hidden" id="twitterTokenSecret" name="twitterTokenSecret" value='<%=twitteracesstoken[1]%>'>
            <input type="hidden" id="isFacebook" name="isFacebook" value='<%= isFacebook%>'/>
            <input type="hidden" id="isTwitter" name="isTwitter" value='<%= isTwitter%>'/>
            <div id="popupschedule" style="display:none;">
                    <div id="content">
<!--                                 Mapper file name<input type="text" id="mapperxml" required><br><br>
                            Layout file name<input type="text" id="layoutxml" required><br>-->
                        Title: <input type="text" class="form-control simplebox" id="schedule_title" name="schedule_title"><br>
                        Description: <textarea class="form-control simplebox1" name="schedule_desc" id="schedule_desc"></textarea><br>
                        Date : <input type="datetime-local" class="form-control simplebox" id="schedule_time" name="schedule_time"><br>

                        <input type="hidden" name="socialmedia" id="socialmedia" value="socialmedia"/>
                        <input type="button" id ="schedulethepost" value="Done"/>   
                        <input type="button" id="hidepopup" value="Close" onclick="hidepopup()"/>   
                    </div>
            </div>
            <input type="hidden" id="sortLengthurl"/>
                                        <div id="boxes">
                                            <div id="dialog" class="window"><br><br>
                                              <img id="loadingGif" src="images/YogaLoadingGif.gif" />
                                            </div>
                                            <div id="mask"></div>
                                        </div>
        </div>
            
        <script>
                $(".cross").hide();
                $(".menu").hide();
                $("#Linkurl").hide();
                $(".link_title").hide();
                $(".link_description").hide();
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
                
                 $("#title").keyup(function (){
                    $(".link_title").val($("#title").val());
                    });
                 $("#description").keyup(function (){
                    $(".link_description").val($("#description").val()); 
                });
                
                $("#url").keyup(function (){
                    var link=$("#url").val();
                    var twittertext=$("#twittertext").val();
                    var f=link.startsWith("http");
                    if(!f)
                    {
                       link="http://"+ $("#url").val();
                    }
                    if(twittertext.endsWith("bit.ly/1XOkJo"))
                    {
                    $("#twittertext").val($("#twittertext").val());
                    }
                    else{
                        $("#twittertext").val($("#twittertext").val()+ "  bit.ly/1XOkJo");
                    }
                    
//                    $("#twittertext").val($("#twittertext").val()+" "+bit_url+"");
                    $("#Linkurl").val(link);
                    $("#url").val(link);
                    
                    
                });
                

                if (isFacebook === "false") {
                    $("#facebookimage").hide();
                    $("#fabookpreviewdiv").hide();
                    $("#facebookcancel").hide();
                    $(".forfb").hide();
                }
                else {
                    $("#facebookimage").show();
                    $("#fabookpreviewdiv").show();
                    $(".forfb").show();
                }

                if (isTwitter === "false") {
                    $("#twitterimage").hide();
                    $("#twitterpreviewdiv").hide();
                    $("#twittercancel").hide();
                }
                else {
                    $("#twitterimage").show();
                    $("#twitterpreviewdiv").show();
                    $("#fbtextcontainer").show();
                }

                    $("#chnagetolinkpost").click(function () {
                        $("#twittertext").attr("placeholder", "Twitter Text goes here until it reaches 127 characters long");
                        $("#twittertext").attr("maxlength", "127");
                        $("#linkpostdiv").show();
                        $("#fblinkpostDiv").addClass("linkPostStyle");
                        $("#previewchield").css("height","480px");
                        $("#Linkurl").show();
                        $(".link_title").show();
                        $(".link_description").show();
                });

                $("#removelink").click(function () {
                    $("#twittertext").attr("placeholder", "Twitter Text goes here until it reaches 140 characters long");
                    $("#twittertext").attr("maxlength", "140");
                    $("#title").val("");
                    $("#description").val("");
                    $("#url").val("");
                    $("#linkpostdiv").hide();
                    $("#previewchield").css("height","400px");
                    $("#Linkurl").hide();
                    $(".link_title").hide();
                    $(".link_description").hide();
                    $("#Linkurl").val("");
                    $(".link_title").val("");
                    $(".link_description").val("");
                     $("#fblinkpostDiv").removeClass("linkPostStyle");
                     $("#twittertext").val("");
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
                    $(".forfb").hide();
                });

                $("#posttofb").click(function () {
                        fadepopup();                  
                        var isFacebook = $("#isFacebook").val();
                        var isTwitter = $("#isTwitter").val();
                        var image_name= $("#imageToPost").val();
                    var link=$("#url").val();
                    var f=link.startsWith("http");
                    if(!f)
                    {
                       link="http://"+ $("#url").val();
                    }
                    var url=link;
                    var username="sandeep264328"; // bit.ly username
                    var key="R_63e2f83120b743bc9d9534b841d41be6";
                    $.ajax({
                    url:"http://api.bit.ly/v3/shorten",
                    data:{longUrl:url,apiKey:key,login:username},
                    dataType:"jsonp",
                    success:function(v)
                    {
                        var bit_url=v.data.url;
                        $("#sortLengthurl").val(bit_url);
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
                                    imagePost: image_name,
                                    shorturl:$("#sortLengthurl").val()
                                },
                                success: function (responseText) {
                                    $('#mask').hide();
                                    $('.window').hide();
                                    alert("Your post has been published successfully");
                                    
                                     document.location.href = "dashboard.jsp";
                                }
                            });
                        }

                        else {
                            alert("select atleast one");
                        }

                    }
                    });
                    
                });
                
                $("#schedulethepost").click(function () {

                    var isFacebook = $("#isFacebook").val();
                    var isTwitter = $("#isTwitter").val();
                    var image_name= $("#imageToPost").val();
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

                    var social_schedule = "";
                    if (isFacebook == "true" || isTwitter == "true"){
                        
                     social_schedule =  [
                                          {
                                            type: "facebook",
                                            image_name: image_name,
                                            schedule_time: newEpoch,
                                            schedule_title: '"'+schedule_title+'"',
                                            schedule_desc: schedule_desc,
                                            token_data: {
                                              "access_token": '"'+$("#accesstoken").val()+'"'
                                            },
                                            metadata: {
                                              description: '"'+$("#description").val()+'"',
                                              post_text: '"'+$("#posttext").val()+'"',
                                              url: '"'+$("#url").val()+'"'
                                            }
                                          },
                                          {
                                            type: "twitter",
                                            image_name: image_name,
                                            schedule_time: newEpoch,
                                            schedule_title: '"'+schedule_title+'"',
                                            schedule_desc: schedule_desc,
                                            token_data: {
                                              "access_token": '"'+$("#twittweraccestoken").val()+'"',
                                              "token_secret": '"'+$("#twitterTokenSecret").val()+'"'
                                            },
                                            metadata: {
                                              text: '"'+$("#twittertext").val()+'"'
                                            }
                                          }
                                        ];
//                        social_schedule = {
//                                        "token_data": {
//                                          "access_token": $("#accesstoken").val(),
//                                          "twittweraccestoken": $("#twittweraccestoken").val(),
//                                          "twitterTokenSecret": $("#twitterTokenSecret").val()
//                                        },
//                                        "image_name": image_name,
//                                        "metadata": {
//                                            "text": $("#twittertext").val(),
//                                            "postText": $("#posttext").val(),
//                                            "title": $("#title").val(),
//                                            "description": $("#description").val(),
//                                            "url": $("#url").val()
//                                          
//                                        },
//                                        "isFacebook": isFacebook,
//                                        "isTwitter": isTwitter,
//                                        "schedule_time": newEpoch,
//                                        "schedule_title": schedule_title
//                                      }
                        console.log(JSON.stringify(social_schedule));
                        $.ajax({
                            url:  getHost() + 'ScheduleSocialPost',
                            method: 'post',
                            dataType: 'json',
                            contentType: 'application/json',
                            mimeType: 'application/json',
                            data: JSON.stringify(social_schedule),
                            success: function (responseText) {
//                            $("#tokenHere").html(responseText);
//                                alert(image_name);
                                alert("Your post has been published successfully");
                                document.location.href = "dashboard.jsp";
                            }
                        });                                      
                    }                  
                            
                    

                });                
                
            });
            
            function fadepopup() {	
                var id = '#dialog';

                //Get the screen height and width
                var maskHeight = $(document).height();
                var maskWidth = $(window).width();

                //Set heigth and width to mask to fill up the whole screen
                $('#mask').css({'width':maskWidth,'height':maskHeight});

                //transition effect
                $('#mask').fadeIn(500);	
                $('#mask').fadeTo("slow",10);	

                //Get the window height and width
                var winH = $(window).height();
                var winW = $(window).width();

                //Set the popup window to center
                $(id).css('top',  winH/2-$(id).height()/2);
                $(id).css('left', winW/2-$(id).width()/2);

                //transition effect
                $(id).fadeIn(2000); 	

                //if close button is clicked
                $('.window .close').click(function (e) {
                //Cancel the link behavior
                e.preventDefault();

                $('#mask').hide();
                $('.window').hide();
                });

                //if mask is clicked
                $('#mask').click(function () {
                $(this).hide();
                $('.window').hide();
                });

                }

        </script> 
        
    </body> 

</html>
