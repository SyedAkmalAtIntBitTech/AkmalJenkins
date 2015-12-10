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
    String logoImageName = null;
    String companyName = "";
    String isFacebook = "";
    String isTwitter = "";
    String accesstoken = "";
    String ManagedPage = "";
    String[] twitteracesstoken = {"", ""};

%>
<%
    try {
        sql_methods.session = request.getSession();
        imageName = (String) sql_methods.session.getAttribute("image_file_name");
        user_id = (Integer) sql_methods.session.getAttribute("UID");
        logoImageName = (String) sql_methods.session.getAttribute("ImageFileName");
        companyName = (String) sql_methods.session.getAttribute("company");

        isFacebook = request.getParameter("isFacebook");
        isTwitter = request.getParameter("isTwitter");

        if (isFacebook.equalsIgnoreCase("true")) {
            accesstoken = request.getParameter("fbaccessTokenSend");
            ManagedPage = request.getParameter("pagenameSend");
        }
        if (isTwitter.equalsIgnoreCase("true")) {
            twitteracesstoken = request.getParameter("twaccessTokenSend").split(",");
        }
    } catch (Exception e) {
        System.out.println(e.getCause());
        System.out.println(e.getMessage());
    } finally {
        sql_methods.closeConnection();
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <title>BrndBot - Social Media Posting</title>
        <meta charset="UTF-8">
        <%@ include file="fonttypekit.jsp"%>
        <link rel="stylesheet" href="css/pikaday.css">
        <link rel="stylesheet" href="css/datepickerpikaday.css">
        <script src="js/pikaday.js"></script>
         <link href="css/style.css" rel="stylesheet" type="text/css"/>
       <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script data-require="angular.js@*" data-semver="1.2.12" src="http://code.angularjs.org/1.2.12/angular.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/glyphiconiconstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js" type="text/javascript"></script>
        <!--        <script src="js/socialmedia.js" type="text/javascript"></script>-->
        <style>
            #fabookpreviewdiv,#twitterpreviewdiv,#facebookimage,#twitterimage,#facebookcancel,#twittercancel{
                display:none;
            }
            .timepicker_wrap{
                left: 0px;
                margin-top: -186px;
            }
            .arrow_top {
                    top: 165px;
                  left: 20px;
                  transform: rotate(180deg);
            }
            #mask {
                position: absolute;
                left: 0;
                top: 0;
                z-index: 9000;
                background-color:white;
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
  background-color: white;
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
.selects{
    padding:3px;
    margin: 0;
    width: 230px;
    -webkit-border-radius:4px;
    -moz-border-radius:4px;
    border-radius:4px;
    -webkit-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
    -moz-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
    box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;
    background: #f8f8f8;
    color:#888;
    border:none;
    outline:none;
    display: inline-block;
    -webkit-appearance:none;
    -moz-appearance:none;
    appearance:none;
    cursor:pointer;
    margin-bottom: 5%;
}
@media screen and (-webkit-min-device-pixel-ratio:0) {
    select {padding-right:18px}
}

