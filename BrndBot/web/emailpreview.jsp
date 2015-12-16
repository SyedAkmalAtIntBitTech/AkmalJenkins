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
        <link rel="stylesheet" href="css/pikaday.css">
        <link rel="stylesheet" href="css/datepickerpikaday.css">
        <script src="js/pikaday.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <!--<script src="js/leftmenuhamburger.js" type="text/javascript"></script>-->
        
       <link href="css/style.css" rel="stylesheet" type="text/css"/>
       <link href="css/timepicki.css" rel="stylesheet" type="text/css"/>
        
        <link href="css/emailpreview.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="http://cdnjs.cloudflare.com/ajax/libs/fancybox/1.3.4/jquery.fancybox-1.3.4.css" />
        <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        
        <style>
/*            .slt {
                                position:relative;
				display:inline-block;
				text-align:left;
				line-height:30px;
				clear:both;
                                font-weight:600;
				color: #000000;
                                background-color: #f6f6f6;
				border:1px solid #f6f6f6;
                                border-radius:10px;
				width:300px;
			}
                        option.optn{
                            background-color:#d4d4d4;
                            position: absolute;
                            top:50px;
                        }*/

        #drop-zone {
            /*Sort of important*/
            width: 607px;
            /*Sort of important*/
            height: 200px;
            position:absolute;
            margin-left: 34%;
            border: 2px dashed rgba(0,0,0,.3);
            border-radius: 20px;
            font-family: Arial;
            text-align: center;
            position: relative;
            line-height: 180px;
            font-size: 20px;
            color: rgba(0,0,0,.3);
            }
            #emailaddresses {
            margin-left: 25%;
            }
            #drop-zone input {
                /*Important*/
                position: absolute;
                /*Important*/
                cursor: pointer;
                left: 0px;
                top: 0px;
                /*Important This is only comment out for demonstration purpeses.
                opacity:0; */
            }

            /*Important*/
            #drop-zone.mouse-over {
                border: 2px dashed rgba(0,0,0,.5);
                color: rgba(0,0,0,.5);
                
            }


            /*If you dont want the button*/
            #clickHere {
                position: absolute;
                cursor: pointer;
                
                top: 0%;
                margin-left: 12px;
                margin-top: 20px;
                line-height: 26px;
                color: white;
                font-size: 15px;
                width: 270px;
                height: 150px;
                border-radius: 4px;
                color:#000;
                background-color: #f2f2f4;
            }

            #clickHere:hover {
             

            }            
        .header1{
/*            font-family: "proxima-nova",sans-serif;
            font-style: normal;
            font-weight: 600;*/
            color: #3f4042;
            text-align: left;
            line-height: 25.8px;
            letter-spacing: 0em;
            opacity: 1;
            position: relative;
            padding-top: 6%;
            left: 24%;
           
        }
        .header2{
            font-family: "proxima-nova",sans-serif;
            font-style: normal;
            font-weight: 300;
            font-size:13.5px;
            color: #3f4042;
            text-align: left;
            line-height: 15.8px;
            letter-spacing: 0em;
            opacity: 1;
            position: relative;
        }
            .hideinputborder{
                background-color:transparent;
                border: 0px solid;
                height:50px;
                width:450px;
                font-size:18.5px;
                position: relative;
                padding-top:10%;
            }
/*            
 #emailsubject:focus ~ input, #emailsubject:valid ~ input 
 {     
  left:-35px;
  font-size:13px;
  color:#999;
}*/

            .hideinputborder:focus{
                outline: none;
            }
            .hideinputEmailId{
                background-color:transparent;
                border: 0px solid;
                height:50px;
                width:250px;
                margin-left: 150px;
                font-size: 18px;
            }
            .hideinputEmailId{
                outline: none;
            }
            #chooseEmailListopt{
               
                color:black;
                border: 1px solid #DADADA;
                height:2em;
                width:350px;
                font-size: 18px;
                border-radius: 5px;
            }
            
            fileUpload {
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
            
            #chooseEmailList{
               border: none ;
                width: 304px;
                height: 27px;
               -webkit-appearance: none;
                -moz-appearance: none;
                 box-shadow: none !important ;
                appearance: none;
                padding: 7px 1px 5px 5px;
                border: 2px solid #d0d0d0;
                border-left: none;
                border-right: none;
                border-top: none;
                border-bottom: none;
                border-radius: 2px;
                color:#2b2f35;
                background: url("images/dropdown.png") no-repeat 275px center;
                background-color: #F2F2F4; 
                font-size:18px;
                color: #3f4042;
                height:40px;
                margin-left: 24%;
            }
            
            #chooseEmailId:focus{
                outline: none;
            }
            #textArea{
                width: 400px;
                height : 300px;
                background-color: #e4e4e4;
            }
            .btn-primary{
                border-radius:10px;
                position: fixed;
                top: 70%;
                width: 200px;
                background-color: #00A37A;
                border-color: #00A37A;
            }
            .btn-upload{
                position: relative;
                width: 100px;
                background-color:#D1D2D9;
                color:#fff;
                font-size:15px;
                border-radius:3px;
                border-style: none;
            }
            .btn-csv{
                font-size:20px;
                border-radius:5px;
                position:relative;
                top: 20px;
                width: 200px;
                background-color:#D1D2D9;
                color:#fff;
                border-style: none;
                margin-left: 24%;
            }
            .btn-prim{
                position: fixed;
                top: 60%;
                left:17%;
            }
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

        

