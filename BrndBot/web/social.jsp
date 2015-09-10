<%@page import="com.controller.SqlMethods"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>social</title>
        <meta charset="UTF-8" >
         <%@ include file="fonttypekit.jsp"%>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>  
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <script src="js/tabcontent.js" type="text/javascript"></script>
        <link href="tabs/tabcontent.css" rel="stylesheet" type="text/css"/>
        <script src="js/socialsettings.js" type="text/javascript"></script>
         <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <style>
            .glyphicon.glyphicon-home,.glyphicon.glyphicon-envelope,.glyphicon.glyphicon-comment, .glyphicon.glyphicon-picture, .glyphicon.glyphicon-cog{
                font-size:20px;
                position: relative;
                left:-15px;
                padding: 7px;
            }
            
            #popup
            {
                display:none;
                position: fixed;
                width:350px;
                height: 450px;
                top: 40%;
                left: 50%;
                margin-left:-155px;
                margin-top:-110px;
                border:5px solid #98b3b5;
                background-color:#F0F8FF;
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
                margin:30px auto;
            }
            
        </style>
        <style>
            .socialimage{
                width: 100px;
                height: 100px;
                margin-left:  5px;
            } 
            
            #popup
            {
               display:none;
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
        <style>
            .fileUpload {
                position: relative;
                overflow: hidden;
                margin: 10px;
            }
            .fileUpload input.upload {
                position: absolute;
                top: 0;
                right: 0;
                margin: 0;
                padding: 0;
                font-size: 20px;
                cursor: pointer;
                opacity: 0;
                filter: alpha(opacity=0);
            }

            .container{
                   position: absolute;
                   float: left;
/*                            margin-left: -250px;*/
               }

        </style>
        <%! 
            HttpServletRequest request;
            Object code = "";
            String ImageName="";
            String user_name = "";
        %>
        <% 
            try{
                code = (Object)request.getAttribute("objkey");
                user_name = (String)request.getAttribute("user_profile_name");
                ImageName=request.getParameter("image");
            }catch (Exception e){
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                
            }
        
        %>
          <script>
            
            $(document).ready(function () {
                var myVar1 = '<%= code %>';    /* retrieve json from request attribute */
                var mytest = eval('(' + myVar1 + ')');

                var removecote = myVar1.replace("[", '').replace(/"/g, '').replace(']', '');
                var pages = removecote.split(",");

                if (myVar1 === "null") {
                    $("#popup").hide();
                }
                else {
                    $("#popup").show();

                    for (var i = 0; i < pages.length; i = i + 3) {
                        $("#fbmanagepages").append("<tr  id=page#" + i + "><td><p id=p"+i+">" + pages[i] + "</p></td><td><input type=hidden id=access" + i + " value=" + pages[i + 1] + "></td><td><img src=" + pages[i + 2] + "></td> </tr>");
                    }

                    $("#content").append(" <br><center><input id=isdefault name=isdefault type=checkbox class=btn btn-primary value=default>Default</input></center>");
                    $("#content").append(" <br><center><input id='facebookok' name='facebookok' type='button' class='btn btn-primary' value='ok' ng-click='getfacebookdetails()' >&nbsp;&nbsp;<input id=close name=close type=button class=btn btn-primary value=cancel></center>");
                }
 
                var managed_page = "";
                var default_access_token = "";
                var fb_user_profile_name = "";
                var check_default = "false";
                var check_default_managed_page;
                $("tr").click(function () {
                    var id = this.id.split("#");

                    var page = $("#p" + id[1]).text();
                    var accessToken = $("#access" + id[1]).val();
                    $("#pagenameSend").val(page);
                    $("#fbaccessTokenSend").val(accessToken);
                    $("#fbdefaultAccessToken").val("true");
                    check_default = $("#fbdefaultAccessToken").val();

                });

                $("#isdefault").click(function () {
                    if (check_default == "true"){
                        default_access_token = $("#fbaccessTokenSend").val();
                        managed_page = $("#pagenameSend").val();
                        fb_user_profile_name = $("#fbusername").val();
                    }
                    
                    });

                $("#facebookok").click(function () {
                    check_default_managed_page = document.getElementById("isdefault").checked;
                    
                    if ((check_default_managed_page === true) && (check_default === "true")){
                        $.ajax({
                                url: 'ServletUserPreferencesFacebook',
                                method: 'post',
                                data: {
                                   default_page_name: managed_page,
                                   fb_user_profile_name: fb_user_profile_name,
                                   access_token_method: "setAccessToken",
                                   access_token:default_access_token
                                },
                                success: function (responseText) {
                                    alert("sucess");
                                    $("#popup").hide(); 
                                    $("#submitbutton").prop("disabled",false);
                                    angular.element(document.getElementById('controllerSocial')).scope().getFacebookDetails();                                    
                            }
                            });
                    }else if((check_default_managed_page === false) && (check_default === "true")) { 
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
           
           function imgchng(){
               document.getElementById("fb").src="images/fb_icon.png";
               
           }
           
        </script>
      
        <script>
                    
                function controllerSocial($scope, $http) {
    
                    $scope.getFacebookDetails = function () {
                        $http({
                            method: 'GET',
                            url: 'ServletUserPreferencesFacebook?access_token_method=getAccessToken&settings=true'
                        }).success(function (data, status, headers, config) {
                            $scope.facebookPage = data;
                            if (data.FacebookLoggedIn == "true"){
                                $("#fbclear").show();
                            }else{
                                $("#fbclear").hide();
                            }
                            if (data === error) {
                                alert(data);
                            }
                        }).error(function (data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });
                    };
                    
                    $scope.clearFacebookDetails = function () {

                    if(confirm('Do you want to really clear the details?')){
                        $http({
                            method: 'POST',
                            url: 'ServletUserPreferencesFacebook?access_token_method=clearFacebookDetails'
                        }).success(function (data, status, headers, config) {
                            $scope.getfacebookdetails();
                        }).error(function (data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });
                        alert('Details cleared successfully!');
                    }
                    };
                    
                    $scope.getTwitterDetails = function () {
                        
                        $http({
                            method: 'GET',
                            url: 'ServletUserPreferencesTwitter?access_token_method=getAccessToken&settings=true'
                        }).success(function (data, status, headers, config) {
                            $scope.twitterPage = data;

                            if (data.TwitterLoggedIn == "true"){
                                $("#twitterclear").show();
                            }else{
                                $("#twitterclear").hide();
                            }
                        }).error(function (data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });
                    };
                    
                    $scope.clearTwitterDetails = function () {

                    if(confirm('Do you want to really clear the details?')){
                        $http({
                            method: 'POST',
                            url: 'ServletUserPreferencesTwitter?access_token_method=clearTwitterDetails'
                        }).success(function (data, status, headers, config) {
                            $scope.getTwitterDetails();
                        }).error(function (data, status, headers, config) {
                            alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });
                        alert('Details cleared successfully!');
                    }
                    };
                 }                    
       
        </script>
        <jsp:include page="basejsp.jsp" />
    </head>
    <body ng-app>
        
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
        <div ng-controller="controllerSocial" id="controllerSocial">

            <div class="row">
                <jsp:include page="mainmenu.html"/><!--/end left column-->

                <div class="col-md-10 col-md-offset-1">
                    <div class="col-md-10 ">
                        <p id="text3" class="MH1"> Setting page</p>


                        <div style="width:500px; margin:0px; padding:120px 0 40px;">

                            <div class="tabcontents">
                                <div id="view1" style="width:550px; height:230px ">

                                    <b>Facebook</b>
                                    <div id="fbpagename" ng-init="getFacebookDetails()">
                                        Profile Name : {{facebookPage.user_profile_page}}<br>
                                        Default Managed Page Name : {{facebookPage.fb_default_page_name}}<br>
                                        <button id="facebook" class="button button--moema button--text-thick button--text-upper button--size-s" name="change">change</button>
                                        <button id="fbclear" class="button button--moema button--text-thick button--text-upper button--size-s" name="fbclear" ng-click="clearFacebookDetails()">clear</button><br>
                                    </div>
                                    
                                    
                                </div>
                                <div id="view2" style="width:750px; height:auto;" >
                                    <b>Twitter</b>
                                    <div id="twpagename" >
                                        Profile Name : {{twitterPage.twitter_user_name}}<br>
                                        <button id="twitter" class="button button--moema button--text-thick button--text-upper button--size-s" name="change">change</button>
                                        <button id="twitterclear" class="button button--moema button--text-thick button--text-upper button--size-s" name="twitterclear" ng-click="clearTwitterDetails()">clear</button><br>
                                    </div>
                                    
                                </div>

                            </div>
                            <input type="hidden" id="fbusername" name="fbusername" value="<%= user_name %>"/>
                            <input type="hidden" id="fbaccessTokenSend" name="fbaccessTokenSend">
                            <input type="hidden" id="pagenameSend" name="pagenameSend">
                            <input type="hidden" id="fbdefaultAccessToken" name="fbdefaultAccessToken">
                            <input type="hidden" id="twaccessTokenSend" name="twaccessTokenSend" >
                            <ul class="tabs1" data-persist="true">
                                <li><a href="#view1" style="width:180px;" ng-click="getFacebookDetails()">Facebook</a></li>
                                <li><a href="#view2"  style="width:180px;" ng-click="getTwitterDetails()">Twitter</a></li>
                            </ul>
                        </div>                        
                    </div>
                </div>


            </div>
        </div>

    </body>
</html>
