<%-- 
    Document   : emailsubject
    Created on : 29 Jul, 2015, 4:38:08 PM
    Author     : sandeep-kumar 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="js/angular.min.js" type="text/javascript"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<!--        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <link href="css/popup.css" rel="stylesheet" type="text/css"/>
           <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
        <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
                <link rel="stylesheet" href="css/main1.css">
        <script src="js/jquery.csv-0.71.js" type="text/javascript"></script>
        <title>email subject</title>
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
            width: 300px;
            /*Sort of important*/
            height: 200px;
            position:absolute;
            left:80%;
            top:-100px;
            margin-left:-50px;
            border: 2px dashed rgba(0,0,0,.3);
            border-radius: 20px;
            font-family: Arial;
            text-align: center;
            position: relative;
            line-height: 180px;
            font-size: 20px;
            color: rgba(0,0,0,.3);
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
                left: -2%;
                top: 0%;
                margin-left: 20px;
                margin-top: 20px;
                line-height: 26px;
                color: white;
                font-size: 12px;
                width: 270px;
                height: 150px;
                border-radius: 4px;
                background-color: #3b85c3;
            }

            #clickHere:hover {
                background-color: #4499DD;

            }            
        .header1{
            font-family: "proxima-nova",sans-serif;
            font-style: normal;
            font-weight: 600;
            color: #3f4042;
            text-align: left;
            line-height: 25.8px;
            letter-spacing: 0em;
            opacity: 1;
            position: relative;
            padding-top: 17%;
           
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
            #chooseEmailList{
                
                color: #3f4042;
                background-color: #ccc;
                border: 1px solid #DADADA;
                height:40px;
                width:300px;
                font-size: 18px;
                border-radius: 5px;
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
            .btn-csv:focus{
             outline: none;
            }
            .btn-csv{
                border-radius:20px;
                position: fixed;
                top: 62%;
                right:50%;
                width: 200px;
                background-color:transparent;
                border-color: #003399;
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

        

            .header1{
                font-size: 28px;
            }
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
        <%! String mindbody_id=""; %>
        <% mindbody_id = request.getParameter("id"); %>
        <script>
            
            $(document).ready(function () {
                
                
                $(".cross").hide();
                $(".menu").hide();
                $("#emaillist").hide();
                $("#popup").hide();

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

                $("#emailSubjectContinueButton").click(function () {
                    
                    $("#chooseEmailList").show();
                    $("#emailsubjectdiv").hide();
                    $("#emailaddresses").hide();
                    $("#drop-zone").hide();
                    $("#clickHere").hide();
                    $("#upload").hide();
                    $("#emaillist").show();

                });
          
                $("#chooseEmailList").change(function () {
                var x = document.getElementById("chooseEmailList").selectedIndex;
                var List_name = document.getElementsByTagName("option")[x].value;
                
                if (List_name == 0){
                    $("#emailaddresses").hide();
                    $("#drop-zone").hide();
                    $("#clickHere").hide();
                    $("#upload").hide();
                }else {
                    
                    $("#email_list_name").val(List_name);
                    $.ajax({
                            url: getHost() + "GetEmailLists",
                            data: {
                                update: "emailsForEmailList",
                                list_name: List_name
                            },
                            success: function(result){
    //                            alert(JSON.stringify(result));
                                var email_addresses = JSON.stringify(result.emailAddresses);
                                var email_add = email_addresses.replace("\"",'');
                                var email_address = email_add.replace("\"",'');
                                $("#emailaddresses").val(email_address);
                            }
                    });
                }
                });

                $("#emailIdContinueButton").click(function () {
                    var email_subject = $("#emailsubject").val();
                    var email_addresses = $("#emailaddresses").val();
                    var email_list = $("#chooseEmailList").val();
                        $.ajax({
                        url: getHost() + "EmailTextDataServlet",
//                        dataType: 'json',
                        data: {
                            email_subject: email_subject,
                            email_addresses: email_addresses,
                            email_list : email_list
                        },
                        success: function(result){
//                            alert("Email sent successfully");
                        document.location.href = "emaileditor.jsp?id="+<%= mindbody_id %>;
                        }
                });

        //
//
//                 $("#popupCancel").click(function () {      
//                    $("#popup").hide();
//                });
                
            

            });
              });
            
        </script>
        
         <script>
//           $(document).ready(function () { 
//               
//            
//        });
//        
            function selectCsvFile(){
                $("#chooseEmailList").show();
                var x = document.getElementById("chooseEmailList").selectedIndex;
                var list_name = document.getElementsByTagName("option")[x].value;
//                    $("#emailsubjectdiv").show();
                if (list_name != 0){

                    $("#emailaddresses").show();
                    $("#drop-zone").show();
                    $("#clickHere").show();
                    $("#upload").show();
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
                    alert("please select a list");
                }

            }
            //   
//   function uploadCsvFileData(){
//        var fileUpload = document.getElementById("selectedCsvFile");
//               var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
//               if (regex.test(fileUpload.value.toLowerCase())) {
//                   if (typeof (FileReader) != "undefined") {
//                       var reader = new FileReader();
//                       reader.onload = function (e) {
//                           var table = document.createElement("table");
//                           var rows = e.target.result.split("\n");
////                           alert(rows);
//                           document.getElementById("textArea").value = rows;
////                            for (var i = 0; i < rows.length; i++) {
////                                var row = table.insertRow(-1);
////                                var cells = rows[i].split(",");
////                                for (var j = 0; j < cells.length; j++) {
////                                   var cell = row.insertCell(-1);
////                                   cell.innerHTML = cells[j];
////                                }
////                            }
//                       }
//                       reader.readAsText(fileUpload.files[0]);
//                       
//                   } else {
//                       alert("This browser does not support HTML5.");
//                   }
//               } else {
//                   alert("Please upload a valid CSV file.");
//               }
//               
//           }
//           
//          function saveEmailList(){
//               var emailId=$("#textArea").val();
//             var filter =/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
//             var filter1=/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+(([a-zA-Z0-9]{2,4})+\,)/;
//               if(filter.test(emailId) ||filter1.test(emailId)){
//                   alert("true");
//               }
//              else{
//                  alert("false");
//              }
//          } 
//       
   
        </script>
        <script>
            
//        $(document).ready(function () { 
//            $(function () {
//                
//
//                var dropZoneId = "drop-zone";
//                var buttonId = "clickHere";
//                var mouseOverClass = "mouse-over";
//
//                var dropZone = $("#" + dropZoneId);
//                var ooleft = dropZone.offset().left;
//                var ooright = dropZone.outerWidth() + ooleft;
//                var ootop = dropZone.offset().top;
//                var oobottom = dropZone.outerHeight() + ootop;
//                var inputFile = dropZone.find("input");
//                
//                document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
//                    e.preventDefault();
//                    e.stopPropagation();
//                    dropZone.addClass(mouseOverClass);
//                    var x = e.pageX;
//                    var y = e.pageY;
//
//                    if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
//                        inputFile.offset({ top: y - 15, left: x - 100 });
//                    } else {
//                        inputFile.offset({ top: -400, left: -400 });
//                    }
//
//                }, true);
//
//                if (buttonId != "") {
//                    var clickZone = $("#" + buttonId);
//
//                    var oleft = clickZone.offset().left;
//                    var oright = clickZone.outerWidth() + oleft;
//                    var otop = clickZone.offset().top;
//                    var obottom = clickZone.outerHeight() + otop;
//
//                    $("#" + buttonId).mousemove(function (e) {
//                        var x = e.pageX;
//                        var y = e.pageY;
//                        if (!(x < oleft || x > oright || y < otop || y > obottom)) {
//                            inputFile.offset({ top: y - 15, left: x - 160 });
//                        } else {
//                            inputFile.offset({ top: -400, left: -400 });
//                        }
//                    });
//                }
//
//                document.getElementById(dropZoneId).addEventListener("drop", function (e) {
//                    $("#" + dropZoneId).removeClass(mouseOverClass);
//                    alert("file have been added, click on the upload button to load the csv file");
////                    upload();
//                }, true);
//            });

              function upload() {
                var fileUpload = document.getElementById("file");
                alert(fileUpload.value.toLowerCase());
                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
                if (regex.test(fileUpload.value.toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = document.createElement("table");
                            var rows = e.target.result.split("\n");
                            alert(rows);
//                            $('#emailaddresses').val(rows);
//                            $('#emailaddresses').append(rows);
                             if ($('#emailaddresses').val() == "") {
                                $('#emailaddresses').val(rows);
                            } else {
                                $('#emailaddresses').val($('#emailaddresses').val() + ", " + rows);
                            }
                        }
                        reader.readAsText(fileUpload.files[0]);

                    } else {
                        alert("This browser does not support HTML5.");
                    }
                } else {
                    alert("Please upload a valid CSV file.");
                }
            }
            
                
//                });        
//        });
            
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
                            $scope.emailLists = data.allEmailListNames
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
       
            
        </script>
    </head>
    <%! 
        String html_text = "";
    %>
    <%
        try{
//            html_text = request.getParameter("htmlString");
        }catch (Exception e){
            
        }
     %>
    <body ng-app>
                   <div class="row">
                 <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div><!--/end left column-->
            <div id="datadiv" class="col-md-8 col-md-offset-2">
                <div id="emailsubjectdiv">
                    <p class="header1 MH2"> Enter the Subject Line of the Email:</p>
                   <div class="sublingrp">
                   <div class="col-md-5 col-md-offset-0">                            
                       <input id="emailsubject" class="inputsubline" name="emailsubject"  type="text" required>
                        <label>SUBJECT LINE</label><br>
                    </div>
                    </div>
         
              
                    <input  id="emailSubjectContinueButton" type="button" class="button button--moema button--text-thick button--text-upper button--size-s btn-prim" value="CONTINUE">

                </div>


                <div  id="emaillist" ng-controller="EmailListController" ng-init="showEmailList()">
                    <p class="header1 MH2"> Who do you want to send this email to?</p>
                    <br><br>   
                   
                    <select id="chooseEmailList" name="chooseEmailList" class="emaillist slt" hidden="true">
                        <option value="0">Select</option>
                        <option style="background:#fff;" ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                    </select>
                    <div id="drop-zone">
                        Drop files here...
                        <div id="clickHere">
                            or click here..
                            <input type="file" name="file" id="file" style="width:300px;"/><br><br>
                        </div>
                    </div>

                  
                    
                    <input type="button" id="upload" value="Upload" onclick="upload()"  style="position:relative;left:32.7em;top:-90px;border-radius:15px;"/>
                    <input type="button" id="addCsvFileButton" onclick="selectCsvFile()" class="btn btn-csv BT1" value="Add CSV or Email Manually">
                    <textarea style="width:300px; height:100px;position:relative;left:42em;top:-20px;" id="emailaddresses"></textarea><br><br>
               

                    <div id="popup">
                        <div id="content">
                            Enter List Name: <input type="text" id="listname"><br><br>
                            Csv File: <input type="file" id="selectedCsvFile">
                            <input type="button" onclick="uploadCsvFileData()" value="upload">
                            <br><br>
                            <center>
                                <textarea id="textArea" style="width:300px; height:100px;"></textarea><br><br>
                                <input type="button" class="btn btn-default" ng-click="addEmailList()" value="Save">
                                <input type="button" id="popupCancel" class="btn btn-default" value="Cancel">
                            </center>
                        </div>   
                    </div>                          
<!--                    <input type="text" class="hideinputEmailId" id="emailId" name="emailsubject" placeholder="Add CSV or Email Manually"> <br><br><br><br><br>-->
                    <input  id="emailIdContinueButton" type="button" class=" button button--moema button--text-thick button--text-upper button--size-s btn-prim" value="CONTINUE">
                   </div>                   
            </div>      
<!--        </div>-->
       
    </body>
</html>