/*            .header1{
                font-size: 28px;
            }*/
            .hideinputborder{
                background-color:transparent;
                border: 0px solid;
                height:50px;
                width:450px;
                font-size: 35px;
            }
            .hideinputborder:focus{
                outline: none;
            }
            .hideinputEmailId{
                background-color:transparent;
                border: 0px solid;
                height:50px;
                width:250px;
                margin-left: 150px;
                font-size: 18px;
            }
            .hideinputEmailId{
                outline: none;
            }
            #chooseEmailId{
                background-color:transparent;
                border: 0px solid;
                height:50px;
                width:250px;
                font-size: 18px;
            }
            #chooseEmailId:focus{
                outline: none;
            }

        </style>
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
        <title>BrndBot - Preview Email</title>
        <style>
            #emailSettings{
                display:none;
            }
            .timepicker_wrap {
                margin-top:22px;
            }
            .icon-chevron-up,.bootstrap-datetimepicker-widget,.timepicker,.timepicker-hours,.table-condensed,.btn,.dropdown-menu{
                z-index: 2000;
            }
            .icon-chevron-up{
                z-index: 2000;
            }
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
                height:485px;
                top: 30%;
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
            .selectsocialact>option{
                background-color: #fff;
                font-size: 16px;
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
                margin-left:200px;
                /*               
                                width: 500px;
                                height: 50em;*/
            }
            .preview{
            }
            #entertext,#dragtext,#emailaddresses,#drop-zone,#upload,#clickHere{
                display:none;
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

    <%!
        String emailSubject = "";
        String emailList = "";
        String htmlData = "";
        String emailAddresses = "";
        String iframeName = "";
        String iframeUrl="";
        String draft_id = "0";
    %>
    <%
        sqlmethods.session = request.getSession(true);

        emailSubject = (String) sqlmethods.session.getAttribute("email_subject");
        //emailAddresses = (String) sqlmethods.session.getAttribute("email_addresses");
        htmlData = (String) sqlmethods.session.getAttribute("htmldata");
        iframeName = (String) sqlmethods.session.getAttribute("iframeName");
        iframeUrl="/BrndBot/DownloadHtmlServlet?file_name="+iframeName+".html";
        draft_id = "0";
        if (!request.getParameter("draftid").equals("null")){
                    draft_id = (String)request.getParameter("draftid");
                    out.println();
        }
    %>
    <script>
        var draft_id = 0;
         $(document).ready(function () {
                    draft_id = <%= draft_id %>;
                    console.log(draft_id);
                });