label {position:relative}
label:after {
    content:'<>';
    font:11px "Consolas", monospace;
    color:#aaa;
    -webkit-transform:rotate(90deg);
    -moz-transform:rotate(90deg);
    -ms-transform:rotate(90deg);
    transform:rotate(90deg);
    right:8px; top:2px;
    padding:0 0 2px;
    border-bottom:1px solid #ddd;
    position:absolute;
    pointer-events:none;
}
label:before {
    content:'';
    right:6px; top:0px;
    width:20px; height:20px;
    background:#f8f8f8;
    position:absolute;
    pointer-events:none;
    display:block;
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
                width:220px;
                position:relative;

            }
            .hideinputborder:focus{
                outline: none;
            }
            .hideinputborderInsideImage{
                background-color:transparent;
                border: 0px solid;
                height:30px;
                width:310px;
                position:relative;
                
            }
            .hideinputborderInsideImage:focus{
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
                width:900px;
                height:590px;
                top: 25%;
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
                height: 245px;
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
                position: relative;
                left: 7px;
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
                position:relative;
                top:-10px;
                left: 7px;
                opacity: 1;
            }
            .link_url{
                font-family:AGaramondPro-Regular;
                font-size:  11px;
                color: #676767;
                font-style: normal;
                text-align: left;
                line-height: 15px;
                letter-spacing: 0em;
                position: relative;
                top: -15px;
                left: 7px;
                opacity: 1;
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
            .simplebox{
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
            .twitterlinklist{
                display:none;
            }
        </style>

        <script>
            function overlay() {
                document.getElementById('light').style.display = 'block';
                document.getElementById('fade').style.display = 'block';
                document.body.style.overflow = 'hidden';
            }
            var program_id = '0';

            function displaySchedule() {
                $("#popupschedule").show();
                var isFB = $("#isFacebook").val();
                console.log("isFB" + isFB);
                var isTwitter = $("#isTwitter").val();
                console.log("istwitter" + isTwitter);
                if ((isFB == "true") && (isTwitter == "false")) {
                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialFacebookActions(program_id);
                    $("#facebookactions").show();
                    $("#twitteractions").hide();
                    console.log("true");
                } else if ((isFB == "false") && (isTwitter == "true")) {
                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialTwitterActions(program_id);
                    $("#facebookactions").hide();
                    $("#twitteractions").show();
                } else if ((isFB == "true") && (isTwitter == "true")) {
                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialFacebookActions(program_id);
                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialTwitterActions(program_id);
//                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialActions();
                    $("#facebookactions").show();
                    $("#twitteractions").show();
                }
            }
            
            $(document).ready(function ()
            {
                $("#urlname").click(function(){
                    var link=$("#urlname").val();
                    if(!link.contains('http://'))
                    {
                        link="http://"+link;
                    }
                    $("#url").val(link);
                });
                
                $("#urlnamefb").click(function(){
                    var url=$("#urlnamefb").val();
                    var res = url.split("--");
                    $("#title").val(res[1]);
                    $("#link_title").val(res[1]);
                    var link=res[0];
                    if(!link.contains('http://'))
                    {
                        if(res[0]!==0)
                        {
                            
                        }
                        else
                        {
                            link="http://"+res[0];
                            $("#url").val(link);
                            $("#Linkurl").val(link);
                        }
                    }
                    
                    var twittertext = $("#twittertext").val();
                    if (twittertext.endsWith("bit.ly/1XOkJo"))
                    {
                        $("#twittertext").val($("#twittertext").val());
                    }
                    else {
                        $("#twittertext").val($("#twittertext").val() + "  bit.ly/1XOkJo");
                    }
                });
                
                var isFB = $("#isFacebook").val();
                var isTwitter = $("#isTwitter").val();
                if ((isFB == "true") && (isTwitter == "false")) {
                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialFacebookActions(program_id);
                    console.log("true");
                } else if ((isFB == "false") && (isTwitter == "true")) {
                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialTwitterActions(program_id);
                } else if ((isFB == "true") && (isTwitter == "true")) {
                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialFacebookActions(program_id);
                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialTwitterActions(program_id);
//                    angular.element(document.getElementById('socialmediapreview')).scope().getSocialActions();
                }                
                $("#programs").change(function(){
                    
                    program_id = $("#programs").val();
                    if ( isFB == "true"){
                        angular.element(document.getElementById('socialmediapreview')).scope().getSocialFacebookActions(program_id);
                    }
                    if ( isTwitter == "true"){
                        angular.element(document.getElementById('socialmediapreview')).scope().getSocialTwitterActions(program_id);
                    }
                    
                    if (parseInt(program_id) == 0){
                        $("#facebookactions").attr("disabled", false);
                        $("#twitteractions").attr("disabled", false);

                        document.getElementById('schedule_desc').disabled = false;
                        document.getElementById('schedule_title').disabled = false;
                        document.getElementById('schedule_social_date').disabled = false;
                        document.getElementById('schedule_social_time').disabled = false;
                    }else {
                        $("#facebookactions").attr("disabled", false);
                        $("#twitteractions").attr("disabled", false);

                        document.getElementById('schedule_desc').disabled = true;
                        document.getElementById('schedule_title').disabled = true;
                        document.getElementById('schedule_social_date').disabled = true;
                        document.getElementById('schedule_social_time').disabled = true;
                        
                    }
                });
                
            });
            function hidepopup() {
                $("#popupschedule").hide();
                $("#schedule_title").val("");
                $("#schedule_time").val("");
            }
            function socialmediapreview($scope, $http) {
                
                $http({
                        method: 'GET',
                        url: getHost() + 'getAllUserMarketingProgramsByUserId.do'
                    }).success(function (data) {
                        $scope.urls = data;
                        console.log($scope.urls);
                    }).error(function (data) {
                        alert("request not successful...1");
                    });
                
                $scope.getSocialFacebookActions = function (program_id) {

                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledActions?programid='+ program_id +'&type='+ getfacebook()
                    }).success(function (data) {
                        $scope.facebook_actions = data;
                        console.log($scope.facebook_actions);
                    }).error(function (data) {
                        alert("request not successful");
                    });
                };

                $scope.getSocialTwitterActions = function (program_id) {

                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledActions?programid='+ program_id +'&type=' + gettwitter()
                    }).success(function (data) {
                        $scope.twitter_actions = data;
                    }).error(function (data) {
                        alert("request not successful");
                    });
                };
                
                $scope.getProgramNames = function() {
                    $http({
                       method: 'GET',
                       url:getHost() + 'getAllUserMarketingPrograms.do'
                    }).success(function (data){
//                        alert(JSON.stringify(data));
                        $scope.marketing_programs = data;
                    }).error(function (data){
                        alert("request not successful");
                    });
                };
                
                $scope.getSocialActions = function () {

                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledActions?type=social'
                    }).success(function (data) {
                        $scope.social_actions = data;
                    }).error(function (data) {
                        alert("request not successful");
                    });
                };

            }
            
            function validateact() {
                var facebookactions = $("#facebookactions").val();
                var twitteractions = $("#twitteractions").val();
                console.log("FB" + facebookactions + 'TW' + twitteractions);

                if ((parseInt(facebookactions) != 0) && (parseInt(twitteractions) != 0))
                {
                    document.getElementById('schedule_desc').disabled = true;
                    document.getElementById('schedule_title').disabled = true;
                    document.getElementById('schedule_social_date').disabled = true;
                    document.getElementById('schedule_social_time').disabled = true;
                    document.getElementById('schedule_title').value="";
                    document.getElementById('schedule_desc').value="";
                    document.getElementById('schedule_social_time').value="";
                    document.getElementById('schedule_social_date').value="";
                 
                }
                else if ((parseInt(facebookactions) == 0) && (parseInt(twitteractions) != 0)) {
                    document.getElementById('schedule_desc').disabled = true;
                    document.getElementById('schedule_title').disabled = true;
                    document.getElementById('schedule_social_date').disabled = true;
                    document.getElementById('schedule_social_time').disabled = true;
                    document.getElementById('schedule_title').value="";
                    document.getElementById('schedule_desc').value="";
                    document.getElementById('schedule_social_time').value="";
                    document.getElementById('schedule_social_date').value="";
                }
                else if (((parseInt(facebookactions) != 0) && (parseInt(twitteractions) == 0))) {
                    document.getElementById('schedule_desc').disabled = true;
                    document.getElementById('schedule_title').disabled = true;
                    document.getElementById('schedule_social_date').disabled = true;
                    document.getElementById('schedule_social_time').disabled = true;
                    document.getElementById('schedule_title').value="";
                    document.getElementById('schedule_desc').value="";
                    document.getElementById('schedule_social_time').value="";
                    document.getElementById('schedule_social_date').value="";
//                    document.getElementById('hour').disabled = true;
//                    document.getElementById('minute').disabled = true;
//                    document.getElementById('AMPM').disabled = true;
//                    document.getElementById('schedule_time').disabled = true;
                } else if (((parseInt(facebookactions) == 0) && (parseInt(twitteractions) == 0))) {
                    document.getElementById('schedule_title').disabled = false;
                    document.getElementById('schedule_social_date').disabled = false;
                    document.getElementById('schedule_social_time').disabled = false;
                    document.getElementById('schedule_desc').disabled = false;
                }
            }
        </script>
        <jsp:include page="basejsp.jsp" />
    </head>

    <body ng-app>
        <div id="fade" class="black_overlay"></div>
        <div class="container-fluid" ng-controller="socialmediapreview" id="socialmediapreview">
            <jsp:include page="leftmenu.html"/>
            <div class="col-md-10 col-md-offset-0 row">
                <p id="txtpost" class="MH1">POST TO SOCIAL MEDIA</p>
                <div style="position:relative;left:8em;" class="col-md-0 col-md-offset-10">
                    <a href="selectpromotesocialmedia.jsp">
                        <p id="edit" class="BT2">go back</p>
                    </a>
                </div>
                <div class="col-sm-3 col-sm-offset-1">
                    <p class="psttxt ptpos">What social media would you like to post on?</p>
                    <img class="socialimage" id="facebookimage" src="images/fb_icon.png"><span id="facebookcancel" class="glyphicon glyphicon-remove-sign ptr"> </span>
                    <img class="socialimage" id="twitterimage" src="images/twitter.jpeg"><span id="twittercancel" class="glyphicon glyphicon-remove-sign ptr"></span><br><br><br>
                    <div id="fbtextcontainer">
