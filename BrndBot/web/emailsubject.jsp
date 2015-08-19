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
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
       <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <link href="css/popup.css" rel="stylesheet" type="text/css"/>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.csv-0.71.js" type="text/javascript"></script>
        <script src="js/angular.min.js" type="text/javascript"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <title>email subject</title>
        <style>
            
        .header1{
            font-family: "proxima-nova",sans-serif;
            font-style: normal;
            font-weight: 600;
            font-size:21.5px;
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
                font-size:35px;
                position: relative;
                padding-top:7%;
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
            #chooseEmailList{
                position: relative;
                padding-top: 7%;
                background-color: #e4e4e4;
                border: 1px solid #DADADA;
                height:1rem;
                width:350px;
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
                top: 85%;
                width: 150px;
                background-color: #00A37A;
                border-color: #00A37A;
            }
            .btn-csv{
                border-radius:10px;
                position: fixed;
                top: 30%;
                left:66%;
                width: 200px;
                background-color:transparent;
                border-color: #00A37A;
            }
            .btn-prim{
                border-radius:10px;
                position: fixed;
                top: 70%;
                left:18%;
                width: 150px;
                background-color: #00A37A;
                border-color: #00A37A;
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
                $("#emaillist").show();
                $("#emailsubjectdiv").hide();
            });
            
             $("#popupCancel").click(function () {      
                $("#popup").hide();
            });
        });
            function selectCsvFile(){
                  $("#popup").show();
                
            }
   
   function uploadCsvFileData(){
        var fileUpload = document.getElementById("selectedCsvFile");
               var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
               if (regex.test(fileUpload.value.toLowerCase())) {
                   if (typeof (FileReader) != "undefined") {
                       var reader = new FileReader();
                       reader.onload = function (e) {
                           var table = document.createElement("table");
                           var rows = e.target.result.split("\n");
//                           alert(rows);
                           document.getElementById("textArea").value = rows;
//                            for (var i = 0; i < rows.length; i++) {
//                                var row = table.insertRow(-1);
//                                var cells = rows[i].split(",");
//                                for (var j = 0; j < cells.length; j++) {
//                                   var cell = row.insertCell(-1);
//                                   cell.innerHTML = cells[j];
//                                }
//                            }
                       }
                       reader.readAsText(fileUpload.files[0]);
                       
                   } else {
                       alert("This browser does not support HTML5.");
                   }
               } else {
                   alert("Please upload a valid CSV file.");
               }
               
           }
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
            
        $(document).ready(function () { 
            
              $("#chooseEmailList").change(function () {
                var x = document.getElementById("chooseEmailList").selectedIndex;
                var List_name = document.getElementsByTagName("option")[x].value;
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
                            email_list:email_list
                        },
                        success: function(result){
//                            alert("Email sent successfully");
                        document.location.href = "emaileditor.jsp?id="+<%= mindbody_id %>;
                        }
                });
                
                });        
        });
            
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
                            url: getHost() + 'GetEmailLists?update=allEmailListNames',
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
            <div id="datadiv" class="col-md-10 col-md-offset-2">
                <div id="emailsubjectdiv">
                    <p class="header1"> Enter the Subject Line of the Email:</p>
                    <p class="header2">This can be edited later.</p><br><br>
                    <input type="text" class="hideinputborder" id="emailsubject" name="emailsubject" placeholder="SUBJECT LINE">
                    <hr style="position:relative;left:-22%;width:550px;height:1px;background-color:#777;">
                    <input  id="emailSubjectContinueButton" type="button" class="btn btn-primary" value="CONTINUE">
                </div>

                <div  id="emaillist" ng-controller="EmailListController" ng-init="showEmailList()">
                    <p class="header1"> Who do you want to send this email to?</p>
                    <p class="header2">This can be edited later.</p><br><br>
                      
                    <select maxheight="-1" data-dojo-type="dijit/form/Select" id="chooseEmailList" name="chooseEmailList" class="emaillist" hidden="true">
                        <option  ng-repeat ="Lists in emailLists" value="{{Lists}}">{{Lists}}</option>
                    </select>
                    <div class="col-md-3 col-md-offset-6" style="position: relative;top:-160px;left:8%; "> 
                          <input type="button" id="addCsvFileButton" onclick="selectCsvFile()" class="btn btn-csv" value="Add CSV or Email Manually"><br>
                          <p style="width:200px;">Drag CSV here enter emails manually</p>
                  <textarea  style="width:300px; height:100px;" id="emailaddresses"></textarea></div>
<!--                    <input type="text" id="htmltext" name="htmltext" value=""/>-->
                    

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
                    <input  id="emailIdContinueButton" type="button" class=" btn btn-primary btn-prim" value="CONTINUE">
                   </div>                   
            </div>      
        </div>

       
        

       
    </body>
</html>