//        $(document).ready(function (){$("#toaddress").click(function (){var addr=$("#toaddress").val();if(addr!==""){$("#toaddrlbl").css("left","-70px");}else{$("#toaddrlbl").css("left","0px");alert("data");}});});
        function emailSettings($scope, $http){
            
                $("#emailIdContinueButton").click(function () {
                    var selectedEmail = $("#chooseEmailList").val();
                    if(selectedEmail !== "1")
                    {
                        var email_subject = "";
                        var email_addresses = $("#emailaddresses").val();
                        
                        if(email_addresses!=="")
                        {
                            $("#toaddress").val(email_addresses);
                            $("#emaillistdiv").hide();
                            $("#emailSettings").show();
                            $("#emaillistdiv").hide();
                            $("#emailSettings").show();
                            var email_list = $("#chooseEmailList").val();
                            $.ajax({
                                url: getHost() + "EmailTextDataServlet",
                                data: {
                                    email_subject: email_subject,
                                    email_addresses: email_addresses,
                                    email_list : email_list
                                },
                                success: function(result){
                                }
                            });
                        }
                        else
                        {
                            alert("please select at least one email list or add email manually");
                            selectCsvFile();
                            $("#emailaddresses").focus();
                            return false;
                        }
                    }
                else{
                        
                        var email_subject = "";
                        var email_addresses = $("#emailaddresses").val();
                        if(email_addresses!=="")
                        {
                            $("#toaddress").val(email_addresses);
                            $("#emaillistdiv").hide();
                            $("#toaddress").val(email_addresses);
                            $("#emailSettings").show();
                            var email_list = $("#chooseEmailList").val();
                            $.ajax({
                                url: getHost() + "EmailTextDataServlet",
                                data: {
                                    email_subject: email_subject,
                                    email_addresses: email_addresses,
                                    email_list : email_list
                                },
                                success: function(result){
                                }
                            });
                        }
                        else
                        {
                            alert("please select at least one email list or add email manually");
                            selectCsvFile();
                            $("#emailaddresses").focus();
                            return false;
                        }
                }
            });
                
            
                $scope.getEmailSettings = function(){
                
                var email_settings = {"type": "get"};
                
                $http({
                        method : 'POST',
                        url : 'EmailSettingsServlet',
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
                    var schedule_id = $("#email_actions").val();
                    var from_name = $("#name").val();
                    var email_subject = $("#subject").val();
                    var to_email_addresses = $("#toaddress").val();
                    var from_email_address = $("#formaddress").val();
                    var reply_to_email_address = $("#email").val();
                    var program_id = $("#programs").val();
                    var email_body = formattedHTMLData;
                    var email_list = $("#chooseEmailList").val();
                    var schedule_desc = "none";
                    var iframe_name = $("#iframe_name").val();
                    console.log(schedule_id);
                    if (schedule_id == "0"){
                        if($("#schedule_title").val()=="")
                        {
                            alert("Please Enter Title");
                            $("#schedule_title").focus();
                            return false;
                        }
                        if($("#schedule_date").val()=="")
                        {
                            alert("Please Choose Date");
                            $("#schedule_date").focus();
                            return false;
                        }
                        if($("#schedule_time").val()=="")
                        {
                            alert("Please Chooose Time");
                            $("#schedule_time").focus();
                            return false;
                        }
                        var schedule_title = $("#schedule_title").val();
                        var schedule_date = $("#schedule_date").val();
                        var schedule_time = $("#schedule_time").val().replace(/ /g,'');
//                        var schedule = $("#schedule_time").val();
                        var l=schedule_date.toLocaleString() +" "+schedule_time.toLocaleString();
                        var schedule_time = Date.parse(l);
                        console.log("Epoch: " + schedule_time);
                        var myEpoch = schedule_time;
                        console.log("New Epoch: " + myEpoch);
                        var email_scheduling = {
                            "from_name": from_name, 
                            "program_id": program_id,
                            "email_subject": email_subject, 
                            "to_email_addresses": to_email_addresses, 
                            "from_email_address": from_email_address, 
                            "reply_to_email_address": reply_to_email_address, 
                            "email_list": email_list, 
                            "schedule_title": schedule_title, 
                            "schedule_time": myEpoch, 
                            "email_body": email_body, 
                            "schedule_desc": schedule_desc,
                            "iframeName": iframe_name
                        };
                        $http({
                            method: 'POST',
                            url: 'ScheduleEmail',
                            headers: {'Content-Type': 'application/json'},
                            data: email_scheduling
                        }).success(function (data) {                            
                            if (data != "") {
                               $http({
                               method: 'POST',
                               url: getHost() + "deleteEmailDraft.do?draftid="+draft_id
                               }).success(function (data) {
                                   alert("Your Email has been Scheduled Successfully");
                                   document.location.href = "dashboard.jsp";
                                
                                }).error(function (data) {
                                    alert("No data available, problem fetching the data");
                                });
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
                            "schedule_desc": schedule_desc,
                            "iframeName": iframe_name
                        };
                        $http({
                            method: 'POST',
                            url: 'ScheduleEmailActions',
                            headers: {'Content-Type': 'application/json'},
                            data: email_scheduling
                        }).success(function (data) {
                            if (data != "") {
                               $http({
                               method: 'POST',
                               url: getHost() + "deleteEmailDraft.do?draftid="+draft_id
                               }).success(function (data) {
                                alert("Your Email has been Scheduled Successfully");
                                document.location.href = "dashboard.jsp";
                                
                                }).error(function (data) {
                                    alert("No data available, problem fetching the data");
                                });
                            }
                        }).error(function (data) {
                            alert("No data available, problem fetching the data...2");
                        });
                        
                    }
                    
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
                
                $scope.getActions = function (program_id) {
                    $http({
                        method: 'GET',
                        url: getHost() + 'GetScheduledActions?programid='+ program_id + '&type='+ getemail()
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
        var program_id = '0';
        setTimeout(
        function() 
        {
            formattedHTMLData=$("#dynamictable").contents().find("html").html();
          //do something special
        }, 1000);
       $(document).ready(function () {
           
           $("#chooseEmailList").change(function () {
//                    var x = document.getElementById("chooseEmailList").selectedIndex;
//                    var List_name = document.getElementsByTagName("option")[x].value;
                    var List_name = $("#chooseEmailList").val();
                    if (List_name == 1){
//                        $("#emailaddresses").hide();
//                        $("#drop-zone").hide();
//                        $("#clickHere").hide();
//                        $("#upload").hide();
//                        $("#dragtext").hide();
//                        $("#entertext").hide();
//                         $("#emailIdContinueButton").css("top","50px");
                       
                    }else {
                        var emails = "";
                        $("#email_list_name").val(List_name);
                        $.ajax({
                                url: getHost() + "GetEmailLists",
                                data: {
                                    update: "emailsForEmailList",
                                    list_name: List_name
                                },
                                success: function(result){
                                    var i = 0;
                                    
                                    for(i=0; i<result.user_emailAddresses.length; i++){
                                        if (result.user_emailAddresses[i].emailAddress!= ""){
                                            emails = result.user_emailAddresses[i].emailAddress+ "," + emails;
                                     }
                                    }
                                    for(i=0; i<result.mindbody_emailAddresses.length; i++){
                                        if (result.mindbody_emailAddresses[i] != ""){
                                            emails = result.mindbody_emailAddresses[i] + "," + emails;
                                        }
                                    }                                    
                                    $("#emailaddresses").val(emails);
                                    $("#toaddress").val(emails);
                                }
                        });
                    }
                });
           
//            $(".hamburger,.cross").hide();
            $("#programs").change(function(){
                    
                program_id = $("#programs").val();
                angular.element(document.getElementById('emailSettings')).scope().getActions(program_id);
                if (parseInt(program_id) == 0){
                    $("#email_actions").attr("disabled", false);

                    document.getElementById('schedule_title').disabled=false;
                    document.getElementById('schedule_date').disabled=false;
                    document.getElementById('schedule_time').disabled=false;
                }else {
                    $("#email_actions").attr("disabled", false);

                    document.getElementById('schedule_title').disabled=true;
                    document.getElementById('schedule_date').disabled=true;
                    document.getElementById('schedule_time').disabled=true;

                }

            });
           $("#humbrgr").click(function (){
            if (confirm("Are you sure, You want to leave this page?")){
                
            }
            else{
                $("#crs").click(); 
               $(".navicons,#txtlog").hide().delay( 800 ).fadeIn( 600 );     
            }
            }
            );
           formattedHTMLData=$("#dynamictable").contents().find("html").html();
           show("iphone");
        });
        
        
        
        function displaySchedule() {
                if (validate()) {
                    angular.element(document.getElementById('emailSettings')).scope().getActions(program_id);
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
        function show(id) {
            formattedHTMLData=$("#dynamictable").contents().find("html").html();
            
            var imageUrl = $("#" + id).css("background-image");

            if (id === "ipad") {
                $(".iphoneshow").css("background-image", imageUrl).css("display", 'block').css("width", "239px").css("height", "300px")
                        .css("border-color", "transparent").css("margin-left", "150px").css("margin-top", "-80px").css("background-color","#FFF");
                $('#dynamictable').css("width","770px").css("height","958px").css("top","-465px").css("left","-300px").css("-webkit-transform"," scale(0.265)");
            }
            else if (id === "imac")
            {
                $(".iphoneshow").css("background-image", imageUrl)
                        .css("display", 'block').css("height", "413px").css("width", "295px").css("margin-left", "150px").css("margin-top", "-80px")
                        .css("border-color", "transparent").css("background-color","#FFF");
                 $('#dynamictable').css("width","768px").css("height","615px").css("top","-316px").css("left","-272px").css("-webkit-transform","scale(0.3326)");
            }
            else if (id === "iphone"){
                $(".iphoneshow").css("background-image", imageUrl)
                        .css("display", 'block').css("height", "370px").css("width", "415px").css("margin-left", "150px").css("margin-top", "-80px")
                        .css("border-color", "transparent").css("background-color","#FFF");
                 $('#dynamictable').css("width","358px").css("height","573px").css("top","-241px").css("left","-108px").css("-webkit-transform","scale(0.4999)");
            }
            
        }
        
        function validate(){
            var from_name = $("#name").val();
            var email_subject = $("#subject").val();
            var to_email_addresses = $("#toaddress").val();
            var from_email_address = $("#formaddress").val();
            var reply_to_email_address = $("#email").val();
            var htmldata = formattedHTMLData;
            var email_list = $("email_list").val();
            var schedule_title = $("#schedule_title").val("");
            var schedule_time = $("#schedule_time").val("");
            var reg=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            var toemailvalid=reg.test(to_email_addresses);
            var split=to_email_addresses.split(',');
            var fromaddrvalid=reg.test(from_email_address);
            var replytoaddrvalid=reg.test(reply_to_email_address);
            if (from_name == ""){
                alert("From name not entered, please enter the from name");
                $("#name").focus();
                return false;
            }
            if (email_subject == ""){
                alert("Email subject name not entered, please enter the email subject");
                $("#subject").focus();
                return false;
            }
            if (from_email_address == ""){
                alert("From email address not entered, please enter the from email address");
                $("#formaddress").focus();
                return false;
            }
            if(fromaddrvalid == false){
                alert("From Address Not Valid, please Enter valid Email id");
                $("#formaddress").focus();
                return false;
            }
            if (to_email_addresses == ""){
                alert("To address fieled not entered, please enter the email address");
                $("#toaddress").focus();
                return false;
            }
            if(to_email_addresses !== ""){
                
                var split = to_email_addresses.split(",");
                              
                for (var i = 0; i < split.length; i++) {
                    //alert(split[i]+"  split length"+split.length);
                    var email=split[i].trim();
                    if(reg.test(email) !== "")
                    {
                        if(email !== "")
                        {
                            if(reg.test(split[i]) === false){
                                alert(" To Address field is not Valid, please Enter Valid Email Address \n\n'"+split[i]+"'\t is Invalid Email id");
                                $("#toaddress").focus();
                                return false;
                            } 
                        }
                    }
                 }
            }
            if (reply_to_email_address == ""){
                alert("Reply to email address not entered, please enter the reply to email address");
                $("#email").focus();
                return false;
            }
            if(replytoaddrvalid ==false){
                alert("Reply to email address is not Valid, please enter valid reply to email address");
                $("#email").focus();
                return false;
            }
            if (email_list == ""){
                alert("Email list not entered, please enter the email list");
                $("#email_list").focus();
                return false;
            }
            return true;
            }
            function sendEmail() {
                
                if(validate()){
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
                        email_list: $("#email_list").val(),
                        iframeName: $("#iframe_name").val()
                    },
                    success: function (responseText) {
                       
                        
                        $.ajax({
                            url: getHost() + "deleteEmailDraft.do?draftid="+draft_id,
                            type: "post",
                            success: function (responseText) {
                                if(responseText=="true")
                                {
                                    $('#loadingGif').remove();
                                    document.location.href = "emailsent.jsp";
                                }
                            },
                            error: function () {
                                alert("error");
                            }        
                        });
                    },
                    error: function () {
                        alert("error");
                    }
                });
            }
            }
            function validateact(){
               if(document.getElementById('email_actions').value === "0")
                {
                document.getElementById('schedule_title').disabled=false;
                document.getElementById('schedule_date').disabled=false;
                document.getElementById('schedule_time').disabled=false;
                 }
            else{
                document.getElementById('schedule_title').disabled=true;
                document.getElementById('schedule_date').disabled=true;
                document.getElementById('schedule_time').disabled=true;
                document.getElementById('schedule_title').value="";
                document.getElementById('schedule_time').value="";
                document.getElementById('schedule_date').value="";
                }
                 
        }
        
        function EmailListController($scope, $http) {

                $scope.addEmailList = function () {
                    var email_list_name = $("#listname").val();
                    var email_list = $("#textArea").val();
                
                    var Emails = {"emailListName": email_list_name, "emailAddresses": email_list , "update": "addUpdateEmailList"};
                        $http({
                            method: 'POST',
                            url: getHost() + 'SetEmailLists',
                            headers: {'Content-Type': 'application/json'},
                            data: Emails
                        }).success(function (data)
                        {
                            if (data === "true") {
                                alert("Data saved successfully");
                               window.open(getHost() + 'emailsubject.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                };

                $scope.showEmailList = function () {
                                $(".emaillist").show();
                                $("#email_list_name").hide();

                        var emailids = {"update": "allEmailListNames"};
                        $http({
                            method: 'GET',
                            url: getHost() + 'GetEmailLists?update=allEmailListNames'
                    }).success(function(data, status, headers, config) {
                            $scope.emailLists = data.user;
                            $scope.emailLists_mindbody = data.mindbody;
                            if (data === "true") {
//                                window.open(getHost() + 'emaillists.jsp', "_self");
                            } else if (data === error) {
                                alert(data);
                            }
                        });
                };
                
                $scope.clearfields = function() {
                    $("#email_list_name").val("");
                    $("#emailaddresses").val("");
                    $("#fileUpload").val("");
                    $("#chooseEmailList").val("");
                };
            }
       
        function selectCsvFile(){
                $("#chooseEmailList").show();
//                 $('#chooseEmailList').val("1").trigger('change');
                 
                var x = document.getElementById("chooseEmailList").selectedIndex;
                var list_name = document.getElementsByTagName("option")[x].value;
                if (list_name == 1){                   
                    $("#emailIdContinueButton").show();
                    $("#entertext").show();
                    $("#dragtext").show();
                    $("#emailaddresses").show();
                    $("#emailaddresses").val('');
                    $("#drop-zone").show();
                    $("#clickHere").show();
                    $("#upload").show();
                    $("#emailIdContinueButton").css("top","0px");
                    $(function () {

                    var dropZoneId = "drop-zone";
                    var buttonId = "clickHere";
                    var mouseOverClass = "mouse-over";

                    var dropZone = $("#" + dropZoneId);
                    var ooleft = dropZone.offset().left;
                    var ooright = dropZone.outerWidth() + ooleft;
                    var ootop = dropZone.offset().top;
                    var oobottom = dropZone.outerHeight() + ootop;
                    var inputFile = dropZone.find("input");

                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                        dropZone.addClass(mouseOverClass);
                        var x = e.pageX;
                        var y = e.pageY;

                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
                            inputFile.offset({ top: y - 15, left: x - 100 });
                        } else {
                            inputFile.offset({ top: -400, left: -400 });
                        }

                    }, true);

                    if (buttonId != "") {
                        var clickZone = $("#" + buttonId);

                        var oleft = clickZone.offset().left;
                        var oright = clickZone.outerWidth() + oleft;
                        var otop = clickZone.offset().top;
                        var obottom = clickZone.outerHeight() + otop;

                        $("#" + buttonId).mousemove(function (e) {
                            var x = e.pageX;
                            var y = e.pageY;
                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                                inputFile.offset({ top: y - 15, left: x - 160 });
                            } else {
                                inputFile.offset({ top: -400, left: -400 });
                            }
                        });
                    }

                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
                        $("#" + dropZoneId).removeClass(mouseOverClass);
                        alert("file have been added, click on the upload button to load the csv file");
    //                    upload();
                    }, true);
                    });

                }else {
                   $("#emailIdContinueButton").show();
                    $("#entertext").show();
                    $("#dragtext").show();
                    $("#emailaddresses").show();
//                    $("#emailaddresses").val('');
                    $("#drop-zone").show();
                    $("#clickHere").show();
                    $("#upload").show();
                    $("#emailIdContinueButton").css("top","0px");
                    $(function () {

                    var dropZoneId = "drop-zone";
                    var buttonId = "clickHere";
                    var mouseOverClass = "mouse-over";

                    var dropZone = $("#" + dropZoneId);
                    var ooleft = dropZone.offset().left;
                    var ooright = dropZone.outerWidth() + ooleft;
                    var ootop = dropZone.offset().top;
                    var oobottom = dropZone.outerHeight() + ootop;
                    var inputFile = dropZone.find("input");

                    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
                        e.preventDefault();
                        e.stopPropagation();
                        dropZone.addClass(mouseOverClass);
                        var x = e.pageX;
                        var y = e.pageY;

                        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
                            inputFile.offset({ top: y - 15, left: x - 100 });
                        } else {
                            inputFile.offset({ top: -400, left: -400 });
                        }

                    }, true);

                    if (buttonId != "") {
                        var clickZone = $("#" + buttonId);

                        var oleft = clickZone.offset().left;
                        var oright = clickZone.outerWidth() + oleft;
                        var otop = clickZone.offset().top;
                        var obottom = clickZone.outerHeight() + otop;

                        $("#" + buttonId).mousemove(function (e) {
                            var x = e.pageX;
                            var y = e.pageY;
                            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                                inputFile.offset({ top: y - 15, left: x - 160 });
                            } else {
                                inputFile.offset({ top: -400, left: -400 });
                            }
                        });
                    }

                    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
                        $("#" + dropZoneId).removeClass(mouseOverClass);
                        alert("The CSV file has been added, click on the upload button to load the CSV file in the form.");
    //                    upload();
                    }, true);
                    });

                }

            }
        </script>
        <jsp:include page="basejsp.jsp" />
    </head>

    <body>
        <div id="fade" class="black_overlay"></div>
        <div  class="col-md-1" id="warn"><jsp:include page="leftmenu.html"/></div>
        <div class="row" ng-controller="emailSettings" id="emailSettings">
            
            <div class="col-md-5 " ng-init="getEmailSettings()">
                <p id="textgrt" class="MH1 col-md-offset-3">SEND EMAIL PREVIEW</p>
                <p id="t2">go back</p>
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
                            <input id="formaddress" class="form-control simplebox" name="from_email_address" type="email" required value="{{email_settings.from_address}}">
                            <label>FROM ADDRESS</label><br>
                        </div>
                    </div>
                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <input id="toaddress" class="form-control simplebox" name="email_addresses" type="text" value="">
                            <label>TO ADDRESS</label><br>
                        </div>
                    </div>

                    <div class="group">
                        <div class="col-md-5 col-md-offset-5">
                            <input id="email" class="form-control simplebox" name="reply_to_email_address" type="email" required value="{{email_settings.reply_email_address}}">
                            <label>REPLY TO EMAIL</label><br><br>
                        </div>
                    </div>
                    <div  class="form-group">
                        <div class="col-md-3 col-md-offset-3">
                            <br><br><button type="button" onclick="sendEmail()" class="button button--moema button--text-thick button--text-upper button--size-s">SEND</button><br><br><br>
                        </div>
                        <div class="col-md-1 col-md-offset-1">
                            <br><br><a href = "javascript:void(0)" ><button type="button" onclick="displaySchedule()" class="button button--moema button--text-thick button--text-upper button--size-s">SCHEDULE</button></a><br><br><br>
                        </div>
                    </div>
                            
                            <input type="hidden" id="email_list" value='' name="email_list">
                            <input type="hidden" id="iframe_name" value='<%=iframeName%>'>
                                
                </form>
            </div>
            <div class="col-md-4">
    <!--
                    <ul class="images">
                        <li><div id="iphone" class="img-responsive fancybox" onMouseOver="javascript:showLightBox()" onMouseOut="javascript:stopShowLightBox()" style="cursor: pointer;background-image: url('images/iphone 6 screen.png');"></div></li>
                        <li><img id="imac" class="img-responsive fancybox" src="images/IMAC.png"></li>
                        <li ><img id="ipad" class="img-responsive fancybox" src="images/IPAD3.png"></li>

                    </ul>-->

                <ul class="images col-md-offset-3">
                    <li><div id="iphone" class="img-responsive ptr" onclick="show('iphone');" style="background-image: url('images/Phone.svg');background-repeat: no-repeat; -webkit-background-size: contain;"></div></li>
                    <li><div id="imac" class="img-responsive ptr" onclick="show('imac');"  style="background-image: url('images/imac27.png');background-repeat: no-repeat; -webkit-background-size: contain;"></div></li>
                    <li><div id="ipad" class="img-responsive ptr" onclick="show('ipad');"  style="background-image: url('images/Tablet.svg');background-repeat: no-repeat; -webkit-background-size: contain;"></div></li>
                </ul>
                <div id="popupschedule" style="display:none;">
                    <div id="content" ng-init="getProgramNames()">
                        <!--                                 Mapper file name<input type="text" id="mapperxml" required><br><br>
                                                    Layout file name<input type="text" id="layoutxml" required><br>-->

                        <p class="SH2" style="width:600px;">PLEASE SELECT A TIME FROM YOUR PLAN</p> 
                        <div id="light" class="white_content">
                            <a href = "javascript:void(0)" 
                               onclick = "document.getElementById('light').style.display = 'none';
                               document.getElementById('fade').style.display = 'none';
                               document.body.style.overflow = 'scroll';" 
                               ng-app=""style="text-decoration:none;">
                        <p style="margin-left:740px;margin-top:-35px;cursor: pointer;" id="hidepopup" onclick="hidepopup()" ><img src="images/CloseIcon.svg" height="25" width="25"/></p></a></div>
                        <select name="programs" id="programs" class="SH1 selectsocialact" style="font-variant: normal;">
                            <option value="0" style="background:#fff;" >--General--</option>
                            <option style="background:#fff;" ng-repeat="programs in marketing_programs" value="{{programs.program_id}}">{{programs.name}}</option>
                        </select><br><br>
                        
                        <select class="SH1 selectsocialact" style="font-variant: normal;" name="email_actions" id="email_actions" onchange="validateact()">
                            <option value="0" style="background:#fff;">CUSTOM</option>
                            <option ng-repeat="actions in email_actions" value="{{actions.id}}">{{actions.schedule_title}}</option>
                        </select>
                        <p class="SH2" style="position:relative;top:10px;">OR</p>
                        <p class="SH2" style="position:relative;top:10px;width:700px;">PLEASE CREATE A NEW TITLE AND TIME TO ADD AN ACTION TO YOUR PLAN</p>                       
                        <br>
                        <input type="text" class="simpleinpbox SH2" id="schedule_title" name="schedule_title" placeholder="TITLE" style="font-variant: normal;"><br>
                        
                        <input type="text" readonly id="schedule_date" name="schedule_date" class="simpleinpbox SH2 ptr" style="width:190px;font-variant: normal;" value="" placeholder="DATE">
                                        <script>
                                    var picker = new Pikaday(
                                    {
                                        field: document.getElementById('schedule_date'),
                                        firstDay: 1,
                                        minDate: new Date(2000, 0, 1),
                                        maxDate: new Date(2050, 12, 31),
                                        yearRange: [2000,2050]
                                    });
                                        </script><br>
                                        <input id="schedule_time" type="text" value="" name="schedule_time" class="simpleinpbox SH2 ptr " style="width:150px;" placeholder="TIME"/><br>
                                     <script src="js/timepicki.js" type="text/javascript"></script>
                                    <script>
                                        $('#schedule_time').timepicki();
                                    </script>
                                    <script src="js/bootstrap.min.js" type="text/javascript"></script>
                        
                        <input type="button" ng-click="setScheduling()" id ="schedulethepost" value="SCHEDULE" class="button button--moema button--text-thick button--text-upper button--size-s" style="width:170px;margin-left:0px;font-family:'proxima-nova',sans-serif;font-size:14px;" />  
                    </div>
                </div>
                <div class="iphoneshow img-responsive" id="popup" style="background-repeat: no-repeat; -webkit-background-size: contain; display: none;">
                    <div class="content">  
                        <iframe id='dynamictable' style='position:relative;background-color:#FFF;' src='<%=iframeUrl%>'></iframe>                   
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="emaillistdiv">
            <div  id="emaillist2" ng-controller="EmailListController" ng-init="showEmailList()">
                    <p class="header1 MH2"> Who do you want to send this email to?</p>
                    <br><br>   
                   
                    <select id="chooseEmailList" name="chooseEmailList">
                        <option value="1">Manual</option>
                        <option style="background:#fff;" ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                        <option style="background:#fff;" ng-repeat ="Lists in emailLists_mindbody" value="{{Lists}}">{{Lists}}</option>
                    </select>
                    
                    
                    <input type="button" id="addCsvFileButton" onclick="selectCsvFile()" class=" btn-csv BT1" value="Add CSV or Email Manually">
                    <p style="position:relative;top:45px;margin-left: 24%;" id="dragtext" hidden="true">Drag and drop a csv file here and click to upload:</p>
                    <p style="position:relative;top:16px;left:26em;margin-left: 25%;" id="entertext" hidden="true">Enter Email Addresses manually here:</p>
                    <div style="display:inline-flex;margin-top:14px;">
                        <div id="drop-zone">
                        Drop files here...
                        <div id="clickHere" >
                            <p id="fileselect" style="font-size:20px;top:60px;position:relative;">Click to Select file</p> 
                     
                      <input type="file" name="file"  id="file" style="overflow:hidden;position: absolute;top: 0;right: 0;margin: 0;width:250px;border-radius:15px;border:none;padding: 0;font-size: 20px;cursor: pointer;opacity: 0;filter: alpha(opacity=0);" onchange="fileselected()"/><br><br>
                        </div>
                    </div>
                        <div>
                            
                            <div>
                                <textarea style="width:300px; height:200px;position:relative;top:0px;" id="emailaddresses"></textarea>
                            </div>
                            
                        </div>
                    </div>
                    <input type="button" id="upload" value="Upload" onclick="upload()" class="btn-upload" style="margin-left: 24%;position:relative;left:2px;top:5px;"/>
                    <input style="position:relative;top:25px;margin-left:7%;" id="emailIdContinueButton" type="button" class=" button button--moema button--text-thick button--text-upper button--size-s btn-prim" value="CONTINUE">
                   
               

                    <div id="popup">
                        <div id="content">
                            Enter List Name: <input type="text" id="listname"><br><br>
                            Csv File: <input type="file" id="selectedCsvFile">
                            <input type="button" onclick="uploadCsvFileData()" value="upload">
                            <br><br>
                            <center>
                                <textarea id="textArea" style="width:300px; height:100px; resize: none;" ></textarea><br><br>
                                <input type="button" class="btn btn-default" ng-click="addEmailList()" value="Save">
                                <input type="button" id="popupCancel" class="btn btn-default" value="Cancel">
                            </center>
                        </div>   
                    </div>      
                      
<!--                    <input type="text" class="hideinputEmailId" id="emailId" name="emailsubject" placeholder="Add CSV or Email Manually"> <br><br><br><br><br>-->

                   </div>  
        </div>
<!--<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>--> 
    </body>
</html>