<!--                        <div class="forfb">
                    Post text<input type="text" class="hideinputborder" id="posttext" placeholder="post text goes here">
                    </div>
                    <br><br>-->
                    <input type="button" class="btn btn-default" id="chnagetolinkpost" value="CHANGE TO LINK POST"><br><br>
                    <div id="linkpostdiv">
                        <div class="forfb">
                            <div class="fontpnr">
                                <select id="urlnamefb" name="marketing_program_type" class="selects">
                                    <option value="0" class="caret">LINK URL<lable></lable></option>
                                    <option ng-repeat="url in urls" value="{{url.url}}--{{url.link_name}}">{{url.prigram_name}} - {{url.link_name}} - {{url.url}}</option>
                                </select>
                            </div>
                            
                            <p class="psttxt"></p><textarea class="hideinputborder ptr"  id="title" placeholder="Link title here" style="height:40px; resize: none;"></textarea><br>
                        <p class="psttxt"></p><textarea class="hideinputborder ptr" id="description" placeholder="Link Description" style="height:40px; resize: none;"></textarea><br>
                        </div>
                        
                        <div class="fortw">
                            <div class="fontpnr twitterlinklist">
                                <select id="urlname" name="marketing_program_type" class="selects">
                                    <option value="0" class="caret">LINK URL<lable></lable></option>
                                    <option ng-repeat="url in urls" value="{{url.url}}">{{url.url}}----{{url.prigram_name}}----{{link_name}}</option>
                                </select>
                            </div>
                            <p class="psttxt"> </p><textarea class="hideinputborder ptr" id="url" placeholder="URL here" style="height:40px; resize: none;"></textarea><br>
                        </div>
                            <input type="button" class="btn btn-default" id="removelink" value="REMOVE LINK"><br><br>
                        </div>
                    </div>
                    <input type="button" class="btn btn-primary" id="posttofb" value="POST TO SOCIAL MEDIA">
                    <br>
                    <a href = "javascript:void(0)" onclick = "overlay();"><input type="button" class="btn btn-primary" id="socialschedule" value="SCHEDULE" onclick="displaySchedule()"></a>

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
                             <textarea class="hideinputborder ptr" id="posttext" placeholder="post text goes here" style="resize:none;width:325px;height:50px;"></textarea><br>
                       <div id="fblinkpostDiv">
                       <img id="facebookpreviewimage" src='/BrndBot/DownloadImage?image_type=LAYOUT_IMAGES&image_name=<%=imageName%>'>
                       <table class="fbuserDestable"> 
                           <tr><td><input id="link_title" type="text" class="link_title hideinputborderInsideImage" placeholder="Link Title" disabled></td></tr>
                           <tr><td><textarea id="link_description" class="link_description hideinputborderInsideImage" placeholder="Link Description" disabled style="resize: none;"></textarea></td></tr>
                           <tr><td><input class="hideinputborderInsideImage link_url" type="text" id="Linkurl" placeholder="LINK URL GOES HERE" disabled></td></tr> 
                      
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
                    <textarea class="hideinputborder ptr" maxlength="140" id="twittertext" placeholder="Twitter Text goes here until it reaches 140 characters long"  style="left:50px;resize: none"></textarea>
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
            <input type="hidden" id="pagenameSend" name="pagenameSend" value='<%= ManagedPage%>'/>

            <div id="popupschedule" style="display:none;">
                <div id="content" ng-init="getProgramNames();">
                    <!--                                 Mapper file name<input type="text" id="mapperxml" required><br><br>
                                                Layout file name<input type="text" id="layoutxml" required><br>-->

                    <p class="SH2" style="width:600px;">PLEASE SELECT A TIME FROM YOUR PLAN</p> 
                    <div id="light">
                        <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display = 'none';
                                    document.getElementById('fade').style.display = 'none';
                                    document.body.style.overflow = 'scroll';" style="text-decoration:none;">
                            <p style="margin-left:740px;margin-top:-35px;cursor: pointer;" id="hidepopup" onclick="hidepopup()" >
                                <img src="images/CloseIcon.svg" height="25" width="25"/>
                            </p>
                        </a>
                    </div>
                    <select name="programs" id="programs" class="SH1 selectsocialact" style="font-variant: normal;">
                        <option value="0" style="background:#fff;" >--General--</option>
                        <option style="background:#fff;" ng-repeat="programs in marketing_programs" value="{{programs.program_id}}">{{programs.name}}</option>
                    </select><br><br>
                    <div>
                        <select name="facebookactions" id="facebookactions" class="SH1 selectsocialact" style="font-variant: normal;" onchange="validateact();">
                            <option value="0" style="background:#fff;" >CUSTOM FACEBOOK</option>
                            <option style="background:#fff;" ng-repeat="fbactions in facebook_actions" value="{{fbactions.id}}">{{fbactions.schedule_title}}</option>
                        </select><br><br>
                    </div>
                    <div>
                        <select name="twitteractions" id="twitteractions" class="SH1 selectsocialact" style="font-variant: normal;" onchange="validateact();">
                            <option style="background:#fff;" value="0">CUSTOM TWITTER</option>
                            <option style="background:#fff;" ng-repeat="twitteractions in twitter_actions" value="{{twitteractions.id}}">{{twitteractions.schedule_title}}</option>
                        </select><br>
                    </div>
                    <!--                         <select name="socialactions" id="socialactions" class="SH1 selectsocialact" style="font-variant: normal;">
                                                <option style="background:#fff;" value="0">--SELECT--</option>
                                                <option style="background:#fff;" ng-repeat="socialactions in social_actions" value="{{socialactions.id}}">{{socialactions.schedule_title}}</option>
                                            </select>-->
                    <p class="SH2" style="position:relative;top:10px;">OR</p>
                    <p class="SH2" style="position:relative;top:10px;width:700px;">PLEASE CREATE A NEW TITLE AND TIME TO ADD AN ACTION TO YOUR PLAN</p>                       
                    <br>
                    <input type="text" class="simplebox SH2" id="schedule_title" name="schedule_title" placeholder="TITLE" style="font-variant: normal;"><br>
                    <textarea class="SH1 simplebox" name="schedule_desc" id="schedule_desc" placeholder="Description" style="font-variant: normal;resize:none;"></textarea><br>
                   
                    
                    <input type="text" readonly id="schedule_social_date" name="schedule_social_date" class="SH1 simplebox ptr" style="width:190px;font-variant: normal;" placeholder="DATE">
                    <script>
                        var picker = new Pikaday(
                        {
                            field: document.getElementById('schedule_social_date'),
                            firstDay: 1,
                            minDate: new Date(2000, 0, 1),
                            maxDate: new Date(2050, 12, 31),
                            yearRange: [2000,2050]
                        });
                    </script><br>
                    <input id="schedule_social_time" type="text" name="schedule_social_time" class="SH1 simplebox ptr " style="width:150px;" placeholder="TIME"/><br>
                <script src="js/timepicki.js" type="text/javascript"></script>
                <script>
                    $('#schedule_social_time').timepicki();
                </script>
                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                    <input type="hidden" name="socialscheduleid" id="socialscheduleid" value="socialmedia"/>
                    <input type="button" id ="schedulethepost" value="SCHEDULE" class="button button--moema button--text-thick button--text-upper button--size-s" style="width:170px;margin-left:0px;font-family:'proxima-nova',sans-serif;font-size:14px;" />   

                </div>
            </div>
            <input type="hidden" id="sortLengthurl" name="sortLengthurl"/>
            <div id="boxes">
                <div id="dialog" class="window"><br><br>
                    <img id="loadingGif" src="images/YogaLoadingGif.gif" />
                </div>
                <div id="mask"></div>
            </div>
        </div>

        <script>
//            $(".cross").hide();
//            $(".menu").hide();
            $("#Linkurl").hide();
            $(".link_title").hide();
            $(".link_description").hide();
//            $(".hamburger").click(function () {
//                $(".menu").slideToggle("slow", function () {
//                    $(".hamburger").hide();
//                    $(".cross").show();
//                });
//            });
//            $(".cross").click(function () {
//                $(".menu").slideToggle("slow", function () {
//                    $(".cross").hide();
//                    $(".hamburger").show();
//                });
//            });


            $(document).ready(function () {
                
               $("#facebookimage").hide();
                    $("#fabookpreviewdiv").hide();
                    $(".forfb").hide();
                    $("#twitterimage").hide();
                    $("#twitterpreviewdiv").hide();
                    
                var isFacebook = $("#isFacebook").val();
                var isTwitter = $("#isTwitter").val();
                
                $("#title").keyup(function () {
                    $(".link_title").val($("#title").val());
                });
                $("#description").keyup(function () {
                    $(".link_description").val($("#description").val());
                });

                $("#url").keyup(function () {
                    var link = $("#url").val();
                    var twittertext = $("#twittertext").val();
                    var f = link.startsWith("http");
                    if (!f)
                    {
                        link = "http://" + $("#url").val();
                    }
                    if (twittertext.endsWith("bit.ly/1XOkJo"))
                    {
                        $("#twittertext").val($("#twittertext").val());
                    }
                    else {
                        $("#twittertext").val($("#twittertext").val() + "  bit.ly/1XOkJo");
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
                else if(isFacebook === "true"){
                    $("#facebookimage").show();
                    $("#fabookpreviewdiv").show();
                    $(".forfb").show();
                    $("#facebookcancel").show();
                }

                if (isTwitter === "false") {
                    $("#twitterimage").hide();
                    $("#twitterpreviewdiv").hide();
                    $("#twittercancel").hide();
                }
                else if(isTwitter === "true"){
                    $("#twittercancel").show();
                    $("#twitterimage").show();
                    $("#twitterpreviewdiv").show();
                    $("#fbtextcontainer").show();
                    $(".twitterlinklist").show(); 
                }
                if(isTwitter==="true" && isFacebook==="true")
                {
                        $(".twitterlinklist").hide();                    
                }
                    $("#chnagetolinkpost").click(function () {
                        $("#chnagetolinkpost").hide();
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
                    $("#chnagetolinkpost").show();
                    $("#twittertext").attr("placeholder", "Twitter Text goes here until it reaches 140 characters long");
                    $("#twittertext").attr("maxlength", "140");
                    $("#title").val("");
                    $("#description").val("");
                    $("#url").val("");
                    $("#linkpostdiv").hide();
                    $("#previewchield").css("height", "400px");
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
                    //$(".fortw").hide();

                });

                $("#facebookcancel").click(function () {
                    $("#facebookcancel").hide();
                    $(".twitterlinklist").show();
                    $("#facebookimage").hide();
                    $("#fabookpreviewdiv").hide();
                    $("#isFacebook").val("false");
                    $(".forfb").hide();
                });

                $("#posttofb").click(function () {
                    fadepopup();
                    var isFacebook = $("#isFacebook").val();
                    var isTwitter = $("#isTwitter").val();
                    var image_name = $("#imageToPost").val();
                    var link = $("#url").val();
                    var f = link.startsWith("http");
                    if (!f)
                    {
                        link = "http://" + $("#url").val();
                    }
                    var url = link;
                    var username = "sandeep264328"; // bit.ly username
                    var key = "R_63e2f83120b743bc9d9534b841d41be6";
                    $.ajax({
                        url: "http://api.bit.ly/v3/shorten",
                        data: {longUrl: url, apiKey: key, login: username},
                        dataType: "jsonp",
                        success: function (v)
                        {
                            var bit_url = v.data.url;
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
                                        shorturl: $("#sortLengthurl").val()
                                    },
                                    success: function (responseText) {
                                        $('#mask').hide();
                                        $('.window').hide();
                                        alert("Your post has been Scheduled Successfully");

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
                    var image_name = $("#imageToPost").val();
                    var program_id = $("#programs").val();
                    var schedule_id_facebook = "0";
                    var schedule_id_twitter = "0";
                    var ManagedPage = "";
                    
                    if ((isFacebook == "true") && (isTwitter == "false")) {
                        schedule_id_facebook = $("#facebookactions").val();
                        ManagedPage = $("#pagenameSend").val();
                    } else if ((isFacebook == "false") && (isTwitter == "true")) {
                        schedule_id_twitter = $("#twitteractions").val();
                    } else if ((isFacebook == "true") && (isTwitter == "true")) {
                        schedule_id_facebook = $("#facebookactions").val();
                        schedule_id_twitter = $("#twitteractions").val();
                        ManagedPage = $("#pagenameSend").val();
                    }

                    console.log(schedule_id_facebook);
                    if ((schedule_id_facebook == "0") && (schedule_id_twitter == "0")) {
                        var schedule_date = $("#schedule_social_date").val();
                        var schedule_time = $("#schedule_social_time").val().replace(/ /g,'');  
                        var schedule_title = $("#schedule_title").val();
//                        var schedule = $("#schedule_time").val();
//                        var dateepoch = Date.parse(schedule);
                        
                        var l=schedule_date.toLocaleString() +" "+schedule_time.toLocaleString();
                        var schedule_time = Date.parse(l);
                        console.log("Epoch: " + schedule_time);
                        var myEpoch = schedule_time;
                        console.log("New Epoch: " + myEpoch);
//                        var newdate = new Date(dateepoch);
//
//                        console.log("new date:" + newdate);
//                        var schedule_hour = $("#hour").val();
//                        var schedule_minute = $("#minute").val();
//                        var schedule_AM = $("#AMPM").val();
//
//                        if (schedule_AM == "PM") {
//                            schedule_hour = parseInt(schedule_hour) + 12;
//                        }
//                        newdate.setHours(parseInt(schedule_hour));
//                        newdate.setMinutes(parseInt(schedule_minute));
//
//                        console.log("Value selected from Component: " + newdate);
//                        var schedule_time = Date.parse(newdate);
//
//                        console.log("Epoch: " + schedule_time);
//
//                        var dateObj = new Date(schedule_time);
//                        console.log(dateObj.getTimezoneOffset());
//
//                        var tzOffsetInMillis = dateObj.getTimezoneOffset() * 60 * 1000;
//
//                        var newEpoch = schedule_time;
//                        console.log("New Epoch: " + newEpoch);
                        var schedule_desc = $("#schedule_desc").val();

                        var social_schedule = "";
                        if (isFacebook == "true" && isTwitter == "false") {
                            social_schedule = [
                                {
                                    type: getfacebook(),
                                    image_name: image_name,
                                    schedule_time: myEpoch,
                                    schedule_title: schedule_title,
                                    program_id: program_id,
                                    schedule_desc: schedule_desc,
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        description: '"' + $("#description").val() + '"',
                                        post_text: '"' + $("#posttext").val() + '"',
                                        url: '"' + $("#url").val() + '"',
                                        ManagedPage: '"' + ManagedPage + '"',
                                        title:'"' + $("#link_title").val() + '"'
                                    }
                                }
                            ];

                        } else if (isFacebook == "false" && isTwitter == "true") {
                            social_schedule = [
                                {
                                    type: gettwitter(),
                                    image_name: image_name,
                                    schedule_time: myEpoch,
                                    schedule_title: schedule_title,
                                    program_id: program_id,
                                    schedule_desc: schedule_desc,
                                    token_data: {
                                        "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                        "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                    },
                                    metadata: {
                                        text: '"' + $("#twittertext").val() + '"',
                                        shorturl:'"' + $("#url").val()+'"'
                                    }
                                }
                            ];

                        } else if (isFacebook == "true" && isTwitter == "true") {

                            social_schedule =
                                    [
                                        {
                                            type: getfacebook(),
                                            image_name: image_name,
                                            schedule_time: myEpoch,
                                            schedule_title: schedule_title,
                                            program_id: program_id,
                                            schedule_desc: schedule_desc,
                                            token_data: {
                                                "access_token": '"' + $("#accesstoken").val() + '"'
                                            },
                                            metadata: {
                                                description: '"' + $("#description").val() + '"',
                                                post_text: '"' + $("#posttext").val() + '"',
                                                url: '"' + $("#url").val() + '"',
                                                ManagedPage: '"' + ManagedPage + '"',
                                                title:'"' + $("#link_title").val() + '"'
                                            }
                                        },
                                        {
                                            type: gettwitter(),
                                            image_name: image_name,
                                            schedule_time: myEpoch,
                                            schedule_title: schedule_title,
                                            program_id: program_id,
                                            schedule_desc: schedule_desc,
                                            token_data: {
                                                "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                                "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                            },
                                            metadata: {
                                                text: '"' + $("#twittertext").val() + '"',
                                                shorturl:'"' + $("#url").val()+'"'
                                            }
                                        }
                                    ];
                        }
                        console.log(JSON.stringify(social_schedule));
                        $.ajax({
                            url: getHost() + 'ScheduleSocialPost',
                            method: 'post',
                            dataType: 'json',
                            contentType: 'application/json',
                            mimeType: 'application/json',
                            data: JSON.stringify(social_schedule),
                            success: function (responseText) {
//                            $("#tokenHere").html(responseText);
//                                alert(image_name);
                                alert("Your post has been Scheduled Successfully");
                                document.location.href = "dashboard.jsp";
                            }
                        });
                    } else {
                        if (isFacebook == "true" && isTwitter == "false") {
                            social_schedule = [
                                {
                                    type: getfacebook(),
                                    image_name: image_name,
                                    program_id: program_id,
                                    schedule_id: schedule_id_facebook,
                                    token_data: {
                                        "access_token": '"' + $("#accesstoken").val() + '"'
                                    },
                                    metadata: {
                                        description: '"' + $("#description").val() + '"',
                                        post_text: '"' + $("#posttext").val() + '"',
                                        url: '"' + $("#url").val() + '"',
                                        ManagedPage: '"' + ManagedPage + '"',
                                        title:'"' + $("#link_title").val() + '"'
                                    }
                                }
                            ];

                        } else if (isFacebook == "false" && isTwitter == "true") {
                            social_schedule = [
                                {
                                    type: gettwitter(),
                                    image_name: image_name,
                                    program_id: program_id,
                                    schedule_id: schedule_id_twitter,
                                    token_data: {
                                        "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                        "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                    },
                                    metadata: {
                                        text: '"' + $("#twittertext").val() + '"',
                                        shorturl:'"' + $("#url").val()+'"'
                                    }
                                }
                            ];

                        } else if (isFacebook == "true" && isTwitter == "true") {

                            social_schedule =
                                    [
                                        {
                                            type: getfacebook(),
                                            image_name: image_name,
                                            program_id: program_id,
                                            schedule_id: schedule_id_facebook,
                                            token_data: {
                                                "access_token": '"' + $("#accesstoken").val() + '"'
                                            },
                                            metadata: {
                                                description: '"' + $("#description").val() + '"',
                                                post_text: '"' + $("#posttext").val() + '"',
                                                url: '"' + $("#url").val() + '"',
                                                ManagedPage: '"' + ManagedPage + '"',
                                                title:'"' + $("#link_title").val() + '"'
                                            }
                                        },
                                        {
                                            type: gettwitter(),
                                            image_name: image_name,
                                            program_id: program_id,
                                            schedule_id: schedule_id_twitter,
                                            token_data: {
                                                "access_token": '"' + $("#twittweraccestoken").val() + '"',
                                                "token_secret": '"' + $("#twitterTokenSecret").val() + '"'
                                            },
                                            metadata: {
                                                text: '"' + $("#twittertext").val() + '"',
                                                shorturl:'"' + $("#url").val()+'"'
                                            }
                                        }
                                    ];
                        }
                        console.log(JSON.stringify(social_schedule));
                        $.ajax({
                            url: getHost() + 'ScheduleSocialPostActions',
                            method: 'post',
                            dataType: 'json',
                            contentType: 'application/json',
                            mimeType: 'application/json',
                            data: JSON.stringify(social_schedule),
                            success: function (responseText) {
//                            $("#tokenHere").html(responseText);
//                                alert(image_name);
                                alert("Your post has been Scheduled Successfully");
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
                $('#mask').css({'width': maskWidth, 'height': maskHeight});

                //transition effect
                $('#mask').fadeIn(500);	
                $('#mask').fadeTo("slow",0.95);	

                //Get the window height and width
                var winH = $(window).height();
                var winW = $(window).width();

                //Set the popup window to center
                $(id).css('top', winH / 2 - $(id).height() / 2);
                $(id).css('left', winW / 2 - $(id).width() / 2);

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
